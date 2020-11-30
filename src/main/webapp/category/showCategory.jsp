<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<style>
    table thead th {
        text-align: center
    }
</style>

<script>
    $(function () {
        pageInit();
    });

    function pageInit() {
        $("#cateTable").jqGrid({
            url: "${path}/category/firstCategory",
            datatype: "json",
            page: 1,
            rowNum: 4,
            rowList: [8, 10, 20, 30],
            pager: '#catePage',
            sortname: 'id',
            viewrecords: true,
            styleUI: "Bootstrap",
            autowidth: true,
            height: "auto",
            editurl: "${path}/category/edit",
            colNames: ['id', '名称', '级别', '父级别id'],
            colModel: [
                {name: 'id', index: 'id', align: 'center'},
                {name: 'cateName', index: 'invdate', width: 90, align: 'center', editable: true},
                {name: 'levels', index: 'name', width: 100, align: 'center'},
                {name: 'parentId', index: 'amount', width: 80, align: "right", align: 'center'}
            ],
            subGrid: true,  //开启子表格
            // subgrid_id:是在创建表数据时创建的div标签的ID
            //row_id是该行的ID
            subGridRowExpanded: function (subgrid_id, row_id) {
                addSubGrid(subgrid_id, row_id);
            }
        });
        $("#cateTable").jqGrid('navGrid', '#catePage', {add: true, edit: true, del: true,search: false},
            {
                closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,
            },  //修改
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
            }, //添加
            {
                //删除成功之后触发的function,接收删除返回的提示信息,在页面做展示
                closeAfterDelete: true, //不生效
                reloadAfterSubmit: true,
                afterSubmit: function (response) {
                    if (response.responseJSON.status == 201) {
                        $("#message").html(response.responseJSON.message);
                        $("#showMsg").show();
                        setTimeout(function () {
                            $("#showMsg").hide();
                        }, 1000)
                    } else {
                        $("#message").html(response.responseJSON.message);
                        $("#showMsg").show();
                        setTimeout(function () {
                            $("#showMsg").hide();
                        }, 1000)
                    }
                    return "true";
                }
            }, //删除

        );
    }


    //开启子表格的样式
    function addSubGrid(subgridId, rowId) {

        var subgridTableTd = subgridId + "Table";
        var pagerId = subgridId + "Page";


        $("#" + subgridId).html("" +
            "<table id='" + subgridTableTd + "' />" +
            "<div id='" + pagerId + "' />"
        );


        $("#" + subgridTableTd).jqGrid({
            url: "${path}/category/secondCategory?id=" + rowId,

            datatype: "json",
            rowNum: 20,
            pager: "#" + pagerId,
            sortname: 'num',
            sortorder: "asc",
            styleUI: "Bootstrap",
            autowidth: true,
            height: "auto",
            viewrecords: true,
            editurl: "${path}/category/edit",
            hiddengrid: false,
            colNames: ['id', '名称', '级别', '父级别id'],
            colModel: [
                {name: "id", index: "num", width: 80, key: true, align: 'center'},
                {name: "cateName", index: "item", width: 130, align: 'center', editable: true,},
                {name: "levels", index: "qty", width: 70, align: "right", align: 'center'},
                {
                    name: "parentId", index: "unit", width: 70, align: "right", align: 'center',
                    editable: true,
                    edittype: 'select',
                    editoptions: {dataUrl: "${path}/category/queryCategory"},
                },
            ]
        });

        $("#" + subgridTableTd).jqGrid('navGrid', "#" + pagerId, {edit: true, add: true, del: true,search: false},
            {
                closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,
            },
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
            },
            {
                closeAfterDelete: true, //不生效
                reloadAfterSubmit: true,
                afterSubmit: function (response) {
                    /*$("#message").html(response.responseJSON.message);
                    $("#showMsg").show();
                    setTimeout(function () {
                        $("#showMsg").hide();
                    }, 1000);*/
                    return "true";
                }
            }
        );
    }


</script>


<%--设置面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>类别信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">类别管理</a></li>
    </ul>
    <center>
        <div id="showMsg" style="width:300px;display: none" class="alert alert-danger alert-dismissible" role="alert">
            <span id="message">dfsfsf</span>
        </div>
    </center>

    <%--表单--%>
    <table id="cateTable"/>

    <%--分页工具栏--%>
    <div id="catePage"/>

</div>

<%--
    删除要有提示信息
--%>
