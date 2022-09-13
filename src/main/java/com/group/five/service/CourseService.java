package com.group.five.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group.five.base.BaseService;
import com.group.five.dao.CourseKindMapper;
import com.group.five.dao.CourseMapper;
import com.group.five.pojo.Course;
import com.group.five.pojo.CourseKind;
import com.group.five.query.AllQueryForCourse;
import com.group.five.query.CourseQuery;
import com.group.five.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @BelongsProject: SVN1
 * @BelongsPackage: com.group.five.service
 * @Author: sisyphus
 * @CreateTime: 2022-09-01  15:15
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class CourseService extends BaseService<Course,Integer> {

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private CourseKindMapper courseKindMapper;

    /**
     * 多条件分页查询课程proMax
     * @param allQueryForCourse
     * @return
     */
/*    public Map<String, Object> queryAllForCourseByParams(AllQueryForCourse allQueryForCourse) {
        Map<String, Object> map = new HashMap<>();
        List list = courseMapper.selectInfoByParams(allQueryForCourse);
        PageHelper.startPage(allQueryForCourse.getPage(), allQueryForCourse.getLimit());
        PageInfo<list> pageInfo = new PageInfo<>(courseMapper.selectInfoByParams(allQueryForCourse));
        for (Object course : list) {
            System.out.println(course);
        }
        return map;
    }*/


    /**
     * 多条件分页查询课程
     * @param courseQuery
     * @return
     */
    public Map<String, Object> queryCourseByParams(CourseQuery courseQuery) {
        Map<String, Object> map = new HashMap<>();
/*        List list = courseMapper.selectByParams(courseQuery);
        for(Object course:list){
            System.out.println(course);
        }*/
        PageHelper.startPage(courseQuery.getPage(), courseQuery.getLimit());
        PageInfo<Course> pageInfo = new PageInfo<>(courseMapper.selectByParams(courseQuery));
        map.put("code",0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 课程添加
     * @param course
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCourse(Course course){
        //1.参数校验
        checkParams4AddCourse(course.getCourseKindId(),course.getCourseName(),course.getTotalCount());
        String string = courseKindMapper.selectKindNameById(course.getCourseKindId());
        AssertUtil.isTrue(StringUtils.isBlank(string),"该课程类别不存在！");
        //2.设置相关参数默认值
        course.setCreateTime(new Date());
        course.setUpdateTime(new Date());
        course.setCourseKind(string);
        course.setIsValid(1);
        System.out.println("修改"+course);
        //3.执行添加
        AssertUtil.isTrue(courseMapper.insertSelective(course)<1,"课程添加失败！");
    }

    /**
     * 课程修改
     * @param course
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCourse(Course course){
        //1.通过id查询数据库中的课程记录
        Course temp = courseMapper.selectByPrimaryKey(course.getId());
        AssertUtil.isTrue(null == temp,"该课程不存在！");
        checkParams4AddCourse(course.getCourseKindId(),course.getCourseName(),course.getTotalCount());
        String string = courseKindMapper.selectKindNameById(course.getCourseKindId());
        AssertUtil.isTrue(StringUtils.isBlank(string),"该课程类别不存在！");
        //2.设置相关参数值
        course.setUpdateTime(new Date());
        course.setCourseKind(string);
        System.out.println("修改"+course);
        //3.执行修改，判断结果
        AssertUtil.isTrue(courseMapper.updateByPrimaryKeySelective(course)<1,"课程修改失败！");
    }

    /**
     * 课程删除
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCourse(Integer[] ids){
        // 判断要删除的id是否为空
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择需要删除的数据！");
        // 删除数据
        AssertUtil.isTrue(courseMapper.deleteBatch(ids) < 0, "课程删除失败！");
    }

    private void checkParams4AddCourse(Integer courseKindId,String courseName,Integer totalCount){
        AssertUtil.isTrue(StringUtils.isBlank(courseKindId.toString()),"请输入课程类型！");
        AssertUtil.isTrue(StringUtils.isBlank(courseName),"请输入课程名称！");
        AssertUtil.isTrue(StringUtils.isBlank(totalCount.toString()),"请输入课时数！");
    }



}
