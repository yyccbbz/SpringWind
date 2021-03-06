package com.baomidou.springwind.service;

import com.baomidou.springwind.entity.PerformancePoolImportUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 业绩池名单导入 服务类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
public interface IPerformancePoolImportUserService extends IService<PerformancePoolImportUser> {

    void deleteAll();

    void batchInsert(List<PerformancePoolImportUser> listBean);
}
