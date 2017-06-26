package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.SalesDetails;
import com.baomidou.springwind.entity.vo.SalesDetailVo;
import com.baomidou.springwind.service.ISalesDetailsService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 销售明细 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-15
 */
@Controller
@RequestMapping("/pfmReport/salesDetails")
public class

SalesDetailsController extends BaseController {

    @Autowired
    private ISalesDetailsService salesDetailsService;


    /**
     * 页面跳转
     */
    @Permission("6001")
    @RequestMapping("/list")
    public String list() {
        return "/pfmReport/salesDetails/list";
    }

    @Permission("6001")
    @RequestMapping("/search")
    public String search() {
        return "/pfmReport/salesDetails/search";
    }

    @Permission("6001")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        if (id != null) {
            model.addAttribute("user", salesDetailsService.selectById(id));
        }
        return "/pfmReport/salesDetails/edit";
    }

    /**
     * CRUD
     */
    @ResponseBody
    @Permission("6001")
    @RequestMapping(value = "/getUserList")
    public String getUserList(@RequestParam("_search") String _search) {

        System.err.println("筛选条件 formData =" + _search);

        Map<String, Object> params = new HashMap<>();

        SalesDetailVo salesDetailVo = null;

        if (StringUtil.isNotEmpty(_search)) {
            salesDetailVo = JSONObject.parseObject(_search, SalesDetailVo.class);
        }
        Page<SalesDetails> page = getPage();
        Page<SalesDetails> userPage = salesDetailsService.selectPageByParams(page, salesDetailVo);
        return jsonPage(userPage);
    }

    @ResponseBody
    @Permission("6001")
    @RequestMapping("addTestData")
    public String addTestData() {
        ArrayList<SalesDetails> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            SalesDetails u = new SalesDetails();
            u.setMobileNo(RandomStringUtils.randomNumeric(11));
            u.setMemberNo(RandomStringUtils.randomAlphanumeric(10));
            u.setUserName(RandomStringUtils.randomAlphabetic(5));
            u.setProductName(RandomStringUtils.randomAlphanumeric(6));
            u.setProductType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'1', '2', '3', '4'})));
            u.setProductRate(RandomStringUtils.randomAlphanumeric(10));
            u.setTransAmount((Double.valueOf(RandomStringUtils.randomNumeric(6))));
            u.setTransTime(DateUtil.randomDate("2017-01-01", "2017-06-01"));
            u.setInceptionDate(DateUtil.randomDate("2017-06-01", "2017-10-01"));
            u.setDueDate(DateUtil.randomDate("2017-10-01", "2018-01-01"));
            u.setLimitDays(Integer.parseInt(RandomStringUtils.randomNumeric(3)));
            u.setLimitType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1', '2'})));
            u.setUserType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'1', '2', '3'})));
            u.setReportDate(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            u.setRegisterTime(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            u.setIsVipuser(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1'})));
            u.setVipDate(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            u.setAdvisorId(Integer.parseInt(RandomStringUtils.randomNumeric(4)));
            u.setAdvisorName(RandomStringUtils.randomAlphabetic(6));
            u.setUserMark(RandomStringUtils.randomAlphanumeric(6));
            u.setIsPerformancePool(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1'})));
            u.setCreateTime(new Date());
            u.setUpdateTime(u.getCreateTime());
            list.add(u);
            System.err.println(u);
        }
        Boolean b = salesDetailsService.insertBatch(list);
        return b.toString();
    }


}
