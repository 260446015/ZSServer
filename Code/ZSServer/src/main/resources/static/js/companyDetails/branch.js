$(function(){
	showBranch();
});
function showBranch(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getBranch.json',
		success:function(res){
			if(res.success){
				var arr = res.data.data.result;
				var html = '';
				for(var i=0;i<arr.length;i++){
					var dateStr = getFormatDate(new Date(arr[i].estiblishTime));
					if(arr[i].legalPersonName == null){
						arr[i].legalPersonName = '---';
					}
					if(arr[i].regStatus == null){
						arr[i].regStatus = '---';
					}
					html += '<tr><td>'+arr[i].name+'</td><td>'+arr[i].legalPersonName+'</td>' +
							'<td>'+arr[i].regStatus+'</td><td>'+dateStr+'</td></tr>'
				}
				$("#companyList").html(html);
			}else{
				new Alert({flag:false,text:res.message,timer:2000}).show();
			}
		}
	});
}
