package com.baomidou.springwind.service.impl;

import com.baomidou.framework.annotations.Log;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.AssetsBalance;
import com.baomidou.springwind.mapper.AssetsBalanceMapper;
import com.baomidou.springwind.service.IAssetsBalanceService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资产余额表 服务实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-25
 */
@Service
public class AssetsBalanceServiceImpl extends BaseServiceImpl<AssetsBalanceMapper, AssetsBalance> implements IAssetsBalanceService {

    @Log("根据条件分页查询资产余额列表")
    @Override
    public Page<AssetsBalance> selectPageByParams(Page<AssetsBalance> page, AssetsBalance ab) {

        EntityWrapper<AssetsBalance> ew = new EntityWrapper<>();

        if (StringUtil.isNotEmpty(ab.getUserName())) {
            ew.like("user_name", ab.getUserName());
        }
        if (StringUtil.isNotEmpty(ab.getMobileNo())) {
            ew.eq("mobile_no", ab.getMobileNo());
        }
        if (ab.getUserType() != null) {
            ew.eq("user_type", ab.getUserType());
        }
        if (ab.getTransAum() != null) {
            ew.gt("trans_aum", ab.getTransAum());
        }
        if (ab.getAccountAum() != null) {
            ew.lt("trans_aum", ab.getAccountAum());
        }
        if (ab.getIsPerformancePool() != null) {
            ew.eq("is_performance_pool", ab.getIsPerformancePool());
        }

//        ew.orderBy("aum_date", false);
        System.err.println(ew.getSqlSegment());
        return selectPage(page, ew);
    }

    @Log("Truncate资产余额")
    @Override
    public void deleteAll() {
        baseMapper.truncateTable();
    }
}
