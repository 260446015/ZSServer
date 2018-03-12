$(function () {
    showBond();
    $(".more").on("click", function () {
        count++;
        var html = '';
        var len = count * 10;
        if (len <= arr.length) {
            for (var i = 0; i < len; i++) {
                var publishTime = getFormatDate(new Date(parseInt(arr[i].publishTime)));
                html += '<tr><input type="hidden" value="' + arr[i].id + '"/><td>' + publishTime + '</td><td>' + arr[i].bondName + '</td>' +
                    '<td>' + arr[i].bondNum + '</td><td>' + arr[i].bondType + '</td>' +
                    '<td>' + arr[i].issuedPrice + '</td>' + '</tr>';
            }
            $("#bond").html(html);
        } else {
            $(this).html("已无更多数据").attr("disabled", true).after('<button class="btn btn-link top">返回顶部</button>');
            $(".top").click(function () {
                var sc = $(window).scrollTop();
                $('body,html').animate({scrollTop: 0}, 500);
            });
        }
    })
});
var arr;

function showBond() {
    var req = {"cname": companyName, "pageNumber": 1, "pageSize": 200}
    $.ajax({
        type: 'post',
        data: JSON.stringify(req),
        async: false,
        contentType: 'application/json',
        url: '/apis/openeyes/getBond.json',
        success: function (res) {
            if (res.success) {
                if(res.data.error_code == 0 || res.data.error_code == null) {
                    arr = res.data.result.items;
                    var thead = '<tr><th class="text-left">发行日期</th><th class="text-left">债券名称</th><th class="text-left">债券代码</th>' +
                        '<th class="text-left">债券类型</th><th class="text-left">最新评级</th></tr>'

                    var html = '';
                    if (arr.length > 10) {
                        for (var i = 0; i < 10; i++) {
                            var publishTime = getFormatDate(new Date(parseInt(arr[i].publishTime)));
                            html += '<tr><input type="hidden" value="' + arr[i].id + '"/><td>' + publishTime + '</td><td>' + arr[i].bondName + '</td>' +
                                '<td>' + arr[i].bondNum + '</td><td>' + arr[i].bondType + '</td>' +
                                '<td>' + arr[i].issuedPrice + '</td>' + '</tr>';
                        }
                        $("#bond").prev().html(thead);
                        $("#bond").html(html);
                    } else {
                        if (arr.length > 0) {
                            for (var i = 0; i < arr.length; i++) {
                                var publishTime = getFormatDate(new Date(parseInt(arr[i].publishTime)));
                                html += '<tr><input type="hidden" value="' + arr[i].id + '"/><td>' + publishTime + '</td><td>' + arr[i].bondName + '</td>' +
                                    '<td>' + arr[i].bondNum + '</td><td>' + arr[i].bondType + '</td>' +
                                    '<td>' + arr[i].issuedPrice + '</td>' + '</tr>';
                            }
                            $(".more").html("暂无更多数据").attr("disabled", true).after('<button class="btn btn-link top">返回顶部</button>');
                            $(".top").click(function () {
                                var sc = $(window).scrollTop();
                                $('body,html').animate({scrollTop: 0}, 500);
                            });
                            $("#bond").prev().html(thead);
                            $("#bond").html(html);
                        }
                    }
                }else{
                    var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
                    $("#bond").html(html);
                    window.setTimeout(goBack, 2000);
                }
            }
        }
    });
}
