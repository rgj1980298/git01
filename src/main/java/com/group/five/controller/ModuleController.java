package com.group.five.controller;

import com.group.five.base.BaseController;
import com.group.five.model.ModuleModel;
import com.group.five.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("module")
@Controller
public class ModuleController extends BaseController {

    @Autowired
    public ModuleService moduleService;

    @RequestMapping("queryAllModules")
    @ResponseBody
    public List<ModuleModel> queryAllModules(Integer roleId){
        return moduleService.queryAllModulesByRoleId(roleId);
    }
}
