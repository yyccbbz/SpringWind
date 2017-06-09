package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 分配/上报名单导入 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
@Controller
@RequestMapping("/clientList/importUser")
public class AssignReportImportUserController {

    /**页面跳转*/
    @Permission("5003")
    @RequestMapping("/list")
    public String list() {
        return "/clientList/importUser/list";
    }
	
}
