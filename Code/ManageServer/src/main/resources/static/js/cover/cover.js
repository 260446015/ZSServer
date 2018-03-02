var reportId;
function addData(id) {
    reportId = id;
    coverTime();
    keyWord();
    industryChain();
    allFoucs();
    company();
    dynamic();
    superiorCompany();
}

//获取时间
function coverTime() {
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId,
        type: 'GET',
        async: false,
        success: function (data) {
            var strHtml = "";
            strHtml += "<p>" + data.data.time + "</p>"
            $(".time").html(strHtml);
        }
    })
};


//月关键字
function keyWord() {
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=keyWord',
        type: 'GET',
        async: false,
        success: function (res) {
            for (var i = 0; i < res.data.length; i++) {
                var strHtml = "";
                strHtml += "<div class='popup'>"
                if (i % 2) {
                    strHtml += "<div class='leftborder text'>" + res.data[i].text + "</div>"
                    strHtml += "<div class='leftwire' id='line" + i + "'></div>"
                    $("#keymonth").append(strHtml)
                    getKey('line' + i, res.data[i].keyWord)
                } else {
                    strHtml += "<div class='leftwire' id='line" + i + "'></div>"
                    strHtml += "<div class='rightborder text'>" + res.data[i].text + "</div>"
                    $("#keymonth").append(strHtml)
                    getKey('line' + i, res.data[i].keyWord)
                }
                strHtml += "</div>"
            }
        }
    })
};

function getKey(_id, _data) {
    var myId = 1;
    var arr = new Array();
    $.each(_data, function (index, value) {
        arr.push({
            id: myId,
            name: value
        });
        myId++;
    });
    var linedom = document.getElementById(_id);
    var myChart = echarts.init(linedom);
    myChart.showLoading();
    $.get('/xml/data.gexf', function (xml) {
        myChart.hideLoading();
        var graph = echarts.dataTool.gexf.parse(xml);
        var categories = [];
        for (var i = 0; i < 1; i++) {

        }
        graph.nodes.forEach(function (node) {
            node.itemStyle = null;
            node.symbolSize = 5;
            node.value = node.symbolSize;
            node.category = node.attributes.modularity_class;
            node.x = node.y = null;
            // node.draggable = true;
        });
        var graphOption = {
            color: ["#3385ff"],
            tooltip: {},
            legend: [{
                data: categories.map(function (a) {
                    return a.name;
                })
            }],
            animation: false,
            series: [
                {
                    type: 'graph',
                    layout: 'force',
                    data: arr,
                    links: graph.links,
                    categories: categories,
                    symbolSize: 5,
                    roam: false,
                    animation: 'true',
                    label: {
                        normal: {
                            position: 'right',
                            show: true,
                            textStyle: {
                                color: '#fff'
                            }
                        }
                    },
                    force: {
                        repulsion: 25
                    }
                }
            ]
        };
        myChart.setOption(graphOption);
    }, 'xml');
}

//产业高亮点
function industryChain() {

    function removeAllSpace(str) {
        return str.replace(/\s+/g, "");
    }

    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=chain',
        type: 'GET',
        async: false,
        success: function (res) {
            console.log(res)
            
             $('.aaa s').each(function(index,el){
                 console.log($(el).text())
                 for (var i = 0; i < res.data.length; i++) {
                    /* $.each(res.data[i].keyWord, function (i, v) {
                        console.log(keyWord1 + "==" + v + "+keyWord1 == v")
                        if (keyWord1 == v) {
    
                            $('.aaa').css('color', '#0ec3ff')
                        }
                    }) */
                    console.log(res.data[i].keyWord.indexOf($(el).text()))
                    if(res.data[i].keyWord.indexOf($(el).text())>=0){
                        console.log($(el)[0])
                        $(el).addClass("redaa");
                    }   
                }
             })
       
            
            var strHtml = "";
            for (var i = 0; i < res.data.length; i++) {
                strHtml += "<li>" + res.data[i].text + "</li>";
            }
            $('.industrybottom ul').html(strHtml)
        }
    })
    var swiper = new Swiper('.swiper-container', {
        pagination: {
            el: '.swiper-pagination',
        },
    });
}


