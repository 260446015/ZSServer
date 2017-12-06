$(function(){
	showBids();
});
function showBids(){
	var req = {"cname":'北京百度网讯科技有限公司',"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getBids.json',
		success:function(res){
			if(res.success){
				var arr = res.data.data.items;
				var html = '';
				for(var i=0;i<arr.length;i++){
					var dateStr = getFormatDate(new Date(arr[i].publishTime));
					html += '<tr><td>'+dateStr+'</td><td>'+arr[i].title+'</td>' +
							'<td>'+arr[i].purchaser+'</td></tr>'
				}
				$("#bids").html(html);
			}
		}
	});
}