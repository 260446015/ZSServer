$(function(){
	showLawSuit();
	$(".more").on("click",function(){
		count ++;
		var html = '';
		var len = count * 10;
		if(len <= arr.length){
			for(var i=0;i<len;i++){
				var submittime = getFormatDate(new Date(arr[i].submittime));
				var startTime = getFormatDate(new Date(arr[i].startTime));
				html += '<tr><td>'+submittime+'</td><td>'+arr[i].caseno+'</td>' +
						'<td>'+arr[i].title+'</td><td>'+arr[i].doctype+'</td>' +
						'</tr>';
			}
			$("#lawSuit").html(html);
		}else{
			$(this).html("已无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
			$(".top").click(function(){
				var sc=$(window).scrollTop();
				$('body,html').animate({scrollTop:0},500);
			});
		}
	})
});
function showLawSuit(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		async:false,
		contentType:'application/json',
		url:'/apis/openeyes/getLawsuit.json',
		success:function(res){
			if(res.success){
                if(res.data.error_code == 0 || res.data.error_code == null) {
                    arr = res.data.result.items;
                    var thead = '<tr><th class="text-left">日期</th><th class="text-left">案件号</th><th class="text-left">裁判文书</th><th class="text-left">案件类型</th></tr>';
                    $("#lawSuit").prev().html(thead);
                    var html = '';
                    if (arr.length > 10) {
                        for (var i = 0; i < 10; i++) {
                            var submittime = getFormatDate(new Date(arr[i].submittime));
                            var startTime = getFormatDate(new Date(arr[i].startTime));
                            html += '<tr><td>' + submittime + '</td><td>' + arr[i].caseno + '</td>' +
                                '<td>' + arr[i].title + '</td><td>' + arr[i].doctype + '</td>' +
                                '</tr>';
                        }
                    } else {
                        for (var i = 0; i < arr.length; i++) {
                            var submittime = getFormatDate(new Date(arr[i].submittime));
                            var startTime = getFormatDate(new Date(arr[i].startTime));
                            html += '<tr><td>' + submittime + '</td><td>' + arr[i].caseno + '</td>' +
                                '<td>' + arr[i].title + '</td><td>' + arr[i].doctype + '</td>' +
                                '</tr>';
                        }
                        $(".more").html("暂无更多数据").attr("disabled", true).after('<button class="btn btn-link top">返回顶部</button>');
                        $(".top").click(function () {
                            var sc = $(window).scrollTop();
                            $('body,html').animate({scrollTop: 0}, 500);
                        });
                    }

                    $("#lawSuit").html(html);
                }else{
                    var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
                    $("#lawSuit").html(html);
                    window.setTimeout(goBack, 2000);
                }
			}
		}
	});
}