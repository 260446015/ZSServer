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
            <div class="row">
                <div class="col-md-8">
                    <div class="map" id="parkMap"></div>
                </div>
                <div class="col-md-4" id="gardenCondition">
                    <h3 class="border-shadow-title">关注园区动态</h3>
                    <div class="border-shadow-box">
                        <div class="row" id="condition">
                            
                        </div>
                    </div>
                    <div class="modal-footer text-center">
                		<a href="/apis/area/garden/findMoreCondition.html" class="btn btn-link">查看更多</a>
            		</div>
                </div>
            </div>
            <h3 class="border-shadow-title">园区数量</h3>
            <div class="border-shadow-box">
                <div class="row">
                    <div class="col-md-7">
                        <div class="charts" id="barCharts"></div>
                    </div>
                    <div class="col-md-5">
                        <div class="charts" id="scatter"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade customed-modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <img class="lay lay-left-bottom" src="/images/left_bottom.png" alt="">
            <img class="lay lay-right-top" src="/images/right_top.png" alt="">
            <img class="lay lay-left-top" src="/images/left_bottom.png" alt="">
            <img class="lay lay-right-bottom" src="/images/right_top.png" alt="">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                    <h3 class="border-shadow-title">
                    <span class="icon-block"></span>
                   年产值
                </h3>
                        <div class="charts sm" id="charts_1"></div>
                    </div>
                    <div class="col-md-6">
                    <h3 class="border-shadow-title">
                    <span class="icon-block"></span>
                   单位:亿
                </h3>
                        <div class="charts sm" id="charts_2"></div>
                    </div>
                </div>
                <h3 class="border-shadow-title">
                    <span class="icon-block"></span>
                    政策动向
                </h3>
                <div class="border-shadow-box no-shadow">
                    <div class="row" id="condition2">
                        
                    </div>
                </div>
                <ul class="img-list-box" id="gardenList">
                    
                </ul>
            </div>
            <div class="modal-footer text-center">
                <a href="javascript:void(0);" class="btn btn-link" id="findMore">查看更多</a>
            </div>
        </div>
    </div>
</div>
<#include "/common/footer.ftl"/>
<!-- js 共用部分 start -->
<#include  "/common/script.ftl"/>
<script src="/js/parkMap/parkMap.js"></script>
<!-- js 共用部分 end -->
</body>
</html>