$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getZhixingInfo.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data.items;
				var html = '';
				for(var i=0;i<arr.length;i++){
					var caseCreateTime = getFormatDate(new Date(arr[i].caseCreateTime));
					var startTime = getFormatDate(new Date(arr[i].startTime));
					html += '<tr><td>'+caseCreateTime+'</td><td>'+arr[i].caseCode+'</td>' +
							'<td>'+arr[i].execMoney+'</td><td>'+arr[i].execCourtName+'</td>' +'</tr>'
				}
				$("#zhixinginfo").html(html);
			}
		}
	});
}