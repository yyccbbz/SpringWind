package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.SalesDetails;
import com.baomidou.springwind.entity.vo.SalesDetailsVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 销售明细 Mapper 接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-25
 */
public interface SalesDetailsMapper extends BaseMapper<SalesDetails> {

    @Update("TRUNCATE TABLE sales_details")
    void truncateTable();

    int selectCountByParams(@Param("salesDetailsVO") SalesDetailsVO salesDetailsVO);

    List<SalesDetails> selectPageByParams(@Param("current") int current, @Param("size") int size, @Param("salesDetailsVO") SalesDetailsVO salesDetailsVO);
}