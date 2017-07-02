package com.baomidou.springwind.service.impl;

import com.baomidou.framework.annotations.Log;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.SalesDetails;
import com.baomidou.springwind.entity.vo.SalesDetailsVO;
import com.baomidou.springwind.mapper.SalesDetailsMapper;
import com.baomidou.springwind.service.ISalesDetailsService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 销售明细 服务实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-15
 */
@Service
public class SalesDetailsServiceImpl extends BaseServiceImpl<SalesDetailsMapper, SalesDetails> implements ISalesDetailsService {

    @Log("根据条件分页查询[销售明细]列表")
    @Override
    public Page<SalesDetails> selectPageByParams(Page<SalesDetails> page, SalesDetailsVO sdVO) {

        EntityWrapper<SalesDetails> ew = new EntityWrapper<>();

        if (StringUtil.isNotEmpty(sdVO.getUserName())) {
            ew.like("user_name", sdVO.getUserName());
        }
        if (StringUtil.isNotEmpty(sdVO.getMobileNo())) {
            ew.eq("mobile_no", sdVO.getMobileNo());
        }
        if (sdVO.getUserType() != null) {
            ew.eq("user_type", sdVO.getUserType());
        }

        if (sdVO.getTransTimeStart() != null) {
            ew.gt("trans_time", sdVO.getTransTimeStart());
        }
        if (sdVO.getTransTimeEnd() != null) {
            ew.lt("trans_time", sdVO.getTransTimeEnd());
        }
        if (sdVO.getProductType() != null) {
            ew.eq("product_type", sdVO.getProductType());
        }

        if (sdVO.getTransAmountSmall() != null) {
            ew.gt("trans_amount", sdVO.getTransAmountSmall());
        }
        if (sdVO.getTransAmountBig() != null) {
            ew.lt("trans_amount", sdVO.getTransAmountBig());
        }
        if (sdVO.getLimitType() != null) {
            ew.eq("limit_type", sdVO.getLimitType());
        }

        if (StringUtil.isNotEmpty(sdVO.getAdvisorName())) {
            ew.eq("advisor_name", sdVO.getAdvisorName());
        }
        if (sdVO.getIsPerformancePool() != null) {
            ew.eq("is_performance_pool", sdVO.getIsPerformancePool());
        }
        if (sdVO.getUserMark() != null) {
            ew.eq("user_mark", sdVO.getUserMark());
        }

        ew.orderBy("trans_time", false);
        System.err.println(ew.getSqlSegment());
        return selectPage(page, ew);
    }

    @Log("TRUNCATE[销售明细]表")
    @Override
    public void deleteAll() {
        baseMapper.truncateTable();
    }
}
