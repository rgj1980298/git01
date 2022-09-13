package com.group.five.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group.five.base.BaseService;
import com.group.five.base.ResultInfo;
import com.group.five.dao.UserMapper;
import com.group.five.dao.UserRoleMapper;
import com.group.five.model.UserModel;
import com.group.five.pojo.User;
import com.group.five.pojo.UserRole;
import com.group.five.query.UserQuery;
import com.group.five.utils.AssertUtil;
import com.group.five.utils.Md5Util;
import com.group.five.utils.PhoneUtil;
import com.group.five.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserService extends BaseService<User,Integer> {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    public ResultInfo login(String userName, String password){
        checkLoginParam(userName,password);
        User user = userMapper.selectByName(userName);
        AssertUtil.isTrue(user == null,"账号不存在");

        checkPassword(user.getPassword(),password);
        ResultInfo resultInfo = new ResultInfo();
        UserModel userModel = new UserModel();
        String id = UserIDBase64.encoderUserID(user.getId());
        userModel.setUserIdStr(id);
        userModel.setUserName(user.getUsername());
        userModel.setTrueName(user.getTrueName());
        resultInfo.setResult(userModel);
        return resultInfo;

    }
    public void checkLoginParam(String userName,String password){
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(password),"密码不能为空");
    }
    public void checkPassword(String dbPassword,String password){
        String encode = Md5Util.encode(password);
        AssertUtil.isTrue(!encode.equals(dbPassword),"密码错误");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(User user){
        checkParams(user.getUsername(),user.getPhone(),user.getEmail(),user.getTrueName());
        user.setIsValid(1);
        String password = Md5Util.encode("123");
        user.setPassword(password);
        user.setAge(18);
        user.setAddress("上海");
        AssertUtil.isTrue(null == user.getGradeId() || null == userMapper.queryClazzByid(user.getGradeId()),"班级不存在");
        user.setGrade(userMapper.queryClazzNameByid(user.getGradeId()));
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        AssertUtil.isTrue(userMapper.insertSelective(user)==null,"新增失败");
        Integer id = userMapper.selectByName(user.getUsername()).getId();
        relationUserRole(id,user.getRoleIds());
    }

    private void checkParams(String username, String phone, String email,String trueName) {
        AssertUtil.isTrue(null!= userMapper.selectByName(username),"用户已存在");
        AssertUtil.isTrue(StringUtils.isBlank(username),"用户名不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone),"手机号不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(email),"邮箱不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(trueName),"真实姓名不能为空");
    }


    private void checkUpdateParams(String username, String phone, String email,String trueName) {
        AssertUtil.isTrue(StringUtils.isBlank(username),"用户名不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone),"手机号格式不正确");
        AssertUtil.isTrue(StringUtils.isBlank(email),"邮箱不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(trueName),"真实姓名不能为空");
    }

    public void deleteByIds(Integer[] ids){
        AssertUtil.isTrue(null == ids || ids.length <1,"数据不存在");
        AssertUtil.isTrue(userMapper.deleteById(ids)!=ids.length,"删除失败");
    }

    public void updateUser(User user) {
        AssertUtil.isTrue(null == user.getId() || null == userMapper.selectByPrimaryKey(user.getId()),"数据异常请重试");
        AssertUtil.isTrue(user.getUserName() == null,"用户名不能为空");
        User dbUser = userMapper.selectByName(user.getUserName());
        AssertUtil.isTrue(dbUser != null && user.getId() != dbUser.getId(),"用户名已存在");
        checkUpdateParams(user.getUsername(),user.getPhone(),user.getEmail(),user.getTrueName());
        user.setUpdateDate(new Date());
        AssertUtil.isTrue(null == user.getGradeId() || null == userMapper.queryClazzByid(user.getGradeId()),"班级不存在");
        user.setGrade(userMapper.queryClazzNameByid(user.getGradeId()));
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) < 1,"用户修改失败");
        relationUserRole(user.getId(),user.getRoleIds());
    }

    public void updatePassword(String oldPassword, String newPassword, String confirmPassword,Integer id) {
        AssertUtil.isTrue(id==null,"用户不存在");
        User user = userMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(user==null,"用户异常");
        checkPwd(oldPassword,newPassword,confirmPassword,user.getPassword());
        user.setPassword(Md5Util.encode(newPassword));
        user.setUpdateDate(new Date());
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1,"修改密码失败");
    }

    //逻辑 旧密码不能为空 旧密码要与数据库密码一致，新密码不能为空，新密码不能与旧密码相同，确认密码不能为空 确认密码要与新密码一致
    private void checkPwd(String oldPassword, String newPassword, String confirmPassword,String dbPassword) {
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"旧密码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword),"确认密码不能为空");
        AssertUtil.isTrue(!dbPassword.equals(Md5Util.encode(oldPassword)),"旧密码不正确");
        AssertUtil.isTrue(oldPassword.equals(newPassword),"旧密码不能与新密码一致");
        AssertUtil.isTrue(!newPassword.equals(confirmPassword),"确认密码与新密码需要保持一致");
    }

    public Map<String,Object> queryUserByParams(UserQuery userQuery){
        Map<String,Object> map = new HashMap();
        PageHelper.startPage(userQuery.getPage(), userQuery.getLimit());
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.selectByParams(userQuery));
        map.put("code",0);
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }



    public List<Map<String, Object>> queryAllRoles(Integer id){
        return userMapper.queryAllRoles(id);
    }

    private void relationUserRole(Integer userId, String roleIds) {
        Integer count = userRoleMapper.countUserRole(userId);
        if (count > 0) {
            userRoleMapper.deleteUserRoleByUid(userId);
        }
        if (StringUtils.isNotBlank(roleIds)) {
            String[] splitIds = roleIds.split(",");
            List<UserRole> urs = new ArrayList<>();

            for (String rId : splitIds) {
                UserRole userRole = new UserRole();
                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());
                userRole.setRoleId(Integer.parseInt(rId)); // 角色id
                userRole.setUserId(userId);
                urs.add(userRole);
            }
            //批量添加
            AssertUtil.isTrue(userRoleMapper.insertBatch(urs) != urs.size(), "角色绑定失败");
        }
    }

    public List<Map<String,Object>> queryAllClazz(Integer id){
        return userMapper.queryAllClazz(id);
    }
}
