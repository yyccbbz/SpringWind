package com.baomidou.springwind.mapper;

import com.baomidou.springwind.entity.GetInformation;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
  * 获客信息 Mapper 接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-15
 */
public interface GetInformationMapper extends BaseMapper<GetInformation> {

    @Update("TRUNCATE TABLE get_information")
    void truncateTable();
}