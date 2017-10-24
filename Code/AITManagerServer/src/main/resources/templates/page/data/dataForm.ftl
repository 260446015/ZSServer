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
        <div style="padding: 15px;">
        	<span style="float:left;margin-left:10px;"><a href="javascript:void(0);" class="layui-btn layui-btn-primary" onclick="back();">返回上一页</a></span>
	        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
			</fieldset>
        	<form class="layui-form" action="">
			  <div class="layui-form-item">
			    <label class="layui-form-label">文章标题</label>
			    <div class="layui-input-block">
			      <input name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" type="text">
			    </div>
			  </div>
			  <div class="layui-form-item">
			      <label class="layui-form-label">发布日期</label>
			      <div class="layui-input-inline">
			        <input class="layui-input" name="publishDate" id="publishDate" placeholder="yyyy-MM-dd HH:mm:ss" type="text">
			      </div>
			    </div>
			  <div class="layui-form-item">
			    <div class="layui-inline">
			      <label class="layui-form-label">文章来源</label>
			      <div class="layui-input-inline">
			        <input name="source" lay-verify="title" autocomplete="off" placeholder="请输入来源" class="layui-input" type="tel">
			      </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label">来源url</label>
			      <div class="layui-input-inline">
			        <input name="sourceLink" lay-verify="url" autocomplete="off" placeholder="https://www.baidu.com/" class="layui-input" type="tel">
			      </div>
			    </div>
			  </div>
			  <div class="layui-form-item">
			    <div class="layui-inline">
			      <label class="layui-form-label">原文载体</label>
			      <div class="layui-input-inline">
			        <input name="phone" lay-verify="title" autocomplete="off" placeholder="请输入载体" class="layui-input" type="tel">
			      </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label">原文作者</label>
			      <div class="layui-input-inline">
			        <input name="author" autocomplete="off" placeholder="请输入作者" class="layui-input" type="tel">
			      </div>
			    </div>
			  </div>
			  <div class="layui-form-item">
			    <div class="layui-inline">
			      <label class="layui-form-label">产业</label>
			      <div class="layui-input-inline">
				      <select name="industry" lay-filter="province">
				        <option value="">请选择产业</option>
				        <option value="">请产业</option>
				      </select>
				    </div>
				    <div class="layui-input-inline">
				      <select name="industryLabel">
				        <option value="">请选择产业标签</option>
				      </select>
				    </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label">所属模块</label>
			      <div class="layui-input-inline">
			        <select name="dimension" lay-verify="required" lay-search="">
			          <option value="">直接选择或搜索选择</option>
			          <option value="产业头条">产业头条</option>
			          <option value="科学研究">科学研究</option>
			          <option value="政策解读">政策解读</option>
			          <option value="高峰论坛">高峰论坛</option>
			          <option value="百家论">百家论</option>
			          <option value="企业排行">企业排行</option>
			          <option value="龙头企业">龙头企业</option>
			          <option value="园区政策">园区政策</option>
			          <option value="园区动态">园区动态</option>
			          <option value="企业动态">企业动态</option>
			          <option value="疑似外流">疑似外流</option>
			        </select>
			      </div>
			    </div>
			  </div>
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">涉及公司</label>
			    <div class="layui-input-block">
			      <textarea placeholder="请输涉及公司" class="layui-textarea" name="business"></textarea>
			      <div class="layui-form-mid layui-word-aux">两个公司之间请用、号分开</div>
			    </div>
			     
			  </div>
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">文章内容</label>
			    <div class="layui-input-block">
			      <textarea class="layui-textarea layui-hide" name="content" lay-verify="content" id="LAY_demo_editor"></textarea>
			    </div>
			  </div>
			  <div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
			      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			    </div>
			  </div>
			</form>
        
        </div>
    </div>
	<#include "/common/script.ftl">
    <script>
    layui.use(['form', 'layedit', 'laydate'], function(){
	  var form = layui.form
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;
	  
	  laydate.render({
	    elem: '#publishDate'
	    ,type: 'datetime'
	  });
	  
	  //创建一个编辑器
	  var editIndex = layedit.build('LAY_demo_editor');
	 
	  //自定义验证规则
	  form.verify({
	    title: function(value){
	      if(value.length < 2){
	        return '文本不得少于2个字符！';
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
	  
	  form.on('select(province)', function(data){
        $.getJSON("/api/getCity?pid="+data.value, function(data){
            var optionstring = "";
            $.each(data.data, function(i,item){
                optionstring += "<option value=\"" + item.code + "\" >" + item.name + "</option>";
            });
            $("#city").html('<option value=""></option>' + optionstring);
            form.render('select'); //这个很重要
        });
	});
	});
    </script>
</body>
