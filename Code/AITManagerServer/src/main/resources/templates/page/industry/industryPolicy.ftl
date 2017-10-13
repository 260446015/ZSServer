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
					<li class="layui-this"
						onclick="myClick(industry,industryLabel,time,'政策解读')">政策解读</li>
					<li onclick="myClick(industry,industryLabel,time,'高峰论坛')">高峰论坛</li>
					<li onclick="myClick(industry,industryLabel,time,'科学研究')">科学研究</li>
				</ul>
				<div class="layui-tab-content">
					<!-- 选项卡1 -->
					<div class="layui-tab-item layui-show">
						<div class="layui-nav layui-bg-cyan">

							<ul class="" lay-filter="">
								<li class="layui-nav-item"><a href="">产业分类：</a></li>
								<li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="getLabel('互联网+');myClick('互联网+', '不限', time, '政策解读');">互联网+</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel('高科技');myClick('高科技', '不限', time, '政策解读');">高科技</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel('港口物流');myClick('港口物流', '不限', time, '政策解读');">港口物流</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel('文化创意');myClick('文化创意', '不限', time, '政策解读');">文化创意</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel('其他');myClick('其他', '不限', time, '政策解读');">其他</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel('精英配套');myClick('精英配套', '不限', time, '政策解读');">精英配套</a></li>
							</ul>
							 <ul class="" lay-filter="" id="label">
							</ul>
							<ul class="" lay-filter="">
								<li class="layui-nav-item"><a href="">时排序间：</a></li>
								<li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '一年', '政策解读');">全部</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '今日', '政策解读');">今日</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '昨日', '政策解读');">昨日</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '近3天', '政策解读');">近3天</a></li>
							</ul>
						</div>
						<div class="layui-col-md12">
							<div>
								<ul id="biuuu_city_list_1"></ul>
								<div id="demo1"></div>
							</div>
						</div>
					</div>
					
					<!--选项卡2-->
					<div class="layui-tab-item" class="layui-col-md12">
						<div class="layui-nav layui-bg-cyan">

							<ul class="" lay-filter="">
								<li class="layui-nav-item"><a href="">产业分类：</a></li>
								<li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="getLabel2('互联网+');myClick('互联网+', '不限', time, '高峰论坛');">互联网+</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel2('高科技');myClick('高科技', '不限', time, '高峰论坛');">高科技</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel2('港口物流');myClick('港口物流', '不限', time, '高峰论坛');">港口物流</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel2('文化创意');myClick('文化创意', '不限', time, '高峰论坛');">文化创意</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel2('其他');myClick('其他', '不限', time, '高峰论坛');">其他</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel2('精英配套');myClick('精英配套', '不限', time, '高峰论坛');">精英配套</a></li>
							</ul>
							<ul class="" lay-filter="" id="label2">
							</ul>
							<ul class="" lay-filter="">
								<li class="layui-nav-item"><a href="">时排序间：</a></li>
								<li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '一年', '高峰论坛');">全部</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '今日', '高峰论坛');">今日</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '昨日', '高峰论坛');">昨日</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '近3天', '高峰论坛');">近3天</a></li>
							</ul>
						</div>
						<div>
							<ul id="biuuu_city_list_2"></ul>
							<div id="demo2"></div>
						</div>
					</div>
					
					<!--选项卡3-->
					<div class="layui-tab-item" class="layui-col-md12">
						<div class="layui-nav layui-bg-cyan">

							<ul class="" lay-filter="">
								<li class="layui-nav-item"><a href="">产业分类：</a></li>
								<li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="getLabel3('互联网+');myClick('互联网+', '不限', time, '科学研究');">互联网+</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel3('高科技');myClick('高科技', '不限', time, '科学研究');">高科技</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel3('港口物流');myClick('港口物流', '不限', time, '科学研究');">港口物流</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel3('文化创意');myClick('文化创意', '不限', time, '科学研究');">文化创意</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel3('其他');myClick('其他', '不限', time, '科学研究');">其他</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="getLabel3('精英配套');myClick('精英配套', '不限', time, '科学研究');">精英配套</a></li>
							</ul>
							<ul class="" lay-filter="" id="label3">
							</ul>
							<ul class="" lay-filter="">
								<li class="layui-nav-item"><a href="">时排序间：</a></li>
								<li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '一年', '科学研究');">全部</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '今日', '科学研究');">今日</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '昨日', '科学研究');">昨日</a></li>
								<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(industry, industryLabel, '近3天', '科学研究');">近3天</a></li>
							</ul>
						</div>
						<div>
							<ul id="biuuu_city_list_3"></ul>
							<div id="demo3"></div>
						</div>
					</div>

				</div>

			</div>
		</div>
		<#include "/common/script.ftl">
