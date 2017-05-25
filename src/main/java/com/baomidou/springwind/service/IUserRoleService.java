package com.baomidou.springwind.service;

import com.baomidou.springwind.entity.UserRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户角色表 UserRole 数据服务层接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * <p>
     * 判断是否存在角色对应的用户
     * </p>
     *
     * @param roleId
     *            角色ID
     * @return
     */
    boolean existRoleUser(Long roleId);

    /**
     * 根据 用户id 查询 用户角色
     *
     * @param uid
     * @return userRole
     */
    UserRole selectByUid(Long uid);
}
