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
        success: function(data) {
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
        success: function(res) {
            $("#keymonth").html("");
            for (var i = 0; i < res.data.length; i++) {
                var strHtml = "";
                strHtml += "<div class='popup'>"
                if (i % 2) {
                    strHtml += "<div class='leftborder'>" + res.data[i].text + "</div>"
                    strHtml += "<div class='leftwire' id='line" + i + "'></div>"
                    $("#keymonth").append(strHtml)
                    getKey('line' + i, res.data[i].keyWord)
                } else {
                    strHtml += "<div class='leftwire' id='line" + i + "'></div>"
                    strHtml += "<div class='rightborder'>" + res.data[i].text + "</div>"
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
    $.each(_data, function(index, value) {
        arr.push({
            id: myId,
            name: value
        });
        myId++;
    });
    var linedom = document.getElementById(_id);
    var myChart = echarts.init(linedom);
    myChart.showLoading();
    $.get('/xml/data.gexf', function(xml) {
        myChart.hideLoading();
        var graph = echarts.dataTool.gexf.parse(xml);
        var categories = [];
        for (var i = 0; i < 1; i++) {

        }
        graph.nodes.forEach(function(node) {
            node.itemStyle = null;
            node.symbolSize = 5;
            node.value = node.symbolSize;
            node.category = node.attributes.modularity_class;
            node.x = node.y = null;
        });
        var graphOption = {
            color: ["#3385ff"],
            tooltip: {},
            legend: [{
                data: categories.map(function(a) {
                    return a.name;
                })
            }],
            animation: false,
            series: [{
                type: 'graph',
                layout: 'force',
                data: arr,
                links: graph.links,
                categories: categories,
                symbolSize: 5,
                roam: false,
                animation: true,
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
            }]
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
        success: function(res) {
            $('.aaa s').each(function(index, el) {
                for (var i = 0; i < res.data.length; i++) {
                    if (res.data[i].keyWord.indexOf($(el).text()) >= 0) {
                        $(el).addClass("redaa");
                    }
                }
            })

            var strHtml = "";
            var strHtml1 = "";
            var strHtml2 = "";
            for (var i = 0; i < res.data.length; i++) {
                if (i == 0) {
                    strHtml = "<li>" + res.data[i].text + "</li>";
                }
                if (i == 1) {
                    strHtml1 = "<li>" + res.data[i].text + "</li>";
                }
                if (i == 2) {
                    strHtml2 = "<li>" + res.data[i].text + "</li>";
                }
            }
            $('.box1 ul').html(strHtml)
            $('.box2 ul').html(strHtml1)
            $(".box3 ul").html(strHtml2)
        }
    })
    var swiper = new Swiper('.swiper-container', {
        spaceBetween: 50,
        clickable: true,
        centeredSlides: true,
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
            renderBullet: function(index, className) {
                return '<span class="' + className + '">' + (index + 1) + '</span>';
            }
        },
    });
}


//焦点
function allFoucs() {
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=focus',
        type: 'GET',
        async: false,
        success: function(res) {
            var strHtml = "";
            for (var i = 0; i < res.data.length; i++) {
                strHtml += "<li data-id=" + res.data[i].id + " data-name=" + res.data[i].name + " class='allfoucs'><a href='javascript:void(0);'>"
                strHtml += "<span class='mb10 foucs'> <img src=" + res.data[i].logoClass + ">"
                strHtml += "</span><span class='ticolor'>" + res.data[i].name + "</span></a></li>";
            }
            $('.each ul').html(strHtml)

        }
    })
}

$('.each ul').on('click', 'li', function() {
    var name = $(this).attr('data-name');
    var _id = $(this).attr('data-id');
    switch (name) {
        case '政策焦点':
            window.location.href = "/apis/report/policy.htm?id=" + reportId + '&type=' + _id
            break;
        case '资本焦点':
            window.location.href = "/apis/report/capital.htm?id=" + reportId + '&type=' + _id
            break;
        case '市场焦点':
            window.location.href = "/apis/report/bazaar.htm?id=" + reportId + '&type=' + _id
            break;
        case '舆论焦点':
            window.location.href = "/apis/report/consensus.htm?id=" + reportId + '&type=' + _id
            break;
        case '技术焦点':
            window.location.href = "/apis/report/science.htm?id=" + reportId + '&type=' + _id
            break;
        case '未来焦点':
            window.location.href = "/apis/report/future.htm?id=" + reportId + '&type=' + _id
            break;
    }
});

//明星推荐
function company() {
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=recommend',
        type: 'GET',
        async: false,
        success: function(res) {
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
        success: function(res) {
            var strHtml = "";
            for (var i = 0; i < res.data.length; i++) {
                strHtml += "<li data-id=" + res.data[i].id + " data-name=" + res.data[i].name + " class='alldynamic'>"
                strHtml += "<a href='javascript:void();'><span class='mb10 namic'> <img src=" + res.data[i].logoClass + ">"
                strHtml += "</span><span class='ticolor'>" + res.data[i].name + "</span></a></li>"
            }
            $('.industry ul').html(strHtml)
        }
    })
}

