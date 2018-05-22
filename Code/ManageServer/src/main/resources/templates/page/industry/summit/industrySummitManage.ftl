<!DOCTYPE html>
<html lang="en">

	<head>
	
		<!-- Basic -->
    	<meta charset="UTF-8" />

		<title>产业峰会数据维护</title>
	 
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
							<h2>产业峰会数据管理</h2>
						</div>					
					</div>
					<!-- End Page Header -->
					<div class="row"> <div class="search-box">
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
                        <div class="search-group">
                            <div class="search-item-title">
                                排序
                            </div>
                            <div class="search-item-content">
                                <a href="javascript:void(0);" id="3-按热度" class="search-item active">按热度</a>
                                <a href="javascript:void(0);" id="3-按时间" class="search-item">按时间</a>
                            </div>
                        </div>
                    </div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="panel panel-default bk-bg-white">
								<div class="panel-heading bk-bg-white">
									<h6><i class="fa fa-table red"></i><span class="break"></span>Default</h6>							
									<div class="panel-actions">
										<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
										<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-sm-6">
											<div class="bk-margin-bottom-10">
												<button id="addToTable" class="btn btn-info">Add <i class="fa fa-plus"></i></button>
											</div>
										</div>
									</div>
									<table class="table table-bordered table-striped mb-none" id="datatable-editable">
										<thead>
											<tr>
												<th>标题</th>
												<th>发布时间</th>
												<th>地域</th>
												<th>地址</th>
												<th>展出时间</th>
												<th>峰会图片</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="industrySummitList">
										</tbody>
									</table>
									<div class="page-box clearfix">
        								<ul class="page pull-right" id="page"></ul>
            						</div>
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
		<script src="/assets/js/industry/industrySummit.js"></script>
		<!-- end: JavaScript-->
		
	</body>
	
</html>