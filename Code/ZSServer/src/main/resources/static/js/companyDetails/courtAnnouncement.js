$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":'北京百度网讯科技有限公司',"pageNumber":1,"pageSize":10}
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
					html += '<tr><td>'+arr[i].publishdate+'</td><td>'+arr[i].party1+'</td>' +
							'<td>'+arr[i].party2+'</td><td>'+arr[i].bltntype+'</td>' +
							'<td>'+arr[i].courtcode+'</td>'+'</tr>'
				}
				$("#courtAnnouncement").html(html);
			}
		}
	});
}