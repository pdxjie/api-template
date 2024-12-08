package com.basis.strategy.login;

import com.basis.common.ResponseCode;
import com.basis.exception.BusinessException;
import com.basis.utils.ThrowUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 登录策略工厂
 */
@Slf4j
@Component
public class LoginStrategyFactory {

    private final Map<String, LoginStrategy> strategyMap;

    public LoginStrategyFactory(Map<String, LoginStrategy> handlers) {
        this.strategyMap = handlers;
        log.info("Loaded login handlers: {}", handlers.keySet());
    }

    public LoginStrategy getStrategy(String loginType) {
        LoginStrategy strategy = strategyMap.get(loginType);
        if (strategy == null) {
            ThrowUtil.throwIf(true, new BusinessException(ResponseCode.OPERATE_ERROR));
        }
        return strategy;
    }
}
