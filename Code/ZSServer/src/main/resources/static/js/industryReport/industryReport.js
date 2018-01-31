var initialType="season";
var initialYear="";
var options={
	"id":"page",//显示页码的元素
	"data":null,//显示数据
    "maxshowpageitem":5,//最多显示的页码个数
    "pagelistcount":8,//每页显示数据个数
    "callBack":function(){}
};
$(function(){
	$("#report").addClass("active");
//	new Loading({dom:document.getElementById("report_list"),text:"数据加载中，请勿刷新页面！"}).show();
	$.ajax({
        url: "/apis/report/getScreeningItem.json",
        success: function (response) {
            if(response.success){
                if(response.data.length==0){
                    new Alert({flag:'warning',text:"暂无数据！",timer:2000}).show();
                }else{
                    $('#year_item').html(item(response.data));
                }
            }else {
                new Alert({flag:false,text:response.message,timer:1500}).show();
            }
//            new Loading({dom:document.getElementById("report_list")}).hide();
            $(".search-item").on("click",function(){
				$(this).addClass("active").siblings().removeClass("active");
				var _value = $(this).html();
				if(_value=='按季度'){
					initialType='season';
				}else if(_value=='按月份'){
					initialType='month';
				}else{
					initialYear=_value;
				}
				ajsxPost(initialYear,initialType,0);
			});
        }
    });
})
function ajsxPost(y,t,n){
	$.ajax({
        type: 'post',
        url: "/apis/report/getExpertReport.json",
        async: false,
        contentType: 'application/json',
        data: JSON.stringify({year:y,type:t,pageNumber:n}),
        success: function (response) {
			console.log(response)
            if(response.message!=null){
            	new Alert({flag:false,text:response.message,timer:1500}).show();
            }else{
            	if(response.data.dataList.length==0){
            		$('#biuuu_city_list').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
               	 }else{
            		$('#biuuu_city_list').html(show(response.data.dataList));
            	}
            	if(response.data.totalPage>1){
	            	page.init(response.data.totalNumber,response.data.pageNumber,options);
	            	$("#"+page.pageId +">li[class='pageItem']").on("click",function(){
	            		ajsxPost(initialYear,initialType,$(this).attr("page-data")-1)
	                });
            	 }else{
            		 $('#page').html("");
            	 }
            }
        }
    });
}
function item(d){
	var inner="";
	$.each(d, function (index, year) {
		if(index==0){
			inner+='<a href="javascript:void(0);" class="search-item active">'+year+'</a>';
			initialYear=year;
			ajsxPost(initialYear,initialType,0);
		}else{
			inner+='<a href="javascript:void(0);" class="search-item">'+year+'</a>';
		}
    });
	return inner;
};
function show(d){
	console.log(d)
    var arr = []
    $.each(d, function(index, item){
      arr.push('<div class="col-md-3"><div class="report-list"><a href="/apis/report/reportDetails.html?fileId='+item.id+'" class="border-box"><img class="circle-img" src="/images/report.png" /></a>'+
      			'<a href="/apis/report/reportDetails.html?fileId='+item.id+'" class="report-title">'+item.name+'</a>'+
      			'<p class="report-date">'+item.data+'</p>'+
      			'<p class="report-tags"><i class="glyphicon glyphicon-tag"></i>'+
      			'<span class="tag">'+item.label+'</span></p></p></div></div>'
      		);
    });
    var inner=arr.join('');
    return inner;
}