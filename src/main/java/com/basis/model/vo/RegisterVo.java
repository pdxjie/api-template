package com.basis.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 注册新用户请求体
 */
@Data
public class RegisterVo {

    @ApiModelProperty(value = "用户名")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "邮箱")
    private String phone;

    @ApiModelProperty(value = "验证码")
    private String code;
}
