/**
 * Created by zhangxin on 2017/11/30.
 */
var pieOption = {
	tooltip : {
		trigger : 'item',
		formatter : "{a} <br/>{b}: {c} ({d}%)"
	},
	series : [ {
		name : '访问来源',
		type : 'pie',
		radius : [ '50%', '70%' ],
		data : [ {
			value : 335,
			name : '直接访问'
		}, {
			value : 310,
			name : '邮件营销'
		}, {
			value : 234,
			name : '联盟广告'
		}, {
			value : 135,
			name : '视频广告'
		}, {
			value : 1548,
			name : '搜索引擎'
		} ]
	} ]
};
var barOption = {
	tooltip : {
		trigger : 'axis',
		axisPointer : { // 坐标轴指示器，坐标轴触发有效
			type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
		}
	},
	grid : {
		left : '3%',
		right : '4%',
		bottom : '3%',
		containLabel : true
	},
	xAxis : [ {
		type : 'category',
		data : [ 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun' ],
		axisTick : {
			alignWithLabel : true
		}
	} ],
	yAxis : [ {
		type : 'value'
	} ],
	series : [ {
		name : '直接访问',
		type : 'bar',
		barWidth : '30',
		data : [ 10, 52, 200, 334, 390, 330, 220 ]
	} ]
};
var lineOption = {
	color : "#00a2ff",
	tooltip : {
		trigger : 'axis',
		axisPointer : {
			type : 'cross',
			label : {
				backgroundColor : '#6a7985'
			}
		}
	},
	grid : {
		left : '3%',
		right : '4%',
		bottom : '3%',
		containLabel : true
	},
	xAxis : [ {
		type : 'category',
		boundaryGap : false,
		data : [ '2011', '2012', '2013', '2014', '2015', '2016', '2017' ],
		splitLine : {
			show : false
		}
	} ],
	yAxis : [ {
		type : 'value'
	} ],
	series : [ {
		name : '',
		type : 'line',
		areaStyle : {
			normal : {
				color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
					offset : 0,
					color : '#018dde'
				}, {
					offset : 1,
					color : '#07263b'
				} ])
			}
		},
		data : [ 120, 132, 101, 134, 90, 230, 210 ]
	} ]
};
var pieCharts = echarts.init(document.getElementById("charts1"), "customed");
pieCharts.setOption(pieOption);
var barCharts = echarts.init(document.getElementById("charts2"), "customed");
barCharts.setOption(barOption);
var lineCharts = echarts.init(document.getElementById("charts3"), "customed");
lineCharts.setOption(lineOption);
$(function() {
//	companyName = GetQueryString("companyName");
	showBaseInfo(companyName);
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
function showBaseInfo(companyName){
	$.ajax({
		type:'get',
		url:'/apis/openeyes/getBaseInfo.json?name=北京百度网讯科技有限公司',
		success:function(res){
			if(res.success){
				var data = res.data.result;
				console.log(data);
				$(".datails-title>span").text(data.name);
				$(".score-title").text("企业匹配值："+data.categoryScore+"分");
				$(".inline-lyt").find(".lyt-rt").eq(0).text(data.phoneNumber);
//				$(".inline-lyt").find(".lyt-rt").eq(2).text(data.phoneNumber);
				$(".inline-lyt").find(".lyt-rt").eq(2).text(data.websiteList);
				$(".inline-lyt").find(".lyt-rt").eq(3).text(data.regLocation);
				$(".inline-lyt").find(".lyt-rt").eq(4).text(data.businessScope);
			}
		}
	});
}