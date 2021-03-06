/**
 * Created by zhangxin on 2017/11/17.
 */
var industry = "全部";
var area = "全部";
var sort = "按时间";
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
		updateAreaInfo(industry);
	}else if(a==2){
		area= b;
		$('#2-'+area).addClass("active").siblings().removeClass("active");
	}else if(a== 3){
		sort = b;
	}
	if(industry=="全部"){
		updateAreaInfo(industry);
	}
	var param={industry:industry,area:area,sort:sort,pageSize:pageSize,pageNumber:pageNumber}
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
		url:'/summit/list',
		asynyc:false,
		contentType:'application/json',
		data:JSON.stringify(param),
		success:function(res){
			if( res.message != null){
				new Alert({flag:false,text:res.message,timer:2000}).show();
			}else{
				
				$('#summit-list').html(show(res.data.dataList));
				if(res.data.totalPage>1){
					page.init(res.data.totalNumber,res.data.pageNumber,options);
					$("#"+page.pageId +">li[class='pageItem']").on("click",function(){
	            		var param={industry:industry,sort:sort,area:area,pageNumber:$(this).attr("page-data")-1,pageSize:pageSize};
	            		SummitInfo(param);
	                });
				}else{
					$('#page').html("");
				}
			}
		}
	});
};
function show(b){
	var arr=[];
	$.each(b,function(index,item){
		var imgsrc='/images/list_img.jpg';
		console.log(item.logo);
		if(item.logo != null&&item.logo.length !=0){
			imgsrc=item.logo;
		}
		arr.push('<div class="col-md-3"><div class="img-list">'
		+'<a href="'+item.articleLink+'"; target="_blank")>'		
		+'<img src="'+imgsrc+'"/>'
		+'<p class="title" title="'+item.title+'">'+item.title+'</p></a>'
		+'<div class="details">'
		+'<p class="address" title="'+item.address+'">'+item.address+'</p>'
		+'<p class="date-time" title="'+item.exhibitiontime+'">'+item.exhibitiontime+'</p>'
		+'<a href="javascript:void(0);" id="'+item.id+'" class="follow">添加关注</a>'
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
		url:'/summit/insert',
		asynyc:false,
		contentType:'application/json',
		data:param,
		success:function(res){
			if(res.message!= null){
				new Alert({flag:false,text:res.message,timer:2000}).show();
			}else{
				new Alert({flag:true,text:res.data,timer:2000}).show();
				$(this).html("已关注");
			}
		}
	});
});
function updateAreaInfo(e){
	$.ajax({
		type:'POST',
		url:'/apis/getlabel/getLabel.json',
		asynyc:false,
		contentType:'application/json',
		data:JSON.stringify({
            type:'one',
            industry:e
        }),
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data;
				var html = '<a href="javascript:void(0);" id="2-全部" class="search-item active">全部</a>';
				if(arr.length != 0){
					$(".search-group").eq(1).show();
					$(".search-group").eq(2).show();
					for(var i = 0;i<arr.length;i++){
						if(arr[i] != '全国'){
							html += '<a href="javascript:void(0);" id="2-'+arr[i]+'" class="search-item">'+arr[i]+'</a>';
						}
					}
					$(".search-item-content").eq(1).html(html);
				}else{
					$(".search-group").eq(1).hide();
					$(".search-group").eq(2).hide();
				}
			}
		}
	});
}
