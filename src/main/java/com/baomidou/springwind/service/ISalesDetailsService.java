package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.SalesDetails;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springwind.entity.vo.SalesDetailsVO;

/**
 * <p>
 * 销售明细 服务类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-15
 */
public interface ISalesDetailsService extends IService<SalesDetails> {

    Page<SalesDetails> selectPageByParams(Page<SalesDetails> page, SalesDetailsVO adVO);

    void deleteAll();
}
