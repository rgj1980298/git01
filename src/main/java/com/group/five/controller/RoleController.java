package com.group.five.controller;

import com.group.five.annotations.RequirePermission;
import com.group.five.base.BaseController;
import com.group.five.base.ResultInfo;
import com.group.five.pojo.Role;
import com.group.five.query.RoleQuery;
import com.group.five.service.PermissionService;
import com.group.five.service.RoleService;
import com.group.five.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    @Autowired
    public PermissionService permissionService;

    @Autowired
    public RoleService roleService;

    @RequestMapping("index")
    public String index(){
        return "role/role";
    }

    @RequestMapping("list")
    @ResponseBody
    @RequirePermission(code = "2004")
    public Map<String, Object> list(RoleQuery roleQuery){
        return roleService.list(roleQuery);
    }

    @RequestMapping("addOrUpdateRolePage")
    public String addOrUpdateRolePage(Integer id, Model model){
        if(null != id){
            model.addAttribute("role",roleService.selectByPrimary(id));
        }
        return "role/add_update";
    }

    @RequestMapping("save")
    @ResponseBody
    @RequirePermission(code = "2001")
    public ResultInfo save(Role role){
        //添加角色
        roleService.save(role);
        return success("添加角色成功");
    }

    @RequestMapping("update")
    @ResponseBody
    @RequirePermission(code = "2003")
    public ResultInfo update(Role role){
        //更新角色
        roleService.update(role);
        return success("更新角色信息成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequirePermission(code = "2002")
    public ResultInfo delete(Integer id){
        roleService.delete(id);
        return success("删除成功");
    }


    @RequestMapping("toAddGrantPage")
    public String toAddGrantPage(Integer roleId,Model model){
        AssertUtil.isTrue(null == roleId,"未选中角色");
        model.addAttribute("roleId",roleId);
        return "role/grant";
    }

    @RequestMapping("addGrant")
    @ResponseBody
    @RequirePermission(code = "2005")
    public ResultInfo addGrant(Integer[] mids,Integer roleId){
        permissionService.addGrant(mids,roleId);
        return success("添加权限成功");
    }
}



