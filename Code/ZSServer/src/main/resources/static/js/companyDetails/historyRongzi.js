$(function(){
	showRongZi();
	$(".more").on("click",function(){
		count ++;
		var html = '';
		var len = count * 10;
		if(len <= arr.length){
			for(var i=0;i<len;i++){
				var dateStr = getFormatDate(new Date(arr[i].date));
				var tzArr = arr[i].investorName.split("，");
				var tzHtml = '';
				for(var j=0;j<tzArr.length;j++){
					tzHtml += '<p>'+tzArr[j]+'</p>';
				}
				html += '<tr><td>'+dateStr+'</td><td>'+arr[i].round+'</td><td>'+arr[i].value+'</td>' +
						'<td>'+arr[i].money+'</td><td>'+arr[i].share+'</td><td>' + tzHtml +
						'</td><td>'+arr[i].newsTitle+'</td></tr>';
			}
			$("#rongzi").html(html);
		}else{
			$(this).html("已无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
			$(".top").click(function(){
				var sc=$(window).scrollTop();
				$('body,html').animate({scrollTop:0},500);
			});
		}
	})
});
function showRongZi(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getHistoryRongZi.json',
		success:function(res){
			if(res.success){
				arr = res.data.result.page.rows;
				var thead = '<tr><th class="text-left">时间</th><th class="text-left">轮次</th><th class="text-left">估值</th><th class="text-left">金额</th>'+
                            '<th class="text-left">比例</th><th class="text-left">投资方</th><th class="text-left">新闻来源</th></tr>';
				$("#rongzi").prev().html(thead);
				var html = '';
				if(arr.length > 10){
					for(var i=0;i<10;i++){
						var dateStr = getFormatDate(new Date(arr[i].date));
						var tzArr = arr[i].investorName.split("，");
						var tzHtml = '';
						for(var j=0;j<tzArr.length;j++){
							tzHtml += '<p>'+tzArr[j]+'</p>';
						}
						html += '<tr><td>'+dateStr+'</td><td>'+arr[i].round+'</td><td>'+arr[i].value+'</td>' +
								'<td>'+arr[i].money+'</td><td>'+arr[i].share+'</td><td>' + tzHtml +
								'</td><td>'+arr[i].newsTitle+'</td></tr>';
					}
				}else{
					for(var i=0;i<arr.length;i++){
						var dateStr = getFormatDate(new Date(arr[i].date));
						var tzArr = arr[i].investorName.split("，");
						var tzHtml = '';
						for(var j=0;j<tzArr.length;j++){
							tzHtml += '<p>'+tzArr[j]+'</p>';
						}
						html += '<tr><td>'+dateStr+'</td><td>'+arr[i].round+'</td><td>'+arr[i].value+'</td>' +
								'<td>'+arr[i].money+'</td><td>'+arr[i].share+'</td><td>' + tzHtml +
								'</td><td>'+arr[i].newsTitle+'</td></tr>';
					}
					$(".more").html("暂无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
					$(".top").click(function(){
						var sc=$(window).scrollTop();
						$('body,html').animate({scrollTop:0},500);
					});
				}
				
				$("#rongzi").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#rongzi").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
