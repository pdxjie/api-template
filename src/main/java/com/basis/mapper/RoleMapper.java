package com.basis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.basis.model.entity.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户 ID 获取角色列表
     * @param id 用户 ID
     * @return 角色列表
     */
    List<String> selectRolesByUserId(@Param("userId") Long id);
}
