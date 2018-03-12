$(function(){
	showChangeInfo();
	$(".more").on("click",function(){
		count ++;
		var html = '';
		var len = count * 10;
		if(len <= arr.length){
			for(var i=0;i<len;i++){
				html += '<tr><td class="tw160">'+arr[i].changeTime+'</td><td class="tw180">'+arr[i].changeItem+'</td>' +
				'<td>'+arr[i].contentBefore+'</td><td>'+arr[i].contentAfter+'</td></tr>';
			}
			$("#changeInfo").html(html);
		}else{
			$(this).html("已无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
			$(".top").click(function(){
				var sc=$(window).scrollTop();
				$('body,html').animate({scrollTop:0},500);
			});
		}
	})
});
function showChangeInfo(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		async:false,
		contentType:'application/json',
		url:'/apis/openeyes/getChangeInfo.json',
		success:function(res){
			if(res.success){
                if(res.data.error_code == 0 || res.data.error_code == null) {
                    arr = res.data.result.items;
                    var thead = '<tr><th class="text-left">变更时间</th><th class="text-left">变更项目</th><th class="text-left">变更前</th><th class="text-left">变更后</th></tr>';
                    $("#changeInfo").prev().html(thead);
                    var html = '';
                    if (arr.length > 10) {
                        for (var i = 0; i < 10; i++) {
                            html += '<tr><td class="tw160">' + arr[i].changeTime + '</td><td class="tw180">' + arr[i].changeItem + '</td>' +
                                '<td>' + arr[i].contentBefore + '</td><td>' + arr[i].contentAfter + '</td></tr>';
                        }
                    } else {
                        for (var i = 0; i < arr.length; i++) {
                            html += '<tr><td class="tw160">' + arr[i].changeTime + '</td><td class="tw180">' + arr[i].changeItem + '</td>' +
                                '<td>' + arr[i].contentBefore + '</td><td>' + arr[i].contentAfter + '</td></tr>';
                        }
                        $(".more").html("暂无更多数据").attr("disabled", true).after('<button class="btn btn-link top">返回顶部</button>');
                        $(".top").click(function () {
                            var sc = $(window).scrollTop();
                            $('body,html').animate({scrollTop: 0}, 500);
                        });
                    }
                    $("#changeInfo").html(html);
                }else{
                    var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
                    $("#changeInfo").html(html);
                    window.setTimeout(goBack, 2000);
                }
			}
		}
	});
}