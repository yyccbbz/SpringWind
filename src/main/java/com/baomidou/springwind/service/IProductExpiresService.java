package com.baomidou.springwind.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.ProductExpires;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 产品到期表 服务类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-25
 */
public interface IProductExpiresService extends IService<ProductExpires> {

    Page<ProductExpires> selectPageByParams(Page<ProductExpires> page, ProductExpires pe);

    void deleteAll();

}
