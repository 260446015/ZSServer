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
            <div class="map-box">
               <div class="gaodeMap" id="map"></div>
                <div id="tip"></div>
                <div class="search-circle-box">
                    <img class="search-circle-img" src="/images/search_img.png" />
                </div>
                <a href="javascript:void(0);" class="follow follow-black" id="attation" onclick="attation(this);">添加关注</a>
                <div class="panel panel-primary panel-opacity">
                    <div class="panel-heading">
                        <h3 class="panel-title text-center" id="gardenName"></h3>
                    </div>
                    <div class="panel-body">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-4 control-label">地址：</label>
                                <div class="col-md-8">
                                    <p class="form-control-static" id="gardenAddress"></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label">产业：</label>
                                <div class="col-md-8">
                                    <p class="form-control-static" id="gardenIndustry"></p></div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label">占地面积：</label>
                                <div class="col-md-8">
                                    <p class="form-control-static" id="gardenSquare"></p></div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label">园区级别：</label>
                                <div class="col-md-8">
                                    <p class="form-control-static" id="gardenLevel"></p></div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label">入驻企业数量：</label>
                                <div class="col-md-8">
                                    <p class="form-control-static" id="enterCount"></p></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="model-box">
                    <div class="model-body border-box">
                        <div class="row" id="companyList">
                            
                        </div>
                    </div>
                    <div class="page-box clearfix">
        				<ul class="page pull-right" id="page"></ul>
            		</div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <p class="text-center">Copyright©2008-2018 中科点击（北京）科技有限公司-版权所有  京ICP备11012241-3号</p>
</div>
<!-- js 共用部分 start -->
<#include  "/common/script.ftl"/>
<!-- js 共用部分 end -->
<script type="text/javascript" src = 'http://webapi.amap.com/maps?v=1.4.0&key=8a3cd2d06a064e33adbf0d7a71c3246f&plugin=AMap.Geocoder'></script>
<script src="http://webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
<script src="/js/allCityPark/allCityParkDetails.js"></script>
</body>
</html>