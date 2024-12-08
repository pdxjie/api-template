package com.basis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basis.common.PageParams;
import com.basis.common.Result;
import com.basis.model.entity.Role;
import com.basis.model.vo.AssignRoleVo;
import com.basis.model.vo.InsertRoleVo;
import com.basis.model.vo.RemovePermissionVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author IT 派同学
 * @since 2024-12-07
 */
public interface IRoleService extends IService<Role> {

    /**
     * 添加角色功能
     *
     * @param vo 添加角色请求体
     * @return 操作结果
     */
    Result<?> insertRole(InsertRoleVo vo);

    /**
     * 更新角色功能
     *
     * @param vo 更新角色请求体
     * @return 操作结果
     */
    Result<?> updateRole(InsertRoleVo vo);

    /**
     * 删除角色信息
     *
     * @param roleId 角色 ID
     * @return 操作结果
     */
    Result<?> removeRole(Long roleId);

    /**
     * 获取角色信息
     *
     * @param roleId 角色 ID
     * @return 操作结果
     */
    Result<Role> roleInfo(Long roleId);

    /**
     * 角色分页查询
     *
     * @param params 查询条件
     * @return 操作结果
     */
    Result<?> fetchPage(PageParams params);

    /**
     * 分配角色
     *
     * @param vo 分配角色请求体
     * @return 操作结果
     */
    Result<?> assignmentRole(AssignRoleVo vo);

    /**
     * 移除角色下的权限
     *
     * @param vo 请求体
     * @return 操作结果
     */
    Result<?> removePermission(RemovePermissionVo vo);
}
