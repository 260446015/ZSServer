<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="description" content="中科点击·慧数招商">
    <meta name="keywords" content="慧数招商人工智能行业月度报告">
    <meta name="author" content="慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
    <title>投融速递</title>
    <link rel="stylesheet" href="/vendor/base.css">
    <link rel="stylesheet" href="/vendor/rem.js">
    <link rel="stylesheet" href="/css/common.css">
</head>

<body>
    <div class='express'>
        <div id='bar' style='height:16rem'>

        </div>
        <div class='maptexts'>
            <ul></ul>
        </div>
    </div>
</body>
<script src="/vendor/jquery-3.3.1.min.js"></script>
<script src="/vendor/echarts/echarts.min.js"></script>
<script src="/vendor/echarts/china.js"></script>
<script>
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + ${ id } + '&type=' + ${ type },
        type: 'GET',
        async: false,
        success: function (res) {
            console.log(res)
            var strHtml = "";
            var _index = new Array();
            var _data = new Array();
            var _objs = new Array();
            for (var i = 0; i < res.data.length; i++) {
                _index.push(res.data[i].industry);
                _data.push(res.data[i].money);
                var _obj = new Object();
                _obj.name = res.data[i].industry;
                _obj.value = res.data[i].array;
                _objs.push(_obj);
                if (i == 0) {
                    var strHtml = "";
                    for (var j = 0; j < _objs[0].value.length; j++) {
                        strHtml += "<li>" + _objs[0].value[j] + "</li>";
                    }
                    $('.maptexts ul').html(strHtml)
                }
            }
            meltingPar(_index, _data, _objs)

        }
      })
    function meltingPar(a, b, c) {
        var bardom = document.getElementById("bar");
        var myChart2 = echarts.init(bardom);
        var baroption = {
            title:{
                subtext:'单位:亿人民币'
            },
            color: ['#3398DB'],
            legendHoverLink: false,
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: a,
                    axisTick: {
                        alignWithLabel: true
                    },
                    show: true,
                    splitLine: {
                        show: false
                    },
                    axisLabel: {
                        interval: 0,
                        rotate: -90,
                        textStyle: {
                            color: '#00c0ff'
                        }
                    }
                }
            ],
            yAxis: [
                {
                    axisLine: {
                        show: false
                    },
                    show: false,
                    splitLine: {
                        show: false
                    },
                    type:'value',
                    min:-20,
                    max:15
                }
            ],
            series: [
                {
                    name: '',
                    type: 'bar',
                    barWidth: '15%',
                    data: b,
                    itemStyle: {
                        emphasis: {
                            barBorderRadius: 30
                        },
                        normal: {
                            barBorderRadius: [20, 20],
                            label: {
                                show: true,
                                position:'top',
                                textStyle: {
                                    ontWeight: 'bolder',
                                    fontSize: '12',
                                    fontFamily: '微软雅黑',
                                }
                            }
                        }
                    }
                }
            ]
        };
        myChart2.setOption(baroption);
        myChart2.on('click', function (e) {
            $.each(c, function (index, value) {
                if (value.name == e.name) {
                    var strHtml = "";
                    for (var i = 0; i < value.value.length; i++) {
                        strHtml += "<li>" + value.value[i] + "</li>";
                    }
                    $('.maptexts ul').html(strHtml)
                }
            })
        })
    }
</script>

</html>