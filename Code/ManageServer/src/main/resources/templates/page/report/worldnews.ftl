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
    <title>各地新闻</title>
    <link rel="stylesheet" href="/vendor/base.css">
    <link rel="stylesheet" href="/vendor/rem.js">
    <link rel="stylesheet" href="/css/common.css">
</head>

<body>
    <div class='container'>
        <div id='map' style='height:16rem'></div>
        <div class='maptext'>
            <ul></ul>
        </div>
    </div>

    <script src="/vendor/echarts/echarts.min.js"></script>
    <script src="/vendor/echarts/china.js"></script>
    <script src="/vendor/jquery-3.3.1.min.js"></script>
    <script>
        $.ajax({
                url: '/apis/report/getHtmlData.do?id=' + $ {
                    id
                } + '&type=' + $ {
                    type
                },
                type: 'GET',
                async: false,
                success: function(res) {
                    var mapname = new Array();
                    var maptext = new Array();
                    for (var i = 0; i < res.data.length; i++) {
                        // 地名
                        mapname.push(res.data[i].area)
                        var textobj = new Object();
                        textobj.name = res.data[i].area;
                        textobj.text = res.data[i].data;
                        maptext.push(textobj)
                        if (i == 0) {
                            var strHtml = "";
                            for (var j = 0; j < maptext[0].text.length; j++) {
                                strHtml += "<li>" + maptext[0].text[j] + "</li>"
                            }
                            $('.maptext ul').html(strHtml)
                        }
                    }
                    allMap(mapname, maptext)

                }
            })
            //  map渲染
        function allMap(index, text) {
            console.log(index, text)
            var _map = new Array();
            $.each(index, function(i, v) {
                _map.push({
                    name: v,
                    selected: true
                });
            })
            var dom = document.getElementById("map");
            var app = {};
            var mapOption = null;
            mapOption = {
                tooltip: {
                    trigger: 'item',
                    formatter: '{b}'
                },
                series: [{
                    name: '中国',
                    type: 'map',
                    mapType: 'china',
                    selectedMode: 'multiple',
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: false,
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
                    },
                    data: _map

                }]
            };
            var myChart1 = echarts.init(dom);
            console.log(myChart1)
            myChart1.setOption(mapOption, true);
            myChart1.on('click', function(e) {
                $.each(text, function(ind, val) {
                    if (val.name == e.name) {
                        var strHtml = "";
                        for (var i = 0; i < val.text.length; i++) {
                            strHtml += "<li>" + val.text[i] + "</li>"
                        }
                        $('.maptext ul').html(strHtml)
                    }
                })
            })

        }
    </script>

</body>

</html>