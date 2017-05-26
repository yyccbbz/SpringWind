package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.SystemLog;
import com.baomidou.springwind.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 操作日志表 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
@Controller
@RequestMapping("/log")
public class SystemLogController extends BaseController {

    @Autowired
    private ISystemLogService systemLogService;

    @Permission("4001")
    @RequestMapping("/list")
    public String list(Model model) {
        return "/log/list";
    }

    @ResponseBody
    @Permission("4001")
    @RequestMapping("/getLogList")
    public String getUserList() {
        Page<SystemLog> page = getPage();
        return jsonPage(systemLogService.selectPage(page, new EntityWrapper<SystemLog>().orderBy("createTime",false)));
    }

    @ResponseBody
    @Permission("4001")
    @RequestMapping("/delete/{id}")
    public String delUser(@PathVariable Long id) {
        systemLogService.deleteById(id);
        return Boolean.TRUE.toString();
    }

}
