$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getPatents.json',
		success:function(res){
			if(res.success){
				var arr = res.data.data.items;
				var thead = '<tr><th class="text-left">申请公布日</th><th class="text-left">专利名称</th><th class="text-left">申请号</th><th class="text-left">申请公布号</th></tr>';
				$("#patents").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var applicationPublishTime = getFormatDate(new Date(arr[i].applicationPublishTime));
					var startTime = getFormatDate(new Date(arr[i].startTime));
					html += '<tr><td>'+applicationPublishTime+'</td><td>'+arr[i].patentName+'</td>' +
							'<td>'+arr[i].patentNum+'</td><td>'+arr[i].applicationPublishNum+'</td>'+'</tr>'
				}
				$("#patents").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#patents").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}