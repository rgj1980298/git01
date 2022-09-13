package com.group.five.dao;

import com.group.five.base.BaseMapper;
import com.group.five.pojo.Clazz;
import com.group.five.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User,Integer> {

    public User selectByName(String name);
    public Integer deleteById(Integer[] Ids);
    public User selectByPrimaryKey(Integer userId);
    public List<Map<String,Object>> queryAllRoles(Integer id);

    List<Map<String,Object>> queryAllClazz(Integer id);

    @Select("select clazz_name from t_clazz where id = #{id}")
    String queryClazzNameByid(Integer id);

    @Select("select * from t_clazz where id = #{id}")
    Clazz queryClazzByid(Integer id);
}
