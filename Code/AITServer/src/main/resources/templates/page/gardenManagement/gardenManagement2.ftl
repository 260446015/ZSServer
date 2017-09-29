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

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" 
						aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					模态框（Modal）标题
				</h4>
			</div>
			<div class="modal-body">
				<form class="layui-form"  id="accountForm">
					<input type="hidden" name="userType" value="user"/>
 	 				<div class="layui-form-item">
    						<label class="layui-form-label">手机</label>
    					<div class="layui-input-block">
      						<input type="text" name="telphone" required  lay-verify="required" placeholder="请输入电话" autocomplete="off" class="layui-input">
    					</div>
  					</div>
  					<div class="layui-form-item">
    						<label class="layui-form-label">姓名</label>
    					<div class="layui-input-inline">
      						<input type="text" name="name" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
    					</div>
  					</div>
   <div class="layui-form-item">
    <label class="layui-form-label">邮箱</label>
    <div class="layui-input-inline">
      <input type="email" name="userEmail" required lay-verify="required" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">园区</label>
    <div class="layui-input-inline">
      <input type="text" name="park" required lay-verify="required" placeholder="请输入园区" autocomplete="off" class="layui-input">
    </div>
	</div>
    <div class="layui-form-item">
    <label class="layui-form-label">地域</label>
    <div class="layui-input-inline">
      <input type="text" name="area" required lay-verify="required" placeholder="请输入地域" autocomplete="off" class="layui-input">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">公司名称</label>
    <div class="layui-input-inline">
      <input type="text" name="company" required lay-verify="required" placeholder="请输入公司名称" autocomplete="off" class="layui-input">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">部门</label>
    <div class="layui-input-inline">
      <input type="text" name="department" required lay-verify="required" placeholder="请输入您所在的部门" autocomplete="off" class="layui-input">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">职务</label>
    <div class="layui-input-inline">
      <input type="text" name="job" required lay-verify="required" placeholder="请输入您的职务" autocomplete="off" class="layui-input">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">试用期限</label>
    <div class="layui-input-inline">
      <select name="time" lay-verify="">
  		<option value="">请选择试用期</option>
  		<option value="一年">一年</option>
  		<option value="两年">两年</option>
  		<option value="三年">三年</option>
	  </select>        
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-filter="formDemo" onclick="submit();">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
  </form>
			</div>
		</div><!-- /.modal-content -->
	</div>
</div>
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
						<td>${park.name}</td>
						<th>所属地</th>
						<td>${park.area}</td>
					</tr>
					<tr>
						<th>详细地址</th>
						<td>${park.address}</td>
						<th>园区产业</th>
						<td>${park.industry}</td>
					</tr>
					<tr>
						<th>简介</th>
						<td colspan="3">${park.introduction}</td>
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
						<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">添加账户</button>
						<table class="layui-table" id="parkAccount">
							<colgroup>
								<col width="150">
								<col width="200">
								<col>
							</colgroup>
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
									<td><a style="cursor: pointer;" href="#" onclick="myClick('不限')">不限</a></td>
									<td><a style="cursor: pointer;" href="#" onclick="myClick('目标企业')">目标企业</a></td>
									<td><a style="cursor: pointer;" href="#" onclick="myClick('洽谈中月')">洽谈中月</a></td>
									<td><a style="cursor: pointer;" href="#" onclick="myClick('合同部署')">合同部署</a></td>
								</tr>
							</tbody>
						</table>
						<table class="layui-table" id="accountList" style="border: none;">
							<colgroup>
								<col width="150">
								<col width="200">
								<col>
							</colgroup>
						</table>
					</div>
				</div>
			</div>

			<#include "/common/script.ftl">
</body>
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
	var status = '不限';
	$(function(){
		showParkAccount();
		myClick(status);
	});
	function myClick(status){
		var arr = new Array();
		arr.push(status);
		arr.push('${park.name}');
		arr.push('全部');
		var obj = {msg:arr};
		showCompanyList(obj);
	};
	function showParkAccount(){
		$.ajax({
			type : 'post',
			url : '/apis/back/garden/findParkAccount.json',
			async: false,
			contentType : 'application/json',
			data : JSON.stringify({userId:${park.id}}),
			success : function(response){
				layui.use('table', function(){
  					var table = layui.table;
  					
  					//展示已知数据
	  				table.render({
	   			 	elem: '#parkAccount'
	   			    ,data:response.data
		    		,height: 272
		   		    ,cols: [[ //标题栏
		      			{field: 'id', title: '账号ID', width: 180, sort: true}
		      			,{field: 'account', title: '登录帐号', width: 330}
		      			,{field: 'name', title: '用户名', width: 330}
		      			,{field: 'time', title: '开通时间', width: 330}
			 		    ,{fixed: 'right',  align:'center', title: '操作', width: 220,toolbar: '#operation' }
		   		    ]] 
		   			 ,skin: 'row' //表格风格
		    		 ,even: true
		    		 ,page: true //是否显示分页
		    		 ,limits: [5, 7, 10]
		    		 ,limit: 5 //每页默认显示的数量
		 		 });
			});
		}
		});
	};
	
	function showCompanyList(obj){
		$.ajax({
			type : 'post',
			url : '/apis/back/pool/getCompanyList.json',
			async: false,
			contentType : 'application/json',
			data : JSON.stringify(obj),
			success : function(response){
				layui.use('table', function(){
  					var table = layui.table;
  					
  					//展示已知数据
	  				table.render({
	   			 	elem: '#accountList'
	   			    ,data:response.data
		    		,height: 272
		   		    ,cols: [[ //标题栏
		      			{field: 'name', title: '企业名', width: 80, sort: true}
		      			,{field: 'fatherName', title: '所属企业', width: 130}
		      			,{field: 'label', title: '企业标签', width: 130}
		      			,{field: 'relation', title: '企业关系', width: 130}
		      			,{field: 'relationRemark', title: '企业关系备注', width: 130}
		      			,{field: 'area', title: '地域', width: 130}
		      			,{field: 'companyStatus', title: '企业状态', width: 130}
		      			,{field: 'responsiblePerson', title: '负责人', width: 130}
		      			,{field: 'investmentStatus', title: '招商状态', width: 130}
		      			,{field: 'investmentRemark', title: '招商备注', width: 130}
		      			,{field: 'createTime', title: '创建时间', width: 130}
			 		    ,{fixed: 'right',  align:'center', title: '操作', width: 120,toolbar: '#del' }
		   		    ]] 
		   			 ,skin: 'row' //表格风格
		    		 ,even: true
		    		 ,page: true //是否显示分页
		    		 ,limits: [5, 7, 10]
		    		 ,limit: 5 //每页默认显示的数量
		 		 });
			});
		}
		});
	};
	function addAccount(){
		$('#myModal').modal({
			keyboard: true
		});
	};
	function submit(){
		 $("#accountForm").ajaxSubmit();
	};
</script>
<script  type="text/html" id="operation">
 <a class="layui-btn layui-btn-mini" lay-event="edit" href="/apis/back/garden/dropParkAccount.json?id={{d.id}}">删除</a>
</script>
<script  type="text/html" id="del">
 <a class="layui-btn layui-btn-mini" lay-event="edit" href="/apis/back/garden/dropParkAccount.json?id={{d.id}}">删除</a>
</script>
</html>