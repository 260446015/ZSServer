<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-融资速递</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="慧数招商平台，是一个关于园区产业招商的大数据管理平台">
    <meta name="keywords" content="慧数，招商，慧数招商，招商平台，园区，园区招商，园区招商平台，科技园，产业园，大数据，产业">
    <meta name="author" content="张鑫，慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
    <!-- css共用部分 start -->
    <#include "/common/link.ftl"/>
    <!-- css 共用部分 end -->

    <link rel="stylesheet" href="/css/summitMeetingDetails.css">
    <!-- js 兼容低版本IE start -->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- js 兼容低版本IE end -->
</head>
<body class="bg2">
<!-- header部分  start -->
<#include "/common/header.ftl"/>
<!-- header部分  end -->
<div class="wrapper">
    <div class="page-content">
        <#include "/common/sidebar2.ftl"/>
        <div class="right-content right-content-padding">
            <div class="border-shadow-box mt30">
                <div class="meeting-details-box" id="essay">
					
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
	$(function(){
		$.ajax({
       		url: "/summit/get?id="+'${Request.essayId}',
        	success: function (response) {
            	if(response.message!=null){
            		new Alert({flag:false,text:result.message,timer:1500}).show();
            	}else{
					if(response.data==null){
            			$('#essay').html('暂无该文章详情');
            		}else{
           				$('#essay').html('<div class="meeting-details-box-header"><h4>'+response.data.title+'</h4></div><div class="item">'+
                        			'<div><span class="iconfont icon-company"></span>涉及公司:<span>'+response.data.bus+'</span></div>'+
                        			'<div><span class="iconfont icon-shijian2"></span>发布时间:<span>'+response.data.publishDate+'</span></div></div>'+
									'<pre id="kr-article-article" class="kr-article-article meeting-details-box-text" v-html="content">'+response.data.content+'</pre>'+
                    				'<div class="item"><div><span class="iconfont icon-qianbi"></span>情报采集:<span>'+response.data.vector+'</span></div>'+
                        			'<div>情报原址:<a href="'+response.data.articleLink+'" target="_blank">'+response.data.articleLink+'</a></div></div>');
			        }
				}
        	}
    	});
	})
</script>
</body>
</html>