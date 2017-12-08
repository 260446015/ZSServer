$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getCopyReg.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data.items;
				var html = '';
				for(var i=0;i<arr.length;i++){
					var regtime = getFormatDate(new Date(arr[i].regtime));
					var startTime = getFormatDate(new Date(arr[i].startTime));
					html += '<tr><td>'+regtime+'</td><td>'+arr[i].fullname+'</td>' +
							'<td>'+arr[i].simplename+'</td><td>'+arr[i].regnum+'</td>' +
							'<td>'+arr[i].catnum+'</td><td>'+arr[i].version+'</td>'+'</tr>'
				}
				$("#copyReg").html(html);
			}
		}
	});
}