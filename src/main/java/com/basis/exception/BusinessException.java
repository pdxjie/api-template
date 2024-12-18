package com.basis.exception;

import com.basis.common.ResponseCode;
import lombok.Data;
import lombok.Getter;

/*
 * @Author 派同学
 * @Description 业务异常处理
 * @Date 2023/7/24
 **/
@Data
@Getter
public class BusinessException extends RuntimeException{

    // 错误码 ❌
    private Integer code;

    // 错误信息 ❎
    private String message;

    public BusinessException(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message) {
        this.code = ResponseCode.OPERATE_ERROR.getCode();
        this.message = message;
    }

    public BusinessException(ResponseCode response, String message) {
        this.code = response.getCode();
        this.message = message;
    }


    public BusinessException() {
        this.code = ResponseCode.OPERATE_ERROR.getCode();
        this.message =ResponseCode.OPERATE_ERROR.getMessage();
    }
}
