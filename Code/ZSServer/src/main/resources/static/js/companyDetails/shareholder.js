$(function(){
	showShareHolder();
	$(".more").on("click",function(){
		count ++;
		var html = '';
		var len = count * 10;
		if(len <= arr.length){
			for(var i=0;i<len;i++){
				html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+arr[i].name+'</td><td>'+arr[i].holdingNum+'</td>' +
				'<td>'+arr[i].holdingChange+'</td><td>'+arr[i].tenPercent+'</td>' +
				'<td>'+arr[i].actual+'</td><td>'+arr[i].shareType+'</td>'+'</tr>';
			}
			$("#shareholder").html(html);
		}else{
			$(this).html("已无更多数据").attr("disabled",true).after('<button class="btn btn-link top">返回顶部</button>');
			$(".top").click(function(){
				var sc=$(window).scrollTop();
				$('body,html').animate({scrollTop:0},500);
			});
		}
	})
});
function showShareHolder(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		async:false,
		contentType:'application/json',
		url:'/apis/openeyes/getShareholder.json',
		success:function(res){
			if(res.success){
                if(res.data.error_code == 0 || res.data.error_code == null) {
                    arr = res.data.result.holderList;
                    var thead = '<tr><th class="text-left">机构或基金</th><th class="text-left">持有数量</th><th class="text-left">持股变化 （万股）</th><th class="text-left">占股本比例（%）</th>' +
                        '<th class="text-left">实际增减持（%）</th><th class="text-left">股份类型</th></tr>';
                    $("#shareholder").prev().html(thead);
                    var html = '';
                    if (arr.length > 10) {
                        for (var i = 0; i < 10; i++) {
                            html += '<tr><input type="hidden" value="' + arr[i].id + '"/><td>' + arr[i].name + '</td><td>' + arr[i].holdingNum + '</td>' +
                                '<td>' + arr[i].holdingChange + '</td><td>' + arr[i].tenPercent + '</td>' +
                                '<td>' + arr[i].actual + '</td><td>' + arr[i].shareType + '</td>' + '</tr>';
                        }
                    } else {
                        for (var i = 0; i < arr.length; i++) {
                            html += '<tr><input type="hidden" value="' + arr[i].id + '"/><td>' + arr[i].name + '</td><td>' + arr[i].holdingNum + '</td>' +
                                '<td>' + arr[i].holdingChange + '</td><td>' + arr[i].tenPercent + '</td>' +
                                '<td>' + arr[i].actual + '</td><td>' + arr[i].shareType + '</td>' + '</tr>';
                        }
                        $(".more").html("暂无更多数据").attr("disabled", true).after('<button class="btn btn-link top">返回顶部</button>');
                        $(".top").click(function () {
                            var sc = $(window).scrollTop();
                            $('body,html').animate({scrollTop: 0}, 500);
                        });
                    }

                    $("#shareholder").html(html);
                }else{
                    var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
                    $("#shareholder").html(html);
                    window.setTimeout(goBack, 2000);
                }
			}
		}
	});
}
