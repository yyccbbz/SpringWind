package com.baomidou.springwind.service.impl;

import com.baomidou.framework.annotations.Log;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.FinalUser;
import com.baomidou.springwind.mapper.FinalUserMapper;
import com.baomidou.springwind.service.IFinalUserService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

        int total = baseMapper.selectCountByParams(finalUser);
        List<FinalUser> list = baseMapper.selectPageByParams(page.getCurrent()-1,page.getSize(), finalUser);
        page.setTotal(total);
        page.setRecords(list);
        return page;
    }
}
