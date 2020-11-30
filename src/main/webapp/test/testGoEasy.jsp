<%@page pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.1.1.js"></script>
    <script>
        var goEasy = new GoEasy({
            host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-3fd7fdb3827b46eb92d10e879932cbfe", //替换为您的应用appkey
        });

        goEasy.subscribe({
            channel: "my_channel", //替换为您自己的channel
            onMessage: function (message) {
                console.log("Channel:" + message.channel + " content:" + message.content);
                alert(message.content)
            }
        });
    </script>
</head>

<body>
    <h1>是不是</h1>
</body>
</html>