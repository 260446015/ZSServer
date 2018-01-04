$(function(){
	showTaxCredit();
	$(".more").on("click",function(){
		count ++;
		var html = '';
		var len = count * 10;
		if(len <= arr.length){
			for(var i=0;i<len;i++){
				html += '<tr><td>'+arr[i].year+'</td><td>'+arr[i].grade+'</td>' +
				'<td>'+arr[i].type+'</td><td>'+arr[i].idNumber+'</td>' +
				'<td>'+arr[i].evalDepartment+'</td>'+'</tr>';
			}
			$("#taxCredit").html(html);
		}else{
			$(this).html("已无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
			$(".top").click(function(){
				var sc=$(window).scrollTop();
				$('body,html').animate({scrollTop:0},500);
			});
		}
	})
});
function showTaxCredit(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getTaxCredit.json',
		success:function(res){
			if(res.success){
				arr = res.data.data.items;
				var thead = '<tr><th class="text-left">年份</th><th class="text-left">纳税评级</th><th class="text-left">类型</th><th class="text-left">纳税人识别号</th><th class="text-left">评价单位</th></tr>';
				$("#taxCredit").prev().html(thead);
				var html = '';
				if(arr.length > 10){
					for(var i=0;i<10;i++){
						html += '<tr><td>'+arr[i].year+'</td><td>'+arr[i].grade+'</td>' +
								'<td>'+arr[i].type+'</td><td>'+arr[i].idNumber+'</td>' +
								'<td>'+arr[i].evalDepartment+'</td>'+'</tr>';
					}
				}else{
					for(var i=0;i<arr.length;i++){
						html += '<tr><td>'+arr[i].year+'</td><td>'+arr[i].grade+'</td>' +
								'<td>'+arr[i].type+'</td><td>'+arr[i].idNumber+'</td>' +
								'<td>'+arr[i].evalDepartment+'</td>'+'</tr>';
					}
					$(".more").html("暂无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
					$(".top").click(function(){
						var sc=$(window).scrollTop();
						$('body,html').animate({scrollTop:0},500);
					});
				}
				
				$("#taxCredit").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#taxCredit").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}