$(function(){
	showBids();
});
function showBids(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":500}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getCompanyInfo.json',
		success:function(res){
			if(res.success){
				var data = res.data.data;
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
				new Alert({
                    flag : true,
                    text : '暂无数据',
                    timer : 2000
                }).show();
				goBack();
			}
		}
	});
}