//焦点
function allFoucs() {
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=focus',
        type: 'GET',
        async: false,
        success: function (res) {
            var strHtml = "";
            for (var i = 0; i < res.data.length; i++) {
                strHtml += "<li><a href=" + res.data[i].url + "?id='+reportId+'&type=" + res.data[i].id + ">"
                strHtml += "<span class='mb10'> <img src=" + res.data[i].logoClass + ">"
                strHtml += "</span>" + res.data[i].name + "</a></li>";
            }
            for (var i = res.data.length - 1; i > -1; i--) {
                apendHtml(res.data[i].id, res.data[i].name);
            }
            $('.each ul').html(strHtml)

        }
    })
}


function apendHtml(_id, _name) {
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=' + _id,
        type: 'GET',
        async: false,
        success: function (res) {
            if (_name == '政策焦点') {
                var policyhtml = $("<div class='page page11'></div>");
                var appendHeader = $("<header><div class='center'><h1>" + _name + "</h1></div></header>")
                policyhtml.append(appendHeader);
                var container = $("<div class='container'></div>");
                var boxdom = $("<div class='dottedline'></div>");
                boxdom.append(container);
                var strHtml = "";
                for (var i = 0; i < res.data.length; i++) {
                    if (i % 2) {
                        strHtml += "<div class='rightline'><p class='ptext'><span class='leftdot'></span><small class='textcolor'>" + res.data[i].keyWord
                            + "</small></p>";
                        strHtml += "<p class='ptext'>" + res.data[i].text + "</p></div>"
                    } else {
                        strHtml += "<div class='leftline'><p><span class='rightdot'></span><small class='textcolor'>" + res.data[i].keyWord
                            + "</small></p>";
                        strHtml += "<p>" + res.data[i].text + "</p></div>"
                    }
                }
                boxdom.html(strHtml)
                policyhtml.append(boxdom)
                $('.page5').after(policyhtml);
            } else if (_name == '资本焦点') {
                var policyhtml = $("<div class='page page12'></div>");
                var appendHeader = $("<header><div class='center'><h1>" + _name + "</h1></div></header>")
                policyhtml.append(appendHeader);
                var container = $("<div class='container'></div>");
                var boxdom = $("<div class='allcapital'></div>");
                boxdom.append(container);
                var strHtml = "";
                for (var i = 0; i < res.data.length; i++) {
                    strHtml += "<div><p>" + res.data[i].keyWord + "</p><p class='text'>" + res.data[i].text + "</p></div>";
                }
                boxdom.html(strHtml)
                policyhtml.append(boxdom)
                $('.page5').after(policyhtml);
            } else if (_name == '市场焦点') {
                var policyhtml = $("<div class='page page13'></div>");
                var appendHeader = $("<header><div class='center'><h1>" + _name + "</h1></div></header>")
                policyhtml.append(appendHeader);
                var container = $("<div class='container'></div>");
                var boxdom = $("<div class='allbazzr'></div>");
                boxdom.append(container);
                var strHtml = "";
                for (var i = 0; i < res.data.length; i++) {
                    strHtml += "<div><p>" + res.data[i].keyWord + "</p><p class='text'>" + res.data[i].text + "</p></div>";
                }
                boxdom.html(strHtml)
                policyhtml.append(boxdom)
                $('.page5').after(policyhtml);
            } else if (_name == '舆论焦点') {
                var policyhtml = $("<div class='page page14'></div>");
                var appendHeader = $("<header><div class='center'><h1>" + _name + "</h1></div></header>")
                policyhtml.append(appendHeader);
                var container = $("<div class='container'></div>");
                var boxdom = $("<div class='dottedline'></div>");
                boxdom.append(container);
                var strHtml = "";
                for (var i = 0; i < res.data.length; i++) {
                    if (i % 2) {
                        strHtml += "<div class='rightline'><p class='ptext'><span class='leftdot'></span><small class='textcolor'>" + res.data[i].keyWord
                            + "</small></p>";
                        strHtml += "<p class='ptext'>" + res.data[i].text + "</p></div>"
                    } else {
                        strHtml += "<div class='leftline'><p><span class='rightdot'></span><small class='textcolor'>" + res.data[i].keyWord
                            + "</small></p>";
                        strHtml += "<p>" + res.data[i].text + "</p></div>"
                    }
                }
                boxdom.html(strHtml)
                policyhtml.append(boxdom)
                $('.page5').after(policyhtml);
            } else if (_name == '技术焦点') {
                var policyhtml = $("<div class='page page12'></div>");
                var appendHeader = $("<header><div class='center'><h1>" + _name + "</h1></div></header>")
                policyhtml.append(appendHeader);
                var container = $("<div class='container'></div>");
                var boxdom = $("<div class='allcapital'></div>");
                boxdom.append(container);
                var strHtml = "";
                for (var i = 0; i < res.data.length; i++) {
                    strHtml += "<div><p>" + res.data[i].keyWord + "</p><p class='text'>" + res.data[i].text + "</p></div>";
                }
                boxdom.html(strHtml)
                policyhtml.append(boxdom)
                $('.page5').after(policyhtml);
            } else if (_name == '未来焦点') {
                var policyhtml = $("<div class='page page13'></div>");
                var appendHeader = $("<header><div class='center'><h1>" + _name + "</h1></div></header>")
                policyhtml.append(appendHeader);
                var container = $("<div class='container'></div>");
                var boxdom = $("<div class='allbazzr'></div>");
                boxdom.append(container);
                var strHtml = "";
                for (var i = 0; i < res.data.length; i++) {
                    strHtml += "<div><p>" + res.data[i].keyWord + "</p><p class='text'>" + res.data[i].text + "</p></div>";
                }
                boxdom.html(strHtml)
                policyhtml.append(boxdom)
                $('.page5').after(policyhtml);
            }
        }
    })
}


