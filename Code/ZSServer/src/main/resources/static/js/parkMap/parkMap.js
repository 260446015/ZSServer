/**
 * Created by zhangxin on 2017/11/21.
 */
$(function(){
	$("#gardenMap").addClass("active");
	$("#indus").removeClass("active");
	$("#report").removeClass("active");
	barCharts = echarts.init(document.getElementById('barCharts'),"customed");
	barCharts.on('click',function(param){
		province = param.name;
		showGardenGdpPiechart(province);
	});
	scatter = echarts.init(document.getElementById("scatter"),"customed");
	scatter.on('click',function(param){
		industryType = param.name;
		showGardenIndustryCount(industryType);
	});
	showGardenCondition("","condition");
	showGardenIndustryCount(industryType);
	showGardenGdpPiechart(province);
});
var data = [
    {name: '上海', value: 200},
    {name: '天津', value: 200},
    {name: '太原', value: 50},
    {name: '清远', value: 50},
    {name: '寿光', value: 49},
    {name: '盘锦', value: 50},
    {name: '长治', value: 50},
    {name: '深圳', value: 50},
    {name: '珠海', value: 50},
    {name: '大连', value: 50},
    {name: '临汾', value: 50},
    {name: '吴江', value: 50},
    {name: '石嘴山', value: 50},
    {name: '沈阳', value: 50},
    {name: '苏州', value: 50},
    {name: '茂名', value: 50},
    {name: '嘉兴', value: 50},
    {name: '长春', value: 50},
    {name: '胶州', value: 50},
    {name: '银川', value: 50},
    {name: '张家港', value: 50},
    {name: '三门峡', value: 50},
    {name: '西安', value: 50},
    {name: '金坛', value: 50},
    {name: '东营', value: 50},
    {name: '牡丹江', value: 50},
    {name: '遵义', value: 50},
    {name: '绍兴', value: 50},
    {name: '扬州', value: 50},
    {name: '常州', value: 50},
    {name: '潍坊', value: 50},
    {name: '重庆', value: 200},
    {name: '台州', value: 50},
    {name: '渭南', value: 50},
    {name: '焦作', value: 50},
    {name: '句容', value: 50},
    {name: '徐州', value: 50},
    {name: '衡水', value: 50},
    {name: '包头', value: 50},
    {name: '绵阳', value: 50},
    {name: '乌鲁木齐', value: 50},
    {name: '兰州', value: 50},
    {name: '沧州', value: 50},
    {name: '临沂', value: 50},
    {name: '宜昌', value: 50},
    {name: '义乌', value: 50},
    {name: '丽水', value: 50},
    {name: '洛阳', value: 50},
    {name: '秦皇岛', value: 50},
    {name: '株洲', value: 50},
    {name: '石家庄', value: 50},
    {name: '莱芜', value: 50},
    {name: '常德', value: 50},
    {name: '保定', value: 50},
    {name: '湘潭', value: 50},
    {name: '金华', value: 50},
    {name: '岳阳', value: 50},
    {name: '长沙', value: 50},
    {name: '衢州', value: 50},
    {name: '廊坊', value: 50},
    {name: '菏泽', value: 50},
    {name: '合肥', value: 50},
    {name: '武汉', value: 50},
    {name: '北京', value: 200}
];

