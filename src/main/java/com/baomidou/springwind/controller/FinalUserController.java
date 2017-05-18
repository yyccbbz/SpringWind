package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.common.encrypt.SaltEncoder;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.FinalUser;
import com.baomidou.springwind.service.IFinalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String list(Model model) {
        return "/clientList/finalUser/list";
    }


    /*CRUD*/
    @ResponseBody
    @Permission("2001")
    @RequestMapping("/getUserList")
    public String getUserList(@RequestParam("_search") String _search) {

        System.out.println("搜索条件 =" + _search);

//        Page<FinalUser> page = getPage();
//        Page<FinalUser> userPage = finalUserService.selectPageBySearch(page, StringUtil.getStrEmpty(_search));
//        return jsonPage(userPage);
        return "";
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










}
