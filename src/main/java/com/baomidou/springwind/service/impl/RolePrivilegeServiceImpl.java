package com.baomidou.springwind.service.impl;

import com.baomidou.framework.annotations.Log;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springwind.entity.RolePrivilege;
import com.baomidou.springwind.mapper.RolePrivilegeMapper;
import com.baomidou.springwind.service.IRolePrivilegeService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色权限表 RolePrivilege 数据服务层接口实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
@Service
public class RolePrivilegeServiceImpl extends BaseServiceImpl<RolePrivilegeMapper, RolePrivilege> implements IRolePrivilegeService {

    @Autowired
    private RolePrivilegeMapper rolePrivilegeMapper;

    @Log("菜单查询")
    @Override
    public boolean existRolePermission(Long permissionId) {
        RolePrivilege rp = new RolePrivilege();
        rp.setPid(permissionId);
        int rlt = baseMapper.selectCount(new EntityWrapper<RolePrivilege>(rp));
        return rlt >= 1;
    }

    @Log("角色关联菜单查询")
    @Override
    public List<Long> selecPermissionIdsByRoleId(Long id) {
        return rolePrivilegeMapper.selecPermissionIdsByRoleId(id);
    }
}