var geoCoordMap = {
    '上海':[121.339766,31.196099],
    '天津':[117.210061,39.135884],
    '太原':[112.53,37.87],
    '清远':[113.01,23.7],
    '中山':[113.38,22.52],
    '昆明':[102.73,25.04],
    '寿光':[118.73,36.86],
    '盘锦':[122.070714,41.119997],
    '长治':[113.08,36.18],
    '深圳':[114.07,22.62],
    '珠海':[113.52,22.3],
    '大连':[121.62,38.92],
    '临汾':[111.5,36.08],
    '吴江':[120.63,31.16],
    '石嘴山':[106.39,39.04],
    '沈阳':[123.38,41.8],
    '苏州':[120.62,31.32],
    '茂名':[110.88,21.68],
    '嘉兴':[120.76,30.77],
    '长春':[125.35,43.88],
    '胶州':[120.03336,36.264622],
    '银川':[106.27,38.47],
    '张家港':[120.555821,31.875428],
    '三门峡':[111.19,34.76],
    '西安':[108.95,34.27],
    '金坛':[119.56,31.74],
    '东营':[118.49,37.46],
    '牡丹江':[129.58,44.6],
    '遵义':[106.9,27.7],
    '绍兴':[120.58,30.01],
    '扬州':[119.42,32.39],
    '常州':[119.95,31.79],
    '潍坊':[119.1,36.62],
    '重庆':[106.54,29.59],
    '渭南':[109.5,34.52],
    '马鞍山':[118.48,31.56],
    '宝鸡':[107.15,34.38],
    '句容':[119.16,31.95],
    '北京':[116.46,39.92],
    '徐州':[117.2,34.26],
    '衡水':[115.72,37.72],
    '包头':[110,40.58],
    '绵阳':[104.73,31.48],
    '乌鲁木齐':[87.68,43.77],
    '兰州':[103.73,36.03],
    '沧州':[116.83,38.33],
    '临沂':[118.35,35.05],
    '宜昌':[111.3,30.7],
    '义乌':[120.06,29.32],
    '丽水':[119.92,28.45],
    '洛阳':[112.44,34.7],
    '秦皇岛':[119.57,39.95],
    '株洲':[113.16,27.83],
    '石家庄':[114.48,38.03],
    '莱芜':[117.67,36.19],
    '常德':[111.69,29.05],
    '保定':[115.48,38.85],
    '湘潭':[112.91,27.87],
    '金华':[119.64,29.12],
    '岳阳':[113.09,29.37],
    '长沙':[113,28.21],
    '衢州':[118.88,28.97],
    '廊坊':[116.7,39.53],
    '菏泽':[115.480656,35.23375],
    '合肥':[117.27,31.86],
    '武汉':[114.31,30.52]
};

var convertData = function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var geoCoord = geoCoordMap[data[i].name];
        if (geoCoord) {
            res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value)
            });
        }
    }
    return res;
};

var chinaOption = {
    geo: {
        map: 'china',
        label: {
            emphasis: {
                show: false
            }
        },
        itemStyle: {
            normal: {
                areaColor: '#000622',
                borderColor: '#13acf3'
            },
            emphasis: {
                areaColor: '#2a333d'
            }
        }
    },
    series : [
        {
            type: 'scatter',
            coordinateSystem: 'geo',
            data: convertData(data),
            symbolSize: function (val) {
                return val[2] / 10;
            },
            label: {
                normal: {
                    show: false
                }
            },
            itemStyle: {
                normal: {
                    color: '#c5a425'
                }
            }
        },
        {
            name: 'Top 5',
            type: 'effectScatter',
            coordinateSystem: 'geo',
            data: convertData(data.sort(function (a, b) {
                return b.value - a.value;
            }).slice(0, 4)),
            symbolSize: function (val) {
                return val[2] / 10;
            },
            showEffectOn: 'emphasis',
            rippleEffect: {
                brushType: 'stroke'
            },
            hoverAnimation: true,
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    color: '#04fee4',
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            zlevel: 1
        }
    ]
};

var parkMap = echarts.init(document.getElementById('parkMap'),"customed");
parkMap.setOption(chinaOption);

var area = "北京";
var year = 2015;
var flag = false;
parkMap.on("click",function (e) {
    if(e.componentType=="series"){
    	area = e.data.name;
    	var value = e.data.value[2];
    	if(value != 200)
    		return;
    	$("#myModalLabel").html(area);
    	showGardenPolicy(area);
    	showGardenList(area);
        $("#myModal").modal("show");
    }else if(e.componentType=="geo"){
    	area = e.name;
    	$("#myModalLabel").html(area);
    	showGardenPolicy(area);
    	showGardenList(area);
    	if(flag)
    		$("#myModal").modal("show");
    	else
    		new Alert({flag:false,text:'暂无数据',timer:2000}).show();
    }
    $("#morePolicy").on('click',function(){
		$(this).attr('href','/apis/area/garden/findMorePolicy.html?area='+area);
	});
});

