package com.basis.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 添加权限信息
 */
@Data
public class InsertPermissionVo {

    @ApiModelProperty(value = "权限 ID")
    private Long permissionId;

    @ApiModelProperty(value = "权限名称")
    @NotEmpty(message = "权限名称不能为空")
    private String permissionName;

    @ApiModelProperty(value = "角色码")
    @NotEmpty(message = "权限码不能为空")
    private String permissionCode;
}
