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
			<table class="layui-table">
				<colgroup>
					<col width="150">
					<col width="200">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<td rowspan="3"><img alt="" src=""></td>
						<th>园区名称</th>
						<td>中关村软件园</td>
						<th>所属地</th>
						<td>北京市</td>
					</tr>
					<tr>
						<th>详细地址</th>
						<td>东北旺西路8号</td>
						<th>园区产业</th>
						<td>产业基地</td>
					</tr>
					<tr>
						<th>简介</th>
						<td colspan="3">位于海淀区</td>
					</tr>
				</tbody>
			</table>
			<div class="layui-tab">
				<ul class="layui-tab-title">
					<li class="layui-this">账号监管</li>
					<li>招商需求池</li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<h1>查询消费检测</h1>
						<div class="layui-row">
							<table class="layui-table">
								<colgroup>
									<col width="150">
									<col width="200">
									<col>
								</colgroup>
								<thead>
									<tr>
										<th colspan="9">
											<div class="layui-col-md3">
												<div id="mycxl"
													style="width: 330px; height: 220px; border: 1px solid #e6e6e6;"></div>
											</div>
											<div class="layui-col-md3">
												<div id="ksyje"
													style="width: 330px; height: 220px; border: 1px solid #e6e6e6;"></div>
											</div>
											<div class="layui-col-md3">
												<div id="xfje"
													style="width: 330px; height: 220px; border: 1px solid #e6e6e6;"></div>
											</div>
											<div class="layui-col-md3">
												<div id="syje"
													style="width: 330px; height: 220px; border: 1px solid #e6e6e6;"></div>
											</div>
										</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th>账号1</th>
										<th>账号2</th>
									</tr>
									<tr>
										<td>50000</td>
										<td>60000</td>
									</tr>
								</tbody>
							</table>
						</div>
						<h1>账号管理</h1>
						<button class="layui-btn layui-btn-primary"
							style="position: relative; left: 1205px; top: 10px;">添加账户</button>
						<table class="layui-table">
							<colgroup>
								<col width="150">
								<col width="200">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>账号ID</th>
									<th>登录帐号</th>
									<th>用户名</th>
									<th>开通时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>312312</td>
									<td>test</td>
									<td>ddd</td>
									<td>2017-09-25</td>
									<td>删除</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="layui-tab-item">
						<table class="layui-table" style="border: none;">
							<colgroup>
								<col width="150">
								<col width="200">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td><i class="layui-icon">&#xe64c;</i>模块来源</td>
									<td><a style="cursor: pointer;">招商情报(12)</a></td>
									<td><a style="cursor: pointer;">园区监管(24)</a></td>
									<td><a style="cursor: pointer;">精准招商(33)</a></td>
									<td><a style="cursor: pointer;">企业搜索(25)</a></td>
								</tr>
								<tr>
									<td><i class="layui-icon">&#xe857;</i>状态</td>
									<td><a style="cursor: pointer;">不限</a></td>
									<td><a style="cursor: pointer;">目标企业</a></td>
									<td><a style="cursor: pointer;">洽谈中月</a></td>
									<td><a style="cursor: pointer;">合同部署</a></td>
								</tr>
							</tbody>
						</table>
						<table class="layui-table">
							<colgroup>
								<col width="150">
								<col width="200">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>企业名</th>
									<th>产业领域</th>
									<th>企业规模</th>
									<th>所属地区</th>
									<th>模块来源</th>
									<th>负责人</th>
									<th>状态</th>
									<th>匹配度</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>贤心</td>
									<td>2016-11-29</td>
									<td>人生就像是一场修行</td>
									<td>人生就像是一场修行</td>
									<td>人生就像是一场修行</td>
									<td>人生就像是一场修行</td>
									<td>人生就像是一场修行</td>
									<td>人生就像是一场修行</td>
								</tr>
							</tbody>
						</table>
						<div align="center" id="fy">
							<a href="http://www.layui.com" class="layui-btn">上一页</a> <a
								href="http://www.layui.com" class="layui-btn">下一页</a>
						</div>
					</div>
				</div>
			</div>

			<#include "/common/script.ftl">
</body>
<script src="index.js"></script>
<script>
	//分页
	layui.use('laypage', function() {
		var laypage = layui.laypage;

		//执行一个laypage实例
		laypage.render({
			elem : 'fy' //注意，这里的 test1 是 ID，不用加 # 号
			,
			count : 50
		//数据总数，从服务端得到
		});
	});
	// 基于准备好的dom，初始化echarts实例
	var myCxl = echarts.init(document.getElementById('mycxl'));
	var ksyje = echarts.init(document.getElementById('ksyje'));
	var xfje = echarts.init(document.getElementById('xfje'));
	var syje = echarts.init(document.getElementById('syje'));
	option = {
		tooltip : {
			formatter : "{a} <br/>{b} : {c}%"
		},
		series : [ {
			name : '查询率',
			type : 'gauge',
			detail : {
				formatter : '{value}%'
			},
			data : [ {
				value : 70,
				name : '查询率'
			} ],
		} ]
	};
	option2 = {
		color : [ '#436EEE' ],
		series : [ {
			name : '可使用金额',
			type : 'pie',
			radius : [ '50%', '70%' ],

			data : [ {
				value : 80000,
				name : '可使用金额'
			} ]
		} ]
	};
	option3 = {
		color : [ '#90EE90', '#FAF0E6' ],
		series : [ {
			name : '消费金额',
			type : 'pie',
			radius : [ '50%', '70%' ],

			data : [ {
				value : 40000,
				name : '未消费金额'
			}, {
				value : 80000,
				name : '消费金额'
			} ]
		} ]
	};
	option4 = {
		color : [ '#FF7F24', '#EEE5DE' ],
		series : [ {
			name : '消费金额',
			type : 'pie',
			radius : [ '50%', '70%' ],

			data : [ {
				value : 40000,
				name : '未消费金额'
			}, {
				value : 80000,
				name : '剩余金额'
			} ]
		} ]
	};
	myCxl.setOption(option);
	ksyje.setOption(option2);
	xfje.setOption(option3);
	syje.setOption(option4);
</script>
</html>