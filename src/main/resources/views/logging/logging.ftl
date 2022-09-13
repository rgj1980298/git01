<!DOCTYPE html>
<html>
<head>
	<title>日志管理</title>
	<#include "../common.ftl">
	<style>
		.moduleName {
			box-shadow: 0px -1px 13px -1px #3dc21b;
			background-color:#35ab0d;
			border-radius:25px;
			border:1px solid #000000;
			display:inline-block;
			color:#ffffff;
			font-family:Arial;
			font-size:3px;
			padding:0px 20px;
			text-decoration:none;
			text-shadow:1px 1px 0px #2f6627;
		}
		.moduleName:hover {
			background-color:#5cbf2a;
		}
		.moduleName:active {
			position:relative;
			top:1px;
		}

	</style>
</head>
<body class="childrenBody">

<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="userName"
						   class="layui-input
					searchVal" placeholder="角色名" />
				</div>
				<div class="layui-input-inline">
					<input type="text" name="moduleName"
						   class="layui-input
					searchVal" placeholder="模块操作" />
				</div>
				<a class="layui-btn search_btn" data-type="reload"><i
							class="layui-icon">&#xe615;</i> 搜索</a>
				<div class="layui-input-inline">
					<label class="layui-form-label">屏蔽-查询</label>
					<input type="checkbox" name="mode" lay-skin="switch" checked lay-text="NO|OFF" lay-filter="sexDemo" >
				</div>
			</div>
		</form>
	</blockquote>
	<table id="loggingList" class="layui-table"  lay-filter="loggings"></table>

	<#--<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
				<i class="layui-icon">&#xe608;</i>
				添加角色
			</a>
			<a class="layui-btn layui-btn-normal delNews_btn" lay-event="grant">
				<i class="layui-icon">&#xe672;</i>
				授权
			</a>
		</div>
	</script>-->
	<!--操作-->
	<script id="loggingListBar" type="text/html">
		<#--<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>-->
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>

	</script>
</form>
<script type="text/javascript" src="${ctx}/js/logging/logging.js"></script>
</body>
</html>