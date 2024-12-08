package com.basis.exception;

import com.basis.common.ResponseCode;
import com.basis.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/*
 * @Author IT 派同学
 * @Description 异常处理
 * @Date 2024/12/7
 **/
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<?> exception(Exception e){
        log.error("Exception====>{}",e.getLocalizedMessage(),e);
        return Result.fail(ResponseCode.OPERATE_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    public Result<?> businessException(BusinessException e){
        log.error("businessException ====>{}",e.getLocalizedMessage(),e);
        return Result.fail(e.getCode(),e.getMessage());
    }


    /**
     * 框架异常
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    <T> Result<T> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        log.error("methodArgumentNotValidExceptionHandler bindingResult.allErrors():{},exception:{}", e.getBindingResult().getAllErrors(), e);
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return createValidExceptionResp(errors);
    }

    private <T> Result<T> createValidExceptionResp(List<ObjectError> errors) {
        String[] messages = new String[errors.size()];
        int i = 0;
        for (ObjectError error : errors) {
            messages[i] = error.getDefaultMessage();
            log.info("msg={}",messages[i]);
            i++;
        }
        return Result.fail(ResponseCode.OPERATE_ERROR.getCode(), messages[0]);
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<?> runtimeExceptionHandler (RuntimeException e) {
        log.error("runtimeExceptionHandler ====>{}",e.getLocalizedMessage(),e);
        return Result.fail(ResponseCode.OPERATE_ERROR.getCode(), ResponseCode.OPERATE_ERROR.getMessage());
    }
}
