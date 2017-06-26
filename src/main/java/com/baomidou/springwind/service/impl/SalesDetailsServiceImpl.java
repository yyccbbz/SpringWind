package com.baomidou.springwind.service.impl;

import com.baomidou.framework.annotations.Log;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.SalesDetails;
import com.baomidou.springwind.entity.vo.SalesDetailVo;
import com.baomidou.springwind.mapper.SalesDetailsMapper;
import com.baomidou.springwind.service.ISalesDetailsService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Log("根据条件分页查询销售明细")
    @Override
    public Page<SalesDetails> selectPageByParams(Page<SalesDetails> page, SalesDetailVo salesDetailVo) {

        int total = baseMapper.selectCountByParams(salesDetailVo);
        List<SalesDetails> list = baseMapper.selectPageByParams(page.getCurrent()-1,page.getSize(),salesDetailVo);
        page.setTotal(total);
        page.setRecords(list);
        return page;
    }
}