//明星推荐
function company() {
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=recommend',
        type: 'GET',
        async: false,
        success: function (res) {
            var strHtml = "";
            for (var i = 0; i < res.data.company.length; i++) {
                strHtml += "<div><dl><dt>"
                strHtml += "<p><img src= " + res.data.company[i].logo + "></p>";
                strHtml += "<p>" + res.data.company[i].name + "</p></dt>";
                strHtml += "<dd>" + res.data.company[i].reason + "</dd></dl></div>"
            }
            $(".allcontainer").html(strHtml)
            var str = res.data.people;
            var manHtml = "";
            manHtml += "<ul>"
            manHtml += "<li> <img src='/img/photo/renwu.png' alt=''></li>";
            manHtml += "<li class='texts'>" + str.name + "</li>";
            manHtml += "<li class='texts'>" + str.identity + "</li>";
            manHtml += "<li>上榜理由：" + str.reason + "</li>"
            manHtml += "</ul>";
            $('.starmanimg').html(manHtml)
        }
    })
}


//行业动态
function dynamic() {
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=dynamic',
        type: 'GET',
        async: false,
        success: function (res) {
            var strHtml = "";
            for (var i = 0; i < res.data.length; i++) {
                strHtml += "<li><a>"
                strHtml += "<span class='mb10'> <img src=" + res.data[i].logoClass + ">"
                strHtml += "</span>" + res.data[i].name + "</a></li>"
            }
            for (var i = res.data.length - 1; i > -1; i--) {
                adddynamic(res.data[i].id, res.data[i].name);
            }
            $('.page-dynamic div>ul').html(strHtml)
        }
    })
}

