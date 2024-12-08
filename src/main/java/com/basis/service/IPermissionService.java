package com.basis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basis.common.PageParams;
import com.basis.common.Result;
import com.basis.model.entity.Permission;
import com.basis.model.vo.AssignPermissionVo;
import com.basis.model.vo.InsertPermissionVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author IT 派同学
 * @since 2024-12-07
 */
public interface IPermissionService extends IService<Permission> {

    /**
     * 添加权限功能
     *
     * @param vo 添加权限请求体
     * @return 操作结果
     */
    Result<?> insertPermission(InsertPermissionVo vo);

    /**
     * 更新权限功能
     *
     * @param vo 更新权限请求体
     * @return 操作结果
     */
    Result<?> updatePermission(InsertPermissionVo vo);

    /**
     * 删除权限信息
     *
     * @param PermissionId 权限 ID
     * @return 操作结果
     */
    Result<?> removePermission(Long PermissionId);

    /**
     * 获取权限信息
     *
     * @param PermissionId 权限 ID
     * @return 操作结果
     */
    Result<Permission> permissionInfo(Long PermissionId);

    /**
     * 权限分页查询
     *
     * @param params 查询条件
     * @return 操作结果
     */
    Result<?> fetchPage(PageParams params);

    /**
     * 分配权限
     * @param vo 分配权限请求体
     * @return 操作结果
     */
    Result<?> assignmentPermission(AssignPermissionVo vo);

    /**
     * 根据角色 ID 获取权限信息
     *
     * @param roleId 角色 ID
     * @return 操作结果
     */
    Result<?> permissionsByRole(Long roleId);
}
