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
    <#include "/common/link.ftl"/>
    <script src="/theme/chacha/cms/v2/js/custom.js?time=201801051"></script>

</head>
<body>
<script type="text/javascript" src="/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/jquery.form.min.js"></script>
<link rel="stylesheet" href="/theme/chacha/cms/v2/css/companyDetail.css?time=1515295315" type="text/css"/>
<div class="mao-nodata" id="no_data">
    <p class="noface"><img src="/theme/chacha/cms/v2/images/nodata.png"/></p>
    还没找到企业关系
</div>
<div class="load_data" id="load_data"><img src="/theme/chacha/cms/v2/images/preloader.gif"></div>
<div id="MainCy" style=" width: 100%;height:100%;"></div>
<div id="MainD3" scale="1" class="no-padding tp-container">
    <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" xml:space="preserve"> </svg>
</div>
<link rel="stylesheet" href="/plugins/tupu/tupu1.css?time=1515295315" type="text/css"/>
<div class="tp-detail box">
    <img class="lay lay-top" src="/images/lay_top.png" alt="">
    <img class="lay lay-right" src="/images/lay_right.png" alt="">
    <img class="lay lay-bottom" src="/images/lay_bottom.png" alt="">
    <h3 class="box-title tp-detail-close"><span>X</span></h3>
    <div class="box-body">
        <ul class="tp-name">
        </ul>
    </div>
</div>
<script src="/plugins/tupu/cytoscape.js"></script>
<script src="/plugins/tupu/d3.v4.js"></script>
<script src="/plugins/tupu/tupu.min.js"></script>
</html>