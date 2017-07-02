package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.GetSalesDetails;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 获客销售明细 Mapper 接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-15
 */
public interface GetSalesDetailsMapper extends BaseMapper<GetSalesDetails> {
    @Update("TRUNCATE TABLE get_sales_details")
    void truncateTable();
}