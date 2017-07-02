package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.HistoryFinalUser;
import com.baomidou.springwind.service.IHistoryFinalUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 历史正式名单 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
@Controller
@RequestMapping("/clientList/historyFinalUser")
public class HistoryFinalUserController extends BaseController {

    @Autowired
    private IHistoryFinalUserService historyFinalUserService;

    /**
     * excel导出相关
     */
    @Value("${historyFinalUser.excelName}")
    private String excelName;
    @Value("${historyFinalUser.excelId}")
    private String excelId;
    @Value("${historyFinalUser.fields}")
    private String excelFields;

    /**
     * 页面跳转
     */
    @Permission("5005")
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("monthIdList", historyFinalUserService.getMonthData());
        return "/clientList/historyFinalUser/list";
    }

    @Permission("5005")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        if (id != null) {
            model.addAttribute("user", historyFinalUserService.selectById(id));
        }
        return "/clientList/historyFinalUser/edit";
    }


    /**
     * CRUD
     */
    @ResponseBody
    @Permission("5005")
    @RequestMapping(value = "/getUserList")
    public String getUserList(@RequestParam("_userName") String _userName,
                              @RequestParam("_mobileNo") String _mobileNo,
                              @RequestParam("_monthId") String _monthId) {

        System.err.println("筛选条件：客户姓名_userName = " + _userName + "，手机号码_mobileNo = " + _mobileNo
                + "，历史月份_monthId = " + _monthId);

        EntityWrapper<HistoryFinalUser> ew = new EntityWrapper<>();
        if (StringUtil.isNotEmpty(_userName)) {
            ew.like("user_name", _userName);
        }
        if (StringUtil.isNotEmpty(_mobileNo)) {
            ew.like("mobile_no", _mobileNo);
        }
        if (StringUtil.isNotEmpty(_monthId)) {
            ew.eq("month_id", _monthId);
        }
        ew.orderBy("month_id",false);
        Page<HistoryFinalUser> page = getPage();
        Page<HistoryFinalUser> userPage = historyFinalUserService.selectPage(page, ew);
        return jsonPage(userPage);
    }

    @ResponseBody
    @Permission("5005")
    @RequestMapping("/editUser")
    public String editUser(HistoryFinalUser historyFinalUser) {
        boolean rlt = false;
        if (historyFinalUser != null) {
            if (historyFinalUser.getId() != null) {
                rlt = historyFinalUserService.updateById(historyFinalUser);
            } else {
                historyFinalUser.setCreateTime(new Date());
                historyFinalUser.setUpdateTime(historyFinalUser.getCreateTime());
                rlt = historyFinalUserService.insert(historyFinalUser);
            }
        }
        return callbackSuccess(rlt);
    }


    /**
     * Excel导出列表
     *
     * @return
     */
    @Permission("5005")
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.POST)
    public ModelAndView downloadExcel() {

        List<String> fields = Arrays.asList(excelFields.split(","));
        List<HistoryFinalUser> beans = historyFinalUserService.selectList(null);
        return super.exportExcel(excelId, beans, null, fields, excelName);
    }

    @ResponseBody
    @Permission("5005")
    @RequestMapping("/addTestData")
    public String addTestData() {
        ArrayList<HistoryFinalUser> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            HistoryFinalUser u = new HistoryFinalUser();
            u.setMonthId(RandomStringUtils.random(6, "201706201820162016"));
            u.setMobileNo(RandomStringUtils.randomNumeric(11));
            u.setMemberNo(RandomStringUtils.randomAlphanumeric(10));
            u.setUserName(RandomStringUtils.randomAlphabetic(5));
            u.setUserType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'1', '2', '3'})));
            u.setReportDate(DateUtil.randomDate("2017-01-01", "2017-06-01"));
            u.setRegisterTime(DateUtil.randomDate("2017-01-01", "2017-06-01"));
            u.setIsVipuser(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1'})));
            u.setVipDate(DateUtil.randomDate("2017-01-01", "2017-06-01"));
            u.setAdvisorId(Integer.parseInt(RandomStringUtils.randomNumeric(4)));
            u.setAdvisorName(RandomStringUtils.randomAlphabetic(6));
            u.setUserMark(RandomStringUtils.randomAlphanumeric(6));
            u.setIsPerformancePool(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1'})));
            u.setCreateTime(new Date());
            u.setUpdateTime(u.getCreateTime());
            list.add(u);
            System.err.println(u);
        }
        Boolean b = historyFinalUserService.insertBatch(list);
        return b.toString();
    }

}
