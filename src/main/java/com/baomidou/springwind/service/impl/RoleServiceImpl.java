package com.baomidou.springwind.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springwind.entity.Role;
import com.baomidou.springwind.entity.UserRole;
import com.baomidou.springwind.mapper.RoleMapper;
import com.baomidou.springwind.mapper.UserRoleMapper;
import com.baomidou.springwind.service.IRoleService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void deleteByUserId(Long userId) {
        UserRole ur = new UserRole();
        ur.setUid(userId);
        userRoleMapper.delete(new EntityWrapper<UserRole>(ur));
    }

}
