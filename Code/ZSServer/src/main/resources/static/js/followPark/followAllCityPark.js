/**
 * Created by zhangxin on 2017/11/22.
 */
var address = new Array();
var province;
$(function() {
	$("#gardenMap").addClass("active");
	$("#all").addClass("active");
	AMapUI.setDomLibrary($);
	showGardenInfo(park);
	showCompanyList(industry, registTime, registCapital, invest, park, pageNumber, pageSize);
	// 加载BasicControl，loadUI的路径参数为模块名中 'ui/' 之后的部分
	AMapUI.loadUI([ 'control/BasicControl' ], function(BasicControl) {
		var map = new AMap.Map('map', {
			// mapStyle: 'amap://styles/e15ea366314a2314abda4c7761ee02a6',
			resizeEnable : false,
			center : [ 117.125867, 36.680942 ],
			zoom : 13,
		});
		map.setCity(province);
		var geocoder = new AMap.Geocoder({
			radius : 1000
		// 范围，默认：500
		});
		for (var j = 0; j < address.length; j++) {
			(function(j){
				var addr = address[j].address;
				var point;
				var companyName = address[j].companyName;
				// 地理编码,返回地理编码结果
				geocoder.getLocation(addr, function(status, result) {
					if (status === 'complete' && result.info === 'OK') {
						var marker = new AMap.Marker({
							map : map,
							icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
							position : [ result.geocodes[0].location.lng, result.geocodes[0].location.lat ]
						});
						marker.on('click',function(){
							 // 自定义点标记内容
					        var markerContent = document.createElement("div");

					        // 点标记中的图标
					        var markerImg = document.createElement("img");
					        markerImg.className = "markerlnglat";
					        markerImg.src = "http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png";
					        markerContent.appendChild(markerImg);

					        // 点标记中的文本
					        var markerSpan = document.createElement("span");
					        markerSpan.className = 'marker';
					        markerSpan.style.color = 'blue';
					        markerSpan.innerHTML = companyName;
					        markerContent.appendChild(markerSpan);

					        marker.setContent(markerContent); //更新点标记内容
						});
					}
				});
			})(j);
		}
		setTimeout(function() {
			unrotates();
		}, 2000);
	});
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
var options = {
	"id" : "page",// 显示页码的元素
	"data" : null,// 显示数据
	"maxshowpageitem" : 5,// 最多显示的页码个数
	"pagelistcount" : 10,// 每页显示数据个数
	"callBack" : function() {
	}
};
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
		async : false,
		data : JSON.stringify(req),
		success : function(res) {
			if (res.success) {
				if (res.data != null) {
					var arr = res.data.content;
					var html = '';
					address = arr;
					for (var i = 0; i < arr.length; i++) {
						html += '<div class="col-md-12 border-bottom"><a class="scatter-blocks no-border" href="./allCityParkDetails.html">' + '<span class="scatter-title">' + arr[i].companyName
								+ '</span><span class="pull-right numbers">' + '<span class="glyphicon glyphicon-map-marker"></span>' + arr[i].address + '</span></a><p class="net-address mb20">'
								+ '<span class="mr15">法定代表人：' + arr[i].boss + '</span><span class="mr15">注册资本：' + arr[i].registerCapital + '</span><span class="mr15">注册时间：' + arr[i].registerDate
								+ '</span></p></div>'
					}
					$("#companyList").html(html);
					if (res.data.totalPages > 1) {
						page.init(res.data.totalElements, res.data.number + 1, options);
						$("#" + page.pageId + ">li[class='pageItem']").on("click", function() {
							showCompanyList(industry, registTime, registCapital, invest, park, $(this).attr("page-data") - 1, pageSize);
						});
					} else {
						$('#page').html("");
					}
				} else {
					new Alert({
						flag : true,
						text : '暂无数据',
						timer : 2000
					}).show();
				}
			}
		}
	});
}
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
function showGardenInfo(data) {
	$.ajax({
		type : 'get',
		async : false,
		url : '/apis/area/findGardenInfo.json?gardenName=' + data,
		success : function(res) {
			if (res.success) {
				province = res.data.province;
				if (res.data.flag)
					$("#attation").html('取消关注');
				else
					$("#attation").html('关注');
				$("#analysis").attr('href','/apis/analysis/analysisOutputValue.html?park='+data);
				$("#attation").before('<input type="hidden" value="' + res.data.id + '"/>');
				$("#gardenName").html(res.data.gardenName);
				$("#gardenAddress").html(res.data.address);
				$("#registTime").html(res.data.establishDate);
				$("#gardenIndustry").html(res.data.industryType);
				$("#gardenSquare").html(res.data.gardenSquare + '平方千米');
			}
		}
	});
}
function attation(event) {
	var value = $(event).html();
	var gardenId = $(event).prev().val();
	var flag;
	if (value == '关注') {
		flag = true;
	} else {
		flag = false;
	}
	$.ajax({
		url : '/apis/area/attentionGarden.json?gardenId=' + gardenId + '&flag=' + flag,
		type : 'get',
		success : function(res) {
			if (res.success) {
				showGardenInfo(park);
			}
		}
	});
}
