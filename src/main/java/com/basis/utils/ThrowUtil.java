package com.basis.utils;

import com.basis.common.ResponseCode;
import com.basis.exception.BusinessException;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/7
 * @Description: 通用异常抛出
 */
public class ThrowUtil {

    /**
     * 条件成立则抛出异常
     * @param condition 条件
     * @param exception 异常
     */
    public static void throwIf(boolean condition, RuntimeException exception) {
        if (condition) throw exception;
    }

    /**
     * 条件成立则抛出异常
     * @param condition 条件
     * @param response 异常响应
     */
    public static void throwIf(boolean condition, ResponseCode response) {
        if (condition) throw new BusinessException(response);
    }

    /**
     * 条件成立则抛出异常
     * @param condition 条件
     * @param response  异常响应
     * @param message   响应信息
     */
    public static void throwIf(boolean condition, ResponseCode response, String message) {
        throwIf(condition, new BusinessException(response, message));
    }
}
