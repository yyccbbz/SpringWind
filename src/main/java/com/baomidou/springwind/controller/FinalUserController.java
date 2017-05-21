package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.FinalUser;
import com.baomidou.springwind.service.IFinalUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;

/**
 * <p>
 * 正式名单 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
@Controller
@RequestMapping("/clientList/finalUser")
public class FinalUserController extends BaseController {

    @Autowired
    private IFinalUserService finalUserService;

    /*页面跳转*/
    @Permission("5001")
    @RequestMapping("/list")
    public String list() {
        return "/clientList/finalUser/list";
    }

    @Permission("5001")
    @RequestMapping("/search")
    public String search() {
        return "/clientList/finalUser/search";
    }


    /*CRUD*/
    @ResponseBody
    @Permission("5001")
    @RequestMapping(value = "/getUserList")
    public String getUserList(@RequestParam("_search") String _search) {

        System.err.println("筛选条件 formData =" + _search);

        FinalUser finalUser = null;
        if(StringUtil.isNotEmpty(_search)){
             finalUser = JSONObject.parseObject(_search, FinalUser.class);
        }
        Page<FinalUser> page = getPage();
        Page<FinalUser> userPage = finalUserService.selectPageByParams(page, finalUser);
        return jsonPage(userPage);
    }

    /*@ResponseBody
    @Permission("2001")
    @RequestMapping("/editUser")
    public String editUser(FinalUser user) {
        boolean rlt = false;
        if (user != null) {
            user.setPassword(SaltEncoder.md5SaltEncode(user.getLoginName(), user.getPassword()));
            if (user.getId() != null) {
                rlt = userService.updateById(user);
            } else {
                user.setCreateTime(new Date());
                user.setLastTime(user.getCreateTime());
                rlt = userService.insert(user);
            }
        }
        return callbackSuccess(rlt);
    }*/

    /*@ResponseBody
    @Permission("2001")
    @RequestMapping("/delUser/{userId}")
    public String delUser(@PathVariable Long userId) {
        finalUserService.deleteUser(userId);
        return Boolean.TRUE.toString();
    }*/

    @ResponseBody
    @Permission("5001")
    @RequestMapping("addTestData")
    public String addTestData(){

        ArrayList<FinalUser> list = new ArrayList<>();
        for (int i = 1;i<= 80;i++){
            FinalUser u = new FinalUser();

            u.setMobileNo(RandomStringUtils.randomNumeric(11));
            u.setMemberNo(RandomStringUtils.randomAlphanumeric(10));
            u.setUserName(RandomStringUtils.randomAlphabetic(5));
            u.setUserType(Integer.parseInt(RandomStringUtils.random(1,new char[]{'1','2','3'})));
            u.setReportDate(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            u.setRegisterTime(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            u.setIsVipuser(Integer.parseInt(RandomStringUtils.random(1,new char[]{'0','1'})));
            u.setVipDate(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            u.setAdvisorId(Integer.parseInt(RandomStringUtils.randomNumeric(4)));
            u.setAdvisorName(RandomStringUtils.randomAlphabetic(6));
            u.setUserMark(RandomStringUtils.randomAlphanumeric(6));
            u.setIsPerformancePool(Integer.parseInt(RandomStringUtils.random(1,new char[]{'0','1'})));
            u.setCreateTime(new Date());
            u.setUpdateTime(u.getCreateTime());

            list.add(u);

            System.out.println(u);
        }

        Boolean b = finalUserService.insertBatch(list);

        return b.toString();
    }








}
