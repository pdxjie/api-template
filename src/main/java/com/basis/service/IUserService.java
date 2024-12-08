package com.basis.service;

import com.basis.common.Result;
import com.basis.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.basis.model.vo.LoginVo;
import com.basis.model.vo.RegisterVo;

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
     * 退出登录
     */
    void logout();

    /**
     * 登录操作
     * @param vo 请求体
     * @return 操作结果
     */
    Result<?> login(LoginVo vo);

    /**
     * 用户注册系统
     * @param vo 注册请求体
     * @return 操作结果
     */
    Result<?> register(RegisterVo vo);
}
