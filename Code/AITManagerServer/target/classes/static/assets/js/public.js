var GB_Page_Size = 10;
var GB_Current_Index=1;

function showList(id,url,page) {
	
	var data = {
		pageNum: page,
		pageSize: GB_Page_Size
	};

	$.ajax({
		url:url,
		type:'POST',
		data:data,
		dataType:'json',
		success:function(result){
			if(result.success&&result.data.list.length>0){
				// 附上模板  
			    $("#Div_List").setTemplateElement("template_list_batch");
			    // 给模板加载数据
			    $("#Div_List").processTemplate(result.data.list);
			    if (result.data.pages == 0) {
					$("#div_dataPage").css("display", "none");
				}else{
					$("#div_dataPage").css("display", "");
					$.jqPaginator(
					 id, {
						totalPages: result.data.pages,
                        visiblePages: 10,
                        currentPage: result.data.pageNum,
                        first: '<li class="first"><a href="javascript:;">首页</a></li>',
						prev : '<li class="prev"><a href="javascript:;">上一页</a></li>',
						next : '<li class="next"><a href="javascript:;">下一页</a></li>',
						last : '<li class="last"><a href="javascript:;">末页</a></li>',
						page : '<li class="page"><a href="javascript:;">{{page}}</a></li>',
						onPageChange : function(num, type) {
							if(type=='change'){
								changeContent(url, num);
                            }
							//跳转页数
							$(".btn_info").on("click",function(){
							    var index=$(this).parents("ul").find("input").val()-0;
							    //控制范围
							    if(index > 0 && index <= result.data.pages){
						    	   $('#div_dataPage').jqPaginator('option', {
						    		   	currentPage:index
							       });
								   changeContent(url, index);
							    }
							});
							
						}
					});
				}
			    try{
					top.AutoSetMainFrameHeight();
				}catch(e){}
			}else{
				showNull(id);
			}
		},
		error:function(e){
			
		}
	});
}




// 点击跳转页数查询
function changeContent(url, num) {
  $.ajax({
      url: url,
      dataType: "json",
      type: 'POST',
      data: {
          pageNum: num,
          pageSize: GB_Page_Size
      },
      success: function(result) {
	    // 给模板加载数据
	    $("#Div_List").processTemplate(result.data.list);
      },
      error: function(result) {
          return;
      }
  });
}



//条件参数点击跳转页数查询
function changeParameterContent(url, num) {
	var parameter = $("#signupForm1").serialize();
	parameter += "&pageNum=" + num + "&pageSize=" + GB_Page_Size;
	$.ajax({
		url : url,
		dataType : "json",
		type : 'POST',
		data : parameter,
		success : function(result) {
			// 给模板加载数据
			$("#Div_List").processTemplate(result.data.list);
		},
		error : function(result) {
			return;
		}
	});
}



// 条件查询分页数据
function showSelectList(id,url,page) {
	var parameter = $("#signupForm1").serialize();
	parameter += "&pageNum=" + page + "&pageSize=" + GB_Page_Size;
	$.ajax({
	    dataType: "json",
	    url: url,
	    data: parameter,
	    type: 'POST',
	    success: function(result) {
			if(result.success&&result.data.list.length>0){
				// 附上模板  
			    $("#Div_List").setTemplateElement("template_list_batch");
			    // 给模板加载数据
			    $("#Div_List").processTemplate(result.data.list);
			    if (result.data.pages == 0) {
					$("#div_dataPage").css("display", "none");
				}else{
					$("#div_dataPage").css("display", "");
					$.jqPaginator(
					 id, {
						totalPages: result.data.pages,
                        visiblePages: 10,
                        currentPage: result.data.pageNum,
                        first : '<li class="first"><a href="javascript:;">首页</a></li>',
						prev : '<li class="prev"><a href="javascript:;">上一页</a></li>',
						next : '<li class="next"><a href="javascript:;">下一页</a></li>',
						last : '<li class="last"><a href="javascript:;">末页</a></li>',
						page : '<li class="page"><a href="javascript:;">{{page}}</a></li>',
						onPageChange : function(num, type) {
							if(type=='change'){
								changeParameterContent(url, num);
                            }
							//跳转页数
							$(".btn_info").on("click",function(){
							    var index=$(this).parents("ul").find("input").val()-0;
							    //控制范围
							    if(index > 0 && index <= result.data.pages){
						    	   $('#div_dataPage').jqPaginator('option', {
						    		   	currentPage:index
							       });
						    	   changeParameterContent(url, index);
							    }
							});
							
						}
					});
				}
			    try{
					top.AutoSetMainFrameHeight();
				}catch(e){}
			}else{
				showNull(id);
			}
	    }
	});
}

//没有数据显示 
function showNull(id){
	$.jqPaginator(
            id, {
                totalPages: 1,
                visiblePages: 1,
                currentPage: 1,
                first : '<li class="first"><a href="javascript:;">首页</a></li>',
				prev : '<li class="prev"><a href="javascript:;">上一页</a></li>',
				next : '<li class="next"><a href="javascript:;">下一页</a></li>',
				last : '<li class="last"><a href="javascript:;">末页</a></li>',
				page : '<li class="page"><a href="javascript:;">{{page}}</a></li>',
                onPageChange: function(num,type) {
                	
        			$("#Div_List").find("tr").remove(); 
    		    	html="<tr style='width: 100%;display: block;'><td style='width: 100%;display: block;text-align: center;' colspan='4'>无数据</td></tr>";
    		    	$("#Div_List").append(html);
                }
    });	
}


/**
 * 无数据转为空
 */
function convertValue(val) {
	var msg="";
	if (val == null || val.length==0) {
		msg = "";
	} else {
		if(val.length>20){
			for (var int = 0; int < 20; int++) {
				msg += val[int];
			}
			msg+= ".....";
		}else{
			msg = val;
		}
		
	}
	return msg;
}


/**
 * 限制12位 无数据转为空
 */
function convertTwelveValue(val) {
	var msg="";
	if (val == null || val.length==0) {
		msg = "";
	} else {
		if(val.length>12){
			for (var int = 0; int < 12; int++) {
				msg += val[int];
			}
			msg+= ".....";
		}else{
			msg = val;
		}
		
	}
	return msg;
}

/**
 * 转换日期
 */
function convertTime(val) {
	var msg;
	if (val == null) {
		msg = "";
	}else{
		msg = getFormatDateByLong(val,"yyyy-MM-dd hh:mm:ss");
	}
	return msg;
}