$(function(){
	showBranch();
});
function showBranch(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getBranch.json',
		success:function(res){
			if(res.success){
				var arr = res.data.data.result;
				var thead = '<tr><th class="text-left">企业名称</th><th class="text-left">法定代表人</th><th class="text-left">状态</th><th class="text-left">注册时间</th></tr>';
				$("#companyList").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var dateStr = getFormatDate(new Date(arr[i].estiblishTime));
					if(arr[i].legalPersonName == null){
						arr[i].legalPersonName = '---';
					}
					if(arr[i].regStatus == null){
						arr[i].regStatus = '---';
					}
					html += '<tr><td>'+arr[i].name+'</td><td>'+arr[i].legalPersonName+'</td>' +
							'<td>'+arr[i].regStatus+'</td><td>'+dateStr+'</td></tr>'
				}
				$("#companyList").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#companyList").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
