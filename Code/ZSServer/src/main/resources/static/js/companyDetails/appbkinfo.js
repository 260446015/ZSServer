$(function(){
	showAppbkinfo();
});
function showAppbkinfo(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getAppbkInfo.json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.data.items;
				var thead = '<tr><th class="text-left">图标</th><th class="text-left">产品名称</th><th class="text-left">产品简称</th>' +
                            '<th class="text-left">产品分类</th><th class="text-left">领域</th></tr>';
				$("#appbkinfo").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					html += '<tr><td><img src="'+arr[i].icon+'" width="120px" height="50px"/></td><td>'+arr[i].name+'</td>' +
							'<td>'+arr[i].filterName+'</td><td>'+arr[i].type+'</td>' +
							'<td>'+arr[i].classes+'</td></tr>';
				}
				$("#appbkinfo").html(html);
			}else{
				 var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#appbkinfo").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}