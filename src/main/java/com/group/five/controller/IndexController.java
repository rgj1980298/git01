package com.group.five.controller;

import com.group.five.base.BaseController;
import com.group.five.base.ResultInfo;
import com.group.five.pojo.User;
import com.group.five.service.PermissionService;
import com.group.five.service.UserService;
import com.group.five.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private PermissionService permissionService;

    @RequestMapping(value = {"index","/"})
    public String index(){
        return "index";
    }

    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("main")
    public String main(HttpServletRequest request){
        int id = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.selectByPrimaryKey(id);
        request.setAttribute("user",user);

        List<String> permissions = permissionService.queryUserHasRolesHasPermissions(id);
        request.getSession().setAttribute("permissions",permissions);
        return "main";
    }

    
}
