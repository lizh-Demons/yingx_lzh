<%@ page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<style>
    table thead th {
        text-align: center
    }
</style>


<script>

</script>

<script>

    //修改用户状态
    function updateStatus(status, id) {

        let csy = confirm("确定修改用户的当前状态");
        if(csy==true){
            $.ajax({
                url: "${pageContext.request.contextPath}/user/updateStatus",
                type: "post",
                dataType:"json",
                data: {
                    "status" : status,
                    "id" : id,
                },
                success: function (result) {
                    if(result.status==200){
                        $("#table").jqGrid("clearGridData")
                        $("#table").trigger("reloadGrid");
                        alert(result.message);

                    }else{
                        alert(result.message)
                    }
                }
            })
        }
        <%--$.post("${pageContext.request.contextPath}/user/updateStatus", {"status": status, "id": id});--%>
        <%--$("#table").trigger("reloadGrid");--%>
    };


    //发送验证码
    $(function () {
        $("#sendMsg").click(function () {
            var phone = $("#phone").val();
            console.log(phone);
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/user/sendPhoneMsg",
                data : {
                    "phone":phone
                },
                datatype: "json",
                success:function (result) {
                    if(result.status==200){
                        alert(result.message)
                    }else{
                        alert(result.message)
                    }
                }
            })
        });

        $("#dervie").click(function () {
            $.get("${pageContext.request.contextPath}/user/export",function (data) {
                if(data.message=='success'){
                    alert("导出成功")
                }else{
                    alert("导出失败")
                }
            })
        });

        <%--"${pageContext.request.contextPath}/user/import", data--%>
        $("#lead").click(function () {
            $.get("${pageContext.request.contextPath}/user/import",function (data) {
                if(data.message=='success'){
                    alert("导入成功")
                }else{
                    alert("导入失败")
                }
            })
        });


    })

    //到处用户信息


</script>

<script>
    $(function () {
        $("#table").jqGrid({
            url: "${pageContext.request.contextPath}/user/showPage",//数据路径
            styleUI: "Bootstrap",//创建表格分格
            datatype: "json",
            colNames: ["id", "头像", "名字", "密码", "状态", "手机号", "注册时间"],
            colModel: [
                {name: "id", align: "center"},
                {
                    name: "picImg", align: "center",
                    formatter: function (value, option, row) {
                        return "<img src='${pageContext.request.contextPath}/" + value + "' style='height:60px'>"
                    }
                },
                {name: "nickName", align: "center"},
                {name: "password", align: "center"},
                {
                    name: "status", align: "center",
                    formatter: function (value, option, row) {
                        if (value == "0") {
                            return "<button class='btn btn-danger' onclick='updateStatus(\"" + value + "\",\"" + row.id + "\")'>冻结</button>"
                        }
                        return "<button class='btn btn-warning' onclick='updateStatus(\"" + value + "\",\"" + row.id + "\")'>正常</button>"
                    }
                },
                {name: "phone", align: "center"},
                {name: "createDate", align: "center"}
            ],
            autowidth: true,//自适应父容器
            pager: "#userPage",
            page: 1,
            rowNum: 4,
            rowList: [2, 4, 8, 10],
            viewrecords: true,
            height: "auto",
        });
        $("#table").jqGrid('navGrid', '#userPage', {edit: false, add: false, del: false, search: false});
    });


</script>


<%--面板--%>
<div class="panel panel-danger">
    <%--    面板头--%>
    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>
    <div>
        <button class="btn btn-success" id="dervie">导出用户信息</button>
        <button class="btn btn-info" id="lead" >导入用户信息</button>
    </div>
    <br>


    <!-- Nav tabs -->

    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">用户管理</a></li>
        <%--                    <div class="input-group" style=" margin-left:900px">--%>
        <%--                        <input type="text" class="form-control" placeholder="请输入手机号" aria-describedby="basic-addon2">--%>
        <%--&lt;%&ndash;                        style="color: #1c94c4 "   发送验证码  &ndash;%&gt;--%>
        <%--                        <span class="input-group-addon" id="basic-addon2">--%>
        <%--                            <button class="btn btn-default" type="button">Go!</button>--%>
        <%--                        </span>--%>
        <%--                    </div>--%>
        <div class="input-group" style=" margin-left:900px">
            <input type="text" class="form-control" placeholder="请输入手机号" id="phone" name="phone">
            <span class="input-group-btn">
                        <button class="btn btn-default" type="button" id="sendMsg"><span
                                style="color: #1c94c4 ">获取验证码</span></button>
                    </span>
        </div><!-- /input-group -->
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
