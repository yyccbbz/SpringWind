package com.baomidou.springwind.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springwind.entity.UserRole;
import com.baomidou.springwind.mapper.UserRoleMapper;
import com.baomidou.springwind.service.IUserRoleService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public boolean existRoleUser( Long roleId ) {
        UserRole ur = new UserRole();
        ur.setRid(roleId);
        int rlt = baseMapper.selectCount(new EntityWrapper<UserRole>(ur));
        return rlt >= 1;
    }

    @Override
    public UserRole selectByUid(Long uid) {
        UserRole userRole = new UserRole();
        userRole.setUid(uid);
        return baseMapper.selectOne(userRole);
    }

}
