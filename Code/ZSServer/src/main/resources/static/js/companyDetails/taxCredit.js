$(function(){
	showTaxCredit();
});
function showTaxCredit(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getTaxCredit.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data.items;
				var html = '';
				for(var i=0;i<arr.length;i++){
					html += '<tr><td>'+arr[i].year+'</td><td>'+arr[i].grade+'</td>' +
							'<td>'+arr[i].type+'</td><td>'+arr[i].idNumber+'</td>' +
							'<td>'+arr[i].evalDepartment+'</td>'+'</tr>'
				}
				$("#taxCredit").html(html);
			}
		}
	});
}