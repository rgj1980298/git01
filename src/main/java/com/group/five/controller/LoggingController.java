package com.group.five.controller;

import com.group.five.annotations.RequirePermission;
import com.group.five.base.BaseController;
import com.group.five.base.ResultInfo;
import com.group.five.query.LoggingQuery;
import com.group.five.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("logging")
public class LoggingController extends BaseController {

    @Autowired
    public LoggingService loggingService;

    @RequestMapping("index")
    public String index(){
        return "logging/logging";
    }

    @RequirePermission(code = "7004")
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(LoggingQuery loggingQuery){
        return loggingService.queryByParamsForTable(loggingQuery);
    }

    @RequirePermission(code = "7002")
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer id){
        loggingService.deleteLogging(id);
        return success("删除日志成功");
    }
}
