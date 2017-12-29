$(function(){
	showBond();
	$(".more").on("click",function(){
		count ++;
		var html = '';
		var len = count * 10;
		if(len < arr.length){
			for(var i=0;i<len;i++){
				html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+arr[i].time+'</td><td>'+arr[i].title+'</td>' +
						'</tr>';
			}
			$("#announcement").html(html);
		}else{
			$(this).html("已无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
			$(".top").click(function(){
				var sc=$(window).scrollTop();
				$('body,html').animate({scrollTop:0},500);
			});
		}
	})
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getAnnouncement.json',
		success:function(res){
			if(res.success){
				arr = res.data.data.dataList;
				var thead = '<tr><th class="text-left">日期</th><th class="text-left">上市公告</th></tr>';
				$("#announcement").prev().html(thead);
				var html = '';
				
				for(var i=0;i<10;i++){
					html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+arr[i].time+'</td><td>'+arr[i].title+'</td>' +
							'</tr>';
				}
				$("#announcement").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#announcement").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
