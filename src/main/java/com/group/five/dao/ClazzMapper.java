package com.group.five.dao;

import com.group.five.base.BaseMapper;
import com.group.five.pojo.Clazz;
import com.group.five.query.ClazzQuery;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author Dcz
 * @DateCreated 2022/9/1 17:10
 */

@Repository
public interface ClazzMapper extends BaseMapper<Clazz,Integer> {

    // 多条件查询
    public List<Clazz> queryClazzByParams(ClazzQuery clazzQuery);

    // 通过id查询clazz对象
    Clazz selectById(Integer id);

    // 通过班级名称查询班级是否存在
    Integer selectClazzByName(String clazzName);

    // 根据id查询教师是否存在
    @Select("select * from t_user_role where role_id = 1 and user_id = #{id}")
    Integer selectTeacherById(Integer teacherId);

    // 添加班级
    Integer addClazz(Clazz clazz);

    // 下拉框 查询教师id
    List<Map<String, Object>> queryAllTeachers();

    @Select("select username from t_user t left join t_user_role tu on t.id = tu.user_id where role_id = 1 and t.id = #{id}")
    String selectUserNameById(Integer id);

    // 自习状态
    Integer updateStatus(Clazz clazz);

    // 学生到齐状态
    Integer updateMode(Clazz clazz);

    Integer selectClazzByNameNotId(Clazz clazz);
}
