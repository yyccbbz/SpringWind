package com.baomidou.springwind.service.impl;

import com.baomidou.springwind.entity.HistoryPfmPoolUser;
import com.baomidou.springwind.mapper.HistoryPfmPoolUserMapper;
import com.baomidou.springwind.service.IHistoryPfmPoolUserService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 历史业绩池名单 服务实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
@Service
public class HistoryPfmPoolUserServiceImpl extends BaseServiceImpl<HistoryPfmPoolUserMapper, HistoryPfmPoolUser> implements IHistoryPfmPoolUserService {

    @Override
    public List<String> getMonthData() {
        return baseMapper.getMonthData();
    }
}
