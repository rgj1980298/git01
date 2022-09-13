package com.group.five.dao;

import com.group.five.base.BaseMapper;
import com.group.five.pojo.CourseKind;

import java.util.List;
import java.util.Map;

public interface CourseKindMapper extends BaseMapper<CourseKind,Integer> {

    public List<Map<String,Object>> queryAllCourseKind();

    public List<Map<String,Object>> selectCourseByKindIds(Integer[] ids);

    public String selectKindNameById(Integer courseKindId);
}