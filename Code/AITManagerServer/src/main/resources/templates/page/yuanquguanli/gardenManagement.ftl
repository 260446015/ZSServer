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
			<div class="layui-tab layui-tab-brief">
				<ul class="layui-tab-title" lay-filter="">
					<li>筛选设置:</li>
				</ul>
				<ul class="layui-tab-title" lay-filter="" id="area">
					<li>地区:</li>
					<li class="layui-this" onclick="myClick('全部',type,day)">全部</li>
					<li onclick="myClick('安徽',type,day)">安徽</li>
					<li onclick="myClick('北京',type,day)">北京</li>
					<li onclick="myClick('河北',type,day)">河北</li>
					<li onclick="myClick('山西',type,day)">山西</li>
					<li onclick="myClick('内蒙古',type,day)">内蒙古</li>
					<li onclick="myClick('辽宁',type,day)">辽宁</li>
					<li onclick="myClick('吉林',type,day)">吉林</li>
					<li onclick="myClick('黑龙江',type,day)">黑龙江</li>
					<li onclick="myClick('上海',type,day)">上海</li>
					<li onclick="myClick('江苏',type,day)">江苏</li>
					<li onclick="myClick('浙江',type,day)">浙江</li>
				</ul>
				<ul class="layui-tab-title" lay-filter="" id="level">
					<li>会员级别:</li>
					<li class="layui-this" onclick="myClick(area,'全部',day)">全部</li>
					<li onclick="myClick(area,'0',day)">试用会员</li>
					<li onclick="myClick(area,'1',day)">正式会员</li>
				</ul>
				<ul class="layui-tab-title" lay-filter="">
					<li>新增会员:</li>
					<li class="layui-this" onclick="myClick(area,type,'全部')">全部</li>
					<li onclick="myClick(area,type,'今日')">今日新增</li>
					<li onclick="myClick(area,type,'昨日')">昨日新增</li>
					<li onclick="myClick(area,type,'近一周')">近7天新增</li>
				</ul>
			</div>
			<div class="layui-row">
		<div class="layui-col-md3">
		<input type="text" name="title" placeholder="请输入园区名称"  class="layui-input">
		</div>
		<div class="layui-col-md4 ">
		 <button class="layui-btn layui-btn-normal layui-btn-radius" onclick="mySearch()">搜索</button>
		</div>
		</div>
			<table class="layui-table" id="table" lay-filter="demoEvent">
				
			</table>
		</div>

		<#include "/common/script.ftl">
</body>
<script>
	var area='全部';
	var type='全部';
	var day='全部';
	var gid = 0;
	function mySearch(){
		var search=$('input[name=title]').val(); 
		var datalist= new Array();
			datalist.push(area);         
			datalist.push(type);         
			datalist.push(day); 
		var obj={msg:datalist,search:search};
            myRequest(obj);
	}
	$(function(){
		myClick(area,type,day);
	});
	function myClick(a,t,d){
		area = a;
		type = t;
		day = d;
		var datalist= new Array();
			datalist.push(a);         
			datalist.push(t);         
			datalist.push(d); 
		var obj={msg:datalist};
        myRequest(obj);
	}
	function myRequest(obj){
		$.ajax({
            type: 'post',
            url: "/apis/back/garden/getGardenList.json",
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
	   			 elem: '#table'
	   		 ,data:dataList
		    ,height: 272
		    ,cols: [[ //标题栏
		      {field: 'id', title: '园区ID', width: 80, sort: true}
		      ,{field: 'parkName', title: '园区名称', width: 170}
		      ,{field: 'area', title: '所属地区', width: 150}
		      ,{field: 'accountCount', title: '会员类型', width: 120}
		      ,{field: 'checkAccountCount', title: '开通账号', width: 120}
		      ,{field: 'expireAccountCount', title: '已到期', width: 170}
			  ,{field: 'normalAccountCount', title: '未到期', width: 110}
			  ,{field: 'dueAccountCount', title: '即将到期', width: 195}
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
	function search(){
		var context = $("#input1-group2").html();
		myClick('全部','全部','全部');
	}
</script>
<script  type="text/html" id="operation">
 <a class="layui-btn layui-btn-mini" lay-event="edit" href="/apis/back/garden/findParkInformation.json?id={{d.id}}">查看详情</a>
</script>

</html>