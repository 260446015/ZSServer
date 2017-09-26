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
	
<!-- End Page Header -->
 <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
		<div class="layui-tab layui-tab-brief">
			<ul class="layui-tab-title">
				<li class="layui-this" onclick="myClick(0,0)">待审核会员<span class="layui-badge">9</span></li>
				<li onclick="myClick(0,1)">预到期会员<span class="layui-badge">9</span></li>
			</ul>
		</div>
		<div class="layui-tab layui-tab-brief">
			<ul class="layui-tab-title">
			<li ><i class="layui-icon">&#xe637;</i>日期</li>
			 <li class="layui-this" onclick="myClick(1,'全部')">全部</li>
			 <li  onclick="myClick(1,'今日')">今日</li>
			 <li onclick="myClick(1,'昨日')" >昨日</li>
			 <li  onclick="myClick(1,'近一周')">近一周</li>
			</ul>
		</div>
		<div class="layui-tab layui-tab-brief">
			<ul class="layui-tab-title" type="hidden">
			<li ><i class="layui-icon">&#xe756;</i>会员类型</li>
			 <li class="layui-this" onclick="myClick(2,1)" >正式会员</li>
			 <li  onclick="myClick(2,0)">试用会员</li>
			</ul>
		</div>
		<div class="layui-row">
		<div class="layui-col-md3">
		<input type="text" name="title" placeholder="请输入园区名称/人物称呼"  class="layui-input">
		</div>
		<div class="layui-col-md4 ">
		 <button class="layui-btn layui-btn-normal layui-btn-radius" onclick="mySearch()">搜索</button>
		</div>
		</div>

	<table id="demo"></table>
	</div>
<form class="layui-form" type="hidden" action="">

</form>
</div>

	<#include "/common/script.ftl">
	<script  type="text/html" id="appendix">
	<a class="layui-btn layui-btn-mini" lay-event="detail">查看详情</a>
</script>
<script  type="text/html" id="operation">
 <a class="layui-btn layui-btn-mini" lay-event="edit">开通试用</a>
</script>
<script>
	var tab=0;
	var type=1;
	var day='全部';
	$(function(){
		myClick(0,0);
	})
	function mySearch(){
		var search=$('input[name=title]').val(); 
		var obj={type:type,
            day:day,
            search:search};
            if(tab==0){
            	myRequest(obj);
            }else{
            	myRequest2(obj);
            }
	}
	function myClick(a,b){
		if(a==0){
			tab=b;
		}else if(a==1){
			day=b;
		}else{
			type=b;
		}
		var obj={type:type,
            day:day};
            if(tab==0){
            	myRequest(obj);
            }else{
            	myRequest2(obj);
            }
	}
	function myRequest(obj){
		$.ajax({
            type: 'post',
            url: "/apis/back/admin/getAccountList.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (response) {
                layui.use('layer', function(){
								  var layer = layui.layer;
								  if(response.success){
								 		showTable(response.data);
			                        }else{
			                        	if(response.code!=null){
			                        		window.location.href="/login";
			                        	}else{
			                       			layer.alert(response.message);
			                       		}
			                        }
								}); 
            }
        });
	}
	function myRequest2(obj){
		$.ajax({
            type: 'post',
            url: "/apis/back/admin/getWarningAccountList.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (response) {
                layui.use('layer', function(){
								  var layer = layui.layer;
								  if(response.success){
								 		showTable(response.data);
			                        }else{
			                        	if(response.code!=null){
			                        		window.location.href="/login";
			                        	}else{
			                       			layer.alert(response.message);
			                       		}
			                        }
								}); 
            }
        });
	}
	function showTable(dataList){
		layui.use('table', function(){
	  		var table = layui.table;
	  
	  		//展示已知数据
	  		table.render({
	   			 elem: '#demo'
	   		 ,data:dataList
		    ,height: 272
		    ,cols: [[ //标题栏
		      {field: 'id', title: 'ID', width: 80, sort: true}
		      ,{field: 'userPark', title: '园区', width: 170}
		      ,{field: 'telphone', title: '手机号', width: 150}
		      ,{field: 'realName', title: '称呼', width: 120}
		      ,{field: 'userJob', title: '职务', width: 120}
		      ,{field: 'userEmail', title: '邮箱', width: 170}
			  ,{field: 'appendix', title: '附录', width: 110 ,toolbar: '#appendix'}
			  ,{field: 'createTime', title: '申请日期', width: 195}
			  ,{fixed: 'right',  align:'center', title: '操作', width: 110 ,toolbar: '#operation'}
		    ]] 
		    ,skin: 'row' //表格风格
		    ,even: true
		    ,page: true //是否显示分页
		    ,limits: [5, 7, 10]
		    ,limit: 5 //每页默认显示的数量
		  });
		  table.on('tool(demo)', function(obj){
		    var data = obj.data;
		    if(obj.event === 'detail'){
		      layer.msg('ID：'+ data.id + ' 的查看操作');
		    } });
		});
	}

</script>
</body>

</html>	