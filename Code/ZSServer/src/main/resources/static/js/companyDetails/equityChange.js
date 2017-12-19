$(function(){
	showShareStructure();
});
var arr;
function showShareStructure(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getEquityChange.json',
		success:function(res){
			if(res.success){
				arr = res.data.data.dataList;
				var thead = '<tr><th class="text-left">时间</th><th class="text-left">变动原因</th><th class="text-left">变动后A股总股本</th>'+
                            '<th class="text-left">变动后流通A股</th><th class="text-left">变动后限售A股</th></tr>';
				$("#equityChange").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var changeDate = getFormatDate(new Date(arr[i].changeDate));
					if(arr[i].afterAll == null){
						arr[i].afterAll = '---';
					}
					if(arr[i].afterNoLimit == null){
						arr[i].afterNoLimit = '---';
					}
					if(arr[i].afterLimit == null){
						arr[i].afterLimit = '---';
					}
					html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+changeDate+'</td><td>'+arr[i].changeReason+'</td>' +
							'<td>'+arr[i].afterAll+'</td><td>'+arr[i].afterNoLimit+'</td>' +
							'<td>'+arr[i].afterLimit+'</td>'+'</tr>';
				}
				$("#equityChange").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#equityChange").html(html);
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