$(function(){
	showChangeInfo();
});
function showChangeInfo(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getChangeInfo.json',
		success:function(res){
			if(res.success){
				console.log(res);
				var arr = res.data.data.result;
				var html = '';
				for(var i=0;i<arr.length;i++){
					html += '<tr><td class="tw160">'+arr[i].changeTime+'</td><td class="tw180">'+arr[i].changeItem+'</td>' +
							'<td>'+arr[i].contentBefore+'</td><td>'+arr[i].contentAfter+'</td></tr>';
				}
				$("#changeInfo").html(html);
			}
		}
	});
}