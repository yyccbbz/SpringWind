package com.baomidou.springwind.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.GetSalesDetails;
import com.baomidou.springwind.mapper.GetSalesDetailsMapper;
import com.baomidou.springwind.service.IGetSalesDetailsService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 获客销售明细 服务实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-15
 */
@Service
public class GetSalesDetailsServiceImpl extends BaseServiceImpl<GetSalesDetailsMapper, GetSalesDetails> implements IGetSalesDetailsService {

    @Override
    public Page<GetSalesDetails> selectPageByParams(Page<GetSalesDetails> page, GetSalesDetails gsd) {
        EntityWrapper<GetSalesDetails> ew = new EntityWrapper<>();

        if (StringUtil.isNotEmpty(gsd.gettUserName())) {
            ew.like("t_user_name", gsd.gettUserName());
        }
        if (StringUtil.isNotEmpty(gsd.gettMobileNo())) {
            ew.eq("t_mobile_no", gsd.gettMobileNo());
        }
        if (gsd.gettUserType() != null) {
            ew.eq("t_user_type", gsd.gettUserType());
        }

        if (StringUtil.isNotEmpty(gsd.getBtUserName())) {
            ew.like("bt_user_name", gsd.getBtUserName());
        }
        if (StringUtil.isNotEmpty(gsd.getBtMobileNo())) {
            ew.eq("bt_mobile_no", gsd.getBtMobileNo());
        }
        if (StringUtil.isNotEmpty(gsd.getAdvisorName())) {
            ew.eq("advisor_name", gsd.getAdvisorName());
        }

        if (gsd.getBtRegisterTime() != null) {
            ew.gt("bt_register_time", gsd.getBtRegisterTime());
        }
        if (gsd.getUpdateTime() != null) {
            ew.lt("bt_register_time", gsd.getUpdateTime());
        }
        if (gsd.getProductType() != null) {
            ew.eq("product_type", gsd.getProductType());
        }

        if (gsd.getTransTime() != null) {
            ew.gt("trans_time", gsd.getTransTime());
        }
        if (gsd.getCreateTime() != null) {
            ew.lt("trans_time", gsd.getCreateTime());
        }
        if (gsd.getLimitType() != null) {
            ew.eq("limit_type", gsd.getLimitType());
        }

        ew.orderBy("trans_time", false);
        System.err.println(ew.getSqlSegment());
        return selectPage(page, ew);
    }


    @Override
    public void deleteAll() {
        baseMapper.truncateTable();
    }
}
