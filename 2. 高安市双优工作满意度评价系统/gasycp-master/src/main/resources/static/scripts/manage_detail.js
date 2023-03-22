var unit_id='';
var role= '';
$(function(){
    //找到url传过来的键值对，获取到id和role
    function getParamer(paramer){
        var url=window.location.href.split("?")[1];            /*获取url里"?"后面的值*/
        if(url.indexOf("&")>0){                                      /*判断是否是一个参数还是多个参数*/
            var urlParamArry=url.split("&");                            /*分开每个参数，并放到数组里*/
            for(var i=0; i<urlParamArry.length; i++){
                var paramerName=urlParamArry[i].split("=");   /*把每个参数名和值分开，并放到数组里*/
                if(paramer==paramerName[0]){                     /*匹配输入的参数和数组循环出来的参数是否一样*/
                    return paramerName[1];                           /*返回想要的参数值*/
                }
            }
        }else{                                                              /*判断只有个参数*/
            var paramerValue=url.split("=")[1];
            return paramerValue;
        }
    }
    unit_id=getParamer("unit_id");
    role=getParamer("role");
    //调用初始化函数
    initTable(unit_id,role);
});

function initTable(id,role) {
    var unit_id = id;
    var role = role;
    if(role==="module")
    {
        show_module(unit_id,role);
    }
    if(role==="adm")
    {
        show_adm(unit_id,role);
    }
    if(role==="expert")
    {
        show_expert(unit_id,role);
    }
    if(role==="leader")
    {
        show_leader(unit_id,role);
    }

}
//四个初始化不同table的函数
function show_module(id,role) {
    var unit_id = id;
    var role = role;
    var new_table = unit_id+role;
    $("#table").attr("id", new_table);
    $('#' + new_table).bootstrapTable('destroy');
    $('#' + new_table).bootstrapTable({
        url: '/testList/getTestList',         //请求后台的URL（*）
        method: 'post',             //请求方式（*）
        toolbar: '#toolbar',        //工具按钮用哪个容器
        pagination: true,                   //是否显示分页（*）
        cache: false,
        dataField:"data",
        clickToSelect: true,
        showRefresh: true,                  //是否显示刷新按钮
        showPaginationSwitch: false,
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        search: false,
        queryParams: queryParams,
        responseHandler: function (res) {
            // return res.data;
            return res.data;//本地测可以，服务器测不知道是不是要换res.data
        },
        uniqueId: 'id',
        columns: [{
            field: 'module1_status', //对应json的上传状况key
            title: '工作重视上传状况',
            formatter : function (value, row, index) {
                if (row['module1_status'] === 0) {
                    return '未上传';
                }
                if (row['module1_status'] === 1) {
                    return '已上传';
                }
                return value;
            }

        }, {
            field: 'module2_status', //对应json的上传状况key
            title: '政务环境上传状况',
            formatter : function (value, row, index) {
                if (row['module2_status'] === 0) {
                    return '未上传';
                }
                if (row['module2_status'] === 1) {
                    return '已上传';
                }
                return value;
            }

        },{
            field: 'module3_status', //对应json的上传状况key
            title: '法治环境上传状况',
            formatter : function (value, row, index) {
                if (row['module3_status'] === 0) {
                    return '未上传';
                }
                if (row['module3_status'] === 1) {
                    return '已上传';
                }
                return value;
            }

        },{
            field: 'module4_status', //对应json的上传状况key
            title: '营商环境上传状况',
            formatter : function (value, row, index) {
                if (row['module4_status'] === 0) {
                    return '未上传';
                }
                if (row['module4_status'] === 1) {
                    return '已上传';
                }
                return value;
            }

        },{
            field: 'module5_status', //对应json的上传状况key
            title: '人文环境上传状况',
            formatter : function (value, row, index) {
                if (row['module5_status'] === 0) {
                    return '未上传';
                }
                if (row['module5_status'] === 1) {
                    return '已上传';
                }
                return value;
            }

        },{
            field: 'module6_status', //对应json的上传状况key
            title: '服务环境上传状况',
            formatter : function (value, row, index) {
                if (row['module6_status'] === 0) {
                    return '未上传';
                }
                if (row['module6_status'] === 1) {
                    return '已上传';
                }
                return value;
            }
        }]
    });
}
function show_adm(id,role) {
    var unit_id = id;
    var role = role;
    var new_table = unit_id+role;
    $("#table").attr("id", new_table);
    $('#' + new_table).bootstrapTable('destroy');
    $('#' + new_table).bootstrapTable({
        url: '/testList/getTestList',         //请求后台的URL（*）
        method: 'post',             //请求方式（*）
        toolbar: '#toolbar',        //工具按钮用哪个容器
        pagination: true,                   //是否显示分页（*）
        cache: false,
        dataField:"data",
        clickToSelect: true,
        showRefresh: true,                  //是否显示刷新按钮
        showPaginationSwitch: false,
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        search: false,
        queryParams: queryParams,
        responseHandler:function (res) {
            // return res.data;
            return res.data;//本地测可以，服务器测不知道是不是要换res.data
        },
        uniqueId:'id',
        columns: [{
            field: 'is_score',       //对应json的企业评分状况key
            title: '评分状况',
            formatter : function (value, row, index) {
                if (row['is_score'] === 0) {
                    return '未评分';
                }
                if (row['is_score'] === 1) {
                    return '已评分';
                }
                return value;
            },
        },{
            field: 'login_name',
            title: '所属园区/街道'
        },{
            field: 'username',              //对应json的企业模块key
            title: '登录账号'
        }]
    });
}
function show_expert(id,role) {
    var unit_id = id;
    var role = role;
    var new_table = unit_id+role;
    $("#table").attr("id", new_table);
    $('#' + new_table).bootstrapTable('destroy');
    $('#' + new_table).bootstrapTable({
        url: '/testList/getTestList',         //请求后台的URL（*）
        method: 'post',             //请求方式（*）
        toolbar: '#toolbar',        //工具按钮用哪个容器
        pagination: true,                   //是否显示分页（*）
        cache: false,
        dataField:"data",
        clickToSelect: true,
        showRefresh: true,                  //是否显示刷新按钮
        showPaginationSwitch: false,
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        search: false,
        queryParams: queryParams,
        responseHandler:function (res) {
            // return res.data;
            return res.data;//本地测可以，服务器测不知道是不是要换res.data
        },
        uniqueId:'id',
        columns: [{
            field: 'is_score',      //对应json的专家评分状况key
            title: '评分状况',
            formatter : function (value, row, index) {
                if (row['is_score'] === 0) {
                    return '未评分';
                }
                if (row['is_score'] === 1) {
                    return '已评分';
                }
                return value;
            },
        },{
            field: 'username',              //对应json的专家名称key
            title: '专家登录账号'
        }]
    });
}
function show_leader(id,role) {
    var unit_id = id;
    var role = role;
    var new_table = unit_id+role;
    $("#table").attr("id", new_table);
    // $('#' + new_table).bootstrapTable('destroy');
    $('#' + new_table).bootstrapTable({
        url: '/testList/getTestList',         //请求后台的URL（*）
        method: 'post',             //请求方式（*）
        toolbar: '#toolbar',        //工具按钮用哪个容器
        pagination: true,                   //是否显示分页（*）
        cache: false,
        dataField:"data",
        clickToSelect: true,
        showRefresh: true,
        searchOnEnterKey: true,//是否显示刷新按钮
        showPaginationSwitch: false,
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,
        queryParamsType:'',//每页的记录行数（*）
        //  search: false,
        queryParams: queryParams,
        responseHandler:function (res) {
            // return res.data;
            return res.data;//本地测可以，服务器测不知道是不是要换res.data
        },
        uniqueId:'id',
        columns: [{
            field: 'is_score',      //对应json的领导评分状况key
            title: '评分状况',
            formatter : function (value, row, index) {
                if (row['is_score'] === 0) {
                    return '未评分';
                }
                if (row['is_score'] === 1) {
                    return '已评分';
                }
                return value;
            },
        },
        {
            field: 'detail_info',      //对应json的领导所在单位key
            title: '所在单位'
        },{
            field: 'login_name',      //对应json的领导名称key
            title: '领导姓名'
        }, {
            field: 'username',      //对应json的领导手机号码key
            title: '手机号码'
        }]
    });

}
function queryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        // limit: params.limit,   //页面大小
        // org_id: orgInfo.org_id, //页码
        org_id: unit_id, //前端测试所用
        role:role
    };
    return temp;
}
