var arr = [coverTime, keyWord, industryChain, allFoucs, dynamic, company, superiorCompany]
var reportId;
function addData(id) {
    reportId = id;
    coverTime();
    keyWord;
    industryChain;
    allFoucs;
    company;
    dynamic;
    superiorCompany;
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
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=chain',
        type: 'GET',
        async: false,
        success: function (res) {
            $('.aaa s').each(function (index, el) {
                for (var i = 0; i < res.data.length; i++) {
                    if (res.data[i].keyWord.indexOf($(el).text()) >= 0) {
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
        spaceBetween: 50,
        centeredSlides: true,
        autoplay: {
            delay: 3000,
            disableOnInteraction: false,
        },
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        }
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
                strHtml += "<li data-id=" + res.data[i].id + " data-name=" + res.data[i].name + " class='allfoucs'><a href='javascript:void(0);'>"
                strHtml += "<span class='mb10'> <img src=" + res.data[i].logoClass + ">"
                strHtml += "</span><span class='ticolor'>" + res.data[i].name + "</span></a></li>";
            }
            $('.each ul').html(strHtml)

        }
    })
}

$('.each ul').on('click', 'li', function () {
    var name = $(this).attr('data-name');
    var _id= $(this).attr('data-id');
    switch (name) {
        case '政策焦点':
            window.location.href = "/apis/report/policy.htm?id="+ reportId+'&type=' + _id
            break;
        case '资本焦点':
            window.location.href = "/apis/report/capital.htm?id="+ reportId+'&type=' + _id
            break;
        case '市场焦点':
            window.location.href = "/apis/report/bazaar.htm?id="+ reportId+'&type=' + _id
            break;
        case '舆论焦点':
            window.location.href = "/apis/report/consensus.htm?id="+ reportId+'&type=' + _id
            break;
        case '技术焦点':
            window.location.href = "/apis/report/science.htm?id="+ reportId+'&type=' + _id
            break;
        case '未来焦点':
            window.location.href = "/apis/report/future.htm?id="+ reportId+'&type=' + _id
            break;
    }
});












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
            manHtml += "<li> <img src=" + str.logo + " alt=''></li>";
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
                strHtml += "<li data-id=" + res.data[i].id + " data-name=" + res.data[i].name + " class='alldynamic'>"
                strHtml += "<a href='javascript:void();'><span class='mb10'> <img src=" + res.data[i].logoClass + ">"
                strHtml += "</span><span class='ticolor'>" + res.data[i].name + "</span></a></li>"
            }
            $('.industry ul').html(strHtml)
        }
    })
}

$(".industry ul").on("click","li",function(){
    var name = $(this).attr('data-name');
    var _id= $(this).attr('data-id');
    switch (name) {
        case '各地新闻':
            window.location.href = "/apis/report/worldnews.htm?id="+ reportId+'&type=' + _id
            break;
        case '合作动向':
            window.location.href = "/apis/report/collaborate.htm?id="+ reportId+'&type=' + _id
            break;
        case '企业动向':
            window.location.href = "/apis/report/tendency.htm?id="+ reportId+'&type=' + _id
            break;
        case '会议日程':
            window.location.href = "/apis/report/schedule.htm?id="+ reportId+'&type=' + _id
            break;
        case '排行报告':
            window.location.href = "/apis/report/rankingreport.htm?id="+ reportId+'&type=' + _id
            break;
        case '投融速递':
            window.location.href = "/apis/report/express.htm?id="+ reportId+'&type=' + _id
            break;
    }
})



