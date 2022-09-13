package com.group.five.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group.five.base.BaseService;
import com.group.five.dao.ClazzMapper;
import com.group.five.dao.CourseMapper;
import com.group.five.dao .ExamMapper;
import com.group.five.dao.UserMapper;
import com.group.five.pojo.Exam;
import com.group.five.query.ExamQuery;
import com.group.five.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ExamService extends BaseService<Exam,Integer> {
    @Resource
    ExamMapper examMapper;


    /**
     * 多条件查询
     * @param query
     * @return
     */
    public Map<String, Object> queryExamByParams (ExamQuery query) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(query.getPage(), query.getLimit());
        PageInfo<Exam> pageInfo =
                new PageInfo<>(examMapper.selectByParams(query));
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
    //查询所有教师给前端下拉框
    public List<Map<String,Object>> queryAllTeacherid() {
        return examMapper.queryAllTeacherid();
    }
    @Transactional
    public void saveExam(Exam exam) {

        checkParams(exam.getCoursename(),exam.getTime(),exam.getPlace(),exam.getClazzid(),exam.getTeacherid());


       /* AssertUtil.isTrue(courseMapper.selectByPrimaryKey(exam.getCourseid())==null,"该课程不存在！");
        AssertUtil.isTrue(clazzMapper.selectByPrimaryKey(exam.getClazzid())==null,"该班级不存在！");
        AssertUtil.isTrue(userMapper.selectByPrimaryKey(exam.getTeacherid())==null||userMapper.selectByPrimaryKey(exam.getTeacherid()).getRoleid()!=1,"该教师不存在！");
*/
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now=df.format(new Date());
        String time=df.format(exam.getTime());
        AssertUtil.isTrue(now.compareTo(time)>0,"考试日期不能早于当前日期！");

        AssertUtil.isTrue(examMapper.queryCourseName(exam.getCoursename()).contains(exam.getClazzid()),"该班已经设置过该场考试！");

        exam.setIsValid(1);
        exam.setCreateDate(new Date());
        exam.setUpdateDate(new Date());


        AssertUtil.isTrue(examMapper.insertSelective(exam) < 1, "考试数据添加失败！");
    }

    @Transactional
    public void updateExam(Exam exam) {
        AssertUtil.isTrue(exam.getId()==null,"待修改考试不存在！");
        Exam temp = examMapper.selectByPrimaryKey(exam.getId());
        AssertUtil.isTrue(temp==null,"待修改考试不存在！");


        checkParams(exam.getCoursename(),exam.getTime(),exam.getPlace(),exam.getClazzid(),exam.getTeacherid());

       /* AssertUtil.isTrue(courseMapper.selectByPrimaryKey(exam.getCourseid())==null,"该课程不存在！");
        AssertUtil.isTrue(clazzMapper.selectByPrimaryKey(exam.getClazzid())==null,"该班级不存在！");
        AssertUtil.isTrue(userMapper.selectByPrimaryKey(exam.getTeacherid())==null||userMapper.selectByPrimaryKey(exam.getTeacherid()).getRoleid()!=1,"该教师不存在！");
*/
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now=df.format(new Date());
        String time=df.format(exam.getTime());
        AssertUtil.isTrue(now.compareTo(time)>0,"考试日期不能早于当前日期！");

        AssertUtil.isTrue( examMapper.queryCourseName(exam.getCoursename()).contains(exam.getClazzid())&&exam.getId()!=examMapper.queryExamid(exam.getClazzid(),exam.getCoursename()),"该班已经设置过该场考试！");

        exam.setUpdateDate(new Date());

        AssertUtil.isTrue(examMapper.updateByPrimaryKeySelective(exam) < 1, "考试数据修改失败！");
    }

    //非空校验
    private void checkParams(String coursename, Date date, String place, Integer claszzid, Integer teacherid) {
        AssertUtil.isTrue(StringUtils.isBlank(coursename), "请输入课程名称！");
        AssertUtil.isTrue(date == null, "请输入考试时间！");
        AssertUtil.isTrue(StringUtils.isBlank(place), "请输入考试地点！");
        AssertUtil.isTrue(claszzid == null, "请输入班级编号！");
        AssertUtil.isTrue(teacherid == null, "请输入监考老师编号！");
    }



}
