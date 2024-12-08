package com.basis.strategy.sendStrategy;

import com.basis.common.Result;
import com.basis.model.vo.SendVo;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 发送验证码策略接口
 */
public interface SendCaptchaStrategy {

    /**
     * 登录操作
     * @param vo 请求体
     * @return Token 值
     */
    Result<String> send(SendVo vo);
}
