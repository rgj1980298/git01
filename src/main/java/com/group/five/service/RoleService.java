package com.group.five.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group.five.dao.RoleMapper;
import com.group.five.pojo.Role;
import com.group.five.query.RoleQuery;
import com.group.five.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class RoleService{
    @Autowired(required = false)
    private RoleMapper roleMapper;

    public Map<String,Object> list(RoleQuery roleQuery){
        Map<String,Object> result = new HashMap<>();
        PageHelper.startPage(roleQuery.getPage(),roleQuery.getLimit());
        PageInfo<Role> pageInfo =new PageInfo<>(roleMapper.selectByParams(roleQuery));
        result.put("count",pageInfo.getTotal());
        result.put("data",pageInfo.getList());
        result.put("code",0);
        result.put("msg","");
        return result;
    }

    public Role selectByPrimary(Integer id){
        Role role = roleMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(null != role && role.getIsValid() == 0,"该用户已被删除");
        return role;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Role role){
        AssertUtil.isTrue(null == role.getRoleName() || "".equals(role.getRoleName().trim()),"角色名不能为空");
        AssertUtil.isTrue(null == role.getRoleRemark() || "".equals(role.getRoleRemark().trim()),"角色备注不能为空");
        AssertUtil.isTrue(null != roleMapper.selectByRoleName(role.getRoleName()),"该角色名已存在");
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        role.setIsValid(1);
        AssertUtil.isTrue(roleMapper.insertSelective(role)<1,"角色添加失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Role role){
        AssertUtil.isTrue(null == role.getRoleName() || "".equals(role.getRoleName().trim()),"角色名不能为空");
        AssertUtil.isTrue(null == role.getRoleRemark() || "".equals(role.getRoleRemark().trim()),"角色备注不能为空");
        AssertUtil.isTrue(null == role.getId(),"该用户不存在");
        AssertUtil.isTrue(role.getId() == 1 || role.getId() == 2,"学生与教师是系统角色，不能更改");
        Role temp = roleMapper.selectByPrimaryKey(role.getId());
        AssertUtil.isTrue(null == temp,"该用户不存在");
        AssertUtil.isTrue(null != roleMapper.selectByRoleNameNotId(role.getRoleName(), role.getId()),"该角色和其他角色重名");
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role)<1,"角色添加失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id){
        AssertUtil.isTrue(null == roleMapper.selectByPrimaryKey(id),"待删除的角色不存在");
        AssertUtil.isTrue(id == 1 || id == 2,"学生与教师是系统角色，不能删除");
        AssertUtil.isTrue(roleMapper.deleteFromPrimaryKey(id) < 1,"删除失败");
    }
}
