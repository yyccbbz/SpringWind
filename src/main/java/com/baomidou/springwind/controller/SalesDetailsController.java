package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.common.view.SpringMvcExcelView;
import com.baomidou.springwind.entity.SalesDetails;
import com.baomidou.springwind.entity.vo.SalesDetailsVO;
import com.baomidou.springwind.service.ISalesDetailsService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

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
public class SalesDetailsController extends BaseController {

    @Autowired
    private ISalesDetailsService salesDetailsService;

    /**
     * excel导出相关
     */
    @Value("${salesDetails.excelName}")
    private String excelName;
    @Value("${salesDetails.excelId}")
    private String excelId;
    @Value("${salesDetails.fields}")
    private String excelFields;

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
            model.addAttribute("pojo", salesDetailsService.selectById(id));
        }
        return "/pfmReport/salesDetails/edit";
    }

    /**
     * CRUD
     */
    @ResponseBody
    @Permission("6001")
    @RequestMapping(value = "/getList")
    public String getUserList(@RequestParam("_search") String _search) {

        System.err.println("筛选条件 formData =" + _search);

        Page<SalesDetails> page = getPage();
        if (StringUtil.isNotEmpty(_search)) {
            SalesDetailsVO sdVO = JSONObject.parseObject(_search, SalesDetailsVO.class);
            page = salesDetailsService.selectPageByParams(page, sdVO);
        } else {
            page = salesDetailsService.selectPage(page,
                    new EntityWrapper<SalesDetails>().orderBy("trans_time", false));
        }
        return jsonPage(page);
    }

    @ResponseBody
    @Permission("6001")
    @RequestMapping(value = "/editOne")
    public String editSalesDetail(SalesDetails salesDetails) {
        boolean rlt = false;
        if (salesDetails != null) {
            if (salesDetails.getId() != null) {
                rlt = salesDetailsService.updateById(salesDetails);
            } else {
                salesDetails.setCreateTime(new Date());
                salesDetails.setUpdateTime(salesDetails.getCreateTime());
                rlt = salesDetailsService.insert(salesDetails);
            }
        }
        return callbackSuccess(rlt);
    }


    @ResponseBody
    @Permission("6001")
    @RequestMapping("/delOne/{id}")
    public String delUser(@PathVariable Long id) {
        Boolean rlt = salesDetailsService.deleteById(id);
        return rlt.toString();
    }

    /**
     * Excel导出列表
     *
     * @return
     */
    @Permission("6001")
    @RequestMapping(value = "/downloadExcel",method = RequestMethod.POST)
    public ModelAndView downloadExcel(){

        List<String> fields = Arrays.asList(excelFields.split(","));
        List<SalesDetails> beans = salesDetailsService.selectList(null);
        return super.exportExcel(excelId, beans, null, fields, excelName);
    }

    @ResponseBody
    @Permission("6001")
    @RequestMapping("addTestData")
    public String addTestData() {

        salesDetailsService.deleteAll();

        ArrayList<SalesDetails> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            SalesDetails sd = new SalesDetails();
            /*
id,mobileNo,memberNo,userName,advisorId,
advisorName,productId,productName,productType,productRate,transAmount,transTime,
inceptionDate,dueDate,limitDays,limitType,userType,registerTime,reportDate,
isVipuser,vipDate,isPerformancePool,userMark,createTime,updateTime,
            */
            sd.setMobileNo(RandomStringUtils.randomNumeric(11));
            sd.setMemberNo(RandomStringUtils.randomAlphanumeric(10));
            sd.setUserName(RandomStringUtils.randomAlphabetic(5));
            // advisor --> serialNo
            sd.setAdvisorId(RandomStringUtils.randomNumeric(4));
            sd.setAdvisorName(RandomStringUtils.randomAlphabetic(5));
            sd.setProductId(RandomStringUtils.randomAlphanumeric(4));

            sd.setProductName(RandomStringUtils.randomAlphanumeric(6));
            sd.setProductType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'1', '2', '3', '4'})));
            sd.setProductRate(RandomStringUtils.randomAlphanumeric(2));
            sd.setTransAmount((Double.valueOf(RandomStringUtils.randomNumeric(6))));
            sd.setTransTime(DateUtil.randomDate("2017-01-01", "2017-07-01"));
            sd.setInceptionDate(DateUtil.randomDate("2017-06-01", "2017-10-01"));
            sd.setDueDate(DateUtil.randomDate("2017-10-01", "2018-01-01"));
            sd.setLimitDays(Integer.parseInt(RandomStringUtils.randomNumeric(3)));
            if (i % 3 == 0) {
                sd.setLimitType(0);
            } else if (i % 3 == 1) {
                sd.setLimitType(6);
            } else {
                sd.setLimitType(12);
            }
            sd.setUserType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'1', '2', '3'})));
            sd.setRegisterTime(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            sd.setReportDate(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            sd.setIsVipuser(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1'})));
            sd.setVipDate(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            sd.setIsPerformancePool(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1'})));
            sd.setUserMark(RandomStringUtils.randomAlphanumeric(6));
            sd.setCreateTime(new Date());
            sd.setUpdateTime(sd.getCreateTime());
            list.add(sd);
            System.err.println(sd);
        }
        Boolean b = salesDetailsService.insertBatch(list);
        return b.toString();
    }


}
