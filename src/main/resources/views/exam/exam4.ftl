<!DOCTYPE html>
<html>
<head>
    <title>考试安排</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="id" class="layui-input searchVal" placeholder="考试编号" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="courseid" class="layui-input searchVal" placeholder="课程编号" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="clazzid" class="layui-input searchVal" placeholder="班级编号" />
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

<script type="text/javascript" src="${ctx}/js/exam/exam4.js"></script>
</body>
</html>



