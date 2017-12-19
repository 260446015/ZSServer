$(function(){
	showSeniorExecutive();
});
var arr;
function showSeniorExecutive(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getSeniorExecutive.json',
		success:function(res){
			if(res.success){
				arr = res.data.data.dataList;
				var thead = '<tr><th class="text-left">姓名</th><th class="text-left">职务</th><th class="text-left">持股数</th><th class="text-left">年龄</th></tr>';
				$("#seniorExecutive").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					html += '<tr><input type="hidden" value="'+arr[i].id+'"/><td>'+arr[i].name+'</td><td>'+arr[i].position+'</td>' +
							'<td>'+arr[i].numberOfShares+'</td><td>'+arr[i].age+'</td></tr>';
				}
				$("#seniorExecutive").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#seniorExecutive").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}