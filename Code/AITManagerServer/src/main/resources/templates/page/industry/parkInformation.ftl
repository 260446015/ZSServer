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
					    <div class="layui-nav layui-bg-cyan">
				           
				            <ul class="" lay-filter="">
					            <li class="layui-nav-item"><a href="">产业类型：</a></li>
								<li class="layui-nav-item layui-this"><a href="">不限</a></li>
					            <li class="layui-nav-item "><a href="">互联网</a></li>
					            <li class="layui-nav-item"><a href="">生态科技</a></li>
					            <li class="layui-nav-item"><a href="">环保产业</a></li>
					            <li class="layui-nav-item"><a href="">物流产业</a></li>
					            <li class="layui-nav-item"><a href="">制造产业</a></li>
					            <li class="layui-nav-item"><a href="">医药化学</a></li>
					            <li class="layui-nav-item"><a href="">新能源</a></li>
				            </ul>
				           
				            <ul class="" lay-filter="">
					            <li class="layui-nav-item"><a href="">区域：</a></li>
					            <li class="layui-nav-item layui-this"><a href="">全部</a></li>
					            <li class="layui-nav-item"><a href="">今日</a></li>
					            <li class="layui-nav-item"><a href="">昨日</a></li>
					            <li class="layui-nav-item"><a href="">近3天</a></li>
				            </ul>
			            </div>
			            <div class="layui-col-md12 paddingX20" style="margin-top:20px;">
			            <form class="layui-form" action="">
			                <div class="layui-form-item">
                                <input type="text" name="title" required  lay-verify="required" placeholder="请输入搜索内容" autocomplete="off" class="layui-input" style="width:200px;display:inline"><button class="layui-btn layui-btn-normal">搜索</button>
                            </div>
                        </form>
			            <table class="layui-table" lay-size="lg">
			            	<colgroup>
			            		<col width="100">
			            		<col width="75">
			            		<col width="75">
			            		<col width="200">
			            		<col width="200">
			            		<col width="110">
								<col width="100">
			            	</colgroup>
			            	<thead>
			            		<tr>
			            			<th>园区名称</th>
			            			<th>园区级别</th>
			            			<th>地域（城市）</th>
			            			<th>具体地址</th>
			            			<th>园区产业</th>
			            			<th>成立时间</th>
									<th>园区面积</th>
			            		</tr>
			            	</thead>
			            	<tbody>
			            		<tr>
			            			<td><a href="园区详情.html">清华创业园</a></td>
			            			<td>市级</td>
			            			<td>北京</td>
			            			<td>海淀区清华科技园学研大厦A308</td>
			            			<td>互联网+光伏机电生物医药食品加工精英配套石油化工文化创意其他</td>
			            			<td>1999年8月20日</td>
									<td>0平方公里(已开发0平方公里)</td>
			            		</tr>
			            		<tr>
			            			<td><a href="园区详情.html">清华创业园</a></td>
			            			<td>市级</td>
			            			<td>北京</td>
			            			<td>海淀区清华科技园学研大厦A308</td>
			            			<td>互联网+光伏机电生物医药食品加工精英配套石油化工文化创意其他</td>
			            			<td>1999年8月20日</td>
									<td>0平方公里(已开发0平方公里)</td>
			            		</tr>
			            		<tr>
			            			<td><a href="园区详情.html">清华创业园</a></td>
			            			<td>市级</td>
			            			<td>北京</td>
			            			<td>海淀区清华科技园学研大厦A308</td>
			            			<td>互联网+光伏机电生物医药食品加工精英配套石油化工文化创意其他</td>
			            			<td>1999年8月20日</td>
									<td>0平方公里(已开发0平方公里)</td>
			            		</tr>
			            		<tr>
			            			<td><a href="园区详情.html">清华创业园</a></td>
			            			<td>市级</td>
			            			<td>北京</td>
			            			<td>海淀区清华科技园学研大厦A308</td>
			            			<td>互联网+光伏机电生物医药食品加工精英配套石油化工文化创意其他</td>
			            			<td>1999年8月20日</td>
									<td>0平方公里(已开发0平方公里)</td>
			            		</tr>
			            	</tbody>
			            </table>
			        </div>
		</div>
	</div>
	<#include "/common/script.ftl">
</body>

</html>	