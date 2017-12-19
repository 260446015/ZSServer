$(function(){
	showChangeInfo();
});
function showChangeInfo(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getChangeInfo.json',
		success:function(res){
			if(res.success){
				console.log(res);
				var arr = res.data.data.result;
				var thead = '<tr><th class="text-left">变更时间</th><th class="text-left">变更项目</th><th class="text-left">变更前</th><th class="text-left">变更后</th></tr>';
				$("#changeInfo").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					html += '<tr><td class="tw160">'+arr[i].changeTime+'</td><td class="tw180">'+arr[i].changeItem+'</td>' +
							'<td>'+arr[i].contentBefore+'</td><td>'+arr[i].contentAfter+'</td></tr>';
				}
				$("#changeInfo").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#changeInfo").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}