</body>
<script>
	function onDel(id) {
		layer.confirm('确定删除该文章？', function(index) {
			$.ajax({
				url : "/art/delete",
				contentType : 'application/json',
				data : {
					id : id
				},
				success : function(response) {
					layer.alert(response.data);
					myClick(industry, industryLabel, time, dimension,current);
				}
			});
			layer.close(index);
		});
	}
	function toTop() {
		layer.open({
			content : '确认置顶该片数据',
			btn : [ '确认', '取消' ],
			yes : function(index, layero) {
				//按钮【按钮一】的回调
			},
			btn2 : function(index, layero) {
				//按钮【按钮二】的回调

				//return false 开启该代码可禁止点击该按钮关闭
			},
			cancel : function() {
				//右上角关闭回调

				//return false 开启该代码可禁止点击该按钮关闭
			}
		});
	}
	$(function() {
		getLabel(industry);
		getLabel2(industry);
		getLabel3(industry);
		myClick(industry, industryLabel, time, '政策解读');
	});
	function getLabel(industry){
	$.ajax({
		type : 'get',
		url : '/head/getLabel.json?industry='+industry,
		success : function(response){
			var before = '<li class="layui-nav-item"><a href="">产业子类：</a></li><li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="myClick(\''+response.industry+'\',\'不限\',\''+time+'\',\'政策解读\');">不限</a></li>';
			var arr = [];
			for(var i=0;i<response.label.length;i++){
				arr.push('<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(\''+response.industry+'\',\''+response.label[i]+'\',\''+time+'\',\'政策解读\')" >'+response.label[i]+'</a></li>');
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
			var before = '<li class="layui-nav-item"><a href="">产业子类：</a></li><li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="myClick(\''+response.industry+'\',\'不限\',\''+time+'\',\'高峰论坛\');">不限</a></li>';
			var arr = [];
			for(var i=0;i<response.label.length;i++){
				arr.push('<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(\''+response.industry+'\',\''+response.label[i]+'\',\''+time+'\',\'高峰论坛\')" >'+response.label[i]+'</a></li>');
			}
			var s=before+arr.join('');
			$("#label2").html(s);
		}
	});
}
function getLabel3(industry){
	$.ajax({
		type : 'get',
		url : '/head/getLabel.json?industry='+industry,
		success : function(response){
			var before = '<li class="layui-nav-item"><a href="">产业子类：</a></li><li class="layui-nav-item layui-this"><a href="javascript:void(0)" onclick="myClick(\''+response.industry+'\',\'不限\',\''+time+'\',\'科学研究\');">不限</a></li>';
			var arr = [];
			for(var i=0;i<response.label.length;i++){
				arr.push('<li class="layui-nav-item"><a href="javascript:void(0)" onclick="myClick(\''+response.industry+'\',\''+response.label[i]+'\',\''+time+'\',\'科学研究\')" >'+response.label[i]+'</a></li>');
			}
			var s=before+arr.join('');
			$("#label3").html(s);
		}
	});
}
	function myRequest(str, url) {
		$.ajax({
			type : 'post',
			url : url,
			async : false,
			contentType : 'application/json',
			data : JSON.stringify(str),
			success : function(response) {
				layui.use('layer', function() {
					var layer = layui.layer;
					if (response.success) {
						if ('政策解读' == str.dimension) {
							showTable(response.data, 'demo1', str.dimension);
						} else if('高峰论坛' == str.dimension) {
							showTable(response.data, 'demo2', str.dimension);
						} else if('科学研究' == str.dimension) {
							showTable(response.data, 'demo3', str.dimension);
						}
					} else {
						if (response.code != null) {
							window.location.href = "/login";
						} else {
							layer.alert(response.message);
						}
					}
				});
			}
		});
	}
	function show(d, dimension) {
		var showTab;
		if ('政策解读' == dimension) {
			showTab = document.getElementById('biuuu_city_list_1');
		} else if ('高峰论坛' == dimension) {
			showTab = document.getElementById('biuuu_city_list_2');
		} else if ('科学研究' == dimension) {
			showTab = document.getElementById('biuuu_city_list_3');
		}
		showTab.innerHTML = function() {
			var before = '<table class="layui-table" lay-even="" lay-skin="nob">' + '<colgroup><col width="90"><col width="200"><col width="450"><col width="200"><col width="220"><col></colgroup>'
					+ '<thead><tr><th>作者</th><th>标题</th><th>详情</th><th>时间</th><th>来源</th><th>操作</th></tr></thead><tbody>';
			var arr = []
			layui.each(d, function(index, item){
		          arr.push('<tr><td>'+item.author+'</td><td>'+item.title+'</td><td><a href="/head/getDetail.json?id='+item.id+'">'+item.summary+
		          			'</a></td><td>'+item.publishTime+'</td><td>'+item.source+
		          			'</td><td id="appendix"><a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="detail" onclick="onDel(\''+item.id+'\')">删除</a>'+
		          			'<a class="layui-btn layui-btn-mini" lay-event="detail" onclick="myTop(\''+item.id+'\')">置顶</a></td></tr>');
		        });
			var inner = arr.join('');
			var after = '</tbody></table> ';
			return before + inner + after;
		}();
	}
</script>

</html>