var barOption = {
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['北京', '上海', '深圳', '广州', '天津', '重庆', '山东'],
            axisTick: {
                alignWithLabel: true
            },
            splitLine:{
                show:false
            }
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    dataZoom: [
               {
                   show: false,
                   end: 50,
                   left:"10%",
                   start: 0,
               },
               {
                   type: 'inside',
                   start: 50,
                   end: 100
               },
               {
                   show: false,
                   yAxisIndex: 0,
                   filterMode: 'empty',
                   width: 30,
                   height: '80%',
                   showDataShadow: false,
                   left: '10%',
               }
           ],
    series : [
        {
            name:'园区数量',
            type:'bar',
            barWidth: '20%',
            data:[10, 52, 200, 334, 390, 330, 220]
        }
    ]
};
var barCharts = echarts.init(document.getElementById('barCharts'),"customed");
barCharts.setOption(barOption);

var  label = {
    normal: {
        show: true,
        textStyle: {
            fontSize: '16',
            color: '#ffffff'
        },
        position: 'inside',
        formatter: function(param) {
            return param.data.name;
        }
    }
};
function initEcharts(pieChartName){
	data1 = [
	         {
	             value: [50,50], symbolSize: 160, name: pieChartName[0], label:label
	         },
	         {
	             value: [10,60], symbolSize: 115, name: pieChartName[1], label:label
	         },
	         {
	             value: [24,18], symbolSize: 85, name: pieChartName[2], label:label
	         },
	         {
	             value: [90,30], symbolSize: 113, name: pieChartName[3], label:label
	         },
	         {
	             value: [85,82], symbolSize: 106, name: pieChartName[4], label:label
	         },
	         {
	             value: [30,92], symbolSize: 90, name: pieChartName[5], label: label
	         }
	     ];
	return data1;
}
var data1 = [
	 {
		 value: [90,30], symbolSize: 140, name: '环保产业', label:label
	 },
	 {
		 value:  [50, 50], symbolSize:90, name: '融合', label:label
	 },
    {
        value: [18,90], symbolSize: 79, name: '新一代信息技术', label:label
    },
    {
        value: [10,20], symbolSize: 76, name: '新能源', label:label
    },
    {
        value: [57,11], symbolSize: 69, name: '生物产业', label:label
    },
    {
        value: [85,82], symbolSize: 79, name: '新材料', label:label
    },
    {
        value: [65,90], symbolSize: 63, name: '科技园', label: label
    },
    {
        value: [42,100], symbolSize: 74, name: '行业', label: label
    },
    {
        value: [18,55], symbolSize: 74, name: '创新', label:label
    }
];

var option = {
    xAxis: {
        show: false,
        type: 'value',
        splitLine: {
            lineStyle: {
                type: 'dashed'
            }
        }
    },
    yAxis: {
        show: false,
        type: 'value',
        splitLine: {
            lineStyle: {
                type: 'dashed'
            }
        },
    },

    series: [{
        name: '热词',
        type: 'scatter',
        showEffectOn: 'emphasis',
        itemStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#00a5fb'},
                        {offset: 0.5, color: '#00caf2'},
                        {offset: 1, color: '#00f0e8'}
                    ]
                ),
            }
        },
        data: data1
    }]
};

/*modal窗口里的图表*/
var modalOption1 = {
    tooltip : {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ['2011','2012','2013','2014','2015','2016','2017'],
            splitLine:{
                show:false
            }
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'',
            type:'line',
            stack: '总量',
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#018dde'
                    }, {
                        offset: 1,
                        color: '#07263b'
                    }])
                }
            },
            data:[120, 132, 101, 134, 90, 230, 210]
        }
    ]
};

