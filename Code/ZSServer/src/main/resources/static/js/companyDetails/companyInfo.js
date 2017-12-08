$(function(){
	showBids();
});
function showBids(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":500}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getCompanyInfo.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var data = res.data.data;
				$("#companyInfo").html(html);
			}
		}
	});
}