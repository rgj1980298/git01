layui.use(['form', 'layer','formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    formSelects = layui.formSelects;

    /**
     * 加载下拉框数据
     */

    var adad=$("#adad").val();
    if(adad==-1){
        var clazzid;
        $.post(ctx+"/score/queryAllClazzid",function (data){
            console.log(data);
            var am = $("#clazzid");
            var clazzid = $("#clazzId").val();
            var count=0;
            console.log(clazzid);
            if(data != null){
                for(var i = 0; i < data.length; i++){
                    if(clazzid==data[i].clazzid){
                        var opt = "<option selected value="+data[i].clazzid+">"+data[i].clazzid+"</option>";
                    }else {
                        var opt = "<option value="+data[i].clazzid+">"+data[i].clazzid+"</option>";
                        count++;
                    }

                    am.append(opt);
                }

            }

            // 重新渲染下拉框内容
            layui.form.render("select");
        });

        var studentid;
        $.post(ctx+"/score/queryAllStudentid",function (data){
            console.log(data);
            var am = $("#studentid");
            var studentid = $("#studentId").val();
            var count=0;
            if(data != null){
                for(var i = 0; i < data.length; i++){
                    if(studentid==data[i].studentid){
                        var opt = "<option selected value="+data[i].studentid+">"+data[i].studentid+"</option>";
                    }else {
                        var opt = "<option value="+data[i].studentid+">"+data[i].studentid+"</option>";
                        count++;
                    }

                    am.append(opt);
                }

            }

            // 重新渲染下拉框内容
            layui.form.render("select");
        });

        var coursename;
        $.post(ctx+"/score/queryAllCourse",function (data){
            console.log(data);

            var am = $("#coursename");
            var coursename = $("#courseName").val();
            var count=0;
            if(data != null){
                for(var i = 0; i < data.length; i++){
                    if(coursename==data[i].coursename){
                        var opt = "<option selected value="+data[i].coursename+">"+data[i].coursename+"</option>";
                    }else{
                        var opt = "<option value="+data[i].coursename+">"+data[i].coursename+"</option>";
                        count++;
                    }

                    am.append(opt);
                }


            }

            // 重新渲染下拉框内容
            layui.form.render("select");
        });
    }else{
        var clazzid;
        $.post(ctx+"/score/queryAllClazzid",function (data){
            console.log(data);
            var am = $("#clazzid");
            var clazzid = $("#clazzId").val();
            var count=0;
            console.log(clazzid);
            if(data != null){
                for(var i = 0; i < data.length; i++){
                    if(clazzid==data[i].clazzid){
                        var opt = "<option selected value="+data[i].clazzid+">"+data[i].clazzid+"</option>";
                    }else {
                        var opt = "<option value="+data[i].clazzid+">"+data[i].clazzid+"</option>";
                        count++;
                    }

                    am.append(opt);
                }
                if(count== data.length){
                    var opt = "<option selected value="+"该班级已经删除"+">"+"该班级已经删除"+"</option>";

                    am.append(opt);
                }
            }

            // 重新渲染下拉框内容
            layui.form.render("select");
        });

        var studentid;
        $.post(ctx+"/score/queryAllStudentid",function (data){
            console.log(data);
            var am = $("#studentid");
            var studentid = $("#studentId").val();
            var count=0;
            if(data != null){
                for(var i = 0; i < data.length; i++){
                    if(studentid==data[i].studentid){
                        var opt = "<option selected value="+data[i].studentid+">"+data[i].studentid+"</option>";
                    }else {
                        var opt = "<option value="+data[i].studentid+">"+data[i].studentid+"</option>";
                        count++;
                    }

                    am.append(opt);
                }
                if(count== data.length){
                    var opt = "<option selected value="+"该学号已经删除"+">"+"该学号已经删除"+"</option>";

                    am.append(opt);
                }
            }

            // 重新渲染下拉框内容
            layui.form.render("select");
        });

        var coursename;
        $.post(ctx+"/score/queryAllCourse",function (data){
            console.log(data);

            var am = $("#coursename");
            var coursename = $("#courseName").val();
            var count=0;
            if(data != null){
                for(var i = 0; i < data.length; i++){
                    if(coursename==data[i].coursename){
                        var opt = "<option selected value="+data[i].coursename+">"+data[i].coursename+"</option>";
                    }else{
                        var opt = "<option value="+data[i].coursename+">"+data[i].coursename+"</option>";
                        count++;
                    }

                    am.append(opt);
                }
                if(count== data.length){
                    var opt = "<option selected value="+"该课程已经删除"+">"+"该课程已经删除"+"</option>";

                    am.append(opt);
                }

            }

            // 重新渲染下拉框内容
            layui.form.render("select");
        });
    }







    /**
     * 监听表单的提交
     */
    form.on("submit(addOrUpdateUser)",function (data){
        console.log(data.field);

        var index = layer.msg("数据提交中,请稍后...",{
            icon:16, // 图标
            time:false, // 不关闭
            shade:0.8 // 设置遮罩的透明度
        });

        var url = ctx+"/score/save";
        var id = $('[name="id"]').val();
        console.log(id);
        //如果进入判断则是修改
        if(id){
            url = ctx+"/score/update?id="+id;
        }

        //发送请求
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

        return false;
    });


    form.verify({
        operationCount: [
            /^[+]{0,1}(\d+)$/,
            '成绩限制必须为正整数！'
        ]

    });
});