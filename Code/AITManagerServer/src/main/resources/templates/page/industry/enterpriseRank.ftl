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
				<div class="layui-tab-content">
				<!-- 选项卡1 -->
					<div class="layui-tab-item layui-show">
					    <div >
				            <div class="layui-nav layui-bg-cyan">
				            <ul class="" lay-filter="">
								<li class="nav-label-title">产业分类：</li>
								<li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="getLabel('互联网+');myClick('互联网+', industryLabel, time, '企业排行');">互联网+</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel('高科技');myClick('高科技', industryLabel, time, '企业排行');">高科技</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel('港口物流');myClick('港口物流', industryLabel, time, '企业排行');">港口物流</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel('文化创意');myClick('文化创意', industryLabel, time, '企业排行');">文化创意</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel('其他');myClick('其他', industryLabel, time, '企业排行');">其他</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel('精英配套');myClick('精英配套', industryLabel, time, '企业排行');">精英配套</a></li>
							</ul>
							</div>
							<div class="layui-nav layui-bg-cyan" id="label-list">
				            <ul class="" lay-filter="" id="label">
							</ul>
							</div>
							<div class="layui-nav layui-bg-cyan">
				            <ul class="" lay-filter="">
								<li class="nav-label-title">时排序间：</li>
								<li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '一年', '企业排行');">全部</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '今日', '企业排行');">今日</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '昨日', '企业排行');">昨日</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '近3天', '企业排行');">近3天</a></li>
							</ul>
							</div>
			            </div>
			            <div class="layui-col-md12">
			            	<div>
			            	 <ul id="biuuu_city_list_1"></ul> 
    						<div id="demo1"></div>
			            </div>
			            </div>
				    </div>
			</div>		
			
	</div>
	</div>
	<#include "/common/script.ftl">
	
</body>
<script>
		function onDel(id){
		layer.confirm('确定删除该文章？', function(index){
            $.ajax({
                url: "/art/delete",
                contentType: 'application/json',
                data: {id: id},
                success: function (response) {
                    layer.alert(response.data);
           		    myClick(industry, industryLabel, time, dimension,current);
                }
            });
            layer.close(index);
        });
	}
	function toTop(){
		layer.open({
           content: '确认置顶该片数据'
           ,btn: ['确认', '取消']
           ,yes: function(index, layero){
           //按钮【按钮一】的回调
          }
          ,btn2: function(index, layero){
           //按钮【按钮二】的回调
    
           //return false 开启该代码可禁止点击该按钮关闭
          }
          ,cancel: function(){ 
          //右上角关闭回调
    
          //return false 开启该代码可禁止点击该按钮关闭
           }
        });
	}
  $(function(){
  		getLabel(industry);
		myClick(industry,industryLabel,time,'企业排行');
	});
	function getLabel(industry){
	$.ajax({
		type : 'get',
		url : '/head/getLabel.json?industry='+industry,
		success : function(response){
			var before = '<li class="nav-label-title">产业子类：</li><li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="myClick(\''+response.industry+'\',\'不限\',\''+time+'\',\'企业排行\');">不限</a></li>';
			var arr = [];
			for(var i=0;i<response.label.length;i++){
				arr.push('<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(\''+response.industry+'\',\''+response.label[i]+'\',\''+time+'\',\'企业排行\')" >'+response.label[i]+'</a></li>');
			}
			var s=before+arr.join('');
			$("#label").html(s);
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
							 			showTable(response.data,'demo1',str.dimension);
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
			var showTab = document.getElementById('biuuu_city_list_1');
			showTab.innerHTML = function(){
				var before='<table class="layui-table" lay-even="" lay-skin="nob">'+
					 	'<colgroup><col width="90"><col width="200"><col width="450"><col width="150"><col width="220"><col></colgroup>'+
					 	'<thead><tr><th>作者</th><th>标题</th><th>详情</th><th>时间</th><th>来源</th><th>操作</th></tr></thead><tbody>';
		        var arr = []
		        layui.each(d, function(index, item){
		          arr.push('<tr><td>'+item.author+'</td><td>'+item.title+'</td><td>'+item.summary+
		          			'</td><td>'+item.publishTime+'</td><td>'+item.source+
		          			'</td><td id="appendix"><a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="detail" onclick="onDel(\''+item.id+'\')">删除</a>'+
		          			'<a class="layui-btn layui-btn-mini" lay-event="detail" onclick="myTop(\''+item.id+'\')">置顶</a>'+
		          			'<a class="layui-btn layui-btn-mini" lay-event="detail" href="/head/getDetail.json?id='+item.id+'">查看详情</a></td></tr>');
		        });
		        var inner=arr.join('');
		        var after='</tbody></table> ';
		        return before+inner+after;
			}();
		}
</script>
	
</html>	