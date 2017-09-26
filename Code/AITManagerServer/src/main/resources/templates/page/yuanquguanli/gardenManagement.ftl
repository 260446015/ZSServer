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
<body class="">
<div class="layui-layout layui-layout-admin">
	<#include "/common/header.ftl">
	<!-- Start: Content -->
	
	<#include "/common/sidebar.ftl">
	<!-- 内容 Page -->

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div class="layui-nav layui-bg-cyan">
				<ul class="" lay-filter="">
					<li class="layui-nav-item"><a href="">筛选设置:</a></li>
				</ul>
				<ul class="" lay-filter="">
					<li class="layui-nav-item"><a href="">地区:</a></li>
					<li class="layui-nav-item layui-this"><a href="">全部</a></li>
					<li class="layui-nav-item"><a href="">安徽</a></li>
					<li class="layui-nav-item"><a href="">北京</a></li>
					<li class="layui-nav-item"><a href="">河北</a></li>
					<li class="layui-nav-item"><a href="">山西</a></li>
					<li class="layui-nav-item"><a href="">内蒙古</a></li>
					<li class="layui-nav-item"><a href="">辽宁</a></li>
					<li class="layui-nav-item"><a href="">吉林</a></li>
					<li class="layui-nav-item"><a href="">黑龙江</a></li>
					<li class="layui-nav-item"><a href="">上海</a></li>
					<li class="layui-nav-item"><a href="">江苏</a></li>
					<li class="layui-nav-item"><a href="">浙江</a></li>
				</ul>
				<ul class="" lay-filter="">
					<li class="layui-nav-item"><a href="">会员级别:</a></li>
					<li class="layui-nav-item layui-this"><a href="">全部</a></li>
					<li class="layui-nav-item"><a href="">试用会员</a></li>
					<li class="layui-nav-item"><a href="">正式会员</a></li>
				</ul>
				<ul class="" lay-filter="">
					<li class="layui-nav-item"><a href="">新增会员:</a></li>
					<li class="layui-nav-item layui-this"><a href="">全部</a></li>
					<li class="layui-nav-item"><a href="">今日新增</a></li>
					<li class="layui-nav-item"><a href="">昨日新增</a></li>
					<li class="layui-nav-item"><a href="">近7天新增</a></li>
					<li class="layui-nav-item"><a href="">自定义</a></li>
				</ul>
			</div>
			<form action="" method="post" class="form-horizontal ">
				<div class="form-group">
					<div class="col-md-12">
						<div class="input-group">
							<span class="input-group-btn">
								<button type="button" class="btn btn-success">
									<i class="fa fa-search"></i>
								</button>
							</span> <input type="text" id="input1-group2" name="input1-group2"
								class="form-control" placeholder="请输入高校名称/相关人物名称" />
						</div>
					</div>
				</div>
			</form>
			<table class="layui-table">
				<colgroup>
					<col width="150">
					<col width="200">
					<col>
				</colgroup>
				<thead>
					<tr>
						<th>园区ID</th>
						<th>园区名称</th>
						<th>所属地区</th>
						<th>会员类型</th>
						<th>开通账号</th>
						<th>已到期</th>
						<th>未到期</th>
						<th>即将到期</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>

		<#include "/common/script.ftl">
</body>

</html>