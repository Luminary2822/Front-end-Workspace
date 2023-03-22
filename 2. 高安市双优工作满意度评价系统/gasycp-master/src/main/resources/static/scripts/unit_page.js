function grades_alert()
{
    alert("你好，评分环节还未结束，目前无法查看得分详情，请耐心等待!");
}
var  arr=["工作重视","政务环境","法治环境","营商环境","人文环境","服务环境"];

$(function() {
    var  $selectParent=$("#queryMaterial");
    for(var i=0,j=1;i<6;i++,j++) {
        var $option=$("<option>"+arr[i]+"</option>");
        $option.val(arr[i]+j);
        $selectParent.append($option);
    }

});

//声明三个模块等级的全局变量
var     module1='';
//文件上传
//bootstrap-fileinput的使用时，首先要初始化
$(function () {
    initFileInput("input-id");
});
//初始化上传的fileinput控件
function initFileInput(ctrlName) {
    var control = $('#' + ctrlName);
    control.fileinput({
        language: 'zh', //设置语言
        uploadUrl: "/upload/uploadBlog", //上传的地址
        allowedFileExtensions: ['pdf'],//接收的文件后缀
        // maxFilesNum : 1,//上传最大的文件数量
        maxFileCount : 1,
        uploadAsync: true, //默认异步上传
        showUpload: true, //是否显示上传按钮
        showRemove :true, //显示移除按钮
        showPreview : true, //是否显示预览
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        enctype: 'multipart/form-data',
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "一个模块只允许上传一份文件！",
        uploadExtraData : function() {  //传递参数
            return {type:'type'};
        }

    }).on('filepreupload', function(event, data, previewId, index) {     //上传中
        var form = data.form, files = data.files, extra = data.extra,
            response = data.response, reader = data.reader;
        console.log('文件正在上传');
    }).on("fileuploaded", function (event, data, previewId, index) {
        //一个文件上传成功
        console.log('文件上传成功！'+data.id);

        //通过ctrlName: 'input-id_1'格式拆模块等级
        module1="TYPE_ONE_MODULE"+ctrlName.charAt(9);
        $.ajax({
            url: "/upload/uploadData", //登录接口
            type: "post",
            contentType: "application/json",
            data:JSON.stringify({
                org_id:orgInfo.org_id,
                file_name:'',
                file_url:'',
                create_at:'',
                one_type:module1
                // two_type:
                // tree_type:
            }),
            dataType: "json",
            success: function (res) {
                if (res.code !== 200) {
                    alert("此模块已有文件");
                    return false;
                }
            },
            error: function () {
                alert("此模块已有文件");
            }
        });

    }).on('fileerror', function(event, data, msg) {  //一个文件上传失败
        console.log('文件上传失败！'+data.id);
    });

}
$('#input-id').on('filesuccessremove', function(event, id) {
    if (some_processing_function(id)) {
        console.log('Uploaded thumbnail successfully removed');
    } else {
        return false;
    }
});


//材料一
function showModule() {
    var obj_m = document.getElementById('queryMaterial');
    var index_m = obj_m.selectedIndex;
    var option = $("#queryMaterial option:selected");

    if (!document.getElementById("module_" + index_m )) {
        $("#module").after("            <div class=\"module_upload\" id=\"module_\" style=\"display: none\">\n" +
            "                <form id=\"form\" action=\"\" method=\"post\" enctype=\"multipart/form-data\" style=\"width: 100%;height: 180px;\">\n" +
            "                    <div class=\"form-group\" style=\"width: 100%;height: 180px;\">\n" +
            "                        <input id=\"input-id_\" name=\"file\" multiple type=\"file\" data-show-caption=\"true\" class=\"file-loading\" accept=\".pdf\">\n" +
            "                    </div>\n" +
            "                </form>\n" +
            "            </div>");
        $("#module_").attr('id', 'module_' + index_m );
        $("#input-id_").attr('id', 'input-id_' + index_m );
        initFileInput("input-id_" + index_m);
        $("#module_" + index_m).show();
        $("#module_" + index_m).siblings().hide();
    }
    if (document.getElementById("module_" + index_m)) {
        $("#module_" + index_m).show();
        $("#module_" + index_m).siblings().hide();
    }
    if (option.text() === "请选择材料模块")  {
        $("#module").show();
        $("#module").siblings().hide();
    }
}

/*********************点击已上传切换文件列表*************************/
// var module_one = '';
function showList1() {
    // module_one = id;
    // var lastnum = id.charAt(id.length-1);
     $('#file-table').bootstrapTable('refresh');
    if (document.getElementById("list_change").innerHTML==='已上传')
    {

        document.getElementById("list_change").innerHTML='继续上传';
        // document.getElementById("queryMaterial").style.display='none';
        document.getElementById("module_upload").style.display='none';
        document.getElementById("file_list").style.display='block';
    }
   else {
        document.getElementById("list_change").innerHTML='已上传';
        // document.getElementById("queryMaterial").style.display='block';
        document.getElementById("module_upload").style.display='block';
        document.getElementById("file_list").style.display='none';
    }
}
/*********************文件列表*************************/
$(function () {
    initTable();
});
function list_remove() {
    var ids = $.map($('#file-table').bootstrapTable('getSelections'), function (row) {
        return row.id;
    });
    if (ids.length != 1 ) {
        alert("请选择一行删除!");
        return;
    }
    $('#file-table').bootstrapTable('remove', {
        field: 'id',
        values: ids
    });
    $.ajax({
        type: "POST",
        url: "/org/deletedFile",
        data: {
            id:ids[0],
            org_id:orgInfo.org_id,
            one_type:module1
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

function initTable(){
    $("#file-table").bootstrapTable({
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
//增加预览效果
function operateFormatter(value, row, index) {//赋予的参数
    var result = '<a href="'+row.file_url+ '" class="btn btn-xs green title="预览" target="_blank"><span class="glyphicon glyphicon-search"></span></a>';

    return result;
}

