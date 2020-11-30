<%@ page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<style>
    table thead th {
        text-align: center
    }
</style>


<script>

</script>

<%--<script>--%>

<%--    //修改用户状态--%>
<%--    function updateStatus(status, id) {--%>

<%--        let csy = confirm("确定修改用户的当前状态");--%>
<%--        if(csy==true){--%>
<%--            $.ajax({--%>
<%--                url: "${pageContext.request.contextPath}/user/updateStatus",--%>
<%--                type: "post",--%>
<%--                dataType:"json",--%>
<%--                data: {--%>
<%--                    "status" : status,--%>
<%--                    "id" : id,--%>
<%--                },--%>
<%--                success: function (result) {--%>
<%--                    if(result.status==200){--%>
<%--                        $("#table").jqGrid("clearGridData")--%>
<%--                        $("#table").trigger("reloadGrid");--%>
<%--                        alert(result.message);--%>

<%--                    }else{--%>
<%--                        alert(result.message)--%>
<%--                    }--%>
<%--                }--%>
<%--            })--%>
<%--        }--%>
<%--        &lt;%&ndash;$.post("${pageContext.request.contextPath}/user/updateStatus", {"status": status, "id": id});&ndash;%&gt;--%>
<%--        &lt;%&ndash;$("#table").trigger("reloadGrid");&ndash;%&gt;--%>
<%--    };--%>


<%--    //发送验证码--%>
<%--    $(function () {--%>
<%--        $("#sendMsg").click(function () {--%>
<%--            var phone = $("#phone").val();--%>
<%--            console.log(phone);--%>
<%--            $.ajax({--%>
<%--                type: "post",--%>
<%--                url: "${pageContext.request.contextPath}/user/sendPhoneMsg",--%>
<%--                data : {--%>
<%--                    "phone":phone--%>
<%--                },--%>
<%--                datatype: "json",--%>
<%--                success:function (result) {--%>
<%--                    if(result.status==200){--%>
<%--                        alert(result.message)--%>
<%--                    }else{--%>
<%--                        alert(result.message)--%>
<%--                    }--%>
<%--                }--%>
<%--            })--%>
<%--        })--%>
<%--    })--%>


<%--</script>--%>

<script>
    $(function () {
        $("#table").jqGrid({
            url: "${pageContext.request.contextPath}/video/videoPage",//数据路径
            styleUI: "Bootstrap",//创建表格分格
            datatype: "json",
            colNames: ["id", "名称", "视频", "上传时间", "描述", "所属类别", "类别id", "用户id"],
            colModel: [
                {name: "id", align: "center"},
                {
                    name: "title", align: "center", editable: true


                },
                {
                    name: "videoPath", align: "center", editable: true, edittype: "file",
                    formatter: function (value, option, row) {
                        return "<video controls='controls' src='"+value+"' style='height:60px'>"
                    }
                },
                {name: "uploadTime", align: "center"},
                {
                    name: "brief", align: "center", editable: true
                },
                {name: "likeCount", align: "center", editable: true},
                {name: "categoryId", align: "center"},
                {name: "userId", align: "center"}
            ],
            autowidth: true,//自适应父容器
            pager: "#userPage",
            page: 1,
            rowNum: 5,
            rowList: [2, 4, 8, 10],
            viewrecords: true,
            height: "auto",
            editurl: "${pageContext.request.contextPath}/video/edit"
        });
        $("#table").jqGrid('navGrid', '#userPage', {edit: true, add: true, del: true, search: false},
            {},//修改
            {
                closeAfterAdd: true,
                afterSubmit: function (data) {
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/video/videoUpload",
                        type: "post",
                        data: {"id": data.responseText},
                        fileElementId: "videoPath",
                        success: function () {
                            //刷新页面
                            $("#table").trigger("reloadGrid");
                        }
                    });
                    return "true";
                }
            },//添加
            {
                closeAfterDelete: true,
                reloadAfterSubmit: true,
            },
        );
    });


</script>


<%--面板--%>
<div class="panel panel-danger">
    <%--    面板头--%>
    <div class="panel panel-heading">
        <h2>视频信息</h2>
    </div>
    <%--    <div>--%>
    <%--        <button class="btn btn-success">导出用户信息</button>--%>
    <%--        <button class="btn btn-info">导入用户</button>--%>
    <%--        <button class="btn btn-warning">测试按钮</button>--%>
    <%--    </div>--%>
    <%--    <br>--%>


    <!-- Nav tabs -->

    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">视频管理</a></li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="home">
            <table id="table"/>
        </div>
        <%--分页工具栏--%>
        <div id="userPage"/>
    </div>
</div>
