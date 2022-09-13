package com.group.five.controller;

import com.group.five.annotations.RequirePermission;
import com.group.five.base.BaseController;
import com.group.five.base.ResultInfo;
import com.group.five.pojo.Clazz;
import com.group.five.query.ClazzQuery;
import com.group.five.service.ClazzService;
import com.group.five.utils.AssertUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author Dcz
 * @DateCreated 2022/9/1 16:47
 */
@RequestMapping("clazz")
@Controller
public class ClazzController extends BaseController {

    @Resource
    private ClazzService clazzService;

    @RequestMapping("index")
    public String index(){
        return "clazz/clazz";
    }


    /**
     * 多条件查询 显示数据
     * @param clazzQuery
     * @return
     */
    @RequirePermission(code = "4004")
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(ClazzQuery clazzQuery){
        return clazzService.queryClazzByParams(clazzQuery);
    }

    // 判断是添加还是更新
    @RequestMapping("toAddOrUpdatePage")
    public String toAddOrUpdatePage(Integer id, HttpServletRequest request){
        if (id != null){
            Clazz clazz = clazzService.selectById(id);
            AssertUtil.isTrue(clazz==null,"数据异常 请重试！");
            request.setAttribute("clazz",clazz);
        }
        return "clazz/update";
    }
    // 添加
    @RequirePermission(code = "4001")
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo add(Clazz clazz){
        clazzService.addClazz(clazz);
        return success();
    }
    // 更新
    @RequirePermission(code = "4003")
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Clazz clazz){
        clazzService.updateClazz(clazz);
        return success();
    }
    // 删除
    @RequirePermission(code = "4002")
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids){
        clazzService.deleteBatch(ids);
        return success();
    }

    // 下拉框
    @RequestMapping("queryAllTeachers")
    @ResponseBody
    public List<Map<String, Object>> queryAllTeachers(){
        return clazzService.queryAllTeachers();
    }

    // 自习状态
    @RequirePermission(code = "4003")
    @ResponseBody
    @RequestMapping("updateStatus")
    public ResultInfo updateStatus(Clazz clazz) {
        clazzService.updateStatus(clazz);
        return success();
    }

    // 学生到齐状态
    @RequirePermission(code = "4003")
    @ResponseBody
    @RequestMapping("mode")
    public ResultInfo mode(Clazz clazz) {
        clazzService.updateMode(clazz);
        return success();
    }




}
