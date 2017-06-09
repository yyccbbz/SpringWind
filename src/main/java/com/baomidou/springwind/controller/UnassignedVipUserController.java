package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.UnassignedVipUser;
import com.baomidou.springwind.service.IUnassignedVipUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @Permission("5001")
    @RequestMapping(value = "/getUserList")
    public String getUserList(@RequestParam("_userName") String _userName,@RequestParam("_mobileNo") String _mobileNo) {

        System.err.println("筛选条件：客户姓名_userName = " + _userName+"，手机号码_mobileNo = "+_mobileNo);

        UnassignedVipUser user = new UnassignedVipUser();
        if(StringUtil.isNotEmpty(_userName)){
            user.setUserName(_userName);
        }
        if(StringUtil.isNotEmpty(_mobileNo)){
            user.setUserName(_mobileNo);
        }
        Page<UnassignedVipUser> page = getPage();
        Page<UnassignedVipUser> userPage = unassignedVipUserService.selectPage(page,new EntityWrapper<UnassignedVipUser>(user));
        return jsonPage(userPage);
    }

	
}
