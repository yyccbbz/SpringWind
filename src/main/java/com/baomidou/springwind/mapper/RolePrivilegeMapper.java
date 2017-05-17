package com.baomidou.springwind.mapper;

import com.baomidou.springwind.entity.RolePrivilege;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * 角色权限表 Mapper 接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
public interface RolePrivilegeMapper extends BaseMapper<RolePrivilege> {

    /**
     * 根据角色ID获取对应的所有关联权限的ID
     * @param id 角色Id
     * @return
     */
    List<Long> selecPermissionIdsByRoleId(Long id);
}