function adddynamic(id, name) {
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=' + id,
        type: 'GET',
        async: false,
        success: function (res) {
            if (name == '各地新闻') {
                var policyhtml = $("<div class='page page17'></div>");
                var appendHeader = $("<header><div class='center'><h1>" + name + "</h1></div></header>")
                policyhtml.append(appendHeader);
                var container = $("<div class='container'><div id='map' style='height:16rem'></div><div class='maptext'><ul></ul></div></div>");
                policyhtml.append(container)
                $('.page6').after(policyhtml);
                ajaxMap(id);
            } else if (name == '合作动向') {
                var policyhtml = $("<div class='page page18'></div>");
                var appendHeader = $("<header><div class='center'><h1>" + name + "</h1></div></header>")
                policyhtml.append(appendHeader);
                var container = $("<div class='container'></div>");
                var boxdom = $("<div class='dottedline'></div>");
                boxdom.append(container);
                var strHtml = "";
                for (var i = 0; i < res.data.length; i++) {
                    if (i % 2) {
                        strHtml += "<div class='rightline'><p class='ptext'><span class='leftdot'></span><small class='textcolor'>" + res.data[i].keyWord
                            + "</small></p>";
                        strHtml += "<p class='ptext'>" + res.data[i].text + "</p></div>"
                    } else {
                        strHtml += "<div class='leftline'><p><span class='rightdot'></span><small class='textcolor'>" + res.data[i].keyWord
                            + "</small></p>";
                        strHtml += "<p>" + res.data[i].text + "</p></div>"
                    }
                }
                boxdom.html(strHtml)
                policyhtml.append(boxdom)
                $('.page6').after(policyhtml);
            } else if (name == '企业动向') {
                var policyhtml = $("<div class='page page19'></div>");
                var appendHeader = $("<header><div class='center'><h1>" + name + "</h1></div></header>")
                policyhtml.append(appendHeader);
                var container = $("<div class='container'></div>");
                var boxdom = $("<div class='allcapital'></div>");
                boxdom.append(container);
                var strHtml = "";
                for (var i = 0; i < res.data.length; i++) {
                    strHtml += "<div><p>" + res.data[i].keyWord + "</p><p class='text'>" + res.data[i].text + "</p></div>";
                }
                boxdom.html(strHtml)
                policyhtml.append(boxdom)
                $('.page6').after(policyhtml);
            } else if (name == '会议日程') {
                var policyhtml = $("<div class='page page20'></div>");
                var appendHeader = $("<header><div class='center'><h1>" + name + "</h1></div></header>")
                policyhtml.append(appendHeader);
                // var container = $("<div class='container'></div>");
                var boxdom = $("<div id='myId' class='jalendar'></div><div class='daytext'><ul></ul></div>");
                boxdom.append(container);
                policyhtml.append(boxdom)
                $('.page6').after(policyhtml);
                dayMeeting(id)
            } else if (name == '排行报告') {
                var policyhtml = $("<div class='page page21'></div>");
                var appendHeader = $("<header><div class='center'><h1>" + name + "</h1></div></header>")
                policyhtml.append(appendHeader);
                var container = $("<div class='container'></div>");
                var boxdom = $("<div class='allreport'></div>");
                boxdom.append(container);
                var strHtml = "";
                for (var i = 0; i < res.data.length; i++) {
                    strHtml += "<div><ul><a href=" + res.data[i].img + "><li><p>" + res.data[i].keyWord + "</p><p class='read'>阅读</p></li></a>"
                    strHtml += "<li>" + res.data[i].text + "</li><li>报告发布:" + res.data[i].people + "</li></a></ul></div>"
                }
                boxdom.html(strHtml);
                policyhtml.append(boxdom)
                $('.page6').after(policyhtml)
            } else if (name == '投融速递') {
                var policyhtml = $("<div class='page page22'></div>");
                var appendHeader = $("<header><div class='center'><h1>" + name + "</h1></div></header>")
                policyhtml.append(appendHeader);
                var container = $("<div class='container'><div id='bar' style='height:16rem'></div><div class='maptexts'><ul></ul></div></div>");
                policyhtml.append(container)
                $('.page6').after(policyhtml);
                melting(id)
            }
        }
    })
}

//各地新闻

