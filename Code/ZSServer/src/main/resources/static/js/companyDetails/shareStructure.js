$(function(){
	showShareStructure();
});
var arr;
function showShareStructure(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":500}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getShareStructure.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				arr = res.data.data.dataList;
				var thead = '<tr><th class="text-left">时间</th><th class="text-left">总股本</th><th class="text-left">A股总股本</th><th class="text-left">流通A股</th><th class="text-left">限售A股</th>'+
                            '<th class="text-left">H股总股本</th><th class="text-left">流通H股</th><th class="text-left">限售H股</th><th class="text-left">变动原因</th></tr>';
				$("#shareStructure").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var pubDate = getFormatDate(new Date(arr[i].pubDate));
					if(arr[i].hshareAll == null){
						arr[i].hshareAll = '---';
					}
					if(arr[i].hnoLimitShare == null){
						arr[i].hnoLimitShare = '---';
					}
					if(arr[i].hlimitShare == null){
						arr[i].hlimitShare = '---';
					}
					html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+pubDate+'</td><td>'+arr[i].shareAll+'</td>' +
							'<td>'+arr[i].ashareAll+'</td><td>'+arr[i].noLimitShare+'</td>' +
							'<td>'+arr[i].limitShare+'</td><td>'+arr[i].hshareAll+'</td><td>'+arr[i].hnoLimitShare+'</td>'+
							'<td>'+arr[i].hlimitShare+'</td><td>'+arr[i].changeReason+'</td>'+'</tr>';
				}
				$("#shareStructure").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#shareStructure").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
function showModel(_this){	
	var id = $(_this).parent().siblings('input').val();
	var modelHtml = '';
	for(var i=0;i<arr.length;i++){
		if(id == arr[i].id){
			$('#bondName').html(arr[i].bondName);
			$('#bondNum').html(arr[i].bondNum);
			$('#publisherName').html(arr[i].publisherName);
			$('#bondType').html(arr[i].bondType);
			$('#publishTime').html(arr[i].publishTime);
			$('#calInterestType').html(arr[i].calInterestType);
			$('#bondTradeTime').html(arr[i].bondTradeTime);
			$('#creditRatingGov').html(arr[i].creditRatingGov);
			$('#bondStopTime').html(arr[i].bondStopTime);
			$('#faceValue').html(arr[i].faceValue);
			$('#refInterestRate').html(arr[i].refInterestRate);
			$('#faceInterestRate').html(arr[i].faceInterestRate);
			$('#realIssuedQuantity').html(arr[i].realIssuedQuantity);
			$('#planIssuedQuantity').html(arr[i].planIssuedQuantity);
			$('#issuedPrice').html(arr[i].issuedPrice);
			$('#interestDiff').html(arr[i].interestDiff);
			$('#payInterestHZ').html(arr[i].payInterestHZ);
			$('#startCalInterestTime').html(arr[i].startCalInterestTime);
			$('#exeRightType').html(arr[i].exeRightType);
			$('#exeRightTime').html(arr[i].exeRightTime);
			$('#escrowAgent').html(arr[i].escrowAgent);
			$('#flowRange').html(arr[i].flowRange);
		}
	}
	$("#myModal").modal('show');
}