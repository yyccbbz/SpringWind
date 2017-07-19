package com.baomidou.springwind.mapper;

import com.baomidou.springwind.entity.AssignReportImportUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
  * 分配/上报名单导入 Mapper 接口
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
public interface AssignReportImportUserMapper extends BaseMapper<AssignReportImportUser> {
    @Update("TRUNCATE TABLE assign_report_import_user")
    void truncateTable();

    void batchInsert(@Param("list") List<AssignReportImportUser> list);
}