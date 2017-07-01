package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.CustomerRegistry;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 客户注册情况 服务类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-25
 */
public interface ICustomerRegistryService extends IService<CustomerRegistry> {

    Page<CustomerRegistry> selectPageByParams(Page<CustomerRegistry> page, CustomerRegistry cr);

    void deleteAll();
}
