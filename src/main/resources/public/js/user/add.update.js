layui.use(['form', 'layer','formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        formSelects = layui.formSelects;

    //监听表单
    form.on("submit(addOrUpdateUser)",function(data){
        //加载层
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //添加
        var url = ctx + "/user/save";
        //获取当前页面中的id
        var id = $("[name = 'id']").val();
        if(id){
            url = ctx + "/user/update";
        }

        //发送请求
        $.post(url,data.field,function(data){
            if(data.code == 200){
                //提示用户添加成功
                layer.msg("数据添加成功",{icon:6});
                //关闭加载层
                top.layer.close(index);
                //关闭添加窗口
                layer.closeAll("iframe");

                //刷新页面的营销记录
                parent.location.reload();
            }else{
                layer.msg(data.msg,{icon:5});
            }
        });



        return false;//阻止表单提交
    });


    formSelects.config('selectId',{
        type:'post',
        searchUrl:ctx + "/user/queryAllRoles?id=" + $('[name="id"]').val(),
        keyName:'roleName',
        keyVal:'id'
    },true);

    /**
     * 加载下拉框
     */
    $.post(ctx + "/user/queryAllClazz?id=" + $('[name="id"]').val(),function (data) {
        // 如果是修改操作，判断当前修改记录的指派人的值
        /*var clazz = $("input[name='clazz']").val();*/
        for (var i = 0; i < data.length; i++) {
            $("#clazz").append('<option value="'+data[i].id+'" ' + data[i].selected + '>'+data[i].clazzName+'</option>');
            /*// 当前修改记录的指派人的值 与 循环到的值 相等，下拉框则选中
            if (clazz == data[i].id) {
                $("#clazz").append('<option value="'+data[i].id+'" selected>'+data[i].uname+'</option>');
            } else {
                $("#clazz").append('<option value="'+data[i].id+'">'+data[i].uname+'</option>');
            }*/
        }
        // 重新渲染下拉框内容
        layui.form.render("select");
    });

    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });

});