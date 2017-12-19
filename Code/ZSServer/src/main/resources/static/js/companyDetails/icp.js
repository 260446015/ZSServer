$(function(){
	showBond();
});
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getIcp.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data;
				var thead = '<tr><th class="text-left">审核时间</th><th class="text-left">网站名称</th><th class="text-left">网站首页</th><th class="text-left">域名</th>'+
                            '<th class="text-left">备案号</th><th class="text-left">状态</th><th class="text-left">单位性质</th></tr>';
				$("#icp").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var web;
					if(arr[i].webSite != null){
						web = arr[i].webSite.replace('["','').replace('"]','').split(';')[0];
					}
					html += '<tr><td>'+arr[i].examineDate+'</td><td>'+arr[i].webName+'</td>' +
							'<td>'+web+'</td><td>'+arr[i].ym+'</td>' +
							'<td>'+arr[i].liscense+'</td><td>正常</td><td>'+arr[i].companyType+'</td>'+'</tr>'
				}
				$("#icp").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#icp").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}