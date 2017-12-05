$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":'北京百度网讯科技有限公司',"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getLawsuit.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data.items;
				var html = '';
				for(var i=0;i<arr.length;i++){
					var submittime = getFormatDate(new Date(arr[i].submittime));
					var startTime = getFormatDate(new Date(arr[i].startTime));
					html += '<tr><td>'+submittime+'</td><td>'+arr[i].caseno+'</td>' +
							'<td>'+arr[i].title+'</td><td>'+arr[i].doctype+'</td>' +
							'</tr>'
				}
				$("#lawSuit").html(html);
			}
		}
	});
}