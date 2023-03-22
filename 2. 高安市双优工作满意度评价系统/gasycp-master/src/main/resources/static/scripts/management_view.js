$(function () {
    initTable();
    $('#management-table').bootstrapTable('refresh');
});
function initTable(){
    $("#management-table").bootstrapTable({
        height:300,
        method: 'post',
        toolbar: '#toolbar',    //工具按钮用哪个容器
        striped: true,      //是否显示行间隔色
        cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,     //是否显示分页（*）
        sortable: false,      //是否启用排序
        sortOrder: "asc",     //排序方式
        pageNumber:1,      //初始化加载第一页，默认第一页
        pageSize: 4,      //每页的记录行数（*）
        //pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）
        pageList: [4],  //可供选择的每页的行数（*）
        url: "/org/OrgFilesTable",//这个接口需要处理bootstrap table传递的固定参数
        queryParamsType:'', //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
        // 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber

        queryParams: queryParams,//前端调用服务时，会默认传递上边提到的参数，如果需要添加自定义参数，可以自定义一个函数返回请求参数
        sidePagination: "client",   //分页方式：client客户端分页，server服务端分页（*）
        //search: true,      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        //showColumns: true,     //是否显示所有的列
        showRefresh: true,     //是否显示刷新按钮
        minimumCountColumns: 2,    //最少允许的列数
        clickToSelect: true,    //是否启用点击选中行
        searchOnEnterKey: true,
        columns: [
            {
                checkbox: true,
                visible: true                  //是否显示复选框
            },
            {
                title: 'ID',
                field: 'id',
                align: 'center',
            },
            {
                field: 'file_name',
                title: '文件名称',
                align: 'center'
            }, {
                field: 'create_at',
                title: '上传时间',
                align: 'center'
            }, {
                field: 'tree_type',
                title: '所属模块',
                align: 'center'
            }, {
                field: 'operation',
                title: '操作',
                align: 'center',
                formatter: operateFormatter //自定义方法，添加操作按钮
            }
        ],
        responseHandler: function(data){
            return data.rows;
        },

    });

}
function queryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        // limit: params.limit,   //页面大小
        // org_id: orgInfo.org_id, //页码
        org_id: orgInfo.org_id, //前端测试所用
        one_type:module1
    };
    return temp;
}
//删除操作
function DeleteByIds(id) {
    var ids =id;
    $('#management-table').bootstrapTable('remove', {
        field: 'id',
        values: ids
    });
    $.ajax({
        type: "POST",
        url: "/wadmin/ad/deleteAd",
        data: {
            adId: row['adId']
        },
        dataType: 'JSON',
        success: function (data) {
            if (data.result != 0) {
                toastr.info("info", data.message);
                return;
            }
            toastr.success("success", '删除成功');
            $('#file-table').bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
        }
    });
}

//增加预览、删除效果
function operateFormatter(value, row, index) {//赋予的参数
    var result = "";
    result += "<a href=\"#?id = '+row.id +'\" class='btn btn-xs green' title='预览'><span class='glyphicon glyphicon-search'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"DeleteByIds('" +row.id + "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
    return result;
}