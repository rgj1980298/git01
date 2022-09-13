package com.group.five.dao;

import com.group.five.base.BaseMapper;
import com.group.five.query.CommentQuery;
import com.group.five.pojo.Comment;

import java.util.Map;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment,Integer> {
    //多条件分页查询数据
    public  List<Comment> queryByParams(CommentQuery query);

    //批量删除
    public Integer deleteBatch(Integer[] ids);

}