<%@ page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<style>
    table thead th {
        text-align: center
    }
</style>




<script>
    $(function () {
        $("#table").jqGrid({
            url: "${pageContext.request.contextPath}/log/logPage",//数据路径
            styleUI: "Bootstrap",//创建表格分格
            datatype: "json",
            colNames: ["id", "管理员姓名", "操作时间", "操作内容","操作结果"],
            colModel: [
                {name: "id", align: "center"},
                {name: "name", align: "center"},
                {name: "times", align: "center"},
                {name: "event", align: "center"},
                {name: "status", align: "center"},
            ],
            autowidth: true,//自适应父容器
            pager: "#logPage",
            page: 1,
            rowNum: 4,
            rowList: [8, 15, 25, 40],
            viewrecords: true,
            height: "auto",
            editurl:"${pageContext.request.contextPath}/log/edit"
        });
        $("#table").jqGrid('navGrid', '#logPage', {edit: false, add: false, del: true, search: false},
            {},
            {},
            {
                closeAfterDelete: true,
                reloadAfterSubmit: true,
            },
            {},
            );
    });


</script>


<%--面板--%>
<div class="panel panel-primary">
    <%--    面板头--%>
    <div class="panel panel-heading">
        <h2>日志信息</h2>
    </div>
    <div>
        <button class="btn btn-primary">导出日志信息</button>
    </div>
    <br>


    <!-- Nav tabs -->

    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">日志管理</a></li>
        <%--                    <div class="input-group" style=" margin-left:900px">--%>
        <%--                        <input type="text" class="form-control" placeholder="请输入手机号" aria-describedby="basic-addon2">--%>
        <%--&lt;%&ndash;                        style="color: #1c94c4 "   发送验证码  &ndash;%&gt;--%>
        <%--                        <span class="input-group-addon" id="basic-addon2">--%>
        <%--                            <button class="btn btn-default" type="button">Go!</button>--%>
        <%--                        </span>--%>
        <%--                    </div>--%>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="home">
            <table id="table"/>
        </div>
        <%--分页工具栏--%>
        <div id="logPage"/>
    </div>
</div>
