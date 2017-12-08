$(function(){
	showTouzi();
});
function showTouzi(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getTouZi.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.result.items;
				var html = '';
				for(var i=0;i<arr.length;i++){
					var dateStr = getFormatDate(new Date(arr[i].tzdate));
					html += '<tr><td>'+dateStr+'</td><td>'+arr[i].lunci+'</td>' +
							'<td>'+arr[i].money+'</td><td>'+arr[i].company+'</td>' +
							'<td>'+arr[i].product+'</td><td>'+arr[i].location+'</td>'+
							'<td>'+arr[i].hangye1+'</td><td>'+arr[i].yewu+'</td>'+'</tr>'
				}
				$("#touzi").html(html);
			}
		}
	});
}