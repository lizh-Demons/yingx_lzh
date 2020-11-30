<%@ page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Echarts测试</title>
    <script src="${pageContext.request.contextPath}/bootstrap/js/echarts.common.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery.min.js"></script>

    <script>
        $(function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            $.get("${pageContext.request.contextPath}/user/getUserData", function (data) {

                console.log(data)
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '用户注册统计信息',//标题
                        subtext: '纯属虚构，如有雷同请联系作者',//子标题
                        sublink: 'www.baidu.com',
                        textStyle: {
                            color: "#cad",
                        }
                    },
                    tooltip: {},//鼠标移动提示信息
                    legend: {
                        data: ['男生', '女生']//选项卡
                    },
                    xAxis: {
                        data: data.month,
                    },
                    yAxis: {},
                    series: [{  //数据
                        name: '男生',  //选项卡
                        type: 'bar',  //类型
                        data: data.boy
                    },{  //数据
                        name: '女生',
                        type: 'bar',
                        data: data.girl
                    }]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }, "json")
        })

    </script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>

