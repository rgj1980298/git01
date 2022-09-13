layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 用户列表展示
     */
    var  tableIns = table.render({
        elem: '#userList',
        url : ctx + '/user/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'userName', title: '用户名', minWidth:50, align:"center"},
            {field: 'email', title: '用户邮箱', minWidth:100, align:'center'},
            {field: 'phone', title: '用户电话', minWidth:100, align:'center'},
            {field: 'trueName', title: '真实姓名', align:'center'},
            {field: 'age' , title: '年龄', align:'center'},
            {field: 'grade' , title: '班级', align:'center'},
            {field: 'address' , title: '地址', align:'center'},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:150},
            {field: 'updateDate', title: '更新时间', align:'center',minWidth:150},
            {title: '操作', minWidth:150, templet:'#userListBar',fixed:"right",align:"center"}
        ]],
        done: function(){
            $("[data-field='roleId']").children().each(function(){
                if($(this).text()=='1'){
                    $(this).text("老师")
                }
                if($(this).text()=='2'){
                    $(this).text("学生")
                }
            });

        }
    });


    //数据表格重载
    $(".search_btn").on("click",function(){
        table.reload("userListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                userName: $("input[name='userName']").val(), //用户名
                email: $("input[name='email']").val(), //邮箱
                phone: $("input[name='phone']").val() //手机号
            }
        })
    });


    /**
     * 监听表格的头部工具栏
     */
    table.on('toolbar(users)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus.data);
        switch(obj.event){
            case 'add':
                openAddOrUpdateDialog();//打开添加修改的窗口页面
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
        //修改
        if(obj.event == "edit"){
            openAddOrUpdateDialog(obj.data.id);
        }else if(obj.event == "del"){
            // 确认删除
            layer.confirm("确定要删除这条记录吗？", {icon: 3, title:" "}, function (index) {
                // 关闭窗口
                layer.close(index);
                $.ajax({
                    type:"post",
                    url: ctx + "/user/delete",
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
                url: ctx+"/user/delete",
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
        var title = "<h2>用户管理-用户添加</h2>";
        var url = ctx + "/user/toUpdateAddPage";

        //通过参数id判断目前是修改还是添加操作
        if(id){
            title = "<h2>用户管理-用户修改</h2>";
            url += "?id="+id;
        }

        layer.open({
            type:2,
            title:title,
            content: url,
            area:["650px","400px"],
            maxmin:true
        });
    }
});