$(function(){
	showSeniorExecutive();
});
var arr;
function showSeniorExecutive(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":20}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getSeniorExecutive.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				arr = res.data.data.dataList;
				var html = '';
				for(var i=0;i<arr.length;i++){
					html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+arr[i].name+'</td><td>'+arr[i].position+'</td>' +
							'<td>'+arr[i].numberOfShares+'</td><td>'+arr[i].age+'</td>' +
							'<td><a href="javascript:void(0)" onclick="showModel(this)">查看详情</a></td>'+'</tr>';
				}
				$("#seniorExecutive").html(html);
			}
		}
	});
}