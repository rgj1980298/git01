layui.use(['form', 'layer','formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        formSelects = layui.formSelects,
        $ = layui.jquery;

    //加载下拉框  授课教师
    $.post(ctx+"/clazz/queryAllTeachers",function (data){
        //获取下拉框
        var am = $("#teacher");
        // <#--获取当前数据的教师id-->
        var aid = $("#teacherId").val();
        if(data != null){
            for(var i = 0; i < data.length; i++){
                // 回显当前数据的指派人
                if(aid == data[i].teacherName){
                    var opt = "<option selected value="+data[i].id+">"+data[i].teacherName+"</option>";
                }else{
                    var opt = "<option value="+data[i].id+">"+data[i].teacherName+"</option>";
                }
                am.append(opt);
            }
        }
        // 重新渲染下拉框内容
        layui.form.render("select");
    });

    /**
     * 监听表单的提交
     */
    form.on('submit(addOrUpdateUser)',function (data){
        // 提交数据时的加载层 （https://layer.layui.com/）
        var index = layer.msg("数据提交中,请稍后...",{
            icon:16, // 图标
            time:false, // 不关闭
            shade:0.8 // 设置遮罩的透明度
        });
        var url=ctx+"/clazz/add";

        //判断修改，还是添加操作
        if($("[name='id']").val()){
            url=ctx+"/clazz/update"
        }

        //发送请求
        $.post(url,{
            "id": data.field.id.replace(",",""),
            "clazzName": data.field.clazzName,
            "teacherId": data.field.teacherId,
            "num": data.field.num
        },function (data){
            if(data.code == 200){
                layer.msg("操作成功！")
                //关闭弹出框
                layer.close(index);
                //关闭iframe层
                layer.closeAll("iframe");
                //刷新父页面，将添加的新数据展示
                parent.location.reload();
            } else {
                layer.msg(data.msg,{icon:5})
            }
        });

        return false;//阻止表单提交
    })




    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    })

});