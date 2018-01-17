<!DOCTYPE html>
<html lang="en">

	<head>
	
		<!-- Basic -->
    	<meta charset="UTF-8" />

		<title>产业地图数据维护</title>
	 
		<!-- Mobile Metas -->
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		
	
		<!-- Favicon and touch icons -->
		<#include "/common/link.ftl">
		
		<!-- end: CSS file-->	
	    <link href="/assets/css/public.css" rel="stylesheet" />
		
		<!-- Head Libs -->
		<script src="/assets/plugins/modernizr/js/modernizr.js"></script>
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->		
		
	</head>
	
	<body>
	
		<!-- Start: Header -->
		<#include "/common/header.ftl">
		<!-- End: Header -->
		
		<!-- Start: Content -->
		<div class="container-fluid content">	
			<div class="row">
			
				<!-- Sidebar -->
				<#include "/common/sidebar.ftl">
				<!-- End Sidebar -->
						
				<!-- Main Page -->
				<div class="main sidebar-minified">			
					<!-- Page Header -->
					<div class="page-header">
						<div class="pull-left">
							<ol class="breadcrumb visible-sm visible-md visible-lg">								
								<li><a href="index.html"><i class="icon fa fa-home"></i>主页</a></li>
								<li><a href="#"><i class="fa fa-table"></i>表</a></li>
								<li class="active"><i class="fa fa-pencil-square-o"></i>产业信息</li>
							</ol>							
						</div>
						<div class="pull-right">
							<h2>产业地图数据管理</h2>
						</div>					
					</div>
					<!-- End Page Header -->
					<div class="row">
					 <div class="search-box">
                        <div class="search-group">
                            <div class="search-item-title">
                              	  产业
                            </div>
                            <div class="search-item-content">
                                <a href="javascript:void(0);"    id="人工智能"  class="search-item active">人工智能</a>
                                <a href="javascript:void(0);"    id="大数据"  class="search-item">大数据</a>
                                <a href="javascript:void(0);"    id="物联网"  class="search-item">物联网</a>
                                <a href="javascript:void(0);"     id="生物技术" class="search-item">生物技术</a>
                            </div>
                        </div>
                      </div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="panel panel-default bk-bg-white">
								<div class="panel-heading bk-bg-white">
									<h6><i class="fa fa-table red"></i><span class="break"></span>产业热度</h6>							
									<div class="panel-actions">
										<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
										<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-sm-6">
											<div class="bk-margin-bottom-10">
												<button id="addToTable" class="btn btn-info">添加 <i class="fa fa-plus"></i></button>
											</div>
										</div>
									</div>
									<table class="table table-bordered table-striped mb-none" id="datatable-editable1">
										<thead>
											<tr>
												<th>id</th>
												<th>地域</th>
												<th>数量</th>
												<th>产业</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="industryRank">
										</tbody>
									</table>
									<div class="page-box clearfix">
        								<ul class="page pull-right" id="page"></ul>
            						</div>
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="panel panel-default bk-bg-white">
								<div class="panel-heading bk-bg-white">
									<h6><i class="fa fa-table red"></i><span class="break"></span>重点实验室数目</h6>							
									<div class="panel-actions">
										<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
										<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<div class="panel-body">
									
									<table class="table table-bordered table-striped mb-none" id="datatable-editable2">
										<thead>
											<tr>
												<th>地域</th>
												<th>数量</th>
												<th>产业</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="industryinst">
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="panel panel-default bk-bg-white">
								<div class="panel-heading bk-bg-white">
									<h6><i class="fa fa-table red"></i><span class="break"></span>产业峰会</h6>							
									<div class="panel-actions">
										<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
										<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<div class="panel-body">
									<table class="table table-bordered table-striped mb-none" id="datatable-editable3">
										<thead>
											<tr>
												<th>文章id</th>
												<th>发布时间</th>
												<th>标题</th>
												<th>展出时间</th>
												<th>所属战略新兴产业</th>
												<th>产业标签</th>
												<th>产业</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="indusrySummit">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>					   
				</div>
				<!-- End Main Page -->	
		
				<!-- Footer -->
				<#include "/common/footer.ftl">
				<!-- End Footer -->

			
			</div>
		</div><!--/container-->
		
		
		<div class="clearfix"></div>		
		
		
		<!-- start: JavaScript-->
		
		<!-- Vendor JS-->				
		<#include "/common/script.ftl">
		<script src="/assets/js/industry/industryMap.js"></script>
		<!-- end: JavaScript-->
		
	</body>
	
</html>