package com.group.five.dao;

import com.group.five.model.ModuleModel;
import com.group.five.pojo.Module;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ModuleMapper {
    @Select("select id,parent_id,module_name from t_module where role_id = #{roleId}")
    List<ModuleModel> queryAllModuleByRoleId(Integer roleId);

    @Select("select id,parent_id pId,module_name name from t_module where is_valid = 1")
    List<ModuleModel> queryAllModule();

    @Select("select opt_value from t_module where id = #{mid}")
    String queryAclVauleByMid(Integer mid);

    @Select("select * from t_module where opt_value = #{optValue}")
    Module selectByOptValue(String optValue);

}
