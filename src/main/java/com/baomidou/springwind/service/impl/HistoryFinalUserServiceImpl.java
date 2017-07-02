package com.baomidou.springwind.service.impl;

import com.baomidou.framework.annotations.Log;
import com.baomidou.springwind.entity.HistoryFinalUser;
import com.baomidou.springwind.mapper.HistoryFinalUserMapper;
import com.baomidou.springwind.service.IHistoryFinalUserService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 历史正式名单 服务实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
@Service
public class HistoryFinalUserServiceImpl extends BaseServiceImpl<HistoryFinalUserMapper, HistoryFinalUser> implements IHistoryFinalUserService {

    @Log("查询历史正式名单的历史月份数据集")
    @Override
    public List<String> getMonthData() {
        return baseMapper.getMonthData();
    }
}
