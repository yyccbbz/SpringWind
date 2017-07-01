package com.baomidou.springwind.mapper;

import com.baomidou.springwind.entity.CustomerRegistry;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
  * 客户注册情况 Mapper 接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-25
 */
public interface CustomerRegistryMapper extends BaseMapper<CustomerRegistry> {

    @Update("TRUNCATE TABLE customer_registry")
    void truncateTable();
}