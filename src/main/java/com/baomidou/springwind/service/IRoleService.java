package com.baomidou.springwind.service;

import com.baomidou.springwind.entity.Role;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
public interface IRoleService extends IService<Role> {

    void deleteByUserId(Long userId);

}
