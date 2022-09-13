package com.group.five.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group.five.base.BaseService;
import com.group.five.dao.*;
import com.group.five.pojo.Score;
import com.group.five.query.ScoreQuery;
import com.group.five.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreService extends BaseService<Score,Integer> {
    @Resource
    ScoreMapper scoreMapper;

    @Resource
    ExamMapper examMapper;

    @Resource
    UserMapper userMapper;



    public Map<String, Object> queryScoreByParams (ScoreQuery query) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(query.getPage(), query.getLimit());
        PageInfo<Score> pageInfo =
                new PageInfo<>(scoreMapper.selectByParams(query));
        map.put("code",0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }
    //查询所有课程给前端下拉框
    public List<Map<String,Object>> queryAllCourse() {
        return examMapper.queryAllCourse();
    }
    //查询所有班级给前端下拉框
    public List<Map<String,Object>> queryAllClazzid() {
        return examMapper.queryAllClazzid();
    }
    //查询所有学生给前端下拉框
    public List<Map<String,Object>> queryAllStudentid() {
        return examMapper.queryAllStudentid();
    }


    @Transactional
    public void saveScore(Score score) {

        checkParams(score.getStudentid(),score.getClazzid(),score.getCoursename());


       /* AssertUtil.isTrue(userMapper.selectByPrimaryKey(score.getStudentid())==null,"该学号不存在！");
        AssertUtil.isTrue(clazzMapper.selectByPrimaryKey(score.getClazzid())==null,"该班级不存在！");
        AssertUtil.isTrue(examMapper.selectByPrimaryKey(score.getExamid())==null,"该场考试不存在！");*/

        AssertUtil.isTrue(!(score.getClazzid().equals(userMapper.selectByPrimaryKey(Integer.valueOf(score.getStudentid())).getGradeId())),"该学生不是这个班的！");
        AssertUtil.isTrue(!(examMapper.selectCourse(score.getClazzid()).contains(examMapper.queryCourseid(score.getCoursename()))),"该学生未参与此场考试！");
        AssertUtil.isTrue(scoreMapper.selectExamId(Integer.valueOf(score.getStudentid())).contains(examMapper.queryExamid(score.getClazzid(),score.getCoursename())),"该成绩记录已存在！");
        AssertUtil.isTrue(score.getScore() >100,"成绩最高100分");

        AssertUtil.isTrue(score.getScore()>79&&!(score.getState().equals("优秀")),"状态有误：80及其以上为优秀，60到80为及格，30到60为挂科，30以下为重修");
        AssertUtil.isTrue(score.getScore()>59&&score.getScore()<80&&!(score.getState().equals("及格")),"状态有误：80及其以上为优秀，60到80为及格，30到60为挂科，30以下为重修");
        AssertUtil.isTrue(score.getScore()>29&&score.getScore()<60&&!(score.getState().equals("挂科")),"状态有误：80及其以上为优秀，60到80为及格，30到60为挂科，30以下为重修");
        AssertUtil.isTrue(score.getScore()<30&&!(score.getState().equals("重修")),"状态有误：80及其以上为优秀，60到80为及格，30到60为挂科，30以下为重修");



        score.setIsValid(1);
        score.setCreateDate(new Date());
        score.setUpdateDate(new Date());


        AssertUtil.isTrue(scoreMapper.insertSelective(score) < 1, "考试数据添加失败！");
    }

    @Transactional
    public void updateScore(Integer id, Score score) {
        AssertUtil.isTrue(score.getId()==null,"待修改成绩不存在！");
        Score temp = scoreMapper.selectByPrimaryKey(score.getId());
        AssertUtil.isTrue(temp==null,"待修改成绩不存在！");

       /* System.out.println(scoreMapper.selectExamId(Integer.valueOf(score.getStudentid())).contains(examMapper.queryExamid(score.getClazzid(), score.getCoursename())));
        System.out.println(scoreMapper.queryExamId(score.getClazzid(), score.getCoursename()));
        System.out.println(examMapper.queryExamid(score.getClazzid(), score.getCoursename()));*/



        checkParams(score.getStudentid(),score.getClazzid(),score.getCoursename());


       /* AssertUtil.isTrue(examMapper.selectByPrimaryKey(exam.getCourseid())==null,"该课程不存在！");
        AssertUtil.isTrue(clazzMapper.selectByPrimaryKey(exam.getClazzid())==null,"该班级不存在！");
        AssertUtil.isTrue(userMapper.selectByPrimaryKey(exam.getTeacherid())==null||userMapper.selectByPrimaryKey(exam.getTeacherid()).getRoleid()!=1,"该教师不存在！");
*/
        AssertUtil.isTrue(!(score.getClazzid().equals(userMapper.selectByPrimaryKey(Integer.valueOf(score.getStudentid())).getGradeId())),"该学生不是这个班的！");
        AssertUtil.isTrue(!(examMapper.selectCourse(score.getClazzid()).contains(examMapper.queryCourseid(score.getCoursename()))),"该学生未参与此场考试！");
        AssertUtil.isTrue(scoreMapper.selectExamId(Integer.valueOf(score.getStudentid())).contains(examMapper.queryExamid(score.getClazzid(),score.getCoursename()))&&scoreMapper.selectexamid(id)!=examMapper.queryExamid(score.getClazzid(),score.getCoursename()),"该成绩记录已存在！");
        AssertUtil.isTrue(score.getScore() >100,"成绩最高100分");
        
        AssertUtil.isTrue(score.getScore()>79&&!(score.getState().equals("优秀")),"状态有误：80及其以上为优秀，60到80为及格，30到60为挂科，30以下为重修");
        AssertUtil.isTrue(score.getScore()>59&&score.getScore()<80&&!(score.getState().equals("及格")),"状态有误：80及其以上为优秀，60到80为及格，30到60为挂科，30以下为重修");
        AssertUtil.isTrue(score.getScore()>29&&score.getScore()<60&&!(score.getState().equals("挂科")),"状态有误：80及其以上为优秀，60到80为及格，30到60为挂科，30以下为重修");
        AssertUtil.isTrue(score.getScore()<30&&!(score.getState().equals("重修")),"状态有误：80及其以上为优秀，60到80为及格，30到60为挂科，30以下为重修");


        score.setUpdateDate(new Date());

        AssertUtil.isTrue(scoreMapper.updateByPrimaryKeySelective(score) < 1, "成绩数据修改失败！");
    }

    private void checkParams(String studentid, Integer claszzid, String coursename) {
        AssertUtil.isTrue(StringUtils.isBlank(studentid), "请输入学号！");
        AssertUtil.isTrue(claszzid == null, "请输入班级编号！");
        AssertUtil.isTrue(StringUtils.isBlank(coursename), "请输入课程名称！");
    }


}
