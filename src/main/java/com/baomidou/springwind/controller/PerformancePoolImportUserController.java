package com.baomidou.springwind.controller;

import com.baomidou.framework.upload.UploadFile;
import com.baomidou.framework.upload.UploadMsg;
import com.baomidou.framework.upload.UploadMultipartRequest;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.entity.Excel;
import com.baomidou.springwind.entity.PerformancePoolImportUser;
import com.baomidou.springwind.excel.result.ExcelImportResult;
import com.baomidou.springwind.service.IExcelService;
import com.baomidou.springwind.service.IPerformancePoolImportUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * <p>
 * 业绩池名单导入 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-09
 */
@Controller
@RequestMapping("/clientList/pfmPoolImportUser")
public class PerformancePoolImportUserController extends BaseController {

    @Autowired
    private IPerformancePoolImportUserService performancePoolImportUserService;
    @Autowired
    private IExcelService excelService;

    /**
     * excel导出相关
     */
    @Value("${performancePoolImportUser.excelName}")
    private String excelName;
    @Value("${performancePoolImportUser.excelId}")
    private String excelId;
    @Value("${performancePoolImportUser.fields}")
    private String excelFields;

    //限制最大上传大小--30M
    private final static int MAX_POST_SIZE = 30 * 1024 * 1024;

    /**
     * 页面跳转
     */
    @Permission("5004")
    @RequestMapping("/list")
    public String list() {
        return "/clientList/pfmPoolImportUser/list";
    }


    @Permission("5004")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        if (id != null) {
            model.addAttribute("user", performancePoolImportUserService.selectById(id));
        }
        return "/clientList/pfmPoolImportUser/edit";
    }


    /**
     * CRUD
     */
    @ResponseBody
    @Permission("5004")
    @RequestMapping(value = "/getUserList")
    public String getUserList(@RequestParam("_userName") String _userName, @RequestParam("_mobileNo") String _mobileNo) {

        System.err.println("筛选条件：客户姓名_userName = " + _userName + "，手机号码_mobileNo = " + _mobileNo);

        EntityWrapper<PerformancePoolImportUser> ew = new EntityWrapper<>();
        if (StringUtil.isNotEmpty(_userName)) {
            ew.like("user_name", _userName);
        }
        if (StringUtil.isNotEmpty(_mobileNo)) {
            ew.like("mobile_no", _mobileNo);
        }
        Page<PerformancePoolImportUser> page = getPage();
        Page<PerformancePoolImportUser> userPage = performancePoolImportUserService.selectPage(page, ew);
        return jsonPage(userPage);
    }

    @ResponseBody
    @Permission("5004")
    @RequestMapping("/editUser")
    public String editUser(PerformancePoolImportUser user) {
        boolean rlt = false;
        if (user != null) {
            if (user.getId() != null) {
                rlt = performancePoolImportUserService.updateById(user);
            } else {
                user.setCreateTime(new Date());
                user.setUpdateTime(user.getCreateTime());
                rlt = performancePoolImportUserService.insert(user);
            }
        }
        return callbackSuccess(rlt);
    }

    @ResponseBody
    @Permission("5004")
    @RequestMapping("/delUser/{userId}")
    public String delUser(@PathVariable Long userId) {
        Boolean rlt = performancePoolImportUserService.deleteById(userId);
        return rlt.toString();
    }

    /**
     * Excel导入
     */
    @ResponseBody
    @Permission("5004")
    @RequestMapping(value = "/uploadExcel", method = RequestMethod.POST)
    public String uploadExcel() {

        performancePoolImportUserService.deleteAll();

        UploadMsg msg = new UploadMsg();
        try {
            UploadMultipartRequest umr = new UploadMultipartRequest(request, getSaveDir(), MAX_POST_SIZE);
            umr.setFileHeaderExts("504b03.xlsx");
            umr.upload();
            Enumeration<?> files = umr.getFileNames();
            while (files.hasMoreElements()) {
                String name = (String) files.nextElement();
                UploadFile cf = umr.getUploadFile(name);
                if (cf != null) {
                    /**
                     * 上传成功
                     */
                    if (cf.isSuccess()) {
                        msg.setSuccess(true);
                        msg.setUrl(cf.getFileUrl());
                        msg.setSize(cf.getSize());
                    }
                    msg.setMsg(cf.getUploadCode().desc());
                    /**读取Excel内容，进行写表*/
                    Excel excel = new Excel();
                    excel.setExcelName(cf.getOriginalFileName());
                    excel.setExcelRealName(cf.getFilesystemName());
                    excel.setExcelRealPath(cf.getFileUrl());
                    excel.setUid(getCurrentUserId());
                    excel.setCreateTime(new Date());
                    excelService.insert(excel);

                    FileInputStream excelStream = new FileInputStream(cf.getFileUrl());
                    ExcelImportResult readExcel = excelContext.readExcel(excelId, excelStream);
                    List<PerformancePoolImportUser> listBean = readExcel.getListBean();

//                    performancePoolImportUserService.insertBatch(listBean,2000);
                    performancePoolImportUserService.batchInsert(listBean);

                }
            }
        } catch (IOException e) {
            logger.error("uploadFile error. ", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("msg = " + toJson(msg));
        return toJson(msg);
    }

    /**
     * Excel导出列表
     *
     * @return
     */
    @Permission("5004")
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.POST)
    public ModelAndView downloadExcel(@RequestParam("_userName") String _userName,
                                      @RequestParam("_mobileNo") String _mobileNo) throws UnsupportedEncodingException {
        EntityWrapper<PerformancePoolImportUser> ew = new EntityWrapper<>();
        if (StringUtil.isNotEmpty(_userName)) {
            String userName = new String(_userName.getBytes("iso-8859-1"), "utf-8");
            ew.like("user_name", userName);
        }
        if (StringUtil.isNotEmpty(_mobileNo)) {
            String mobileNo = new String(_mobileNo.getBytes("iso-8859-1"), "utf-8");
            ew.like("mobile_no", mobileNo);
        }
        List<String> fields = Arrays.asList(excelFields.split(","));
        List<PerformancePoolImportUser> beans = performancePoolImportUserService.selectList(ew);
        return super.exportExcel(excelId, beans, null, fields, excelName);
    }

    @ResponseBody
    @Permission("5004")
    @RequestMapping("addTestData")
    public String addTestData() {
        ArrayList<PerformancePoolImportUser> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            PerformancePoolImportUser u = new PerformancePoolImportUser();
            u.setMobileNo(RandomStringUtils.randomNumeric(11));
            u.setUserName(RandomStringUtils.randomAlphabetic(5));
            u.setPfmPoolDate(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            u.setAdvisorName(RandomStringUtils.randomAlphabetic(6));
            u.setUserMark(RandomStringUtils.randomAlphanumeric(6));
            u.setCreateTime(new Date());
            u.setUpdateTime(u.getCreateTime());
            list.add(u);
            System.err.println(u);
        }
        Boolean b = performancePoolImportUserService.insertBatch(list);
        return b.toString();
    }

}
