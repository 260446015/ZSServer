var scatter = echarts.init(document.getElementById("scatter"));
var label = {
    normal: {
        show: true,
        textStyle: {
            fontSize: '16',
            color: '#000000'
        },
        position: 'inside',
        formatter: function(param) {
            return param.data.name;
        }
    }
};
var data1 = [
    {
        value: [50, 50], symbolSize: 140, name: '智能', label:label
    },
    {
        value: [35,60], symbolSize: 100, name: '节能', label:label
    },
    {
        value: [65,90], symbolSize: 85, name: '大数据', label:label
    },
    {
        value: [32,20], symbolSize: 75, name: 'AI', label:label
    },
    {
        value: [55,10], symbolSize: 70, name: 'VR', label:label
    },
    {
        value: [70,50], symbolSize: 65, name: '科技园', label: label
    },
    {
        value: [35,95], symbolSize: 60, name: '行业', label: label
    },
    {
        value: [50,100], symbolSize: 55, name: '创新', label:label
    },
    {
        value:  [82,90], symbolSize: 50, name: '融合', label:label
    },
    {
        value:  [15,80], symbolSize: 50, name: '大数据', label:label
    },
    {
        value:  [15,10], symbolSize: 50, name: '智能化', label:label
    },
    {
        value:  [82,15], symbolSize: 50, name: '变化', label:label
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
                color: "rgba(0,0,0,0)",
                borderWidth: 1,
                borderType: "solid",
                borderColor: "#093982",
                shadowBlur: 20,
                shadowColor: "#00c3f3",
                shadowOffsetX: 3,
                opacity: 1
            },
            emphasis: {
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#00a5fb'},
                        {offset: 0.5, color: '#00caf2'},
                        {offset: 1, color: '#00f0e8'}
                    ]
                ),
                shadowBlur: 0,
                borderWidth: 0,
                shadowOffsetX: 0,
            }
        },
        data: data1
    }]
};
scatter.setOption(option);
$(":radio").click(function(){
    alert('改变data1')
    option.series[0].data=data1;
    scatter.setOption(option,true);
});

