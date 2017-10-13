<!DOCTYPE html>
<html lang="en">
<head>
<!-- Basic -->
<meta charset="UTF-8" />
<title>慧数招商后台系统</title>
<!-- Mobile Metas -->
<!--文章详情页面-->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<#include "/common/link.ftl">
<style>
	.layui-form-label{
		width : 200px;
	}
	.layui-input-block {
	  margin-left:200px;
	}
	form{   
	 width: 700px;
    margin: 0 auto;
    }
</style>
</head>
<body class="">
	<div class="layui-layout layui-layout-admin">
		<#include "/common/header.ftl">
		<!-- Start: Content -->

		<#include "/common/sidebar.ftl">
		<!-- 内容 Page -->
		<div class="layui-body">
			<!-- 内容主体区域 -->
			<a href="javascript:window.history.go(-1);"
				class="layui-btn layui-btn-radius layui-btn-primary">返回</a>

			<form class="layui-form" action="/art/saveArt.json" id="artForm" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">标题</label>
					<div class="layui-input-block">
						<input name="title" lay-verify="title" autocomplete="off"
							placeholder="请输入标题" class="layui-input" type="text">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">作者</label>
					<div class="layui-input-block">
						<input name="author" lay-verify="required" placeholder="请输入作者"
							autocomplete="off" class="layui-input" type="text">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">产业分类</label>
					<div class="layui-input-block">
						<select name="industry" lay-filter="chanye" lay-verify="required">
							<option value=""></option>
							<option value="0"></option>
							<option value="1" selected="">高科技</option>
							<option value="2">其他</option>
							<option value="3">港口物流</option>
							<option value="4">互联网+</option>
							<option value="5">文化创意</option>
							<option value="6">精英配套</option>
						</select>
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">发布时间</label>
					<div class="layui-input-block">
						<input name="publishTime" lay-verify="publishTime" placeholder="请输入发布时间"
							autocomplete="off" class="layui-input" type="text">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">来源</label>
					<div class="layui-input-block">
						<input name="source" lay-verify="required" placeholder="请输入来源"
							autocomplete="off" class="layui-input" type="text">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">原文网址</label>
					<div class="layui-input-block">
						<input name="articleLink" lay-verify="required" placeholder="请输入原文网址"
							autocomplete="off" class="layui-input" type="text">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">载体</label>
					<div class="layui-input-block">
						<input name="vector" lay-verify="required" placeholder="请输入载体"
							autocomplete="off" class="layui-input" type="text">
					</div>
				</div>


				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">内容</label>
					<div class="layui-input-block">
						<textarea placeholder="请输入内容" class="layui-textarea" name="content" lay-verify="required"></textarea>
					</div>
				</div>

				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>

			</form>
			<#include "/common/script.ftl">
		</div>
	</div>
</body>
<script>
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  
  //创建一个编辑器
  var editIndex = layedit.build('LAY_demo_editor');
  //自定义验证规则
  form.verify({
    title: function(value){
      if(value.length < 5){
        return '标题至少得5个字符啊';
      }
    }
    ,publishTime : function(value){
    	var reg = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
		var regExp = new RegExp(reg);
		if(!regExp.test(value)){
		　　return "日期格式不正确，正确格式为：yyyy-MM-dd";
		}
    }
    ,content: function(value){
      layedit.sync(editIndex);
    }
  });
   //监听提交
  form.on('submit(demo1)', function(data){
    layer.alert(JSON.stringify(data.field), {
      title: '最终的提交信息'
    })
    return false;
  });
  
  
});
</script>
</html>