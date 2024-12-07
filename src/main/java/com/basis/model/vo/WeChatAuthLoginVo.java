package com.basis.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/7
 * @Description: 微信授权认证登录参数
 */
@Data
public class WeChatAuthLoginVo {

    @NotBlank(message = "微信授权 CODE 不能为空")
    @ApiModelProperty(value = "微信授权码")
    private String code;
}
