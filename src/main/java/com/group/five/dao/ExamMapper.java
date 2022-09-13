package com.group.five.dao;

import com.group.five.base.BaseMapper;
import com.group.five.pojo.Exam;
import com.group.five.query.ExamQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ExamMapper extends BaseMapper<Exam,Integer> {
    List<Integer> queryCourseName(String coursename);

    List<Integer> selectCourse(Integer clazzid);

    Integer queryExamid(Integer clazzid,String coursename);

    Integer queryCourseid(String coursename);

    List<Map<String,Object>> queryAllClazzid();

    List<Map<String,Object>> queryAllCourse();

    List<Map<String,Object>> queryAllStudentid();

    List<Map<String,Object>> queryAllTeacherid();

}