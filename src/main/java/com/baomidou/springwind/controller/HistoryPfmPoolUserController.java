package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.HistoryPfmPoolUser;
import com.baomidou.springwind.service.IHistoryPfmPoolUserService;
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
 * 历史业绩池名单 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
@Controller
@RequestMapping("/clientList/historyPfmPoolUser")
public class HistoryPfmPoolUserController extends BaseController {

    @Autowired
    private IHistoryPfmPoolUserService historyPfmPoolUserService;

    /**
     * excel导出相关
     */
    @Value("${historyPfmPoolUser.excelName}")
    private String excelName;
    @Value("${historyPfmPoolUser.excelId}")
    private String excelId;
    @Value("${historyPfmPoolUser.fields}")
    private String excelFields;

    /**
     * 页面跳转
     */
    @Permission("5006")
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("monthIdList", historyPfmPoolUserService.getMonthData());
        return "/clientList/historyPfmPoolUser/list";
    }

    @Permission("5006")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        if (id != null) {
            model.addAttribute("user", historyPfmPoolUserService.selectById(id));
        }
        return "/clientList/historyPfmPoolUser/edit";
    }


    /**
     * CRUD
     */
    @ResponseBody
    @Permission("5006")
    @RequestMapping(value = "/getUserList")
    public String getUserList(@RequestParam("_userName") String _userName,
                              @RequestParam("_mobileNo") String _mobileNo,
                              @RequestParam("_monthId") String _monthId) {

        System.err.println("筛选条件：客户姓名_userName = " + _userName + "，手机号码_mobileNo = " + _mobileNo
                + "，历史月份_monthId = " + _monthId);

        EntityWrapper<HistoryPfmPoolUser> ew = new EntityWrapper<>();
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
        Page<HistoryPfmPoolUser> page = getPage();
        Page<HistoryPfmPoolUser> userPage = historyPfmPoolUserService.selectPage(page, ew);
        return jsonPage(userPage);
    }

    @ResponseBody
    @Permission("5006")
    @RequestMapping("/editUser")
    public String editUser(HistoryPfmPoolUser historyFinalUser) {
        boolean rlt = false;
        if (historyFinalUser != null) {
            if (historyFinalUser.getId() != null) {
                rlt = historyPfmPoolUserService.updateById(historyFinalUser);
            } else {
                historyFinalUser.setCreateTime(new Date());
                historyFinalUser.setUpdateTime(historyFinalUser.getCreateTime());
                rlt = historyPfmPoolUserService.insert(historyFinalUser);
            }
        }
        return callbackSuccess(rlt);
    }

    /**
     * Excel导出列表
     *
     * @return
     */
    @Permission("5006")
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.POST)
    public ModelAndView downloadExcel() {

        List<String> fields = Arrays.asList(excelFields.split(","));
        List<HistoryPfmPoolUser> beans = historyPfmPoolUserService.selectList(null);
        return super.exportExcel(excelId, beans, null, fields, excelName);
    }

    @ResponseBody
    @Permission("5005")
    @RequestMapping("/delUser/{userId}")
    public String delUser(@PathVariable Long userId) {
        Boolean rlt = historyPfmPoolUserService.deleteById(userId);
        return rlt.toString();
    }

    @ResponseBody
    @Permission("5006")
    @RequestMapping("/addTestData")
    public String addTestData() {
        ArrayList<HistoryPfmPoolUser> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            HistoryPfmPoolUser u = new HistoryPfmPoolUser();
            u.setMonthId(RandomStringUtils.random(6, "201706201820162016"));
            u.setMobileNo(RandomStringUtils.randomNumeric(11));
            u.setUserName(RandomStringUtils.randomAlphabetic(5));
            u.setPfmPoolDate(DateUtil.randomDate("2017-01-01", "2017-06-01"));
            u.setAdvisorName(RandomStringUtils.randomAlphabetic(6));
            u.setUserMark(RandomStringUtils.randomAlphanumeric(6));
            u.setCreateTime(new Date());
            u.setUpdateTime(u.getCreateTime());
            list.add(u);
            System.err.println(u);
        }
        Boolean b = historyPfmPoolUserService.insertBatch(list);
        return b.toString();
    }

}
