package com.group.five.dao;

import com.group.five.base.BaseMapper;
import com.group.five.pojo.Course;
import com.group.five.pojo.Score;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreMapper extends BaseMapper<Score,Integer> {
    List<Integer> selectExamId(Integer studentid);

    Integer queryExamId(Integer clazzid,String coursename);

    Integer selectexamid(Integer id);


}