package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.common.view.SpringMvcExcelView;
import com.baomidou.springwind.entity.UnassignedVipUser;
import com.baomidou.springwind.service.IUnassignedVipUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 未分配的VIP名单 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
@Controller
@RequestMapping("/clientList/unassignedVipUser")
public class UnassignedVipUserController extends BaseController {

    @Autowired
    private IUnassignedVipUserService unassignedVipUserService;

    /**
     * excel导出相关
     */
    @Value("${unassignedVipUser.excelName}")
    private String excelName;
    @Value("${unassignedVipUser.excelId}")
    private String excelId;
    @Value("${unassignedVipUser.fields}")
    private String excelFields;

    /**页面跳转*/
    @Permission("5002")
    @RequestMapping("/list")
    public String list() {
        return "/clientList/unassignedVipUser/list";
    }

    @Permission("5002")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        if (id != null) {
            model.addAttribute("user", unassignedVipUserService.selectById(id));
        }
        return "/clientList/unassignedVipUser/edit";
    }


    /**CRUD*/
    @ResponseBody
    @Permission("5002")
    @RequestMapping(value = "/getUserList")
    public String getUserList(@RequestParam("_userName") String _userName,@RequestParam("_mobileNo") String _mobileNo) {

        System.err.println("筛选条件：客户姓名_userName = " + _userName+"，手机号码_mobileNo = "+_mobileNo);

        EntityWrapper<UnassignedVipUser> ew = new EntityWrapper<>();
        if (StringUtil.isNotEmpty(_userName)) {
            ew.like("user_name", _userName);
        }
        if (StringUtil.isNotEmpty(_mobileNo)) {
            ew.like("mobile_no", _mobileNo);
        }
        Page<UnassignedVipUser> page = getPage();
        Page<UnassignedVipUser> userPage = unassignedVipUserService.selectPage(page, ew);
        return jsonPage(userPage);
    }

    @ResponseBody
    @Permission("5002")
    @RequestMapping("/editUser")
    public String editUser(UnassignedVipUser user) {
        boolean rlt = false;
        if (user != null) {
            if (user.getId() != null) {
                rlt = unassignedVipUserService.updateById(user);
            } else {
                user.setUpdateTime(user.getCreateTime());
                user.setCreateTime(new Date());
                rlt = unassignedVipUserService.insert(user);
            }
        }
        return callbackSuccess(rlt);
    }

    @ResponseBody
    @Permission("5002")
    @RequestMapping("/delUser/{userId}")
    public String delUser(@PathVariable Long userId) {
        Boolean rlt = unassignedVipUserService.deleteById(userId);
        return rlt.toString();
    }


    /**
     * Excel导出列表
     *
     * @return
     */
    @Permission("5002")
    @RequestMapping(value = "/downloadExcel",method = RequestMethod.POST)
    public ModelAndView downloadExcel(@RequestParam("_userName") String _userName,
                                      @RequestParam("_mobileNo") String _mobileNo) throws UnsupportedEncodingException {

        EntityWrapper<UnassignedVipUser> ew = new EntityWrapper<>();
        if (StringUtil.isNotEmpty(_userName)) {
            String userName = new String(_userName.getBytes("iso-8859-1"), "utf-8");
            ew.like("user_name", userName);
        }
        if (StringUtil.isNotEmpty(_mobileNo)) {
            String mobileNo = new String(_mobileNo.getBytes("iso-8859-1"), "utf-8");
            ew.like("mobile_no", mobileNo);
        }
        List<String> fields = Arrays.asList(excelFields.split(","));
        List<UnassignedVipUser> beans = unassignedVipUserService.selectList(ew);
        return super.exportExcel(excelId, beans, null, fields, excelName);
    }

    @ResponseBody
    @Permission("5002")
    @RequestMapping("addTestData")
    public String addTestData() {
        ArrayList<UnassignedVipUser> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            UnassignedVipUser u = new UnassignedVipUser();
            u.setMobileNo(RandomStringUtils.randomNumeric(11));
            u.setMemberNo(RandomStringUtils.randomAlphanumeric(10));
            u.setUserName(RandomStringUtils.randomAlphabetic(5));
            u.setRegisterTime(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            u.setVipDate(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            u.setVipTransDingqi(Integer.parseInt(RandomStringUtils.randomNumeric(4)));
            u.setCreateTime(new Date());
            u.setUpdateTime(u.getCreateTime());
            list.add(u);
            System.err.println(u);
        }
        Boolean b = unassignedVipUserService.insertBatch(list);
        return b.toString();
    }

}
