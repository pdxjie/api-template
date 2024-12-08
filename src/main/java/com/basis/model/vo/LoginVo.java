package com.basis.model.vo;

import com.basis.model.enums.LoginTypeEnums;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 登录请求实体
 */
@Data
public class LoginVo {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "邮箱")
    private String phone;

    @ApiModelProperty(value = "微信校验码")
    private String wechatCode;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "登录方式 默认是普通登录")
    private String loginType = LoginTypeEnums.NORMAL.name();
}