$(".industry ul").on("click", "li", function() {
    var name = $(this).attr('data-name');
    var _id = $(this).attr('data-id');
    switch (name) {
        case '各地新闻':
            window.location.href = "/apis/report/worldnews.htm?id=" + reportId + '&type=' + _id
            break;
        case '合作动向':
            window.location.href = "/apis/report/collaborate.htm?id=" + reportId + '&type=' + _id
            break;
        case '企业动向':
            window.location.href = "/apis/report/tendency.htm?id=" + reportId + '&type=' + _id
            break;
        case '会议日程':
            window.location.href = "/apis/report/schedule.htm?id=" + reportId + '&type=' + _id
            break;
        case '排行报告':
            window.location.href = "/apis/report/rankingreport.htm?id=" + reportId + '&type=' + _id
            break;
        case '投融速递':
            window.location.href = "/apis/report/express.htm?id=" + reportId + '&type=' + _id
            break;
    }
})





// 优质企业
function superiorCompany() {
    $.ajax({
        url: '/apis/report/getHtmlData.do?id=' + reportId + '&type=industry',
        type: 'GET',
        async: false,
        success: function(res) {
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
            $('.mege').on("click", function(e) {
                $(".magimg").attr('style', "display:none")
                $(this).next().attr('style', "display:block")
                $(document).on('click', function() {
                    $('.magimg').hide()
                })
                e.stopPropagation();
            })
        }
    })

}


// 获取页面

$(function() {
    $('#dowebok').fullpage({
        anchors: ['page1', 'page2', 'page3', 'page4', 'page5', 'page6', 'page7', 'page8', 'page9', 'page10'],
        scrollOverflow: true,
        afterLoad: function(anchorLink, index) {
            if (index == 2) {
                $('.menu').delay(1000).removeClass('fadeOutUp').addClass('animated fadeInUp')
            }
            if (index == 5) {
                allFoucs()
                $('.foucs').delay(1000).removeClass('fadeOutUp').addClass('animated fadeInUp')
            }
            if (index == 6) {
                dynamic()
                $('.namic').delay(1000).removeClass('fadeOutUp').addClass('animated fadeInUp')
            }
            if (index == 7) {
                company()
            }
            if (index == 9) {
                superiorCompany()
            }
        },
        onLeave: function(index, nextIndex, direction) {
            if (index == '2') {
                $('.menu').delay(100).addClass('fadeOutUp')
            }
            if (index == 5) {
                $('.foucs').delay(100).addClass('fadeOutUp')
            }
            if (index == 6) {
                $('.namic').delay(100).addClass('fadeOutUp')
            }
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


$(document).on('click', '.cd-list>li>a', function() {
    var name = $(this).attr("href");
    name = name.split('#')[1];
    $.fn.fullpage.moveTo(name, 1);
});