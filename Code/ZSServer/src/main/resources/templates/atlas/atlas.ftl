<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta name="author" content="leslie">
    <title>知识图谱</title>
    <link rel="icon" href="">
    <link rel="stylesheet" href="/theme/chacha/cms/v2/css/bootstrap.css" type="text/css"/>
    <link rel="stylesheet" href="/theme/chacha/cms/v2/css/font-awesome.min.css" type="text/css"/>
    <link rel="stylesheet" href="/theme/chacha/cms/v2/css/icon.css" type="text/css"/>
    <link rel="stylesheet" href="/theme/chacha/cms/v2/css/font.css" type="text/css"/>
    <link rel="stylesheet" href="/theme/chacha/cms/v2/css/app.css?time=201801051" type="text/css"/>
    <!--[if lt IE 9]>
    <script src="/js/html5shiv.js"></script>
    <script src="/js/respond.js"></script>
    <![endif]-->
    <script type="text/javascript" src="/js/siteconfig.js"></script>
    <script src="/theme/chacha/cms/v2/js/jquery.min.js"></script>
    <script src="/theme/chacha/cms/v2/js/bootstrap.js"></script>
    <link rel="stylesheet" href="/theme/chacha/cms/v2/css/toastr.css"/>
    <script src="/theme/chacha/cms/v2/js/custom.js?time=201801051"></script>

</head>
<body>
<script type="text/javascript" src="/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/jquery.form.min.js"></script>
<link rel="stylesheet" href="/theme/chacha/cms/v2/css/companyDetail.css?time=1515295315" type="text/css"/>
<style>
    html, body {
        height: 100%;
        background-color: #fff;
    }

    .nodetext {
        font-size: 12px;
        fill: #fff;
    }

    .linetext {
        font-size: 12px;
    }

    .find-company {
        padding-top: 0px;
        background: #ffffff;
        width: 300px;
        position: absolute;
        right: 20px;
        top: 110px;
        -webkit-box-shadow: 0 0 10px rgba(0, 0, 0, .5);
        -moz-box-shadow: 0 0 10px rgba(0, 0, 0, .5);
        box-shadow: 0 0 10px rgba(0, 0, 0, .5);
        display: block;
        display: none;
        z-index: 99;
    }

    .find-company-number {
        margin-left: 10px;
        color: #e33244;
    }

    .find-mao-detail-item {
        line-height: 40px;
        font-size: 16px;
        padding-left: 12px;
        padding-right: 12px;
        border-bottom: 1px solid #dddddd;
        color: #128bed;
    }

    .find-mao-detail-item div {
        margin-top: 5px;
    }

    .find-mao-list-item {
        padding: 10px 12px 10px 0;
        border-bottom: 1px solid #ddd;
    }

    .find-mao-list-item a {
        text-decoration: none;
        display: inline-block;
        width: 100%;
        height: 100%;
    }

    .find-mao-list-item a:active {
        background: #dddddd;
    }

    .find-company-list {
        padding-left: 12px;
        height: 500px;
        overflow: auto;
    }

    .find-mao-float-wrap {
        width: 100%;
        overflow: hidden;
    }

    .find-mao-cop-name {
        float: left;
        font-size: 15px;
        color: #128bed;
        width: 75%;
    }

    .find-mao-cop-province {
        float: right;
        color: #666666;
        font-size: 13px;
        background: url("./theme/chacha/cms/v2/images/address.png") no-repeat 0% 50%;
        background-size: 15px;
        padding-left: 15px;
    }

    .find-mao-oper {
        color: #666666;
        font-size: 13px;
        margin-top: 5px;
    }

    .find-mao-cop-money {
        float: left;
        color: #666666;
        font-size: 13px;
    }

    .find-mao-cop-time {
        float: right;
        color: #666666;
        font-size: 13px;
    }

    .find-mao-close {
        margin-top: 10px;
        color: #333;
    }


    .water-mark {
        position: absolute;
        z-index: 15;
        font-size: 12px;
        color: #aaa;
        text-align: center;
        width: 100%;
        bottom: 20px;
    }

    .bsBox, .bsBox * {
        box-sizing: content-box;
    }

    :-webkit-full-screen.mao-screen-area {
        /* width: 100%; */
        height: 100% !important;
        background: #fff;
    }

    .mao-screen-area {
        width: 100%;
        height: 100%;
        background: #fff;
        position: relative;
    }

    .text-blue {
        color: #128bed;
    }

    /*d3*/
    #MainD3 {
        display: none;
    }
    .printLogo {
        position: absolute;
        width: 100%;
        height: 1px;
        z-index: 0;
    }

    .printLogo img {
        display: block;
        margin: 0 auto;
        margin-top: 100px;
    }

    #no_data {
        margin-top: 0px;
        position: relative;
        top: 300px;
    }
    #MainCy{
        margin:-20px;
    }
    .tp-detail{
        display:none;
    }
    .tp-name li{
        color:#00ffe4;
        font-size:16px;
    }
</style>
<div class="mao-nodata" id="no_data">
    <p class="noface"><img src="/theme/chacha/cms/v2/images/nodata.png"/></p>
    小查还没找到企业关系
</div>
<div class="load_data" id="load_data"><img src="/theme/chacha/cms/v2/images/preloader.gif"></div>
<div id="MainCy" style=" width: 100%;height:100%;"></div>
<div id="MainD3" scale="1" class="no-padding tp-container">
    <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" xml:space="preserve"> </svg>
</div>
<link rel="stylesheet" href="/plugins/tupu/tupu1.css?time=1515295315" type="text/css"/>
<div class="tp-detail">
    <div class="tp-detail-close"><span>X</span></div>
    <ul class="tp-name">

    </ul>
</div>
<script src="/plugins/tupu/cytoscape.js"></script>
<script src="/plugins/tupu/d3.v4.js"></script>
<script src="/plugins/tupu/tupu.min.js"></script>
</html>