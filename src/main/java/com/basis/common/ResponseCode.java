package com.basis.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 * @Author IT 派同学
 * @Description 响应枚举类
 * @Date 2024/12/7
 **/
@AllArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS(0, "操作成功"),

    PARAM_ERROR(40100, "参数错误，请稍后重试!"),

    NOT_LOGIN(40101, "用户未登录，请先登录!"),

    AUTH_FORBID(40103, "权限不足，请联系管理员!"),

    NOT_FOUND(40400, "未找到该资源!"),

    SYSTEM_ERROR(50000, "系统异常，请稍后重试!"),

    OPERATE_ERROR(50001, "操作失败"),

    WECHAT_AUTHORIZATION(50002, "微信图像识别能力调用失败")
    ;

    @ApiModelProperty(value = "状态码")
    private final Integer code;

    @ApiModelProperty(value = "响应信息")
    private final String message;

}
