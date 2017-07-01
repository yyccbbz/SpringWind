package com.baomidou.springwind.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.ProductExpires;
import com.baomidou.springwind.mapper.ProductExpiresMapper;
import com.baomidou.springwind.service.IProductExpiresService;
import com.baomidou.springwind.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品到期表 服务实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-25
 */
@Service
public class ProductExpiresServiceImpl extends BaseServiceImpl<ProductExpiresMapper, ProductExpires> implements IProductExpiresService {

    @Override
    public Page<ProductExpires> selectPageByParams(Page<ProductExpires> page, ProductExpires pe) {

        EntityWrapper<ProductExpires> ew = new EntityWrapper<>();

        if (StringUtil.isNotEmpty(pe.getUserName())) {
            ew.like("user_name", pe.getUserName());
        }
        if (StringUtil.isNotEmpty(pe.getMobileNo())) {
            ew.eq("mobile_no", pe.getMobileNo());
        }
        if (StringUtil.isNotEmpty(pe.getAdvisorName())) {
            ew.eq("advisor_name", pe.getAdvisorName());
        }
        if (pe.getDueDate() != null) {
            ew.gt("due_date", pe.getDueDate());
        }
        if (pe.getInceptionDate() != null) {
            ew.lt("due_date", pe.getInceptionDate());
        }
        if (pe.getLimitType() != null) {
            ew.eq("limit_type", pe.getLimitType());
        }

        ew.orderBy("due_date", false);
        System.err.println(ew.getSqlSegment());
        return selectPage(page, ew);
    }

    @Override
    public void deleteAll() {
        baseMapper.truncateTable();
    }
}
