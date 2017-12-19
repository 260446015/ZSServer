$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getCopyReg.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data.items;
				var thead = '<tr><th class="text-left">批准日期</th><th class="text-left">软件全称</th><th class="text-left">软件简称</th><th class="text-left">登记号</th>'+
							'<th class="text-left">分类号</th><th class="text-left">版本号</th></tr>';
				$("#copyReg").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var regtime = getFormatDate(new Date(arr[i].regtime));
					if(arr[i].simplename == null){
						arr[i].simplename = '---';
					}
					html += '<tr><td>'+regtime+'</td><td>'+arr[i].fullname+'</td>' +
							'<td>'+arr[i].simplename+'</td><td>'+arr[i].regnum+'</td>' +
							'<td>'+arr[i].catnum+'</td><td>'+arr[i].version+'</td>'+'</tr>'
				}
				$("#copyReg").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#copyReg").html(html);
			}
		}
	});
}