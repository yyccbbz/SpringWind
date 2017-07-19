package com.baomidou.springwind.service;

import com.baomidou.springwind.entity.AssignReportImportUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 分配/上报名单导入 服务类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
public interface IAssignReportImportUserService extends IService<AssignReportImportUser> {

    void deleteAll();

    void batchInsert(List<AssignReportImportUser> listBean);
}
