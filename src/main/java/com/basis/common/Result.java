package com.basis.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.io.Serializable;

/*
 * @Author IT 派同学
 * @Description 响应结果
 * @Date 2024/12/7
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "响应状态码")
    private Integer code;

    @ApiModelProperty(value = "响应信息")
    private String message;

    @ApiModelProperty(value = "响应数据")
    private T data;

    private Result(Integer code) {
        this.code = code;
    }

    private Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功
     *
     * @param <T>
     */
    public static <T>Result<T> success() {
        return new Result<T>(ResponseCode.SUCCESS.getCode());
    }

    /**
     * 成功
     *
     * @param message 响应信息
     * @param <T>
     */
    public static <T>Result<T> success(String message) {
        return new Result<T>(ResponseCode.SUCCESS.getCode(), message);
    }

    /**
     * 成功
     *
     * @param data 响应数据
     * @param <T>
     */
    public static <T>Result<T> success(ResponseCode response, T data) {
        return new Result<T>(response.getCode(), response.getMessage(), data);
    }

    /**
     * 成功
     *
     * @param data 响应数据
     * @param <T>
     */
    public static <T>Result<T> success(T data) {
        return new Result<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    /**
     * 失败
     *
     * @param <T>
     */
    public static <T>Result<T> fail() {
        return new Result<T>(ResponseCode.OPERATE_ERROR.getCode());
    }

    /**
     * 失败
     *
     * @param message 错误数据
     * @param <T>
     */
    public static <T>Result<T> fail(String message) {
        return new Result<T>(ResponseCode.OPERATE_ERROR.getCode(), message);
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param message 错误数据
     * @param <T>
     */
    public static <T>Result<T> fail(Integer code, String message) {
        return new Result<T>(code, message);
    }

    /**
     * 失败
     *
     * @param responseCode 响应码
     * @param <T>
     */
    public static <T>Result<T> fail(ResponseCode responseCode) {
        return new Result<T>(responseCode.getCode(), responseCode.getMessage());
    }
}
