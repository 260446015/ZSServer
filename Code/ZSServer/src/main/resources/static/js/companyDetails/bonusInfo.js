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
		url:'/apis/openeyes/getBonusInfo.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				arr = res.data.data.dataList;
				var thead = '<tr><th class="text-left">董事会日期</th><th class="text-left">股东大会日期</th><th class="text-left">实施日期</th><th class="text-left">分红方案说明</th>'+
                            '<th class="text-left">A股股权登记日</th><th class="text-left">A股股权除息日</th><th class="text-left">A股派息日</th><th class="text-left">方案进度</th><th class="text-left">股利支付率（%）</th>'+
                            '<th class="text-left">分红率（%）</th></tr>';
				$("#bonusInfo").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					if(arr[i].adividendDate == null){
						arr[i].adividendDate = '---';
					}
					html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+arr[i].boardDate+'</td><td>'+arr[i].shareholderDate+'</td>' +
							'<td>'+arr[i].implementationDate+'</td><td>'+arr[i].introduction+'</td>' +
							'<td>'+arr[i].asharesDate+'</td><td>'+arr[i].acuxiDate+'</td>'+
							'<td>'+arr[i].adividendDate+'</td><td>'+arr[i].progress+'</td>'+
							'<td>'+arr[i].payment+'</td><td>'+arr[i].dividendRate+'</td>'+'</tr>';
				}
				$("#bonusInfo").html(html);
			}else{
				html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#bonusInfo").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
