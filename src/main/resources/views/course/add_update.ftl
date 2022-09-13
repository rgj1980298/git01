<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id" id="id" value="${(course.id)!}">
    <input type="hidden" name="kind" id="kind" value="${(course.courseKindId)!}">
    <#--<input type="hidden" name="id" id="courseKind" value="${(course.courseKind)!}">-->
    <input type="hidden" id="teacherName" value="${(course.teacherName)!}"><#--获取当前数据的指派人id-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">课程名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="courseName" id="courseName"  value="${(course.courseName)!}" placeholder="请输入课程名称">
        </div>
    </div>
    <#--<div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">课程种类</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="courseKind" lay-verify="required"
                   id="courseKind" value="${(course.courseKind)!}" placeholder="请输入课程种类">
        </div>
    </div>-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">课程种类</label>
        <div class="layui-input-block">
            <select name="courseKindId"  id="courseKindId" lay-verify="required">
                <option value="0" >请选择课程种类</option>

            </select>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">课时</label>
        <div class="layui-input-block">
            <input type="number" class="layui-input" name="totalCount" id="totalCount"
                   lay-verify="required"  value="${(course.totalCount)!}" placeholder="请输入课时">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">课程内容</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="content" value="${(course.content)!}" id="content" placeholder="请输入课程内容">
        </div>
    </div>
    <#--<div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">授课老师</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="teacherName" value="${(course.teacherName)!}" id="teacherName" placeholder="请输入授课老师">
        </div>
    </div>-->


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">开课时间</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" value="${(course.beginTime?string("yyyy-MM-dd"))!}" id="beginTime" name="beginTime" placeholder="请选择开课时间">
        </div>



    <#--<div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">成功几率(%)</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="cgjl" value="${(saleChance.cgjl)!}"
                   placeholder="请输入成功几率">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">机会描述</label>
        <div class="layui-input-block">
                    <textarea placeholder="请输入机会描述信息" name="description" class="layui-textarea">
                  	    ${(saleChance.description)!}
                    </textarea>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">指派给</label>
        <div class="layui-input-block">
            <select name="assignMan" id="assignMan">
                <option value="">请选择</option>
            </select>
        </div>
    </div>-->


    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateCourse">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/course/add.update.js"></script>
</body>
</html>