package com.group.five.dao;

import com.group.five.pojo.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {

    @Select("select module_id from t_permission where role_id = #{roleId}")
    List<Integer> queryMidsByRoleId(Integer roleId);

    @Delete("delete from t_permission where role_id = #{roleId}")
    Integer deleteByRoleId(Integer roleId);

    /*@Select("")
    String selectCalValueByMid(Integer mid);*/

    List<String>queryUserHasRolesHasPermissions(Integer userId);

    Integer insertBatch(List<Permission> permissions);
}
