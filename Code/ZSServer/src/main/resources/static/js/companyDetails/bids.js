$(function(){
	showBids();
});
function showBids(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getBids.json',
		success:function(res){
			if(res.success){
				var arr = res.data.data.items;
				var thead = '<tr><th class="text-left">发布时间</th><th class="text-left">标题</th><th class="text-left">采购人</th></tr>';
				$("#bids").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var dateStr = getFormatDate(new Date(arr[i].publishTime));
					html += '<tr><td>'+dateStr+'</td><td>'+arr[i].title+'</td>' +
							'<td>'+arr[i].purchaser+'</td></tr>'
				}
				$("#bids").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#bids").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}