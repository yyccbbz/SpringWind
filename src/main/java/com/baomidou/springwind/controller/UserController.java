package com.baomidou.springwind.controller;

import com.baomidou.framework.common.util.DateUtil;
import com.baomidou.framework.upload.UploadFile;
import com.baomidou.framework.upload.UploadMsg;
import com.baomidou.framework.upload.UploadMultipartRequest;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.common.encrypt.SaltEncoder;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.result.AjaxResult;
import com.baomidou.springwind.common.utils.StringUtil;
import com.baomidou.springwind.common.utils.UploadUtil;
import com.baomidou.springwind.common.view.SpringMvcExcelView;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.excel.exception.ExcelException;
import com.baomidou.springwind.excel.result.ExcelImportResult;
import com.baomidou.springwind.service.IRoleService;
import com.baomidou.springwind.service.IUserService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 用户管理相关操作
 *
 *
 */
@Controller
@RequestMapping("/perm/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    //excel导出的字段
    @Value("${user.fields}")
    private String userFields;

    //上传文件的类型
    private static final HashMap<String, String> TypeMap = new HashMap<String, String>();
    static {
        /*TypeMap.put("image", "gif,jpg,jpeg,png,bmp");
        TypeMap.put("flash", "swf,flv");
        TypeMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        TypeMap.put("file", "doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,dwg,pdf");*/
        TypeMap.put("file", "xls,xlsx");
    }

    //上传文件的最大值 30m
    private final static int MAX_POST_SIZE = 30 * 1024 * 1024;

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
    @RequestMapping(value = "/downloadExcel",method = RequestMethod.POST)
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

    /**
     * Excel导入
     *
     * @return
     */
    @ResponseBody
    @Permission("2001")
    @RequestMapping(value = "/uploadExcel", method = RequestMethod.POST)
    public UploadMsg uploadExcel() {

        UploadMsg msg = new UploadMsg();
        try {
            UploadMultipartRequest umr = new UploadMultipartRequest(request, getSaveDir(), MAX_POST_SIZE);
            umr.setFileHeaderExts("504b03.xlsx");
            umr.upload();
            Enumeration<?> files = umr.getFileNames();
            while ( files.hasMoreElements() ) {
                String name = (String) files.nextElement();
                UploadFile cf = umr.getUploadFile(name);
                if ( cf != null ) {
                    /**
                     * 上传成功
                     */
                    if ( cf.isSuccess() ) {
                        msg.setSuccess(true);
                        msg.setUrl(cf.getFileUrl());
                        msg.setSize(cf.getSize());
                        System.err.println("上传文件地址：" + msg.getUrl());
                    }
                    msg.setMsg(cf.getUploadCode().desc());
                }
            }
        } catch ( IOException e ) {
            logger.error("uploadFile error. ", e);
        }
        System.out.println("msg = " + toJson(msg));
        return msg;
    }


    /**
     * <p>
     * 上传文件存放目录
     * </p>
     */
    private static String getSaveDir() {
        StringBuffer filePath = new StringBuffer(System.getProperty("user.dir"));
        filePath.append(File.separator);
        filePath.append(DateUtil.format(new Date(), "yyyy"));
        filePath.append(File.separator);
        File file = new File(filePath.toString());
        if ( !file.exists() ) {
            file.mkdirs();
        }
        return file.getPath();
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
