
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<title>慧数招商-园区地图</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="慧数招商平台，是一个关于园区产业招商的大数据管理平台">
<meta name="keywords"
	content="慧数，招商，慧数招商，招商平台，园区，园区招商，园区招商平台，科技园，产业园，大数据，产业">
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
			<#include "/common/searchSidebar.ftl"/>
			<div class="posa-right-container">
				<#include "/common/companyDetail.ftl"/>
				<div class="model-box">
					<div class="model-header">
						<h3 class="model-title">基本信息</h3>
						<div class="modal-sub-title">企业主要人员</div>
					</div>
					<div class="text-center">
						<ul class="tabs tabs-custom">
							<li class="active"><a href="javascript:void(0);">高管</a></li>
							<li><a href="javascript:void(0);">股东</a></li>
						</ul>
					</div>

					<div class="modal-body border-shadow-box pd0">
						<div class="tableShow">
							<div class="slider-wrapper">
								<div id="myCarousel" data-wrap="false" data-interval="false"
									class="carousel slide">
									<!-- 轮播（Carousel）项目 -->
									<div class="carousel-inner" id="staff">
										
										
									</div>
									<!-- 轮播（Carousel）导航 -->
									<a class="carousel-control left" href="#myCarousel"
										data-slide="prev">&lsaquo; </a> <a
										class="carousel-control right" href="#myCarousel"
										data-slide="next">&rsaquo; </a>
								</div>
							</div>
						</div>
					<div class="tableShow">
						<table class="table table-striped">
							<thead>
								
							</thead>
							<tbody id="holder">


							</tbody>
						</table>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<p class="text-center">Copyright©2008-2016 中科点击（北京）科技有限公司-版权所有
			京ICP备11012241-3号</p>
	</div>
	<!-- js 共用部分 start -->
	<#include "/common/script.ftl"/>
	<script src="/js/companyDetails/common.js"></script>
	<!-- js 共用部分 end -->
	<script src="/js/companyDetails/staff.js"></script>

</body>
</html>