/**
 * Created by zhangxin on 2017/11/24.
 */
$(function () {
    $("input[type=radio]").iCheck({
        radioClass: 'iradio_square-blue'
    });
    var barOption = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        series: [
            {
                name:'访问来源',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: true,
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
                    {value:335, name:'直接访问'},
                    {value:310, name:'邮件营销'},
                    {value:234, name:'联盟广告'},
                    {value:135, name:'视频广告'},
                    {value:1548, name:'搜索引擎'}
                ]
            }
        ]
    };
    var charts1 = echarts.init(document.getElementById('charts1'),'customed');
    charts1.setOption(barOption);
    var lineChart = {
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
    var charts2 = echarts.init(document.getElementById("charts2"),'customed');
    charts2.setOption(lineChart);
});