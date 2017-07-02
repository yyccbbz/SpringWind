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

        System.err.println("筛选条件 formData =" + _search);

        Page<GetSalesDetails> page = getPage();
        if (StringUtil.isNotEmpty(_search)) {
            //btRegisterTime-->updateTime
            GetSalesDetails getSalesDetails = JSONObject.parseObject(_search, GetSalesDetails.class);
            page = getSalesDetailsService.selectPageByParams(page, getSalesDetails);
        } else {
            page = getSalesDetailsService.selectPage(page,
                    new EntityWrapper<GetSalesDetails>().orderBy("bt_register_time", false));
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
    public ModelAndView downloadExcel() {

        List<String> fields = Arrays.asList(excelFields.split(","));
        List<GetSalesDetails> beans = getSalesDetailsService.selectList(null);
        return super.exportExcel(excelId, beans, null, fields, excelName);
    }

    @ResponseBody
    @Permission("6003")
    @RequestMapping("addTestData")
    public String addTestData() {

        getSalesDetailsService.deleteAll();

        ArrayList<GetSalesDetails> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            GetSalesDetails gi = new GetSalesDetails();
            /*
#id,tMobileNo,tMemberNo,tUserName,advisorId,advisorName,tUserType,tReportDate,tIsPerformancePool,btMobileNo,btMemberNo,
#btUserName,btRegisterTime,productId,productName,productType,productRate,transAmount,transTime,inceptionDate,dueDate,limitDays,limitType,createTime,updateTime,
            */
            gi.settMobileNo(RandomStringUtils.randomNumeric(11));
            gi.settMemberNo(RandomStringUtils.randomAlphanumeric(10));
            gi.settUserName(RandomStringUtils.randomAlphabetic(5));
            gi.setAdvisorId(Integer.parseInt(RandomStringUtils.randomNumeric(4)));
            gi.setAdvisorName(RandomStringUtils.randomAlphabetic(5));
            gi.settUserType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'1', '2', '3'})));
            gi.settReportDate(DateUtil.randomDate("2017-01-01", "2017-07-01"));
            gi.settIsPerformancePool(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1'})));
            gi.setBtMobileNo(RandomStringUtils.randomNumeric(11));
            gi.setBtMemberNo(RandomStringUtils.randomAlphanumeric(10));
            gi.setBtUserName(RandomStringUtils.randomAlphabetic(5));
            gi.setBtRegisterTime(DateUtil.randomDate("2017-01-01", "2017-07-01"));
//            gi.setBtTransAmount((Double.valueOf(RandomStringUtils.randomNumeric(6))));
            gi.setCreateTime(new Date());
            gi.setUpdateTime(gi.getCreateTime());
            list.add(gi);
            System.err.println(gi);
        }
        Boolean b = getSalesDetailsService.insertBatch(list);
        return b.toString();
    }
}
