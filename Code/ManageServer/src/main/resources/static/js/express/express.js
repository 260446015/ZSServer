var bardom = document.getElementById("bar");
var myChart = echarts.init(bardom);
var dataAxis = ['智能硬件', '智能硬件', '智慧金融', '智慧金融', '智慧金融', '智慧金融', '智慧金融'];
var data = [220, 182, 191, 234, 290, 330, 310];
var yMax = 500;
var dataShadow = [];

for (var i = 0; i < data.length; i++) {
    dataShadow.push(yMax);
}

option = {
    title: {
        subtext: '单位：亿人民币'
    },
    xAxis: {
        data: dataAxis,
        axisLabel: {
            inside: false,
            show: true,
            interval: 'auto',
            rotate: 90
        },
        axisTick: {
            show: false
        },
        axisLine: {
            show: false
        },
        z: 10
    },
    yAxis: {
        axisLine: {
            show: false
        },
        axisTick: {
            show: false
        },
        axisLabel: {
            formatter: function () {
                return "";
            }
        }
    },
    dataZoom: [
        {
            type: 'inside'
        }
    ],
    label: {
        normal: {
            show: true,
            position: 'top',
            textStyle: {
                color: 'red'
            }
        }
    },
    series: [
        { // For shadow
            type: 'bar',
            itemStyle: {
                normal: { color: 'rgba(0,0,0,0.05)' }
            },
            barGap: '30%',
            barMaxWidth: 30,
            barCategoryGap: '20%',
            data: dataShadow,
            animation: false
        },
        {
            type: 'bar',
            itemStyle: {
                normal: {
                    barBorderRadius: 10,
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1,
                        [
                            { offset: 0, color: '#83bff6' },
                            { offset: 0.5, color: '#188df0' },
                            { offset: 1, color: '#188df0' }
                        ]
                    )
                },
                emphasis: {
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1,
                        [
                            { offset: 0, color: '#2378f7' },
                            { offset: 0.7, color: '#2378f7' },
                            { offset: 1, color: '#83bff6' }
                        ]
                    )
                }
            },
            data: data
        }
    ]
};

myChart.setOption(option);