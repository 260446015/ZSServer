<!DOCTYPE html>
<html lang="en">
<head>
<!-- Basic -->
<meta charset="UTF-8" />
<title>慧数招商后台系统</title>
<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<#include "/common/link.ftl">
</head>
<body>
	<#include "/common/header.ftl">
	<!-- Start: Content -->
	<div class="container-fluid content">
		<div class="row">
			<#include "/common/sidebar.ftl">
	<!-- 内容 Page -->
	<div class="main ">
				<!-- Page Header -->
				<div class="page-header">
						<div class="pull-left">
							<ol class="breadcrumb visible-sm visible-md visible-lg">
								<li><a href="index.html"><i class="icon fa fa-home"></i>首页</a></li>
							</ol>
						</div>
					</div>
<!-- End Page Header -->
	
		<div class="row">
					<div class="panel-heading panel-heading-transparent bk-border-off">
						<h4><strong>园区会员总数：42</strong></h4>
						<h4><strong>即将到期会员：7</strong></h4>
						<h5><strong>会员分布</strong></h5>
					</div>
					<div class="col-sm-6">
						<div class="panel panel-default bk-bg-white">
							<div class="panel-heading bk-bg-white">
								<h6><i class="fa fa-random red"></i><span class="break"></span>会员区域分布</h6>
								<div class="panel-actions">
									<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
									<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<div id="donutchart" style="height:300px"></div>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="panel panel-default bk-bg-white">
							<div class="panel-heading bk-bg-white">
								<h6><i class="fa fa-random red"></i><span class="break"></span>产业在园区中的占比</h6>
								<div class="panel-actions">
									<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
									<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<div id="donutchart" style="height:300px"></div>
							</div>
						</div>
					</div>
				</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="panel panel-default bk-bg-white">
							<div class="panel-heading bk-bg-white">
								<h6><i class="fa fa-table red"></i><span class="break"></span>数量统计</h6>
								<div class="panel-actions">
									<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
									<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body">
								<table class="table table-bordered table-striped table-condensed mb-none">

									<tbody>
									<tr>
										<td class="text-right">北京</td>
										<td class="text-right hidden-xs hidden-sm">上海</td>
										<td class="text-right hidden-xs">深圳</td>
										<td class="text-right">广州</td>
										<td class="text-right hidden-xs hidden-sm">天津</td>
										<td class="text-right hidden-xs hidden-sm">苏州</td>
										<td class="text-right">青岛</td>
									</tr>
									<tr>
										<td class="text-right">4</td>
										<td class="text-right hidden-xs hidden-sm">3</td>
										<td class="text-right hidden-xs">2</td>
										<td class="text-right">5</td>
										<td class="text-right hidden-xs hidden-sm">10</td>
										<td class="text-right hidden-xs hidden-sm">6</td>
										<td class="text-right">8</td>
									</tr>
									</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
	
	
	
	<!-- 内容 END Page -->
			<#include "/common/footer.ftl">
		</div>
	</div>
<!--/container-->
	<div class="clearfix"></div>
	<#include "/common/script.ftl">
</body>

</html>	