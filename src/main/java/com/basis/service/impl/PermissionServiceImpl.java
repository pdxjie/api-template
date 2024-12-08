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
import com.basis.mapper.PermissionMapper;
import com.basis.mapper.RolePermissionMapper;
import com.basis.model.dto.PermissionsDto;
import com.basis.model.entity.Permission;
import com.basis.model.entity.RolePermission;
import com.basis.model.vo.AssignPermissionVo;
import com.basis.model.vo.InsertPermissionVo;
import com.basis.service.IPermissionService;
import com.basis.utils.ThrowUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 添加权限功能
     *
     * @param vo 添加权限请求体
     * @return 操作结果
     */
    @Override
    public Result<?> insertPermission(InsertPermissionVo vo) {
        Permission permission = new Permission();
        permission.setPName(vo.getPermissionName());
        permission.setPCode(vo.getPermissionCode());
        permission.setCreateTime(LocalDateTime.now());
        permission.setUpdateTime(LocalDateTime.now());
        permission.setIsDeleted(false);
        save(permission);
        return Result.success();
    }

    /**
     * 更新权限功能
     *
     * @param vo 更新权限请求体
     * @return 操作结果
     */
    @Override
    public Result<?> updatePermission(InsertPermissionVo vo) {
        // 校验参数
        ThrowUtil.throwIf(Objects.isNull(vo.getPermissionId()), new BusinessException(ResponseCode.PARAM_ERROR));
        // 根据 ID 获取权限信息
        Permission permission = getById(vo.getPermissionId());
        // 判断是否权限存在
        ThrowUtil.throwIf(Objects.isNull(permission), new BusinessException(ResponseCode.ROLE_NOT_EXISTED));
        update(new LambdaUpdateWrapper<Permission>().eq(Permission::getId, vo.getPermissionId()).set(Permission::getPCode, vo.getPermissionCode())
                .set(Permission::getPName, vo.getPermissionName()));
        return Result.success();
    }

    /**
     * 删除权限信息
     *
     * @param permissionId 权限 ID
     * @return 操作结果
     */
    @Override
    public Result<?> removePermission(Long permissionId) {
        // 校验参数
        ThrowUtil.throwIf(Objects.isNull(permissionId), new BusinessException(ResponseCode.PARAM_ERROR));
        removeById(permissionId);
        return Result.success();
    }

    /**
     * 获取权限信息
     *
     * @param permissionId 权限 ID
     * @return 操作结果
     */
    @Override
    public Result<Permission> permissionInfo(Long permissionId) {
        // 校验参数
        ThrowUtil.throwIf(Objects.isNull(permissionId), new BusinessException(ResponseCode.PARAM_ERROR));
        // 根据 ID 获取权限信息
        Permission role = getById(permissionId);
        ThrowUtil.throwIf(Objects.isNull(role), new BusinessException(ResponseCode.ROLE_NOT_EXISTED));
        return Result.success(role);
    }

    /**
     * 权限分页查询
     *
     * @param params 查询条件
     * @return 操作结果
     */
    @Override
    public Result<?> fetchPage(PageParams params) {
        Page<Permission> pageParams = new Page(params.getCurrent(), params.getPageSize());
        // 创建包装器
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        // 判断是否有条件
        if (StrUtil.isNotEmpty(params.getKeyword())) {
            wrapper.like("role_name", params.getKeyword());
        }
        wrapper.orderByDesc("create_time");
        // 分页查询
        Page<Permission> page = this.page(pageParams, wrapper);
        PageDto<Permission> dto = new PageDto<Permission>();
        dto.setItems(page.getRecords());
        dto.setTotal(page.getTotal());
        return Result.success(dto);
    }

    /**
     * 分配权限
     * @param vo 分配权限请求体
     * @return 操作结果
     */
    @Override
    public Result<?> assignmentPermission(AssignPermissionVo vo) {
        try {
            RolePermission userRole = new RolePermission();
            userRole.setIsDeleted(false);
            userRole.setUpdateTime(LocalDateTime.now());
            userRole.setUpdateTime(LocalDateTime.now());
            userRole.setRId(vo.getRoleId());
            userRole.setPId(vo.getPermissionId());
            rolePermissionMapper.insert(userRole);
            return Result.success();
        } catch (Exception e) {
            log.error("assign role operate is error..{}", e.getCause());
            return Result.fail();
        }
    }

    /**
     * 根据角色 ID 获取权限信息
     *
     * @param roleId 角色 ID
     * @return 操作结果
     */
    @Override
    public Result<?> permissionsByRole(Long roleId) {
        // 校验参数
        ThrowUtil.throwIf(Objects.isNull(roleId), new BusinessException(ResponseCode.PARAM_ERROR));
        // 根据角色 ID 获取权限信息
        List<PermissionsDto> dtos = this.baseMapper.queryPermissionsByRoleId(roleId);
        return Result.success(ResponseCode.SUCCESS, dtos);
    }
}
