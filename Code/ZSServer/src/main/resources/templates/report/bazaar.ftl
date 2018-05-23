<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="description" content="中科点击·慧数招商">
    <meta name="keywords" content="慧数招商人工智能行业月度报告">
    <meta name="author" content="慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
    <title>市场焦点</title>
    <link rel="stylesheet" href="/vendor/base.css">
    <link rel="stylesheet" href="/vendor/rem.js">
    <link rel="stylesheet" href="/css/common.css">
</head>

<body>
    <div class="allbazzr"></div>
</body>
<script src="/vendor/jquery-3.3.1.min.js"></script>
<script>
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + ${id} + '&type=' + ${type},
        type: 'GET',
        async: false,
        success: function(res) {
            var strHtml = "";
            for (var i = 0; i < res.data.length; i++) {
                strHtml += "<div><p>" + res.data[i].keyWord + "</p><p class='text'>" + res.data[i].text + "</p></div>";
            }
            $(".allbazzr").html(strHtml)
        }
    })
</script>

</html>