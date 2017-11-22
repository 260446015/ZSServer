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
        <#include "/common/mark.ftl"/>
        <div class="page-content">
            <div class="left-nav">
                <ul>
                    <li class="active">
                        <a href="/indusMap/map">
                            <div class="circle-out industryIcon">
                                <div class="circle-in">

                                </div>
                            </div>
                            产业地图
                        </a>
                        <ul class="left-nav-menu">
                            <li>
                                <a href="/indus/get">产业动态</a>
                            </li>
                            <li>
                                <a href="/summit/getInfo">产业峰会</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <div class="circle-out parkIcon">
                                <div class="circle-in">

                                </div>
                            </div>
                            园区地图
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <div class="circle-out rzIcon">
                                <div class="circle-in">

                                </div>
                            </div>
                            融资速递
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <div class="circle-out merchantsIcon">
                                <div class="circle-in">

                                </div>
                            </div>
                            招商报告
                        </a>
                    </li>
                </ul>
            </div>
            <div class="right-content">
                <div class="row">
                    <div class="col-md-3">
                        <div class="charts" id="industryHotCity">

                        </div>
                        <div class="box box1">
                            <img class="lay lay-top" src="/images/lay_top.png" alt="">
                            <img class="lay lay-right" src="/images/lay_right.png" alt="">
                            <img class="lay lay-bottom" src="/images/lay_bottom.png" alt="">
                           <h3 class="box-title" id="">北京</h3>
                            <div class="box-body">
                                <ul class="box-list" id="box-list">
                                  <!--<li>
                                        <a href="javascript:void(0);">北京百分点信息科技有限公司</a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);">北京拓尔思信息技术股份有限公司</a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);">北京智慧星光信息技术有限公司</a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);">北京百度网讯科技有限公司</a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);">北京百分点信息科技有限公司</a>
                                    </li>-->
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div style="position: relative">
                            <div class="map" id="industryMap">

                            </div>
                            <div class="layer-person">
                                <h3 class="layer-person-title text-center">无线移动通信国家重点实验室</h3>
                                <div class="layer-body">
                                    <div class="form-horizontal">
                                        <div class="form-group text-center">
                                            <img src="/images/user_head.png" alt="" />
                                        </div>
                                        <div class="form-group">
                                            <p class="text-center">邬贺铨</p>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4 text-right control-label">机构性质</label>
                                            <div class="col-md-7">
                                                <p class="form-control-static">国家重点实验室</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4 text-right control-label">机构产业</label>
                                            <div class="col-md-7">
                                                <p class="form-control-static">新型信息产业</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4 text-right control-label">委托单位</label>
                                            <div class="col-md-7">
                                                <p class="form-control-static">电信科学技术研究所</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4 text-right control-label">机构网址</label>
                                            <div class="col-md-7">
                                                <p class="form-control-static">https://baike.baidu.com/item/</p>
                                            </div>
                                        </div>
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
                                <a class="more" href="/summit/getInfo">更多</a>
                            </h3>
                            <ul class="timeline">
                            	<#list summit as info>
                            	<li>
                                    <a href="javascript:void(0);">
                                        <i class="timeline-circle"></i>
                                        <span class="time">${info.publishTime}</span>
                                        <span class="line-title">${info.title}</span>
                                    </a>
                                <!--<li>
                                    <a href="javascript:void(0);">
                                        <i class="timeline-circle"></i>
                                        <span class="time">11月28日</span>
                                        <span class="line-title">2017年第十九届中国国际高新技术成果交易会</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);">
                                        <i class="timeline-circle"></i>
                                        <span class="time">11月29日</span>
                                        <span class="line-title">2017年第十九届中国国际高新技术成果交易会</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);">
                                        <i class="timeline-circle"></i>
                                        <span class="time">11月30日</span>
                                        <span class="line-title">2017年第十九届中国国际高新技术成果交易会</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);">
                                        <i class="timeline-circle"></i>
                                        <span class="time">11月28日</span>
                                        <span class="line-title">2017年第十九届中国国际高新技术成果交易会</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);">
                                        <i class="timeline-circle"></i>
                                        <span class="time">11月29日</span>
                                        <span class="line-title">2017年第十九届中国国际高新技术成果交易会</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);">
                                        <i class="timeline-circle"></i>
                                        <span class="time">11月30日</span>
                                        <span class="line-title">2017年第十九届中国国际高新技术成果交易会</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);">
                                        <i class="timeline-circle"></i>
                                        <span class="time">11月28日</span>
                                        <span class="line-title">2017年第十九届中国国际高新技术成果交易会</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);">
                                        <i class="timeline-circle"></i>
                                        <span class="time">11月29日</span>
                                        <span class="line-title">2017年第十九届中国国际高新技术成果交易会</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);">
                                        <i class="timeline-circle"></i>
                                        <span class="time">11月30日</span>
                                        <span class="line-title">2017年第十九届中国国际高新技术成果交易会</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);">
                                        <i class="timeline-circle"></i>
                                        <span class="time">11月28日</span>
                                        <span class="line-title">2017年第十九届中国国际高新技术成果交易会</span>
                                    </a>
                                </li>-->
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
    <script src="/js/industryMap/industryMap.js"></script>
</body>
</html>