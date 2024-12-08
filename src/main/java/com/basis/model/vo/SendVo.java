package com.basis.model.vo;

import com.basis.model.enums.LoginTypeEnums;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 发送验证码 Vo
 */
@Data
public class SendVo {

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "邮箱")
    private String phone;

    @ApiModelProperty(value = "默认是 EMAIL")
    private String sendType = LoginTypeEnums.EMAIL.name();
}
