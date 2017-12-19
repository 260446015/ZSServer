$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getPurchaseland.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data.companyPurchaseLandList;
				var thead = '<tr><th class="text-left">签订日期</th><th class="text-left">电子监管号</th><th class="text-left">行政区</th><th class="text-left">宗地位置</th>'+
                            '<th class="text-left">供地总面积</th><th class="text-left">约定动工日</th></tr>';
				$("#bond").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var signedDate = getFormatDate(new Date(arr[i].signedDate));
					var startTime = getFormatDate(new Date(arr[i].startTime));
					html += '<tr><td>'+signedDate+'</td><td>'+arr[i].elecSupervisorNo+'</td>' +
							'<td>'+arr[i].adminRegion+'</td><td>'+arr[i].location+'</td>' +
							'<td>'+arr[i].totalArea+'</td><td>'+startTime+'</td>'+'</tr>'
				}
				$("#bond").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#bond").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}