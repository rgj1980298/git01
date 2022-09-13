package com.group.five.controller;

import com.group.five.annotations.RequirePermission;
import com.group.five.base.BaseController;
import com.group.five.base.ResultInfo;
import com.group.five.pojo.Course;
import com.group.five.pojo.CourseKind;
import com.group.five.query.CourseKindQuery;
import com.group.five.query.CourseQuery;
import com.group.five.service.CourseKindService;
import com.group.five.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: SVN1
 * @BelongsPackage: com.group.five.controller
 * @Author: sisyphus
 * @CreateTime: 2022-09-02  16:26
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
@RequestMapping("course_kind")
public class CourseKindController extends BaseController{

    @Resource
    private CourseKindService courseKindService;


    /**
     * 查询课程种类
     * @return
     */
    @ResponseBody
    @RequestMapping("query")
    public List<Map<String,Object>> queryAllCourseKind(){
        List<Map<String,Object>> list = courseKindService.queryAllKinds();
        return list;
    }

    /**
     * 页面：课程类别主页
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "course/course_kind";
    }

    /**
     * 多条件分页查询课程信息
     * @param courseKindQuery
     * @return
     */
    @RequirePermission(code = "302004")
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCourseByParams (CourseKindQuery courseKindQuery) {
        return courseKindService.queryCourseKindByParams(courseKindQuery);
    }

    /**
     * 页面：添加/修改
     * @param id
     * @param model
     * @return
     */

    @RequestMapping("to_add_or_update")
    public String addOrUpdateCoursePage(Integer id, Model model){
        if (null != id) {
            CourseKind courseKind = courseKindService.selectByPrimaryKey(id);
            System.out.println("to_add_or_update"+courseKind);
            model.addAttribute("courseKind", courseKind);
        }
        return "course/add_update_kind";
    }

    /**
     * 添加课程类别
     * @param request
     * @param courseKind
     * @return
     */
    @RequirePermission(code = "302001")
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addCourseKind(HttpServletRequest request, CourseKind courseKind){
        // 获取用户ID,只有老师可以添加课程类别
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        //此处需要根据用户id查询用户真实姓名
        //String name = "AAA";
/*        course.setTeacherName(name);*/
        courseKindService.addCourseKind(courseKind);
        return success("课程类别添加成功");
    }

    /**
     * 方法：修改课程种类
     * @param request
     * @param courseKind
     * @return
     */
    @RequirePermission(code = "302003")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateCourse(HttpServletRequest request, CourseKind courseKind){
        courseKindService.updateCourse(courseKind);
        return success("课程修改成功！");
    }

    /**
     * 删除课程种类
     * @param ids
     * @return
     */
    @RequirePermission(code = "302002")
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCourseKind (Integer[] ids) {
        // 删除课程
        courseKindService.deleteCourseKind(ids);
        return success("课程删除成功！");
    }


}
