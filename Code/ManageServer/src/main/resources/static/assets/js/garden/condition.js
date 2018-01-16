$(function(){
	showCondition(pageNum,pageSize,serarchName,dimension);
	$(".btn-info").on("click",function(){
		serarchName = $("#search").val();
		showCondition(pageNum,pageSize,serarchName,dimension);
	});
});
var options={
	    "id":"page",//显示页码的元素
	    "data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":20,//每页显示数据个数
	    "callBack":function(){}
	};
var pageSize = 20;
var pageNum = 0;
var serarchName = "";
var dimension = "园区动态";
function showCondition(_pageNum,_pageSize,_serarchName,_dimension){
	var req = {
			"pageNum" : _pageNum,
			"pageSize" : _pageSize,
			"serarchName":_serarchName,
			"dimension":_dimension
			
		};
	$.ajax({
		type:'post',
		url:'/apis/area/findGardensCondition.json',
		data:JSON.stringify(req),
		contentType:'application/json',
		success:function(res){
			if(res.success){
				console.log(res.data);
				var arr = res.data.content;
				var html = '';
				for (var i = 0; i < arr.length; i++) {
					html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td><input type="checkbox" name="checkname"/></td><td>' + arr[i].title + '</td><td>' + arr[i].publishTime + '</td><td>' + arr[i].park + '</td><td>' + arr[i].source
					+ '</td><td class="actions"><a href="javascript:void(0);" class="on-default remove-row"><i class="fa fa-trash-o"></i></a></td></tr>';
				}
				$("#condition").html(html);
				$(".remove-row").on("click",function(i){
					var ids = new Array();
					$("#condition").find('input[type="checkbox"]').each(function(){
						if(this.checked){
							var id = $(this).parents('.gradeX').find( 'input' ).val();
							ids.push(id);
						}
					});
					delIndus(ids);
				})
				$("#selectAll").on("click",function(){
					if($(this).context.checked){
						$("#condition").find('input[type="checkbox"]').each(function(){
							$(this).attr("checked",true);
						});
					}else{
						$("#condition").find('input[type="checkbox"]').each(function(){
							$(this).attr("checked",false);
						});
					}
				})
				if(res.data.totalPages>1){
                    page.init(res.data.totalElements,res.data.number+1,options);
                    $("#"+page.pageId +">li[class='pageItem']").on("click",function(){
                    	pageNum = $(this).attr("page-data")-1;
                    	showCondition($(this).attr("page-data")-1,pageSize,serarchName,dimension);
                    });
                }else{
                    $('#page').html("");
                }
			}
		}
	});
}
function delIndus(_ids){
	$.ajax({
		async:false,
		url : "/apis/area/deleteCondition.json?id="+_ids,
		success : function(res) {
			if(res.success){
				showCondition(pageNum,pageSize,serarchName,dimension);
			}
		}
	});
}