package com.baomidou.springwind.service.impl;

import com.baomidou.springwind.entity.PerformancePoolImportUser;
import com.baomidou.springwind.mapper.PerformancePoolImportUserMapper;
import com.baomidou.springwind.service.IPerformancePoolImportUserService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 业绩池名单导入 服务实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
@Service
public class PerformancePoolImportUserServiceImpl extends BaseServiceImpl<PerformancePoolImportUserMapper, PerformancePoolImportUser> implements IPerformancePoolImportUserService {

    @Override
    public void deleteAll() {
        baseMapper.truncateTable();
    }

    @Override
    public void batchInsert(List<PerformancePoolImportUser> listBean) {
        baseMapper.batchInsert(listBean);
    }
}
