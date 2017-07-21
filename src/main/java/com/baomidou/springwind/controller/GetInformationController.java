package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.Advisor;
import com.baomidou.springwind.entity.GetInformation;
import com.baomidou.springwind.service.IGetInformationService;
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
 * 获客信息 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-15
 */
@Controller
@RequestMapping("/pfmReport/getInformation")
public class GetInformationController extends BaseController {

    @Autowired
    private IGetInformationService getInformationService;

    /**
     * excel导出相关
     */
    @Value("${getInformation.excelName}")
    private String excelName;
    @Value("${getInformation.excelId}")
    private String excelId;
    @Value("${getInformation.fields}")
    private String excelFields;

    /**
     * 页面跳转
     */
    @Permission("6002")
    @RequestMapping("/list")
    public String list() {
        return "/pfmReport/getInformation/list";
    }

    @Permission("6002")
    @RequestMapping("/search")
    public String search(Model model) {
        model.addAttribute("advisors",
                advisorService.selectList(new EntityWrapper<Advisor>().eq("is_valid", 1)));
        return "/pfmReport/getInformation/search";
    }

    @Permission("6002")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        if (id != null) {
            model.addAttribute("pojo", getInformationService.selectById(id));
        }
        return "/pfmReport/getInformation/edit";
    }

    /**
     * CRUD
     */
    @ResponseBody
    @Permission("6002")
    @RequestMapping(value = "/getList")
    public String getUserList(@RequestParam("_search") String _search) {

        System.err.println("筛选条件 formData =" + _search);

        Page<GetInformation> page = getPage();
        if (StringUtil.isNotEmpty(_search)) {
            //btRegisterTime-->updateTime
            GetInformation getInformation = JSONObject.parseObject(_search, GetInformation.class);
            page = getInformationService.selectPageByParams(page, getInformation);
        } else {
            page = getInformationService.selectPage(page,
                    new EntityWrapper<GetInformation>().orderBy("bt_register_time", false));
        }
        return jsonPage(page);
    }

    @ResponseBody
    @Permission("6002")
    @RequestMapping(value = "/editOne")
    public String editSalesDetail(GetInformation getInformation) {
        boolean rlt = false;
        if (getInformation != null) {
            if (getInformation.getId() != null) {
                rlt = getInformationService.updateById(getInformation);
            } else {
                getInformation.setCreateTime(new Date());
                getInformation.setUpdateTime(getInformation.getCreateTime());
                rlt = getInformationService.insert(getInformation);
            }
        }
        return callbackSuccess(rlt);
    }


    @ResponseBody
    @Permission("6002")
    @RequestMapping("/delOne/{id}")
    public String delUser(@PathVariable Long id) {
        Boolean rlt = getInformationService.deleteById(id);
        return rlt.toString();
    }

    /**
     * Excel导出列表
     *
     * @return
     */
    @Permission("6002")
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.POST)
    public ModelAndView downloadExcel(@RequestParam("_search") String _search) throws UnsupportedEncodingException {
        String search = new String(_search.getBytes("iso-8859-1"), "utf-8");
        System.err.println("获客信息 excel导出的条件 search =" + search);

        EntityWrapper<GetInformation> ew = new EntityWrapper<>();
        if (StringUtil.isNotEmpty(search)) {
            GetInformation gi = JSONObject.parseObject(search, GetInformation.class);
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
        }
        List<GetInformation> beans = getInformationService.selectList(ew);
        List<String> fields = Arrays.asList(excelFields.split(","));
        return super.exportExcel(excelId, beans, null, fields, excelName);
    }

    @ResponseBody
    @Permission("6002")
    @RequestMapping("addTestData")
    public String addTestData() {

        getInformationService.deleteAll();

        ArrayList<GetInformation> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            GetInformation gi = new GetInformation();

            gi.settMobileNo(RandomStringUtils.randomNumeric(11));
            gi.settMemberNo(RandomStringUtils.randomAlphanumeric(10));
            gi.settUserName(RandomStringUtils.randomAlphabetic(5));
            gi.setAdvisorId(RandomStringUtils.randomNumeric(4));
            gi.setAdvisorName(RandomStringUtils.randomAlphabetic(5));
            gi.settUserType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'1', '2', '3'})));
            gi.settReportDate(DateUtil.randomDate("2017-01-01", "2017-07-01"));
            gi.settIsPerformancePool(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1'})));
            gi.setBtMobileNo(RandomStringUtils.randomNumeric(11));
            gi.setBtMemberNo(RandomStringUtils.randomAlphanumeric(10));
            gi.setBtUserName(RandomStringUtils.randomAlphabetic(5));
            gi.setBtRegisterTime(DateUtil.randomDate("2017-01-01", "2017-07-01"));
            gi.setBtTransAmount((Double.valueOf(RandomStringUtils.randomNumeric(6))));
            gi.setCreateTime(new Date());
            gi.setUpdateTime(gi.getCreateTime());
            list.add(gi);
            System.err.println(gi);
        }
        Boolean b = getInformationService.insertBatch(list);
        return b.toString();
    }

}
