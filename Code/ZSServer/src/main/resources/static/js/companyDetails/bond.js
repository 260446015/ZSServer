$(function(){
	showBond();
});
var arr;
function showBond(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getBond.json',
		success:function(res){
			if(res.success){
				arr = res.data.data.bondList;
				var thead = '<tr><th class="text-left">发行日期</th><th class="text-left">债券名称</th><th class="text-left">债券代码</th>' +
                            '<th class="text-left">债券类型</th><th class="text-left">最新评级</th></tr>'
				$("#bond").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var publishTime = getFormatDate(new Date(parseInt(arr[i].publishTime)));
					html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+publishTime+'</td><td>'+arr[i].bondName+'</td>' +
							'<td>'+arr[i].bondNum+'</td><td>'+arr[i].bondType+'</td>' +
							'<td>'+arr[i].issuedPrice+'</td>'+'</tr>';
				}
				$("#bond").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#bond").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
