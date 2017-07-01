package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.ProductExpires;
import com.baomidou.springwind.service.IProductExpiresService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 产品到期表 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-25
 */
@Controller
@RequestMapping("/statsReport/productExpires")
public class ProductExpiresController extends BaseController {

    @Autowired
    private IProductExpiresService productExpiresService;

    /**
     * excel导出相关
     */
    @Value("${productExpires.excelName}")
    private String excelName;
    @Value("${productExpires.excelId}")
    private String excelId;
    @Value("${productExpires.fields}")
    private String excelFields;

    /**
     * 页面跳转
     */
    @Permission("8001")
    @RequestMapping("/list")
    public String list(Model model) {
        return "/statsReport/productExpires/list";
    }

    @Permission("8001")
    @RequestMapping("/search")
    public String search(Model model) {
        return "/statsReport/productExpires/search";
    }

    @Permission("8001")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        if (id != null) {
            model.addAttribute("pe", productExpiresService.selectById(id));
        }
        return "/statsReport/productExpires/edit";
    }


    /**
     * CRUD
     */
    @ResponseBody
    @Permission("8001")
    @RequestMapping(value = "/getUserList")
    public String getUserList(@RequestParam("_search") String _search) {

        System.err.println("筛选条件 formData =" + _search);

        Page<ProductExpires> page = getPage();
        if (StringUtil.isNotEmpty(_search)) {
            ProductExpires pe = JSONObject.parseObject(_search, ProductExpires.class);
            page = productExpiresService.selectPageByParams(page, pe);
        } else {
            page = productExpiresService.selectPage(page,
                    new EntityWrapper<ProductExpires>().orderBy("due_date", false));
        }
        return jsonPage(page);
    }

    @ResponseBody
    @Permission("8001")
    @RequestMapping("/editOne")
    public String editUser(ProductExpires pe) {
        boolean rlt = false;
        if (pe != null) {
            if (pe.getId() != null) {
                rlt = productExpiresService.updateById(pe);
            } else {
                pe.setCreateTime(new Date());
                pe.setUpdateTime(pe.getCreateTime());
                rlt = productExpiresService.insert(pe);
            }
        }
        return callbackSuccess(rlt);
    }


    @ResponseBody
    @Permission("8001")
    @RequestMapping("/delOne/{peId}")
    public String delUser(@PathVariable Long peId) {
        Boolean rlt = productExpiresService.deleteById(peId);
        return rlt.toString();
    }


    /**
     * Excel导出列表
     *
     * @return
     */
    @Permission("8001")
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.POST)
    public ModelAndView downloadExcel() {

        List<String> fields = Arrays.asList(excelFields.split(","));
        List<ProductExpires> beans = productExpiresService.selectList(null);
        return exportExcel(excelId, beans, null, fields, excelName);
    }

    @ResponseBody
    @Permission("8001")
    @RequestMapping("addTestData")
    public String addTestData() {

        productExpiresService.deleteAll();

        ArrayList<ProductExpires> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            ProductExpires pe = new ProductExpires();
            /*
                id,mobileNo,memberNo,userName,advisorId,advisorName,isPerformancePool,productId,productName,
                transAmount,inceptionDate,dueDate,limitDays,limitType,productRate,createTime,updateTime
            */
            pe.setMobileNo(RandomStringUtils.randomNumeric(11));
            pe.setMemberNo(RandomStringUtils.randomAlphanumeric(10));
            pe.setUserName(RandomStringUtils.randomAlphabetic(5));
            pe.setAdvisorId(Integer.parseInt(RandomStringUtils.randomNumeric(4)));
            pe.setAdvisorName(RandomStringUtils.randomAlphabetic(6));
            pe.setIsPerformancePool(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1'})));
            pe.setProductId(RandomStringUtils.randomNumeric(5));
            pe.setProductName(RandomStringUtils.random(5, "abcd"));
            pe.setTransAmount(Double.parseDouble(RandomStringUtils.randomNumeric(5)));
            pe.setInceptionDate(DateUtil.randomDate("2017-01-01", "2017-06-01"));
            pe.setDueDate(DateUtil.randomDate("2017-01-01", "2017-06-01"));
            pe.setLimitDays(Integer.parseInt(RandomStringUtils.randomNumeric(2)));

            if (i % 3 == 0) {
                pe.setLimitType(0);
            } else if (i % 3 == 1) {
                pe.setLimitType(6);
            } else {
                pe.setLimitType(12);
            }

            pe.setProductRate("" + i);
            pe.setCreateTime(new Date());
            pe.setUpdateTime(pe.getCreateTime());
            list.add(pe);
            System.err.println(pe);
        }
        Boolean b = productExpiresService.insertBatch(list);
        return b.toString();
    }

}
