<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-园区地图</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="慧数招商平台，是一个关于园区产业招商的大数据管理平台">
    <meta name="keywords" content="慧数，招商，慧数招商，招商平台，园区，园区招商，园区招商平台，科技园，产业园，大数据，产业">
    <meta name="author" content="张鑫，慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
    <!-- css共用部分 start -->
    <style>
        body{
            background: url(/plugins/tupu/images/bg.jpg);
            background-size: cover;
        }
        .bigbox {
            width: 1000px;
            height: 500px;
            margin: 20px auto;
        }

        .content-show {
            width: 650px;
            height: 500px;
            overflow: hidden;
        }
        .conteny-list{
            width: 650px;
            height: 500px;
            overflow: hidden;
        }
        .content-list > li{
            cursor: pointer;
        }

        .content-center {
            width: 250px;
            height: 500px;
        }

        .content-text {
            width: 100px;
            height: 500px;
        }

        .content-list {
            margin: 0 auto;
            margin-top: 100px;
        }

        .content-list li {
            width: 100px;
            line-height: 50px;
            text-align: center;
            background: #4ea2f0;
            color: #fff;
            margin-top: 15px;
        }

        .con {
            display: none;
        }

        .block {
            display: block;
        }
        #MainD3 svg {
            width: 650px;
            height: 500px;
            background: #fff;
            z-index: 9999;
        }
        .atlasbox{
            width: 990px;
            height: 600px;
            position: absolute;
            left: 160px ;
            top: 35px;
            display: none;
            background-color: rgba(9, 14, 21, .9);
            box-shadow:inset 0 0 35px #0e2f61;
            border-radius:10px;
            margin-top:80px;
        }
        #iframeId{
            width: 100%;
            height: 100%;
        }
        .colse{
            position: absolute;
            right: 15px;
            top: 15px;
            width: 40px;
            line-height: 40px;
            color: #00ffe4;
            font-weight: bolder;
            font-size: 20px;
            text-align: center;
        }
        .fl{
            float:left;
        }
        .fr{
            float:right;
        }
    </style>

	<#include "/common/link.ftl"/>
    <!-- css 共用部分 end -->
    <!-- js 兼容低版本IE start -->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- js 兼容低版本IE end -->
</head>
<body class="bg2">
<#include "/common/header.ftl"/>
<div class="wrapper">
    <div class="page-content">
      <#include "/common/sidebar2.ftl"/>
        <div class="posa-right-container">
            <div class="container">
                <div class="bigbox">
                    <div class="content-show fl">
                        <div class="conteny-list echarts1 con block" id="echarts1"></div>
                        <div class="conteny-list echarts2 con" id="echarts2"></div>
                        <div class="conteny-list echarts3 con" id="echarts3"></div>
                        <div class="conteny-list echarts4 con" id="echarts4"></div>
                    </div>
                    <div class="content-text fr">
                        <ol class="content-list fr ols">
                            <li>感知层</li>
                            <li>网络层</li>
                            <li>执行层</li>
                            <li>应用层</li>
                        </ol>
                    </div>
                </div>
                <div class="atlasbox">
                    <iframe src="" frameborder="0" id="iframeId"></iframe>
                    <span class="colse">X</span>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/common/footer.ftl"/>
<!-- js 共用部分 start -->
<#include  "/common/script.ftl"/>
<!-- js 共用部分 end -->
<script src="/js/jquery-1.10.2.min.js"></script>
<script src="/js/echarts.min.js"></script>
<script src="/js/index.js"></script>

</body>
</html>