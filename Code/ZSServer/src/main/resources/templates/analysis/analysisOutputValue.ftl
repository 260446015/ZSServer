<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-年产值分析</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="慧数招商平台，是一个关于园区产业招商的大数据管理平台">
    <meta name="keywords" content="慧数，招商，慧数招商，招商平台，园区，园区招商，园区招商平台，科技园，产业园，大数据，产业">
    <meta name="author" content="张鑫，慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
    <!-- css共用部分 start -->
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
<!-- header部分  start -->
<#include "/common/header.ftl"/>
<div class="wrapper">
    <div class="page-content">
		<#include "/common/sidebar2.ftl"/>
        <div class="right-content">
            <div class="container">
                <div class="model-box">
                    <div class="model-header">
                        <h3 class="model-title">${Request.park}</h3>
                    </div>
                    <div class="model-body">
                        <ul class="mark-box">
                            <li class="active">
                                <a class="mark-item" href="/apis/analysis/analysisOutputValue.html?park=${Request.park}">图表分析</a>
                            </li>
                            <li>
                                <a class="mark-item" href="/apis/analysis/informationPush.html?park=${Request.park}">情报推送</a>
                            </li>
                        </ul>
                        <h3 class="text-center sub-title">企业融资分布</h3>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="charts" id="charts1"></div>
                            </div>
                            <div class="col-md-6">
                                <div class="box box1">
                                    <img class="lay lay-left-bottom" src="/images/left_bottom.png" alt="">
                                    <img class="lay lay-right-top" src="/images/right_top.png" alt="">
                                    <h3 class="box-title"></h3>
                                    <div class="box-body">
                                        <div class="search-box mb20" id="city_list">
                                        </div>
                                        <div class="btn-box-center">
                                            <button type="button" class="btn btn-fill btn-blue">上一页</button>
                                            <button type="button" class="btn btn-fill btn-blue">下一页</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <h3 class="text-center sub-title">价值榜</h3>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="radio-box">
                                    <label class="radio-inline">
                                        <input class="radio-item" name="timer" checked type="radio" value="新兴信息产业"/>
                                        新兴信息产业
                                    </label>
                                    <label class="radio-inline">
                                        <input class="radio-item" name="timer" type="radio" value="新材料产业"/>
                                        新材料产业
                                    </label>
                                    <label class="radio-inline">
                                        <input class="radio-item" name="timer" type="radio" value="生物产业"/>
                                        生物产业
                                    </label>
                                    <label class="radio-inline">
                                        <input class="radio-item" name="timer" type="radio" value="数字创意产业"/>
                                        数字创意产业
                                    </label>
                                    <label class="radio-inline">
                                        <input class="radio-item" name="timer" type="radio" value="环保与新能源产业"/>
                                        环保与新能源产业
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-6 text-center">
                                <ul class="tabs tabs-custom">
                                    <li class="active" id="chan">
                                        <a href="javascript:void(0);" onclick="myClick('年产值')">年产值</a>
                                    </li>
                                    <li id="shui">
                                        <a href="javascript:void(0);" onclick="myClick('年税收')">年税收</a>
                                    </li>
                                </ul>
                                <div class="charts" id="charts2"></div>
                            </div>
                            <div class="col-md-6">
                                <div class="box box1 mt88">
                                    <img class="lay lay-left-bottom" src="/images/left_bottom.png" alt="">
                                    <img class="lay lay-right-top" src="/images/right_top.png" alt="">
                                    <h3 class="box-title"></h3>
                                    <div class="box-body">
                                        <div class="search-box top" id="top_list">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include  "/common/footer.ftl"/>
<!-- js 共用部分 start -->
<#include  "/common/script.ftl"/>
<!-- js 共用部分 end -->
<script src="/js/analysisOutputValue/analysisOutputValue.js"></script>
<script type="text/javascript">
	$(function(){
		myPost('${Request.park}');
		myPost2('年产值','新兴信息产业');
		myTopList('新兴信息产业');
	})
</script>
</body>
</html>