package com.basis.configuration;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.basis.mapper.PermissionMapper;
import com.basis.mapper.RoleMapper;
import com.basis.mapper.RolePermissionMapper;
import com.basis.mapper.UserRoleMapper;
import com.basis.model.entity.Permission;
import com.basis.model.entity.Role;
import com.basis.model.entity.RolePermission;
import com.basis.model.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 权限验证接口
 */
@Slf4j
@Component
public class SaTokenRoleAndPermissionProvider implements StpInterface {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RoleMapper roleMapper;

    // 缓存角色和权限信息
    public static final String ROLE_CACHE_KEY = "sa-token:role-find-permission:";

    // 缓存权限信息
    public static final String PERMISSION_CACHE_KEY = "sa-token:role-find-permission:";

    // 过期时间
    public static final int expiredTime = 60 * 60 * 24 * 30;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        log.debug("current login userId --> {}", loginId);
        // 根据用户 ID 获取当前用户所具备的权限
        List<UserRole> userRoles = userRoleMapper.selectList(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUId, loginId));
        List<String> allPermissionKeys = new ArrayList<>();
        // 获取角色 ID 集合
        List<Long> roleIds = userRoles.stream().map(UserRole::getRId).collect(Collectors.toList());
        // 获取角色权限信息
        if (CollectionUtil.isNotEmpty(roleIds)) {
            roleIds.forEach(roleId -> {
                String cacheKey = StrUtil.join(PERMISSION_CACHE_KEY, roleId);
                List<String> permissionKeys = (List<String>)SaManager.getSaTokenDao().getObject(cacheKey);
                if (Objects.isNull(permissionKeys) || CollectionUtil.isEmpty(permissionKeys)) {
                    List<RolePermission> rolePermissions = rolePermissionMapper.selectList(new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getRId, roleId));
                    if (Objects.nonNull(rolePermissions) && CollectionUtil.isNotEmpty(rolePermissions)) {
                        List<Long> permissionIds = rolePermissions.stream().map(RolePermission::getPId).collect(Collectors.toList());
                        List<Permission> permissions = permissionMapper.selectList(new LambdaQueryWrapper<Permission>().in(Permission::getId, permissionIds));
                        if (Objects.nonNull(permissions)) {
                            permissionKeys = permissions.stream().map(Permission::getPCode).collect(Collectors.toList());
                            allPermissionKeys.addAll(permissionKeys);
                            SaManager.getSaTokenDao().setObject(cacheKey, permissionKeys, expiredTime);
                        }
                    }
                } else {
                    allPermissionKeys.addAll(permissionKeys);
                }
            });
        }
        return allPermissionKeys;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        log.debug("current login userId --> {}", loginId);
        String cacheKey = StrUtil.join(ROLE_CACHE_KEY, loginId);
        List<String> roleKeys = (List<String>) SaManager.getSaTokenDao().getObject(cacheKey);
        // 判断是否具有角色集合
        if (Objects.isNull(roleKeys) || CollectionUtil.isEmpty(roleKeys)) {
            // 根据用户 ID 获取当前用户所具备的权限
            List<UserRole> userRoles = userRoleMapper.selectList(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUId, loginId));
            roleKeys = new ArrayList<>();
            // 获取角色 ID 集合
            List<Long> roleIds = userRoles.stream().map(UserRole::getRId).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(roleIds)) {
                List<Role> roles = roleMapper.selectList(new LambdaQueryWrapper<Role>().in(Role::getId, roleIds));
                roleKeys = roles.stream().map(Role::getRoleCode).collect(Collectors.toList());
            }
            log.debug("roles ----> {}", JSONUtil.toJsonStr(roleKeys));
            SaManager.getSaTokenDao().setObject(cacheKey, roleKeys, expiredTime);
        }
        return roleKeys;
    }
}
