$(function(){
	showAppbkinfo();
});
function showAppbkinfo(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getAppbkInfo.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data.companyPurchaseLandList;
				var thead = '<tr><th class="text-left">图标</th><th class="text-left">产品名称</th><th class="text-left">产品简称</th>' +
                            '<th class="text-left">产品分类</th><th class="text-left">领域</th></tr>';
				$("#appbkinfo").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var signedDate = getFormatDate(new Date(arr[i].signedDate));
					var startTime = getFormatDate(new Date(arr[i].startTime));
					html += '<tr><td>'+signedDate+'</td><td>'+arr[i].elecSupervisorNo+'</td>' +
							'<td>'+arr[i].adminRegion+'</td><td>'+arr[i].location+'</td>' +
							'<td>'+arr[i].totalArea+'</td><td>'+startTime+'</td>'+'</tr>'
				}
				$("#appbkinfo").html(html);
			}else{
				 var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#appbkinfo").html(html);
			}
		}
	});
}