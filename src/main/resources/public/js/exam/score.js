layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 用户列表展示
     */
    var  tableIns = table.render({
        elem: '#userList',
        url : ctx + '/score/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "studentid", title:'学号',fixed:"true", width:80},
            {field: 'username', title: '姓名', minWidth:50, align:"center"},
            {field: 'clazzid', title: '班级编号', minWidth:50, align:"center"},
            {field: 'clazzname', title: '班级名称', minWidth:50, align:"center"},
            {field: 'examid', title: '考试场次', minWidth:50, align:"center"},
            {field: 'coursename', title: '课程', align:'center',minWidth:150},
            {field: 'score', title: '成绩', minWidth:100, align:'center'},
            {field: 'state', title: '状态', minWidth:100, align:'center'},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:150},
            {field: 'updateDate', title: '更新时间', align:'center',minWidth:150},
            {title: '操作', minWidth:150, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });


    //数据表格重载
    $("#btnSearch").click(function (){
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                username:$('[name="id"]').val(),
                state:$('[name="courseid"]').val(),
                clazzname:$('[name="clazzid"]').val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });

    });


    /**
     * 监听表格的头部工具栏
     */
    table.on('toolbar(users)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus.data);
        switch(obj.event){
            case 'add':
                openAddOrUpdateDialog(-1);//打开添加修改的窗口页面
                break;
            case 'del':
                deleteBatch(checkStatus.data);
                break;
        };
    });

    /**
     * 监听表格的行工具栏
     */
    table.on('tool(users)', function(obj){
        //修改操作
        if(obj.event == "edit"){
            openAddOrUpdateDialog(obj.data.id);
        }else if(obj.event == "del"){
            // 询问是否确认删除
            layer.confirm("确定要删除这条记录吗？", {icon: 3, title:"成绩管理"}, function (index) {
                // 关闭窗口
                layer.close(index);
                // 发送ajax请求，删除记录
                $.ajax({
                    type:"post",
                    url: ctx + "/score/delete",
                    data:{
                        ids:obj.data.id
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

    //批量删除用户
    function deleteBatch(data){
        //判断是否选中数据
        if(data.length == 0){
            layer.msg("请至少选中一条数据");
            return;
        }
        //向用户确认删除行为
        layer.confirm("您确定要删除选中的记录吗？",{
            btn:["确认","取消"],
        },function (index) {
            //关闭弹出框
            layer.close(index);

            //拼接后台需要的id数组  ids=1&ids=2
            var str = "ids=";
            for(var i = 0; i < data.length; i++){
                //判断是否是倒数第二个
                if(i < data.length - 1){
                    str += data[i].id + "&ids=";
                }else{
                    str += data[i].id;
                }
            }
            console.log(str);

            $.ajax({
                type:"post",
                url: ctx+"/score/delete",
                data:str,
                dataType:"json",
                success:function(data){
                    if(data.code == 200){
                        //刷新数据表格
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg,{icon:5})
                    }
                }
            });


        })
    }


    //打开对应添加修改的窗口
    function openAddOrUpdateDialog(id){
        var title = "<h2>添加成绩</h2>";
        var url = ctx + "/score/addOrUpdateScorePage";

        if(id==-1){
            url += "?id="+id;
        }

        //通过参数id判断目前是修改还是添加操作
        if(id>0){
            title = "<h2>修改成绩</h2>";
            url += "?id="+id;
        }

        //打开修改添加页面
        layer.open({
            type:2,   //ifame
            title:title,
            content: url,   //页面的内容
            area:["650px","400px"], //设置宽高
            maxmin:true //可以伸缩页面大小
        });
    }
});