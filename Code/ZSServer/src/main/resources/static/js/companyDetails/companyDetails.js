/**
 * Created by zhangxin on 2017/11/30.
 */
$(function() {
//	companyName = GetQueryString("companyName");
	showCompanyDetail();
});
var companyName;
function GetQueryString(key) {// 获取地址栏中的name
	// 获取参数
	var url = window.location.search;
	// 正则筛选地址栏
	var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
	// 匹配目标参数
	var result = url.substr(1).match(reg);
	// 返回参数值
	return result ? decodeURIComponent(result[2]) : null;
}
var resData;
function showCompanyDetail(){
	$.ajax({
		type:'get',
		url:'/apis/openeyes/getBaseInfo.json?name=北京百度网讯科技有限公司',
		success:function(res){
			if(res.success){
				resData = res.data.result;
				console.log(resData);
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