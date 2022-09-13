package com.group.five.controller;

import com.group.five.annotations.RequirePermission;
import com.group.five.base.BaseController;
import com.group.five.base.ResultInfo;
import com.group.five.pojo.Exam;
import com.group.five.query.ExamQuery;
import com.group.five.service.ExamService;
import com.group.five.utils.AssertUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("exam")
public class ExamController extends BaseController {
    @Resource
    ExamService examService;

    @RequirePermission(code = "501004")
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryExamByParams (ExamQuery query) {
        return examService.queryExamByParams(query);
    }

    @RequirePermission(code = "5030")
    @RequestMapping("list2")
    @ResponseBody
    public Map<String, Object> queryExamByParams2 (ExamQuery query) {
        return examService.queryExamByParams(query);
    }


    @RequestMapping("index/1")
    public String index1(){
        return "exam/exam1";
    }

    @RequestMapping("index/4")
    public String index4(){
        return "exam/exam4";
    }


    @RequestMapping("queryAllCourse")
    @ResponseBody
    public List<Map<String,Object>> queryAllCourse(){
        return examService.queryAllCourse();
    }

    @RequestMapping("queryAllClazzid")
    @ResponseBody
    public List<Map<String,Object>> queryAllClazzid(){
        return examService.queryAllClazzid();
    }

    @RequestMapping("queryAllTeacherid")
    @ResponseBody
    public List<Map<String,Object>> queryAllTeacherid(){
        return examService.queryAllTeacherid();
    }

    @RequirePermission(code = "501001")
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveExam(Exam exam){
        examService.saveExam(exam);
        return success();
    }

    @RequirePermission(code = "501003")
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateExam(Exam exam){
        examService.updateExam(exam);
        return success();
    }

    @RequirePermission(code = "501002")
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteExam(Integer[] ids){
        examService.deleteBatch(ids);
        return success("考试记录删除成功");
    }

    @RequestMapping("addOrUpdateExamPage")
    public String addOrUpdateExamPage(Integer id, HttpServletRequest request){
        if(id != null&&id>0){
            Exam exam = examService.selectByPrimaryKey(id);
            AssertUtil.isTrue(exam == null,"数据异常请重试");
            request.setAttribute("exam",exam);
        }
        if(id != null&&id==-1){
            request.setAttribute("add",-1);
        }
        return "exam/add_update";
    }
}
