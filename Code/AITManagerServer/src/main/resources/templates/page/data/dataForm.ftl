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
<style>
	.layui-form-label{
		width : 200px;
	}
	.layui-input-block {
	  margin-left:200px;
	}
	form{   
	 width: 900px;
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
			        <input name="source"  autocomplete="off" placeholder="请输入来源" class="layui-input" type="tel">
			      </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label">来源url</label>
			      <div class="layui-input-inline">
			        <input name="sourceLink"  autocomplete="off" placeholder="https://www.baidu.com/" class="layui-input" type="tel">
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
				        <option value="互联网+">互联网+</option>
				        <option value="高科技">高科技</option>
				        <option value="文化创意">文化创意</option>
				        <option value="精英配套">精英配套</option>
				        <option value="其他">其他</option>
				        <option value="港口物流">港口物流</option>
				      </select>
				    </div>
				    <div class="layui-input-inline">
				      <select name="industryLabel" id="industryLabel">
				        <option value=""></option>
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
			  <div class="layui-form-item">
			    <label class="layui-form-label">相关园区</label>
			    <div class="layui-input-block">
			      <input name="park" autocomplete="off" placeholder="请输入园区" class="layui-input" type="text">
			    </div>
			  </div>
			  <div class="layui-form-item">
			    <div class="layui-inline">
			      <label class="layui-form-label">文章情感</label>
			      <div class="layui-input-inline">
			        <select name="emotion" lay-verify="required" lay-search="">
			          <option value="">直接选择或搜索选择</option>
			          <option value="positive">正面</option>
			          <option value="negative">负面</option>
			          <option value="neutral">中性</option>
			        </select>
			      </div>
			    </div>
			    <div class="layui-inline">
			      <label class="layui-form-label">园区地域</label>
			      <div class="layui-input-inline">
			        <input name="area" autocomplete="off" placeholder="请输入地域" class="layui-input" type="text">
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
	    	if(value.length > 32766){
	        return '您输入了'+value.length+'字符，文本不得多于32766个字符！';
	      }
	      layedit.sync(editIndex);
	    }
	  });
	  //监听提交
	  form.on('submit(demo1)', function(data){
	  alert(JSON.stringify(data.field))
	      var result;
          $.ajax({
              type: 'post',
              url: "/apis/data/addData.json",
              async: false,
              contentType: 'application/json',
              data: JSON.stringify(data.field),
              success: function (response) {
                  result=response.success;
              }
          });
           return result;
	  });
	  
	  form.on('select(province)', function(data){
	  	var optionstring = "";
	  	if(data.value=='互联网+'){
	  		optionstring += "<option value='网络游戏' >网络游戏</option>";
	  		optionstring += "<option value='大数据' >大数据</option>";
	  		optionstring += "<option value='电子商务' >电子商务</option>";
	  		optionstring += "<option value='网络视听' >网络视听</option>";
	  		optionstring += "<option value='移动阅读' >移动阅读</option>";
	  		optionstring += "<option value='智能硬件' >智能硬件</option>";
	  	}
	  	if(data.value=='高科技'){
	  		optionstring += "<option value='新一代信息技术' >新一代信息技术</option>";
	  		optionstring += "<option value='智能机器人' >智能机器人</option>";
	  		optionstring += "<option value='生物医药' >生物医药</option>";
	  		optionstring += "<option value='节能环保技术装备' >节能环保技术装备</option>";
	  		optionstring += "<option value='新能源' >新能源</option>";
	  		optionstring += "<option value='新材料' >新材料</option>";
	  		optionstring += "<option value='航空装备' >航空装备</option>";
	  	}
	  	if(data.value=='文化创意'){
	  		optionstring += "<option value='动漫制作' >动漫制作</option>";
	  		optionstring += "<option value='影视传媒' >影视传媒</option>";
	  		optionstring += "<option value='图书出版' >图书出版</option>";
	  		optionstring += "<option value='广告营销' >广告营销</option>";
	  	}
	  	if(data.value=='精英配套'){
	  		optionstring += "<option value='金融服务' >金融服务</option>";
	  		optionstring += "<option value='住宅地产' >住宅地产</option>";
	  		optionstring += "<option value='商业综合体' >商业综合体</option>";
	  		optionstring += "<option value='康体美容' >康体美容</option>";
	  		optionstring += "<option value='母婴产业' >母婴产业</option>";
	  		optionstring += "<option value='健康产业' >健康产业</option>";
	  		optionstring += "<option value='教育培训' >教育培训</option>";
	  	}
	  	if(data.value=='其他'){
	  		optionstring += "<option value='特色旅游综合体' >特色旅游综合体</option>";
	  		optionstring += "<option value='体育产业' >体育产业</option>";
	  	}
	  	if(data.value=='港口物流'){
	  		optionstring += "<option value='生鲜贸易' >生鲜贸易</option>";
	  		optionstring += "<option value='食品加工' >食品加工</option>";
	  		optionstring += "<option value='冷链物流' >冷链物流</option>";
	  	}
        $("#industryLabel").html('<option value=""></option>' + optionstring);
        form.render('select'); //这个很重要
	});
	});
    </script>
</body>
