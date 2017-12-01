/**
 * Created by zhangxin on 2017/11/27.
 */
$(function () {
    var dot = {
        name: '强相关',
        type: 'effectScatter',
        showEffectOn: 'emphasis',
        xAxisIndex: 0,
        yAxisIndex: 0,
        symbol: 'circle',
        symbolSize: 40,
        label: {
            normal: {
                show: true,
                textStyle: {
                    color: '#fff'
                },
                position: 'inside',
                formatter: function(param) {
                    return param.data[2];
                },
            },
        },
        itemStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#20c2fe'},
                        {offset: 0.5, color: '#6e92fb'},
                        {offset: 1, color: '#bd62f7'}
                    ]
                )
            }
        },

        data: [],
    }

    var datalist = [
        /**
         * x坐标
         * y坐标
         * name 标签名称
         * symbolSize 点大小
         * 趋势，1:上升 0:下降
         */
        [22, 36, '浪潮', 80, 1],
        [80, 50, '华为', 70, 1],
        [60, 20, '亚信', 80, 1],
        [75, 70, '小米', 87, 1],
        [52, 83, '360', 60, 0],
        [32, 83, '微软', 90, 0],
        [17, 60, '网易', 70, 0]
    ];

    var dataMap = datalist.map((item) => {
            return Object.assign({}, dot, {
                symbolSize: item[3],
                data: [
                    item
                ]
            })
        });


    var option = {
        title: {
            text: '',
            x: '35%',
            y: 0
        },
        tooltip: {
            trigger: 'item',
            backgroundColor: '#fff',
            textStyle: {
                color: '#999'
            },
            formatter: (item) => {
                if (item.data[2]) {
                    return `${item.data[2]}<br/>  坐标: x ${item.data[0]}  y ${item.data[1]}`;
                }
            }
        },
        xAxis: [{
            gridIndex: 0,
            type: 'value',
            show: false,
            min: 0,
            max: 100,
            nameLocation: 'middle',
            nameGap: 30


        }],
            yAxis: [{
            gridIndex: 0,
            min: 0,
            show: false,
            max: 100,
            nameLocation: 'middle',
            nameGap: 30,
        }],
            series: [
            ...dataMap, {
            name: '弱相关',
            type: 'effectScatter',
            showEffectOn: 'emphasis',
            xAxisIndex: 0,
            yAxisIndex: 0,
            symbol: 'circle',
            symbolSize: 120,
            label: {
                normal: {
                    show: true,
                    textStyle: {
                        color:"#fff",
                        fontSize: '20'
                    },
                    formatter: function(param) {
                        return param.data[2];
                    },
                },

            },
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1,
                        [
                            {offset: 0, color: '#20c2fe'},
                            {offset: 0.5, color: '#6e92fb'},
                            {offset: 1, color: '#bd62f7'}
                        ]
                    )
                }
            },
            data: [
                [50, 50, '中科点击', '#fff']
            ]
        }]
    };
    $("#charts").height($(window).height()-$(".navbar-trans").height()-$(".footer").height()-122);
    var charts = echarts.init(document.getElementById("charts"),"customed");
    charts.setOption(option)
    charts.on("click",function (e) {
        $(".layer-person").css({
            display: "block",
            top: e.event.offsetY,
            left: e.event.offsetX+200
        });
    })
});