package com.group.five.controller;

import com.group.five.annotations.RequirePermission;
import com.group.five.base.BaseController;
import com.group.five.base.ResultInfo;
import com.group.five.pojo.User;
import com.group.five.query.UserQuery;
import com.group.five.service.UserService;
import com.group.five.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public ResultInfo login(String userName, String userPwd){
        return userService.login(userName,userPwd);
    }

    @RequirePermission(code = "1001")
    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo add(User user){
        userService.saveUser(user);
        return success("新增用户成功");

    }
    @RequirePermission(code = "1002")
    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids){
        userService.deleteByIds(ids);
        return success("删除用户成功");
    }

    @RequirePermission(code = "1003")
    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(User user){
        userService.updateUser(user);
        return success("更新用户成功");
    }

    @RequirePermission(code = "1004")
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(UserQuery userQuery){
        return userService.queryUserByParams(userQuery);
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public ResultInfo updatePassword(String oldPassword,String newPassword,String confirmPassword,HttpServletRequest request){
        Integer id= LoginUserUtil.releaseUserIdFromCookie(request);
        userService.updatePassword(oldPassword,newPassword,confirmPassword,id);
        return success("更新密码成功");
    }
    @RequestMapping("index")
    public String index(){
        return "user/user";
    }
    @RequestMapping("toPasswordPage")
    public String toPasswordPage(){
        return "user/password";
    }
    @RequestMapping("toUpdateAddPage")
    public String  addUserPage(Integer id, Model model){
        if(null != id){
            model.addAttribute("user",userService.selectByPrimaryKey(id));
        }
        return "user/add_update";
    }
    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String,Object>> queryAllRoles(Integer id){
        return userService.queryAllRoles(id);
    }


    @RequestMapping("queryAllClazz")
    @ResponseBody
    public List<Map<String,Object>> queryAllClazz(Integer id){
        return userService.queryAllClazz(id);
    }
}
