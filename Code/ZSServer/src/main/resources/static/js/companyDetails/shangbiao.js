$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getShangBiao.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.result.items;
				var html = '';
				for(var i=0;i<arr.length;i++){
					var appDate = getFormatDate(new Date(arr[i].appDate));
					var startTime = getFormatDate(new Date(arr[i].startTime));
					html += '<tr><td>'+appDate+'</td><td><img src=""/></td>' +
							'<td>'+arr[i].tmName+'</td><td>'+arr[i].regNo+'</td>' +
							'<td>'+arr[i].intCls+'</td><td>'+status+'</td>'+'</tr>'
				}
				$("#shangbiao").html(html);
			}
		}
	});
}