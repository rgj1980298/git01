package com.group.five.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group.five.base.BaseService;
import com.group.five.dao.ClazzMapper;
import com.group.five.pojo.Clazz;
import com.group.five.query.ClazzQuery;
import com.group.five.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Dcz
 * @DateCreated 2022/9/1 17:04
 */
@Service
public class ClazzService extends BaseService<Clazz, Integer> {

    @Resource
    private ClazzMapper clazzMapper;

    /**
     * 多条件查询
     * @param query
     * @return
     */
    public Map<String, Object> queryClazzByParams(ClazzQuery query){
        // 开启分页
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(query.getPage(),query.getLimit());
        PageInfo<Clazz> pageInfo = new PageInfo<>(clazzMapper.queryClazzByParams(query));

        map.put("code",0);
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     * 根据主键做链表查询 显示相关数据
     * @param id
     * @return
     */
    public Clazz selectById(Integer id) {
        Clazz clazz = clazzMapper.selectById(id);
        return clazz;
    }

    /**
     * 添加数据
     * @param clazz
     */
    public void addClazz(Clazz clazz) {
        // 校验班级名称
        AssertUtil.isTrue(clazz.getClazzName() == null,"班级名称不能为空");
        AssertUtil.isTrue(clazzMapper.selectClazzByName(clazz.getClazzName()) > 0,"该班级名称已存在");
        // 检验参数
        checkParams(clazz);
        // 根据teacherId设置teacherName
        clazz.setTeacherName(clazzMapper.selectUserNameById(clazz.getTeacherId()));

        // 执行添加操作 判断是否添加成功
        clazzMapper.addClazz(clazz);
    }

    /**
     * 添加更新 - 校验参数
     * 班级名称 非空 唯一
     * 班级人数 非空 大于30 小于60
     * 任课教师 非空 存在
     *
     */
    private void checkParams(Clazz clazz) {
        // 校验班级人数
        AssertUtil.isTrue(clazz.getNum() == null||clazz.getNum() == "","班级人数不能为空");
        AssertUtil.isTrue(clazz.getNum() != null&&Integer.parseInt(clazz.getNum())<30,"班级人数过少");
        AssertUtil.isTrue(clazz.getNum() != null&&Integer.parseInt(clazz.getNum())>60,"班级人数过多");

        // 校验任课教师
        AssertUtil.isTrue(clazz.getTeacherId()==null,"请选择教师");
        AssertUtil.isTrue(clazzMapper.selectTeacherById(clazz.getTeacherId()) == null,"该教师不存在");
    }

    /**
     *  更新数据 - 校验参数
     *
     */
    public void updateClazz(Clazz clazz) {
        // 校验班级名称
        AssertUtil.isTrue(clazz.getClazzName() == null,"班级名称不能为空");
/*        Clazz temp = selectByPrimaryKey(clazz.getId());*/
        AssertUtil.isTrue(clazzMapper.selectClazzByNameNotId(clazz) > 0
                ,"该班级已存在");
        checkParams(clazz);
        // 执行更新参操作 判断受影响的行数
        clazz.setTeacherName(clazzMapper.selectUserNameById(clazz.getTeacherId()));
        AssertUtil.isTrue(clazzMapper.updateByPrimaryKeySelective(clazz)<1,"数据更新失败");
    }

    // 加载下拉框
    public List<Map<String, Object>> queryAllTeachers() {
        return clazzMapper.queryAllTeachers();
    }



    // 更新自习状态
    public Integer updateStatus(Clazz clazz) {
        AssertUtil.isTrue(clazz==null,"自习状态修改失败！");
        return clazzMapper.updateStatus(clazz);
    }

    // 学生到齐状态
    public Integer updateMode(Clazz clazz) {
        AssertUtil.isTrue(clazz==null,"学生到齐状态修改失败！");
        return clazzMapper.updateMode(clazz);
    }
}
