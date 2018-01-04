$(function(){
	showBids();
	$(".more").on("click",function(){
		count ++;
		var html = '';
		var len = count * 10;
		if(len <= arr.length){
			for(var i=0;i<len;i++){
				var dateStr = getFormatDate(new Date(arr[i].publishTime));
				html += '<tr><td>'+dateStr+'</td><td>'+arr[i].title+'</td>' +
						'<td>'+arr[i].purchaser+'</td></tr>';
			}
			$("#bids").html(html);
		}else{
			$(this).html("已无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
			$(".top").click(function(){
				var sc=$(window).scrollTop();
				$('body,html').animate({scrollTop:0},500);
			});
		}
	})
});
function showBids(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getBids.json',
		success:function(res){
			if(res.success){
				arr = res.data.data.items;
				var thead = '<tr><th class="text-left">发布时间</th><th class="text-left">标题</th><th class="text-left">采购人</th></tr>';
				$("#bids").prev().html(thead);
				var html = '';
				if(arr.length > 10){
					for(var i=0;i<10;i++){
						var dateStr = getFormatDate(new Date(arr[i].publishTime));
						html += '<tr><td>'+dateStr+'</td><td>'+arr[i].title+'</td>' +
								'<td>'+arr[i].purchaser+'</td></tr>';
					}
				}else{
					for(var i=0;i<arr.length;i++){
						var dateStr = getFormatDate(new Date(arr[i].publishTime));
						html += '<tr><td>'+dateStr+'</td><td>'+arr[i].title+'</td>' +
								'<td>'+arr[i].purchaser+'</td></tr>';
					}
					$(".more").html("暂无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
					$(".top").click(function(){
						var sc=$(window).scrollTop();
						$('body,html').animate({scrollTop:0},500);
					});
				}
				
				$("#bids").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#bids").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}