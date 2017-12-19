$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getZhixingInfo.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data.items;
				var thead = '<tr><th class="text-left">立案日期</th><th class="text-left">案号</th><th class="text-left">执行标的</th><th class="text-left">执行法院</th></tr>';
				$("#zhixinginfo").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var caseCreateTime = getFormatDate(new Date(arr[i].caseCreateTime));
					var startTime = getFormatDate(new Date(arr[i].startTime));
					html += '<tr><td>'+caseCreateTime+'</td><td>'+arr[i].caseCode+'</td>' +
							'<td>'+arr[i].execMoney+'</td><td>'+arr[i].execCourtName+'</td>' +'</tr>'
				}
				$("#zhixinginfo").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#zhixinginfo").html(html);
				window.setTimeout(goBack,2000);
			}
		}
	});
}