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
							<li><a href="/"><i class="icon fa fa-home"></i>首页</a></li>
						</ol>
					</div>
					<div class="pull-right">
						<h2>首页</h2>
					</div>
				</div>
<!-- End Page Header -->
<div class="panel panel-default bk-bg-white">
			<div class="panel panel-default bk-bg-white">
								<div class="panel-body">
									<button type="button" class="bk-margin-5 btn btn-default active">试用会员</button>
									<button type="button" class="bk-margin-5 btn btn-primary active">正式会员</button>
									<button type="button" class="bk-margin-5 btn btn-success active">预到期会员</button>
									 
									<div class="panel-body bk-fg-info">
										<div class="row fontawesome-icon-list">
											<ul class="list">
												<li class="item"><i class="fa fa-calendar"></i>日期</li>
												<li class="item selected">全部</li>
												<li class="item">昨天</li>
												<li class="item">今天</li>
												<li class="item">近一周</li>
											</ul>
											</div>
										</div>
									<div>
									 
								</div>
								
					</div>
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="panel panel-default bk-bg-white">
								<div class="panel-heading bk-bg-white">
									<h6><i class="fa fa-table red"></i><span class="break"></span>试用账号审核</h6>
									<div class="panel-actions">
										<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
										<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<div class="panel-body">
									<form action="" method="post" class="form-horizontal ">
										<div class="form-group">
											<div class="col-md-12">
												<div class="input-group">
													<span class="input-group-btn">
													<button type="button" class="btn btn-success"><i class="fa fa-search"></i></button>
													</span>
													<input type="text" id="input1-group2" name="input1-group2" class="form-control" placeholder="请输入高校名称/相关人物名称" />
												</div>
											</div>
										</div>
									</form>

									
									<table class="table table-bordered table-striped mb-none" id="datatable-editable">
										<thead>
										<tr>
											<th>园区</th>
											<th>手机号</th>
											<th>称呼</th>
											<th>职务</th>
											<th>邮箱</th>
											<th>所在省份</th>
											<th>详细地址</th>
											<th>附录</th>
											<th>申请日期</th>
											<th>操作</th>
										</tr>
										</thead>
										<tbody>
										<tr class="gradeX">
											<td>中关村软件园</td>
											<td>17600000000</td>
											<td>张伟</td>
											<td>招商专员</td>
											<td>qhdx@163.com</td>
											<td>北京</td>
											<td>北京海淀区中关村软件园2期华胜天成</td>
											<td><a href="#" >查看详情</a></td>
											<td>2016/6/23</td>
											<td class="actions">
											  <div class="col-lg-12 col-md-4 col-sm-4 col-xs-4">
													<label class="switch bk-margin-top-5">
													  <input type="checkbox" class="switch-input" />
													  <span class="switch-label" data-on="On" data-off="Off"></span>
													  <span class="switch-handle"></span>
													</label>
												</div>
											</td>
										</tr>
										<tr class="gradeA">
											<td>中关村软件园</td>
											<td>17600000000</td>
											<td>张伟</td>
											<td>招商专员</td>
											<td>qhdx@163.com</td>
											<td>北京</td>
											<td>北京海淀区中关村软件园2期华胜天成</td>
											<td><a href="#" >查看详情</a></td>
											<td>2016/6/23</td>
											<td class="actions">
											  <div class="col-lg-12 col-md-4 col-sm-4 col-xs-4">
													<label class="switch bk-margin-top-5">
													  <input type="checkbox" class="switch-input" />
													  <span class="switch-label" data-on="On" data-off="Off"></span>
													  <span class="switch-handle"></span>
													</label>
												</div>
											</td>
										</tr>
										

										</tbody>
									</table>
								</div>
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