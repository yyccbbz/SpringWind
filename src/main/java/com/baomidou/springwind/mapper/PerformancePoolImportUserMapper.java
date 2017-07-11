package com.baomidou.springwind.mapper;

import com.baomidou.springwind.entity.PerformancePoolImportUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
  * 业绩池名单导入 Mapper 接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
public interface PerformancePoolImportUserMapper extends BaseMapper<PerformancePoolImportUser> {
    @Update("TRUNCATE TABLE performance_pool_import_user")
    void truncateTable();
}