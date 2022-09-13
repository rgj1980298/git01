package com.group.five.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group.five.base.BaseQuery;
import com.group.five.dao.LoggingMapper;
import com.group.five.pojo.Logging;
import com.group.five.query.LoggingQuery;
import com.group.five.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoggingService {

    @Autowired(required = false)
    public LoggingMapper loggingMapper;

    public Map<String, Object> queryByParamsForTable(LoggingQuery loggingQuery) {
        Map<String,Object> result = new HashMap<>();
        PageHelper.startPage(loggingQuery.getPage(),loggingQuery.getLimit());
        PageInfo<Logging> pageInfo =new PageInfo<>(loggingMapper.selectByParams(loggingQuery));
        result.put("count",pageInfo.getTotal());
        result.put("data",pageInfo.getList());
        result.put("code",0);
        result.put("msg","");
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteLogging(Integer id){
        AssertUtil.isTrue(null == id,"日志id不存在");
        AssertUtil.isTrue(null == loggingMapper.selectByPrimary(id),"该日志不存在");
        AssertUtil.isTrue(loggingMapper.deleteLogging(id) < 1,"日志删除失败");
    }

}
