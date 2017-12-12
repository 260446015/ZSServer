$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getIcp.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data;
				var html = '';
				for(var i=0;i<arr.length;i++){
					var web;
					if(arr[i].webSite != null){
						web = arr[i].webSite.replace('["','').replace('"]','').split(';')[0];
					}
					html += '<tr><td>'+arr[i].examineDate+'</td><td>'+arr[i].webName+'</td>' +
							'<td>'+web+'</td><td>'+arr[i].ym+'</td>' +
							'<td>'+arr[i].liscense+'</td><td>正常</td><td>'+arr[i].companyType+'</td>'+'</tr>'
				}
				$("#icp").html(html);
			}
		}
	});
}