$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getCheckInfo.json',
		success:function(res){
			if(res.success){
				var arr = res.data.data.companyPurchaseLandList;
				var thead = '<tr><th class="text-left">日期</th><th class="text-left">类型</th><th class="text-left">结果</th><th class="text-left">检查实施机关</th></tr>';
				$("#checkinfo").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var signedDate = getFormatDate(new Date(arr[i].signedDate));
					var startTime = getFormatDate(new Date(arr[i].startTime));
					html += '<tr><td>'+signedDate+'</td><td>'+arr[i].elecSupervisorNo+'</td>' +
							'<td>'+arr[i].adminRegion+'</td><td>'+arr[i].location+'</td>' +
							'<td>'+arr[i].totalArea+'</td><td>'+startTime+'</td>'+'</tr>'
				}
				$("#checkinfo").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#checkinfo").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}