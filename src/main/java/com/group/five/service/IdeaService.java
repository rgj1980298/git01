package com.group.five.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group.five.base.BaseService;
import com.group.five.dao.IdeaMapper;
import com.group.five.pojo.Idea;
import com.group.five.query.IdeaQuery;
import com.group.five.utils.AssertUtil;
import com.group.five.utils.PhoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IdeaService extends BaseService<Idea,Integer> {

    @Resource
    private IdeaMapper ideaMapper ;



    public Idea selectById(Integer id){
        return ideaMapper.selectById(id);
    }


    /**
     * 多条件分页查询作业情况 (BaseService 中有对应的方法)
     *
     * @param ideaQuery
     * @return
     */

    public Map<String, Object> queryByParams(IdeaQuery ideaQuery) {
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(ideaQuery.getPage(), ideaQuery.getLimit());
        List<Idea> ideas = ideaMapper.queryByParams(ideaQuery);

        PageInfo<Idea> ideaInfo = new PageInfo<>(ideas);

        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", ideaInfo.getTotal());
        map.put("data", ideaInfo.getList());
        return map;
    }

    /**
     * 更新开发状态
     *  1.校验参数
     *      非空
     *  2.更新的数据必须存在
     * @param id
     * @param
     */
    @Transactional
    public void updateIdea(Integer id,Integer state){
        Idea idea = ideaMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(id == null || null == idea,"更新数据不存在");
        AssertUtil.isTrue(null == state,"作业未提交");
        //设置状态
        idea.setIsValid(1);
        idea.setUpdateDate(new Date());
        AssertUtil.isTrue(ideaMapper.updateByPrimaryKeySelective(idea) < 1,"状态更新失败");
    }

/**
 * 添加数据
 *      1.校验参数
 *          customerName   客户名称 非空
 *          linkMan       联系人   非空
 *          linkPhone      手机号码 非空  手机号11位正则校验
 *      2.设置默认值
 *          is_valid     数据有效   0无效 1有效
 *          create_date  数据创建时间
 *          update_date  数据修改时间
 *          create_man   数据的创建人  当前登录用户（交给controller层从cookie获取）直接设置到 salechance对象中
 *
 *
 *       3.执行添加操作，判断是否添加成功
 * @return
 */
@Transactional(propagation = Propagation.REQUIRED)
    public void addIdea(Idea idea){
    //参数校验
    CheckParams(idea.getCustomerName(),idea.getLinkMan(),idea.getLinkPhone());
    //设置默认值
    idea.setIsValid(1);
    idea.setUpdateDate(new Date());
    idea.setCreateDate(new Date());
    System.out.println(idea);
    //执行添加操作，判断是否添加成功
    AssertUtil.isTrue(ideaMapper.insertSelective(idea) < 1,"数据添加失败");
    
}


    /**
     * 校验添加数据
     *          customerName   客户名称 非空
     *          linkMan       联系人   非空
     *          linkPhone      手机号码 非空  手机号11位正则校验
     * @param customerName
     * @param linkMan
     * @param linkPhone
     */
    private void CheckParams(String customerName, String linkMan, String linkPhone) {

AssertUtil.isTrue(StringUtils.isBlank(customerName),"班级名不能为空");
    AssertUtil.isTrue(StringUtils.isBlank(linkMan),"班长名不能为空");
    AssertUtil.isTrue(StringUtils.isBlank(linkPhone),"电话号码不能为空");

    AssertUtil.isTrue(!PhoneUtil.isMobile(linkPhone),"电话号码不符合规范");
}


    /**
     * 改
     * @param idea
     */
    @Transactional(propagation = Propagation.REQUIRED)
    //判断id是否存在
    public  void updateIdea(Idea idea) {
      //校验非空参数
    AssertUtil.isTrue(null ==idea.getId() ||null==selectByPrimaryKey(idea.getId()),"待修改的记录不存在");
    AssertUtil.isTrue(StringUtils.isBlank(idea.getCustomerName()),"请输入班级");
        //设置默认值
        idea .setIsValid(1);
        idea.setUpdateDate(new Date());

        //执行修改操作
        AssertUtil.isTrue(ideaMapper.updateByPrimaryKeySelective(idea) < 1,"修改失败");


    }


//    /**
//     * 删除
//     * @param ideaId
//     */
//    public void deleteId(Integer ideaId){
//        Idea temp =selectByPrimaryKey(ideaId);
//        AssertUtil.isTrue(null==ideaId||null==temp,"待删除的记录不存在!");
//        temp.setIsValid(0);
//        AssertUtil.isTrue(updateByPrimaryKeySelective(temp)<1,"记录删除失败!");
//    }
//
//    public void save(Idea idea) {
//        AssertUtil.isTrue(insertSelective(idea)<1,"记录添加失败!");
//    }



    /**
     * 逻辑删除
     * @param ids
     */
    @Transactional
    public void deleteBatchs(Integer[] ids){
        AssertUtil.isTrue(ids == null || ids.length < 1,"未选中删除数据");
        ideaMapper.deleteBatch(ids);
    }
}
