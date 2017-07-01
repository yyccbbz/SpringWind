package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.AssetsBalance;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 资产余额表 服务类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-25
 */
public interface IAssetsBalanceService extends IService<AssetsBalance> {

    Page<AssetsBalance> selectPageByParams(Page<AssetsBalance> page, AssetsBalance ab);

    void deleteAll();

}
