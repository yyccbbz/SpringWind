package com.baomidou.springwind.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.StringUtil;
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

    @Override
    public Page<CustomerRegistry> selectPageByParams(Page<CustomerRegistry> page, CustomerRegistry cr) {
        EntityWrapper<CustomerRegistry> ew = new EntityWrapper<>();

        if (StringUtil.isNotEmpty(cr.getUserName())) {
            ew.like("user_name", cr.getUserName());
        }
        if (StringUtil.isNotEmpty(cr.getMobileNo())) {
            ew.eq("mobile_no", cr.getMobileNo());
        }
        if (cr.getIsRegister() != null) {
            ew.eq("is_register", cr.getIsRegister());
        }
        if (StringUtil.isNotEmpty(cr.gettUserName())) {
            ew.like("t_user_name", cr.gettUserName());
        }
        if (StringUtil.isNotEmpty(cr.gettMobileNo())) {
            ew.eq("t_mobile_no", cr.gettMobileNo());
        }

        ew.orderBy("register_time", false);
        System.err.println(ew.getSqlSegment());
        return selectPage(page, ew);
    }

    @Override
    public void deleteAll() {
        baseMapper.truncateTable();
    }
}
