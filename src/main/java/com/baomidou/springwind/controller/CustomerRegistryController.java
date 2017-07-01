package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.CustomerRegistry;
import com.baomidou.springwind.service.ICustomerRegistryService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 客户注册情况 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-25
 */
@Controller
@RequestMapping("/statsReport/customerRegistry")
public class CustomerRegistryController extends BaseController {
    @Autowired
    private ICustomerRegistryService customerRegistryService;

    /**
     * excel导出相关
     */
    @Value("${customerRegistry.excelName}")
    private String excelName;
    @Value("${customerRegistry.excelId}")
    private String excelId;
    @Value("${customerRegistry.fields}")
    private String excelFields;

    /**
     * 页面跳转
     */
    @Permission("8002")
    @RequestMapping("/list")
    public String list(Model model) {
        return "/statsReport/customerRegistry/list";
    }

    @Permission("8002")
    @RequestMapping("/search")
    public String search(Model model) {
        return "/statsReport/customerRegistry/search";
    }

    @Permission("8002")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        if (id != null) {
            model.addAttribute("cr", customerRegistryService.selectById(id));
        }
        return "/statsReport/customerRegistry/edit";
    }


    /**
     * CRUD
     */
    @ResponseBody
    @Permission("8002")
    @RequestMapping(value = "/getList")
    public String getUserList(@RequestParam("_search") String _search) {

        System.err.println("筛选条件 formData =" + _search);

        Page<CustomerRegistry> page = getPage();
        if (StringUtil.isNotEmpty(_search)) {
            //transAumSmall=transAum transAumBig=accountAum
            CustomerRegistry cr = JSONObject.parseObject(_search, CustomerRegistry.class);
            page = customerRegistryService.selectPageByParams(page, cr);
        } else {
            page = customerRegistryService.selectPage(page,
                    new EntityWrapper<CustomerRegistry>().orderBy("register_time", false));
        }
        return jsonPage(page);
    }

//    @ResponseBody
//    @Permission("8002")
//    @RequestMapping("/editUser")
//    public String editUser(CustomerRegistry pe) {
//        boolean rlt = false;
//        if (pe != null) {
//            if (pe.getId() != null) {
//                rlt = customerRegistryService.updateById(pe);
//            } else {
//                pe.setCreateTime(new Date());
//                pe.setUpdateTime(pe.getCreateTime());
//                rlt = customerRegistryService.insert(pe);
//            }
//        }
//        return callbackSuccess(rlt);
//    }
//
//    @ResponseBody
//    @Permission("8002")
//    @RequestMapping("/delUser/{userId}")
//    public String delUser(@PathVariable Long userId) {
//        Boolean rlt = customerRegistryService.deleteById(userId);
//        return rlt.toString();
//    }


    /**
     * Excel导出列表
     *
     * @return
     */
    @Permission("8002")
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.POST)
    public ModelAndView downloadExcel() {

        List<String> fields = Arrays.asList(excelFields.split(","));
        List<CustomerRegistry> beans = customerRegistryService.selectList(null);
        return super.exportExcel(excelId, beans, null, fields, excelName);
    }

    @ResponseBody
    @Permission("8002")
    @RequestMapping("addTestData")
    public String addTestData() {

        customerRegistryService.deleteAll();

        ArrayList<CustomerRegistry> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            CustomerRegistry ab = new CustomerRegistry();
            /*
#id,mobileNo,memberNo,userName,isRegister,registerTime,tMobileNo,tMemberNo,tUserName,createTime,upTime,
            */
            ab.setMobileNo(RandomStringUtils.randomNumeric(11));
            ab.setMemberNo(RandomStringUtils.randomAlphanumeric(10));
            ab.setUserName(RandomStringUtils.randomAlphabetic(5));
            ab.setIsRegister(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1'})));
            ab.setRegisterTime(DateUtil.randomDate("2017-01-01", "2017-07-01"));
            ab.settMobileNo(RandomStringUtils.randomNumeric(11));
            ab.settMemberNo(RandomStringUtils.randomAlphanumeric(10));
            ab.settUserName(RandomStringUtils.randomAlphabetic(5));
            ab.setCreateTime(new Date());
            ab.setUpdateTime(ab.getCreateTime());
            list.add(ab);
            System.err.println(ab);
        }
        Boolean b = customerRegistryService.insertBatch(list);
        return b.toString();
    }
}
