$(function(){
	showCourtAnnounce();
	$(".more").on("click",function(){
		count ++;
		var html = '';
		var len = count * 10;
		if(len <= arr.length){
			for(var i=0;i<len;i++){
				if(arr[i].party1 == null){
					arr[i].party1 = '---';
				}
				if(arr[i].party2 == null){
					arr[i].party2 = '---';
				}
				html += '<tr><td>'+arr[i].publishdate+'</td><td>'+arr[i].party1+'</td>' +
						'<td>'+arr[i].party2+'</td><td>'+arr[i].bltntype+'</td>' +
						'<td>'+arr[i].courtcode+'</td>'+'</tr>';
			}
			$("#courtAnnouncement").html(html);
		}else{
			$(this).html("已无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
			$(".top").click(function(){
				var sc=$(window).scrollTop();
				$('body,html').animate({scrollTop:0},500);
			});
		}
	})
});
function showCourtAnnounce(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		async:false,
		contentType:'application/json',
		url:'/apis/openeyes/getGonggao.json',
		success:function(res){
			if(res.success){
				arr = res.data.courtAnnouncements;
				var thead = '<tr><th class="text-left">公告时间</th><th class="text-left">上诉方</th><th class="text-left">被诉方</th>'+
                            '<th class="text-left">公告类型</th><th class="text-left">法院</th></tr>';
				$("#courtAnnouncement").prev().html(thead);
				var html = '';
				if(arr.length > 10){
					for(var i=0;i<10;i++){
						if(arr[i].party1 == null){
							arr[i].party1 = '---';
						}
						if(arr[i].party2 == null){
							arr[i].party2 = '---';
						}
						html += '<tr><td>'+arr[i].publishdate+'</td><td>'+arr[i].party1+'</td>' +
								'<td>'+arr[i].party2+'</td><td>'+arr[i].bltntype+'</td>' +
								'<td>'+arr[i].courtcode+'</td>'+'</tr>';
					}
				}else{
					for(var i=0;i<arr.length;i++){
						if(arr[i].party1 == null){
							arr[i].party1 = '---';
						}
						if(arr[i].party2 == null){
							arr[i].party2 = '---';
						}
						html += '<tr><td>'+arr[i].publishdate+'</td><td>'+arr[i].party1+'</td>' +
								'<td>'+arr[i].party2+'</td><td>'+arr[i].bltntype+'</td>' +
								'<td>'+arr[i].courtcode+'</td>'+'</tr>';
					}
					$(".more").html("暂无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
					$(".top").click(function(){
						var sc=$(window).scrollTop();
						$('body,html').animate({scrollTop:0},500);
					});
				}
				$("#courtAnnouncement").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#courtAnnouncement").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}