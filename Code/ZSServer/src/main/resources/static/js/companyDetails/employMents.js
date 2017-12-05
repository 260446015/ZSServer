$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":'嘉兴银行股份有限公司',"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getEmployment.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.result.items;
				var html = '';
				for(var i=0;i<arr.length;i++){
					var startdate = getFormatDate(new Date(arr[i].startdate));
					html += '<tr><td>'+startdate+'</td><td>'+arr[i].title+'</td>' +
							'<td>'+arr[i].oriSalary+'</td><td>'+arr[i].experience+'</td>' +
							'<td>'+arr[i].employerNumber+'</td><td>'+arr[i].city+'</td>'+'</tr>'
				}
				$("#employMents").html(html);
			}
		}
	});
}