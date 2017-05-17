package com.baomidou.springwind.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.Privilege;
import com.baomidou.springwind.entity.Role;
import com.baomidou.springwind.entity.RolePrivilege;
import com.baomidou.springwind.service.IPrivilegeService;
import com.baomidou.springwind.service.IRolePrivilegeService;
import com.baomidou.springwind.service.IRoleService;
import com.baomidou.springwind.service.IUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author CuiCan
 * @since 2017-05-17
 */
@Controller
@RequestMapping("/perm/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPrivilegeService privilegeService;

    @Autowired
    private IRolePrivilegeService rolePrivilegeService;

    @Autowired
    private IUserRoleService userRoleService;


    @Permission("2002")
    @RequestMapping("/list")
    public String list(Model model) {
        return "/role/list";
    }


    @ResponseBody
    @Permission("2002")
    @RequestMapping("/getRoleList")
    public String getUserList() {
        Page<Role> page = getPage();
        return jsonPage(roleService.selectPage(page, null));
    }


    @ResponseBody
    @Permission("2003")
    @RequestMapping("/delete/{roleId}")
    public String delete(@PathVariable Long roleId) {
        boolean exist = userRoleService.existRoleUser(roleId);
        if (exist) {
            return "false";
        }
        return booleanToString(roleService.deleteById(roleId));
    }


    @Permission("2002")
    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        if (id != null) {
            model.addAttribute("role", roleService.selectById(id));
        }
        return "/role/edit";
    }


    @ResponseBody
    @Permission("2002")
    @RequestMapping("/editRole")
    public String editRole(Role role) {
        boolean rlt = false;
        if (role != null) {
            if (role.getId() != null) {
                rlt = roleService.updateById(role);
            } else {
                rlt = roleService.insert(role);
            }
        }
        return callbackSuccess(rlt);
    }

    /**
     * 跳转到权限分配页面
     *
     * @param model
     * @param id
     * @return
     */
    @Permission("2003")
    @RequestMapping("/assigning")
    public String assigning(Model model, Long id) {
        model.addAttribute("roleId", id);
        return "/role/assigning";
    }

    /**
     * 获取权限树
     *
     * @param model
     * @param roleId
     * @return
     */
    @Permission("2003")
    @RequestMapping("/right")
    @ResponseBody
    public String right(Model model, @RequestParam(value = "roleId", required = true) Long roleId) {
        List<Privilege> list = privilegeService.selectList(null);
        List<Long> roleRightList = rolePrivilegeService.selecPermissionIdsByRoleId(roleId);
        List<Map<String, String>> rightList = new ArrayList<Map<String, String>>();
        for (Privilege r : list) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", r.getId().toString());
            map.put("pId", r.getPid().toString());
            map.put("name", r.getTitle());
            //默认展开树
            map.put("open", "true");
            //如果角色已有该权限，则默认选中
            if (roleRightList.contains(r.getId())) {
                map.put("checked", "true");
            }
            rightList.add(map);
        }
        return JSONObject.toJSONString(rightList);
    }

    /**
     * 更新权限列表
     *
     * @param roleId
     * @param rights
     * @return
     * @throws Exception
     */
    @Permission("2003")
    @RequestMapping("updateRoleRight")
    @ResponseBody
    public String updateRoleRight(@RequestParam(value = "roleId", required = false) Long roleId,
                                  @RequestParam(value = "rights", required = false) String rights) throws Exception {

        System.out.println("前台的参数：roleId = " + roleId + " , rights = " + rights);

        try {
            //查询出本角色已经分配了的权限
            RolePrivilege rolePrivilege = new RolePrivilege();
            rolePrivilege.setRid(roleId);
            EntityWrapper<RolePrivilege> ew = new EntityWrapper<RolePrivilege>();
            ew.setEntity(rolePrivilege);
            List<RolePrivilege> roleRightList = rolePrivilegeService.selectList(ew);

            //如果存在权限，先进行删除
            if (roleRightList.size() > 0) {
                for (RolePrivilege rp : roleRightList) {
                    rolePrivilegeService.delete(new EntityWrapper<RolePrivilege>(rp));
                }
            }

            String[] rightIds = rights.split(",");
            if (StringUtils.isNotBlank(rights) && rightIds != null) {
                //添加新分配的权限
                List<RolePrivilege> privileges = new ArrayList<RolePrivilege>();
                RolePrivilege e = null;
                for (String pid : rightIds) {
                    e = new RolePrivilege();
                    e.setPid(Long.valueOf(pid));
                    e.setRid(roleId);
                    privileges.add(e);
                }
                rolePrivilegeService.insertBatch(privileges);
            }
            return "true";
        } catch (Exception e) {
            return "false";
        }
    }
}
