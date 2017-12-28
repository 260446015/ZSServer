<!DOCTYPE html>
<html lang="en">

<head>

<!-- Basic -->
<meta charset="UTF-8" />

<title>xChart | Fire - Admin Template</title>

<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />


<!-- end: CSS file-->


<!-- Head Libs -->
<script src="/assets/plugins/modernizr/js/modernizr.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
<#include "/common/link.ftl"> <#include "/common/script.ftl">
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
			<div class="main ">
				<!-- Page Header -->
				<div class="page-header">
					<div class="pull-left">
						<ol class="breadcrumb visible-sm visible-md visible-lg">
							<li><a href="index.html"><i class="icon fa fa-home"></i>Home</a></li>
							<li><a href="#"><i class="icon fa fa-signal"></i>Visual
									chart</a></li>
							<li class="active"><i class="fa fa-retweet"></i>xChart</li>
						</ol>
					</div>
					<div class="pull-right">
						<h2>xChart</h2>
					</div>
				</div>
				<!-- End Page Header -->
				<table class="table table-bordered table-striped mb-none"
					id="datatable-editable">
					<thead>
						<tr>
							<th>产业标签</th>
							<th>企业名称</th>
							<th>企业简称</th>
							<th>行业标签</th>
							<th>所属战略新兴产业</th>
						</tr>
					</thead>
					<tbody id="indusCompany">
					</tbody>
				</table>
				<div class="page-box clearfix">
        				<ul class="page pull-right" id="page"></ul>
            	</div>
				<!-- End Main Page -->

				<!-- Footer -->
				<#include "/common/footer.ftl">
				<!-- End Footer -->

			</div>
		</div>
		<!--/container-->


		<div class="clearfix"></div>
</body>

</html>