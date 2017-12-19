$(function(){
	showCertificate();
});
function showCertificate(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getCertificate.json',
		success:function(res){
			if(res.success){
				var arr = res.data.data.resultList;
				var thead = '<tr><td>证书类型</td><td>许可编号</td><td>发证日期</td><td>截止日期</td></tr>	';
				$("#certificate").prev().html(thead);
				console.log(arr);
				var html = '';
				for(var i=0;i<arr.length;i++){
					html += '<tr><td>'+arr[i].certificateName+'</td><td>'+arr[i].certNo+'</td>' +
							'<td>'+arr[i].startDate+'</td><td>'+arr[i].endDate+'</td></tr>'
				}
				$("#certificate").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#certificate").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
