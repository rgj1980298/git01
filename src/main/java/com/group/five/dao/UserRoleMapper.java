package com.group.five.dao;


import com.group.five.base.BaseMapper;
import com.group.five.pojo.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole,Integer> {

    //获取某个用户对应的角色数量
    Integer countUserRole(Integer id);

    //删除某个用户下所有角色
    Integer deleteUserRoleByUid(Integer id);
}