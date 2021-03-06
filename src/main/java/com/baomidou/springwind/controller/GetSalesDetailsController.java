package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.Advisor;
import com.baomidou.springwind.entity.GetSalesDetails;
import com.baomidou.springwind.service.IAdvisorService;
import com.baomidou.springwind.service.IGetSalesDetailsService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 获客销售明细 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-15
 */
@Controller
@RequestMapping("/pfmReport/getSalesDetails")
public class GetSalesDetailsController extends BaseController {

    @Autowired
    private IGetSalesDetailsService getSalesDetailsService;

    @Autowired
    private IAdvisorService advisorService;

    /**
     * excel导出相关
     */
    @Value("${getSalesDetails.excelName}")
    private String excelName;
    @Value("${getSalesDetails.excelId}")
    private String excelId;
    @Value("${getSalesDetails.fields}")
    private String excelFields;

    /**
     * 页面跳转
     */
    @Permission("6003")
    @RequestMapping("/list")
    public String list() {
        return "/pfmReport/getSalesDetails/list";
    }

    @Permission("6003")
    @RequestMapping("/search")
    public String search(Model model) {
        model.addAttribute("advisors",
                advisorService.selectList(new EntityWrapper<Advisor>().eq("is_valid", 1)));
        return "/pfmReport/getSalesDetails/search";
    }

