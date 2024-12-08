package com.basis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.basis.model.dto.PermissionsDto;
import com.basis.model.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author IT 派同学
 * @since 2024-12-07
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据角色 ID 获取对应的权限信息
     * @param roleId 角色 ID
     * @return 操作结果
     */
    List<PermissionsDto> queryPermissionsByRoleId(@Param("roleId") Long roleId);
}
