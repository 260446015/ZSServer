$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getLawsuit.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data.items;
				var thead = '<tr><th class="text-left">日期</th><th class="text-left">案件号</th><th class="text-left">裁判文书</th><th class="text-left">案件类型</th></tr>';
				$("#lawSuit").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var submittime = getFormatDate(new Date(arr[i].submittime));
					var startTime = getFormatDate(new Date(arr[i].startTime));
					html += '<tr><td>'+submittime+'</td><td>'+arr[i].caseno+'</td>' +
							'<td>'+arr[i].title+'</td><td>'+arr[i].doctype+'</td>' +
							'</tr>'
				}
				$("#lawSuit").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#lawSuit").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}