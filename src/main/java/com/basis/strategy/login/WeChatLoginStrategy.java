package com.basis.strategy.login;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.basis.annotations.LoginTypeAnno;
import com.basis.common.Result;
import com.basis.mapper.UserMapper;
import com.basis.mapper.UserRoleMapper;
import com.basis.model.entity.User;
import com.basis.model.entity.UserRole;
import com.basis.model.vo.LoginVo;
import com.basis.utils.WeChatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.basis.model.constant.BasicConstant.DEFAULT_NICK_NAME;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 微信小程序授权登录
 */
@Service
@LoginTypeAnno("WECHAT")
public class WeChatLoginStrategy implements LoginStrategy {

    @Resource
    private UserMapper userMapper;

    @Resource
    private WeChatUtils weChatUtils;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Value("${system.default.roleId}")
    private Long defaultRoleId;

    @Override
    public Result<String> login(LoginVo vo) {
        // 获取微信用户 ID
        final String openId = weChatUtils.getOpenId(vo.getWechatCode());
        //是否存在
        User one = userMapper.selectOne(new QueryWrapper<User>()
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
            userMapper.insert(one);
            // 设置默认角色
            UserRole userRole = new UserRole();
            userRole.setId(one.getId());
            // 默认新用户只有普通用户角色
            userRole.setRId(defaultRoleId);
            userRoleMapper.insert(userRole);
        }
        // 执行登录
        StpUtil.login(one.getId());
        return Result.success(StpUtil.getTokenValue());
    }
}
