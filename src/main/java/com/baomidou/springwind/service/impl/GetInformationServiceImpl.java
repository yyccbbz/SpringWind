package com.baomidou.springwind.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.GetInformation;
import com.baomidou.springwind.mapper.GetInformationMapper;
import com.baomidou.springwind.service.IGetInformationService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 获客信息 服务实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-15
 */
@Service
public class GetInformationServiceImpl extends BaseServiceImpl<GetInformationMapper, GetInformation> implements IGetInformationService {

    @Override
    public Page<GetInformation> selectPageByParams(Page<GetInformation> page, GetInformation gi) {

        EntityWrapper<GetInformation> ew = new EntityWrapper<>();

        if (StringUtil.isNotEmpty(gi.gettUserName())) {
            ew.like("t_user_name", gi.gettUserName());
        }
        if (StringUtil.isNotEmpty(gi.gettMobileNo())) {
            ew.eq("t_mobile_no", gi.gettMobileNo());
        }
        if (gi.gettUserType() != null) {
            ew.eq("t_user_type", gi.gettUserType());
        }

        if (StringUtil.isNotEmpty(gi.getBtUserName())) {
            ew.like("bt_user_name", gi.getBtUserName());
        }
        if (StringUtil.isNotEmpty(gi.getBtMobileNo())) {
            ew.eq("bt_mobile_no", gi.getBtMobileNo());
        }
        if (StringUtil.isNotEmpty(gi.getAdvisorName())) {
            ew.eq("advisor_name", gi.getAdvisorName());
        }

        if (gi.getBtRegisterTime() != null) {
            ew.gt("bt_register_time", gi.getBtRegisterTime());
        }
        if (gi.getUpdateTime() != null) {
            ew.lt("bt_register_time", gi.getUpdateTime());
        }
        if (gi.gettIsPerformancePool() != null) {
            ew.eq("t_is_performance_pool", gi.gettIsPerformancePool());
        }

        ew.orderBy("bt_register_time", false);
        System.err.println(ew.getSqlSegment());
        return selectPage(page, ew);
    }

    @Override
    public void deleteAll() {
        baseMapper.truncateTable();
    }
}
