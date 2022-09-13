<!DOCTYPE html>
<html>
<head>
    <title>成绩管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="id" class="layui-input searchVal" placeholder="学生姓名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="courseid" class="layui-input searchVal" placeholder="学生状态" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="clazzid" class="layui-input searchVal" placeholder="班级名称" />
                </div>
                <a class="layui-btn search_btn" data-type="reload" id="btnSearch">
                    <i class="layui-icon">&#xe615;</i>
                    搜索
                </a>
            </div>
        </form>
    </blockquote>

    <#--数据表格-->
    <table id="userList" class="layui-table"  lay-filter="users"></table>

    <#--头部工具栏-->
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                添加成绩
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除成绩
            </a>
        </div>
    </script>

    <!--操作-->
    <#--行工具栏-->
    <script id="userListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>
</form>

<script type="text/javascript" src="${ctx}/js/exam/score.js"></script>
</body>
</html>



