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
        <div style="padding: 15px;width:900px;margin:0 auto;margin-top:120px;">

            <div class="layui-row" style="margin-bottom:20px;">
			    <div class="layui-col-md3 ">
			      <div class="grid-demo grid-demo-bg1"><a class="layui-btn" href="/apis/data/dataForm">手动添加数据</a></div>
			    </div>
			  </div>
            <div class="layui-row">
                <div class="layui-col-md9 ">
                    <div class="layui-upload">
					  <button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
					  <button type="button" class="layui-btn" id="test9"><i class="layui-icon"></i>读取数据上传</button>
					</div>
					<div class="layui-form-mid layui-word-aux">温馨提示：文件仅支持xlsx格式，表格格式请严格按照范例，无值置空，勿删表头</div>
                </div>
            </div>
        </div>
    </div>
	<#include "/common/script.ftl">
    <script>
    layui.use('upload', function(){
	  var $ = layui.jquery
	  ,upload = layui.upload;
	  
	  //选完文件后不自动上传
	  upload.render({
	    elem: '#test8'
	    ,url: '/apis/dataUpload.do'
	    ,accept: 'file' 
   		,exts: 'xlsx' 
	    ,auto: false
	    //,multiple: true
	    ,bindAction: '#test9'
	    ,done: function(res){
		    if(res.success){
		    	layer.alert('上传成功，请等待后台校验格式存储数据');
	    	}else{
	    		layer.alert(res.message);
	    	}
	    }
	  });
	  
	});
    </script>
</body>
