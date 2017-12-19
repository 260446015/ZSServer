/**
 * Created by zhangxin on 2017/11/15.
 */
var industry="";
$(function (){
	new Loading({isfullscreen:true,text:"正在努力加载,三秒后消失"}).show();
	$("#indus").addClass("active");
	 industry= $(".mark-box").find($(".active")).find($("li>a.mark-item"))[0].id;
	/*获取企业排行的数据*/
	industryHotRank(industry);
	industryMapData(industry);
	
});

$(".mark-box").on("click","li>a.mark-item",function () {
        var _this = $(this);
        _this.parent().addClass("active").siblings().removeClass("active");
        $(".layer-person").hide();
        
         industry  = $(this).attr("id");
        
        /*获取企业排行的数据*/
        industryHotRank(industry);
        /*获取地图的数据*/
        industryMapData(industry);
        
        /*获取峰会数据*/
        industrySummitInfo(industry);
    });
	


function industrySummitInfo(industry){
	$.ajax({
		url:'/indusMap/getIndustrySummit.json',
		type:'POST',
		data:{"industry":industry},
		success:function(res){
			if(res.data != null){
			$("#timeline li").remove();
				var aa = res.data;
				for(var i=0;i<aa.length;i++){
					$("#timeline").append('<li>'+'<a href="'+aa[i].articleLink+'"; target="_blank")><i class="timeline-circle"></i><span class="time">'+aa[i].publishTime+'</span><span class="line-title">'+aa[i].title+'</span></a></li>');
				}
				
			}
			
		}
	});
};

var industryHotCity = {
	    title: {
	        text: '产业热度城市排行',
	        textStyle:{
	            fontWeight: 400,
	            fontSize: 26
	        }
	    },
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow'
	        }
	    },
	    grid: {
	        show:false,
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel:true,
	    },
	    xAxis: {
	        axisLine:{
	            show:false
	        },
	        axisTick:{
	            show:false
	        },
	        axisLabel:{
	            show:false
	        },
	        splitLine:{
	            show:false
	        },
	        type: 'value',
	        boundaryGap: [0, 0.01]
	    },
	    yAxis: {
	        axisLine:{
	            show:false
	        },
	        axisTick:{
	            show:false
	        },
	        splitLine:{
	            show:false
	        },
	        type: 'category',

	        data: ["苏州","青岛","南京",'天津','广州','杭州','深圳','上海','北京']
	    },
	    series: [
{
    type: 'bar',
    barWidth: 18,
    itemStyle: {
        normal: {
            color: 'rgba(0,0,0,1)',
            borderWidth: 1,
            barBorderRadius: 9,
            borderColor: "#0096ff"
        },
        emphasis: {
            color: 'rgba(0,0,0,1)',
            borderWidth: 1,
            barBorderRadius: 9,
            borderColor: "#0096ff"
        }
    },
    data: [46756,46756, 46756, 46756, 46756, 46756, 46756, 46756, 46756, 46756]
},
	        {
	            type: 'bar',
	            barWidth:14,
	            barGap: '-89%',
	            itemStyle: {
	                normal: {
	                    borderColor: "#2768ca",
	                    borderWidth: 1,
	                    barBorderRadius: 7,
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 1, 0,
	                        [
	                            {offset: 0, color: '#0096ff'},
	                            {offset: 0.5, color: '#53c9f0'},
	                            {offset: 1, color: '#00ffe4'}
	                        ]
	                    )
	                },
	                emphasis: {
	                    borderColor: "#2768ca",
	                    borderWidth: 1,
	                    barBorderRadius: 7,
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 1, 0,
	                        [
	                            {offset: 0, color: '#00ffe4'},
	                            {offset: 0.7, color: '#53c9f0'},
	                            {offset: 1, color: '#0096ff'}
	                        ]
	                    )
	                }
	            },
	            
	            data: [50, 51, 52, 53, 54, 55, 56,57, 58]
	        }
	    ]
	};
