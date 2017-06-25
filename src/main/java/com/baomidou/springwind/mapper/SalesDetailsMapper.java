package com.baomidou.springwind.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.springwind.entity.SalesDetails;
import com.baomidou.springwind.entity.vo.SalesDetailVo;
import org.apache.ibatis.annotations.Param;

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


    int selectCountByParams(@Param("salesDetailVo") SalesDetailVo salesDetailVo);

    List<SalesDetails> selectPageByParams(@Param("current") int current, @Param("size") int size, @Param("salesDetailVo") SalesDetailVo salesDetailVo);
}