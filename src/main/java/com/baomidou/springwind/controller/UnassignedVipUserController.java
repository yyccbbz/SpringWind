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

    //excel-config.xml中配置的ID
    @Value("${unassignedVipUser.excelId}")
    private String userExcelId;

    //excel导出的字段
    @Value("${unassignedVipUser.fields}")
    private String userFields;

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
        List<UnassignedVipUser> list = unassignedVipUserService.selectList(null);
        List<String> fields = Arrays.asList(userFields.split(","));
        try {
            workbook = excelContext.createExcel(id, list, null, fields);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**2.跳转到Excel下载视图*/
        ModelAndView view = new ModelAndView("springMvcExcelView");
        view.addObject(SpringMvcExcelView.EXCEL_NAME, "未分配的vip名单" + DateUtil.getCurrentTime());
        view.addObject(SpringMvcExcelView.EXCEL_WORKBOOK, workbook);
        view.addObject(SpringMvcExcelView.EXCEL_EMPTY_MESSAGE, "未分配的vip名单 没有相关数据可以导出");
        return view;
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

    /**
     * form表单提交 Date类型数据绑定
     * <功能详细描述>
     * @param binder
     * @see [类、类#方法、类#成员]
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


}
