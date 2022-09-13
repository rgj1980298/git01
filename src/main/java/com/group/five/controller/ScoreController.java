package com.group.five.controller;

import com.group.five.annotations.RequirePermission;
import com.group.five.base.BaseController;
import com.group.five.base.ResultInfo;
import com.group.five.pojo.Score;
import com.group.five.query.ScoreQuery;
import com.group.five.service.ExamService;
import com.group.five.service.ScoreService;
import com.group.five.utils.AssertUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("score")
public class ScoreController extends BaseController {
    @Resource
    ScoreService scoreService;

    @Resource
    ExamService examService;

    @RequestMapping("index/2")
    public String index2(){
        return "exam/exam2";
    }

    @RequestMapping("index/5")
    public String index5(){
        return "exam/exam5";
    }

    @RequestMapping("queryAllCourse")
    @ResponseBody
    public List<Map<String,Object>> queryAllCourse(){
        return scoreService.queryAllCourse();
    }

    @RequestMapping("queryAllClazzid")
    @ResponseBody
    public List<Map<String,Object>> queryAllClazzid(){
        return scoreService.queryAllClazzid();
    }

    @RequestMapping("queryAllStudentid")
    @ResponseBody
    public List<Map<String,Object>> queryAllStudentid(){
        return scoreService.queryAllStudentid();
    }

    @RequirePermission(code = "502004")
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryScoreByParams (ScoreQuery query) {
        return scoreService.queryScoreByParams(query);
    }

    @RequirePermission(code = "5040")
    @RequestMapping("list2")
    @ResponseBody
    public Map<String, Object> queryScoreByParams2 (ScoreQuery query) {
        return scoreService.queryScoreByParams(query);
    }

    @RequirePermission(code = "502001")
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveScore(Score score){
        scoreService.saveScore(score);
        return success();
    }

    @RequirePermission(code = "502003")
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateExam(@RequestParam() Integer id, Score score){
        scoreService.updateScore(id,score);
        return success();
    }

    @RequirePermission(code = "502002")
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteScore(Integer[] ids){
        scoreService.deleteBatch(ids);
        return success("成绩记录删除成功");
    }

    @RequestMapping("addOrUpdateScorePage")
    public String addOrUpdateScorePage(Integer id, HttpServletRequest request){
        if(id != null&&id>0){
            Score score = scoreService.selectByPrimaryKey(id);
            AssertUtil.isTrue(score == null,"数据异常请重试");
            request.setAttribute("score",score);
        }
        if(id != null&&id==-1){
            request.setAttribute("add",-1);
        }
        return "exam/ad_upScore";
    }
}






