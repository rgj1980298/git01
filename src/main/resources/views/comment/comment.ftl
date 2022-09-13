<!DOCTYPE html>
<html>
<head>
    <title>吐槽系统</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="studentName" class="layui-input searchVal" placeholder="学生名字" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="teacherName" class="layui-input searchVal" placeholder="老师名字" />
                </div>

                <a class="layui-btn search_btn" id="btnSearch" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="commentList" class="layui-table"  lay-filter="comments">
    </table>

    <#--头部工具栏-->
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                增加评论
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除
            </a>
        </div>
    </script>


    <!--操作 行工具栏-->
    <script id="commentListBar" type="text/html">
        <a class="layui-btn layui-btn-xs"  lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/comment/comment.js"></script>
</body>
</html>