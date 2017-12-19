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
		url:'/apis/openeyes/getHoldingCompany.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				arr = res.data.data.dataList;
				var thead = '<tr><th class="text-left">关联公司</th><th class="text-left">参股关系</th><th class="text-left">参股比例（%）</th><th class="text-left">投资金额（万元）</th>'+
                            '<th class="text-left">被参股公司净利润(元)</th><th class="text-left">是否报表合并</th></tr>';
				$("#holdingCompany").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+arr[i].name+'</td><td>'+arr[i].relationship+'</td>' +
							'<td>'+arr[i].participationRatio+'</td><td>'+arr[i].investmentAmount+'</td>' +
							'<td>'+arr[i].profit+'</td><td>'+arr[i].reportMerge+'</td>'+'</tr>';
				}
				$("#holdingCompany").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#holdingCompany").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
