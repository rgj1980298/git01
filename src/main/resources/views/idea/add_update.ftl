<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">

    <input type="hidden" name="id" id="id" value="${(idea.id)!}">
   <#-- <input type="hidden" id="assignId" value="${(saleChance.assignMan)!}">-->

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级</label>
        <div class="layui-input-block">
            <input   hidden="hidden"   type="text" class="layui-input" lay-verify="required"
                   name="customerName" id="customerName"  value="${(idea.customerName)!}" placeholder="请输入班级">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班长</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="linkMan"
                   lay-verify="required"  value="${(idea.linkMan)!}" placeholder="请输入班长名字">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="phone"
                   name="linkPhone" value="${(idea.linkPhone)!}" id="phone" placeholder="请输入联系电话">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">概要</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="overview" value="${(idea.overview)!}" id="overview" placeholder="请输入概要">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">分配状态</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="state"
                   id="state" value="${(idea.state)!}" placeholder="请输入状态">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">完成率(%)</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="cgjl" value="${(idea.cgjl)!}"
                   placeholder="请输入完成率">
        </div>
    </div>


    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateIdea">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/idea/add.update.js"></script>
</body>
</html>