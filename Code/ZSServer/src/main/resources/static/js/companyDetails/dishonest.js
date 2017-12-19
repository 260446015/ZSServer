$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getDishonest.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.result.items;
				var thead = '<tr><th class="text-left">立案日期</th><th class="text-left">案号</th><th class="text-left">执行法院</th><th class="text-left">履行状态</th>'+
                            '<th class="text-left">执行依据文号</th></tr>'
				$("#dishonest").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var regdate = getFormatDate(new Date(arr[i].regdate));
					html += '<tr><td>'+regdate+'</td><td>'+arr[i].gistid+'</td>' +
							'<td>'+arr[i].courtname+'</td><td>'+arr[i].performance+'</td>' +
							'<td>'+arr[i].gistid+'</td>'+'</tr>'
				}
				$("#dishonest").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#dishonest").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}