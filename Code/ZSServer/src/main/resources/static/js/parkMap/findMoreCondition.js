$(function(){
	$("#indus").removeClass("active");
	$("#gardenMap").addClass("active");
	$("#report").removeClass("active");
	showGardenCondition('',pageNumber,pageSize);
});
var pageNumber = 0;
var pageSize = 10;
var options={
		"id":"page",//显示页码的元素
		"data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":10,//每页显示数据个数
	    "callBack":function(){}
	};
function showGardenCondition(area,pn,ps){
	var url = "";
	var req = {"pageNumber":pn,"pageSize":ps,"province":area};
	$.ajax({
		type:"post",
		url:"/apis/area/findGardensCondition.json",
		data:JSON.stringify(req),
		contentType:"application/json",
		success:function(res){
			if(res.success){
				var html = "";
				var arr = res.data.content;
				for(var i=0;i<arr.length;i++){
					html += '<div class="col-md-12 border-bottom">' +
								'<a class="scatter-blocks no-border" href="/summit/getEssayDetails.json?essayId='+arr[i].id+'">' +
									'<span class="scatter-title">'+arr[i].title+'</span></a>' +
									'<p class="scatter-content">'+arr[i].summary +'</p>' + 
									'<p class="scatter-lib">' +
										'<span>'+arr[i].park+'</span>' +
                                		'<span>'+arr[i].publishTime+'</span></p></div>';
				}
				$("#condition").html(html);
				if(res.data.totalPages>1){
					page.init(res.data.totalElements,res.data.number+1,options);
					$("#"+page.pageId +">li[class='pageItem']").on("click",function(){
						showGardenCondition('',$(this).attr("page-data")-1,pageSize);
	                });
				}else{
					$('#page').html("");
				}
			}
		}
	});
}