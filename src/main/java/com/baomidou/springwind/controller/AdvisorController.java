package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.Advisor;
import com.baomidou.springwind.service.IAdvisorService;
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
 * 投资顾问表 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-07-02
 */
@Controller
@RequestMapping("/features/advisor")
public class AdvisorController extends BaseController {

    @Autowired
    private IAdvisorService advisorService;

    /**
     * excel导出相关
     */
    @Value("${advisor.excelName}")
    private String excelName;
    @Value("${advisor.excelId}")
    private String excelId;
    @Value("${advisor.fields}")
    private String excelFields;

    /**
     * 页面跳转
     */
    @Permission("9001")
    @RequestMapping("/list")
    public String list() {
        return "/features/advisor/list";
    }

    @Permission("9001")
    @RequestMapping("/search")
    public String search(Model model) {
        model.addAttribute("advisors",
                advisorService.selectList(new EntityWrapper<Advisor>().eq("is_valid", 1)));
        return "/features/advisor/search";
    }

    @Permission("9001")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        if (id != null) {
            model.addAttribute("pojo", advisorService.selectById(id));
        }
        return "/features/advisor/edit";
    }

    /**
     * CRUD
     */
    @ResponseBody
    @Permission("9001")
    @RequestMapping(value = "/getList")
    public String getUserList(@RequestParam("_search") String _search) {

        System.err.println("筛选条件 formData =" + _search);

        Page<Advisor> page = getPage();
        if (StringUtil.isNotEmpty(_search)) {
            //btRegisterTime-->updateTime trans_time-->createTime
            Advisor advisor = JSONObject.parseObject(_search, Advisor.class);
//            page = advisorService.selectPageByParams(page, advisor);
        } else {
            page = advisorService.selectPage(page,
                    new EntityWrapper<Advisor>().orderBy("trans_time", false));
        }
        return jsonPage(page);
    }

    @ResponseBody
    @Permission("9001")
    @RequestMapping(value = "/editOne")
    public String editSalesDetail(Advisor advisor) {
        boolean rlt = false;
        if (advisor != null) {
            if (advisor.getId() != null) {
                rlt = advisorService.updateById(advisor);
            } else {
                advisor.setCreateTime(new Date());
                advisor.setUpdateTime(advisor.getCreateTime());
                rlt = advisorService.insert(advisor);
            }
        }
        return callbackSuccess(rlt);
    }


    @ResponseBody
    @Permission("9001")
    @RequestMapping("/delOne/{id}")
    public String delUser(@PathVariable Long id) {
        Boolean rlt = advisorService.deleteById(id);
        return rlt.toString();
    }

    /**
     * Excel导出列表
     *
     * @return
     */
    @Permission("9001")
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.POST)
    public ModelAndView downloadExcel() {

        List<String> fields = Arrays.asList(excelFields.split(","));
        List<Advisor> beans = advisorService.selectList(null);
        return super.exportExcel(excelId, beans, null, fields, excelName);
    }

    @ResponseBody
    @Permission("9001")
    @RequestMapping("addTestData")
    public String addTestData() {

//        advisorService.deleteAll();

        ArrayList<Advisor> list = new ArrayList<>();
//        for (int i = 1; i <= 100; i++) {
//            Advisor advisor = new Advisor();
//            /*
//#id,serialNo,jobTitle,loginName,actualName,mobileNo,email,teamName,teamLeader,teamLocation,isValid,createTime,updateTime
//            */
//            advisor.settMobileNo(RandomStringUtils.randomNumeric(11));
//            advisor.settMemberNo(RandomStringUtils.randomAlphanumeric(10));
//            advisor.settUserName(RandomStringUtils.randomAlphabetic(5));
//            advisor.setAdvisorId(Integer.parseInt(RandomStringUtils.randomNumeric(4)));
//            advisor.setAdvisorName(RandomStringUtils.randomAlphabetic(5));
//            advisor.settUserType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'1', '2', '3'})));
//            advisor.settReportDate(DateUtil.randomDate("2017-01-01", "2017-07-01"));
//            advisor.settIsPerformancePool(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1'})));
//            advisor.setBtMobileNo(RandomStringUtils.randomNumeric(11));
//            advisor.setBtMemberNo(RandomStringUtils.randomAlphanumeric(10));
//            advisor.setBtUserName(RandomStringUtils.randomAlphabetic(5));
//            advisor.setBtRegisterTime(DateUtil.randomDate("2017-01-01", "2017-07-01"));
//            advisor.setProductId(RandomStringUtils.randomNumeric(4));
//            advisor.setProductName(RandomStringUtils.randomNumeric(4));
//            advisor.setProductType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'1', '2', '3','4'})));
//            advisor.setProductRate(RandomStringUtils.randomNumeric(2));
//            advisor.setTransAmount((Double.valueOf(RandomStringUtils.randomNumeric(6))));
//            advisor.setTransTime(DateUtil.randomDate("2017-01-01", "2017-07-01"));
//            advisor.setInceptionDate(DateUtil.randomDate("2017-01-01", "2017-07-01"));
//            advisor.setDueDate(DateUtil.randomDate("2017-01-01", "2017-07-01"));
//            advisor.setLimitDays(Integer.parseInt(RandomStringUtils.randomNumeric(3)));
//
//            if (i % 3 == 2) {
//                advisor.setLimitType(0);
//            } else if (i % 3 == 0) {
//                advisor.setLimitType(6);
//            } else {
//                advisor.setLimitType(12);
//            }
//
//            advisor.setCreateTime(new Date());
//            advisor.setUpdateTime(advisor.getCreateTime());
//            list.add(advisor);
//            System.err.println(advisor);
//        }
        Boolean b = advisorService.insertBatch(list);
        return b.toString();
    }
}
