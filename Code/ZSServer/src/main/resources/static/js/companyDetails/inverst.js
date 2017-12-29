$(function(){
	showInverst();
});
function showInverst(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getInverst.json',
		success:function(res){
			if(res.success){
				var arr = res.data.data.result;
				var thead = '<tr><th class="text-left">被投资企业名称</th><th class="text-left">被投资法定代表人</th><th class="text-left">注册资本</th><th class="text-left">投资数额</th>'+
                            '<th class="text-left">投资占比</th><th class="text-left">注册时间</th><th class="text-left">状态</th></tr>';
				$("#inverst").prev().html(thead);
				if(arr.length > 10){
					arr.length = 10;
				}
				var html = '';
				for(var i=0;i<arr.length;i++){
					var dateStr = getFormatDate(new Date(arr[i].estiblishTime));
					if(arr[i].percent == null){
						arr[i].percent = '---';
					}
					html += '<tr><td>'+arr[i].name+'</td><td>'+arr[i].legalPersonName+'</td>' +
							'<td>'+arr[i].regCapital+'</td><td>'+arr[i].amount+'</td><td>'+arr[i].percent+'</td><td>'+dateStr+'</td><td>'+arr[i].regStatus+'</td></tr>';
				}
				$("#inverst").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#inverst").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
