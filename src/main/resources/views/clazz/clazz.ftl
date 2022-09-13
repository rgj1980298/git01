<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form" >
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="userName" class="layui-input searchVal" placeholder="班级编号" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="clazzName" id="clazzName" class="layui-input searchVal" placeholder="班级名称" />
                </div>
                <a class="layui-btn search_btn  " data-type="reload" >
                    <i class="layui-icon">&#xe615;</i>
                    查询
                </a>
            </div>
        </form>
    </blockquote>

    <table id="userList" class="layui-table"  lay-filter="users"></table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                新增
            </a>
            <a class="layui-btn layui-btn-danger delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                批量删除
            </a>
        </div>
    </script>

    <!--操作-->
    <script id="userListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>
</form>

<script type="text/javascript" src="${ctx}/js/clazz/clazz.js"></script>
</body>
</html>