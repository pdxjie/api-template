package com.basis.strategy.login;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.basis.annotations.LoginTypeAnno;
import com.basis.common.ResponseCode;
import com.basis.common.Result;
import com.basis.exception.BusinessException;
import com.basis.mapper.UserMapper;
import com.basis.model.entity.User;
import com.basis.model.vo.LoginVo;
import com.basis.service.IUserRoleService;
import com.basis.utils.RedisUtils;
import com.basis.utils.ThrowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.basis.model.constant.BasicConstant.DEFAULT_NICK_NAME;
import static com.basis.model.constant.BasicConstant.PHONE_CODE_PREFIX;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 手机号登录
 */
@Service
@LoginTypeAnno("PHONE")
public class PhoneLoginStrategy implements LoginStrategy {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public Result<String> login(LoginVo vo) {
        // 校验参数
        ThrowUtil.throwIf(StrUtil.isEmpty(vo.getPhone()) || StrUtil.isEmpty(vo.getCode()), new BusinessException(ResponseCode.PHONE_OR_CODE_EMPTY));
        // 从 Redis 中获取验证码
        String key = StrUtil.join(PHONE_CODE_PREFIX, vo.getEmail());
        String code = (String) redisUtils.getValue(key);
        // 校验验证码
        ThrowUtil.throwIf(Objects.isNull(code) || StrUtil.isEmpty(code), new BusinessException(ResponseCode.CODE_NOT_EXISTED));
        // 对比验证码是否正确
        ThrowUtil.throwIf(!vo.getCode().equals(code), new BusinessException(ResponseCode.CODE_NOT_CORRECT));
        // 验证码无误后随即删除缓存中的验证码
        redisUtils.delKey(key);
        // 根据用户名查询用户是否存在
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, vo.getPhone()).last("LIMIT 1"));
        // 校验是否存在
        if (Objects.isNull(user)) {
            user = new User();
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            user.setUserName(vo.getUsername());
            user.setIsDeleted(false);
            user.setPhone(vo.getPhone());
            user.setNickName(DEFAULT_NICK_NAME);
            user.setSex(2); // 默认未知
            userMapper.insert(user);
            // 分配初始角色
            userRoleService.assignmentRole(user.getId());
        }
        // 执行登录
        StpUtil.login(user.getId());
        // 返回 Token 值
        return Result.success(StpUtil.getTokenValue());
    }
}
