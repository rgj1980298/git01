<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <input name="id" type="hidden" value="${(exam.id?c)!}"/>
            <input id="adad" type="hidden" value="${(add)!}"/>
            <input  type="hidden" id="courseName"  value="${(exam.coursename)!}"/>
            <input  type="hidden" id="clazzId"  value="${(exam.clazzid?c)!}"/>
            <input  type="hidden" id="teacherId"  value="${(exam.teacherid?c)!}"/>

            <#--<div class="magb15 layui-col-md4 layui-col-xs12">
                <label class="layui-form-label">课程编号</label>
                <div class="layui-input-block">
                    <select name="courseid"  xm-select="selectId">
                    </select>
                </div>
            </div>-->

            <#--<div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">课程编号</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           lay-verify="required" name="courseid" id="userName"  value="${(exam.courseid)!}" placeholder="请输入课程编号">
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
                <label class="layui-form-label">考试时间</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           lay-verify="required" name="time" id="time" value="${(exam.time?string("yyyy-MM-dd HH:mm:ss"))!}" placeholder="请输入考试时间">
                </div>
            </div>

            <#--<div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">考试地点</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userEmail"
                           lay-verify="email" name="email" value="${(exam.place)!}"
                           id="email"
                           placeholder="请输入考试地点">
                </div>
            </div>-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">考试地点</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           lay-verify="required" name="place" id="trueName" value="${(exam.place)!}" placeholder="请输入考试地点">
                </div>
            </div>

            <#--<div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">班级编号</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userEmail"
                           lay-verify="phone" name="phone" value="${(user.phone)!}" id="phone" placeholder="请输入班级编号">
                </div>
            </div>-->
           <#-- <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">班级编号</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           lay-verify="required" name="clazzid" id="trueName" value="${(exam.clazzid?c)!}" placeholder="请输入班级编号">
                </div>
            </div>-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">班级编号</label>
                <div class="layui-input-block">
                    <select name="clazzid"  id="clazzid" lay-verify="required">
                        <option value="${(exam.clazzid?c)!}" >请选择班级编号</option>

                    </select>
                </div>
            </div>

           <#-- <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">监考老师编号</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userEmail"
                           lay-verify="phone" name="phone" value="${(user.phone)!}" id="phone" placeholder="请输入监考老师编号">
                </div>
            </div>-->
           <#-- <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">监考老师编号</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           lay-verify="required" name="teacherid" id="trueName" value="${(exam.teacherid)!}" placeholder="请输入监考老师编号">
                </div>
            </div>-->
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">监考老师编号</label>
                <div class="layui-input-block">
                    <select name="teacherid"  id="teacherid" lay-verify="required">
                        <option value="${(exam.teacherid?c)!}" >请选择监考老师编号</option>

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
                   <#-- <button class="layui-btn layui-btn-lg layui-btn-normal">重置</button>-->
                </div>
            </div>

             <#--日期类型的选择框-->
            <script>
                layui.use('laydate', function(){
                    var laydate = layui.laydate;

                    //执行一个laydate实例
                    laydate.render({
                        elem: '#time' //指定元素
                        ,type: 'datetime'
                    });
                });
            </script>
        </form>

    <script type="text/javascript" src="${ctx}/js/exam/add.update.js"></script>

    </body>
</html>