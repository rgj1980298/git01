layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 用户列表展示
     */
    var  tableIns = table.render({
        elem: '#userList',
        url : ctx + '/exam/list2',
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
            {field: 'courseid', title: '课程编号', minWidth:50, align:"center"},
            {field: 'coursename', title: '课程名', minWidth:50, align:"center"},
            {field: 'time', title: '考试时间', align:'center',minWidth:150},
            {field: 'place', title: '考试地点', minWidth:100, align:'center'},
            {field: 'clazzid', title: '班级编号', minWidth:100, align:'center'},
            {field: 'clazzName', title: '班级名称', minWidth:100, align:'center'},
            {field: 'teacherid', title: '监考老师编号', align:'center'},
            {field: 'username', title: '监考老师姓名', align:'center'},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:150},
            {field: 'updateDate', title: '更新时间', align:'center',minWidth:150},

        ]]
    });


    //数据表格重载
    $("#btnSearch").click(function (){
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                id:$('[name="id"]').val(),
                courseid:$('[name="courseid"]').val(),
                clazzid:$('[name="clazzid"]').val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });

    });



});