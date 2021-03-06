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
	$("#zsqb").removeClass("active");
	$("#jzzs").addClass("active");
	$(".left-nav").find("ul>li").removeClass("active");
	companyName = GetQueryString("companyName");
	$(".btn-primary").on('click',function () {
        if($(this).text() == '下载PDF'){
            downLoad('pdf');
        }else if($(this).text() == '下载WORD'){
            downLoad('word');
        }
    })
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
                if(resData.error_code == 0 || resData.error_code == null){
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
                    if(resData.phoneNumber == null){
                        resData.phoneNumber = '---';
                    }
                    if(resData.websiteList == null){
                        resData.websiteList = '---';
                    }
                    if(resData.percentileScore == null){
                        resData.percentileScore = '82';
                    }else{
                        resData.percentileScore = Math.round(resData.percentileScore/100)
                    }
                    $("#baseName").text(resData.name);
                    $("#baseScore").text("企业匹配值："+resData.percentileScore+"分");
                    $("#baseTel").text(resData.phoneNumber);
//				$(".inline-lyt").find(".lyt-rt").eq(2).text(resData.phoneNumber);
                    $("#baseWeb").text(resData.websiteList);
                    $("#baseAddr").text(resData.regLocation);
                    $("#legalPerson").text(resData.legalPersonName);
                    $("#regCapital").text(resData.regCapital);
                    $("#estiblishTime").text(getFormatDate(new Date(resData.estiblishTime)));
                    $("#regStatus").text(resData.regStatus);
                    $("#regNumber").text(resData.regNumber);
                    $("#industry").text(resData.industry);
                    $("#orgNumber").text(resData.orgNumber);
                    $("#toTime").text(getFormatDate(new Date(resData.toTime)));
                    $("#creditCode").text(resData.creditCode);
                    $("#approvedTime").text(getFormatDate(new Date(resData.approvedTime)));
                    $("#companyOrgType").text(resData.companyOrgType);
                    $("#regLocation").text(resData.regLocation);
                    $("#regInstitute").text(resData.regInstitute);
                    $("#businessScope").text(resData.businessScope);
				}else{
                    new Alert({flag : true,text : res.message,timer : 2000}).show();
				}
			}
		}
	});
}
function attationCompany(companyId,flag){
	if(flag)
		new Alert({flag : true,text : '关注成功',timer : 2000}).show();
	else{
		new Alert({flag : true,text : '取消关注成功',timer : 2000}).show();
	}
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
function goBack(){
	window.history.back(-1);
}
var arr;
var count = 1;
function downLoad(type) {
    var methods = new Array();
    $(".method").each(function () {
        if($(this)[0].checked)
            methods.push($(this).val());
    })
    var form = $("<form>");   //定义一个form表单
    form.attr('style', 'display:none');   //在form表单中添加查询参数
    form.attr('target', '');
    form.attr('method', 'post');
    form.attr('action', '/apis/openeyes/downLoad.json');
    var input1 = $('<input>');
    input1.attr('type', 'hidden');
    input1.attr('name', 'methods');
    input1.attr('value', methods);
    var input2 = $('<input>');
    input2.attr('type', 'hidden');
    input2.attr('name', 'cname');
    input2.attr('value', companyName);
    var input3 = $('<input>');
    input3.attr('type', 'hidden');
    input3.attr('name', 'exportType');
    input3.attr('value', type);
    $('body').append(form);
    form.append(input1);
    form.append(input2);
    form.append(input3);
    form.submit();
    form.remove();

}



