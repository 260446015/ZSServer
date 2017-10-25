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

            <div class="layui-row">
                <div id="chart2" style="width: 500px;height: 120px;">
                </div>	
            </div>
            <div class="layui-row">
			    <div class="layui-col-md3 layui-col-md-offset3">
			      <div class="grid-demo grid-demo-bg1"><a class="layui-btn" href="/apis/data/dataForm">手动添加数据</a></div>
			    </div>
			  </div>
            <div class="layui-row">
                <div class="layui-col-md6 ">
                    <div id="chart2" style="width: 500px;height: 220px; border: 1px solid #e6e6e6 ;">
                    	<button type="button" class="layui-btn" id="test3"><i class="layui-icon"></i>上传文件</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<#include "/common/script.ftl">
    <script>
    layui.use('upload', function(){
  var $ = layui.jquery
  ,upload = layui.upload;
  
  
  //多图片上传
  upload.render({
    elem: '#test2'
    ,url: '/upload/'
    ,multiple: true
    ,before: function(obj){
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result){
        $('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
      });
    }
    ,done: function(res){
      //上传完毕
    }
  });
  
  upload.render({ //允许上传的文件后缀
    elem: '#test4'
    ,url: '/upload/'
    ,accept: 'file' //普通文件
    ,exts: 'zip|rar|7z' //只允许上传压缩文件
    ,done: function(res){
      console.log(res)
    }
  });
  upload.render({
    elem: '#test5'
    ,url: '/upload/'
    ,accept: 'video' //视频
    ,done: function(res){
      console.log(res)
    }
  });
  upload.render({
    elem: '#test6'
    ,url: '/upload/'
    ,accept: 'audio' //音频
    ,done: function(res){
      console.log(res)
    }
  });
  
});
    </script>
</body>
