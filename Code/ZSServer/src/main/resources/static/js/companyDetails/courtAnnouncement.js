$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":500}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getGonggao.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.courtAnnouncements;
				var html = '';
				for(var i=0;i<arr.length;i++){
					if(arr[i].party1 == null){
						arr[i].party1 = '---';
					}
					if(arr[i].party2 == null){
						arr[i].party2 = '---';
					}
					html += '<tr><td>'+arr[i].publishdate+'</td><td>'+arr[i].party1+'</td>' +
							'<td>'+arr[i].party2+'</td><td>'+arr[i].bltntype+'</td>' +
							'<td>'+arr[i].courtcode+'</td>'+'</tr>'
				}
				$("#courtAnnouncement").html(html);
			}
		}
	});
}