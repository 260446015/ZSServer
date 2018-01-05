$(function(){
	showBranch();
	$(".more").on("click",function(){
		count ++;
		var html = '';
		var len = count * 10;
		if(len <= arr.length){
			for(var i=0;i<len;i++){
				var dateStr = getFormatDate(new Date(arr[i].estiblishTime));
				if(arr[i].legalPersonName == null){
					arr[i].legalPersonName = '---';
				}
				if(arr[i].regStatus == null){
					arr[i].regStatus = '---';
				}
				html += '<tr><td>'+arr[i].name+'</td><td>'+arr[i].legalPersonName+'</td>' +
						'<td>'+arr[i].regStatus+'</td><td>'+dateStr+'</td></tr>';
			}
			$("#branch").html(html);
		}else{
			$(this).html("已无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
			$(".top").click(function(){
				var sc=$(window).scrollTop();
				$('body,html').animate({scrollTop:0},500);
			});
		}
	})
});
function showBranch(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		async:false,
		contentType:'application/json',
		url:'/apis/openeyes/getBranch.json',
		success:function(res){
			if(res.success){
				arr = res.data.data.result;
				var thead = '<tr><th class="text-left">企业名称</th><th class="text-left">法定代表人</th><th class="text-left">状态</th><th class="text-left">注册时间</th></tr>';
				$("#branch").prev().html(thead);
				var html = '';
				if(arr.length > 10){
					for(var i=0;i<10;i++){
						var dateStr = getFormatDate(new Date(arr[i].estiblishTime));
						if(arr[i].legalPersonName == null){
							arr[i].legalPersonName = '---';
						}
						if(arr[i].regStatus == null){
							arr[i].regStatus = '---';
						}
						html += '<tr><td>'+arr[i].name+'</td><td>'+arr[i].legalPersonName+'</td>' +
								'<td>'+arr[i].regStatus+'</td><td>'+dateStr+'</td></tr>';
					}
				}else{
					for(var i=0;i<arr.length;i++){
						var dateStr = getFormatDate(new Date(arr[i].estiblishTime));
						if(arr[i].legalPersonName == null){
							arr[i].legalPersonName = '---';
						}
						if(arr[i].regStatus == null){
							arr[i].regStatus = '---';
						}
						html += '<tr><td>'+arr[i].name+'</td><td>'+arr[i].legalPersonName+'</td>' +
								'<td>'+arr[i].regStatus+'</td><td>'+dateStr+'</td></tr>';
					}
					$(".more").html("暂无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
					$(".top").click(function(){
						var sc=$(window).scrollTop();
						$('body,html').animate({scrollTop:0},500);
					});
				}
				
				$("#branch").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#branch").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
