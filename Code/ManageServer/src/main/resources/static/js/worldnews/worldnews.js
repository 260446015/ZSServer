var dom = document.getElementById("map");
var myChart = echarts.init(dom);
var app = {};
option = null;
var locations = [{
    name: '上海',
    coord: [121.472644, 31.231706]
}, {
    name: '北京',
    coord: [116.405285, 39.904989]
}, {
    name: '广东',
    coord: [113.280637, 23.839463714285714]
}];
option = {
    
    tooltip: {
        trigger: 'item',
        formatter: '{b}'
    },

    series: [
        {
            name: '中国',
            type: 'map',
            mapType: 'china',
            selectedMode : 'multiple',
            label: {
                normal: {
                    show: false
                },
                emphasis: {
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    areaColor: '#101029',
                    borderColor: '#00c0ff'
                },
                emphasis: {
                    areaColor: '#00c0ff'
                }
            }
        }
    ]
};

var currentLoc = 0;
setInterval(function () {
    myChart.setOption({
        series: [{
            center: locations[currentLoc].coord,
            zoom: 3,
            label: {
                normal: {
                    show:false,
                    textStyle: {
                        color: '#fff'
                    }
                },
                emphasis:{
                    show: true,
                    textStyle: {
                        color: "#fff"
                    }
                }
            },
            data:[
                {name: locations[currentLoc].name, selected: true}
            ],
            animationDurationUpdate: 1000,
            animationEasingUpdate: 'cubicInOut'
        }]
    });
    currentLoc = (currentLoc + 1) % locations.length;
}, 5000);;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}