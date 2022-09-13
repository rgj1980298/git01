<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
   <#-- <script>
        $(function(){
            $('#State').val(${(score.state)!});
        })
    </script>-->
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(score.id)!}"/>
    <input id="adad" type="hidden" value="${(add)!}"/>
    <input id="studentId" type="hidden" value="${(score.studentid)!}"/>
    <input  type="hidden" id="courseName"  value="${(score.coursename)!}"/>
    <input  type="hidden" id="clazzId"  value="${(score.clazzid?c)!}"/>




    <#--<div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">学号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="studentid" id="userName"  value="${(score.studentid)!}" placeholder="请输入学号">
        </div>
    </div>-->

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">学号</label>
        <div class="layui-input-block">
            <select name="studentid"  id="studentid" lay-verify="required">
                <option value="" >请选择学号</option>

            </select>
        </div>
    </div>


    <#--<div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级编号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="clazzid" id="trueName" value="${(score.clazzid?c)!}" placeholder="请输入班级编号">
        </div>
    </div>-->

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级编号</label>
        <div class="layui-input-block">
            <select name="clazzid"  id="clazzid" lay-verify="required">
                <option value="" >请选择班级编号</option>

            </select>
        </div>
    </div>

   <#-- <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">考试编号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="examid" id="trueName" value="${(socre.examid)!}" placeholder="请输入考试编号">
        </div>
    </div>-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">课程名称</label>
        <div class="layui-input-block">
            <select name="coursename"  id="coursename" lay-verify="required">
                <option value="" >请选择课程名称</option>

            </select>
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">成绩</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required|operationCount" name="score" id="trueName" value="${(score.score)!}" placeholder="请输入成绩">
        </div>
    </div>

   <#-- <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="state" id="trueName" value="${(score.state)!}" placeholder="请输入状态">
        </div>
    </div>-->

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <select name="state"  id="State" lay-verify="required">
                <option  value="" >请选择状态</option>
                <option   <#if "${(score.state)!}" = "优秀"  >selected="selected"</#if> value="优秀" >优秀</option>
                <option   <#if "${(score.state)!}" = "及格"  >selected="selected"</#if> value="及格" >及格</option>
                <option   <#if "${(score.state)!}" = "挂科"  >selected="selected"</#if> value="挂科" >挂科</option>
                <option   <#if "${(score.state)!}" = "重修"  >selected="selected"</#if> value="重修" >重修</option>
            </select>
        </div>
    </div>
    <#--参数传递数据类型 1,2,3,4-->

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateUser">确认
            </button>
            <#--<button class="layui-btn layui-btn-lg layui-btn-normal">重置</button>-->
        </div>
    </div>

</form>
<#--<script type="text/javascript">
    // 数据回显
    // 假设从后台请求回来被选中的下拉框数据type为1
    var type = ${(score.state)!};
    $("#backData").click(function(){
        $.each($("#state option"),function(index,item){
            if($(item).val() == type){
                $(item).prop("selected",true)
            }
        })
    });
</script>-->

<script type="text/javascript" src="${ctx}/js/exam/ad_upScore.js"></script>

</body>
</html>