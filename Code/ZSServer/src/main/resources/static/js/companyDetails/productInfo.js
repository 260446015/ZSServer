$(function(){
	showProductInfo();
});
function showProductInfo(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		async:false,
		contentType:'application/json',
		url:'/apis/openeyes/getProductInfo.json',
		success:function(res){
			if(res.success){
				var arr = res.data.result.page.rows;
				var html = '';
				for(var i=0;i<arr.length;i++){
					html += '<div class="col-md-3"><div class="img-list"><a href="javascript:void(0);">' +
							'<img src="'+arr[i].logo+'" /></a><div class="details"><p class="address">'+arr[i].product+'</p>' +
							'<p class="type">'+arr[i].hangye+'</p><p class="date-time">'+arr[i].yewu+'</p></div></div></div>';
				}
				$("#productInfo").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#productInfo").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}