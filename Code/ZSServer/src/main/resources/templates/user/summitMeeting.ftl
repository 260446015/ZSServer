<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>关注中心-产业峰会</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="慧数招商平台，是一个关于园区产业招商的大数据管理平台">
    <meta name="keywords" content="慧数，招商，慧数招商，招商平台，园区，园区招商，园区招商平台，科技园，产业园，大数据，产业">
    <meta name="author" content="张鑫，慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
   <#include "/common/link.ftl"/>
   
</head>
<body class="bg2">
<#include "/common/header3.ftl"/>
<div class="wrapper">
    <div class="page-content">
        <#include "/common/UserSidebar.ftl"/>
        <div class="right-content">
        	<div class="container">
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
                                <a href="javascript:void(0);" id="2-北京" class="search-item">北京</a>
                                <a href="javascript:void(0);" id="2-上海" class="search-item">上海</a>
                                <a href="javascript:void(0);" id="2-广州" class="search-item">广州</a>
                                <a href="javascript:void(0);" id="2-深圳"  class="search-item">深圳</a>
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
<#include  "/common/footer.ftl"/>
<#include  "/common/script.ftl"/>
<script src="/js/follow/summitMeeting.js"></script>
</body>
</html>