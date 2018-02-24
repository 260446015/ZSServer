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
    <link rel="stylesheet" href="/css/schedule.css">
</head>
<body>
    <div class="catalog">
        <header>
            <!-- <div class="left">
                <
            </div> -->
            <div class="center">
                <h1>
                    会议日程
                </h1>
            </div>
        </header>
        <div class="container">
           <div  class="calendar">
            <div class="sign" id="sign_cal">
                <table>
                    <tbody>
                        <tr>
                            <th>日</th>
                            <th>一</th>
                            <th>二</th>
                            <th>三</th>
                            <th>四</th>
                            <th>五</th>
                            <th>六</th>
                        </tr>
                        <tr>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th>四</th>
                            <th>五</th>
                            <th>六</th>
                        </tr>
                        <tr>
                            <th>日</th>
                            <th>一</th>
                            <th>二</th>
                            <th>三</th>
                            <th class="on">四 <span class="smalldot"></span></th>
                            <th>五</th>
                            <th>六</th>
                        </tr>
                        <tr>
                            <th>日</th>
                            <th>一</th>
                            <th>二</th>
                            <th>三</th>
                            <th>四</th>
                            <th>五</th>
                            <th>六</th>
                        </tr>
                        <tr>
                            <th>日</th>
                            <th>一</th>
                            <th>二</th>
                            <th>三</th>
                            <th>四</th>
                            <th>五</th>
                            <th>六</th>
                        </tr>
                        <tr>
                            <th>日</th>
                            <th>一</th>
                            <th>二</th>
                            <th>三</th>
                            <th>四</th>
                            <th>五</th>
                            <th></th>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="calendarbgimg">
                <ul>
                    <li>本月一共 <span>3</span>场会议</li>
                    <li>最多会议地点是<span>上海</span></li>
                    <li>会议覆盖的行业是<span>无人机、人工智能</span></li>
                    <li>推荐参加<span>无人机</span>交流大会 </li>
                </ul>
            </div>
           </div>
        </div>
        <footer>
         <div class="index">
             <img src="/img/icon/jiantou.png" alt=""  class="img">
         </div>
        </footer>
    </div>
    <script src="/vendor/jquery-3.3.1.min.js"></script>
    <script src="/js/schedule/schedule.js"></script>
    <script>
        $(function(){
  //ajax获取日历json数据
        var signList=[{"signDay":"09"},{"signDay":"11"},{"signDay":"12"},{"signDay":"13"}];
        calUtil.init(signList);
        });
    </script>
</body>
</html>