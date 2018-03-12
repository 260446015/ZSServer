$(function(){
	showIssueRelated();
});
function showIssueRelated(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		async:false,
		contentType:'application/json',
		url:'/apis/openeyes/getIssueRelated.json',
		success:function(res){
			if(res.success){
                if(res.data.error_code == 0 || res.data.error_code == null) {
                    var data = res.data.result;
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
                    var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
                    $("#issueRelated").html(html);
                    window.setTimeout(goBack, 2000);
				}
			}
		}
	});
}