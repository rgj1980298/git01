<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(clazz.id)!}" />
    <input type="hidden" id="teacherId" value="${(clazz.teacherName)!}"><#--获取当前数据的教师id-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="clazzName" id="clazzName"  value="${(clazz.clazzName)!}" placeholder="请输入课程名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">授课教师</label>
        <div class="layui-input-block">
            <select name="teacherId" id="teacher">
                <option value="">请选择</option>
            </select>
        </div>
    </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级人数</label>
        <div class="layui-input-block">
            <input type="number" class="layui-input userEmail"
                   lay-verify="num" name="num" value="${(clazz.num)!}" id="num" placeholder="请输入班级人数">
        </div>
    </div>
    <#--    <div class="magb15 layui-col-md4 layui-col-xs12">-->
    <#--        <label class="layui-form-label">角色</label>-->
    <#--        <div class="layui-input-block">-->
    <#--            <select name="roleIds"  xm-select="selectId">-->
    <#--            </select>-->
    <#--        </div>-->
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateUser">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx}/js/clazz/update.js"></script>
</body>
</html>