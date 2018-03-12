$(function () {
    showBond();
    $(".more").on("click", function () {
        count++;
        var html = '';
        var len = count * 10;
        if (len <= arr.length) {
            for (var i = 0; i < len; i++) {
                var startdate = getFormatDate(new Date(arr[i].startdate));
                html += '<tr><td>' + startdate + '</td><td>' + arr[i].title + '</td>' +
                    '<td>' + arr[i].oriSalary + '</td><td>' + arr[i].experience + '</td>' +
                    '<td>' + arr[i].employerNumber + '</td><td>' + arr[i].city + '</td>' + '</tr>';
            }
            $("#employMents").html(html);
        } else {
            $(this).html("已无更多数据").attr("disabled", true).after('<button class="btn btn-link top">返回顶部</button>');
            $(".top").click(function () {
                var sc = $(window).scrollTop();
                $('body,html').animate({scrollTop: 0}, 500);
            });
        }
    })
});

function showBond() {
    var req = {"cname": companyName, "pageNumber": 1, "pageSize": 200}
    $.ajax({
        type: 'post',
        data: JSON.stringify(req),
        async: false,
        contentType: 'application/json',
        url: '/apis/openeyes/getEmployment.json',
        success: function (res) {
            if (res.success) {
                if (res.data.error_code == 0 || res.data.error_code == null) {
                    arr = res.data.result.items;
                    var thead = '<tr><th class="text-left">发布时间</th><th class="text-left">招聘职位</th><th class="text-left">薪资</th><th class="text-left">工作经验</th>' +
                        '<th class="text-left">招聘人数</th><th class="text-left">所在城市</th></tr>';

                    var html = '';
                    if (arr.length > 10) {
                        for (var i = 0; i < 10; i++) {
                            var startdate = getFormatDate(new Date(arr[i].startdate));
                            html += '<tr><td>' + startdate + '</td><td>' + arr[i].title + '</td>' +
                                '<td>' + arr[i].oriSalary + '</td><td>' + arr[i].experience + '</td>' +
                                '<td>' + arr[i].employerNumber + '</td><td>' + arr[i].city + '</td>' + '</tr>';
                        }
                        $("#employMents").prev().html(thead);
                        $("#employMents").html(html);
                    } else {
                        if (arr.length > 0) {
                            for (var i = 0; i < arr.length; i++) {
                                var startdate = getFormatDate(new Date(arr[i].startdate));
                                html += '<tr><td>' + startdate + '</td><td>' + arr[i].title + '</td>' +
                                    '<td>' + arr[i].oriSalary + '</td><td>' + arr[i].experience + '</td>' +
                                    '<td>' + arr[i].employerNumber + '</td><td>' + arr[i].city + '</td>' + '</tr>';
                            }
                            $(".more").html("暂无更多数据").attr("disabled", true).after('<button class="btn btn-link top">返回顶部</button>');
                            $(".top").click(function () {
                                var sc = $(window).scrollTop();
                                $('body,html').animate({scrollTop: 0}, 500);
                            });
                            $("#employMents").prev().html(thead);
                            $("#employMents").html(html);
                        }
                    }
                } else {
                    var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
                    $("#employMents").html(html);
                    window.setTimeout(goBack, 2000);
                }
            }
        }
    });
}