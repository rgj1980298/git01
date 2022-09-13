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
							<input type="text" name="kindName" class="layui-input searchVal" placeholder="课程种类" />
						</div>

						<a class="layui-btn search_btn" data-type="reload" id="btnSearch">
							<i class="layui-icon">&#xe615;</i> 搜索
						</a>
					</div>
				</form>
			</blockquote>

			<#--数据表格-->
			<table id="courseKindList" class="layui-table"  lay-filter="courseKind">
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
	<script type="text/javascript" src="${ctx}/js/course/course.kind.js"></script>
	</body>
</html>