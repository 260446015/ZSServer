<!DOCTYPE html>
<html lang="en">

<head>

<!-- Basic -->
<meta charset="UTF-8" />

<title>Editable Table | Fire - Admin Template</title>

<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />


<!-- Favicon and touch icons -->
<#include "/common/link.ftl">
<!-- end: CSS file-->


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
							<li><a href="index.html"><i class="icon fa fa-home"></i>Home</a></li>
							<li><a href="#"><i class="fa fa-table"></i>Tables</a></li>
							<li class="active"><i class="fa fa-pencil-square-o"></i>园区管理</li>
						</ol>
					</div>
					<div class="pull-right">
						<h2>Editable Table</h2>
					</div>
				</div>
				<!-- End Page Header -->
				
			<!-- End Main Page -->
			 <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-default bk-bg-white">
                    	<div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-8 text-left bk-vcenter">
                                        <h2 class="bk-margin-off">园区基本信息</h2>
                                    </div>
                                </div>
                                <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                                    <tbody>
	                                    <tr>
	                                        <td rowspan="3" id="logo"></td>
	                                        <th>园区名称</th>
	                                        <td id="parkName"></td>
	                                        <th>所属地</th>
	                                        <td id="parkArea"></td>
	                                    </tr>
	                                    <tr>
	                                        <th>详细地址</th>
	                                        <td id="address"></td>
	                                        <th>园区产业</th>
	                                        <td id="industry"></td>
	                                    </tr>
	                                    <tr>
	                                        <th>简介</th>
	                                        <td colspan="3" id="introduction"></td>
	                                    </tr>
                                	</tbody>
                                </table>
                   		</div>
                   		
                   		<div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-8 text-left bk-vcenter">
                                        <h2 class="bk-margin-off">园区动态</h2>
                                    </div>
                                </div>
                                <div class="row">
									<div class="col-sm-6">
										<div class="bk-margin-bottom-10">
											<button class="btn btn-info add">
												Add <i class="fa fa-plus"></i>
											</button>
										</div>
									</div>
								</div>
                                <table class="table table-bordered table-striped mb-none" id="datatable-editable">
										<thead>
											<tr>
												<th>全选<input type="checkbox" id="selectAll"/></th>
												<th>标题</th>
												<th>时间</th>
												<th>所属园区</th>
												<th>来源</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="condition">
										</tbody>
									</table>
									<div class="page-box clearfix">
        								<ul class="page pull-right" id="page"></ul>
            						</div>
                   		</div>
                   		
                   		<div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-8 text-left bk-vcenter">
                                        <h2 class="bk-margin-off">园区政策</h2>
                                    </div>
                                </div>
                                <div class="row">
									<div class="col-sm-6">
										<div class="bk-margin-bottom-10">
											<button class="btn btn-info add">
												Add <i class="fa fa-plus"></i>
											</button>
										</div>
									</div>
								</div>
                                <table class="table table-bordered table-striped mb-none" id="datatable-editable">
										<thead>
											<tr>
												<th>全选<input type="checkbox" id="selectAll2"/></th>
												<th>标题</th>
												<th>时间</th>
												<th>所属园区</th>
												<th>来源</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="policy">
										</tbody>
									</table>
									<div class="page-box clearfix">
        								<ul class="page pull-right" id="page2"></ul>
            						</div>
                   		</div>
                   		
                   		<div class="panel-body">
								<div class="row">
									<div class="col-xs-8 text-left bk-vcenter">
                                        <h2 class="bk-margin-off">园区企业</h2>
                                    </div>
								</div>
								<div class="search-box">
									<div class="search-group">
										<div class="search-item-title">成立时间</div>
										<div class="search-item-content">
											<a href="javascript:void(0);" class="search-item active">全部</a>
											<a href="javascript:void(0);" class="search-item">成立1年内</a> <a
												href="javascript:void(0);" class="search-item">成立1-5年</a> <a
												href="javascript:void(0);" class="search-item">成立5-10年</a> <a
												href="javascript:void(0);" class="search-item">成立10-15年</a>
											<a href="javascript:void(0);" class="search-item">成立15年以上</a>
										</div>
									</div>
									<div class="search-group">
										<div class="search-item-title">注册资本</div>
										<div class="search-item-content">
											<a href="javascript:void(0);" class="search-item active">全部</a>
											<a href="javascript:void(0);" class="search-item">0-100万</a>
											<a href="javascript:void(0);" class="search-item">100-200万</a>
											<a href="javascript:void(0);" class="search-item">200-500万</a>
											<a href="javascript:void(0);" class="search-item">500-1000万</a>
											<a href="javascript:void(0);" class="search-item">1000万以上</a>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="bk-margin-bottom-10">
											总数:<span id="total"></span>
										</div>
									</div>
								</div>
								<div class="col-sm-6">
										<div class="bk-margin-bottom-10">
											<button id="addToTable" class="btn btn-info" style="float:right">
												Add <i class="fa fa-plus"></i>
											</button>
										</div>
								</div>
								<table class="table table-bordered table-striped mb-none"
									id="datatable-editable">
									<thead>
										<tr>
											<th>企业名称</th>
											<th>企业地址</th>
											<th>高层管理</th>
											<th>所属园区</th>
											<th>注册资本</th>
											<th>注册时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="company">
									</tbody>
								</table>
								<div class="page-box clearfix">
									<ul class="page pull-right" id="page3"></ul>
								</div>
							</div>
                   	</div>	
               </div>
            </div>
			<!-- Footer -->
			<#include "/common/footer.ftl">
			<!-- End Footer -->


		</div>
	</div>
	<!--/container-->


	<div class="clearfix"></div>


	<!-- start: JavaScript-->

	<!-- Vendor JS-->
	<#include "/common/script.ftl">
	<script src="/assets/js/garden/gardenDetail.js"></script>
	<!-- end: JavaScript-->

</body>

</html>