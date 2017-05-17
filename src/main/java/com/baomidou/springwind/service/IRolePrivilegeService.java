package com.baomidou.springwind.service;

import com.baomidou.springwind.entity.RolePrivilege;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限表 RolePrivilege 数据服务层接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
public interface IRolePrivilegeService extends IService<RolePrivilege> {

    /**
     * <p>
     * 判断是否存在角色对应的权限
     * </p>
     *
     * @param permissionId
     *            权限ID
     * @return
     */
    boolean existRolePermission(Long permissionId);

    /**
     * 根据角色ID获取对应的所有关联权限的ID
     * @param id
     * @return
     */
    List<Long> selecPermissionIdsByRoleId(Long id);
}
