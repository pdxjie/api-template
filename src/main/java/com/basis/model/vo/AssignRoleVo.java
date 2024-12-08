package com.basis.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 分配角色
 */
@Data
public class AssignRoleVo {

    @ApiModelProperty(value = "角色 ID")
    @NotNull(message = "角色 ID 不能为空")
    private Long roleId;

    @ApiModelProperty(value = "用户 ID")
    @NotNull(message = "用户 ID 不能为空")
    private Long userId;
}
