$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":'北京百度网讯科技有限公司',"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getDishonest.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.result.items;
				var html = '';
				for(var i=0;i<arr.length;i++){
					var regdate = getFormatDate(new Date(arr[i].regdate));
					html += '<tr><td>'+regdate+'</td><td>'+arr[i].gistid+'</td>' +
							'<td>'+arr[i].courtname+'</td><td>'+arr[i].performance+'</td>' +
							'<td>'+arr[i].gistid+'</td>'+'</tr>'
				}
				$("#dishonest").html(html);
			}
		}
	});
}