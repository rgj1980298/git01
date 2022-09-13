package com.group.five.service;

import com.group.five.dao.ModuleMapper;
import com.group.five.dao.PermissionMapper;
import com.group.five.model.ModuleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModuleService {

    @Autowired(required = false)
    public ModuleMapper moduleMapper;

    @Autowired(required = false)
    public PermissionMapper permissionMapper;

    public List<ModuleModel> queryAllModulesByRoleId(Integer roleId){
        List<ModuleModel> list = moduleMapper.queryAllModule();
        List<Integer> mids = permissionMapper.queryMidsByRoleId(roleId);
        if(null != mids && mids.size() > 0){
            list.forEach(each->{
                if(mids.contains(each.getId())){
                    each.setChecked(true);
                }
            });
        }
        return list;
    }
}
