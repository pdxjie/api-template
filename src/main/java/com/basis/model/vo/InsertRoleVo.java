package com.basis.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 添加角色信息
 */
@Data
public class InsertRoleVo {

    @ApiModelProperty(value = "角色 ID")
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    @NotEmpty(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "角色码")
    @NotEmpty(message = "角色码不能为空")
    private String roleCode;
}
