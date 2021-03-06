<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-报告详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="慧数招商平台，是一个关于园区产业招商的大数据管理平台">
    <meta name="keywords" content="慧数，招商，慧数招商，招商平台，园区，园区招商，园区招商平台，科技园，产业园，大数据，产业">
    <meta name="author" content="张鑫，慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
    <!-- css共用部分 start -->
    <#include "/common/link.ftl"/>
    <!-- css 共用部分 end -->

    <link rel="stylesheet" href="/css/summitMeetingDetails.css">
</head>
<body class="bg2">
<!-- header部分  start -->
<#if isUser>
	<#include "/common/header3.ftl"/>
<#else>
	<#include "/common/header.ftl"/>
</#if>
<!-- header部分  end -->
<div class="wrapper">
    <div class="page-content">
    	<#if isUser>
        	<#include "/common/UserSidebar.ftl"/>
        <#else>
        	<#include "/common/sidebar2.ftl"/>
        </#if>
        <div class="posa-right-container right-content-padding">
        	<div class="container">
            <div class="border-shadow-box mt30">
                <div class="meeting-details-box" id="essay">

                </div>
            </div>
            </div>
        </div>
    </div>
</div>
<#include  "/common/footer.ftl"/>
<!-- js 共用部分 start -->
<#include  "/common/script.ftl"/>
<!-- js 共用部分 end -->
<script type="text/javascript">
$("#report").addClass("active");
$("#followItem").addClass("active");
$("#reportItem").addClass("active");
	$(function(){
		$.ajax({
       		url: "/apis/report/getReportContent.json?id="+'${fileId}',
        	success: function (response) {
				if(response.success){
					if(response.data.imageUrl==''){
						$('#essay').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
					}else{
						$('#essay').html(show(response.data));
						$(window).scroll(function(){
							var windsc =$(window).scrollTop();
							if(windsc > 200){
								$('.scrollTop').css("display","block")
							}else{
								$('.scrollTop').css("display","none")
							}
						})
						$(".scrollTop").on("click",function(){
							$('body,html').animate({
								scrollTop:0
							},2000)
							return false;
						})
					}
            	}else{
           			new Alert({flag:false,text:result.message,timer:1500}).show();
				}
        	}
    	});
	})
	function show(data){
	    var url=data.url.split('/pdf/pdf/');
		var follow='<a href="http://58.16.181.24:9322/fileserver/file/downLoad.do?filePath='+url[1]+'" onclick="aa()" class="pull-right">点击下载报告</a>';
		var before='<div class="meeting-details-box-header"><h4>'+data.name+follow+'</h4></div><div class="item">';
		var inner='';
		var strs= new Array(); 
		strs=data.imageUrl; 

		for (var i= 0; i<strs.length;i++){
			inner+='<img src="'+strs[i].imageUrl+'" style="width: 100%;height: 100%;">';
		} 
        var after= inner+'<span class="scrollTop">返回顶部</span></div>';
       return before+after;
	}
	function aa(){
		$.ajax({
       		url: "/apis/report/addReportRecord.json?id="+'${fileId}',
        	success: function (response) {
        	}
    	});
	}
</script>
</body>
</html>