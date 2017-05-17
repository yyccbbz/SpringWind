package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
public interface IUserService extends IService<User> {

    User selectByLoginName(String loginName);

    void deleteUser(Long userId);

    Page<User> selectPageBySearch(Page<User> page, String search);
}
