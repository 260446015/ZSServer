$(function() {
	showCompanyDetail();
});
function showCompanyDetail(){
	$.ajax({
		type:'get',
		url:'/apis/openeyes/getBaseInfo.json?name='+companyName,
		success:function(res){
			if(res.success){
				resData = res.data.result;
				var value;
				var flag;
				if(resData.isAttation){
					value = "取消关注";
					flag = false;
				}else{
					value = "添加关注";
					flag = true;
				}
				$("#attationCompany").html(value);
				$("#attationCompany").on("click",function(){
					attationCompany(resData.companyId,flag);
				});
				$("#legalPerson").text(resData.legalPersonName);
				$("#regCapital").text(resData.regCapital);
				$("#estiblishTime").text(getFormatDate(new Date(resData.estiblishTime)));
				$("#regStatus").text(resData.regStatus);
				$("#regNumber").text(resData.regNumber);
				$("#industry").text(resData.industry);
				$("#orgNumber").text(resData.orgNumber);
				$("#toTime").text(getFormatDate(new Date(resData.toTime)));
				$("#creditCode").text(resData.creditCode);
				$("#approvedTime").text(resData.approvedTime);
				$("#companyOrgType").text(resData.companyOrgType);
				$("#regLocation").text(resData.regLocation);
				$("#regInstitute").text(resData.regInstitute);
				$("#businessScope").text(resData.businessScope);
			}
		}
	});
}
