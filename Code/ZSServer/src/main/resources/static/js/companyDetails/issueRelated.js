$(function(){
	showIssueRelated();
});
function showIssueRelated(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":500}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getIssueRelated.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var data = res.data.data;
				$("#issueDate").html(data.issueDate);
				$("#listingDate").html(data.listingDate);
				$("#issueNumber").html(data.issueNumber);
				$("#issuePrice").html(data.issuePrice);
				$("#ipoRatio").html(data.ipoRatio);
				$("#expectedToRaise").html(data.expectedToRaise);
				$("#openingPrice").html(data.openingPrice);
				$("#rate").html(data.rate);
				$("#actualRaised").html(data.actualRaised);
				$("#mainUnderwriter").html(data.mainUnderwriter.name);
				$("#listingSponsor").html(data.listingSponsor.name);
				$("#history").html(data.history);
			}else{
				new Alert({
	                flag : true,
	                text : '暂无数据',
	                timer : 2000
	            }).show();
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}