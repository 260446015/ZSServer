<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-情报推送</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="慧数招商平台，是一个关于园区产业招商的大数据管理平台">
    <meta name="keywords" content="慧数，招商，慧数招商，招商平台，园区，园区招商，园区招商平台，科技园，产业园，大数据，产业">
    <meta name="author" content="张鑫，慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
    <!-- css共用部分 start -->
    <#include "/common/link.ftl"/>
    <!-- css 共用部分 end -->
    <!-- js 兼容低版本IE start -->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- js 兼容低版本IE end -->
</head>
<body class="bg2">
<#include "/common/header.ftl"/>
<div class="wrapper">
    <div class="page-content">
		<#include "/common/sidebar2.ftl"/>
        <div class="right-content">
            <div class="container">
                <div class="model-box">
                    <div class="model-header">
                        <h3 class="model-title">${Request.park}</h3>
                    </div>
                    <div class="model-body">
                        <h3 class="text-center sub-title">园区政策</h3>
                        <div class="border-shadow-box small-list">
                            <div class="row" id="policy_list">
                            </div>
                        </div>
                        <h3 class="text-center sub-title mt50">园区动态</h3>
                        <div class="border-shadow-box small-list">
                            <div class="row" id="dynamic_list">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include  "/common/footer.ftl"/>
<!-- js 共用部分 start -->
<#include  "/common/script.ftl"/>
<!-- js 共用部分 end -->
</body>
<script type="text/javascript">
	$(function(){
		$("#gardenMap").addClass("active");
		$("#follow").addClass("active");
        myPost('${Request.park}','园区政策',0);
		myPost('${Request.park}','园区动态',0);
    })
	function myPost(a,b,p){
		$.ajax({
			type:"post",
            url: "/apis/area/getInformationPush.json",
			contentType:'application/json',
			data:JSON.stringify({park:a,dimension:b,pageNumber:p,pageSize:4}),
            success: function (response) {
                if(response.success){
                	if(b=='园区政策'){
						$('#policy_list').html(show(response.data.dataList,b));
					}else{
						$('#dynamic_list').html(show(response.data.dataList,b));
					}
                }else{
					alert(response.message);
                }
            }
        });
	}
	function show(d,b){
		if(d.length==0){
			return '<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">未查询到相关讯息！</p></div>';
		}
        var arr = []
        $.each(d, function(index, item){
          arr.push('<div class="col-md-12 border-bottom"><a class="scatter-blocks no-border" target="_blank" href="/summit/getEssayDetails.json?essayId='+item.id+'">'+
					'<span class="scatter-title">'+item.title+'</span></a><p class="scatter-content">'+item.summary+'</p>'+
					'<p class="scatter-lib"><span class="glyphicon glyphicon-globe"></span><span>'+item.vector+'</span></p></div>'
          		);
        });
        var inner=arr.join('');
        var url="";
        if(b=='园区政策'){
			url='/apis/analysis/findMorePolicy.html?park=${Request.park}';
		}else{
			url='/apis/analysis/findMoreCondition.html?park=${Request.park}';
		}
        return inner+'<div class="modal-footer text-center"><a href="'+url+'" class="btn btn-link">查看更多</a></div>';
  	}
</script> 
</html>