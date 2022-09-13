package com.group.five.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group.five.base.BaseService;
import com.group.five.dao.CourseKindMapper;
import com.group.five.pojo.Course;
import com.group.five.pojo.CourseKind;
import com.group.five.query.CourseKindQuery;
import com.group.five.query.CourseQuery;
import com.group.five.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.Key;
import java.util.*;

/**
 * @BelongsProject: SVN1
 * @BelongsPackage: com.group.five.service
 * @Author: sisyphus
 * @CreateTime: 2022-09-02  16:11
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class CourseKindService extends BaseService<CourseKind,Integer> {

    @Resource
    private CourseKindMapper courseKindMapper;
    /**
     * 查询所有课程种类
     * @return
     */
    public List<Map<String,Object>> queryAllKinds() {
        return courseKindMapper.queryAllCourseKind();
    }


    /**
     * 课程种类多条件查询
     * @param courseKindQuery
     * @return
     */

    public Map<String, Object> queryCourseKindByParams(CourseKindQuery courseKindQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(courseKindQuery.getPage(), courseKindQuery.getLimit());
        PageInfo<CourseKind> pageInfo = new PageInfo<>(courseKindMapper.selectByParams(courseKindQuery));
        map.put("code",0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 课程类别添加
     * @param courseKind
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCourseKind(CourseKind courseKind){
        AssertUtil.isTrue(StringUtils.isBlank(courseKind.getKindName()),"课程种类不能为空！");
        courseKind.setCreateTime(new Date());
        courseKind.setUpdateTime(new Date());
        courseKind.setIsValid(1);
        AssertUtil.isTrue(courseKindMapper.insertSelective(courseKind)<1,"课程种类添加失败！");
    }

    /**
     * 课程类别修改
     * @param courseKind
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCourse(CourseKind courseKind){
        //1.通过id查询数据库中的课程记录
        CourseKind temp = courseKindMapper.selectByPrimaryKey(courseKind.getId());
        AssertUtil.isTrue(null == temp,"该课程不存在！");
        AssertUtil.isTrue(StringUtils.isBlank(courseKind.getKindName()),"课程种类不能为空！");
        //2.设置相关参数值
        courseKind.setUpdateTime(new Date());
        //3.执行修改，判断结果
        AssertUtil.isTrue(courseKindMapper.updateByPrimaryKeySelective(courseKind)<1,"课程修改失败！");
    }

    /**
     * 课程类别删除
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCourseKind(Integer[] ids){
        // 判断要删除的id是否为空
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择需要删除的课程种类！");
        List<Map<String,Object>> list = courseKindMapper.selectCourseByKindIds(ids);
        System.out.println(list.size());
        for(Map<String,Object> map:list){
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            System.out.println(map);
        }
        if(list.size()>0)
        AssertUtil.isTrue(list.size()>0,"被选中的课程种类下有课程！");
        // 删除数据
        AssertUtil.isTrue(courseKindMapper.deleteBatch(ids) < 0, "课程种类删除失败！");
    }

}
