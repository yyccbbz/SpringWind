package com.baomidou.springwind.service.impl;

import com.baomidou.framework.annotations.Log;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.FinalUser;
import com.baomidou.springwind.mapper.FinalUserMapper;
import com.baomidou.springwind.service.IFinalUserService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 正式名单 服务实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
@Service
public class FinalUserServiceImpl extends BaseServiceImpl<FinalUserMapper, FinalUser> implements IFinalUserService {


    @Log("根据条件分页查询正式用户名单")
    @Override
    public Page<FinalUser> selectPageByParams(Page<FinalUser> page, FinalUser finalUser) {

        EntityWrapper<FinalUser> ew = new EntityWrapper<>();
//        ew.where("1={0}", "1");
        if (StringUtil.isNotEmpty(finalUser.getUserName())) {
            ew.like("user_name", finalUser.getUserName());
        }
        if (StringUtil.isNotEmpty(finalUser.getMobileNo())) {
            ew.eq("mobile_no", finalUser.getMobileNo());
        }
        if (finalUser.getUserType() != null) {
            ew.eq("user_type", finalUser.getUserType());
        }
        if (finalUser.getIsVipuser() != null) {
            ew.eq("is_vipuser", finalUser.getIsVipuser());
        }
        if (StringUtil.isNotEmpty(finalUser.getAdvisorName())) {
            ew.eq("advisor_name", finalUser.getAdvisorName());
        }
        if (StringUtil.isNotEmpty(finalUser.getUserMark())) {
            ew.eq("user_mark", finalUser.getUserMark());
        }
        if (finalUser.getIsPerformancePool() != null) {
            ew.eq("is_performance_pool", finalUser.getIsPerformancePool());
        }
        // 日期比较有三种情况
        if (finalUser.getReportDate() != null) {
            ew.gt("report_date", finalUser.getReportDate());
        }
        if (finalUser.getVipDate() != null) {
            ew.lt("report_date", finalUser.getVipDate());
        }
        ew.orderBy("report_date", false);
        System.err.println(ew.getSqlSegment());
        return selectPage(page, ew);
    }
}
