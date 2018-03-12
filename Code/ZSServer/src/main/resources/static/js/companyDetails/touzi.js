$(function(){
	showTouzi();
	$(".more").on("click",function(){
		count ++;
		var html = '';
		var len = count * 10;
		if(len <= arr.length){
			for(var i=0;i<len;i++){
				var dateStr = getFormatDate(new Date(arr[i].tzdate));
				html += '<tr><td>'+dateStr+'</td><td>'+arr[i].lunci+'</td>' +
						'<td>'+arr[i].money+'</td><td>'+arr[i].company+'</td>' +
						'<td>'+arr[i].product+'</td><td>'+arr[i].location+'</td>'+
						'<td>'+arr[i].hangye1+'</td><td>'+arr[i].yewu+'</td>'+'</tr>';
			}
			$("#touzi").html(html);
		}else{
			$(this).html("已无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
			$(".top").click(function(){
				var sc=$(window).scrollTop();
				$('body,html').animate({scrollTop:0},500);
			});
		}
	})
});
function showTouzi(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		async:false,
		contentType:'application/json',
		url:'/apis/openeyes/getTouZi.json',
		success:function(res){
			if(res.success){
                if(res.data.error_code == 0 || res.data.error_code == null) {
                    arr = res.data.result.items;
                    var thead = '<tr><th class="text-left">时间</th><th class="text-left">轮次</th><th class="text-left">金额</th><th class="text-left">投资方</th>' +
                        '<th class="text-left">产品</th><th class="text-left">地区</th><th class="text-left">行业</th><th class="text-left">业务</th></tr>';
                    $("#touzi").prev().html(thead);
                    var html = '';
                    if (arr.length > 10) {
                        for (var i = 0; i < 10; i++) {
                            var dateStr = getFormatDate(new Date(arr[i].tzdate));
                            html += '<tr><td>' + dateStr + '</td><td>' + arr[i].lunci + '</td>' +
                                '<td>' + arr[i].money + '</td><td>' + arr[i].company + '</td>' +
                                '<td>' + arr[i].product + '</td><td>' + arr[i].location + '</td>' +
                                '<td>' + arr[i].hangye1 + '</td><td>' + arr[i].yewu + '</td>' + '</tr>';
                        }
                    } else {
                        for (var i = 0; i < arr.length; i++) {
                            var dateStr = getFormatDate(new Date(arr[i].tzdate));
                            html += '<tr><td>' + dateStr + '</td><td>' + arr[i].lunci + '</td>' +
                                '<td>' + arr[i].money + '</td><td>' + arr[i].company + '</td>' +
                                '<td>' + arr[i].product + '</td><td>' + arr[i].location + '</td>' +
                                '<td>' + arr[i].hangye1 + '</td><td>' + arr[i].yewu + '</td>' + '</tr>';
                        }
                        $(".more").html("暂无更多数据").attr("disabled", true).after('<button class="btn btn-link top">返回顶部</button>');
                        $(".top").click(function () {
                            var sc = $(window).scrollTop();
                            $('body,html').animate({scrollTop: 0}, 500);
                        });
                    }

                    $("#touzi").html(html);
                }else{
                    var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
                    $("#touzi").html(html);
                    window.setTimeout(goBack,2000);
				}
			}
		}
	});
}