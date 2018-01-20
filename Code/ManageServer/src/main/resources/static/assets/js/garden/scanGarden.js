$(function(){
	showScanGarden(pageNum,pageSize,serarchName,dimension);
	$(".btn-info").on("click",function(){
		serarchName = $("#search").val();
		showScanGarden(pageNum,pageSize,serarchName,dimension);
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
var dr = 0;
function showScanGarden(_pageNum,_pageSize,_serarchName,_dimension){
	var req = {
			"pageNum" : _pageNum,
			"pageSize" : _pageSize,
			"serarchName":_serarchName,
			"dimension":_dimension
		};
	$.ajax({
		type:'post',
		url:'/apis/area/scanGarden.json',
		contentType:'application/json',
		data:JSON.stringify(req),
		async:false,
		success:function(res){
			if(res.success){
				var arr = res.data.content;
				html = '';
				console.log(arr);
				for (var i = 0; i < arr.length; i++) {
					html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td>' + arr[i].gardenName + '</td><td>' + arr[i].gardenId + '</td><td>' + arr[i].scanDate + '</td><td><input class="input" value="' + arr[i].scanCount + '"/></td>' +
						'<td class="actions"><div class="col-lg-12 col-md-4 col-sm-4 col-xs-4"><label class="switch switch-success bk-margin-top-10">';
						if(arr[i].dr==0){
							html +='<input type="checkbox" class="switch-input" />';
	                    }else{
	                    	html +='<input type="checkbox" class="switch-input" checked="">';
	                    }
					html += '<span class="switch-label" data-on="On" data-off="Off"></span><span class="switch-handle"></span></label></div></td></tr>';
				}
				$("#scan").html(html);
				$(".input").blur(function(){
					if($(this).context.checked){
						dr = 1;
					}
					var id = $(this).parents('.gradeX').find( '.input-block' ).val();
					var indusArr = new Array();
					var _input = $(this).parents('.gradeX').find('td');
					_input.each(function( i ) {
						var $this = $( this );
						if(i == 3){
							indusArr.push($this.children("input").val());
						}else
							indusArr.push($this.text());
					});
					var req = {"id":id,"gardenName":indusArr[0],"gardenId":indusArr[1],"scanDate":indusArr[2],"scanCount":indusArr[3],"dr":dr};
					changeScanStatus(req);
				})
				$(".switch-input").on('click',function(){
					if($(this).context.checked){
						dr = 1;
					}
					var id = $(this).parents('.gradeX').find( '.input-block' ).val();
					var indusArr = new Array();
					var _input = $(this).parents('.gradeX').find('td');
					_input.each(function( i ) {
						var $this = $( this );
						if(i == 3){
							indusArr.push($this.children("input").val());
						}else
							indusArr.push($this.text());
					});
					var req = {"id":id,"gardenName":indusArr[0],"gardenId":indusArr[1],"scanDate":indusArr[2],"scanCount":indusArr[3],"dr":dr};
					changeScanStatus(req);
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
function changeScanStatus(_req){
	$.ajax({
		type:'post',
		url:'/apis/area/changeScanStatus.json',
		contentType:'application/json',
		data:JSON.stringify(_req),
		success:function(res){
			console.log(res.data);
		}
	})
}
