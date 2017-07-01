package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.AssetsBalance;
import com.baomidou.springwind.service.IAssetsBalanceService;
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
 * 资产余额表 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-25
 */
@Controller
@RequestMapping("/statsReport/assetsBalance")
public class AssetsBalanceController extends BaseController {

    @Autowired
    private IAssetsBalanceService assetsBalanceService;

    /**
     * excel导出相关
     */
    @Value("${assetsBalance.excelName}")
    private String excelName;
    @Value("${assetsBalance.excelId}")
    private String excelId;
    @Value("${assetsBalance.fields}")
    private String excelFields;

    /**
     * 页面跳转
     */
    @Permission("8002")
    @RequestMapping("/list")
    public String list(Model model) {
        return "/statsReport/assetsBalance/list";
    }

    @Permission("8002")
    @RequestMapping("/search")
    public String search(Model model) {
        return "/statsReport/assetsBalance/search";
    }

    @Permission("8002")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        if (id != null) {
            model.addAttribute("ab", assetsBalanceService.selectById(id));
        }
        return "/statsReport/assetsBalance/edit";
    }


    /**
     * CRUD
     */
    @ResponseBody
    @Permission("8002")
    @RequestMapping(value = "/getList")
    public String getUserList(@RequestParam("_search") String _search) {

        System.err.println("筛选条件 formData =" + _search);

        Page<AssetsBalance> page = getPage();
        if (StringUtil.isNotEmpty(_search)) {
            //transAumSmall=transAum transAumBig=accountAum
            AssetsBalance ab = JSONObject.parseObject(_search, AssetsBalance.class);
            page = assetsBalanceService.selectPageByParams(page, ab);
        } else {
            page = assetsBalanceService.selectPage(page,
                    new EntityWrapper<AssetsBalance>().orderBy("aum_date", false));
        }
        return jsonPage(page);
    }

//    @ResponseBody
//    @Permission("8002")
//    @RequestMapping("/editUser")
//    public String editUser(AssetsBalance pe) {
//        boolean rlt = false;
//        if (pe != null) {
//            if (pe.getId() != null) {
//                rlt = assetsBalanceService.updateById(pe);
//            } else {
//                pe.setCreateTime(new Date());
//                pe.setUpdateTime(pe.getCreateTime());
//                rlt = assetsBalanceService.insert(pe);
//            }
//        }
//        return callbackSuccess(rlt);
//    }
//
//    @ResponseBody
//    @Permission("8002")
//    @RequestMapping("/delUser/{userId}")
//    public String delUser(@PathVariable Long userId) {
//        Boolean rlt = assetsBalanceService.deleteById(userId);
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
        List<AssetsBalance> beans = assetsBalanceService.selectList(null);
        return super.exportExcel(excelId, beans, null, fields, excelName);
    }

    @ResponseBody
    @Permission("8002")
    @RequestMapping("addTestData")
    public String addTestData() {

        assetsBalanceService.deleteAll();

        ArrayList<AssetsBalance> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            AssetsBalance ab = new AssetsBalance();
            /*
id,mobile_no,member_no,user_name,user_type,register_time,
dingqi_aum,huoqi_aum,huobaoding_aum,secondmarket_aum,
trans_aum,account_aum,
aum_date,is_performance_pool,create_time,update_time,
            */
            ab.setMobileNo(RandomStringUtils.randomNumeric(11));
            ab.setMemberNo(RandomStringUtils.randomAlphanumeric(10));
            ab.setUserName(RandomStringUtils.randomAlphabetic(5));
            ab.setUserType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'2','3', '1'})));
            ab.setRegisterTime(DateUtil.randomDate("2017-01-01", "2017-07-01"));
            ab.setDingqiAum(Double.parseDouble(RandomStringUtils.randomNumeric(4)));
            ab.setHuoqiAum(Double.parseDouble(RandomStringUtils.randomNumeric(4)));
            ab.setHuobaodingAum(Double.parseDouble(RandomStringUtils.randomNumeric(4)));
            ab.setSecondmarketAum(Double.parseDouble(RandomStringUtils.randomNumeric(4)));
            ab.setTransAum(Double.parseDouble(RandomStringUtils.randomNumeric(5)));
            ab.setAccountAum(Double.parseDouble(RandomStringUtils.randomNumeric(5)));
            ab.setAumDate(DateUtil.randomDate("2017-01-01", "2017-07-01"));
            ab.setIsPerformancePool(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1'})));
            ab.setCreateTime(new Date());
            ab.setUpdateTime(ab.getCreateTime());
            list.add(ab);
            System.err.println(ab);
        }
        Boolean b = assetsBalanceService.insertBatch(list);
        return b.toString();
    }
}
