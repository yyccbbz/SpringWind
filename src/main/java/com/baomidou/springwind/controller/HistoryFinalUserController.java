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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
public class HistoryFinalUserController extends BaseController{

    @Autowired
    private IHistoryFinalUserService historyFinalUserService;

    /**页面跳转*/
    @Permission("5005")
    @RequestMapping("/list")
    public String list() {
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


    /**CRUD*/
    @ResponseBody
    @Permission("5005")
    @RequestMapping(value = "/getUserList")
    public String getUserList(@RequestParam("_userName") String _userName,@RequestParam("_mobileNo") String _mobileNo) {

        System.err.println("筛选条件：客户姓名_userName = " + _userName+"，手机号码_mobileNo = "+_mobileNo);

        Map<String,Object> params = new HashMap<>();

        if(StringUtil.isNotEmpty(_userName)){
            params.put("user_name",_userName);
        }

        if(StringUtil.isNotEmpty(_mobileNo)){
            params.put("mobile_no",_mobileNo);
        }

        Page<HistoryFinalUser> page = getPage();
        Page<HistoryFinalUser> userPage = historyFinalUserService.selectPage(page,
                new EntityWrapper<HistoryFinalUser>().allEq(params));
        return jsonPage(userPage);
    }


    @ResponseBody
    @Permission("5005")
    @RequestMapping("addTestData")
    public String addTestData() {
        ArrayList<HistoryFinalUser> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            HistoryFinalUser u = new HistoryFinalUser();
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
