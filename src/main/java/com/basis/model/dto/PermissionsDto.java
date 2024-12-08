package com.basis.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 权限信息响应体
 */
@Data
public class PermissionsDto {

    @ApiModelProperty(value = "权限 ID")
    private Long id;

    @ApiModelProperty(value = "权限码")
    private String permissionCode;

    @ApiModelProperty(value = "权限名称")
    private String permissionName;
}
