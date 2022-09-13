package com.group.five.controller;
import com.group.five.annotations.RequirePermission;
import com.group.five.base.BaseController;
import com.group.five.base.ResultInfo;
import com.group.five.service.CommentService;
import com.group.five.utils.AssertUtil;
import com.group.five.pojo.Comment;
import com.group.five.query.CommentQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("comment")
public class CommentController extends BaseController {
    @Resource
    private CommentService commentService;


    /**
     * 多条件分页查询数据
     * @param commentQuery
     * @return
     */
    @RequirePermission(code = "8004")
    @GetMapping("list")
    @ResponseBody
    public Map<String, Object> queryByParams(CommentQuery commentQuery){
        return commentService.queryByParams(commentQuery);
    }

    @RequestMapping("toAddUpdatePage")
    public String toAddUpdatePage(Integer id,HttpServletRequest request){
        //如果是修改操作那么需要将修改的数据映射在页面中
        if(id != null){
            Comment comment= commentService.selectByPrimaryKey(id);
            AssertUtil.isTrue(comment == null,"数据异常，请重试");
            request.setAttribute("comment",comment);
        }
        return "comment/add_update";
    }

    @RequestMapping("index")
    public String index(){
        return "comment/comment";
    }

    /**
     * 添加数据
     * @return
     */
    @RequirePermission(code = "8001")
    @PostMapping("save")
    @ResponseBody
    public ResultInfo save(Comment comment){
        //获取创建人
        commentService.addComment(comment);
        return success();
    }

    @RequirePermission(code = "8003")
    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(Comment comment){
        commentService.updateComment(comment);
        return success();
    }

    @RequirePermission(code = "8002")
    @RequestMapping("deleteBatch")
    @ResponseBody
    public ResultInfo deleteBatchs(Integer[] ids){
        commentService.deleteBatchs(ids);
        return success("删除成功");
    }
}
