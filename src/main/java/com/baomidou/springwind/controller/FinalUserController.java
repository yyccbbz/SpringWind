package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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


    /*页面跳转*/
    @Permission("5001")
    @RequestMapping("/list")
    public String list(Model model) {
        return "/clientList/finalUser/list";
    }

}
