package com.group.five.service;

import com.group.five.dao.ModuleMapper;
import com.group.five.dao.PermissionMapper;
import com.group.five.pojo.Permission;
import com.group.five.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PermissionService {

    @Autowired(required = false)
    public PermissionMapper permissionMapper;

    @Autowired(required = false)
    public ModuleMapper moduleMapper;


    public List<String> queryUserHasRolesHasPermissions(Integer userId){
        return permissionMapper.queryUserHasRolesHasPermissions(userId);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void addGrant(Integer[] mids,Integer roleId){
        //删除权限
        permissionMapper.deleteByRoleId(roleId);

        if(null == mids || mids.length <= 0){
            return ;
        }

        List<Permission> list = new ArrayList<>();
        //添加权限
        for(Integer mid : mids){
            Permission permission = new Permission();
            permission.setModuleId(mid);
            permission.setRoleId(roleId);
            permission.setCreateDate(new Date());
            permission.setUpdateDate(new Date());
            permission.setAclValue(moduleMapper.queryAclVauleByMid(mid));
            list.add(permission);
        }
        AssertUtil.isTrue(permissionMapper.insertBatch(list) < mids.length,"更改权限失败");
    }
}