    @Permission("6003")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        if (id != null) {
            model.addAttribute("pojo", getSalesDetailsService.selectById(id));
        }
        return "/pfmReport/getSalesDetails/edit";
    }

    /**
     * CRUD
     */
    @ResponseBody
    @Permission("6003")
    @RequestMapping(value = "/getList")
    public String getUserList(@RequestParam("_search") String _search) {

        System.err.println("获客销售明细筛选条件 formData =" + _search);

        Page<GetSalesDetails> page = getPage();
        if (StringUtil.isNotEmpty(_search)) {
            //btRegisterTime-->updateTime trans_time-->createTime
            GetSalesDetails getSalesDetails = JSONObject.parseObject(_search, GetSalesDetails.class);
            page = getSalesDetailsService.selectPageByParams(page, getSalesDetails);
        } else {
            page = getSalesDetailsService.selectPage(page,
                    new EntityWrapper<GetSalesDetails>().orderBy("trans_time", false));
        }
        return jsonPage(page);
    }

    @ResponseBody
    @Permission("6003")
    @RequestMapping(value = "/editOne")
    public String editSalesDetail(GetSalesDetails getSalesDetails) {
        boolean rlt = false;
        if (getSalesDetails != null) {
            if (getSalesDetails.getId() != null) {
                rlt = getSalesDetailsService.updateById(getSalesDetails);
            } else {
                getSalesDetails.setCreateTime(new Date());
                getSalesDetails.setUpdateTime(getSalesDetails.getCreateTime());
                rlt = getSalesDetailsService.insert(getSalesDetails);
            }
        }
        return callbackSuccess(rlt);
    }


    @ResponseBody
    @Permission("6003")
    @RequestMapping("/delOne/{id}")
    public String delUser(@PathVariable Long id) {
        Boolean rlt = getSalesDetailsService.deleteById(id);
        return rlt.toString();
    }

    /**
     * Excel导出列表
     *
     * @return
     */
    @Permission("6003")
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.POST)
    public ModelAndView downloadExcel(@RequestParam("_search") String _search) throws UnsupportedEncodingException {
        String search = new String(_search.getBytes("iso-8859-1"), "utf-8");
        System.err.println("获客销售明细 excel导出的条件 search =" + search);

        EntityWrapper<GetSalesDetails> ew = new EntityWrapper<>();
        if (StringUtil.isNotEmpty(search)) {
            GetSalesDetails gsd = JSONObject.parseObject(search, GetSalesDetails.class);
            if (StringUtil.isNotEmpty(gsd.gettUserName())) {
                ew.like("t_user_name", gsd.gettUserName());
            }
            if (StringUtil.isNotEmpty(gsd.gettMobileNo())) {
                ew.eq("t_mobile_no", gsd.gettMobileNo());
            }
            if (gsd.gettUserType() != null) {
                ew.eq("t_user_type", gsd.gettUserType());
            }

            if (StringUtil.isNotEmpty(gsd.getBtUserName())) {
                ew.like("bt_user_name", gsd.getBtUserName());
            }
            if (StringUtil.isNotEmpty(gsd.getBtMobileNo())) {
                ew.eq("bt_mobile_no", gsd.getBtMobileNo());
            }
            if (StringUtil.isNotEmpty(gsd.getAdvisorName())) {
                ew.eq("advisor_name", gsd.getAdvisorName());
            }

            if (gsd.getBtRegisterTime() != null) {
                ew.gt("bt_register_time", gsd.getBtRegisterTime());
            }
            if (gsd.getUpdateTime() != null) {
                ew.lt("bt_register_time", gsd.getUpdateTime());
            }
            if (gsd.getProductType() != null) {
                ew.eq("product_type", gsd.getProductType());
            }

            if (gsd.getTransTime() != null) {
                ew.gt("trans_time", gsd.getTransTime());
            }
            if (gsd.getCreateTime() != null) {
                ew.lt("trans_time", gsd.getCreateTime());
            }
            if (gsd.getLimitType() != null) {
                ew.eq("limit_type", gsd.getLimitType());
            }
        }
        List<GetSalesDetails> beans = getSalesDetailsService.selectList(ew);
        List<String> fields = Arrays.asList(excelFields.split(","));
        return super.exportExcel(excelId, beans, null, fields, excelName);
    }

    @ResponseBody
    @Permission("6003")
    @RequestMapping("addTestData")
    public String addTestData() {

        getSalesDetailsService.deleteAll();

        ArrayList<GetSalesDetails> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            GetSalesDetails gsd = new GetSalesDetails();

            gsd.settMobileNo(RandomStringUtils.randomNumeric(11));
            gsd.settMemberNo(RandomStringUtils.randomAlphanumeric(10));
            gsd.settUserName(RandomStringUtils.randomAlphabetic(5));
            gsd.setAdvisorId(RandomStringUtils.randomNumeric(4));
            gsd.setAdvisorName(RandomStringUtils.randomAlphabetic(5));
            gsd.settUserType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'1', '2', '3'})));
            gsd.settReportDate(DateUtil.randomDate("2017-01-01", "2017-07-01"));
            gsd.settIsPerformancePool(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1'})));
            gsd.setBtMobileNo(RandomStringUtils.randomNumeric(11));
            gsd.setBtMemberNo(RandomStringUtils.randomAlphanumeric(10));
            gsd.setBtUserName(RandomStringUtils.randomAlphabetic(5));
            gsd.setBtRegisterTime(DateUtil.randomDate("2017-01-01", "2017-07-01"));
            gsd.setProductId(RandomStringUtils.randomNumeric(4));
            gsd.setProductName(RandomStringUtils.randomNumeric(4));
            gsd.setProductType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'1', '2', '3', '4'})));
            gsd.setProductRate(RandomStringUtils.randomNumeric(2));
            gsd.setTransAmount((Double.valueOf(RandomStringUtils.randomNumeric(6))));
            gsd.setTransTime(DateUtil.randomDate("2017-01-01", "2017-07-01"));
            gsd.setInceptionDate(DateUtil.randomDate("2017-01-01", "2017-07-01"));
            gsd.setDueDate(DateUtil.randomDate("2017-01-01", "2017-07-01"));
            gsd.setLimitDays(Integer.parseInt(RandomStringUtils.randomNumeric(3)));

            if (i % 3 == 2) {
                gsd.setLimitType(0);
            } else if (i % 3 == 0) {
                gsd.setLimitType(6);
            } else {
                gsd.setLimitType(12);
            }

            gsd.setCreateTime(new Date());
            gsd.setUpdateTime(gsd.getCreateTime());
            list.add(gsd);
            System.err.println(gsd);
        }
        Boolean b = getSalesDetailsService.insertBatch(list);
        return b.toString();
    }
}
