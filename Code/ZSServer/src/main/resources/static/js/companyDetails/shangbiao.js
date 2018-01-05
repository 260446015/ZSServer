$(function(){
	showShangBiao();
	$(".more").on("click",function(){
		count ++;
		var html = '';
		var len = count * 10;
		if(len <= arr.length){
			for(var i=0;i<len;i++){
				var appDate = getFormatDate(new Date(arr[i].appDate));
				if(arr[i].tmName == null){
					arr[i].tmName = '---';
				}
				html += '<tr><td>'+appDate+'</td><td><img src="'+arr[i].tmPic+'" width="120px" height="50px"/></td>' +
						'<td>'+arr[i].tmName+'</td><td>'+arr[i].regNo+'</td>' +
						'<td>'+arr[i].intCls+'</td><td>'+arr[i].status+'</td>'+'</tr>';
			}
			$("#shangbiao").html(html);
		}else{
			$(this).html("已无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
			$(".top").click(function(){
				var sc=$(window).scrollTop();
				$('body,html').animate({scrollTop:0},500);
			});
		}
	})
});
function showShangBiao(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		async:false,
		contentType:'application/json',
		url:'/apis/openeyes/getShangBiao.json',
		success:function(res){
			if(res.success){
				arr = res.data.data.items;
				var thead = '<tr><th class="text-left">申请日期</th><th class="text-left">商标</th><th class="text-left">商标名称</th><th class="text-left">注册号</th>'+
                            '<th class="text-left">类别</th><th class="text-left">状态</th></tr>';
				$("#shangbiao").prev().html(thead);
				var html = '';
				if(arr.length > 10){
					for(var i=0;i<10;i++){
						var appDate = getFormatDate(new Date(arr[i].appDate));
						if(arr[i].tmName == null){
							arr[i].tmName = '---';
						}
						html += '<tr><td>'+appDate+'</td><td><img src="'+arr[i].tmPic+'" width="120px" height="50px"/></td>' +
								'<td>'+arr[i].tmName+'</td><td>'+arr[i].regNo+'</td>' +
								'<td>'+arr[i].intCls+'</td><td>'+arr[i].status+'</td>'+'</tr>';
					}
				}else{
					for(var i=0;i<arr.length;i++){
						var appDate = getFormatDate(new Date(arr[i].appDate));
						if(arr[i].tmName == null){
							arr[i].tmName = '---';
						}
						html += '<tr><td>'+appDate+'</td><td><img src="'+arr[i].tmPic+'" width="120px" height="50px"/></td>' +
								'<td>'+arr[i].tmName+'</td><td>'+arr[i].regNo+'</td>' +
								'<td>'+arr[i].intCls+'</td><td>'+arr[i].status+'</td>'+'</tr>';
					}
					$(".more").html("暂无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
					$(".top").click(function(){
						var sc=$(window).scrollTop();
						$('body,html').animate({scrollTop:0},500);
					});
				}
				
				$("#shangbiao").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#shangbiao").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}