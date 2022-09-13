package com.group.five.dao;

import com.group.five.base.BaseMapper;
import com.group.five.pojo.Role;
import com.group.five.query.RoleQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RoleMapper{
    @Select("select * from t_role where is_valid = 1")
    List<Role> selectAllRole();

    List<Role> selectByParams(RoleQuery roleQuery);

    @Select("select * from t_role where id = #{id} and is_valid = 1")
    Role selectByPrimaryKey(Integer id);

    @Select("select * from t_role where role_name = #{roleName} and is_valid = 1")
    Role selectByRoleName(String roleName);

    @Select("select * from t_role where role_name = #{roleName} and id != #{id} and is_valid = 1")
    Role selectByRoleNameNotId(@Param("roleName") String roleName, @Param("id") Integer id);

    Integer insertSelective(Role role);

    Integer updateByPrimaryKey(Role role);

    Integer updateByPrimaryKeySelective(Role role);

    @Update("update t_role set is_valid = 0 where id = #{id}")
    Integer deleteFromPrimaryKey(Integer id);
}