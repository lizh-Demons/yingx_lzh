<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>
<!--顶部导航-->
<div class="col-sm-12">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">应学视频App后台管理系统</a>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎：<span class="text-primary">${sessionScope.admin.username}</span></a></li>
                <li><a href="${path}/admin/exit">退出 <span class="glyphicon glyphicon-log-out"></span></a></li>
            </ul>
        </div><!-- /.container-fluid -->
    </nav>
</div>
<!--栅格系统-->
<div class="col-sm-12">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2">
                <!--左边手风琴部分-->
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="false">

                    <div class="panel panel-danger text-center">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                                   aria-expanded="true" aria-controls="collapseOne">
                                    用户管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingOne">
                            <div class="panel-body">
                                <div class="list-group">
                                    <a href="javascript:$('#content').load('${path}/user/showUser.jsp')"
                                       class="list-group-item list-group-item-danger">用户信息</a>
                                    <a href="javascript:$('#content').load('${path}/user/userChina.jsp')" class="list-group-item list-group-item-info">用户分布</a>
                                    <a href="javascript:$('#content').load('${path}/user/statistics.jsp')" class="list-group-item list-group-item-warning">用户统计</a>
                                </div>
                            </div>

                        </div>
                    </div>
                    <hr>
                    <div class="panel panel-success text-center">
                        <div class="panel-heading" role="tab" id="headingTwo">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    分类管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingTwo">
                            <div class="panel-body">
                                <div class="list-group">
                                    <a href="javascript:$('#content').load('${path}/category/showCategory.jsp')"
                                       class="list-group-item list-group-item-success" style="color: #2b542c;">分类列表</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="panel panel-info text-center">
                        <div class="panel-heading" role="tab" id="headingThree">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    视频管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingThree">
                            <div class="panel-body">
                                <div class="list-group">
                                    <a href="javascript:$('#content').load('${path}/video/showVideo.jsp')"
                                    class="list-group-item list-group-item-info" style="color: #000066;">视频信息</a>
                                    <a href="#" class="list-group-item list-group-item-info" style="color: #000066;">视频搜索</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="panel panel-warning text-center">
                        <div class="panel-heading" role="tab" id="headingFour">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                    反馈管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseFour" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingFour">
                            <div class="panel-body">
                                <div class="list-group">
                                    <a href="javascript:$('#content').load('${path}/feedback/showFeedback.jsp')" class="list-group-item list-group-item-warning" style="color: #985f0d;">反馈信息</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>

                    <div class="panel panel-primary text-center">
                        <div class="panel-heading" role="tab" id="headingFive">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                    日志管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseFive" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingFive">
                            <div class="panel-body">
                                <div class="list-group">
                                    <a href="javascript:$('#content').load('${path}/log/showLog.jsp')" class="list-group-item list-group-item-success" style="color: #985f0d;">反馈信息</a>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <!--巨幕开始-->
            <div class="col-sm-10">
                <div id="content">
                    <div class="jumbotron">
                        <h1>欢迎来到应学视频App管理系统</h1>
                    </div>
                    <!--右边轮播图部分-->
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                        </ol>

                        <!-- Wrapper for slides -->
                        <div class="carousel-inner" role="listbox">
                            <div class="item active">
                                <img src="${path}/bootstrap/img/pic1.jpg" alt="赤行" class="center-block">
                            </div>
                            <div class="item">
                                <img src="${path}/bootstrap/img/pic2.jpg" alt="赤行" class="center-block">
                            </div>

                            <div class="item">
                                <img src="${path}/bootstrap/img/pic3.jpg" alt="赤行" class="center-block">
                            </div>
                            <div class="item">
                                <img src="${path}/bootstrap/img/pic4.jpg" alt="赤行" class="center-block">
                            </div>
                        </div>

                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" role="button"
                           data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" role="button"
                           data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>
<!--页脚-->
<nav class="navbar navbar-default navbar-fixed-bottom">
    <%--    <span class="center-block" style="margin-top: 12px; margin-left: 700px">赤行科技 lizh©chixing.com</span>--%>
    <div class="panel panel-footer" align="center">
        <span>赤行科技 lizh©chixing.com</span>
    </div>
</nav>

<!--栅格系统-->
<%--<div class="panel panel-footer" align="center">--%>
<%--    <span>赤行科技 lizh©chixing.com</span>--%>
<%--</div>--%>

<%--<div class="panel panel-footer" align="center">--%>
<%--    <span>@百知教育 zhangcn@zparkhr.com</span>--%>
<%--</div>--%>
</body>
</html>