function ajaxMap(id) {
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=' + id,
        type: 'GET',
        async: false,
        success: function (res) {
            var mapname = new Array();
            var maptext = new Array();
            for (var i = 0; i < res.data.length; i++) {
                //地名
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
}
function allMap(index, text) {
    var _map = new Array();
    $.each(index, function (i, v) {
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
        series: [
            {
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

            }
        ]
    };
    var myChart1 = echarts.init(dom);
    myChart1.setOption(mapOption, true);
    myChart1.on('click', function (e) {
        $.each(text, function (ind, val) {
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


// 日程

function dayMeeting(id) {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 2;
    var day = date.getDate();
    // 下一个月
    function nextDate() {
        return year + '/' + month + '/' + day
    }
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=' + id,
        type: 'GET',
        async: false,
        success: function (res) {
            $.each(res.data.schedule, function (index, item) {
                $('#myId').append(
                    '<div class="added-event" ' +
                    'data-date="' + item.date + '/' + month + '/' + year + '"' +
                    ' data-place="会议地点："' +
                    'data-place-val="' + item.place + '"' +
                    'data-sponsor="会议主办方："' +
                    'data-sponsor-val="' + item.sponsor + '"></div>'
                )
            })
            $('#myId').jalendar({
                customDay: nextDate(),  // Format: Year/Month/Day
            });
            var strHtml = "";
            strHtml += "<li>本月一共" + res.data.total + "场会议</li>";
            strHtml += "<li>最多会议地点是" + res.data.place + "</li>";
            strHtml += "<li>会议覆盖的行业是" + res.data.industry + "</li>";
            strHtml += "<li>推荐参加" + res.data.advise + "交流大会</li>";
            $('.daytext ul').html(strHtml);

        }
    })

}



// 投融速递

function melting(id) {

    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=' + id,
        type: 'GET',
        async: false,
        success: function (res) {
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
}


function meltingPar(a, b, c) {
    var bardom = document.getElementById("bar");
    var myChart2 = echarts.init(bardom);
    var baroption = {
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
                    rotate: -90
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
                }
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

function superiorCompany() {
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=industry',
        type: 'GET',
        async: false,
        success: function (res) {
            var strHtml1 = "";
            strHtml1 += " <div><h2>龙头企业</h2><ul>"
            strHtml1 += "<li><span>企业名称</span><span>企业产业</span></li>";
            for (var i = 0; i < res.data.faucet.length; i++) {
                strHtml1 += "<li><span class='mege'>" + res.data.faucet[i].company + "</span><div class='magimg'><p>注册资金:" + res.data.faucet[i].money + "</p><p>成立时间:" + res.data.faucet[i].time + "</p></div>"
                strHtml1 += "<span>" + res.data.faucet[i].text + "</span></li>";
            }
            strHtml1 += "</ul></div>"

            var strHtml2 = "";
            strHtml2 += " <div><h2>成长企业</h2><ul>"
            strHtml2 += "<li><span>企业名称</span><span>企业产业</span></li>";
            for (var i = 0; i < res.data.growth.length; i++) {
                strHtml2 += "<li><span class='mege'>" + res.data.growth[i].company + "</span><div class='magimg'><p>注册资金:" + res.data.growth[i].money + "</p><p>成立时间:" + res.data.faucet[i].time + "</p></div>"
                strHtml2 += "<span>" + res.data.growth[i].text + "</span></li>";
            }
            strHtml2 += "</ul></div>"
            var strHtml3 = "";
            strHtml3 += " <div><h2>潜力企业</h2><ul>"
            strHtml3 += "<li><span>企业名称</span><span>企业产业</span></li>";
            for (var i = 0; i < res.data.potential.length; i++) {
                strHtml3 += "<li><span class='mege'>" + res.data.potential[i].company + "</span><div class='magimg'><p>注册资金:" + res.data.potential[i].money + "</p><p>成立时间:" + res.data.faucet[i].time + "</p></div>"
                strHtml3 += "<span>" + res.data.potential[i].text + "</span></li>";
            }
            strHtml3 += "</ul></div>"
            strHtml = strHtml1 + strHtml2 + strHtml3;
            $('.company').html(strHtml)
            $('.mege').on("click", function () {
                $(".magimg").attr('style', "display:none")
                $(this).next().attr('style', "display:block")
            })
        }

    })

}


function getmege(faucet) {
    console.log(faucet)
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=industry',
        type: 'GET',
        async: false,
        success: function (res) {
            $('.mege').on("click", function () {
                console.log($(this))
                console.log(1)
                var megeval = $('.mege').html();
                console.log(megeval)
                for (var i = 0; i < res.data.faucet.length; i++) {
                    // console.log(faucet == res.data.faucet[i].company)
                    if (megeval == res.data.faucet[i].company) {
                        $('.magimg').show()
                    } else {
                        $('.magimg').hide()
                    }
                }

            })
        }
    })

}

//页面上下滑动

setInterval(function () {
    var newpage = 0;
    var num = $('.skippage .page').length - 1;
    $('.skippage').swipe({
        swipe: function (event, direction, distance, duration, fingerCount) {
            if (direction == 'up') {
                newpage = newpage + 1;
            } else if (direction == 'down') {
                newpage = newpage - 1;
            }
            if (newpage > num) {
                newpage = num;
            }
            if (newpage < 0) {
                newpage = 0;
            }
            $('.skippage').animate({ "top": newpage * -100 + "%" })
        }
    })
}, 1000)

