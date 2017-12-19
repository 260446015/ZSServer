$(function(){
	showShareStructure();
});
var arr;
function showShareStructure(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getShareStructure.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				arr = res.data.data.dataList;
				var thead = '<tr><th class="text-left">时间</th><th class="text-left">总股本</th><th class="text-left">A股总股本</th><th class="text-left">流通A股</th><th class="text-left">限售A股</th>'+
                            '<th class="text-left">H股总股本</th><th class="text-left">流通H股</th><th class="text-left">限售H股</th><th class="text-left">变动原因</th></tr>';
				$("#shareStructure").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var pubDate = getFormatDate(new Date(arr[i].pubDate));
					if(arr[i].hshareAll == null){
						arr[i].hshareAll = '---';
					}
					if(arr[i].hnoLimitShare == null){
						arr[i].hnoLimitShare = '---';
					}
					if(arr[i].hlimitShare == null){
						arr[i].hlimitShare = '---';
					}
					html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+pubDate+'</td><td>'+arr[i].shareAll+'</td>' +
							'<td>'+arr[i].ashareAll+'</td><td>'+arr[i].noLimitShare+'</td>' +
							'<td>'+arr[i].limitShare+'</td><td>'+arr[i].hshareAll+'</td><td>'+arr[i].hnoLimitShare+'</td>'+
							'<td>'+arr[i].hlimitShare+'</td><td>'+arr[i].changeReason+'</td>'+'</tr>';
				}
				$("#shareStructure").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#shareStructure").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
