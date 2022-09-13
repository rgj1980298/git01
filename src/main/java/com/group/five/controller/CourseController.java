package com.group.five.controller;

import com.group.five.annotations.RequirePermission;
import com.group.five.base.BaseController;
import com.group.five.base.ResultInfo;
import com.group.five.pojo.Course;
import com.group.five.query.AllQueryForCourse;
import com.group.five.query.CourseQuery;
import com.group.five.service.CourseService;
import com.group.five.utils.AssertUtil;
import com.group.five.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @BelongsProject: SVN1
 * @BelongsPackage: com.group.five.controller
 * @Author: sisyphus
 * @CreateTime: 2022-09-01  15:15
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
@RequestMapping("course")
public class CourseController extends BaseController {

    @Resource
    private CourseService courseService;


    /**
     * 页面：课程主页
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "course/course";
    }


    /**
     * 多条件分页查询课程信息proMax
     * @param allQueryForCourse
     * @return
     */
/*    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryAllForCourseByParams (AllQueryForCourse allQueryForCourse) {
        return courseService.queryAllForCourseByParams(allQueryForCourse);
    }*/

    /**
     * 多条件分页查询课程信息
     * @param courseQuery
     * @return
     */
    @RequirePermission(code = "301004")
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCourseByParams (CourseQuery courseQuery) {
        return courseService.queryCourseByParams(courseQuery);
    }


    /**
     * 添加课程
     * @param request
     * @param course
     * @return
     */
    @RequirePermission(code = "301001")
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addCourse(HttpServletRequest request, Course course){
        // 获取用户ID,只有老师可以添加课程
        System.out.println(course);
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        //此处需要根据用户id查询用户真实姓名
        String name = "AAA";
        course.setTeacherName(name);
        courseService.addCourse(course);
        return success("课程添加成功");
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
            Course course = courseService.selectByPrimaryKey(id);
            System.out.println("to_add_or_update"+course);
            model.addAttribute("course", course);
        }
        return "course/add_update";
    }


    /**
     * 方法：修改课程
     * @param request
     * @param course
     * @return
     */
    @RequirePermission(code = "301003")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateCourse(HttpServletRequest request, Course course){
        System.out.println(course);
        courseService.updateCourse(course);
        return success("课程修改成功！");
    }

    /**
     * 删除课程
     * @param ids
     * @return
     */
    @RequirePermission(code = "301002")
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCourse (Integer[] ids) {
        // 删除课程
        courseService.deleteCourse(ids);
        return success("课程删除成功！");
    }

}
