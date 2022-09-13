<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id" id="hidId" value="${(courseKind.id)!}">

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">课程种类</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="kindName" lay-verify="required"
                   id="kindName" value="${(courseKind.kindName)!}" placeholder="请输入课程种类">
        </div>
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
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateCourseKind">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/course/add.update.kind.js"></script>
</body>
</html>