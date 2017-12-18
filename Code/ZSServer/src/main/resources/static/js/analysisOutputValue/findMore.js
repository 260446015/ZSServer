var park="";
var dimension="";
var pageNumber = 0;
var pageSize = 10;
var options={
		"id":"page",//显示页码的元素
		"data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":10,//每页显示数据个数
	    "callBack":function(){}
	};

function showMore(a,b,p){
	park=a;
	dimension=b;
	$.ajax({
		type:'post',
		url:'/apis/area/getInformationPush.json',
		data:JSON.stringify({park:a,dimension:b,pageNumber:p,pageSize:10}),
		contentType:'application/json',
		success:function(res){
			if(res.success){
				var html = '';
				var arr = res.data.dataList;
				for(var i=0;i<arr.length;i++){
					html += '<div class="col-md-12 border-bottom">' +
								'<a class="scatter-blocks no-border" target="_blank" href="/summit/getEssayDetails.json?essayId='+arr[i].id+'">' +
									'<span class="scatter-title">'+arr[i].title+'</span></a>' +
									'<p class="scatter-content">'+arr[i].summary +'</p>' + 
									'<p class="scatter-lib">' +
										'<span>'+arr[i].area+'</span>' +
                                		'<span>'+arr[i].publishTime+'</span></p></div>';
				}
				$("#findMore").html(html);
				if(res.data.totalPage>1){
					page.init(res.data.totalNumber,res.data.pageNumber,options);
					$("#"+page.pageId +">li[class='pageItem']").on("click",function(){
						showMore(park,dimension,$(this).attr("page-data")-1);
	                });
				}else{
					$('#page').html("");
				}
			}
		}
	});
}
