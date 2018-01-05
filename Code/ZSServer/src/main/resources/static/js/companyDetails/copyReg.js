$(function(){
	showCopyReg();
	$(".more").on("click",function(){
		count ++;
		var html = '';
		var len = count * 10;
		if(len <= arr.length){
			for(var i=0;i<len;i++){
				var regtime = getFormatDate(new Date(arr[i].regtime));
				if(arr[i].simplename == null){
					arr[i].simplename = '---';
				}
				html += '<tr><td>'+regtime+'</td><td>'+arr[i].fullname+'</td>' +
						'<td>'+arr[i].simplename+'</td><td>'+arr[i].regnum+'</td>' +
						'<td>'+arr[i].catnum+'</td><td>'+arr[i].version+'</td>'+'</tr>';
			}
			$("#copyReg").html(html);
		}else{
			$(this).html("已无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
			$(".top").click(function(){
				var sc=$(window).scrollTop();
				$('body,html').animate({scrollTop:0},500);
			});
		}
	})
});
function showCopyReg(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		async:false,
		contentType:'application/json',
		url:'/apis/openeyes/getCopyReg.json',
		success:function(res){
			if(res.success){
				arr = res.data.data.items;
				var thead = '<tr><th class="text-left">批准日期</th><th class="text-left">软件全称</th><th class="text-left">软件简称</th><th class="text-left">登记号</th>'+
							'<th class="text-left">分类号</th><th class="text-left">版本号</th></tr>';
				$("#copyReg").prev().html(thead);
				var html = '';
				if(arr.length > 10){
					for(var i=0;i<10;i++){
						var regtime = getFormatDate(new Date(arr[i].regtime));
						if(arr[i].simplename == null){
							arr[i].simplename = '---';
						}
						html += '<tr><td>'+regtime+'</td><td>'+arr[i].fullname+'</td>' +
								'<td>'+arr[i].simplename+'</td><td>'+arr[i].regnum+'</td>' +
								'<td>'+arr[i].catnum+'</td><td>'+arr[i].version+'</td>'+'</tr>';
					}
				}else{
					for(var i=0;i<arr.length;i++){
						var regtime = getFormatDate(new Date(arr[i].regtime));
						if(arr[i].simplename == null){
							arr[i].simplename = '---';
						}
						html += '<tr><td>'+regtime+'</td><td>'+arr[i].fullname+'</td>' +
								'<td>'+arr[i].simplename+'</td><td>'+arr[i].regnum+'</td>' +
								'<td>'+arr[i].catnum+'</td><td>'+arr[i].version+'</td>'+'</tr>';
					}
					$(".more").html("暂无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
					$(".top").click(function(){
						var sc=$(window).scrollTop();
						$('body,html').animate({scrollTop:0},500);
					});
				}
				
				$("#copyReg").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#copyReg").html(html);
				window.setTimeout(goBack,2000);
			}
		}
	});
}