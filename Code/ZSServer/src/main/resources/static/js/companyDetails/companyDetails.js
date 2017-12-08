/**
 * Created by zhangxin on 2017/11/30.
 */
/*$(function() {
	companyName = GetQueryString("companyName");
	showCompanyDetail();
});
function showCompanyDetail(){
	$.ajax({
		type:'get',
		url:'/apis/openeyes/getBaseInfo.json?name=北京百度网讯科技有限公司',
		success:function(res){
			if(res.success){
				resData = res.data.result;
				$(".datails-title>span").text(resData.name);
				$(".score-title").text("企业匹配值："+resData.categoryScore+"分");
				$(".inline-lyt").find(".lyt-rt").eq(0).text(resData.phoneNumber);
//				$(".inline-lyt").find(".lyt-rt").eq(2).text(resData.phoneNumber);
				$(".inline-lyt").find(".lyt-rt").eq(2).text(resData.websiteList);
				$(".inline-lyt").find(".lyt-rt").eq(3).text(resData.regLocation);
				$(".inline-lyt").find(".lyt-rt").eq(4).text(resData.businessScope);
			}
		}
	});
}
function attationCompany(companyId,flag){
	$.ajax({
		url:'/apis/company/attationCompany.json?companyId='+companyId+'&flag='+flag,
		type:'get',
		success:function(res){
			if(res.success){
				showCompanyDetail();
			}
		}
	});
}*/