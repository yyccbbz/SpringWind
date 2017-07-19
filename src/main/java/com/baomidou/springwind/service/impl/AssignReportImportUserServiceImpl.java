package com.baomidou.springwind.service.impl;

import com.baomidou.springwind.entity.AssignReportImportUser;
import com.baomidou.springwind.mapper.AssignReportImportUserMapper;
import com.baomidou.springwind.service.IAssignReportImportUserService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分配/上报名单导入 服务实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
@Service
public class AssignReportImportUserServiceImpl extends BaseServiceImpl<AssignReportImportUserMapper, AssignReportImportUser> implements IAssignReportImportUserService {

    @Override
    public void deleteAll() {
        baseMapper.truncateTable();
    }

    @Override
    public void batchInsert(List<AssignReportImportUser> listBean) {
        baseMapper.batchInsert(listBean);
    }
}
