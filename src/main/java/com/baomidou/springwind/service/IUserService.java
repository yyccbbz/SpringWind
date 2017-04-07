package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.entity.User;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IService<User> {

	User selectByLoginName(String loginName);

	void deleteUser(Long userId);

    Page<User> selectPageBySearch(Page<User> page, String search);
}