package com.baomidou.springwind.controller;

import com.baomidou.framework.upload.UploadFile;
import com.baomidou.framework.upload.UploadMsg;
import com.baomidou.framework.upload.UploadMultipartRequest;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.utils.DateUtil;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.common.view.SpringMvcExcelView;
import com.baomidou.springwind.entity.AssignReportImportUser;
import com.baomidou.springwind.entity.Excel;
import com.baomidou.springwind.excel.result.ExcelImportResult;
import com.baomidou.springwind.service.IAssignReportImportUserService;
import com.baomidou.springwind.service.IExcelService;
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

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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
public class AssignReportImportUserController extends BaseController{

    @Autowired
    private IAssignReportImportUserService assignReportImportUserService;

    @Autowired
    private IExcelService excelService;

    //excel-config.xml中配置的ID
    @Value("${assignReportImportUser.excelId}")
    private String userExcelId;

    //excel导出的字段
    @Value("${assignReportImportUser.fields}")
    private String userFields;

    //限制最大上传大小--30M
    private final static int MAX_POST_SIZE = 30 * 1024 * 1024;

    /**页面跳转*/
    @Permission("5003")
    @RequestMapping("/list")
    public String list() {
        return "/clientList/importUser/list";
    }

    @Permission("5003")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        if (id != null) {
            model.addAttribute("user", assignReportImportUserService.selectById(id));
        }
        return "/clientList/importUser/edit";
    }


    /**CRUD*/
    @ResponseBody
    @Permission("5003")
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

        Page<AssignReportImportUser> page = getPage();
        Page<AssignReportImportUser> userPage = assignReportImportUserService.selectPage(page,
                new EntityWrapper<AssignReportImportUser>().allEq(params));
        return jsonPage(userPage);
    }


    @ResponseBody
    @Permission("5003")
    @RequestMapping("/editUser")
    public String editUser(AssignReportImportUser user) {
        boolean rlt = false;
        if (user != null) {
            if (user.getId() != null) {
                rlt = assignReportImportUserService.updateById(user);
            } else {
                user.setCreateTime(new Date());
                user.setUpdateTime(user.getCreateTime());
                rlt = assignReportImportUserService.insert(user);
            }
        }
        return callbackSuccess(rlt);
    }


    /**
     * Excel导入
     */

    @ResponseBody
    @Permission("5003")
    @RequestMapping(value = "/uploadExcel", method = RequestMethod.POST)
    public UploadMsg uploadExcelFile(){
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
                        System.err.println("上传文件地址：" + msg.getUrl());
                        System.err.println("UploadFile cf：" + cf.toString());
                    }
                    msg.setMsg(cf.getUploadCode().desc());
                    /**读取Excel内容，进行写表*/
                    Excel excel = new Excel();
                    excel.setExcelName(cf.getOriginalFileName());
                    excel.setExcelRealName(cf.getFilesystemName());
                    excel.setExcelRealPath(cf.getFileUrl());
                    excel.setUid(0L);
                    excel.setCreateTime(new Date());
                    excelService.insert(excel);

                    FileInputStream excelStream = new FileInputStream(cf.getFileUrl());
                    ExcelImportResult readExcel = excelContext.readExcel(userExcelId, excelStream);
                    List<AssignReportImportUser> listBean = readExcel.getListBean();

                    assignReportImportUserService.insertBatch(listBean);
                }
            }
        } catch (IOException e) {
            logger.error("uploadFile error. ", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("msg = " + toJson(msg));
        return msg;

    }


    /**
     * Excel导出列表
     *
     * @return
     */
    @Permission("5003")
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
        List<AssignReportImportUser> list = assignReportImportUserService.selectList(null);
        List<String> fields = Arrays.asList(userFields.split(","));
        try {
            workbook = excelContext.createExcel(id, list, null, fields);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**2.跳转到Excel下载视图*/
        ModelAndView view = new ModelAndView("springMvcExcelView");
        view.addObject(SpringMvcExcelView.EXCEL_NAME, "分配-上报名单" + DateUtil.getCurrentTime());
        view.addObject(SpringMvcExcelView.EXCEL_WORKBOOK, workbook);
        view.addObject(SpringMvcExcelView.EXCEL_EMPTY_MESSAGE, "分配-上报名单 没有相关数据可以导出");
        return view;
    }

    @ResponseBody
    @Permission("5003")
    @RequestMapping("/delUser/{userId}")
    public String delUser(@PathVariable Long userId) {
        Boolean rlt = assignReportImportUserService.deleteById(userId);
        return rlt.toString();
    }


    @ResponseBody
    @Permission("5003")
    @RequestMapping("addTestData")
    public String addTestData() {
        ArrayList<AssignReportImportUser> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            AssignReportImportUser u = new AssignReportImportUser();
            u.setMobileNo(RandomStringUtils.randomNumeric(11));
            u.setUserName(RandomStringUtils.randomAlphabetic(5));
            u.setUserType(Integer.parseInt(RandomStringUtils.random(1, new char[]{'1', '2', '3'})));
            u.setReportDate(DateUtil.randomDate("2017-01-01", "2017-05-01"));
            u.setAdvisorName(RandomStringUtils.randomAlphabetic(6));
            u.setUserMark(RandomStringUtils.randomAlphanumeric(6));
            u.setCreateTime(new Date());
            u.setUpdateTime(u.getCreateTime());
            list.add(u);
            System.err.println(u);
        }
        Boolean b = assignReportImportUserService.insertBatch(list);
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
