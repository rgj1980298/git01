layui.use(['form', 'layer','laydate'], function () {
    var form = layui.form,

        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#beginTime' //指定元素
    });

    //常规用法
    laydate.render({
        elem: '#planDate'
    });


    /**
     * 添加或修改课程
     */
    form.on("submit(addOrUpdateCourseKind)", function (data) {
        // 弹出loading层
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        var url = ctx+"/course_kind/add";

        if($('[name="id"]').val()){
            url = ctx+"/course_kind/update";
        }

        $.post(url,data.field,function (data){
            if(data.code == 200){
                //关闭弹出框
                layer.close(index);
                //关闭iframe层
                layer.closeAll("iframe");
                //刷新父页面，将添加的新数据展示
                parent.location.reload();
            }else{
                layer.msg(data.msg,{icon:5})
            }
        });
        return false; //阻止表单提交
    });

    /**
     * 关闭弹出层
     */
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });

});