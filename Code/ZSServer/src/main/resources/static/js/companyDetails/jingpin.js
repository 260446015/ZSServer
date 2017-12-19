$(function(){
	showJinpin();
});
function showJinpin(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getJingPin.json',
		async:false,
		success:function(res){
			if(res.success){
				var arr = res.data.result.page.rows;
				var thead = '<tr><th class="text-left">企业名称</th><th class="text-left">行业</th><th class="text-left">竞品名</th><th class="text-left">地区</th><th class="text-left">产品</th>'+
                    '<th class="text-left">轮次</th><th class="text-left">投资时间</th><th class="text-left">估值</th><th class="text-left">业务范围</th></tr>';
				$("#jingpin").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var dateStr = getFormatDate(new Date(arr[i].setupDate));
					if(arr[i].companyName == null){
						arr[i].companyName = '---';
					}
					if(arr[i].hangye == null){
						arr[i].hangye = '---';
					}
					if(arr[i].location == null){
						arr[i].location = '---';
					}
					if(arr[i].round == null){
						arr[i].round = '---';
					}
					if(arr[i].value == null){
						arr[i].value = '---';
					}
					if(arr[i].yewu == null){
						arr[i].yewu = '---';
					}
					html += '<tr><td>'+arr[i].companyName+'</td><td>'+arr[i].hangye+'</td>' +
							'<td>'+arr[i].jingpinProduct+'</td><td>'+arr[i].location+'</td>' +
							'<td>'+arr[i].product+'</td><td>'+arr[i].round+'</td>' +
							'<td>'+dateStr+'</td><td>'+arr[i].value+'</td><td>'+arr[i].yewu+'</td></tr>'
				}
				$("#jingpin").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#jingpin").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
