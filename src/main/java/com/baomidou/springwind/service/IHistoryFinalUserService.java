package com.baomidou.springwind.service;

import com.baomidou.springwind.entity.HistoryFinalUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 历史正式名单 服务类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
public interface IHistoryFinalUserService extends IService<HistoryFinalUser> {

    List<String> getMonthData();
	
}
