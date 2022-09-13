layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 课程列表
     */
    var  tableIns = table.render({
        elem: '#courseList',
        url : ctx+'/course/list?flag=1&state=1',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "courseListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'课程编号',fixed:"true",minWidth: 100},
            {field: 'courseName', title: '课程名称',align:"center"},
            {field: 'courseKind', title: '课程种类',  align:'center'},
            {field: 'totalCount', title: '课时', align:'center',minWidth: 60},
            {field: 'content', title: '课程内容', align:'center',minWidth: 200},
            {field: 'beginTime', title: '开始时间',  align:'center',minWidth: 180},
            {field: 'createTime', title: '创建时间', align:'center',minWidth: 180},
            {field: 'updateTime', title: '更新时间', align:'center',minWidth: 180},
            /*{field: 'teacherName', title: '授课教师', align:'center'},
            {field: 'courseKindId', title: '课程种类ID',  align:'center'},*/
            /*{field: 'createDate', title: '创建时间', align:'center'},
            {field: 'devResult', title: '开发状态', align:'center',
                templet:function (d) {
                    return formatterDevResult(d.devResult);
                }},*/
            {title: '操作',fixed:"right",align:"center", minWidth:150,templet:"#op"}
        ]]
    });

    //数据表格重载
    $("#btnSearch").click(function (){
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                courseKindId:$('[name="courseKindId"]').val(),
                courseName:$('[name="courseName"]').val(),
                minTotalCount:$('[name="minTotalCount"]').val(),
                maxTotalCount:$('[name="maxTotalCount"]').val(),
                teacherName:$('[name="teacherName"]').val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    //加载下拉框  课程种类
    var courseKindId;
    $.post(ctx+"/course_kind/query",function (data){
        console.log(data);
        var am = $("#courseKindId");
        var courseKindId = $("#courseKindId").val();
        console.log('aa'+courseKindId);
        if(data != null){
            for(var i = 0; i < data.length; i++){
                var opt = "<option value="+data[i].id+">"+data[i].kindName+"</option>";
                am.append(opt);
            }
        }

        // 重新渲染下拉框内容
        layui.form.render("select");
    });

    /**
     * 监听表格的头部工具栏
     */
    //监听事件
    table.on('toolbar(course)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus);
        console.log(obj);
        switch(obj.event){
            case 'add':
                openAddOrUpdateDialog();//打开添加修改的窗口页面
                break;
            case 'delete':
                deleteCourse(checkStatus.data);
        };
    });

    //打开课程添加/修改的窗口
    function openAddOrUpdateDialog(title,id){
        var title = "<h2>课程管理 - 课程添加</h2>";
        var url = ctx + "/course/to_add_or_update";
        //通过参数id判断目前是修改还是添加操作
        if(id){
            console.log("qqqqq"+id);
            title = "<h2>课程管理 - 课程修改</h2>";
            url += "?id="+id;
        }
        //打开修改添加页面
        layer.open({
            type:2,   //ifame
            title:title,
            content: url,   //页面的内容
            area:['500px','620px'], //设置宽高
            maxmin:true //可以伸缩页面大小
        });
    }

    /**
     * 监听表格的行工具栏
     */
    table.on('tool(course)', function(obj){
        if(obj.event == "update"){
            openAddOrUpdateDialog("修改课程",obj.data.id);
        }else if(obj.event == "del"){
            deleteCourseSingle(obj.data);
        }
    });

    /**
     * 删除课程数据多行
     * @param data
     */
    function deleteCourse(data) {
        // 判断用户是否选择了要删除的记录
        if (data.length == 0) {
            layer.msg("请选择要删除的记录！");
            return;
        }
        // 询问用户是否确认删除
        layer.confirm("您确定要删除选中的记录吗？",{
            btn:["确认","取消"],
        },function (index) {
            // 关闭确认框
            layer.close(index);
            // ids=1&ids=2&ids=3
            var ids = "ids=";
            // 遍历获取对应的id
            for (var i = 0; i < data.length; i++) {
                if (i < data.length - 1) {
                    ids = ids + data[i].id + "&ids=";
                } else {
                    ids = ids + data[i].id;
                }
            }
            // 发送ajax请求，删除记录
            $.ajax({
                type:"post",
                url: ctx + "/course/delete",
                data:ids, // 参数传递的是数组
                dataType:"json",
                success:function (result) {
                    if (result.code == 200) {
                        // 加载表格
                        tableIns.reload();
                    } else {
                        layer.msg(result.msg, {icon: 5});
                    }
                }
            });
        });
    }

    /**
     * 删除课程数据单行
     */
    function deleteCourseSingle(data){
        // 询问是否确认删除
        layer.confirm("确定要删除这条记录吗？", {icon: 3, title:"课程管理"},
            function (index) {
                // 关闭窗口
                layer.close(index);
                // 发送ajax请求，删除记录
                $.ajax({
                    type:"post",
                    url: ctx + "/course/delete",
                    data:{
                        ids:data.id
                    },
                    dataType:"json",
                    success:function (result) {
                        if (result.code == 200) {
                            // 加载表格
                            tableIns.reload();
                        } else {
                            layer.msg(result.msg, {icon: 5});
                        }
                    }
                });
            });
    }



});
