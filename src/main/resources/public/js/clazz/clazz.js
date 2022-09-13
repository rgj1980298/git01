layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        form=layui.form
    /**
     * 用户列表展示
     */
    var  tableIns = table.render({
        elem: '#userList',
        url : ctx+'/clazz/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'班级编号',fixed:"true", width:100},
            {field: 'clazzName', title: '班级名称', minWidth:100, align:'center'},
            {field: 'teacherName', title: '任课教师', minWidth:100, align:'center'},
            {field: 'mode', title: '学生状态', width: 150, align: 'center', unresize: true, sort: true, templet: function(c){
                    let acc
                    console.log(c)
                    if(c.mode) {
                        acc="<input type=\"checkbox\" name=\"mode\" lay-skin=\"switch\" checked lay-text=\"已到齐|未到齐\" lay-filter=\"sexDemo\" >"
                    }
                    else {
                        acc = "<input type=\"checkbox\" name=\"mode\" lay-skin=\"switch\" lay-text=\"已到齐|未到齐\" lay-filter=\"sexDemo\" >"
                    }
                    return acc
                }},
            {field: 'num', title: '班级人数', minWidth:100, align:'center'},
            {field: 'status', title: '自习状态', width: 170, align: 'center', unresize: true, sort: true, templet: function(d){
                let txt
                    console.log()
                if(d.status) {
                    txt="<input type=\"checkbox\" name=\"status\" checked title=\"自习中\" lay-filter=\"lockDemo\" >"
                }
                else {
                    txt = "<input type=\"checkbox\" name=\"status\" title=\"自习中\" lay-filter=\"lockDemo\" >"
                }
                    return txt
                }},
            {title: '操作', minWidth:150, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });

    /*条件查询*/
    $(".search_btn").click(function(){

        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                id: $("input[name='userName']").val(),
                clazzName: $("input[name='clazzName']").val(),
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    /*绑定头部工具栏*/
    //触发事件
    table.on('toolbar(users)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'add':
                openAddOrUpdateUserDialog();
                break;
            case 'del':
                // layer.msg('删除');
                deleteUser(checkStatus.data);
                break;
        }
    });


    /**
     * 删除
     */
    function  deleteUser(datas){
        if(datas.length==0){
            layer.msg("请选择数据");
            return ;
        }
        layer.confirm("你确定要删除这些数据?",{
            btn:["确认","取消"],
        },function(index){
            //关闭确认框
            layer.close(index);
            //收集数据
            var ids=[];
            //遍历
            for(var x in datas){
                ids.push(datas[x].id);
            }
            /*发送ajax删除*/
            $.ajax({
                type:"post",
                url:ctx+"/clazz/delete",
                data:{"ids":ids.toString()},
                success:function (result){
                    if(result.code==200){
                        layer.msg("删除成功了",{icon:6});
                        //刷新一下
                        parent.location.reload();
                    }else{
                        //提示一下
                        layer.msg(result.msg,{icon:5});
                    }
                }
            });
        });
    }

    function  openAddOrUpdateUserDialog(id){

        var title="<h2>课程管理--添加</h2>";
        var url=ctx+"/clazz/toAddOrUpdatePage";
        //判断添加还是修改

        if(id){
            title="<h2>用户模块--学生人数更新</h2>";
            url=url+"?id="+id;
        }

        layer.open({
            title:title,
            type:2,//iframe
            content:url,
            maxmin:true,
            area:["550px","420px"]
        });
    }

    /*绑定行内工具栏*/

    table.on('tool(users)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if(layEvent === 'edit'){ //查看
            openAddOrUpdateUserDialog(data.id);
        } else if(layEvent === 'del'){ //删除
            layer.confirm('真的删除么', function(index){

                layer.close(index);
                //向服务端发送删除指令
                $.ajax({
                    type:"post",
                    url:ctx+"/clazz/delete",
                    data:{"ids":data.id},
                    success:function (result){
                        if(result.code==200){
                            layer.msg("删除成功了",{icon:6});
                            //刷新一下
                            parent.location.reload();
                        }else{
                            //提示一下
                            layer.msg(result.msg,{icon: 5 });
                        }
                    }
                });
            });
        }
    });

    //监听自习状态
    form.on('checkbox(lockDemo)', function(e){

        var id = e.elem.parentNode.parentNode.parentElement.childNodes[1].innerText
        var status = this.checked;

         $.ajax({
             type: "get",
             url: "updateStatus",
             data: {
                 "id": id,
                 "status":status
             },
             cache: false,
             async: true,
             success: function (data) {
                 if (data.code == 200){
                     layer.msg('修改成功', { icon: 1, offset: "t", time:2000 });
                 }else{
                     layer.msg(data.msg, { icon: 1, offset: "t", time:2000 });
                     $(e.elem).prop("checked", !$(e.elem).prop("checked"));
                     layui.form.render();
                 }
             },
             error: function () {
                 layer.msg('修改失败', { icon: 2, offset: "t", time:2000 });
             }
         });
    });

    // 监听学生到齐状态
    form.on('switch(sexDemo)', function(c){
        console.log(c)
        var id = c.elem.parentNode.parentNode.parentElement.childNodes[1].innerText
        var mode = this.checked;

        $.ajax({
            type: "get",
            url: "mode",
            data: {
                "id": id,
                "mode":mode
            },
            cache: false,
            async: true,
            success: function (data) {
                if (data.code == 200){
                    layer.msg('已修改', { icon: 1, offset: "t", time:2000 });
                }else{
                    layer.msg(data.msg, { icon: 1, offset: "t", time:2000 });
                    $(c.elem).prop("checked", !$(c.elem).prop("checked"));
                    layui.form.render();
                }
            },
            error: function () {
                layer.msg('修改失败', { icon: 2, offset: "t", time:2000 });
            }
        });
    });
});

