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
    <title>会议日程</title>
    <link rel="stylesheet" href="/vendor/base.css">
    <link rel="stylesheet" href="/vendor/rem.js">
    <link rel="stylesheet" href="/css/common.css">
</head>
<script type="text/javascript" src="/vendor/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/vendor/jalendar.js"></script>

<script type="text/javascript">
    $(function() {
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 2;
        var day = date.getDate();
        // 下一个月
        function nextDate() {
            return year + '/' + month + '/' + day
        }
        $.ajax({
            url: '/apis/report/getHtmlData.do?id=' + ${id} + '&type=' + ${type},
            type: 'GET',
            async: false,
            success: function(res) {
                console.log(res)
                $.each(res.data.schedule, function(index, item) {
                   for(var i=0;i<item.length;i++){
                    $('#myId').append(
                        '<div class="added-event" ' +
                        'data-date="' + item[i].date + '/' + month + '/' + year + '"' +
                        'data-name="会议名称:"'+
                        'data-name-val="'+item[i].name+'"'+
                        ' data-place="会议地点："' +
                        'data-place-val="' + item[i].place + '"' +
                        'data-sponsor="会议主办方："' +
                        'data-sponsor-val="' + item[i].sponsor + '"></div>'
                    )
                   }
                })
                $('#myId').jalendar({
                    customDay: nextDate(), // Format: Year/Month/Day
                });
                var strHtml = "";
                strHtml += "<li>本月一共<span class='point'>" + res.data.total + "</span>场会议</li>";
                strHtml += "<li>最多会议地点是<span class='point'>" + res.data.place + "</span></li>";
                strHtml += "<li>会议覆盖的行业是<span class='point'>" + res.data.industry + "</span></li>";
                strHtml += "<li>推荐参加<span class='point'>" + res.data.advise + "交流大会</span></li>";
                $('.daytext ul').html(strHtml);

            }
        })
    })
</script>
</head>

<body>
    <div id='myId' class='jalendar'></div>
    <div class='daytext'>
        <ul></ul>
    </div>
</body>

</html>