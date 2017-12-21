<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>关注中心-企业</title>
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
                    <div class="search-box">
                        <div class="search-group">
                            <div class="search-item-title">
                                产业
                            </div>
                            <div class="search-item-content" id="gardenIndustry">
                            	<a href="javascript:void(0);" class="search-item active">全部</a>
                            </div>
                        </div>
                        <div class="search-group">
                            <div class="search-item-title">
                               成立时间
                            </div>
                            <div class="search-item-content">
                            	<a href="javascript:void(0);" class="search-item active">全部</a>
                            	<a href="javascript:void(0);" class="search-item">1年内</a>
                            	<a href="javascript:void(0);" class="search-item">1-5年</a>
                            	<a href="javascript:void(0);" class="search-item">5-10年</a>
                            	<a href="javascript:void(0);" class="search-item">10-15年</a>
                            	<a href="javascript:void(0);" class="search-item">15年以上</a>
                            </div>
                        </div>
                         <div class="search-group">
                            <div class="search-item-title">
                               注册资本
                            </div>
                            <div class="search-item-content">
                            	<a href="javascript:void(0);" class="search-item active">全部</a>
                            	<a href="javascript:void(0);" class="search-item">0-100万</a>
                            	<a href="javascript:void(0);" class="search-item">100-200万</a>
                            	<a href="javascript:void(0);" class="search-item">200-500万</a>
                            	<a href="javascript:void(0);" class="search-item">500-1000万</a>
                            	<a href="javascript:void(0);" class="search-item">1000万以上</a>
                            </div>
                        </div>
                        <div class="search-group">
                            <div class="search-item-title">
                                地域
                            </div>
                            <div class="search-item-content" id="gardenArea">
                            	<a href="javascript:void(0);" class="search-item active">全部</a>
                            </div>
                        </div>
                        <div class="search-group">
                            <div class="search-item-title">
                                企业分组
                            </div>
                            <div class="search-item-content" >
                                <a href="javascript:void(0);" id="gardenGroup" class="search-item active">全部</a>
                                <a href="javascript:void(0);" class="add-item active" style= "margin-left:80%;" onclick="addGroup();">+添加企业分组</a>
                            </div>
                        </div>
                    </div>
                    <div class="model-body">
                        <div class="row" id="city_list">
                            
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
<script src="/js/follow/enterprise.js"></script>
</body>
</html>