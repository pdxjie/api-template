package com.basis.service.impl;

import com.basis.model.entity.UserRole;
import com.basis.mapper.UserRoleMapper;
import com.basis.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author IT 派同学
 * @since 2024-12-07
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Value("${system.default.roleId}")
    private Long systemDefaultRoleId;

    /**
     * 分配角色
     * @param userId 用户 ID
     */
    @Override
    public void assignmentRole(Long userId) {
        UserRole userRole = new UserRole();
        userRole.setRId(systemDefaultRoleId);
        userRole.setUId(userId);
        userRole.setUpdateTime(LocalDateTime.now());
        userRole.setCreateTime(LocalDateTime.now());
        userRole.setIsDeleted(false);
        this.save(userRole);
    }
}
