package com.baomidou.springwind.controller;

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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    //上传文件的最大值
    public static long fileSize = 30 * 1024 * 1024;

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

    /**
     * Excel导入
     *
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public AjaxResult importExcel(@RequestParam("fileExcel") CommonsMultipartFile file, HttpServletRequest request) {

//        LOGGER.debug("该上传excel文件的原文件名是 :" + file.getOriginalFilename());

        AjaxResult result = AjaxResult.success("导入数据成功...");

        if (!file.isEmpty()) {

            //判断请求类型是否为文件上传类型
            if (!ServletFileUpload.isMultipartContent(request)) {
                result.setCode(AjaxResult.CODE_FAILURE);
                result.setMsg("该请求上传文件失败...");
                return result;
            }

            //当文件超过设置的大小时，则不运行上传
            if (file.getSize() > fileSize) {
                result.setCode(AjaxResult.CODE_FAILURE);
                result.setMsg("该上传文件大小超限制...");
                return result;
            }

            //获取文件名后缀
            String OriginalFilename = file.getOriginalFilename();
            String fileSuffix = OriginalFilename.substring(OriginalFilename.lastIndexOf(".") + 1).toLowerCase();
//            LOGGER.debug("该上传文件的后缀名为 :" + fileSuffix);

            //判断该类型的文件是否在允许上传的文件类型内
            if (!Arrays.asList(TypeMap.get("file").split(",")).contains(fileSuffix)) {
                result.setCode(AjaxResult.CODE_FAILURE);
                result.setMsg("请检查上传文件的格式...");
                return result;
            }

            try {
                /*// 获取Excel对象
                ClExcel excel = ExcelHandlerUtil.getExcelFile(file, request);
                //当前上传用户的id
                excel.setUserId(88888888L);
                excel.setCtime(new Date());
                result.setObj(excel);

                // 读取excel文件
                String excelType = "user";
                ExcelImportResult readExcel = excelContext.readExcel(excelType, file.getInputStream());
                List<ClExtUserBak> listBean = readExcel.getListBean();
                System.out.println("listBean = " + listBean);
                this.clExtUserBakService.importExcelData(listBean, excel);

                // 存储excel文件
                UploadUtil.copy(file, excel.getExcelRealPath(), excel.getExcelRealName());*/
            } catch (Exception e) {
                result.setCode(AjaxResult.CODE_FAILURE);
                if (e instanceof ExcelException) {
                    result.setMsg(e.getMessage());
                } else {
                    if (e instanceof InvalidFormatException) {
                        result.setMsg("错误的文件格式...");
                    } else {
                        result.setMsg(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        } else {
            //上传文件为空，或者当前登录用户不是投资顾问，则无权限上传
            result.setCode(AjaxResult.CODE_DENIED);
            result.setMsg("无法上传，请检查该上传文件或您的登录账户权限！");
        }

        System.out.println("返回页面的结果对象为result = " + result);
        return result;
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
