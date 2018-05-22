$(function(){
	showGdpArea();
	showDifYearGdp(pageNum,pageSize);
	 $(".search-box").on("click",".search-item-content>a",function(){
        $(this).addClass("active").siblings().removeClass("active");
        showDifYearGdp(pageNum,pageSize);
	 });
	 initPage();
});
var industry;
var year;
var province;
var pageSize = 20;
var pageNum = 0;
var options={
	    "id":"page",//显示页码的元素
	    "data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":20,//每页显示数据个数
	    "callBack":function(){}
	};
function showDifYearGdp(_pageNum,_pageSize){//model中展示不同年份某一产业的gdp  //model中展示折线图
	$("#addToTable").attr("disabled",false);
	var msg = new Array();
    var arr = $(".search-box").find(".active");
    arr.each(function(i){
        if(i==0){
        	industry = $(this).text();
        }else if(i==1){
        	year = $(this).text();
        }else if(i == 2){
        	province = $(this).text();
        }
    });
    park = $("#search").val();
	var req = {
			"pageNum":_pageNum,
			"pageSize":_pageSize,
			"industry":industry,
			"year":year,
			"province":province
		};
	$.ajax({
		type:'post',
		url:'/apis/area/findGardenGdp.json',
		contentType:'application/json',
		data:JSON.stringify(req),
		async:false,
		success:function(res){
			if(res.success){
				var arr = res.data.content;
				html = '';
				for(var i= 0;i<arr.length;i++){
					html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td>'+arr[i].industry+'</td><td>'+arr[i].province+'</td><td>'+arr[i].year+'</td><td>'+arr[i].gdp+'</td><td class="actions"><a href="javascript:void(0);" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>'
					+ '<a href="javascript:void(0);" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>' + '<a href="javascript:void(0);" class="on-default edit-row"><i class="fa fa-pencil"></i></a>'
					+ '<a href="javascript:void(0);" class="on-default remove-row"><i class="fa fa-trash-o"></i></a></td></tr>';
				}
				$("#gdp").html(html);
				if(res.data.totalPages>1){
                    page.init(res.data.totalElements,res.data.number+1,options);
                    $("#"+page.pageId +">li[class='pageItem']").on("click",function(){
                    	pageNum = $(this).attr("page-data")-1;
                    	showDifYearGdp($(this).attr("page-data")-1,pageSize);
                    });
                }else{
                    $('#page').html("");
                }
			}
		}
	});
}
function showGdpArea(){
	$.ajax({
		url:'/apis/area/findGardenGdpArea.json',
		async:false,
		success:function(res){
			var arr = res.data;
			var html = '';
			for(var i =0;i<arr.length;i++){
				html += '<a href="javascript:void(0);" class="search-item">'+arr[i]+'</a>';
			}
			$("#area").children().eq(0).after(html);
		}
	})
}
function initPage(){
	$(".edit-row").on("click",function(){
		$(this).parents('.gradeX').children( 'td' ).each(function( i ) {
			var $this = $( this );
			if ( $this.hasClass('actions') ) {
				$this.find("a").each(function(){
					if($(this).hasClass("hidden")){
						$(this).removeClass("hidden");
					}else{
						$(this).addClass("hidden");
					}
				})
			} else {
				$this.html( '<input type="text" class="form-control input-block" value="' + $this.text() + '"/>' );
			}
		});
	})
	$(".save-row").on("click",function(i){
		var _input = $(this).parents('.gradeX').find( '.input-block' );
		$(this).parents('.gradeX').find(".actions").find("a").each(function(){
			if($(this).hasClass("hidden")){
				$(this).removeClass("hidden");
			}else{
				$(this).addClass("hidden");
			}
		});
		var indusArr = new Array();
		_input.each(function( i ) {
			var $this = $( this );
			indusArr.push($this.val());
		});
		var indus = {"id":indusArr[0],"industry":indusArr[1],"province":indusArr[2],"year":indusArr[3],"gdp":indusArr[4]};
		insertIndus(indus);
	})
	$("#addToTable").on("click",function(i){
		$(this).attr("disabled",true);
		var html = '<tr role="row" class="adding odd"><td class="sorting_1"><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td class="actions"><a href="#" class="on-editing save-row">' +
				'<i class="fa fa-save"></i></a> <a href="#" class="on-editing cancel-row">' +
				'<i class="fa fa-times"></i></a> <a href="#" class="on-default edit-row hidden">'+
				'<i class="fa fa-pencil"></i></a> <a href="#" class="on-default remove-row hidden">'+
				'<i class="fa fa-trash-o"></i></a></td></tr>';
		$("#gdp").children().eq(0).before(html);
		$(".save-row").on("click",function(i){
			var _input = $(this).parents('.adding').find( 'input' );
			$(this).parents('.adding').find(".actions").find("a").each(function(){
				if($(this).hasClass("hidden")){
					$(this).removeClass("hidden");
				}else{
					$(this).addClass("hidden");
				}
			});
			var indusArr = new Array();
			_input.each(function( i ) {
				var $this = $( this );
				indusArr.push($this.val());
			});
			var indus = {"id":indusArr[0],"industry":indusArr[1],"province":indusArr[2],"year":indusArr[3],"gdp":indusArr[4]};
			insertIndus(indus);
		})
		$(".cancel-row").on("click",function(){
			window.location.reload();
		})
	})
	$(".remove-row").on("click",function(i){
		var id = $(this).parents('.gradeX').find( 'input' ).val();
		delIndus(id);
	})
	$(".cancel-row").on("click",function(){
		window.location.reload();
	})
}
function insertIndus(_indus){
	console.log(_indus);
	$.ajax({
		type:'post',
		url:'/apis/area/saveGdp.json',
		contentType:'application/json',
		data:JSON.stringify(_indus),
		success:function(res){
			showDifYearGdp(pageNum,pageSize);
		}
	})
	
}
function delIndus(_id){
	$.ajax({
		url:'/apis/area/deleteGdp.json',
		success:function(res){
			showDifYearGdp(pageNum,pageSize);
		}
	})
}