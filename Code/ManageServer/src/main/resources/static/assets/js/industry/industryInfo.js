$('#industry_info').addClass("active");
var industry = "全部";
var area = "全部"; 
var sort = "按热度";
pageSize = 6;
var pageNumber = 0;
var options={
		"id":"page",//显示页码的元素
		"data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":6,//每页显示数据个数
	    "callBack":function(){}
};
$(function () {
	 getIndustry(0,0);
	 getIndustryInfoList();
});
function getIndustryInfoList(){
	$.ajax({
		url:'/apis/industryinfo/findAITInfo.json',
		type:'GET',
		asynyc:false,
		success : function(res){
			if( res.message !=null){
				$('#industryListByNon').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
			}else{
				var _data = res.data;
				$('#industryListByNon').html(showInfoData(_data));
			}
		}
	});
};

function getIndustry(a,b){
	if(a==1){
		industry = b;
	}else if(a==2){
		area = b;
	}else if(a==3){
		sort = b;
	}
	var param ={industry:industry,area:area,sort:sort,pageSize:pageSize,pageNumber:pageNumber};
	AjaxPost(param);
};

$(".search-box").on("click",".search-item-content>a",function(){
	$(this).addClass("active").siblings().removeClass("active");
	var _id = $(this).attr("id");
	var array = _id.split('-');
	var a = array[0];
	var b = array[1];
	getIndustry(a,b);
});

function AjaxPost(param){
	$.ajax({
		url:'/apis/industryinfo/findIndustryInfoArticleList.json',
		type:'POST',
		asynyc:false,
		contentType:'application/json',
		data:JSON.stringify(param),
		success:function(res){
			if(res.data.dataList.length==0){
				new Alert({flag:false,text:"暂无数据",timer:2000}).show();
				$('#indusryInfoList').html('');
				$('#page').html("");
			}else{
				var arr = res.data;
				$('#indusryInfoList').html(ShowArticleList(arr.dataList));
				
				if(res.data.totalPage>1){
					page.init(res.data.totalNumber,res.data.pageNumber,options);
					$("#"+page.pageId +">li[class='pageItem']").on("click",function(){
						var param={industry:industry,sort:sort,area:area,pageNumber:$(this).attr("page-data")-1,pageSize:pageSize};
						AjaxPost(param);
					});
				}else{
					$('#page').html("");
				}
			}
			
		}
	});
};

function ShowArticleList(arr){
	var array = [];
	$.each(arr,function(index,item){
		array.push(
				'<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+item.id+'"/><td>' 
				+item.title+ '</td><td>'
				+item.summary+ '</td><td>'
				+item.industryLabel + '</td><td>' 
				+item.publishTime + '</td><td>'
				+item.hitCount + '</td><td class="actions">'
				+'<a href="javascript:void(0);" class="on-default editinfo"><i class="fa fa-pencil"></i></a>'
				+'<a href="javascript:void(0);" class="on-default removeinfo modal-basic"><i class="fa fa-trash-o"></i></a></td></tr>'
	
		);
	});
	var inner = array.join('');
	return inner;
};
function showInfoData(array){
	var arr =[];
	$.each(array,function(index,item){
		arr.push(
				'<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+item.id+'"/><td>' 
				+item.title+ '</td><td>'
				+item.industryLabel+ '</td><td>'
				+item.summary + '</td><td>' 
				+item.publishTime + '</td><td>'
				+item.bus + '</td><td class="actions">'
				+'<a href="javascript:void(0);" class="on-default removeinfo modal-basic"><i class="fa fa-trash-o"></i></a></td></tr>'
			
		);
	});
	var inner = arr.join('');
	return inner;
};