// 行业动态渲染
// function adddynamic(id, name) {
//     $.ajax({
//         url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=' + id,
//         type: 'GET',
//         async: false,
//         success: function (res) {
//             if (name == '各地新闻') {
//                 var policyhtml = $("<div class='page page17 section'></div>");
//                 var appendHeader = $("<header><div class='left'><</div><div><h1>" + name + "</h1></div></header>")
//                 policyhtml.append(appendHeader);
//                 console.log('po')
//                 var container = $("<div class='container'><div id='map' style='height:16rem'></div><div class='maptext'><ul></ul></div></div>");
//                 policyhtml.append(container)
//                 $('#detil').html(policyhtml);
//                 console.log('html完成')
//                 ajaxMap(id);
//                 console.log($('.left'))
//                 $('.left').on('click', function () {
//                     $('.detail').css('display', 'none')
//                     $('#dowebok').css('display', 'block')
//                 })
//             } else if (name == '合作动向') {
//                 var policyhtml = $("<div class='page page18 section'></div>");
//                 var appendHeader = $("<header><div class='left'><</div><div><h1>" + name + "</h1></div></header>")
//                 policyhtml.append(appendHeader);
//                 var boxdom = $("<div class='dottedline'></div>");
//                 boxdom.append(container);
//                 var strHtml = "";
//                 for (var i = 0; i < res.data.length; i++) {
//                     if (i % 2) {
//                         strHtml += "<div class='rightline'><p class='ptext'><span class='leftdot'></span><small class='textcolor'>" + res.data[i].keyWord
//                             + "</small></p>";
//                         strHtml += "<p class='ptext'>" + res.data[i].text + "</p></div>"
//                     } else {
//                         strHtml += "<div class='leftline'><p><span class='rightdot'></span><small class='textcolor'>" + res.data[i].keyWord
//                             + "</small></p>";
//                         strHtml += "<p>" + res.data[i].text + "</p></div>"
//                     }
//                 }
//                 boxdom.html(strHtml)
//                 policyhtml.append(boxdom)
//                 $('#detil').html(policyhtml);
//                 $('.left').on('click', function () {
//                     $('.detail').css('display', 'none')
//                     $('#dowebok').css('display', 'block')
//                 })
//             } else if (name == '企业动向') {
//                 var policyhtml = $("<div class='page page19 section'></div>");
//                 var appendHeader = $("<header><div class='left'><</div><div><h1>" + name + "</h1></div></header>")
//                 policyhtml.append(appendHeader);
//                 var boxdom = $("<div class='allcapital'></div>");
//                 boxdom.append(container);
//                 var strHtml = "";
//                 for (var i = 0; i < res.data.length; i++) {
//                     strHtml += "<div><p>" + res.data[i].keyWord + "</p><p class='text'>" + res.data[i].text + "</p></div>";
//                 }
//                 boxdom.html(strHtml)
//                 policyhtml.append(boxdom)
//                 $('#detil').html(policyhtml);
//                 $('.left').on('click', function () {
//                     $('.detail').css('display', 'none')
//                     $('#dowebok').css('display', 'block')
//                 })
//             } else if (name == '会议日程') {
//                 var policyhtml = $("<div class='page page20 section'></div>");
//                 var appendHeader = $("<header><div class='left'><</div><div><h1>" + name + "</h1></div></header>")
//                 policyhtml.append(appendHeader);
//                 var boxdom = $("<div id='myId' class='jalendar'></div><div class='daytext'><ul></ul></div>");
//                 boxdom.append(container);
//                 policyhtml.append(boxdom)
//                 $('#detil').html(policyhtml);
//                 dayMeeting(id)
//                 $('.left').on('click', function () {
//                     $('.detail').css('display', 'none')
//                     $('#dowebok').css('display', 'block')
//                 })
//             } else if (name == '排行报告') {
//                 var policyhtml = $("<div class='page page21 section'></div>");
//                 var appendHeader = $("<header><div class='left'><</div><div><h1>" + name + "</h1></div></header>")
//                 policyhtml.append(appendHeader);
//                 var boxdom = $("<div class='allreport'></div>");
//                 boxdom.append(container);
//                 var strHtml = "";
//                 for (var i = 0; i < res.data.length; i++) {
//                     strHtml += "<div><ul><a href=" + res.data[i].img + "><li><p>" + res.data[i].keyWord + "</p><p class='read'>阅读</p></li></a>"
//                     strHtml += "<li>" + res.data[i].text + "</li><li>报告发布:" + res.data[i].people + "</li></a></ul></div>"
//                 }
//                 boxdom.html(strHtml);
//                 policyhtml.append(boxdom)
//                 $('#detil').html(policyhtml);
//                 $('.left').on('click', function () {
//                     $('.detail').css('display', 'none')
//                     $('#dowebok').css('display', 'block')
//                 })
//             } else if (name == '投融速递') {
//                 var policyhtml = $("<div class='page page22 section'></div>");
//                 var appendHeader = $("<header><div class='center'><div class='left'><</div><div><h1>" + name + "</h1></div></div></header>")
//                 policyhtml.append(appendHeader);
//                 var container = $("<div class='container'><div id='bar' style='height:16rem'></div><div class='maptexts'><ul></ul></div></div>");
//                 policyhtml.append(container)
//                 $('#detil').html(policyhtml);
//                 melting(id)
//                 $('.left').on('click', function () {
//                     $('.detail').css('display', 'none')
//                     $('#dowebok').css('display', 'block')
//                 })
//             }
//         }
//     })
// }

