package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.SalesDetails;
import com.baomidou.springwind.entity.vo.SalesDetailsVO;
import com.baomidou.springwind.excel.config.ExcelDefinition;
import com.baomidou.springwind.excel.parsing.ExcelHeader;
import com.baomidou.springwind.service.ISalesDetailsService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 销售明细 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-15
 */
@Controller
@RequestMapping("/pfmReport/salesDetails")
public class SalesDetailsController extends BaseController {

    @Autowired
    private ISalesDetailsService salesDetailsService;

    /**
     * excel导出相关
     */
    @Value("${salesDetails.excelName}")
    private String excelName;
    @Value("${salesDetails.excelId}")
    private String excelId;
    @Value("${salesDetails.fields}")
    private String excelFields;

    /**
     * 页面跳转
     */
    @Permission("6001")
    @RequestMapping("/list")
    public String list() {
        return "/pfmReport/salesDetails/list";
    }

    @Permission("6001")
    @RequestMapping("/search")
    public String search() {
        return "/pfmReport/salesDetails/search";
    }

    @Permission("6001")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        if (id != null) {
            model.addAttribute("pojo", salesDetailsService.selectById(id));
        }
        return "/pfmReport/salesDetails/edit";
    }

    /**
     * CRUD
     */
    @ResponseBody
    @Permission("6001")
    @RequestMapping(value = "/getList")
    public String getUserList(@RequestParam("_search") String _search) {

        System.err.println("筛选条件 formData =" + _search);

        Page<SalesDetails> page = getPage();
        if (StringUtil.isNotEmpty(_search)) {
            SalesDetailsVO sdVO = JSONObject.parseObject(_search, SalesDetailsVO.class);
            page = salesDetailsService.selectPageByParams(page, sdVO);
        } else {
            page = salesDetailsService.selectPage(page,
                    new EntityWrapper<SalesDetails>().orderBy("trans_time", false));
        }
        return jsonPage(page);
    }

    @ResponseBody
    @Permission("6001")
    @RequestMapping(value = "/editOne")
    public String editSalesDetail(SalesDetails salesDetails) {
        boolean rlt = false;
        if (salesDetails != null) {
            if (salesDetails.getId() != null) {
                rlt = salesDetailsService.updateById(salesDetails);
            } else {
                salesDetails.setCreateTime(new Date());
                salesDetails.setUpdateTime(salesDetails.getCreateTime());
                rlt = salesDetailsService.insert(salesDetails);
            }
        }
        return callbackSuccess(rlt);
    }


    @ResponseBody
    @Permission("6001")
    @RequestMapping("/delOne/{id}")
    public String delUser(@PathVariable Long id) {
        Boolean rlt = salesDetailsService.deleteById(id);
        return rlt.toString();
    }

    /**
     * Excel导出列表
     *
     * @return
     */
    @Permission("6001")
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.POST)
    public ModelAndView downloadExcel(@RequestParam("_search") String _search) throws UnsupportedEncodingException {

        String search = new String(_search.getBytes("iso-8859-1"), "utf-8");

        System.err.println("excel导出的条件 search =" + search);

        List<SalesDetails> beans = null;

        EntityWrapper<SalesDetails> ew = new EntityWrapper<>();

        if (StringUtil.isNotEmpty(search)) {
            SalesDetailsVO sdVO = JSONObject.parseObject(search, SalesDetailsVO.class);

            if (StringUtil.isNotEmpty(sdVO.getUserName())) {
                ew.like("user_name", sdVO.getUserName());
            }
            if (StringUtil.isNotEmpty(sdVO.getMobileNo())) {
                ew.eq("mobile_no", sdVO.getMobileNo());
            }
            if (sdVO.getUserType() != null) {
                ew.eq("user_type", sdVO.getUserType());
            }

            if (sdVO.getTransTimeStart() != null) {
                ew.gt("trans_time", sdVO.getTransTimeStart());
            }
            if (sdVO.getTransTimeEnd() != null) {
                ew.lt("trans_time", sdVO.getTransTimeEnd());
            }
            if (sdVO.getProductType() != null) {
                ew.eq("product_type", sdVO.getProductType());
            }

            if (sdVO.getTransAmountSmall() != null) {
                ew.gt("trans_amount", sdVO.getTransAmountSmall());
            }
            if (sdVO.getTransAmountBig() != null) {
                ew.lt("trans_amount", sdVO.getTransAmountBig());
            }
            if (sdVO.getLimitType() != null) {
                ew.eq("limit_type", sdVO.getLimitType());
            }

            if (StringUtil.isNotEmpty(sdVO.getAdvisorName())) {
                ew.eq("advisor_name", sdVO.getAdvisorName());
            }
            if (sdVO.getIsPerformancePool() != null) {
                ew.eq("is_performance_pool", sdVO.getIsPerformancePool());
            }
            if (StringUtil.isNotEmpty(sdVO.getUserMark())) {
                ew.eq("user_mark", sdVO.getUserMark());
            }
            beans = salesDetailsService.selectList(ew);
        } else {
            beans = salesDetailsService.selectList(null);
        }

        List<String> fields = Arrays.asList(excelFields.split(","));

        //如果数据量大于30w，则加入头信息，提示数据最多30w
        /*if (beans != null && beans.size() > 300000) {
            List<SalesDetails> subList = beans.subList(0, 300000);
            ExcelHeader header = new ExcelHeader() {
                @Override
                public void buildHeader(Sheet sheet, ExcelDefinition excelDefinition, List<?> beans) {
                    Row row1 = sheet.createRow(0);
                    Cell cell = row1.createCell(0);
                    cell.setCellValue("共导出【"+subList.size()+"】条数据");
                    Row row2 = sheet.createRow(1);
                    Cell cell2 = row2.createCell(0);
                    cell2.setCellValue("最多导出数据量为30万！");
                }
            };
            return super.exportExcel(excelId, subList, header, fields, excelName);
        }*/

        return super.exportExcel(excelId, beans, null, fields, excelName);
    }

    @ResponseBody
    @Permission("6001")
    @RequestMapping("addTestData")
    public String addTestData() {

        salesDetailsService.deleteAll();

        ArrayList<SalesDetails> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            SalesDetails sd = new SalesDetails();

            sd.setMobileNo(RandomStringUtils.randomNumeric(11));
            sd.setMemberNo(RandomStringUtils.randomAlphanumeric(10));
            sd.setUserName(RandomStringUtils.randomAlphabetic(5));
            // advisor --> serialNo
            sd.setAdvisorId(RandomStringUtils.randomNumeric(4));
            sd.setAdvisorName(RandomStringUtils.randomAlphabetic(5));
            sd.setProductId(RandomStringUtils.randomAlphanumeric(4));

            sd.setProductName(RandomStringUtils.randomAlphanumeric(6));
            sd.setProductType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'1', '2', '3', '4'})));
            sd.setProductRate(RandomStringUtils.randomAlphanumeric(2));
            sd.setTransAmount((Double.valueOf(RandomStringUtils.randomNumeric(6))));
            sd.setTransTime(DateUtil.randomDate("2017-01-01", "2017-07-01"));
            sd.setInceptionDate(DateUtil.randomDate("2017-06-01", "2017-10-01"));
            sd.setDueDate(DateUtil.randomDate("2017-10-01", "2018-01-01"));
            sd.setLimitDays(Integer.parseInt(RandomStringUtils.randomNumeric(3)));
            if (i % 3 == 0) {
                sd.setLimitType(0);
            } else if (i % 3 == 1) {
                sd.setLimitType(6);
            } else {
                sd.setLimitType(12);
            }
            sd.setUserType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'1', '2', '3'})));
            sd.setRegisterTime(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            sd.setReportDate(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            sd.setIsVipuser(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1'})));
            sd.setVipDate(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            sd.setIsPerformancePool(Integer.parseInt(RandomStringUtils.random(1, new char[]{'0', '1'})));
            sd.setUserMark(RandomStringUtils.randomAlphanumeric(6));
            sd.setCreateTime(new Date());
            sd.setUpdateTime(sd.getCreateTime());
            list.add(sd);
            System.err.println(sd);
        }
        Boolean b = salesDetailsService.insertBatch(list);
        return b.toString();
    }


}
