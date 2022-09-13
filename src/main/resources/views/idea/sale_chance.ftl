<!DOCTYPE html>
<html>
<head>
    <title>作业提交与检测</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="customerName" class="layui-input searchVal" placeholder="班级" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="linkMan" class="layui-input
							searchVal" placeholder="班长" />
                </div>
                <div class="layui-input-inline">
                    <select name="state"  id="state">
                        <option value="" >是否提交</option>
                        <option value="0">未提交</option>
                        <option value="1" >已提交</option>
                    </select>
                </div>
                <a type="button" class="layui-btn search_btn" id="btnSearch" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="ideaList" class="layui-table"  lay-filter="ideas">
    </table>

    <#--头部工具栏-->
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                添加
            </a>
<#--            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">-->
<#--                <i class="layui-icon">&#xe608;</i>-->
<#--                删除-->
<#--            </a>-->
        </div>
    </script>


    <!--操作 行工具栏-->
    <script id="asd" type="text/html">
        <a  href="javascript:;" class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a  href="javascript:;" class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/idea/sale.chance.js"></script>
</body>
</html>