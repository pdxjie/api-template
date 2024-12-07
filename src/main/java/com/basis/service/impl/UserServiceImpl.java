package com.basis.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basis.mapper.RoleMapper;
import com.basis.mapper.UserMapper;
import com.basis.mapper.UserRoleMapper;
import com.basis.model.entity.User;
import com.basis.model.entity.UserRole;
import com.basis.service.IUserService;
import com.basis.utils.WeChatUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.basis.model.contant.BasicConstant.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author IT 派同学
 * @since 2024-12-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private WeChatUtils weChatUtils;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 微信授权登录
     *
     * @param code 授权码
     * @return Token 值
     */
    @Override
    public String wechatAuthorizedLogin(String code) {
        // 获取微信用户 ID
        final String openId = weChatUtils.getOpenId(code);
        //是否存在
        User one = getOne(new QueryWrapper<User>()
                .lambda()
                .eq(User::getOpenId, openId)
                .select(User::getId, User::getOpenId));
        // 不存在则写入 DB
        if (Objects.isNull(one)) {
            one = new User();
            one.setCreateTime(LocalDateTime.now());
            one.setUpdateTime(LocalDateTime.now());
            one.setOpenId(openId);
            one.setNickName(DEFAULT_NICK_NAME);
            // 保存用户信息
            save(one);
            // 设置默认角色
            UserRole userRole = new UserRole();
            userRole.setId(one.getId());
            // 默认新用户只有普通用户角色
            userRole.setRId(DEFAULT_USER_ROLE_ID);
            userRoleMapper.insert(userRole);
        }
        // 根据用户 ID 获取角色列表
        List<String> roles = roleMapper.selectRolesByUserId(one.getId());
        // 执行登录
        StpUtil.login(one.getId());
        // 设置具体 TOKEN Session 权限
        StpUtil.getSession().set(OPEN_ID, openId).set(ROLE, roles);
        return StpUtil.getTokenValue();
    }

    /**
     * 退出登录
     */
    @Override
    public void logout() {
        // 判断是否已经登录
        if (StpUtil.isLogin()) {
            // 退出登录
            StpUtil.logout();
        }
    }
}
