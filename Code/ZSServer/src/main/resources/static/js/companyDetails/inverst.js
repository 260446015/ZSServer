$(function(){
	showInverst();
});
function showInverst(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":500}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getInverst.json',
		success:function(res){
			if(res.success){
				var arr = res.data.data.result;
				var html = '';
				for(var i=0;i<arr.length;i++){
					var dateStr = getFormatDate(new Date(arr[i].estiblishTime));
					if(arr[i].percent == null){
						arr[i].percent = '---';
					}
					html += '<tr><td>'+arr[i].name+'</td><td>'+arr[i].legalPersonName+'</td>' +
							'<td>'+arr[i].regCapital+'</td><td>'+arr[i].amount+'</td><td>'+arr[i].percent+'</td><td>'+dateStr+'</td><td>'+arr[i].regStatus+'</td></tr>';
				}
				$("#inverst").html(html);
			}
		}
	});
}
