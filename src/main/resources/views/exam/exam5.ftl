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


</form>

<script type="text/javascript" src="${ctx}/js/exam/exam5.js"></script>
</body>
</html>