// $('.industry ul').on('click', '.alldynamic', function (e) {
//     $('#dowebok').css('display', 'none')
//     adddynamic($(this).attr('data-id'), $(this).attr('data-name'))
//     $('.detail').css('display', 'block')
// })


//各地新闻

// function ajaxMap(id) {
//     console.log(2222)
//     $.ajax({
//         url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=' + id,
//         type: 'GET',
//         async: false,
//         success: function (res) {
//             console.log(res)
//             var mapname = new Array();
//             var maptext = new Array();
//             for (var i = 0; i < res.data.length; i++) {
//                 //地名
//                 mapname.push(res.data[i].area)
//                 var textobj = new Object();
//                 textobj.name = res.data[i].area;
//                 textobj.text = res.data[i].data;
//                 maptext.push(textobj)
//                 if (i == 0) {
//                     var strHtml = "";
//                     for (var j = 0; j < maptext[0].text.length; j++) {
//                         strHtml += "<li>" + maptext[0].text[j] + "</li>"
//                     }
//                     $('.maptext ul').html(strHtml)
//                 }
//             }
//             allMap(mapname, maptext)
//         }
//     })
// }

// map渲染
// function allMap(index, text) {
//     console.log(index,text)
//     var _map = new Array();
//     $.each(index, function (i, v) {
//         _map.push({
//             name: v,
//             selected: true
//         });
//     })
//     var dom = document.getElementById("map");
//     var app = {};
//     var mapOption = null;
//     mapOption = {
//         tooltip: {
//             trigger: 'item',
//             formatter: '{b}'
//         },
//         series: [
//             {
//                 name: '中国',
//                 type: 'map',
//                 mapType: 'china',
//                 selectedMode: 'multiple',
//                 label: {
//                     normal: {
//                         show: false
//                     },
//                     emphasis: {
//                         show: false,
//                     }
//                 },
//                 itemStyle: {
//                     normal: {
//                         areaColor: '#101029',
//                         borderColor: '#00c0ff'
//                     },
//                     emphasis: {
//                         areaColor: '#00c0ff'
//                     }
//                 },
//                 data: _map

//             }
//         ]
//     };
//     var myChart1 = echarts.init(dom);
//     console.log(myChart1)
//     myChart1.setOption(mapOption, true);
//     console.log(1111)
//     myChart1.on('click', function (e) {
//         $.each(text, function (ind, val) {
//             if (val.name == e.name) {
//                 var strHtml = "";
//                 for (var i = 0; i < val.text.length; i++) {
//                     strHtml += "<li>" + val.text[i] + "</li>"
//                 }
//                 $('.maptext ul').html(strHtml)
//             }
//         })
//     })

// }


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
            strHtml += "<li>本月一共<span class='point'>" + res.data.total + "</span>场会议</li>";
            strHtml += "<li>最多会议地点是<span class='point'>" + res.data.place + "</span></li>";
            strHtml += "<li>会议覆盖的行业是<span class='point'>" + res.data.industry + "</span></li>";
            strHtml += "<li>推荐参加<span class='point'>" + res.data.advise + "交流大会</span></li>";
            $('.daytext ul').html(strHtml);

        }
    })

}







// 优质企业
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


// 获取页面

$(function () {
    $('#dowebok').fullpage({
        anchors: ['page1', 'page2', 'page3', 'page4', 'page5', 'page6', 'page7', 'page8', 'page9', 'page10'],
        scrollOverflow: true,
        onLeave: function (index, nextIndex, direction) {
            if (index <= arr.length - 1) {
                switch (true) {
                    case index < 7:
                        return arr[index]();
                    default:
                }
            }
        }
    });
});


$(document).on('click', '.cd-list>li>a', function () {
    var name = $(this).attr("href");
    name = name.split('#')[1];
    $.fn.fullpage.moveTo(name, 1);
});


