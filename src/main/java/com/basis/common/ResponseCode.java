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

    WECHAT_AUTHORIZATION(50002, "微信图像识别能力调用失败"),

    USERNAME_OR_PASS_EMPTY(40102, "用户名或密码不能为空"),

    USER_NOT_EXIST(40104, "用户不存在"),

    PASSWORD_ERROR(401005, "用户密码错误, 请重新输入!"),

    EMAIL_OR_CODE_EMPTY(401006, "邮箱或验证码不能为空"),

    PHONE_OR_CODE_EMPTY(401006, "手机号或验证码不能为空"),

    CODE_NOT_EXISTED(401007, "验证码不存在"),

    CODE_NOT_CORRECT(401008, "验证码不正确"),
    ;

    @ApiModelProperty(value = "状态码")
    private final Integer code;

    @ApiModelProperty(value = "响应信息")
    private final String message;

}
