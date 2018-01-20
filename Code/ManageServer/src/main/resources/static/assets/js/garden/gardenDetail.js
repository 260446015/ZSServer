$(function(){
	id = GetQueryString("id");
	serarchName = GetQueryString("park");
	showGardenInfo(id);
	showCondition(pageNum,pageSize,serarchName,dimension);
	showPolicy(pageNum,pageSize,serarchName,dimension);
	showCompany(pageNum,pageSize,serarchName);
	$(".search-box").on("click",".search-item-content>a",function(){
        $(this).addClass("active").siblings().removeClass("active");
        showCompany(pageNum,pageSize,serarchName);
	});
	$(".add").on("click",function(){
		window.location.href = "/apis/area/editArt.html";
	})
	initPage();
});
var options={
	    "id":"page",//显示页码的元素
	    "data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":20,//每页显示数据个数
	    "callBack":function(){}
	};
var options2={
	    "id":"page2",//显示页码的元素
	    "data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":20,//每页显示数据个数
	    "callBack":function(){}
	};
var options3={
	    "id":"page3",//显示页码的元素
	    "data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":20,//每页显示数据个数
	    "callBack":function(){}
	};
var pageSize = 20;
var pageNum = 0;
var serarchName = "";
var dimension = "园区动态";
var id;
function GetQueryString(key) {// 获取地址栏中的name
	// 获取参数
	var url = window.location.search;
	// 正则筛选地址栏
	var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
	// 匹配目标参数
	var result = url.substr(1).match(reg);
	// 返回参数值
	return result ? decodeURIComponent(result[2]) : null;
}
function showGardenInfo(_id){
	$.ajax({
        url: "/apis/area/getGardenInfo.json?id="+_id,
        async:false,
        success: function (res) {
        	console.log(res.data);
            $("#parkName").html(res.data.gardenName);
            $("#parkArea").html(res.data.province);
            $("#address").html(res.data.address);
            $("#industry").html(res.data.industryType);
            $("#introduction").html(res.data.gardenIntroduce);
            $("#logo").html("<img alt=\"logo\" src=\""+res.data.gardenPicture+"\">");
            $("#condition").attr("href","/apis/area/condition.html?park="+res.data.gardenName);
            $("#gardenCompany").attr("href","/apis/area/gardenCompany.html?park="+res.data.gardenName);
            $("#policy").attr("href","/apis/area/policy.html?park="+res.data.gardenName);
        }
    });
}

function showCondition(_pageNum,_pageSize,_serarchName,_dimension){
	var req = {
			"pageNum" : _pageNum,
			"pageSize" : _pageSize,
			"serarchName":_serarchName,
			"dimension":"园区动态"
			
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
					html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td><input type="checkbox" name="checkname"/></td><td><a href="/apis/area/detailArt.html?artId='+arr[i].id+'">' + arr[i].title + '</a></td><td>' + arr[i].publishTime + '</td><td>' + arr[i].park + '</td><td>' + arr[i].source
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
                    $("#page > li[class='pageItem']").on("click",function(){
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

function showPolicy(_pageNum,_pageSize,_serarchName,_dimension){
	var req = {
			"pageNum" : _pageNum,
			"pageSize" : _pageSize,
			"serarchName":_serarchName,
			"dimension":"园区政策"
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
					html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td><input type="checkbox" name="checkname"/></td><td><a href="/apis/area/detailArt.html?artId='+arr[i].id+'">' + arr[i].title + '</a></td><td>' + arr[i].publishTime + '</td><td>' + arr[i].park + '</td><td>' + arr[i].source
					+ '</td><td class="actions"><a href="javascript:void(0);" class="on-default remove-row"><i class="fa fa-trash-o"></i></a></td></tr>';
				}
				$("#policy").html(html);
				$("#selectAll2").on("click",function(){
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
                    page.init(res.data.totalElements,res.data.number+1,options2);
                    $("#page2 > li[class='pageItem']").on("click",function(){
                    	pageNum = $(this).attr("page-data")-1;
                    	showPolicy($(this).attr("page-data")-1,pageSize,serarchName,dimension);
                    });
                }else{
                    $('#page2').html("");
                }
			}
		}
	})
}

function showCompany(_pageNum,_pageSize,_park){
	var msg = new Array();
    var arr = $(".search-box").find(".active");
    arr.each(function(){
        msg.push($(this).html());
    });
	var req = {
			"pageNum" : _pageNum,
			"pageSize" : _pageSize,
			"msg":msg,
			"park":_park
		};
	$.ajax({
		type:'post',
		url:'/apis/company/findGardenCompany.json',
		contentType:'application/json',
		data:JSON.stringify(req),
		async:false,
		success:function(res){
			var arr = res.data.content;
			var html = '';
			$("#total").html(res.data.totalElements);
			for (var i = 0; i < arr.length; i++) {
				html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td>' + arr[i].companyName + '</td><td>' + arr[i].address + '</td><td>' + arr[i].boss + '</td><td>' + arr[i].park
				+ '<td>' + arr[i].registerCapital + '</td><td>' + arr[i].registerDate + '</td></td><td class="actions"><a href="javascript:void(0);" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>'
				+ '<a href="javascript:void(0);" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>' + '<a href="javascript:void(0);" class="on-default edit-row"><i class="fa fa-pencil"></i></a>'
				+ '<a href="javascript:void(0);" class="on-default remove-row"><i class="fa fa-trash-o"></i></a></td></tr>';
			}
			$("#company").html(html);
			if(res.data.totalPages>1){
                page.init(res.data.totalElements,res.data.number+1,options3);
                $("#page3 > li[class='pageItem']").on("click",function(){
                	pageNum = $(this).attr("page-data")-1;
                	showCompany($(this).attr("page-data")-1,pageSize,serarchName);
                });
            }else{
                $('#page3').html("");
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
		var indus = {"id":indusArr[0],"companyName":indusArr[1],"address":indusArr[2],"boss":indusArr[3],"park":indusArr[4],"registerCapital":indusArr[5],"registerDate":indusArr[6]}
		insertIndus(indus);
	})
	$("#addToTable").on("click",function(i){
		$(this).attr("disabled",true);
		var html = '<tr role="row" class="adding odd"><td class="sorting_1"><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td class="actions"><a href="#" class="on-editing save-row">' +
				'<i class="fa fa-save"></i></a> <a href="#" class="on-editing cancel-row">' +
				'<i class="fa fa-times"></i></a> <a href="#" class="on-default edit-row hidden">'+
				'<i class="fa fa-pencil"></i></a> <a href="#" class="on-default remove-row hidden">'+
				'<i class="fa fa-trash-o"></i></a></td></tr>';
		$("#company").children().eq(0).before(html);
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
			var indus = {"id":indusArr[0],"companyName":indusArr[1],"address":indusArr[2],"boss":indusArr[3],"park":indusArr[4],"registerCapital":indusArr[5],"registerDate":indusArr[6]}
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
