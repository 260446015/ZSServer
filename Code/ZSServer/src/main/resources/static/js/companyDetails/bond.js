$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":'嘉兴银行股份有限公司',"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getBond.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data.bondList;
				var html = '';
				for(var i=0;i<arr.length;i++){
					html += '<tr><td>'+arr[i].publishTime+'</td><td>'+arr[i].bondName+'</td>' +
							'<td>'+arr[i].bondNum+'</td><td>'+arr[i].bondType+'</td>' +
							'<td>'+arr[i].debtRating+'</td>'+'</tr>'
				}
				$("#bond").html(html);
			}
		}
	});
}