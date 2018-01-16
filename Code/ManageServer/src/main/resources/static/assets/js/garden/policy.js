$(function(){
	showPolicy(pageNum,pageSize,serarchName,dimension);
	$(".btn-info").on("click",function(){
		serarchName = $("#search").val();
		showPolicy(pageNum,pageSize,serarchName,dimension);
	});
});
var pageSize = 20;
var pageNum = 0;
var serarchName = "";
var dimension = "园区政策";
var options={
	    "id":"page",//显示页码的元素
	    "data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":20,//每页显示数据个数
	    "callBack":function(){}
	};
function showPolicy(_pageNum,_pageSize,_serarchName,_dimension){
	var req = {
			"pageNum" : _pageNum,
			"pageSize" : _pageSize,
			"serarchName":_serarchName,
			"dimension":_dimension
		};
	$.ajax({
		type:'post',
		url:'/apis/area/findGardensCondition.json',
		contentType:'application/json',
		data:JSON.stringify(req),
		async:false,
		success:function(res){
			if(res.success){
				var arr = res.data.content;
				html = '';
				for (var i = 0; i < arr.length; i++) {
					html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td><input type="checkbox" name="checkname"/></td><td>' + arr[i].title + '</td><td>' + arr[i].publishTime + '</td><td>' + arr[i].park + '</td><td>' + arr[i].source
					+ '</td><td class="actions"><a href="javascript:void(0);" class="on-default remove-row"><i class="fa fa-trash-o"></i></a></td></tr>';
				}
				$("#policy").html(html);
				$("#selectAll").on("click",function(){
					if($(this).context.checked){
						$("#policy").find('input[type="checkbox"]').each(function(){
							$(this).attr("checked",true);
						});
					}else{
						$("#policy").find('input[type="checkbox"]').each(function(){
							$(this).attr("checked",false);
						});
					}
					
				})
				if(res.data.totalPages>1){
                    page.init(res.data.totalElements,res.data.number+1,options);
                    $("#"+page.pageId +">li[class='pageItem']").on("click",function(){
                    	pageNum = $(this).attr("page-data")-1;
                    	showPolicy($(this).attr("page-data")-1,pageSize,serarchName,dimension);
                    });
                }else{
                    $('#page').html("");
                }
			}
		}
	})
}
function delIndus(_ids){
	$.ajax({
		async:false,
		url : "/apis/area/deleteCondition.json?id="+_ids,
		success : function(res) {
			if(res.success){
				showPolicy(pageNum,pageSize,serarchName,dimension);
			}
		}
	});
}