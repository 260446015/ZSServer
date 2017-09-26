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
				<li class="layui-this">试用会员<span class="layui-badge">9</span></li>
				<li>正式会员<span class="layui-badge">9</span></li>
				<li>预到期会员<span class="layui-badge">9</span></li>
			</ul>
		</div>
		<div class="layui-tab layui-tab-brief">
			<ul class="layui-tab-title">
			<li ><i class="layui-icon">&#xe637;</i>日期</li>
			 <li class="layui-this" >全部</li>
			 <li  >今日</li>
			 <li  >昨日</li>
			 <li  >近一周</li>
			</ul>
		</div>
		<div class="layui-tab layui-tab-brief">
			<ul class="layui-tab-title" type="hidden">
			<li ><i class="layui-icon">&#xe756;</i>会员类型</li>
			 <li class="layui-this" >正式会员</li>
			 <li  >试用会员</li>
			</ul>
		</div>
		<div class="layui-row">
		<div class="layui-col-md3">
		<input type="text" name="title" placeholder="请输入高校名称/相关人物关系名称"  class="layui-input">
		</div>
		<div class="layui-col-md4 ">
		 <button class="layui-btn layui-btn-normal layui-btn-radius">搜索</button>
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
	 layui.use('table', function(){
  		var table = layui.table;
  
  		//展示已知数据
  		table.render({
   			 elem: '#demo'
   		 ,data: [{
     		 "id": "10001"
     		 ,"username": "杜甫"
     		 ,"email": "xianxin@layui.com"
      		,"sex": "男"
      		,"city": "浙江杭州"
      		,"sign": "人生恰似一场修行"
      		,"experience": "116"
      
      ,"joinTime": "2016-10-14"
	  ,"address": "106"
	  
    }, {
      "id": "10002"
      ,"username": "李白"
      ,"email": "xianxin@layui.com"
      ,"sex": "男"
      ,"city": "浙江杭州"
      ,"sign": "人生恰似一场修行"
      ,"experience": "12"
      
      ,"joinTime": "2016-10-14"
      ,"LAY_CHECKED": true
	  ,"address": "106"
	  
    }, {
      "id": "10003"
      ,"username": "王勃"
      ,"email": "xianxin@layui.com"
      ,"sex": "男"
      ,"city": "浙江杭州"
      ,"sign": "人生恰似一场修行"
      ,"experience": "65"

      ,"joinTime": "2016-10-14"
	  ,"address": "106"
	  
    }, {
      "id": "10004"
      ,"username": "贤心"
      ,"email": "xianxin@layui.com"
      ,"sex": "男"
      ,"city": "浙江杭州"
      ,"sign": "人生恰似一场修行"
      ,"experience": "666"
      ,"ip": "192.168.0.8"
      ,"logins": "106"
      ,"joinTime": "2016-10-14"
	  ,"address": "106"
	  
	  
    }, {
      "id": "10005"
      ,"username": "贤心"
      ,"email": "xianxin@layui.com"
      ,"sex": "男"
      ,"city": "浙江杭州"
      ,"sign": "人生恰似一场修行"
      ,"experience": "86"
      
      ,"joinTime": "2016-10-14"
	  ,"address": "106"
	  
	  
    }, {
      "id": "10006"
      ,"username": "贤心"
      ,"email": "xianxin@layui.com"
      ,"sex": "男"
      ,"city": "浙江杭州"
      ,"sign": "人生恰似一场修行"
      ,"experience": "12"
      ,"ip": "192.168.0.8"
      ,"logins": "106"
      ,"joinTime": "2016-10-14"
	  ,"address": "106"
	  
	  
    }, {
      "id": "10007"
      ,"username": "贤心"
      ,"email": "xianxin@layui.com"
      ,"sex": "男"
      ,"city": "浙江杭州"
      ,"sign": "人生恰似一场修行"
      ,"experience": "16"
      
      ,"joinTime": "2016-10-14"
	  ,"address": "106"
	
    }, {
      "id": "10008"
      ,"username": "贤心"
      ,"email": "xianxin@layui.com"
      ,"sex": "男"
      ,"city": "浙江杭州"
      ,"sign": "人生恰似一场修行"
      ,"experience": "106"
	  ,"address": "106"
		
      ,"joinTime": "2016-10-14"
    }]
    ,height: 272
    ,cols: [[ //标题栏
      {field: 'id', title: 'ID', width: 80, sort: true}
      ,{field: 'username', title: '园区', width: 100}
      ,{field: 'email', title: '手机号', width: 150}
      ,{field: 'sign', title: '称呼', width: 100}
      ,{field: 'sex', title: '职务', width: 100}
      ,{field: 'city', title: '邮箱', width: 120}
      ,{field: 'experience', title: '所在省市', width: 100 }
	  ,{field: 'address', title: '详细地址', width: 177}
	  ,{field: 'appendix', title: '附录', width: 90 ,toolbar: '#appendix'}
	  ,{field: 'joinTime', title: '申请日期', width: 135}
	  ,{fixed: 'right',  align:'center', title: '操作', width: 100 ,toolbar: '#operation'}
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
</script>
</body>

</html>	