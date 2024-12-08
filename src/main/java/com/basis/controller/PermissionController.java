package com.basis.controller;

import com.basis.common.PageParams;
import com.basis.common.Result;
import com.basis.model.entity.Permission;
import com.basis.model.vo.AssignPermissionVo;
import com.basis.model.vo.InsertPermissionVo;
import com.basis.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author IT 派同学
 * @since 2024-12-07
 */
@RestController
@Api(tags = "权限相关接口")
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @ApiOperation(value = "添加权限功能")
    @PostMapping(value = "/insert", name = "添加权限")
    public Result<?> insertPermission(@RequestBody(required = false) @Valid InsertPermissionVo vo) {
        return permissionService.insertPermission(vo);
    }

    @ApiOperation(value = "更新权限信息")
    @PostMapping(value = "/update", name = "更新权限信息")
    public Result<?> updatePermission(@RequestBody(required = false) @Valid InsertPermissionVo vo) {
        return permissionService.updatePermission(vo);
    }

    @ApiOperation(value = "删除权限信息")
    @PostMapping(value = "/remove", name = "删除权限信息")
    public Result<?> removePermission(@PathParam("permissionId") Long permissionId) {
        return permissionService.removePermission(permissionId);
    }

    @ApiOperation(value = "获取权限信息")
    @PostMapping(value = "/info", name = "获取权限信息")
    public Result<Permission> permissionInfo(@PathParam("permissionId") Long permissionId) {
        return permissionService.permissionInfo(permissionId);
    }

    @ApiOperation(value = "权限分页查询")
    @PostMapping(value = "/page", name = "权限分页查询")
    public Result<?> fetchPage(@RequestBody(required = false) PageParams params) {
        return permissionService.fetchPage(params);
    }

    @ApiOperation(value = "分配权限")
    @PostMapping(value = "/assign/permission", name = "分配权限")
    public Result<?> assignPermission(@RequestBody(required = false) @Valid AssignPermissionVo vo) {
        return permissionService.assignmentPermission(vo);
    }

    @ApiOperation(value = "根据角色 ID 获取权限信息")
    @PostMapping(value = "/permissions", name = "根据角色 ID 获取权限信息")
    public Result<?> permissionsByRole(@PathParam("roleId") Long roleId) {
        return permissionService.permissionsByRole(roleId);
    }

}
