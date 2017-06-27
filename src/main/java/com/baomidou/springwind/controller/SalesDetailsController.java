package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.common.view.SpringMvcExcelView;
import com.baomidou.springwind.entity.SalesDetails;
import com.baomidou.springwind.entity.vo.SalesDetailVo;
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

    //excel-config.xml中配置的ID
    @Value("${salesDetails.excelId}")
    private String salesDetailsExcelId;

    //excel导出的字段
    @Value("${salesDetails.fields}")
    private String salesDetailsFields;

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
    @RequestMapping(value = "/getSalesDetailList")
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
    @RequestMapping(value = "/editSalesDetail",method = RequestMethod.POST)
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


    /**
     * Excel导出列表
     *
     * @return
     */
    @Permission("6001")
    @RequestMapping(value = "/downloadExcel",method = RequestMethod.POST)
    public ModelAndView downloadExcel(){

        /**1.执行你的业务逻辑获取数据，使用ExcelContent生成Workbook，需要四个参数:
         *
         * ①id 配置ID
         * ②beans 配置class对应的List
         * ③header 导出之前,在标题前面做出一些额外的操作,比如增加文档描述等,可以为null
         * ④fields 指定Excel导出的字段(bean对应的字段名称),可以为null
         */
        Workbook workbook = null;
        String id = salesDetailsExcelId;
        List<SalesDetails> list = salesDetailsService.selectList(null);
        List<String> fields = Arrays.asList(salesDetailsFields.split(","));
        try {
            workbook = excelContext.createExcel(id, list, null, fields);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**2.跳转到Excel下载视图*/
        ModelAndView view = new ModelAndView("springMvcExcelView");
        view.addObject(SpringMvcExcelView.EXCEL_NAME, "销售明细" + DateUtil.getCurrentTime());
        view.addObject(SpringMvcExcelView.EXCEL_WORKBOOK, workbook);
        view.addObject(SpringMvcExcelView.EXCEL_EMPTY_MESSAGE, "销售明细 没有相关数据可以导出");
        return view;
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
