/**
 * Created by zhangxin on 2017/11/21.
 */
var data = [
    {name: '延安', value: 38},
    {name: '太原', value: 39},
    {name: '清远', value: 39},
    {name: '中山', value: 39},
    {name: '昆明', value: 39},
    {name: '寿光', value: 40},
    {name: '盘锦', value: 40},
    {name: '长治', value: 41},
    {name: '深圳', value: 41},
    {name: '珠海', value: 42},
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
    {name: '西安', value: 61},
    {name: '金坛', value: 62},
    {name: '东营', value: 62},
    {name: '牡丹江', value: 63},
    {name: '遵义', value: 63},
    {name: '绍兴', value: 63},
    {name: '扬州', value: 64},
    {name: '常州', value: 64},
    {name: '潍坊', value: 65},
    {name: '重庆', value: 66},
    {name: '台州', value: 67},
    {name: '渭南', value: 72},
    {name: '马鞍山', value: 72},
    {name: '宝鸡', value: 72},
    {name: '焦作', value: 75},
    {name: '句容', value: 75},
    {name: '徐州', value: 79},
    {name: '衡水', value: 80},
    {name: '包头', value: 80},
    {name: '绵阳', value: 80},
    {name: '乌鲁木齐', value: 84},
    {name: '兰州', value: 99},
    {name: '沧州', value: 100},
    {name: '临沂', value: 103},
    {name: '宜昌', value: 130},
    {name: '义乌', value: 132},
    {name: '丽水', value: 133},
    {name: '洛阳', value: 134},
    {name: '秦皇岛', value: 136},
    {name: '株洲', value: 143},
    {name: '石家庄', value: 147},
    {name: '莱芜', value: 148},
    {name: '常德', value: 152},
    {name: '保定', value: 153},
    {name: '湘潭', value: 154},
    {name: '金华', value: 157},
    {name: '岳阳', value: 169},
    {name: '长沙', value: 175},
    {name: '衢州', value: 177},
    {name: '廊坊', value: 193},
    {name: '菏泽', value: 194},
    {name: '合肥', value: 229},
    {name: '武汉', value: 273},
    {name: '北京', value: 279}
];

var geoCoordMap = {
    '延安':[109.47,36.6],
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
    '台州':[121.420757,28.656386],
    '渭南':[109.5,34.52],
    '马鞍山':[118.48,31.56],
    '宝鸡':[107.15,34.38],
    '焦作':[113.21,35.24],
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
            }).slice(0, 6)),
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

parkMap.on("click",function (e) {
    if(e.componentType=="series"){
        $("#myModal").modal("show");
    }
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
    series : [
        {
            name:'直接访问',
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
        formatter: function(param) {
            return param.data.name;
        }
    }
};
var data1 = [
    {
        value: [18,90], symbolSize: 79, name: '新一代信息技术', itemStyle: {normal: {color: '#5D9CEC'}},label:label
    },
    {
        value: [10,20], symbolSize: 76, name: '新能源', itemStyle: {normal: {color: '#62C87F'}},label:label
    },
    {
        value: [57,11], symbolSize: 69, name: '生物产业', itemStyle: {normal: {color: '#F57BC1'}},label:label
    },
    {
        value: [90,30], symbolSize: 140, name: '环保产业', itemStyle: {normal: {color: '#6ED5E6'}},label:label
    },
    {
        value: [85,82], symbolSize: 79, name: '新材料', itemStyle: {normal: {color: '#DCB186'}},label:label
    },
    {
        value: [65,90], symbolSize: 63, name: '科技园', itemStyle: {normal: {color: '#7053B6'}},label: label
    },
    {
        value: [42,100], symbolSize: 74, name: '行业', itemStyle: {normal: {color: '#647C9D'}},label: label
    },
    {
        value: [18,55], symbolSize: 74, name: '创新', itemStyle: {normal: {color: '#F15755'}},label:label
    },
    {
        value:  [50, 50], symbolSize:90, name: '融合', itemStyle: {normal: {color: '#FC863F'}},label:label
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
        data: data1
    }]
};
var scatter = echarts.init(document.getElementById("scatter"),"customed");
scatter.setOption(option);

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


$("#myModal").on("shown.bs.modal",function () {
    var modalCharts1 = echarts.init(document.getElementById("charts_1"),"customed");
    modalCharts1.setOption(modalOption1);

    var modalCharts2 = echarts.init(document.getElementById("charts_2"),"customed");
    modalCharts2.setOption(modalOption2);
});


