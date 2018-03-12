$(function(){
	showHoldingCompany();
	$(".more").on("click",function(){
		count ++;
		var html = '';
		var len = count * 10;
		if(len <= arr.length){
			for(var i=0;i<len;i++){
				html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+arr[i].name+'</td><td>'+arr[i].relationship+'</td>' +
				'<td>'+arr[i].participationRatio+'</td><td>'+arr[i].investmentAmount+'</td>' +
				'<td>'+arr[i].profit+'</td><td>'+arr[i].reportMerge+'</td>'+'</tr>';
			}
			$("#holdingCompany").html(html);
		}else{
			$(this).html("已无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
			$(".top").click(function(){
				var sc=$(window).scrollTop();
				$('body,html').animate({scrollTop:0},500);
			});
		}
	})
});
function showHoldingCompany(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		async:false,
		contentType:'application/json',
		url:'/apis/openeyes/getHoldingCompany.json',
		success:function(res){
			if(res.success){
				arr = res.data.result.items;
				var thead = '<tr><th class="text-left">关联公司</th><th class="text-left">参股关系</th><th class="text-left">参股比例（%）</th><th class="text-left">投资金额（万元）</th>'+
                            '<th class="text-left">被参股公司净利润(元)</th><th class="text-left">是否报表合并</th></tr>';
				$("#holdingCompany").prev().html(thead);
				var html = '';
				if(arr.length > 10){
					for(var i=0;i<10;i++){
						html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+arr[i].name+'</td><td>'+arr[i].relationship+'</td>' +
								'<td>'+arr[i].participationRatio+'</td><td>'+arr[i].investmentAmount+'</td>' +
								'<td>'+arr[i].profit+'</td><td>'+arr[i].reportMerge+'</td>'+'</tr>';
					}
				}else{
					for(var i=0;i<arr.length;i++){
						html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+arr[i].name+'</td><td>'+arr[i].relationship+'</td>' +
								'<td>'+arr[i].participationRatio+'</td><td>'+arr[i].investmentAmount+'</td>' +
								'<td>'+arr[i].profit+'</td><td>'+arr[i].reportMerge+'</td>'+'</tr>';
					}
					$(".more").html("暂无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
					$(".top").click(function(){
						var sc=$(window).scrollTop();
						$('body,html').animate({scrollTop:0},500);
					});
				}
				
				$("#holdingCompany").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#holdingCompany").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
