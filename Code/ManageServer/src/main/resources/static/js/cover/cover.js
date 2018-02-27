$(function () {
    coverTime();
    keyWord();
    industryChain();
    allFoucs();
    company();
    dynamic();
    superiorCompany();

    //获取时间
    function coverTime() {
        $.ajax({
            url: '/apis/report/getHtmlData.do?id=5',
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
            url: '/apis/report/getHtmlData.do?id=5&type=keyWord',
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
                node.symbolSize = 3;
                node.value = node.symbolSize;
                node.category = node.attributes.modularity_class;
                // Use random x, y
                node.x = node.y = null;
                node.draggable = true;
            });
            option = {
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
                        roam: true,
                        label: {
                            normal: {
                                position: 'right'
                            }
                        },
                        force: {
                            repulsion: 100
                        }
                    }
                ]
            };
            myChart.setOption(option);
        }, 'xml');
    }

    //产业高亮点
    function industryChain() {
        $.ajax({
            url: '/apis/report/getHtmlData.do?id=5&type=chain',
            type: 'GET',
            async: false,
            success: function (res) {
                var strHtml = "";
                for (var i = 0; i < res.data.length; i++) {
                    strHtml += "<li>" + res.data[i].text + "</li>";
                }
                $('.industrybottom ul').html(strHtml)
            }
        })
    }


    //焦点
    function allFoucs() {
        $.ajax({
            url: '/apis/report/getHtmlData.do?id=5&type=focus',
            type: 'GET',
            async: false,
            success: function (res) {
                var strHtml = "";
                for (var i = 0; i < res.data.length; i++) {
                    strHtml += "<li><a href=" + res.data[i].url + "?id=5&type=" + res.data[i].id + ">"
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
            url: '/apis/report/getHtmlData.do?id=5&type=' + _id,
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
                            strHtml += "<div class='leftline'><p><span class='leftdot'></span><small class='textcolor'>" + res.data[i].keyWord
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
                            strHtml += "<div class='leftline'><p><span class='leftdot'></span><small class='textcolor'>" + res.data[i].keyWord
                                + "</small></p>";
                            strHtml += "<p>" + res.data[i].text + "</p></div>"
                        }
                    }
                    boxdom.html(strHtml)
                    policyhtml.append(boxdom)
                    $('.page5').after(policyhtml);
                } else if (_name == '技术焦点') {
                    var policyhtml = $("<div class='page page15'></div>");
                    var appendHeader = $("<header><div class='center'><h1>" + _name + "</h1></div></header>")
                    policyhtml.append(appendHeader);
                    var container = $("<div class='container'></div>");
                    var strHtml = "";
                    for (var i = 0; i < res.data.length; i++) {
                        if (i % 2) {
                            strHtml += "<div class='rightborder'><p class='ptext'><span class='leftdot'></span><small class='textcolor'>" + res.data[i].keyWord
                                + "</small></p>";
                            strHtml += "<p class='ptext'>" + res.data[i].text + "</p></div>"
                        } else {
                            strHtml += "<div class='leftborder'><p><span class='leftdot'></span><small class='textcolor'>" + res.data[i].keyWord
                                + "</small></p>";
                            strHtml += "<p>" + res.data[i].text + "</p></div>"
                        }
                    }
                    container.html(strHtml)
                    policyhtml.append(container)
                    $('.page5').after(policyhtml);
                } else if (_name == '未来焦点') {

                    var policyhtml = $("<div class='page page16'></div>");
                    var appendHeader = $("<header><div class='center'><h1>" + _name + "</h1></div></header>")
                    policyhtml.append(appendHeader);
                    var container = $("<div class='container'></div>");
                    var strHtml = "";
                    for (var i = 0; i < res.data.length; i++) {
                        if (i % 2) {
                            strHtml += "<div class='rightborder'><p class='ptext'><span class='leftdot'></span><small class='textcolor'>" + res.data[i].keyWord
                                + "</small></p>";
                            strHtml += "<p class='ptext'>" + res.data[i].text + "</p></div>"
                        } else {
                            strHtml += "<div class='leftborder'><p><span class='leftdot'></span><small class='textcolor'>" + res.data[i].keyWord
                                + "</small></p>";
                            strHtml += "<p>" + res.data[i].text + "</p></div>"
                        }
                    }
                    container.html(strHtml)
                    policyhtml.append(container)
                    $('.page5').after(policyhtml);
                }
            }
        })
    }


    //明星推荐
    function company() {
        $.ajax({
            url: '/apis/report/getHtmlData.do?id=5&type=recommend',
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
                manHtml += "<li class='text'>" + str.name + "</li>";
                manHtml += "<li class='text'>" + str.identity + "</li>";
                manHtml += "<li>上榜理由：" + str.reason + "</li>"
                manHtml += "</ul>";
                $('.starmanimg').html(manHtml)
            }
        })
    }


    //行业动态
    function dynamic() {
        $.ajax({
            url: '/apis/report/getHtmlData.do?id=5&type=dynamic',
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
            url: '/apis/report/getHtmlData.do?id=5&type=' + id,
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
                            strHtml += "<div class='leftline'><p><span class='leftdot'></span><small class='textcolor'>" + res.data[i].keyWord
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
                    var container = $("<div class='container'></div>");
                    var boxdom = $("<div class='calendar'></div>");
                    boxdom.append(container);
                    policyhtml.append(boxdom)
                    $('.page6').after(policyhtml);
                } else if (name == '排行报告') {
                    var policyhtml = $("<div class='page page21'></div>");
                    var appendHeader = $("<header><div class='center'><h1>" + name + "</h1></div></header>")
                    policyhtml.append(appendHeader);
                    var container = $("<div class='container'></div>");
                    var boxdom = $("<div class='allreport'></div>");
                    boxdom.append(container);
                    var strHtml = "";
                    for (var i = 0; i < res.data.length; i++) {
                        strHtml += "<div><ul><li><p>" + res.data[i].keyWord + "</p><p class='read'>阅读</p></li>"
                        strHtml += "<li>" + res.data[i].text + "</li><li>报告发布:" + res.data[i].people + "</li></ul></div>"
                    }
                    boxdom.html(strHtml);
                    policyhtml.append(boxdom)
                    $('.page6').after(policyhtml)
                } else if (name == '投融速递') {
                    var policyhtml = $("<div class='page page22'></div>");
                    var appendHeader = $("<header><div class='center'><h1>" + name + "</h1></div></header>")
                    policyhtml.append(appendHeader);
                    var container = $("<div class='container'></div>");
                    policyhtml.append(container)
                    $('.page6').after(policyhtml);
                }
            }
        })
    }

    function superiorCompany() {
        $.ajax({
            url: '/apis/report/getHtmlData.do?id=5&type=industry',
            type: 'GET',
            async: false,
            success: function (res) {
                var strHtml1 = "";
                strHtml1 += " <div><h2>龙头企业</h2><ul>"
                strHtml1 += "<li><span>企业名称</span><span>企业产业</span></li>";
                for (var i = 0; i < res.data.faucet.length; i++) {
                    strHtml1 += "<li><span>" + res.data.faucet[i].company + "</span>"
                    strHtml1 += "<span>" + res.data.faucet[i].text + "</span></li>";
                }
                strHtml1 += "</ul></div>"
                var strHtml2 = "";
                strHtml2 += " <div><h2>成长企业</h2><ul>"
                strHtml2 += "<li><span>企业名称</span><span>企业产业</span></li>";
                for (var i = 0; i < res.data.growth.length; i++) {
                    strHtml2 += "<li><span>" + res.data.growth[i].company + "</span>"
                    strHtml2 += "<span>" + res.data.growth[i].text + "</span></li>";
                }
                strHtml2 += "</ul></div>"
                var strHtml3 = "";
                strHtml3 += " <div><h2>潜力企业</h2><ul>"
                strHtml3 += "<li><span>企业名称</span><span>企业产业</span></li>";
                for (var i = 0; i < res.data.potential.length; i++) {
                    strHtml3 += "<li><span>" + res.data.potential[i].company + "</span>"
                    strHtml3 += "<span>" + res.data.potential[i].text + "</span></li>";
                }
                strHtml3 += "</ul></div>"
                strHtml = strHtml1 + strHtml2 + strHtml3;
                $('.company').html(strHtml)
            }
        })
    }

    //页面上下滑动
    var newpage = 0;
     var num = $('.skippage .page');
     $('.skippage').swipe({
         swipe:function(event, direction, distance, duration, fingerCount) {
             if(direction == 'up'){
                 newpage = newpage + 1;
             }else if(direction == 'down'){
                newpage = newpage - 1;
             }
             if(newpage > num) {
                 newpage = num;
             }
             if( newpage < 0){
                 newpage = 0;
             }
             $('.skippage').animate({"top":newpage* -100 + "%"})
         }
     })
    
     //swiper 
     var swiper = new Swiper('.swiper-container', {
        spaceBetween: 30,
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
    });


})