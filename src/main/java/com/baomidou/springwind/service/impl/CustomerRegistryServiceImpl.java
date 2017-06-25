package com.baomidou.springwind.service.impl;

import com.baomidou.springwind.entity.CustomerRegistry;
import com.baomidou.springwind.mapper.CustomerRegistryMapper;
import com.baomidou.springwind.service.ICustomerRegistryService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户注册情况 服务实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-25
 */
@Service
public class CustomerRegistryServiceImpl extends BaseServiceImpl<CustomerRegistryMapper, CustomerRegistry> implements ICustomerRegistryService {
	
}
