$(function(){
	showBond();
});
var arr;
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":20}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getBond.json',
		success:function(res){
			if(res.success){
				arr = res.data.data.bondList;
				var thead = '<tr><th class="text-left">发行日期</th><th class="text-left">债券名称</th><th class="text-left">债券代码</th>' +
                            '<th class="text-left">债券类型</th><th class="text-left">最新评级</th><th class="text-left">操作</th></tr>'
				$("#bond").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+arr[i].publishTime+'</td><td>'+arr[i].bondName+'</td>' +
							'<td>'+arr[i].bondNum+'</td><td>'+arr[i].bondType+'</td>' +
							'<td>'+arr[i].debtRating+'</td><td><a href="javascript:void(0)" onclick="showModel(this)">查看详情</a></td>'+'</tr>';
				}
				$("#bond").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#bond").html(html);
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