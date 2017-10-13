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
				<a href="javascript:void(0);" class="blue" onclick="back();">返回</a>
				<h4 class="text-center">
					标题 <span>
						<button class=" btn btn-zs" onclick="onDel('${detail.id}');">删除</button>
						<button class=" btn btn-zs">取消收藏</button>
					</span>
				</h4>
				<div class="sub-info">
					<span> 涉及公司： <a>  </a>
					</span> <span> 发布时间： ${detail.publishTime}</span>
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
			function onDel(id){
		layer.confirm('确定删除该文章？', function(index){
            $.ajax({
                url: "/art/delete",
                contentType: 'application/json',
                data: {id: id},
                success: function (response) {
                    layer.alert(response.data);
                }
            });
            layer.close(index);
            window.history.back();
        });
	}
	
	function back(){
	window.history.back();
}
	
</script>

</html>
