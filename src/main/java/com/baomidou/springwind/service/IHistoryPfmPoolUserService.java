package com.baomidou.springwind.service;

import com.baomidou.springwind.entity.HistoryPfmPoolUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 历史业绩池名单 服务类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
public interface IHistoryPfmPoolUserService extends IService<HistoryPfmPoolUser> {

    List<String> getMonthData();
}