var modalOption2 = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    series: [
        {
            name:'产业',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: true
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '16',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: true
                }
            },
            data:[
                {value:335, name:'生物技术'},
                {value:310, name:'大数据'},
                {value:234, name:'新一代信息技术'},
                {value:135, name:'AI'},
                {value:1548, name:'VR'}
            ]
        }
    ]
};

var date = new Date().getFullYear(); 
var dates = [date-6,date-5,date-4,date-3,date-2,date-1,date];
$("#myModal").on("shown.bs.modal",function () {
	modalCharts1 = echarts.init(document.getElementById("charts_1"),"customed");
	modalCharts1.on('click',function(param){
    	showGardenModelIndustry(area,param.name);
    });
	modalCharts2 = echarts.init(document.getElementById("charts_2"),"customed");
    modalCharts2.on('click',function(param){
    	showDifYearGdp(param.name,dates,area);
    });
	showGardenModelIndustry(area,year);
	showDifYearGdp(industry,dates,area);
	showFindMore(area);
	

});

function showGardenCondition(area,target){
	var url = "";
	var req = {"pageNumber":0,"pageSize":4,"province":area};
	$.ajax({
		type:"post",
		url:"/apis/area/findGardensCondition.json",
		data:JSON.stringify(req),
		contentType:"application/json",
		success:function(res){
			if(res.success){
				var html = "";
				var arr = res.data.content;
				if(arr.length != 0){
					for(var i=0;i<arr.length;i++){
						html += '<div class="col-md-12 border-bottom">' +
									'<a class="scatter-blocks no-border" href="/summit/getEssayDetails.json?essayId='+arr[i].id+'">' +
										'<span class="scatter-title">'+arr[i].title+'</span></a>' +
										'<p class="scatter-content">'+arr[i].summary +'</p>' + 
										'<p class="scatter-lib">' +
											'<span>'+arr[i].park+'</span>' +
	                                		'<span>'+arr[i].publishTime+'</span></p></div>';
					}
					$("#"+target).html(html);
				}else{
					$("#gardenCondition").hide();
				}
			}else{
				$("#gardenCondition").hide();
			}
		}
	});
}
function showGardenPolicy(area){
	var req = {"pageNumber":0,"pageSize":4,"province":area};
	$.ajax({
		type:'post',
		url:'/apis/area/getGardenPolicy.json',
		data:JSON.stringify(req),
		contentType:'application/json',
		success:function(res){
			if(res.success){
				var html = '';
				var arr = res.data.content;
				if(arr.length != 0){
					for(var i=0;i<arr.length;i++){
						html += '<div class="col-md-12 border-bottom">' +
									'<a class="scatter-blocks no-border" href="/summit/getEssayDetails.json?essayId='+arr[i].id+'">' +
										'<span class="scatter-title">'+arr[i].title+'</span></a>' +
										'<p class="scatter-content">'+arr[i].summary +'</p>' + 
										'<p class="scatter-lib">' +
											'<span>'+arr[i].area+'</span>' +
	                                		'<span>'+arr[i].publishTime+'</span></p></div>';
					}
					$("#condition2").html(html);
					if($("#condition2").siblings().length == 0){
						$("#condition2").after('<div class="modal-footer text-center"><a href="javascript:void(0);" class="btn btn-link" id="morePolicy">查看更多</a></div>')
						$("#morePolicy").on('click',function(){
							$(this).attr('href','/apis/area/garden/findMorePolicy.html?area='+area);
						});
					}
				}else{
					html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
					$("#condition2").html(html);
					$("#condition2").siblings().remove();
				}
			}
		}
	});
}
function showGardenList(area){
	var req = {"pageNumber":0,"pageSize":5,msg:['全部',area,'园区占地','desc']};
	$.ajax({
		type:'post',
		url:'/apis/area/findGardensList.json',
		data:JSON.stringify(req),
		contentType:'application/json',
		async:false,
		success:function(res){
			if(res.success){
				var arr = res.data.content;
				if(arr.length == 0){
					flag = false;
				}else{
					flag = true;
				}
				var html = "";
				for (var i = 0; i < arr.length; i++) {
					html += '<li><a href="/apis/area/garden/allCityParkDetails.html?name='+arr[i].gardenName+'" class="circle-img-box">' +
									'<img src="'+arr[i].gardenPicture+'" alt="" /><p class="park-name">'+arr[i].gardenName+'</p>'+
							'</a></li>';
				}
				$("#gardenList").html(html);
			}
		}
	});
}
var dateValue = new Array();//创建一个全局变量用来接收返回的gdp值
var dateName = new Array();//创建一个全局变量用来接收返回的年份
var modalCharts1;
function showDifYearGdp(a,b,c){//model中展示不同年份某一产业的gdp  //model中展示折线图
	var req = {"industry":a,"year":b,"province":area};
	
	$.ajax({
		type:'post',
		url:'/apis/area/findGardenGdp.json',
		contentType:'application/json',
		data:JSON.stringify(req),
		async:false,
		success:function(res){
			if(res.success){
				var arr = res.data;
				dateValue = [];
				dateName = [];
				for(var i=0;i<arr.length;i++){
					dateValue.push(arr[i].gdp);
					dateName.push(arr[i].year);
				}
				modalOption1.xAxis[0].data = dateName;
				modalOption1.series[0].data = dateValue;
			    
			    modalCharts1.setOption(modalOption1,true);
			}
		}
	});
}
var industry = '不限';//定义一个全局变量用来接收返回回来产值较多的产业
var modalCharts2;
function showGardenModelIndustry(area,year){
	var seriesData = new Array();
	$.ajax({
		type:'get',
		url:'/apis/area/getGardenIndustryEcharts.json?province='+area+'&year='+year,
		async:false,
		success:function(res){
			if(res.success){
				var arr1 = res.data;
				for(var i=0;i<arr1.length;i++){
					var arr2 = arr1[i];
					var data = {value:arr2[1], name:arr2[2]};
					seriesData.push(data);
					if(i == arr1.length - 1){
						industry = arr2[2];
					}
				}
				modalOption2.series[0].data = seriesData;
			    modalCharts2.setOption(modalOption2,true);
			}
		}
	});
	
}
var industryType='新兴信息产业';
var histogramName = new Array();
var histogramValue = new Array();
var barCharts;
function showGardenIndustryCount(industryType){//查询不同省份某种产业数量的接口
	var req = {"industryType":industryType};
	$.ajax({
		type:'post',
		url:'/apis/area/getGardenIndustryCount.json',
		data:JSON.stringify(req),
		contentType:'application/json',
		async:false,
		success:function(res){
			if(res.success){
				var arr = res.data;
				histogramName = [];
				histogramValue = [];
				for(var i=0;i<arr.length;i++){
					histogramName.push(arr[i][0]);
					histogramValue.push(arr[i][1])
				}
				barOption.xAxis[0].data = histogramName;
				barOption.series[0].data = histogramValue;
				
				
				barCharts.setOption(barOption,true);
			}
		}
	});
}
var province = '山东';
var pieChartName = new Array();
var scatter;
function showGardenGdpPiechart(province){//获取某个省份哪种产业最多的接口
	var req = {"province":province};
	$.ajax({
		type:'post',
		url:'/apis/area/getIndustryByProvince.json',
		data:JSON.stringify(req),
		contentType:'application/json',
		async:false,
		success:function(res){
			if(res.success){
				var arr = res.data;
				pieChartName = [];
				for(var i=0;i<arr.length;i++){
					pieChartName.push(arr[i].industryType);
				}
				option.series[0].data = initEcharts(pieChartName);
				scatter.setOption(option,true);
			}
		}
	});
}
function showFindMore(area){
	$("#findMore").attr("href","/apis/area/garden/allCityPark.html?area="+area);
}


