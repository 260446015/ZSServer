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
                data : ['2013', '2015', '2016', '2017'],
                axisTick: {
                    alignWithLabel: true
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
                barWidth: '40%',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        color:"#fff",
                        formatter:function(val){
                            return val.data+" 万";
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#008fe0'},
                                {offset: 0.7, color: '#006dab'},
                                {offset: 1, color: '#004c78'}
                            ]
                        )
                    },
                    emphasis: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#004c78'},
                                {offset: 0.5, color: '#006dab'},
                                {offset: 1, color: '#008fe0'}
                            ]
                        )
                    }
                },
                data:[10, 52, 200, 334]
            }
        ]
    };
    var charts2 = echarts.init(document.getElementById("charts2"),'customed');
    charts2.setOption(lineChart);
});