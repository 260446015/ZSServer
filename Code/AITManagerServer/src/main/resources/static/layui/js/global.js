 // 基于准备好的dom，初始化echarts实例
    var myChart1 = echarts.init(document.getElementById('chart1'));
    var myChart2 = echarts.init(document.getElementById('chart2'));
    var barChart = echarts.init(document.getElementById('barChart'));
    option1 = {
        color: ['#3398DB','#FFB800','#2F4056','#009688','#4AAD44'],
        series: [
            {
                name:'访问来源',
                type:'pie',
                radius: ['50%', '70%'],

                data:[
                    {value:335, name:'天津'},
                    {value:310, name:'北京'},
                    {value:234, name:'上海'},
                    {value:135, name:'郑州'},
                    {value:23, name:'某某'}
                ]
            }
        ]
    };
    option2 = {
        color: ['#3398DB','#FFB800','#2F4056','#009688','#4AAD44'],
        series: [
            {
                name:'访问来源',
                type:'pie',
                radius: ['50%', '70%'],
                data:[
                    {value:335, name:'影视'},
                    {value:310, name:'外贸'},
                    {value:234, name:'生物医药'},
                    {value:135, name:'互联网'},
                ]
            },
        ]
    };

    option3 = {
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
                data : ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun','Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
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
                barWidth: '60%',
                data:[10, 52, 200, 334, 390, 330, 220,100, 52, 200, 334, 390, 330, 220]
            }
        ]
    };

    myChart1.setOption(option1);
    myChart2.setOption(option2);
    barChart.setOption(option3);