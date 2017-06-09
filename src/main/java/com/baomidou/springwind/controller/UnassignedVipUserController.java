package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.UnassignedVipUser;
import com.baomidou.springwind.service.IUnassignedVipUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @ResponseBody
    @Permission("5002")
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

        Page<UnassignedVipUser> page = getPage();
        Page<UnassignedVipUser> userPage = unassignedVipUserService.selectPage(page,
                new EntityWrapper<UnassignedVipUser>().allEq(params));
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
                user.setCreateTime(new Date());
                user.setUpdateTime(user.getCreateTime());
                rlt = unassignedVipUserService.insert(user);
            }
        }
        return callbackSuccess(rlt);
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
