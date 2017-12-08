 <!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-发行相关</title>
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
      		<#include "/common/companyDetail.ftl"/>
      		<div class="model-box">
      			 <div class="model-header">
                    <div class="modal-sub-title">发行相关</div>
                </div>
                <div class="model-body border-shadow-box pd0">
                     <table class="table table-striped">
                        <thead>
                        </thead>
                        <tbody id="issueRelated">
                        	<tr>
                        		<td>成立日期：</td>
                        		<td id="issueDate"></td>
                        		<td>上市日期：</td>
                        		<td id="listingDate"></td>
                        	</tr>
                        	<tr>
                        		<td>发行数量：</td>
                        		<td id="issueNumber"></td>
                        		<td>发行价格：</td>
                        		<td id="issuePrice"></td>
                        	</tr>
                        	<tr>
                        		<td>发行市盈率：</td>
                        		<td id="ipoRatio"></td>
                        		<td>预计募资：</td>
                        		<td id="expectedToRaise"></td>
                        	</tr>
                        	<tr>
                        		<td>首日开盘价：</td>
                        		<td id="openingPrice"></td>
                        		<td>发行中签率：</td>
                        		<td id="rate"></td>
                        	</tr>
                        	<tr>
                        		<td>实际募资：</td>
                        		<td id="actualRaised"></td>
                        		<td>主承销商：</td>
                        		<td id="mainUnderwriter"></td>
                        	</tr>
                        	<tr>
                        		<td>上市保荐人：</td>
                        		<td colspan="3" id="listingSponsor"></td>
                        	</tr>
                        	<tr>
                        		<td>历史沿革：</td>
                        		<td colspan="3" id="history"></td>
                        	</tr>
                        </tbody>
                    </table>
                </div>
            </div>
      		
      	</div>
<div class="footer">
    <p class="text-center">Copyright©2008-2016 中科点击（北京）科技有限公司-版权所有  京ICP备11012241-3号</p>
</div>
<!-- js 共用部分 start -->
<#include  "/common/script.ftl"/>
<script src="/js/companyDetails/common.js"></script>
<!-- js 共用部分 end -->
<script src="/js/companyDetails/issueRelated.js"></script>

</body>
</html>