<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-产业峰会</title>
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
        <div class="right-content">
            <div id="myCarousel" class="carousel slide">
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="/images/swiper_slide1.jpg">
                        <div class="mask"></div>
                        <div class="carousel-text-box">
                            <div class="carousel-text-body">
                                <p class="title fadeInLeft animated">2017年第三届深圳国际互联网与电子商务博览会</p>
                                <p class="details fadeIn animated">举办时间：2017-10-12～2017-10-14</p>
                                <p class="details fadeIn animated">举办展馆：深圳会展中心</p>
                                <p class="details fadeIn animated">展会行业：电脑、多媒体、网络、通信</p>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <img src="/images/swiper_slide2.jpg">
                        <div class="mask"></div>
                        <div class="carousel-text-box">
                            <div class="carousel-text-body">
                                <p class="title fadeInLeft animated">2018年第三届深圳国际互联网与电子商务博览会</p>
                                <p class="details fadeIn animated">举办时间：2018-10-12～2018-10-14</p>
                                <p class="details fadeIn animated">举办展馆：深圳会展中心</p>
                                <p class="details fadeIn animated">展会行业：电脑、多媒体、网络、通信</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="posa-right-container">
                <div class="model-box">
                    <div class="model-header">
                        <h3 class="model-title">产业峰会</h3>
                    </div>
                    <div class="search-box">
                        <div class="search-group">
                            <div class="search-item-title">
                                产业
                            </div>
                            <div class="search-item-content">
                                <a href="javascript:void(0);" id="1-全部" class="search-item active">全部</a>
                                <a href="javascript:void(0);" id="1-人工智能"  class="search-item">人工智能</a>
                                <a href="javascript:void(0);" id="1-大数据" class="search-item">大数据</a>
                                <a href="javascript:void(0);" id="1-物联网" class="search-item">物联网</a>
                                <a href="javascript:void(0);" id="1-生物技术" class="search-item">生物技术</a>
                            </div>
                        </div>      
                        <div class="search-group">
                            <div class="search-item-title">
                                区域
                            </div>
                            <div class="search-item-content">
                                <a href="javascript:void(0);" id="2-全部" class="search-item active">全部</a>
                                <#list area as bb>
                                	<a href="javascript:void(0);" id="2-${bb}" class="search-item">${bb}</a>
                                </#list>
                            </div>
                        </div>
                        <div class="search-group">
                            <div class="search-item-title">
                                排序
                            </div>
                            <div class="search-item-content">
                                <a href="javascript:void(0);" id="3-按热度" class="search-item active">按热度</a>
                                <a href="javascript:void(0);" id="3-按时间" class="search-item">按时间</a>
                            </div>
                        </div>
                    </div>
                    <div class="model-body">
                        <div class="row img-list-box" id="summit-list">
                        </div>
                         <div class="page-box clearfix">
                				<ul class="page pull-right" id="page"></ul>
            				</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include  "/common/script.ftl"/>
<script src="/js/industrySummitMeeting/industrySummitMeeting.js"></script>
</body>
</html>