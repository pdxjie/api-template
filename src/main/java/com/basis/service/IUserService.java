package com.basis.service;

import com.basis.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author IT 派同学
 * @since 2024-12-07
 */
public interface IUserService extends IService<User> {

    /**
     * 微信授权登录
     *
     * @param code 授权码
     * @return Token 值
     */
    String wechatAuthorizedLogin(String code);

    /**
     * 退出登录
     */
    void logout();
}
