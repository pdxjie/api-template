package com.basis.strategy.sendStrategy;

import com.basis.common.ResponseCode;
import com.basis.exception.BusinessException;
import com.basis.utils.ThrowUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 发送验证码策略工厂
 */
@Slf4j
@Component
public class SendCaptchaStrategyFactory {

    private final Map<String, SendCaptchaStrategy> strategyMap;

    public SendCaptchaStrategyFactory(Map<String, SendCaptchaStrategy> handlers) {
        this.strategyMap = handlers;
        log.info("Loaded login handlers: {}", handlers.keySet());
    }

    public SendCaptchaStrategy getStrategy(String loginType) {
        SendCaptchaStrategy strategy = strategyMap.get(loginType);
        if (Objects.isNull(strategy)) {
            ThrowUtil.throwIf(true, new BusinessException(ResponseCode.OPERATE_ERROR));
        }
        return strategy;
    }
}
