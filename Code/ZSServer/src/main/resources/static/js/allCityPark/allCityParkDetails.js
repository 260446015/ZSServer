/**
 * Created by zhangxin on 2017/11/22.
 */
$(function() {
	AMapUI.setDomLibrary($);

	// 加载BasicControl，loadUI的路径参数为模块名中 'ui/' 之后的部分
	AMapUI.loadUI([ 'control/BasicControl' ], function(BasicControl) {

		var map = new AMap.Map('map', {
			// mapStyle: 'amap://styles/e15ea366314a2314abda4c7761ee02a6',
			resizeEnable : false,
			zoom : 13,
		});
		// 缩放控件
		map.addControl(new BasicControl.Zoom({
			position : 'rb', // left top，左上角
			showZoomNum : false
		// 显示zoom值
		}));
		/* 雷达转动 */
		if ($(window).scrollTop() > 0) {
			$(window).scrollTop(0);
		}
		rotates();
		setTimeout(function() {
			unrotates();
		}, 2000);
	});
	function rotates() {
		$("body,.page-content").addClass("modal-open");
		$(".search-circle-box").addClass("open").children(".search-circle-img").addClass("rotates").css({
			"margin-left" : function() {
				return -$(this).width() / 2
			},
			"margin-top" : function() {
				return -$(this).height() / 2
			}
		});
	}
	function unrotates() {
		$("body,.page-content").removeClass("modal-open");
		$(".search-circle-box").removeClass("open").children(".search-circle-img").removeClass("rotates").css({
			"margin-left" : function() {
				return -$(this).width() / 2
			},
			"margin-top" : function() {
				return -$(this).height() / 2
			}
		});
		$('html,body').animate({
			scrollTop : $(".right-content .container").offset().top - 50
		}, 300);
	}
	showGardenInfo(park);
	showCompanyList(industry, registTime, registCapital, invest, park, pageNumber, pageSize);
});
function sendIndustry(data) {// 传递产业
	industry = data;
	showCompanyList(industry, registTime, registCapital, invest, 0, 10);
}
function sendRegister(data) {// 传递注册时间
	registTime = data;
	showCompanyList(industry, registTime, registCapital, invest, park, 0, 10);
}
function sendCapital(data) {// 传递注册资本
	registCapital = data;
	showCompanyList(industry, registTime, registCapital, invest, park, 0, 10);
}
function sendInvest(data) {// 传递融资阶段
	invest = data;
	showCompanyList(industry, registTime, registCapital, invest, park, 0, 10);
}
var industry = '全部';
var registTime = '全部';
var registCapital = '全部';
var invest = '全部';
var pageNumber = 0;
var pageSize = 10;
var park = GetQueryString('name');
function showCompanyList(a, b, c, d, e, f, g) {// a-产业,b-注册时间,c-注册资本,d-融资阶段,e-查询的园区名称,f页码数,g每页总大小
	var req = {
		"pageNumber" : f,
		"pageSize" : g,
		"msg" : [ a, b, c, d, e ]
	};
	$.ajax({
		type : 'post',
		contentType : 'application/json',
		url : '/apis/company/findCompanyList.json',
		data : JSON.stringify(req),
		success : function(res) {
			if (res.success) {
				console.log(res.data);
				console.log(park);
			}
		}
	});
}
function GetQueryString(key) {//获取地址栏中的name
	// 获取参数
	var url = window.location.search;
	// 正则筛选地址栏
	var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
	// 匹配目标参数
	var result = url.substr(1).match(reg);
	// 返回参数值
	return result ? decodeURIComponent(result[2]) : null;
}
function showGardenInfo(data){
	$.ajax({
		type:'get',
		url:'/apis/area/findGardenInfo.json?gardenName='+data,
		success:function(res){
			if(res.success){
				$("#gardenName").html(res.data.gardenName);
				$("#gardenAddress").html(res.data.address);
				$("#registTime").html(res.data.establishDate);
				$("#gardenIndustry").html(res.data.industryType);
				$("#gardenSquare").html(res.data.gardenSquare + '平方千米');
			}
		}
	});
}
