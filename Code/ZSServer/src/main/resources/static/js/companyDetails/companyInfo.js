$(function(){
	showBids();
});
function showBids(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200,"id":121639504}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		async:false,
		contentType:'application/json',
		url:'/apis/openeyes/getCompanyInfo.json',
		success:function(res){
			if(res.success){
                if(res.data.error_code == 0 || res.data.error_code == null) {
                    var data = res.data.result;
                    $("#engName").html(data.engName);
                    $("#usedName").html(data.usedName);
                    $("#industry").html(data.industry);
                    $("#mainBusiness").html(data.mainBusiness);
                    $("#chairman").html(data.chairman.name);
                    $("#secretaries").html(data.secretaries.name);
                    $("#legal").html(data.legal.name);
                    $("#generalManager").html(data.generalManager.name);
                    $("#registeredCapital").html(data.registeredCapital);
                    $("#employeesNum").html(data.employeesNum);
                    $("#controllingShareholder").html(data.controllingShareholder);
                    $("#actualController").html(data.actualController);
                    $("#finalController").html(data.finalController);
                }else{
                    var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
                    $("#body").html(html);
                    window.setTimeout(goBack, 2000);
				}
			}
		}
	});
}