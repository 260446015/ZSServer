$(function(){
	showTeamMember();
});
function showTeamMember(){
	var req = {"cname":companyName,"pageNumber":1}
	$.ajax({
		url:'/apis/openeyes/getTeamMember.json',
		data:JSON.stringify(req),
		contentType:'application/json',
		type:'post',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var html = '';
				var arr = res.data.result.page.rows;
				for(var i=0;i<arr.length;i++){
					html += '<div class="col-md-12 border-bottom"><div class="layout-box"><div class="left-img">' +
							'<img src="'+arr[i].icon+'" width="160"></div><div class="right-list">' +
							'<a class="scatter-blocks no-border" href="javascript:void(0);"><span class="scatter-title">'+arr[i].title+'</span>' +
							'<span class="scatter-type ml10">'+arr[i].name+'</span></a>' +
							'<p class="person-introduce">'+arr[i].desc+'</p></div></div></div>'
				}
				$("#teamMember").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#teamMember").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
