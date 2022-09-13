<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form">
    <input type="hidden" name="id" id="hidId" value="${(comment.id)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">昵称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="studentName" id="studentName"  value="${(comment.studentName)!}" placeholder="昵称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">评论对象</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="teacherName"
                   id="teacherName" value="${(comment.teacherName)!}" placeholder="请输入对象">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">输入评论内容</label>
        <div class="layui-input-block">
                    <textarea placeholder="评论内容" name="description" class="layui-textarea">
                  	    ${(comment.description)!}
                    </textarea>
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateComment">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/comment/add.update.js"></script>
</body>
</html>