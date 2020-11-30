<%@ page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<style>
    table thead th {
        text-align: center
    }
</style>




<script>
    $(function () {
        $("#table").jqGrid({
            url: "${pageContext.request.contextPath}/feedback/feedbackPage",//数据路径
            styleUI: "Bootstrap",//创建表格分格
            datatype: "json",
            colNames: ["id", "标题", "内容", "用户id","反馈时间"],
            colModel: [
                {name: "id", align: "center"},
                {name: "title", align: "center"},
                {name: "content", align: "center"},
                {name: "userId", align: "center"},
                {name: "feedbackTime", align: "center"},
            ],
            autowidth: true,//自适应父容器
            pager: "#feedbackPage",
            page: 1,
            rowNum: 2,
            rowList: [8, 15, 25, 40],
            viewrecords: true,
            height: "auto",
        });
        $("#table").jqGrid('navGrid', '#feedbackPage', {edit: false, add: false, del: false, search: false});
    });


</script>


<%--面板--%>
<div class="panel panel-warning">
    <%--    面板头--%>
    <div class="panel panel-heading">
        <h2>反馈信息</h2>
    </div>
    <div>
        <button class="btn btn-warning">导出反馈信息</button>
        <button class="btn btn-warning">测试按钮</button>
    </div>
    <br>


    <!-- Nav tabs -->

    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">反馈管理</a></li>
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
        <div id="feedbackPage"/>
    </div>
</div>
