package com.baomidou.springwind.mapper;

import com.baomidou.springwind.entity.HistoryFinalUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * 历史正式名单 Mapper 接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
public interface HistoryFinalUserMapper extends BaseMapper<HistoryFinalUser> {

    public List<HistoryFinalUser> getMonthData();

}