package com.basis.strategy.login;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.basis.annotations.LoginTypeAnno;
import com.basis.common.ResponseCode;
import com.basis.common.Result;
import com.basis.exception.BusinessException;
import com.basis.mapper.RoleMapper;
import com.basis.mapper.UserMapper;
import com.basis.model.entity.User;
import com.basis.model.vo.LoginVo;
import com.basis.utils.PasswordUtils;
import com.basis.utils.ThrowUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static com.basis.common.ResponseCode.*;
import static com.basis.model.constant.BasicConstant.ROLE;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 用户名密码登录
 */
@Service
@LoginTypeAnno("NORMAL")
public class UsernamePasswordLoginStrategy implements LoginStrategy {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Result<String> login(LoginVo vo) {
        // 校验参数
        ThrowUtil.throwIf(StrUtil.isEmpty(vo.getUsername()) || StrUtil.isEmpty(vo.getPassword()), new BusinessException(USERNAME_OR_PASS_EMPTY));
        // 根据用户名查询用户是否存在
        User one = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, vo.getUsername()).last("LIMIT 1"));
        // 校验是否存在
        ThrowUtil.throwIf(Objects.isNull(one), new BusinessException(USER_NOT_EXIST));
        // 校验密码
        // 获取用户加密盐
        String salt = one.getSalt();
        // 校验密码是否正确
        ThrowUtil.throwIf(!PasswordUtils.matches(salt, vo.getPassword(), one.getPassword()), new BusinessException(PASSWORD_ERROR));
        // 根据用户 ID 获取角色列表
        List<String> roles = roleMapper.selectRolesByUserId(one.getId());
        // 执行登录
        StpUtil.login(one.getId());
        // 设置具体 TOKEN Session 权限
        StpUtil.getSession().set(ROLE, roles);
        // 返回 Token 值
        return Result.success(StpUtil.getTokenValue());
    }
}
