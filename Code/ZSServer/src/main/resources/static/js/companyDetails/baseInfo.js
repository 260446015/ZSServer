$(function() {
	showBaseInfo();
});
function showBaseInfo() {
	$.ajax({
		type : 'get',
		url : '/apis/openeyes/getBaseInfo.json?name=北京百度网讯科技有限公司',
		success : function(res) {
			if (res.success) {
				var resData = res.data.result;
				console.log(resData);
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
