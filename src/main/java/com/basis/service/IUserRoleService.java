package com.basis.service;

import com.basis.model.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author IT 派同学
 * @since 2024-12-07
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 分配角色
     * @param userId 用户 ID
     */
    void assignmentRole(Long userId);
}
