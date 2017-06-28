package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.common.view.SpringMvcExcelView;
import com.baomidou.springwind.entity.ProductExpires;
import com.baomidou.springwind.service.IProductExpiresService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.Workbook;
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

    //excel-config.xml中配置的ID
    @Value("${productExpires.excelId}")
    private String userExcelId;

    //excel导出的字段
    @Value("${productExpires.fields}")
    private String userFields;

    /**页面跳转*/
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


    /**CRUD*/
    @ResponseBody
    @Permission("8001")
    @RequestMapping(value = "/getUserList")
    public String getUserList(@RequestParam("_search") String _search) {

        System.err.println("筛选条件 formData =" + _search);

        ProductExpires pe = null;
        if(StringUtil.isNotEmpty(_search)){
            pe = JSONObject.parseObject(_search, ProductExpires.class);
        }
        Page<ProductExpires> page = getPage();
//        Page<ProductExpires> userPage = productExpiresService.selectPageByParams(page, pe);
        Page<ProductExpires> selectPage = productExpiresService.selectPage(page, null);
        return jsonPage(selectPage);
    }

//    @ResponseBody
//    @Permission("8001")
//    @RequestMapping("/editUser")
//    public String editUser(ProductExpires pe) {
//        boolean rlt = false;
//        if (pe != null) {
//            if (pe.getId() != null) {
//                rlt = productExpiresService.updateById(pe);
//            } else {
//                pe.setCreateTime(new Date());
//                pe.setUpdateTime(pe.getCreateTime());
//                rlt = productExpiresService.insert(pe);
//            }
//        }
//        return callbackSuccess(rlt);
//    }
//
//    @ResponseBody
//    @Permission("8001")
//    @RequestMapping("/delUser/{userId}")
//    public String delUser(@PathVariable Long userId) {
//        Boolean rlt = productExpiresService.deleteById(userId);
//        return rlt.toString();
//    }
//
//
//    /**
//     * Excel导出列表
//     *
//     * @return
//     */
//    @Permission("8001")
//    @RequestMapping(value = "/downloadExcel",method = RequestMethod.POST)
//    public ModelAndView downloadExcel(){
//
//        /**1.执行你的业务逻辑获取数据，使用ExcelContent生成Workbook，需要四个参数:
//         *
//         * ①id 配置ID
//         * ②beans 配置class对应的List
//         * ③header 导出之前,在标题前面做出一些额外的操作,比如增加文档描述等,可以为null
//         * ④fields 指定Excel导出的字段(bean对应的字段名称),可以为null
//         */
//        Workbook workbook = null;
//        String id = userExcelId;
//        List<ProductExpires> list = productExpiresService.selectList(null);
//        List<String> fields = Arrays.asList(userFields.split(","));
//        try {
//            workbook = excelContext.createExcel(id, list, null, fields);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        /**2.跳转到Excel下载视图*/
//        ModelAndView view = new ModelAndView("springMvcExcelView");
//        view.addObject(SpringMvcExcelView.EXCEL_NAME, "正式名单" + DateUtil.getCurrentTime());
//        view.addObject(SpringMvcExcelView.EXCEL_WORKBOOK, workbook);
//        view.addObject(SpringMvcExcelView.EXCEL_EMPTY_MESSAGE, "正式名单 没有相关数据可以导出");
//        return view;
//    }

    @ResponseBody
    @Permission("8001")
    @RequestMapping("addTestData")
    public String addTestData() {

//        Boolean boo = productExpiresService.deleteAll();

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
            pe.setProductId("产品id" + i);
            pe.setProductName("产品" + i);
            pe.setTransAmount(0.00 + i);
            pe.setInceptionDate(DateUtil.randomDate("2017-01-01", "2017-06-01"));
            pe.setDueDate(DateUtil.randomDate("2017-01-01", "2017-06-01"));
            pe.setLimitDays(Integer.parseInt(RandomStringUtils.randomNumeric(3)));
            pe.setLimitType( Integer.parseInt(RandomStringUtils.random(1, new char[]{'1', '2', '0'})) );
            pe.setProductRate("利率"+i);
            pe.setCreateTime(new Date());
            pe.setUpdateTime(pe.getCreateTime());
            list.add(pe);
            System.err.println(pe);
        }
        Boolean b = productExpiresService.insertBatch(list);
        return b.toString();
    }

}
