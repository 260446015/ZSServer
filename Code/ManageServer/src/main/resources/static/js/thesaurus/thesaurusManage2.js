var keywordAttribute='0';
var pageSize = 10;
var pageNumber = 0;
var sort = '1';
var options={
		"id":"page",//显示页码的元素
		"data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":8,//每页显示数据个数
	    "callBack":function(){}
};
$(function(){
	$('#keyword_info').addClass("active");
	//获取所有label
	getLabel();
});

function getLabel(){
	$.ajax({
		url:'/apis/keyInfo/getLabelInfo.json',
		type:'GET',
		asynyc:false,
		success:function(res){
			console.log(res.data);
			if(res.data==null){
				 layer.msg(res.message, {icon: 2});
			}else{
				var label ='';
					label +='<a href="javascript:void(0);" id="0" class="search-item active">全部</a>';
				$.each(res.data,function (i,e){
						label +='<a href="javascript:void(0);" id="'+e.id+'" class="search-item">'+e.TypeWord+'</a>';
				})
				$('#labelInfo').html(label);
			}
		}
	});
};



