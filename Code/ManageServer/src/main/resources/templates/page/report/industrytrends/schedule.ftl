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
    <link rel="stylesheet" href="/vender/base.css">
    <link rel="stylesheet" href="/vender/rem.js">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/schedule.css">
</head>
<body>
    <div class="catalog">
        <header>
            <div class="left">
                <
            </div>
            <div class="center">
                <h1>
                    会议日程
                </h1>
            </div>
        </header>
        <div class="container">
           <div  style="width:300px;height:300px;" class="calendar">

           </div>
        </div>
        <footer>
         <div>
             <img src="/img/icon/jiantou.png" alt="">
         </div>
        </footer>
    </div>
    <script src="./libs/jquery-3.3.1.min.js"></script>
    <script src="./js/schedule.js"></script>
    <script>
        $(function(){
  //ajax获取日历json数据
  var signList=[{"signDay":"09"},{"signDay":"11"},{"signDay":"12"},{"signDay":"13"}];
   calUtil.init(signList);
});
    </script>
</body>
</html>