$(function(){
	showTeamMember();
	$(".more").on("click",function(){
		count ++;
		var html = '';
		var len = count * 10;
		if(len <= arr.length){
			for(var i=0;i<len;i++){
				html += '<div class="col-md-12 border-bottom"><div class="layout-box"><div class="left-img">' +
				'<img src="'+arr[i].icon+'" width="160"></div><div class="right-list">' +
				'<a class="scatter-blocks no-border" href="javascript:void(0);"><span class="scatter-title">'+arr[i].title+'</span>' +
				'<span class="scatter-type ml10">'+arr[i].name+'</span></a>' +
				'<p class="person-introduce">'+arr[i].desc+'</p></div></div></div>';
			}
			$("#teamMember").html(html);
		}else{
			$(this).html("已无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
			$(".top").click(function(){
				var sc=$(window).scrollTop();
				$('body,html').animate({scrollTop:0},500);
			});
		}
	})
});
function showTeamMember(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		url:'/apis/openeyes/getTeamMember.json',
		data:JSON.stringify(req),
		contentType:'application/json',
		type:'post',
		success:function(res){
			if(res.success){
				var html = '';
				arr = res.data.result.page.rows;
				if(arr.length > 10){
					for(var i=0;i<10;i++){
						html += '<div class="col-md-12 border-bottom"><div class="layout-box"><div class="left-img">' +
								'<img src="'+arr[i].icon+'" width="160"></div><div class="right-list">' +
								'<a class="scatter-blocks no-border" href="javascript:void(0);"><span class="scatter-title">'+arr[i].title+'</span>' +
								'<span class="scatter-type ml10">'+arr[i].name+'</span></a>' +
								'<p class="person-introduce">'+arr[i].desc+'</p></div></div></div>';
					}
				}else{
					for(var i=0;i<arr.length;i++){
						html += '<div class="col-md-12 border-bottom"><div class="layout-box"><div class="left-img">' +
								'<img src="'+arr[i].icon+'" width="160"></div><div class="right-list">' +
								'<a class="scatter-blocks no-border" href="javascript:void(0);"><span class="scatter-title">'+arr[i].title+'</span>' +
								'<span class="scatter-type ml10">'+arr[i].name+'</span></a>' +
								'<p class="person-introduce">'+arr[i].desc+'</p></div></div></div>';
					}
					$(".more").html("暂无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
					$(".top").click(function(){
						var sc=$(window).scrollTop();
						$('body,html').animate({scrollTop:0},500);
					});
				}
				
				$("#teamMember").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#teamMember").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
