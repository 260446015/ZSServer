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
<#include "/common/sidebar.ftl">
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="layui-col-md12" style="margin:20px;">
            <span style="float:left;margin-left:10px;"><a class="layui-btn layui-btn-primary" href="/apis/area/parkSupervision">返回上一页</a></span>
        
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
		</fieldset>
        <!-- 选项卡 -->
        <div class="layui-tab layui-tab-brief">
            <ul class="layui-tab-title">
                <li class="layui-this" onclick="myClick(0)">企业动态</li>
                <li onclick="myClick(1)">疑似外流</li>
            </ul>
        </div>
    <ul id="biuuu_city_list"></ul> 
    <div id="demo"></div>
    </div>
    </div>
    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
</body>
<#include "/common/script.ftl">
<script  type="text/html" id="appendix">
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>
<script  type="text/html" id="appendix">
    <a class="layui-btn layui-btn-warm layui-btn-mini" lay-event="top">置顶</a>
</script>
<script>
	$(function(){
        myClick("企业动态");
    })
    function myClick(type){
    	var obj={dimension:type,park:'${Request.park}'};
		$.ajax({
                type: 'post',
                url: "/apis/area/findDynamicList.json",
                async: false,
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function (response) {
                	alert(1);
                }
            });
	}
  	function showTable(data){
  		layui.use(['laypage', 'layer'], function(){
			var laypage = layui.laypage
			,layer = layui.layer;
			//调用分页
			laypage.render({
				elem: 'demo'
			    ,count: data.length
			    ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
			    ,jump: function(obj){
					//模拟渲染
					document.getElementById('biuuu_city_list').innerHTML = function(){
				        var arr = []
				        ,thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
				        
				        // thisdata是数据集合
				        layui.each(thisData, function(index, item){
				        	//item是循环对象
				          arr.push('<li>'+ item +'</li>');
				        });
				        return arr.join('');
					}();
				}
			});
		});
  	}
</script>
</html>