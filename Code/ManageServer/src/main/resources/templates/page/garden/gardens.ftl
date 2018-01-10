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
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="panel panel-default bk-bg-white">
							<div class="panel-heading bk-bg-white">
								<h6>
									<i class="fa fa-table red"></i><span class="break"></span>Default
								</h6>
								<div class="panel-actions">
									<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
									<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-sm-6">
										<div class="bk-margin-bottom-10">
											<button id="addToTable" class="btn btn-info">
												Add <i class="fa fa-plus"></i>
											</button>
										</div>
									</div>
								</div>
								<div class="search-box">
									<div class="search-group">
										<div class="search-item-title">产业</div>
										<div class="search-item-content" id="gardenIndustry">
											<a class="search-item active" href="javascript:void(0);">全部</a>
										</div>
									</div>
									<div class="search-group">
										<div class="search-item-title">区域</div>
										<div class="search-item-content" id="gardenArea">
											<a class="search-item active" href="javascript:void(0);">全部</a>
										</div>
									</div>
									<div class="search-group">
										<div class="search-item-title">排序</div>
										<div class="search-item-content">
											<a class="search-item active" href="javascript:void(0);">按企业数</a>
											<a class="search-item" href="javascript:void(0);">按园区占地</a>
										</div>
									</div>
								</div>
								<table class="table table-bordered table-striped mb-none"
									id="datatable-editable">
									<thead>
										<tr>
											<th>地址</th>
											<th>园区名</th>
											<th>园区面积</th>
											<th>所属省份</th>
											<th>产业类型</th>
											<th hight="60px" width="100px">园区图片</th>
											<th>入驻企业数量</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="gardenList">
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
	</div>
	<!--/container-->


	<div class="clearfix"></div>


	<!-- start: JavaScript-->

	<!-- Vendor JS-->
	<#include "/common/script.ftl">
	<script src="/assets/js/garden/gardens.js"></script>
	<!-- end: JavaScript-->

</body>

</html>