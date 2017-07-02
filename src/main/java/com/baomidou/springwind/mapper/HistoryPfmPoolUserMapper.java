package com.baomidou.springwind.mapper;

import com.baomidou.springwind.entity.HistoryPfmPoolUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * 历史业绩池名单 Mapper 接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
public interface HistoryPfmPoolUserMapper extends BaseMapper<HistoryPfmPoolUser> {

    List<String> getMonthData();
}