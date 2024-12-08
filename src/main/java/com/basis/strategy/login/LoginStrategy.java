package com.basis.strategy.login;

import com.basis.common.Result;
import com.basis.model.vo.LoginVo;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 登录策略接口
 */
public interface LoginStrategy {

    /**
     * 登录操作
     * @param vo 请求体
     * @return Token 值
     */
    Result<String> login(LoginVo vo);
}
