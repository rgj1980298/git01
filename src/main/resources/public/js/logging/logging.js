layui.use(['form','table','layer'],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
       form=layui.form,
       flag = true;

    //角色列表展示
    var  tableIns = table.render({
        elem: '#loggingList',
        url : ctx+'/logging/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        /*toolbar: "#toolbarDemo",*/
        id : "loggingListTable",
        cols : [[
            /*{type: "radio", fixed:"left", width:50},*/
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'userName', title: '用户名', minWidth:50, align:"center"},
            /*{field: 'moduleName', title: '模块操作', minWidth:100, align:'center'},*/
            {field: 'moduleName', title: '模块操作', width: 150, align: 'center', unresize: true, sort: true, templet: function(c){
                return "<a href=\"#\" class=\"moduleName\">"+ c.moduleName +"</a>"
                }},
            /*{field: 'createDate', title: '创建时间', align:'center',minWidth:150},*/
            {field: 'updateDate', title: '操作时间', align:'center',minWidth:150},
            {title: '操作', minWidth:150, templet:'#loggingListBar',fixed:"right",align:"center"}
        ]]
    });

    form.on('switch(sexDemo)', function(e){
        flag = e.elem.checked;
        console.log(e.elem.checked);
    });

    // 多条件搜索
    $(".search_btn").on("click",function () {
        table.reload("loggingListTable",{
            page:{
                curr:1
            },
            where:{
                // 角色名
                userName:$("input[name='userName']").val(),
                moduleName:$("input[name='moduleName']").val(),
                status: flag
            }
        })
    });

    /*// 头工具栏事件
    table.on('toolbar(roles)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case "add":
                openAddOrUpdateRoleDialog();
                break;
            case "grant":
                openAddGrantDialog(checkStatus.data);
                break;
        };
    });*/


    table.on('tool(loggings)',function (obj) {
        var layEvent =obj.event;
        /*if(layEvent === "edit"){
            openAddOrUpdateRoleDialog(obj.data.id);
        }else */
        if(layEvent === "del"){
            layer.confirm("确认删除当前记录?",{icon: 3, title: "角色管理"},function (index) {
                $.post(ctx+"/logging/delete",{id:obj.data.id},function (data) {
                    if(data.code==200){
                        layer.msg("删除成功");
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg);
                    }
                })
            })
        }
    });


/*    function openAddOrUpdateRoleDialog(id) {
        var title="角色管理-角色添加";
        var url=ctx+"/role/addOrUpdateRolePage";
        if(id){
            title="角色管理-角色更新";
            url=url+"?id="+id;
        }
        layui.layer.open({
            title:title,
            type:2,
            area:["700px","500px"],
            maxmin:true,
            content:url
        })
    }*/


    /*function openAddGrantDialog(datas) {
        if(datas.length==0){
            layer.msg("请选择待授权角色记录!", {icon: 5});
            return;
        }
        if(datas.length>1){
            layer.msg("暂不支持批量角色授权!", {icon: 5});
            return;
        }
        var url = ctx+"/role/toAddGrantPage?roleId="+datas[0].id;
        var title="角色管理-角色授权";
        layui.layer.open({
            title : title,
            type : 2,
            area:["600px","280px"],
            maxmin:true,
            content : url
        });
    }*/



});
