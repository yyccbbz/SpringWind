package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.common.view.SpringMvcExcelView;
import com.baomidou.springwind.entity.HistoryFinalUser;
import com.baomidou.springwind.service.IHistoryFinalUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

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

    //excel-config.xml中配置的ID
    @Value("${historyFinalUser.excelId}")
    private String userExcelId;

    //excel导出的字段
    @Value("${historyFinalUser.fields}")
    private String userFields;

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
    @RequestMapping("/editUser")
    public String editUser(HistoryFinalUser historyFinalUser) {
        boolean rlt = false;
        if (historyFinalUser!=null) {
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
        String id = userExcelId;
        List<HistoryFinalUser> list = historyFinalUserService.selectList(null);
        List<String> fields = Arrays.asList(userFields.split(","));
        try {
            workbook = excelContext.createExcel(id, list, null, fields);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**2.跳转到Excel下载视图*/
        ModelAndView view = new ModelAndView("springMvcExcelView");
        view.addObject(SpringMvcExcelView.EXCEL_NAME, "历史正式名单" + DateUtil.getCurrentTime());
        view.addObject(SpringMvcExcelView.EXCEL_WORKBOOK, workbook);
        view.addObject(SpringMvcExcelView.EXCEL_EMPTY_MESSAGE, "历史正式名单 没有相关数据可以导出");
        return view;
    }

    @ResponseBody
    @Permission("5005")
    @RequestMapping("/addTestData")
    public String addTestData() {
        ArrayList<HistoryFinalUser> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            HistoryFinalUser u = new HistoryFinalUser();
            u.setMonthId(RandomStringUtils.random(6,"201706201820162016"));
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
