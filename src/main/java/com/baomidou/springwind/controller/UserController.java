package com.baomidou.springwind.controller;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.common.encrypt.SaltEncoder;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.common.view.SpringMvcExcelView;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.service.IRoleService;
import com.baomidou.springwind.service.IUserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户管理相关操作
 * </p>
 *
 * @Author Jack
 * @Date 2016/4/15 15:03
 */
@Controller
@RequestMapping("/perm/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Value("${user.fields}")
    private String userFields;

    @Permission("2001")
    @RequestMapping("/list")
    public String list(Model model) {
        return "/user/list";
    }

    @Permission("2001")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        if (id != null) {
            model.addAttribute("user", userService.selectById(id));
        }
        model.addAttribute("roleList", roleService.selectList(null));
        return "/user/edit";
    }

    @ResponseBody
    @Permission("2001")
    @RequestMapping("/editUser")
    public String editUser(User user) {
        boolean rlt = false;
        if (user != null) {
            user.setPassword(SaltEncoder.md5SaltEncode(user.getLoginName(), user.getPassword()));
            if (user.getId() != null) {
                rlt = userService.updateById(user);
            } else {
                user.setCrTime(new Date());
                user.setLastTime(user.getCrTime());
                rlt = userService.insert(user);
            }
        }
        return callbackSuccess(rlt);
    }

    @ResponseBody
    @Permission("2001")
    @RequestMapping("/getUserList")
    public String getUserList(@RequestParam("_search") String _search) {

        System.out.println("搜索条件 =" + _search);

        Page<User> page = getPage();
        Page<User> userPage = userService.selectPageBySearch(page, StringUtil.getStrEmpty(_search));
        return jsonPage(userPage);
    }

    /**
     * excel导出列表
     *
     * @return
     */
    @Permission("2001")
    @RequestMapping(value = "downloadExcel",method = RequestMethod.POST)
    public ModelAndView downloadExcel(@RequestParam("search") String search){

        /**1.执行你的业务逻辑获取数据，使用ExcelContent生成Workbook，需要四个参数
         * id 配置ID
         * beans 配置class对应的List
         * header 导出之前,在标题前面做出一些额外的操作,比如增加文档描述等,可以为null
         * fields 指定Excel导出的字段(bean对应的字段名称),可以为null
         */
        System.out.println("search = " + search);
        Workbook workbook = null;
        String id = "user";
        List<User> list = userService.selectList(null);
        List<String> fields = Arrays.asList(userFields.split(","));
        try {
            workbook = excelContext.createExcel(id, list,null,fields);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**2.跳转到Excel下载视图*/
        ModelAndView view = new ModelAndView("springMvcExcelView");
        view.addObject(SpringMvcExcelView.EXCEL_NAME,"测试Excel下载");
        view.addObject(SpringMvcExcelView.EXCEL_WORKBOOK,workbook);
        view.addObject(SpringMvcExcelView.EXCEL_EMPTY_MESSAGE,"XXX没有相关数据可以导出");
        return view;
    }

    @ResponseBody
    @Permission("2001")
    @RequestMapping("/delUser/{userId}")
    public String delUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return Boolean.TRUE.toString();
    }

    @ResponseBody
    @Permission("2001")
    @RequestMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.selectById(userId);
    }


    /**
     * 设置头像
     */
    @Permission(action = Action.Skip)
    @RequestMapping(value = "/setAvatar", method = RequestMethod.GET)
    public String setAvatar() {
        return "/user/avatar";
    }


}
