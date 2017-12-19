$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getVolatility.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data;
				$("#cname").html(arr.stockname);
				$("#tmaxprice").html(arr.tmaxprice);
				$("#tminprice").html(arr.tminprice);
				$("#topenprice").html(arr.topenprice);
				$("#pprice").html(arr.pprice);
				$("#thighprice").html(arr.thighprice);
				$("#tlowprice").html(arr.tlowprice);
				$("#tvalue").html(arr.tvalue);
				$("#flowvalue").html(arr.flowvalue);
				$("#tamount").html(arr.tamount);
				$("#tamounttotal").html(arr.tamounttotal);
				$("#tvaluep").html(arr.tvaluep);
				$("#fvaluep").html(arr.fvaluep);
				$("#trange").html(arr.trange);
				$("#tchange").html(arr.tchange);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#volatility").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}