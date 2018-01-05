$(function(){
	showCertificate();
	$(".more").on("click",function(){
		count ++;
		var html = '';
		var len = count * 10;
		if(len <= arr.length){
			for(var i=0;i<len;i++){
				html += '<tr><td>'+arr[i].certificateName+'</td><td>'+arr[i].certNo+'</td>' +
				'<td>'+arr[i].startDate+'</td><td>'+arr[i].endDate+'</td></tr>';
			}
			$("#certificate").html(html);
		}else{
			$(this).html("已无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
			$(".top").click(function(){
				var sc=$(window).scrollTop();
				$('body,html').animate({scrollTop:0},500);
			});
		}
	})
});
function showCertificate(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		async:false,
		contentType:'application/json',
		url:'/apis/openeyes/getCertificate.json',
		success:function(res){
			if(res.success){
				arr = res.data.data.resultList;
				var thead = '<tr><td>证书类型</td><td>许可编号</td><td>发证日期</td><td>截止日期</td></tr>	';
				$("#certificate").prev().html(thead);
				var html = '';
				if(arr.length > 10){
					for(var i=0;i<10;i++){
						html += '<tr><td>'+arr[i].certificateName+'</td><td>'+arr[i].certNo+'</td>' +
								'<td>'+arr[i].startDate+'</td><td>'+arr[i].endDate+'</td></tr>';
					}
				}else{
					for(var i=0;i<arr.length;i++){
						html += '<tr><td>'+arr[i].certificateName+'</td><td>'+arr[i].certNo+'</td>' +
								'<td>'+arr[i].startDate+'</td><td>'+arr[i].endDate+'</td></tr>';
					}
					$(".more").html("暂无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
					$(".top").click(function(){
						var sc=$(window).scrollTop();
						$('body,html').animate({scrollTop:0},500);
					});
				}
				
				$("#certificate").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#certificate").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
