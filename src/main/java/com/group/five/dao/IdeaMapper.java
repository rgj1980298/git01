package com.group.five.dao;

import com.group.five.base.BaseMapper;
import com.group.five.pojo.Idea;
import com.group.five.query.IdeaQuery;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface IdeaMapper extends BaseMapper<Idea,Integer> {

    //多条件分页查询数据
    public List<Idea> queryByParams(IdeaQuery idea);


    List<Map<String, Object>> updateidea(Integer id);

    @Select("select * from qwer where id = #{id}")
    Idea selectById(Integer id);

}