var industryHotCityCharts = echarts.init(document.getElementById("industryHotCity"), 'customed');
industryHotCityCharts.setOption(industryHotCity);
industryHotCityCharts.on('click',function(param){
	findCompany(param.name,industry);
});
function industryHotRank(industry){
	var a=[],b=[];
	 $.ajax({
		 url:'/indusMap/getRankInfoByIndustry.json?industry='+industry,
		 type: 'GET',
		 success:function(result){
			 if(result.data==null){
				 new Alert({flag:false,text:res.message,timer:2000}).show();
			 }else{
				 var data = result.data;
					for( i = 0;i<data.length;i++){
						a[i]= data[i].area;
						b[i]= data[i].count;	
					}
					 var array = b.reverse();
					var arr =  [array[array.length-1], array[array.length-1], array[array.length-1], array[array.length-1], array[array.length-1], array[array.length-1], array[array.length-1], array[array.length-1],array[array.length-1], array[array.length-1]];
					industryHotCity.yAxis.data = a.reverse();
					industryHotCity.series[1].data = array;
					industryHotCity.series[0].data = arr;
					industryHotCityCharts.setOption(industryHotCity);
					findCompany(data[0].area,industry);
					
			 }
			
		 }
	 });
 };
function findCompany(a,b){
	
	$.ajax({
		url :'/indusMap/getBusinessListByArea.json',
		type : 'POST',
		data :{ "area":a,"industry":b},
		success :function(result){
			var data = result.data;
				$(".box-title").html('优质企业('+a+')');
				$("#box-list li").remove();
			for(var i = 0;i<data.length ; i++){
				$("#box-list").append('<li>'+'<a href="/apis/company/baseInfo.html?companyName='+data[i]+'">'+data[i]+'</a></li>');
			}
		}
	});
};



function industryMapData(a){
	$.ajax({
		url:'/indusMap/getMapInfoByIndustry.json',
		type:'GET',
		data:{"industry":a},
		success:function(res){
			if(res.message!=null){
				new Loading({isfullscreen:true}).hide();
				new Alert({flag:false,text:res.message,timer:2000}).show();
			}else{
				var  dd =  res.data.map;
				var label = res.data.label;
				var industryMap = echarts.init(document.getElementById('industryMap'),"customed");
				chinaOption.series[1].data = convertData(dd);
				chinaOption.series[0].data = label;
				industryMap.setOption(chinaOption);
				new Loading({isfullscreen:true}).hide();
			}
		}
	});
};
var data = [
    {name: '延安', value: 38},
    {name: '山西', value: 39},
    {name: '清远', value: 39},
    {name: '中山', value: 39},
    {name: '昆明', value: 39},
    {name: '寿光', value: 40},
    {name: '盘锦', value: 40},
    {name: '长治', value: 41},
    {name: '深圳', value: 41},
    {name: '广东', value: 42},
    {name: '大连', value: 47},
    {name: '临汾', value: 47},
    {name: '吴江', value: 47},
    {name: '石嘴山', value: 49},
    {name: '沈阳', value: 50},
    {name: '苏州', value: 50},
    {name: '茂名', value: 50},
    {name: '嘉兴', value: 51},
    {name: '长春', value: 51},
    {name: '胶州', value: 52},
    {name: '银川', value: 52},
    {name: '张家港', value: 52},
    {name: '三门峡', value: 53},
    {name: '陕西', value: 55},
    {name: '金坛', value: 56},
    {name: '东营', value: 57},
    {name: '牡丹江', value: 58},
    {name: '遵义', value: 59},
    {name: '绍兴', value: 60},
    {name: '扬州', value: 61},
    {name: '常州', value: 62},
    {name: '潍坊', value: 63},
    {name: '重庆', value: 64},
    {name: '台州', value: 65},
    {name: '渭南', value: 66},
    {name: '马鞍山', value: 67},
    {name: '宝鸡', value: 68},
    {name: '焦作', value: 69},
    {name: '句容', value: 71},
    {name: '徐州', value: 72},
    {name: '衡水', value: 73},
    {name: '包头', value: 74},
    {name: '绵阳', value: 75},
    {name: '乌鲁木齐', value: 76},
    {name: '兰州', value: 77},
    {name: '沧州', value: 78},
    {name: '临沂', value: 79},
    {name: '宜昌', value: 80},
    {name: '义乌', value: 81},
    {name: '丽水', value: 82},
    {name: '河南', value: 83},
    {name: '秦皇岛', value: 85},
    {name: '株洲', value: 86},
    {name: '石家庄', value: 87},
    {name: '莱芜', value: 88},
    {name: '常德', value: 89},
    {name: '保定', value: 90},
    {name: '湘潭', value: 91},
    {name: '金华', value: 92},
    {name: '岳阳', value: 93},
    {name: '湖南', value: 94},
    {name: '衢州', value: 95},
    {name: '廊坊', value: 96},
    {name: '菏泽', value: 97},
    {name: '安徽', value: 98},
    {name: '湖北', value: 99},
    {name: '北京', value: 100}
];

