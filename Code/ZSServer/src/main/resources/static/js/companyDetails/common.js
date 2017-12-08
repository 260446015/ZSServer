Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	}
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}
/**
 * date 为long类型 pattern 为格式化参数
 */
function getFormatDate(date, pattern) {
	if (date == undefined) {
		date = new Date();
	}
	if (pattern == undefined) {
		pattern = "yyyy-MM-dd";
	}
	return date.format(pattern);
}
var companyName;
var resData;
$(function(){
	companyName = GetQueryString("companyName");
	showCompanyDetail();
});
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
function showCompanyDetail(){
	$.ajax({
		type:'get',
		url:'/apis/openeyes/getBaseInfo.json?name='+companyName,
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
}

