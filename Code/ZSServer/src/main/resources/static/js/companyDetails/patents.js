$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getPatents.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data.items;
				var html = '';
				for(var i=0;i<arr.length;i++){
					var applicationPublishTime = getFormatDate(new Date(arr[i].applicationPublishTime));
					var startTime = getFormatDate(new Date(arr[i].startTime));
					html += '<tr><td>'+applicationPublishTime+'</td><td>'+arr[i].patentName+'</td>' +
							'<td>'+arr[i].patentNum+'</td><td>'+arr[i].applicationPublishNum+'</td>'+'</tr>'
				}
				$("#patents").html(html);
			}
		}
	});
}