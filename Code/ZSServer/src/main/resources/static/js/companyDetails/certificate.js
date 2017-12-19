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
				console.log(arr);
				var html = '';
				for(var i=0;i<arr.length;i++){
					html += '<tr><td>'+arr[i].name+'</td><td>'+arr[i].legalPersonName+'</td>' +
							'<td>'+arr[i].regStatus+'</td><td>'+dateStr+'</td></tr>'
				}
				$("#companyList").html(html);
			}else{
				new Alert({flag:false,text:res.message,timer:2000}).show();
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
