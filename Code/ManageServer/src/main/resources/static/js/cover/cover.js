$(function () {
    coverTime();
    keyWord();
    industryChain();
    allFoucs();
    company();
    superiorCompany();
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

    function keyWord() {
        $.ajax({
            url: '/apis/report/getHtmlData.do?id=5&type=keyWord',
            type: 'GET',
            async: false,
            success: function (res) {
                // console.log(res)
                var strHtml = "";
                for (var i = 0; i < res.data.length; i++) {
                    strHtml += "<div class='popup'>"
                    if (i % 2) {
                        strHtml += "<div class='leftborder text'>" + res.data[i].text + "</div>"
                        strHtml += "<div class='leftwire'></div>"
                    } else {
                        strHtml += "<div class='leftwire'></div>"
                        strHtml += "<div class='rightborder text'>" + res.data[i].text + "</div>"
                    }
                    strHtml += "</div>"
                }
                $("#keymonth").html(strHtml)
            }
        })
    };

    function industryChain() {
        $.ajax({
            url: '/apis/report/getHtmlData.do?id=5&type=chain',
            type: 'GET',
            async: false,
            success: function (res) {
                // console.log(res)
                var strHtml = "";
                for (var i = 0; i < res.data.length; i++) {
                    strHtml += "<li>" + res.data[i].text + "</li>";
                }
                $('.industrybottom ul').html(strHtml)
            }
        })
    }

    function allFoucs() {
        $.ajax({
            url: '/apis/report/getHtmlData.do?id=5&type=focus',
            type: 'GET',
            async: false,
            success: function (res) {
                // console.log(res)
            }
        })
    }

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
                    console.log(res.data.potential[i].company)
                    strHtml3 += "<li><span>" + res.data.potential[i].company + "</span>"
                    strHtml3 += "<span>" + res.data.potential[i].text + "</span></li>";
                }
                strHtml3 += "</ul></div>"
                strHtml = strHtml1 + strHtml2 + strHtml3;
                $('.company').html(strHtml)
            }
        })
    }




})