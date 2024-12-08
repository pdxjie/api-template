package com.basis.controller;

import com.basis.common.PageParams;
import com.basis.common.Result;
import com.basis.model.entity.Role;
import com.basis.model.vo.AssignRoleVo;
import com.basis.model.vo.InsertRoleVo;
import com.basis.model.vo.RemovePermissionVo;
import com.basis.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "角色相关接口")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "添加角色功能")
    @PostMapping(value = "/insert", name = "添加角色", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<?> insertRole(@RequestBody(required = false) @Valid InsertRoleVo vo) {
        return roleService.insertRole(vo);
    }

    @ApiOperation(value = "更新角色信息")
    @PostMapping(value = "/update", name = "更新角色信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<?> updateRole(@RequestBody(required = false) @Valid InsertRoleVo vo) {
        return roleService.updateRole(vo);
    }

    @ApiOperation(value = "删除角色信息")
    @DeleteMapping(value = "/remove", name = "删除角色信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<?> removeRole(@PathParam("roleId") Long roleId) {
        return roleService.removeRole(roleId);
    }

    @ApiOperation(value = "获取角色信息")
    @GetMapping(value = "/info", name = "获取角色信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Role> roleInfo(@PathParam("roleId") Long roleId) {
        return roleService.roleInfo(roleId);
    }

    @ApiOperation(value = "角色分页查询")
    @PostMapping(value = "/page", name = "角色分页查询", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<?> fetchPage(@RequestBody(required = false) PageParams params) {
        return roleService.fetchPage(params);
    }

    @ApiOperation(value = "分配角色")
    @PostMapping(value = "/assign/role", name = "分配角色", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<?> assignRole(@RequestBody(required = false) @Valid AssignRoleVo vo) {
        return roleService.assignmentRole(vo);
    }

    @ApiOperation(value = "移除角色下的权限")
    @PostMapping(value = "/remove/permission", name = "移除角色下的权限", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<?> removePermission(@RequestBody(required = false) @Valid RemovePermissionVo vo) {
        return roleService.removePermission(vo);
    }
}
