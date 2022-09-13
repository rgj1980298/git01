package com.group.five.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group.five.base.BaseService;
import com.group.five.base.ResultInfo;
import com.group.five.dao.CommentMapper;
import com.group.five.query.CommentQuery;
import com.group.five.utils.AssertUtil;
import com.group.five.pojo.Comment;
import com.group.five.pojo.Comment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService extends BaseService<Comment,Integer> {

    //多条件查询
    @Resource
    private CommentMapper commentMapper;

    public Map<String, Object> queryByParams(CommentQuery commentQuery){
        Map<String, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(commentQuery.getPage(),commentQuery.getLimit());
        List<Comment> comments = commentMapper.queryByParams(commentQuery);
        //按照分页条件，格式化数据
        PageInfo<Comment> commentPageInfo = new PageInfo<>(comments);

        map.put("code",0);
        map.put("msg","");
        map.put("count",commentPageInfo.getTotal());
        map.put("data",commentPageInfo.getList());
        return map;
    }

    @Transactional
    public void addComment(Comment comment){
        comment.setIsValid(1);
        comment.setUpdateDate(new Date());
        AssertUtil.isTrue(commentMapper.insertSelective(comment)<1,"吐槽失败");
    }

    @Transactional
    public void updateComment(Comment comment){
        //判断id是否存在
        AssertUtil.isTrue(comment.getId() == null,"数据异常，请重试");

        //设置默认值
        comment.setUpdateDate(new Date());

        //通过现有的id查询修改之前的数据
        Comment dbComment = commentMapper.selectByPrimaryKey(comment.getId());
        AssertUtil.isTrue(dbComment == null,"数据异常，请重试");

        //执行修改操作
        AssertUtil.isTrue(commentMapper.updateByPrimaryKeySelective(comment) < 1,"吐槽数据修改失败");
    }

    @Transactional
    public void deleteBatchs(Integer[] ids){
        AssertUtil.isTrue(ids == null || ids.length < 1,"未选中要删除的吐槽数据");
        commentMapper.deleteBatch(ids);
    }
}
