package com.group.five.controller;

import com.group.five.annotations.RequirePermission;
import com.group.five.base.BaseController;
import com.group.five.base.ResultInfo;
import com.group.five.pojo.Idea;
import com.group.five.query.IdeaQuery;

import com.group.five.service.IdeaService;
import com.group.five.utils.AssertUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("idea")
public class IdeaController extends BaseController {

    @Resource
    private IdeaService ideaService;

    /**
     * 多条件分页查询数据
     *
     * @param ideaQuery
     * @return
     */
    @RequirePermission(code = "6004")
    @GetMapping("list")
    @ResponseBody
    public Map<String, Object> queryByParams(IdeaQuery ideaQuery) {
        return ideaService.queryByParams(ideaQuery);
    }


    /**
     * 进入营销机会页面
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "idea/sale_chance";
    }



    @RequestMapping("addupdate")
    public String addupdate(Integer id,HttpServletRequest request){
        Idea idea = ideaService.selectById(id);
        //Idea idea = ideaService.selectByPrimaryKey(id);

        request.setAttribute("idea",idea);
       return "idea/add_update";
}





    /**
     * 打开营销机会修改/添加的页面
     * @return
     */
   /* @RequestMapping("addupdate")
    public String toAddUpdatePage(Integer id,HttpServletRequest request){
        //如果是修改操作那么需要将修改的数据映射在页面中
        if(id != null){
            Idea idea = ideaService.selectByPrimaryKey(id);
            AssertUtil.isTrue(idea == null,"数据异常，请重试");
            request.setAttribute("idea",idea);
        }
        return "idea/add_update";
    }*/








    /**
     * 添加数据
     * @return
     */
 /*   @PostMapping("save")
    @ResponseBody
    public ResultInfo save(HttpServletRequest request, Idea idea){

        ideaService.save(idea);
        return success("1");
    }*/

    @RequirePermission(code = "6001")
    @PostMapping("save")
    @ResponseBody
    public ResultInfo save(Idea idea){

        //ideaService.save(idea);
        ideaService.addIdea(idea);

        return success();
    }



    /**add
     * 添加数据
     * @return
     */
    @RequirePermission(code = "6003")
    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(Idea idea){
        ideaService.updateIdea(idea);
        return success();
    }



    /**
     * 逻辑删除
     * @param ids
     */
    @RequirePermission(code = "6002")
    @RequestMapping("deleteBatch")
    @ResponseBody
    public ResultInfo deleteBatchs(Integer[] ids){
        ideaService.deleteBatchs(ids);
        return success("删除成功");
    }
}
