<!DOCTYPE html>
<html lang="en">
<head>
<!-- Basic -->
<meta charset="UTF-8" />
<title>慧数招商后台系统</title>
<!-- Mobile Metas -->
<!--专家观点-->
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
		<div class="layui-tab">
				<ul class="layui-tab-title">
					<li class="layui-this" onclick="myClick(industry,industryLabel,time,'专家观点')">专家说</li>
					<li onclick="myClick(industry,industryLabel,time,'百家论')">百家论</li>
				</ul>
				<div class="layui-tab-content">
				<!-- 选项卡1 -->
					<div class="layui-tab-item layui-show">
					    <div >
				            <div class="layui-nav layui-bg-cyan">
				           <ul class="" lay-filter="">
								<li class="nav-label-title">产业分类：</li>
								<li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="getLabel('互联网+');myClick('互联网+', '不限', time, '专家观点');">互联网+</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel('高科技');myClick('高科技', '不限', time, '专家观点');">高科技</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel('港口物流');myClick('港口物流', '不限', time, '专家观点');">港口物流</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel('文化创意');myClick('文化创意', '不限', time, '专家观点');">文化创意</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel('其他');myClick('其他', '不限', time, '专家观点');">其他</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel('精英配套');myClick('精英配套', '不限', time, '专家观点');">精英配套</a></li>
							</ul>
							</div>
							<div class="layui-nav layui-bg-cyan" id="label-list">
				            <ul class="" lay-filter="" id="label">
							</ul>
							</div>
							<div class="layui-nav layui-bg-cyan">
							<ul class="" lay-filter="">
								<li class="nav-label-title">时排序间：</li>
								<li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '全部', '专家观点');">全部</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '今日', '专家观点');">今日</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '昨日', '专家观点');">昨日</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '近3天', '专家观点');">近3天</a></li>
							</ul>
							</div>
			            </div>
			            <div class="layui-col-md12">
			                <div class="add-article marginY20" style="float:right;">
			                	<a href="/head/createArt" class="layui-btn layui-btn-normal">新建文章</a>
			                </div>
			            	<div>
			            	 <ul id="biuuu_city_list_1"></ul> 
    						<div id="demo1"></div>
			            </div>
			            </div>
				    </div>
                     <!--选项卡2-->
                    <div class="layui-tab-item" class="layui-col-md12">
                         <div >
				           <div class="layui-nav layui-bg-cyan">
				            <ul class="" lay-filter="">
								<li class="nav-label-title">产业分类：</li>
								<li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="getLabel2('互联网+');myClick('互联网+', '不限', time, 百家论');">互联网+</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel2('高科技');myClick('高科技', '不限', time, '百家论');">高科技</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel2('港口物流');myClick('港口物流', '不限', time, '百家论');">港口物流</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel2('文化创意');myClick('文化创意', '不限', time, '百家论');">文化创意</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel2('其他');myClick('其他', '不限', time, '百家论');">其他</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel2('精英配套');myClick('精英配套', '不限', time, '百家论');">精英配套</a></li>
							</ul>
							</div>
							<div class="layui-nav layui-bg-cyan" id="label-list">
				           <ul class="" lay-filter="" id="label2">
							</ul>
							</div>
							<div class="layui-nav layui-bg-cyan">
				           <ul class="" lay-filter="">
								<li class="nav-label-title">时排序间：</li>
								<li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '全部', '百家论');">全部</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '今日', '百家论');">今日</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '昨日', '百家论');">昨日</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '近3天', '百家论');">近3天</a></li>
							</ul>
							</div>
			            </div>
			            <div>
			            	 <ul id="biuuu_city_list_2"></ul> 
    						<div id="demo2"></div>
			            </div>
                    </div>
                    
			</div>		
			
	</div>
	</div>
	<#include "/common/script.ftl">
	
</body>
<script>
  $(function(){
  		getLabel(industry,'专家观点');
  		getLabel2(industry,'百家论');
		myClick(industry,industryLabel,time,'专家观点');
	});
	function getLabel(industry){
	$.ajax({
		type : 'get',
		url : '/head/getLabel.json?industry='+industry,
		success : function(response){
			var before = '<li class="nav-label-title">产业子类：</li><li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="myClick(\''+response.industry+'\',\'不限\',\''+time+'\',\'专家观点\');">不限</a></li>';
			var arr = [];
			for(var i=0;i<response.label.length;i++){
				arr.push('<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(\''+response.industry+'\',\''+response.label[i]+'\',\''+time+'\',\'专家观点\')" >'+response.label[i]+'</a></li>');
			}
			var s=before+arr.join('');
			$("#label").html(s);
		}
	});
}
function getLabel2(industry){
	$.ajax({
		type : 'get',
		url : '/head/getLabel.json?industry='+industry,
		success : function(response){
			var before = '<li class="layui-nav-item"><a href="">产业子类：</a></li><li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="myClick(\''+response.industry+'\',\'不限\',\''+time+'\',\'百家论\');">不限</a></li>';
			var arr = [];
			for(var i=0;i<response.label.length;i++){
				arr.push('<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(\''+response.industry+'\',\''+response.label[i]+'\',\''+time+'\',\'百家论\')" >'+response.label[i]+'</a></li>');
			}
			var s=before+arr.join('');
			$("#label2").html(s);
		}
	});
}
	function myRequest(str,url){
		$.ajax({
            type: 'post',
            url: url,
            async: false,
            contentType: 'application/json',
            data: JSON.stringify(str),
            success: function (response) {
                layui.use('layer', function(){
								  var layer = layui.layer;
								  if(response.success){
								  		if('专家观点' == str.dimension){
								 			showTable(response.data,'demo1',str.dimension);
								  		}else{
								  			showTable(response.data,'demo2',str.dimension);
								  		}
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
	function show(d,dimension){
			var showTab;
			if('专家观点' == dimension){
				showTab = document.getElementById('biuuu_city_list_1');
			}else{
				showTab = document.getElementById('biuuu_city_list_2');
			}
			showTab.innerHTML = function(){
				var before = '<table class="layui-table" lay-even="" lay-skin="nob">' + '<colgroup><col width="7%"><col width="7%"><col width="15%"><col width="30%"><col width="10%"><col width="15%"></colgroup>'
					+ '<thead><tr><th><input type="checkbox" name="" style="vertical-align:top;" id="allcheck" onclick="checkall();"><a class="layui-btn layui-btn-danger layui-btn-mini" href="javascript:void(0);" onclick="deleteCheck();">删除</a></th><th>作者</th><th>标题</th><th>详情</th><th>时间</th><th>来源</th><th>操作</th></tr></thead><tbody>';
			var arr = []
			layui.each(d, function(index, item){
		          arr.push('<tr><td><input type="checkbox" value="'+item.id+'" name="checkname"/></td><td>'+item.author+'</td><td>'+item.title+'</td><td>'+item.summary+
		          			'</td><td>'+item.publishTime+'</td><td>'+item.source+
		          			'</td><td id="appendix"><a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="detail" onclick="onDel([\''+item.id+'\'])">删除</a>'+
		          			'<a class="layui-btn layui-btn-mini" lay-event="detail" onclick="myTop(\''+item.id+'\')">'+(item.istop==true?'已置顶':'置顶')+'</a>'+
		          			'<a class="layui-btn layui-btn-mini" lay-event="detail" href="/art/findInfo?id='+item.id+'">查看详情</a></td></tr>');
		        });
		        var inner=arr.join('');
		        var after='</tbody></table> ';
		        return before+inner+after;
			}();
		}
</script>
	
</html>	