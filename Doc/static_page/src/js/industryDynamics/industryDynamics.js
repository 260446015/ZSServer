/**
 * Created by zhangxin on 2017/11/17.
 */
$(function () {
    $("input[type=radio]").iCheck({
        radioClass: 'iradio_square-blue'
    });
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
            value: [18,90], symbolSize: 79, name: '智能', itemStyle: {normal: {color: '#5D9CEC'}},label:label
        },
        {
            value: [10,20], symbolSize: 76, name: '节能', itemStyle: {normal: {color: '#62C87F'}},label:label
        },
        {
            value: [57,11], symbolSize: 69, name: '大数据', itemStyle: {normal: {color: '#F57BC1'}},label:label
        },
        {
            value: [90,30], symbolSize: 140, name: 'AI', itemStyle: {normal: {color: '#6ED5E6'}},label:label
        },
        {
            value: [85,82], symbolSize: 79, name: 'VR', itemStyle: {normal: {color: '#DCB186'}},label:label
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
    var scatter = echarts.init(document.getElementById("scatter"));
    scatter.setOption(option)
});