var geoCoordMap = {
		'北京' :[116.28, 39.54] , 
		'青岛' :[120.19, 36.04] , 
		'天津' :[117.10, 39.10] , 
		'河南' :[113.42, 34.44] , 
		'郑州' :[113.42, 34.44] ,
		'石家庄': [114.26, 38.03] , 
		'河北': [114.26, 38.03] ,
		'开封' :[114.23, 34.52] , 
		'保定': [115.28, 38.53] ,
		'洛阳' :[112.26, 34.43] , 
		'唐山' :[118.09, 39.37] , 
		'许昌': [113.48, 34.00] , 
		'秦皇岛' :[119.37, 39.54] , 
		'新乡' :[113.54, 35.18] , 
		'张家口': [114.55, 40.51] ,  
		'湖北' :[114.20, 30.37] ,  
		'武汉' :[114.20, 30.37] ,
		'承德' : [117.52, 40.59] , 
		'宜昌' : [111.15, 30.42] , 
		'山西' : [112.33, 37.51] , 
		'太原' : [112.33, 37.51] , 
		'沙市' : [112.17, 30.16] , 
		'大同' : [113.13, 40.07] , 
		'湖南' : [112.55, 28.12] ,
		'长沙' : [112.55, 28.12] , 
		'临汾' : [111.31, 36.05] , 
		'衡阳' : [112.34, 26.55] , 
		'长治' : [113.13, 36.05] , 
		'湘潭' : [112.51, 27.54] , 
		'内蒙古' : [111.38, 40.48] , 
		'呼和浩特' : [111.38, 40.48] , 
		'常德' : [111.39, 29.00] , 
		'包头' : [110.00, 40.35] , 
		'广东' : [113.18, 23.10] , 
		'广州' : [113.18, 23.10] , 
		'海拉尔' : [119.43, 49.14] , 
		'汕头' : [116.40, 23.21] , 
		'辽宁' : [123.23, 41.48] , 
		'沈阳' : [123.23, 41.48] , 
		'韶关' : [113.33, 24.48] , 
		'大连' : [121.38, 38.54] , 
		'海口' : [110.10, 20.03] ,
		'海南' : [110.10, 20.03] , 
		'鞍山' : [123.00, 41.04] , 
		'南宁' : [108.21, 22.47] , 
		'锦州' : [121.09, 41.09] , 
		'桂林' : [110.10, 25.18] , 
		'吉林' : [125.18, 43.55] , 
		'长春' : [125.18, 43.55] , 
		'柳州' : [109.19, 24.20] , 
		'吉林' : [126.36, 43.48] , 
		'悟州' : [111.18, 23.28] , 
		'黑龙江' : [126.38, 45.45] ,
		'哈尔滨' : [126.38, 45.45] , 
		'四川' : [104.04, 30.39] , 
		'成都' : [104.04, 30.39] , 
		'齐齐哈尔' : [123.55, 47.22] , 
		'重庆' : [106.33, 29.33] , 
		'牡丹江' : [129.36, 44.35] , 
		'内江' : [105.03, 29.35] , 
		'上海' : [121.26, 31.12] , 
		'泸州' : [105.27, 28.54] , 
		'江苏' : [118.46, 32.03] , 
		'南京' : [118.46, 32.03] , 
		'万县' : [108.22, 30.48] , 
		'无锡' : [120.18, 31.35] , 
		'贵州' : [106.43, 26.34] , 
		'贵阳' : [106.43, 26.34] ,
		'苏州' : [120.39, 31.20] , 
		'遵义' : [106.53, 27.45] , 
		'徐州' : [117.12, 34.16] , 
		'云南' : [102.42, 25.03] ,
		'昆明' : [102.42, 25.03] ,
		'杭州' : [ 120.10, 30.15] ,  
		'浙江' : [ 120.10, 30.15] , 
		'拉萨' : [91.02, 29.39] , 
		'宁波' : [121.34, 29.53] , 
		'日喀则' : [88.49, 29.16] , 
		'温州' : [120.38, 28.00] , 
		'陕西' : [108.55, 34.15] ,
		'西安' : [108.55, 34.15] , 
		'金华' : [119.49, 29.10] , 
		'宝鸡' : [107.09, 34.21] , 
		'安徽' : [117.16, 31.51] , 
		'合肥' : [117.16, 31.51] , 
		'延安' : [109.26, 36.35] , 
		'芜湖' : [118.20, 31.21] , 
		'甘肃' : [103.50, 36.03] , 
		'兰州' : [103.50, 36.03] , 
		'安庆' : [117.02, 30.32] , 
		'天水' : [105.33, 34.35] , 
		'福州' : [119.19, 26.02] , 
		'福建' : [119.19, 26.02] , 
		'酒泉' : [98.30, 39.44] , 
		'厦门' : [118.04, 24.26] , 
		'青海' : [101.49, 36.37] , 
		'西宁' : [101.49, 36.37] , 
		'泉州' : [118.37, 24.54] , 
		'宁夏' : [106.13, 38.28] , 
		'银川' : [106.13, 38.28] ,
		'江西' : [115.53, 28.41] , 
		'南昌' : [115.53, 28.41] , 
		'新疆' : [87.36, 43.46] ,
		'乌鲁木齐' : [87.36, 43.46] , 
		'九江' : [115.59, 29.43] , 
		'哈密' : [93.27, 42.50] , 
		'赣州' : [114.56, 25.51] , 
		'喀什' : [75.59, 39.27] , 
		'山东' : [117.02, 36.40] ,
		'济南' : [117.02, 36.40] , 
		'和田' : [79.55, 37.07] , 
		'烟台' : [121.20, 37.33] , 
		'台湾' : [121.31, 25.02], 
		'台北' : [121.31, 25.02]
};
var convertData = function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var geoCoord = geoCoordMap[data[i].name];
        if(data[i].value>500){
        	data[i].value = 500;
        }
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
		 visualMap: {
             seriesIndex: 0,
             min: 0,
             max: 1000,
             left: 'left',
             top: 'bottom',
             text:['高','低'],           // 文本，默认为数值文本
             calculable : true,
             show: false,
             inRange: {
                 color: ["#bbdafd","#22508e"]
             }
         },
		geo: {
            map: 'china',
            zoom: 1.25,
            label: {
                normal: {
                    show: false,
                    color: '#fff'
                },
                emphasis: {
                    show: true,
                    color: '#fff'
                }
            },
            itemStyle: {
                emphasis: {
                    show: false,
                    areaColor: '',
                    borderColor: ''
                }
            },
            roam: false,
        },
    series : [{
        type: 'map',
        mapType: 'china',
        zoom: 1.25,
        geoIndex:0,
        label: {
            normal: {
                show: true
            },
            emphasis: {
                show: true
            }
        },
        itemStyle: {
            emphasis: {
                show: false,
                areaColor: '',
                borderColor: ''
            }
        },
        roam: false,
        data:[
        ]
    },
    {
        name: 'Top 5',
        type: 'effectScatter',
        coordinateSystem: 'geo',
        data: convertData(data),
        symbolSize: 15,
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
]/*
        {
            name: 'Top 5',
            type: 'effectScatter',
            coordinateSystem: 'geo',
            data: convertData(data.sort(function (a, b) {
                return b.value - a.value;
            }).slice(0, 6)),
            symbolSize: function (val) {
            	
            		return 20;
            	
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
    ]*/
};

