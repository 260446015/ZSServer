/**
 * Created by zhangxin on 2017/11/17.
 */
var industry = "全部";
var area = "全部";
var sort = "按热度";
var pageSize = 8;
var pageNumber = 0;
$(function(){
	$("#summit").addClass("active");
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
	var param={industry:industry,area:area,sort:sort,pageSize:pageSize,pageNumber:pageNumber}
	ajaxPost(param);
};
function ajaxPost(param){
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
				$('#summit-list').html(show(res.data.content));
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
		+'<a href="'+item.articleLink+'" target="_blank")>'		
		+'<img src="'+imgsrc+'"/>'
		+'<p class="title">'+item.title+'</p></a>'
		+'<div class="details">'
		+'<p class="address">'+item.address+'</p>'
		+'<p class="date-time">'+item.exhibitiontime+'</p>'
		+'<a href="javascript:void(0);" id="'+item.id+'" class="follow">添加关注</a>'
		+'</div></div></div>'
		);
	});
	var inner = arr.join('');
	return inner;
};
$('#summit-list').on("click",".follow",function(){
	var aid = $(this).attr("id");
	console.log(aid);
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
			}
		}
	});
});

