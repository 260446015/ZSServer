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
</head>
<body class="">
	<div class="layui-layout layui-layout-admin">
		<#include "/common/header.ftl">
		<!-- Start: Content -->

		<#include "/common/sidebar.ftl">
		<!-- 内容 Page -->
		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div class="article-details">
				<a href="javascript:void(0);" class="layui-btn layui-btn-primary" onclick="back();">返回</a>
				<h4 class="text-center">
					标题 :${detail.title}
					<div class="pull-right btns" >
						<button class="layui-btn " onclick="onDel(['${detail.id}']);">删除</button>
						<button class="layui-btn " onclick="collectArt('${detail.id}');" id="col">${detail.isCollect}</button>
					</div>
				</h4>
				<div class="sub-info">
					<span id="bus"> 涉及公司： <a href="javascript:void(0)"></a>
					</span> <span> 发布时间： ${detail.publishTime}</span>
                    <button class="layui-btn layui-btn-xs" onclick="onChange('${detail.id}');">修改发布时间</button>
				</div>
				<div class="article-block">${detail.content}</div>
				<div class="sub-info">
					<span> 情报采集： ${detail.source}</span> <span> 情报原址：  ${detail.articleLink}</span>
				</div>
			</div>
			<#include "/common/script.ftl">
		</div>
	</div>
</body>
<script>
    function onChange(_id){
        layer.prompt({title: '输入发布时间，并确认'}, function(text, index){
            layer.close(index);
            var DATE_FORMAT = /^[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}$/;
            if(DATE_FORMAT.test(text)){
                $.ajax({
                    url: "/art/modifyInfo.json",
                    contentType: 'application/json',
                    data: {id: _id,time:text},
                    success: function (response) {
                        if (response.success){
                            window.location.reload();
                        }else{
                            layer.msg(response.message);
                        }
                    }
                });
            } else {
                layer.alert("抱歉，您输入的日期格式有误，正确格式应为'yyyy-MM-dd'");
            }
        });
    }
			function onDel(id){
		layer.confirm('确定删除该文章？', function(index){
            $.ajax({
                url: "/art/delete",
                contentType: 'application/json',
                data: {ids: id},
                success: function (response) {
                    layer.alert(response.data);
            		window.history.go(-1);
                }
            });
            layer.close(index);
        });
	}
	function collectArt(id){
		var flag = $("#col").html();
		if(flag == '收藏'){
			$.ajax({
				type : 'get',
				url : '/art/collectExpertOpinion.json?id=' + id,
				success : function(response){
					if(response.success){
						$("#col").html('取消收藏');
					}
				}
			});
		}else{
			$.ajax({
				type : 'get',
				url : '/art/cancelCollectExpertOpinion.json?id=' + id,
				success : function(response){
					if(response.success){
						$("#col").html('收藏');
					}
				}
			});
		}
	}
	$(function(){
		var str = '${detail.bus}'.replace("[","").replace("]","");
		var arr = str.split(",");
		var html = "";
		
		for(var i=0;i<arr.length;i++){
			html += "<a target='_blank' href='/apis/oauth/getCompanyDetail.json?name="+arr[i]+"'>"+arr[i]+"</a>&nbsp;";
			//html += "<a target='_blank' href='/apis/oauth/loginOpenEye.json'>"+arr[i]+"</a>&nbsp;";
		}
		$("#bus").append(html);
	});
</script>

</html>