var industryMap = echarts.init(document.getElementById('industryMap'),"customed");
industryMap.setOption(chinaOption);
/**
 * 地图点击事件
 */
industryMap.on("click",function (e) {
    /*这里需要判断一下 点击哪一个类型的点，才显示简介*/
	if(e.seriesType=="effectScatter"){
		
		var area = e.name;
		$.ajax({
			url:'/indusMap/getLaboratoryInfo.json',
			type:'POST',
			data:{"industry":industry,"area":area},
			success:function(res){
			 
				if(res.data==null){
					new Alert({flag:false,text:"暂无数据",timer:2000}).show();
				}else{
					var arr = res.data;
					$("#carousel-inner").html(showInfo(arr));
					$(".layer-person").css({
				        display: "block",
				        top: e.event.offsetY-212,
				        left: e.event.offsetX+30
				    });
				}
			}
		});
	}else{
		
	}
	
   
});
/**
 * 点击关注
 */
$(".like").on("click",function () {
    var _this = $(this);
    var _id = _this.parents(".layer-person").find('.layer-body').find('#myCarousel').find($("#carousel-inner .item-active")).find($('input[name="textName"]').val());
    _this.parents(".layer-person").hide();
    $.ajax({
    	url:'/indusMap/insertLaboratoryInfo.json',
    	type:'GET',
    	data:{"id":_id.selector},
    	success:function(res){
    		if(res.data==null){
    			new Alert({flag:false,text:res.message,timer:2000}).show();
    		}else{
    			new Alert({flag:true,text:res.data,timer:2000}).show();
    		}
    	}
    });
});
$(".close").on("click",function () {
	$(this).parents(".layer-person").hide();
});
function showInfo(arr){
	var array = [];
	$.each(arr,function(index,item){
		$('input[name="textName"]').val(item.id);
		if(index==0){
		array.push(
		'<div class="item active">'
		+'<div class="form-horizontal"><input type="hidden" name="textName" value="'+item.id+'"/><div class="form-group text-center">'
		+'</div>'
		+'<div class="form-group"><label class="col-md-4 text-right control-label">实验室名称</label><div class="col-md-7">'
        +'<p class="form-control-static" >'+item.laboratoryName+'</p></div></div>'
		+'<div class="form-group"><label class="col-md-4 text-right control-label">带头人</label><div class="col-md-7">'
        +'<p class="form-control-static" >'+item.academicLeader+'</p></div></div>'
        +'<div class="form-group"><label class="col-md-4 text-right control-label">机构性质</label>'
        +'<div class="col-md-7"><p class="form-control-static">'+item.institutionalCategory+'</p></div></div>'
        +'<div class="form-group"><label class="col-md-4 text-right control-label">机构产业</label><div class="col-md-7">'
        +'<p class="form-control-static">'+item.industry+'</p></div></div>'
        +'<div class="form-group"><label class="col-md-4 text-right control-label">委托单位</label><div class="col-md-7">'
        +'<p class="form-control-static">'+item.supportUnit+'</p></div></div>'
        +'<div class="form-group"><label class="col-md-4 text-right control-label">机构网址</label>'
        +'<div class="col-md-7"><p class="form-control-static" title="'+item.url+'">'+item.url+'</p></div></div></div></div>');
		}else{
		array.push(
			'<div class="item">'
			+'<div class="form-horizontal"><input type="hidden" name="textName" value="'+item.id+'"/><div class="form-group text-center">'
			+'</div>'
			+'<div class="form-group"><label class="col-md-4 text-right control-label">实验室名称</label><div class="col-md-7">'
			+'<p class="form-control-static" >'+item.laboratoryName+'</p></div></div>'
			+'<div class="form-group"><label class="col-md-4 text-right control-label">带头人</label><div class="col-md-7">'
			+'<p class="form-control-static" >'+item.academicLeader+'</p></div></div>'
			+'<div class="form-group"><label class="col-md-4 text-right control-label">机构性质</label>'
			+'<div class="col-md-7"><p class="form-control-static">'+item.institutionalCategory+'</p></div></div>'
			+'<div class="form-group"><label class="col-md-4 text-right control-label">机构产业</label><div class="col-md-7">'
			+'<p class="form-control-static">'+item.industry+'</p></div></div>'
			+'<div class="form-group"><label class="col-md-4 text-right control-label">委托单位</label><div class="col-md-7">'
			+'<p class="form-control-static" >'+item.supportUnit+'</p></div></div>'
			+'<div class="form-group"><label class="col-md-4 text-right control-label">机构网址</label>'
			+'<div class="col-md-7"><p class="form-control-static" title="'+item.url+'">'+item.url+'</p></div></div></div></div>'
			);
		}
	});
	var inner = array.join('');
	return inner;
}
