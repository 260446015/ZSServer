$(function(){
	showBond();
});
var arr;
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getAllotmen.json',
		success:function(res){
			if(res.success){
				arr = res.data.data.dataList;
				var thead = '<tr><th class="text-left">时间</th><th class="text-left">轮次</th><th class="text-left">金额</th><th class="text-left">投资方</th>'
							'<th class="text-left">产品</th><th class="text-left">地区</th><th class="text-left">行业</th><th class="text-left">业务</th></tr>';
				$("#allotmen").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+arr[i].name+'</td><td>'+arr[i].relationship+'</td>' +
							'<td>'+arr[i].participationRatio+'</td><td>'+arr[i].investmentAmount+'</td>' +
							'<td>'+arr[i].profit+'</td><td>'+arr[i].reportMerge+'</td>'+'</tr>';
				}
				$("#allotmen").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#allotmen").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
