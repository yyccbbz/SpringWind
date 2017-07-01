package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.ProductExpires;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
  * 产品到期表 Mapper 接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-25
 */
public interface ProductExpiresMapper extends BaseMapper<ProductExpires> {

    @Update("TRUNCATE TABLE product_expires")
    void truncateTable();
}