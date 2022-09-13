layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 营销机会列表展示
     */
    var  tableIns = table.render({  //返回表格渲染的唯一标识
        elem: '#commentList', // 表格绑定的ID
        url : ctx + '/comment/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "commentListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'studentName', title: '学生名字',align:"center"},//chanceSource
            {field: 'teacherName', title: '老师名字',  align:'center'},//customerName
            {field: 'description', title: '吐槽内容', align:'center'},//cgjl
            {title: '操作', templet:'#commentListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });


    //数据表格重载
    $("#btnSearch").click(function (){
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                studentName:$('[name="studentName"]').val(),
                teacherName:$('[name="teacherName"]').val(),
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });


    /**
     * 监听表格的头部工具栏
     */
    //监听事件
    table.on('toolbar(comments)', function(obj){
        console.log(obj.config.id);
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


    //批量删除
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
                url: ctx+"/comment/deleteBatch",
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


    /**
     * 监听表格的行工具栏
     */
    //监听事件
    table.on('tool(comments)', function(obj){
        console.log(obj.data.id);
        if(obj.event == "edit"){
            openAddOrUpdateDialog(obj.data.id);
        }else if(obj.event == "del"){
            // 询问是否确认删除
            layer.confirm("确定要删除这条记录吗？", {icon: 3, title:"吐槽管理"}, function (index) {
                // 关闭窗口
                layer.close(index);
                // 发送ajax请求，删除记录
                $.ajax({
                    type:"post",
                    url: ctx + "/comment/deleteBatch",
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



    /**
     * 打开添加修改的窗口页面
     */
    function openAddOrUpdateDialog(id){
        var title = "<h2>别说了,拔刀吧.</h2>";
        var url = ctx + "/comment/toAddUpdatePage";

        //通过参数id判断目前是修改还是添加操作
        if(id){
            title = "<h2>吐槽</h2>";
            url += "?id="+id;
        }

        //打开修改添加页面
        layer.open({
            type:2,   //ifame
            title:title,
            content: url,   //页面的内容
            area:['500px','400px'], //设置宽高
            maxmin:true //可以伸缩页面大小
        });
    }
});