package com.baomidou.springwind.mapper;

import com.baomidou.springwind.entity.AssetsBalance;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
  * 资产余额表 Mapper 接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-25
 */
public interface AssetsBalanceMapper extends BaseMapper<AssetsBalance> {

    @Update("TRUNCATE TABLE assets_balance")
    void truncateTable();
}