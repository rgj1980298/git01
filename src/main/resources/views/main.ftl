<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>优极限教务系统</title>
    <#include "common.ftl">
</head>
<body class="layui-layout-body layuimini-all">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header">
        <div class="layui-logo">
            <a href="">
                <img src="images/logo.png" alt="logo">
                <h1>优极限教务系统</h1>
            </a>
        </div>
        <a>
            <div class="layuimini-tool"><i title="展开" class="fa fa-outdent" data-side-fold="1"></i></div>
        </a>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
            </li>
            <li class="layui-nav-item layuimini-setting">
                <a href="javascript:;">${(user.userName)!""}</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/user/toSettingPage" data-title="基本资料" data-icon="fa fa-gears">基本资料</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/user/toPasswordPage" data-title="修改密码" data-icon="fa fa-gears">修改密码</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" class="login-out"><i class="layui-icon layui-icon-logout" ></i>退出登录</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;"></a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll layui-left-menu">
                <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="currency">


                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-street-view"></i><span class="layui-left-nav"> 课程管理</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">

                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-1" data-tab="course/index"
                                   target="_self"><i class="fa fa-tty"></i><span class="layui-left-nav"> 课程信息管理</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-1" data-tab="course_kind/index"
                                   target="_self"><i class="fa fa-tty"></i><span class="layui-left-nav"> 课程种类管理</span></a>
                            </dd>

                        </dl>
                    </li>


                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-flag"></i><span class="layui-left-nav"> 班级管理</span> <span class="layui-nav-more"></span></a><dl class="layui-nav-child">
                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-3" data-tab="clazz/index" target="_self"><i class="fa fa-exchange"></i><span class="layui-left-nav"> 班级信息管理</span></a>
                                </dd>

                        </dl>
                    </li>


                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-desktop"></i><span class="layui-left-nav"> 考试管理</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <#if permissions?seq_contains("501004")>
                            <dd>

                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-5" data-tab="exam/index/1" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 考务管理</span></a>
                            </dd>
                            </#if>

                            <#if permissions?seq_contains("502004")>
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-6" data-tab="score/index/2" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 成绩管理</span></a>
                            </dd>
                            </#if>

                            <#if permissions?seq_contains("5030")>
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-8" data-tab="exam/index/4" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 考试安排</span></a>
                            </dd>
                            </#if>

                            <#if permissions?seq_contains("5040")>
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-9" data-tab="score/index/5" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 成绩查询</span></a>
                            </dd>
                            </#if>
                        </dl>
                    </li>


                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-home"></i><span class="layui-left-nav"> 作业管理</span> <span class="layui-nav-more"></span></a><dl class="layui-nav-child">

                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-10" data-tab="idea/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 作业信息管理</span></a>
                            </dd>

                        </dl>
                    </li>


                        <li class="layui-nav-item">
                            <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 评价管理</span> <span class="layui-nav-more"></span></a>
                            <dl class="layui-nav-child">

                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-10" data-tab="comment/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 评价信息管理</span></a>
                                    </dd>

                            </dl>
                        </li>

                        
                        <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 角色管理</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-10" data-tab="role/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 角色信息管理</span></a>
                            </dd>

                        </dl>
                    </li>
						
						
						<li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 用户管理</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" data-iframe-tab="${ctx}/user/index" class="layui-menu-tips" data-type="tabAdd" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 用户信息管理</span></a>
                            </dd>

                        </dl>
                    </li>
						
						
						<li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 日志管理</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-10" data-tab="logging/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 日志信息管理</span></a>
                            </dd>

                        </dl>
                    </li>
                    <span class="layui-nav-bar" style="top: 201px; height: 0px; opacity: 0;"></span>
                </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
            <ul class="layui-tab-title" id="top_tabs">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id="welcome"><i class="fa fa-home"></i> <span>首页</span></li>
            </ul>

            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/js/main.js"></script>
<script type="text/javascript" src="${ctx}/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
</body>
</html>
