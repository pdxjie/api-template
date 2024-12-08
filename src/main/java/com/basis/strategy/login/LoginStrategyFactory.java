package com.basis.strategy.login;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 登录策略工厂
 */
@Component
public class LoginStrategyFactory {

    @Resource
    private Map<String, LoginStrategy> strategyMap;

    public LoginStrategy getStrategy(String loginType) {
        LoginStrategy strategy = strategyMap.get(loginType);
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported login type: " + loginType);
        }
        return strategy;
    }
}
