/**
 * yindq
 */
$("#followItem").addClass("active");
$("#summitItem").addClass("active");
var industry = "全部";
var area = "全部";
var pageSize = 8;
var pageNumber = 0;
var options={
		"id":"page",//显示页码的元素
		"data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":8,//每页显示数据个数
	    "callBack":function(){}
	};
$(function(){
	$("#indus").addClass("active");
	Click(0,0);
});
function Click(a,b){
	if(a==1){
		industry = b;
	}else if(a==2){
		area= b;
	}else if(a== 3){
		sort = b;
	}
	var param={industry:industry,area:area,pageSize:pageSize,pageNumber:pageNumber}
	SummitInfo(param);
};
$(".search-box").on("click",".search-item-content>a",function(){
	$(this).addClass("active").siblings().removeClass("active");
	var _id = $(this).attr("id");
	var array = _id.split('-');
	var a = array[0];
	var b = array[1];
	Click(a,b);
});
function SummitInfo(param){
	$.ajax({
		type:'POST',
		url:'/apis/follow/listSummitMeetingList.json',
		asynyc:false,
		contentType:'application/json',
		data:JSON.stringify(param),
		success:function(res){
			if(res.success){
				if(res.data.totalNumber==0){
					$('#summit-list').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
					$('#page').html('');
			    }else{
			    	page.init(res.data.totalNumber,res.data.pageNumber,options);
					$("#"+page.pageId +">li[class='pageItem']").on("click",function(){
	            		var param={industry:industry,sort:sort,area:area,pageNumber:$(this).attr("page-data")-1,pageSize:pageSize};
	            		SummitInfo(param);
	                });
			    	$('#summit-list').html(show(res.data.dataList));
			    }
			}else{
        		new Alert({flag:false,text:res.message,timer:2000}).show();
        	}
		}
	});
};
function show(b){
	var arr=[];
	$.each(b,function(index,item){
		var imgsrc='/images/list_img.jpg';
		if(item.logo.length != 0){
			imgsrc=item.logo;
		}
		arr.push('<div class="col-md-3"><div class="img-list">'
		+'<a href="/apis/user/summitMeetingBus.html?articleLink='+item.articleLink+'";)>'		
		+'<img src="'+imgsrc+'"/>'
		+'<p class="title" title="'+item.title+'">'+item.title+'</p></a>'
		+'<div class="details">'
		+'<p class="address" title="'+item.address+'">'+item.address+'</p>'
		+'<p class="date-time" title="'+item.exhibitiontime+'">'+item.exhibitiontime+'</p>'
		+'<a href="javascript:void(0);" id="'+item.id+'">取消关注</a>'
		+'</div></div></div>'
		);
	});
	var inner = arr.join('');
	return inner;
};
$('#summit-list').on("click",".follow",function(){
	var aid = $(this).attr("id");
	var param={aid:aid};
	$.ajax({
		type:'GET',
		url:'/apis/follow/cancelSummitMeeting',
		asynyc:false,
		contentType:'application/json',
		data:param,
		success:function(res){
			if(result.success){
        		new Alert({flag:true,text:"操作成功"}).show();
    			setTimeout("window.location.reload()",2000);
        	}else{
        		new Alert({flag:false,text:result.message,timer:2000}).show();
        	}
		}
	});
});

