<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>关注中心-科研机构</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="慧数招商平台，是一个关于园区产业招商的大数据管理平台">
    <meta name="keywords" content="慧数，招商，慧数招商，招商平台，园区，园区招商，园区招商平台，科技园，产业园，大数据，产业">
    <meta name="author" content="张鑫，慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
   <#include "/common/link.ftl"/>
    <link rel="stylesheet" href="/css/scientificResearchInstitution.css">
   
</head>
<body class="bg2">
<#include "/common/header3.ftl"/>
<div class="wrapper">
    <ul class="mark-box">
        <li class="active">
            <a class="mark-item" href="javascript:void(0);">人工智能</a>
        </li>
        <li>
            <a class="mark-item" href="javascript:void(0);">大数据</a>
        </li>
        <li>
            <a class="mark-item" href="javascript:void(0);">物联网</a>
        </li>
        <li>
            <a class="mark-item" href="javascript:void(0);">生物技术</a>
        </li>
    </ul>
    <div class="page-content">
       <#include "/common/UserSidebar.ftl"/>
        <div class="right-content">
            <div class="container">
                <div class="model-box">
                    <div class="model-body">
                        <div class="institution-box" id="organization_list">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include  "/common/footer.ftl"/>
<#include  "/common/script.ftl"/>
<script src="/js/follow/scientificResearchInstitution.js"></script>
</body>
</html>