package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.FinalUser;
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
    public String getUserList(@RequestParam("_search") String _search) {

        System.err.println("筛选条件 formData =" + _search);

        UnassignedVipUser user = null;
        if(StringUtil.isNotEmpty(_search)){
            user = JSONObject.parseObject(_search, UnassignedVipUser.class);
        }
        Page<UnassignedVipUser> page = getPage();
//        Page<UnassignedVipUser> userPage = unassignedVipUserService.selectPageByParams(page, user);
//        return jsonPage(userPage);
        return null;
    }

	
}
