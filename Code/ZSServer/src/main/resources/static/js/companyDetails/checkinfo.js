$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getCheckInfo.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data.companyPurchaseLandList;
				var html = '';
				for(var i=0;i<arr.length;i++){
					var signedDate = getFormatDate(new Date(arr[i].signedDate));
					var startTime = getFormatDate(new Date(arr[i].startTime));
					html += '<tr><td>'+signedDate+'</td><td>'+arr[i].elecSupervisorNo+'</td>' +
							'<td>'+arr[i].adminRegion+'</td><td>'+arr[i].location+'</td>' +
							'<td>'+arr[i].totalArea+'</td><td>'+startTime+'</td>'+'</tr>'
				}
				$("#checkinfo").html(html);
			}
		}
	});
}