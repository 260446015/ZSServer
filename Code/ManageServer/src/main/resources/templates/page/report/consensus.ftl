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
    <title>舆论焦点</title>
    <link rel="stylesheet" href="/vendor/base.css">
    <link rel="stylesheet" href="/vendor/rem.js">
    <link rel="stylesheet" href="/css/common.css">
</head>

<body>
    <div class="dottedline">

    </div>
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
                if (i % 2) {
                    strHtml += "<div class='rightline'><p class='ptext'><span class='leftdot'></span><small class='textcolor'>" + res.data[i].keyWord +
                        "</small></p>";
                    strHtml += "<p class='ptext bigtext'>" + res.data[i].text + "</p></div>"
                } else {
                    strHtml += "<div class='leftline'><p><span class='rightdot'></span><small class='textcolor'>" + res.data[i].keyWord +
                        "</small></p>";
                    strHtml += "<p class='bigtext'>" + res.data[i].text + "</p></div>"
                }
            }
            $(".dottedline").html(strHtml)

        }
    })
</script>

</html>