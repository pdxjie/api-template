package com.basis.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 删除权限请求体
 */
@Data
public class RemovePermissionVo {

    @ApiModelProperty(value = "角色 ID")
    @NotNull(message = "角色 ID 不能为空")
    private Long roleId;

    @ApiModelProperty(value = "权限 ID")
    @NotNull(message = "权限 ID 不能为空")
    private String permissionId;
}
