package com.basis.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basis.common.PageDto;
import com.basis.common.PageParams;
import com.basis.common.ResponseCode;
import com.basis.common.Result;
import com.basis.exception.BusinessException;
import com.basis.mapper.RoleMapper;
import com.basis.mapper.RolePermissionMapper;
import com.basis.mapper.UserRoleMapper;
import com.basis.model.entity.Role;
import com.basis.model.entity.RolePermission;
import com.basis.model.entity.UserRole;
import com.basis.model.vo.AssignRoleVo;
import com.basis.model.vo.InsertRoleVo;
import com.basis.model.vo.RemovePermissionVo;
import com.basis.service.IRoleService;
import com.basis.utils.ThrowUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author IT 派同学
 * @since 2024-12-07
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 添加角色功能
     *
     * @param vo 添加角色请求体
     * @return 操作结果
     */
    @Override
    public Result<?> insertRole(InsertRoleVo vo) {
        Role role = new Role();
        role.setRoleCode(vo.getRoleCode());
        role.setRoleName(vo.getRoleName());
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        role.setIsDeleted(false);
        save(role);
        return Result.success();
    }

    /**
     * 更新角色功能
     *
     * @param vo 更新角色请求体
     * @return 操作结果
     */
    @Override
    public Result<?> updateRole(InsertRoleVo vo) {
        // 校验参数
        ThrowUtil.throwIf(Objects.isNull(vo.getRoleId()), new BusinessException(ResponseCode.PARAM_ERROR));
        // 根据 ID 获取角色信息
        Role role = getById(vo.getRoleId());
        // 判断是否角色存在
        ThrowUtil.throwIf(Objects.isNull(role), new BusinessException(ResponseCode.ROLE_NOT_EXISTED));
        update(new LambdaUpdateWrapper<Role>().eq(Role::getId, vo.getRoleId()).set(Role::getRoleCode, vo.getRoleCode())
                .set(Role::getRoleName, vo.getRoleName()));
        return Result.success();
    }

    /**
     * 删除角色信息
     *
     * @param roleId 角色 ID
     * @return
     */
    @Override
    public Result<?> removeRole(Long roleId) {
        // 校验参数
        ThrowUtil.throwIf(Objects.isNull(roleId), new BusinessException(ResponseCode.PARAM_ERROR));
        removeById(roleId);
        return Result.success();
    }

    /**
     * 获取角色信息
     *
     * @param roleId 角色 ID
     * @return 操作结果
     */
    @Override
    public Result<Role> roleInfo(Long roleId) {
        // 校验参数
        ThrowUtil.throwIf(Objects.isNull(roleId), new BusinessException(ResponseCode.PARAM_ERROR));
        // 根据 ID 获取角色信息
        Role role = getById(roleId);
        ThrowUtil.throwIf(Objects.isNull(role), new BusinessException(ResponseCode.ROLE_NOT_EXISTED));
        return Result.success(role);
    }

    /**
     * 角色分页查询
     *
     * @param params 查询条件
     * @return 操作结果
     */
    @Override
    public Result<?> fetchPage(PageParams params) {
        Page<Role> pageParams = new Page(params.getCurrent(), params.getPageSize());
        // 创建包装器
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        // 判断是否有条件
        if (StrUtil.isNotEmpty(params.getKeyword())) {
            wrapper.like("role_name", params.getKeyword());
        }
        wrapper.orderByDesc("create_time");
        // 分页查询
        Page<Role> page = this.page(pageParams, wrapper);
        PageDto<Role> dto = new PageDto<Role>();
        dto.setItems(page.getRecords());
        dto.setTotal(page.getTotal());
        return Result.success(dto);
    }

    /**
     * 分配角色
     * @param vo 分配角色请求体
     * @return 操作结果
     */
    @Override
    public Result<?> assignmentRole(AssignRoleVo vo) {
        try {
            UserRole userRole = new UserRole();
            userRole.setIsDeleted(false);
            userRole.setUpdateTime(LocalDateTime.now());
            userRole.setUpdateTime(LocalDateTime.now());
            userRole.setRId(vo.getRoleId());
            userRole.setUId(vo.getUserId());
            userRoleMapper.insert(userRole);
            return Result.success();
        } catch (Exception e) {
            log.error("assign role operate is error..{}", e.getCause());
            return Result.fail();
        }
    }

    /**
     * 移除角色下的权限
     *
     * @param vo 请求体
     * @return 操作结果
     */
    @Override
    public Result<?> removePermission(RemovePermissionVo vo) {
        try {
            rolePermissionMapper.delete(new LambdaUpdateWrapper<RolePermission>()
                    .eq(RolePermission::getPId, vo.getPermissionId())
                    .eq(RolePermission::getRId, vo.getRoleId()));
            return Result.success();
        } catch (Exception e) {
            log.error("remove permission is error operate..{}", e.getCause());
            return Result.fail();
        }
    }
}
