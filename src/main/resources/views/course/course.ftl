<!DOCTYPE html>
<html>
	<head>
		<title>课程信息管理</title>
		<#include "../common.ftl">
	</head>
	<body class="childrenBody">
		<form class="layui-form" >
			<blockquote class="layui-elem-quote quoteBox">
				<form class="layui-form" >
					<div class="layui-inline">
						<div class="layui-input-inline">
							<input type="text" name="courseName" class="layui-input searchVal" placeholder="课程名" />
						</div>
						<div class="layui-input-inline">
							<input type="text" name="minTotalCount" class="layui-input searchVal" placeholder="最小课时" />
						</div>
						<div class="layui-input-inline">
							<input type="text" name="maxTotalCount" class="layui-input searchVal" placeholder="最大课时" />
						</div>
						<#--<div class="layui-input-inline">
							<input type="text" name="teacherName" class="layui-input searchVal" placeholder="教师名称" />
						</div>-->

						<div class="layui-input-inline">
							<select name="courseKindId"  id="courseKindId" >
								<option value="" >请选择课程种类</option>

							</select>
						</div>
						<#--								<option value="Java">Java</option>
                                                        <option value="C++" >C++</option>
                                                        <option value="算法" >算法</option>
                                                        <option value="大数据" >大数据</option>-->
						<a class="layui-btn search_btn" data-type="reload" id="btnSearch">
							<i class="layui-icon">&#xe615;</i> 搜索
						</a>
					</div>
				</form>
			</blockquote>

			<#--数据表格-->
			<table id="courseList" class="layui-table"  lay-filter="course">
			</table>

			<#--头部工具栏-->
			<script type="text/html" id="toolbarDemo">
				<div class="layui-btn-container">
					<a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
						<i class="layui-icon">&#xe608;</i>
						添加
					</a>
					<a class="layui-btn layui-btn-normal delNews_btn" lay-event="delete">
						<i class="layui-icon">&#xe608;</i>
						删除
					</a>
				</div>
			</script>


			<!--操作-->
			<script id="op" type="text/html" >
					<a href="javascript:;"  class="layui-btn layui-btn-warm layui-btn-xs"  lay-event="update">修改</a>
					<a href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs"  lay-event="del">删除</a>
			</script>
		
		</form>
	<script type="text/javascript" src="${ctx}/js/course/course.js"></script>
	</body>
</html>