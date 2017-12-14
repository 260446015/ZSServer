<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-产业地图</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="慧数招商平台，是一个关于园区产业招商的大数据管理平台">
    <meta name="keywords" content="慧数，招商，慧数招商，招商平台，园区，园区招商，园区招商平台，科技园，产业园，大数据，产业">
    <meta name="author" content="张鑫，慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
    <!-- css共用部分 start -->
    <#include "/common/link.ftl"/>
    <link href="/vendor/loading/css/loading.css" rel="stylesheet" />
    <!-- css 共用部分 end -->
    <!-- js 兼容低版本IE start -->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- js 兼容低版本IE end -->
</head>
<body>
 <#include "/common/header.ftl"/>
    <div class="wrapper">
       
        <div class="page-content">
            <#include "/common/sidebar2.ftl"/>
            <div class="right-content">
             <#include "/common/mark.ftl"/>
                <div class="row">
                    <div class="col-md-3">
                        <div class="charts" id="industryHotCity">

                        </div>
                        <div class="box">
                            <img class="lay lay-top" src="/images/lay_top.png" alt="">
                            <img class="lay lay-right" src="/images/lay_right.png" alt="">
                            <img class="lay lay-bottom" src="/images/lay_bottom.png" alt="">
                           <h3 class="box-title" id="box-title">北京</h3>
                            <div class="box-body">
                                <ul class="box-list" id="box-list">
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div style="position: relative">
                            <div class="map" id="industryMap">

                            </div>
                            <div class="layer-person">
                             <h3 class="layer-person-title text-center">
                             	无线移动通信国家重点实验室
                                    <button type="button" class="close">×</button>
                               </h3>
                                <div class="layer-body">
                                <div id="myCarousel" data-wrap="false" data-interval="false" class="carousel slide">
                                        <!-- 轮播（Carousel）项目 -->
                                  <div class="carousel-inner" id="carousel-inner">
                                        </div>
                                       <a class="carousel-control left" href="#myCarousel"
                                           data-slide="prev">&lsaquo;
                                        </a>
                                        <a class="carousel-control right" href="#myCarousel"
                                           data-slide="next">&rsaquo;
                                        </a> 
                                    </div>
                                </div>
                                <div class="layer-footer text-center">
                                    <a href="javascript:void(0);" class="like">添加关注</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="timeline-box">
                            <h3 class="timeline-title">
                                <span class="title">产业峰会</span>
                                <a class="more" href="/summit/industrySummitMeeting.html">更多</a>
                            </h3>
                            <ul class="timeline" id="timeline">
                            	<#list summit as info>
                            	<li>
                                    <a href="${info.articleLink}"; target="_blank" )>
                                        <i class="timeline-circle"></i>
                                        <span class="time">${info.publishTime}</span>
                                        <span class="line-title">${info.title}</span>
                                    </a>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#include "/common/footer.ftl"/>
   <#include  "/common/script.ftl"/>
   <script src="/vendor/loading/js/loading.js"></script>
    <script src="/js/industryMap/industryMap.js"></script>
</body>
</html>