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
            <div class="container">
                <div class="model-box">
                    <div class="model-header">
                        <h3 class="model-title">关注园区</h3>
                    </div>
                    <div class="search-box">
                        <div class="search-group">
                            <div class="search-item-title">
                                产业
                            </div>
                            <div class="search-item-content" id="gardenIndustry">
                                <a href="javascript:void(0);" class="search-item active">全部</a>
                                 <#list industryList as industry>
                                    <a href="javascript:void(0);" class="search-item">${industry.industryOne}</a>
                                 </#list>
                            </div>
                        </div>
                        <div class="search-group">
                            <div class="search-item-title">
                                区域
                            </div>
                            <div class="search-item-content" id="gardenArea">
                                <a href="javascript:void(0);" class="search-item active">全部</a>
                                <#list areas as area>
                                    <a href="javascript:void(0);" class="search-item">${area}</a>
                                </#list>
                            </div>
                        </div>
                        <div class="search-group">
                            <div class="search-item-title">
                                排序
                            </div>
                            <div class="search-item-content">
                                <a href="javascript:void(0);" class="search-item active">按企业数</a>
                                <a href="javascript:void(0);" class="search-item">按园区占地</a>
                            </div>
                        </div>
                    </div>
                    <div class="model-body border-box">
                        <div class="row" id="gardenList">

                        </div>
                    </div>
                    <div class="page-box clearfix">
                        <ul class="page pull-right" id="page"></ul>
                    </div>
                </div>
                <div class="park-contrast-box">
                    <button type="button" class="btn-fill park-btn">园区对比<span class="collapse-left-right">></span>
                    </button>
                    <div class="contrast-content">
                        <div class="img-box-list" id="gardenCompare">
                            <div class="img-box">
                                <input type="hidden" class="attId"/>
                                <img src="" style="display:none">
                            </div>
                            <div class="img-box">
                                <input type="hidden" class="attId"/>
                                <img src="" style="display:none">
                            </div>
                            <div class="img-box">
                                <input type="hidden" class="attId"/>
                                <img src="" style="display:none">
                            </div>
                        </div>
                        <div class="charts-box-list" id="compareList">
                            <div class="charts-box" style="display:none">
                                <p class="charts-title"></p>
                                <div class="small-charts" id="charts1"></div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <span>企业</span>
                                    </div>
                                    <div class="col-md-6">
                                        <span class="enterCount"></span>
                                    </div>
                                    <div class="col-md-6">
                                        <span>占地</span>
                                    </div>
                                    <div class="col-md-6">
                                        <span class="square"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="charts-box" style="display:none">
                                <p class="charts-title"></p>
                                <div class="small-charts" id="charts2"></div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <span>企业</span>
                                    </div>
                                    <div class="col-md-6">
                                        <span class="enterCount"></span>
                                    </div>
                                    <div class="col-md-6">
                                        <span>占地</span>
                                    </div>
                                    <div class="col-md-6">
                                        <span class="square"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="charts-box" style="display:none">
                                <p class="charts-title"></p>
                                <div class="small-charts" id="charts3"></div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <span>企业</span>
                                    </div>
                                    <div class="col-md-6">
                                        <span class="enterCount"></span>
                                    </div>
                                    <div class="col-md-6">
                                        <span>占地</span>
                                    </div>
                                    <div class="col-md-6">
                                        <span class="square"></span>
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
<div class="footer">
    <p class="text-center">Copyright©2008-2016 中科点击（北京）科技有限公司-版权所有 京ICP备11012241-3号</p>
</div>
<!-- js 共用部分 start -->
<#include  "/common/script.ftl"/>
<!-- js 共用部分 end -->
<script src="/js/followPark/followPark.js"></script>


</body>
</html>