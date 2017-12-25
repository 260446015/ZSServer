<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>关注中心-报告中心</title>
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
	        	<div class="model-box" id="report_list">
	                <div class="model-header">
	                </div>
	                <div class="search-box">
	                    <div class="search-group">
	                        <div class="search-item-title">
	                            <span class="icon-block"></span>
	                            年份
	                        </div>
	                        <div class="search-item-content" id="year_item">
	                        </div>
	                    </div>
	                    <div class="search-group">
	                        <div class="search-item-title">
	                            <span class="icon-block"></span>
	                            类型
	                        </div>
	                        <div class="search-item-content">
	                            <a href="javascript:void(0);" class="search-item active">按季度</a>
	                            <a href="javascript:void(0);" class="search-item">按月份</a>
	                        </div>
	                    </div>
	                </div>
	                <div class="model-body">
	                    <div class="row">
	                    	<ul id="biuuu_city_list"></ul>
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
<#include  "/common/footer.ftl"/>
<#include  "/common/script.ftl"/>
<script src="/js/follow/reportCenter.js"></script>
</body>
</html>