var type='0';
var pageSize = 20;
var pageNumber = 0;
var sort = '1';
var option='';
var index=1;
var length=1;
var typeWord='';

var options={
		"id":"page",//显示页码的元素
		"data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":8,//每页显示数据个数
	    "callBack":function(){}
};
var a =0;
$(function(){
	$('#keyword_info').addClass("active");
	//获取所有label
	getLabel();
	var param ={type:type,sort:sort,pageSize:pageSize,pageNumber:pageNumber};
	getInfoList(param);
	
	$('#addKeyword').after('<input type="file" id="load_xls" name="file" style="display:none" onchange ="uploadFile()">');
	$('#addKeyword').on('click',function(){
		$('#load_xls').click();
	});
	getAllRelated();
});

$(".search-box").on("click",".search-item-content>a",function(){
	$(this).addClass("active").siblings().removeClass("active");
	var _id = $(this).attr("id");
	var param ={type:_id,sort:sort,pageSize:pageSize,pageNumber:pageNumber};
	console.log(_id);
	getInfoList(param);
	type=_id;
});

//根据sort排序方式查看属性
function findBySort(){
	var vs = $('select option:selected').val();
	console.log('根据排序方式查看属性：》》》》'+vs);
	var param ={type:type,sort:vs,pageSize:pageSize,pageNumber:pageNumber};
	getInfoList(param);
	sort = vs;
};
$('.my_relatedaddinto').on('click',function(){
	var _type =$("input[name='keyword']").val();//获取输入框的值
	console.log(_type);
	if(_type==null){
		 layer.msg("系统异常", {icon: 2});
		 window.location.href="/apis/keyInfo/ThesaurusManage.html";
	}else{
		var chk_value =[]; 
		$('input[name="lang"]:checked').each(function(){ 
			chk_value.push($(this).val()); 
		});
		if(chk_value.length>0){
			var b = $("input[type='radio']:checked").val();
			if(b!=null){
				var param ={keyword:_type ,msg:chk_value,relatedId:b};
				console.log(param);
				$.ajax({
					url:'/apis/keyInfo/addOrUpAttributeRelatedDTO.json',
					type:'POST',
					data:JSON.stringify(param),
					asynyc:false,
					contentType:'application/json',
					success:function(res){
						var label='';
						console.log(res.data);
						$.each(res.data,function(i,e){
							label +='<tr class="gradeX"><td>'+e.relateId+'</td>';
							label +='<td>'+e.number+'</td>';
							label +='<td>'+e.relateword+'</td>';
							label +='<td>'+e.related+'</td><td>';
							label +='<a href="javascript:void(0);" class="on-default removerelateInfo modal-basic"><i class="fa fa-trash-o">删除</i></a></td></tr>';
						});
						$('#related_info2').append(label);
					}
				});
			}
		}
	};
	//删除错误关系项
	$('.removerelateInfo ').on('click',function(){
		var _id =  $(this).parents('.gradeX').find('td').eq(0).text();
		console.log(_id);
		//删除错误关系项
		$.ajax({
			url:'/apis/keyInfo/deleteRelateById.json?id='+_id,
			 type:'GET',
			 asynyc:false,
			success:function(res){
				if(res.data==true){
					layer.msg('删除成功', {icon: 1});
				}else{
					layer.msg('删除失败', {icon: 2});
				}
			}
		});
	});
	
});
//上一页--修改数据
$('.my_firstadd').on('click',function(){
	$('#secondModal').modal('hide');
	$('#myModal').modal('show');
});
//下一页--查看新增的数据
$('.my_secondadd').on('click',function(){
	//获取新增关键词的内容
	var _type =$("input[name='keyword']").val();//获取输入框的值
	console.log("获取新增词的内容"+_type);
	$.ajax({
		url:'/apis/keyInfo/findInfoByKeyword.json?keyword='+_type,
		type:'GET',
		asynyc:false,
		success:function(res){
			if(res.data==null){
				layer.msg('系统异常', {icon: 2});
			}else{
				var label ='';
				//新增词
				label += '<div class="form-group"><label class="col-md-3 control-label" for="text-input">新增词</label>';
				label += '<div class="col-md-9"><input type="text" name="name"  class="form-control" value='+res.data.keyword+' ></div></div>';
				//词类型
				label += '<div class="form-group"><label class="col-md-3 control-label" for="text-input">词类型</label>';
				label += '<div class="col-md-9"><input type="text" name="name"  class="form-control" value='+res.data.wordtype+' ></div></div>';
				//词属性
				res.data.info;
				if(res.data.info.length==0){
					label += '<div class="form-group"><label class="col-md-3 control-label" for="text-input">词属性：</label>';
					label += '<div class="col-md-9"><h4>无</h4></div></div>';
				}else{
					label += '<div class="form-group"><label class="col-md-3 control-label" for="text-input">词属性：</label>';
					$.each(res.data.info,function(i,e){
						if(i==0){
							label +='<div class="col-md-9">';
							label +='<h4>'+e.attributeName+'</h4>';
      					  	label +='<input type="text" name="dddd" value="'+e.attributeValue+'" class="form-control" placeholder="">';
							label +='</div>';
						}else{
							label +='<div class="col-md-9 list">'
							label +='<h4>'+e.attributeName+'</h4>';
      					  	label +='<input type="text" name="dddd" value="'+e.attributeValue+'" class="form-control" placeholder="">';
							label +='</div>';
							}	
						});	
					label += '</div>';
				 }
				//词关系
				 console.log(res.data.relatetion);
				if(res.data.relatetion.length==0){
					label += '<div class="form-group"><label class="col-md-3 control-label" for="text-input">词关系：</label>';
					label += '<div class="col-md-9"><h4>无</h4></div></div>';
				}else{
					label += '<div class="form-group"><label class="col-md-3 control-label" for="text-input">词关系：</label>';
					label += '<div class="col-md-9">';
					label +='<table class="table table-bordered table-striped mb-none" id="">';
				    label +='<thead><tr><th>编号</th><th>词名称</th><th>关系项</th></tr></thead>';
				    label +='<tbody>';
				   
				    $.each(res.data.relatetion,function(i,e){
				    	label +='<tr><td>'+e.number+'</td>';
				    	label +='<td>'+e.relateword+'</td>';
				    	label +='<td>'+e.related+'</td></tr>';
					});
				    label +='</tbody></table></div></div>';
				}
				label +='<div class="form-group" style="padding-left: 30%">';
				label +='<button class="btn btn-info btn-sm my_fouradd">上一步</button>';
				label +='<button class="btn btn-info btn-sm my_thirdadd" style="margin-left:30%">确认添加</button></div>';
				$('#dynamicData').append(label);
				$('#secondModal').modal('hide');
				$('#fourModal').modal('show');
				$('.my_fouradd').on('click',function(){
					$('#fourModal').modal('hide');
					$('#secondModal').modal('show');
				});
				$('.my_thirdadd').on('click',function(){
					 window.location.href="/apis/keyInfo/ThesaurusManage.html";
				});
			}
		}
	});
});
//删除关系项
$('.removerelateInfo').on('click',function(){
	 /*var _id =  $(this).parents('.gradeX').find('td').eq(0).text();*/
	 var _id =  $(this).parents('.gradeX').find('.input-block').val();
	 console.log(_id);
	 //删除关系项
	 $.ajax({
		 url:'/apis/keyInfo/deleteRelatedInfoById.json?id='+_id,
		 type:'GET',
		 asynyc:false,
		success:function(res){
			if(res.data != false){
				layer.msg('删除成功', {icon: 1});
			}else{
				layer.msg('删除失败', {icon: 2});
			}
		}
		
	 });
	 window.location.href="/apis/keyInfo/ThesaurusManage.html";
});
//获取所有的关系选项
function getAllRelated(){
	var label='';
	$.ajax({
		url:'/apis/keyInfo/getAllRelated.json',
		type:'GET',
		asynyc:false,
		success:function(res){
			//为词赋值
			$.each(res.data,function (i,e){
				label +='<input type="radio" name="langinfo" value="'+e.id+'"><span>'+e.relatetion+'</span><br />';
			});
			$('#related_info').html(label);
		}
	});
	
};

function selChanceInfo(e){
	//第一,根据的默认值更新词的内容
	var label ='';
	$.ajax({
		url:'/apis/keyInfo/findWordByType.json?typeWord='+e,
		type:'GET',
		asynyc:false,
		success:function(res){
			//为词赋值
			$.each(res.data,function (i,e){
				label +='<input type="checkbox" name="lang" value="'+e.id+'"><span>'+e.keyword+'</span><br />';
			});
			$('#word_info').append(label);
		}
	});
};

//第二,select1的选项变了，词的内容也要变
function changeWord(){
	var _type=$("#select1 option:selected").val();
	$('#word_info').html('');
	selChanceInfo(_type);
};
//文件批量上传
function uploadFile(){
	 var myform = new FormData();
    myform.append('file',$('#load_xls')[0].files[0]);
   /* $.ajax({
   	 url: "/apis/keyInfo/ExcelDataUpload.json",
        type: "POST",
        data: myform,
        contentType: false,
        processData: false,
        success:function(res){
       	 if(res.data != null){
       		 layer.msg(res.data, {icon: 1});
       	 }else{
       		 layer.msg(res.message, {icon: 2});
       	 }
       	 var param ={type:type,pageSize:pageSize,pageNumber:pageNumber};
			  getType(param);
        }
    });*/
    
};
//模态框的回显
$('#addKeywordToTable').on('click',function(){
	$('#select').html(option);
	$('#myModal').modal('show');
	//添加属性的功能
	$('.my_add').on('click',function(){
		$('#demo-form').append(
				'<div class="form-group add_'+index+'">' +
	            '<label class="col-sm-3 control-label" for="state">关联关系</label>' +
	            '<div class="col-sm-3">' +
	            '<input type="text" name="name_'+index+'" class="form-control" placeholder="添加属性名">' +
	            '</div>' +
	            '<div class="col-md-3">' +
	            '<input type="text" name="value_'+index+'" class="form-control" placeholder="添加属性内容">' +
	            '</div>' +
	            '<button class="btn btn-info btn-xs drop_'+index+'">删除 <i class="fa fa-minus"></i></button>' +
	            '</div>'		
		);
		 $(".drop_"+index+"").on("click",function () {
	            var a = $(this).attr('class');
	            var s =a.substring(25,a.length);
	            $(".add_"+s+"").remove();
	            length--;
	        });
	        index++;
	        length++;
	});
});

//下一页  跳转下一页，同时保存信息
$('.my_nextadd').on('click',function(){
	/*//属性值
	var arr = new Array();
	var i=1;
    var j=1;
    while (true){
    		//属性名
    	 	var _attributeName = $("input[name='name_"+i+"']").val();
    	 	//属性值
    	 	var _attributeValue = $("input[name='value_"+i+"']").val();
    	 	 arr.push({
    	 		attributeName:_attributeName,
    	 		attributeValue:_attributeValue
             });
             j++;
             i++;
          if(j==length) break;
          if(i>50) break;
    };
  //产业值
    var _typeWord='';
	var _type=$("#select option:selected").val();
	console.log(_type);
	if(_type==0){
		_typeWord=$("input[name=TypeWord]" ).val();
		if(_typeWord==null){
			layer.msg("新增词为空，不能进行操作", {icon: 2});
		}else{
			var _keyword=$("input[name=keyword]" ).val();
			var _describe=$("input[name=describe]" ).val();
			var _industry=$("input[name=industry]" ).val();
			 arr.push({
	    	 		attributeName:'词说明',
	    	 		attributeValue:_describe
	             },{
		    	 		attributeName:'产业分类',
		    	 		attributeValue:_industry
		             });
			var param={typeId:_type,keyword:_keyword,typeWord:_typeWord,msg:arr};
			$.ajax({
				url:'/apis/keyInfo/addOrUpAttributeDTO.json',
				type:'POST',
				data:JSON.stringify(param),
				asynyc:false,
				contentType:'application/json',
				success:function(res){
					if(res.data==true){
						$('#myModal').modal('hide');
						$('#secondModal').modal('show');
					}else{
						 layer.msg("输入内容有误，请检查", {icon: 2});
					}
				}
			});
		}
	}else{
		var _keyword=$("input[name=keyword]" ).val();
		var _describe=$("input[name=describe]" ).val();
		var _industry=$("input[name=industry]" ).val();
		 arr.push({
   	 		attributeName:'词说明',
   	 		attributeValue:_describe
            },{
	    	 		attributeName:'产业分类',
	    	 		attributeValue:_industry
	             });
		var param={typeId:_type,keyword:_keyword,typeWord:_typeWord,msg:arr};
		$.ajax({
			url:'/apis/keyInfo/addOrUpAttributeDTO.json',
			type:'POST',
			data:JSON.stringify(param),
			asynyc:false,
			contentType:'application/json',
			success:function(res){
				if(res.data==true){
					$('#myModal').modal('hide');
					$('#secondModal').modal('show');
				}else{
					 layer.msg("输入内容有误，请检查", {icon: 2});
				}
			}
		});
	}
	*/
	$('#myModal').modal('hide');
	$('#secondModal').modal('show');
	
});
$('.close').on('click',function(){
	window.location.href="/apis/keyInfo/ThesaurusManage.html";
});
//新增分类
$('#new_add').on('click',function(){
	$('#form3').append(
			'<div class="form-group"><label class="col-md-3 control-label" for="text-input">新增词类</label>'
					+'<div class="col-md-9">'
					+'<input type="text" name="addInfo"  class="form-control" ></div></div>'
	);
	$('#thirdModal').modal('show');
	$('.my_fireadd').on('click',function(){
		var addInfo = $("input[name='addInfo']").val();
		console.log(addInfo);
		$.ajax({
			url:'/apis/keyInfo/updateLabel.json?typeWord='+addInfo,
			type:'GET',
			asynyc:false,
			success:function(res){
				getLabel();
				$('#thirdModal').modal('hide');
			}
		});
	});
});
//新增关系分类
$('.my_relatedadd').on('click',function(){
	$('#form3').append(
			'<div class="form-group"><label class="col-md-3 control-label" for="text-input">新增新关系项</label>'
					+'<div class="col-md-9">'
					+'<input type="text" name="addRelatedInfo"  class="form-control" ></div></div>'
	);
	$('#thirdModal').modal('show');
	$('.my_fireadd').on('click',function(){
		var addInfo = $("input[name='addRelatedInfo']").val();
		console.log(addInfo);
		$.ajax({
			url:'/apis/keyInfo/updateRelated.json?relatedWord='+addInfo,
			type:'GET',
			asynyc:false,
			success:function(res){
				getLabel();
				getAllRelated();
				$('#thirdModal').modal('hide');
			}
		});
	});
});
//获取分类信息
function getLabel(){
	$.ajax({
		url:'/apis/keyInfo/getLabelInfo.json',
		type:'GET',
		asynyc:false,
		success:function(res){
			if(res.data==null){
				 layer.msg(res.message, {icon: 2});
			}else{
				var info ='';
				var label ='';
					label +='<a href="javascript:void(0);" id="0" class="search-item active">全部</a>';
				$.each(res.data,function (i,e){
						label +='<a href="javascript:void(0);" id="'+e.id+'" class="search-item">'+e.typeWord+'</a>';
						option +='<option value="'+e.id+'">'+e.typeWord+'</option>';		                  
						info +='<option value="'+e.id+'">'+e.typeWord+'</option>';		                  
				});
				$('#labelInfo').html(label);
				selChanceInfo(res.data[0].id);
				$('#select1').append(info);
				option +='<option value="'+0+'">新增类别</option>';
				$('#select').append(option);
			}
		}
	});
};
//获取列表信息
function getInfoList(e){
	$.ajax({
		url:'/apis/keyInfo/findKeyWordInfoList.json',
		type:'POST',
		asynyc:false,
		contentType:'application/json',
		data:JSON.stringify(e),
		success:function(res){
			if(res.messge!=null){
				$('#manage_keyword').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
			}else{
				var label='';
				console.log(res.data);
				$.each(res.data.dataList,function (i,e){
					label +='<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+e.entntity.id+'"/><td>';
					label += e.keywordNumber+ '</td><td>';
					label += e.entntity.keyword+ '</td><td>';
					label += e.typeWord+ '</td><td>';
					label += '<a href="javascript:void(0);" class="on-default findinfo1"><i class="fa fa-pencil">查看</i></a></td><td>';
					label += '<a href="javascript:void(0);" class="on-default findinfo2"><i class="fa fa-pencil">查看</i></a></td><td class="actions">';
					label +='<a href="javascript:void(0);" class="on-default editinfo"><i class="fa fa-pencil">编辑</i></a>';
					label +='<a href="javascript:void(0);" class="on-default removeinfo modal-basic"><i class="fa fa-trash-o">删除</i></a></td></tr>';
				});
				$('#manage_keyword').html(label);
				initPage();
				if(res.data.totalPage>1){
					page.init(res.data.totalNumber,res.data.pageNumber,options);
					$("#"+page.pageId +">li[class='pageItem']").on("click",function(){
	            		var param={type:type,pageNumber:$(this).attr("page-data")-1,pageSize:pageSize,sort:sort};
	            		getInfoList(param);
	                });
				}else{
					$('#page').html("");
				}
			}
			
		}
		
	});
};
function initPage(){
	var label ='';
	$('.removeinfo').on('click',function(){
		var _id =  $(this).parents('.gradeX').find('.input-block').val();
		console.log('删除关键词根据id：》》》'+_id);
		$.ajax({
			url:'/apis/keyInfo/deleteRelatedInfoById.json?id='+_id,
			type:'GET',
			asynyc:false,
			success:function(res){
				if(res.data==true){
					 layer.msg("删除成功", {icon: 1});
				}else{
					 layer.msg("删除失败", {icon: 2});
				}
				
			 }
		});
		window.location.href="/apis/keyInfo/ThesaurusManage.html";
	});
	//属性信息查看
	$('.findinfo1').on('click',function(){
		var _id =  $(this).parents('.gradeX').find('.input-block').val();
		var _keyword =  $(this).parents('.gradeX').find('td').eq(1).text();
		console.log('查看关系值属性的两个重要属性'+_id+_keyword);
		
		$.ajax({
			 url:'/apis/keyInfo/findAttributeInfoById.json?id='+_id,
			 type:'GET',
			 asynyc:false,
			 success:function(res){
				 if(res.data!=null){
					console.log(res.data);
					label +='<form id="form4" class="form-horizontal mb-lg" novalidate="novalidate" >'
					label +='<div class="form-group"><label class="col-md-3 control-label" for="text-input">'+_keyword+'词属性</label>'
					if(res.data.attr.length==0){
						label +='<div class="col-md-9">';
						label +='<h4>无</h4>';
						label +='</div>';
					}else{
					$.each(res.data.attr,function(i,e){
						if(i==0){
							label +='<div class="col-md-9">';
							label +='<h4>'+e.attributeName+'</h4>';
      					  	label +='<input type="text" name="dddd" value="'+e.attributeValue+'" class="form-control" placeholder="">';
							label +='</div>';
						}else{
							label +='<div class="col-md-9 list">'
							label +='<h4>'+e.attributeName+'</h4>';
      					  	label +='<input type="text" name="dddd" value="'+e.attributeValue+'" class="form-control" placeholder="">';
							label +='</div>';
							}	
						});
					}	
						label +='</form>';
						label +='<div class="form-group" style="padding-left: 30%"><button class="btn btn-info btn-xs my_attribute_info">确认</button></div>'
					$('#dynamicData').append(label);
					$('#fourModal').modal('show');
					 $('.my_attribute_info').on('clik',function(){
						 window.location.href="/apis/keyInfo/ThesaurusManage.html";
					 });
				 }else{
					 layer.msg("查看属性信息失败", {icon: 2});
				 }
			 }
		});
	});
	label='';
	//词关系信息查看
	$('.findinfo2').on('click',function(){
		var _id =  $(this).parents('.gradeX').find('.input-block').val();
		var _keyword =  $(this).parents('.gradeX').find('td').eq(1).text();
		console.log('查看关系值'+_id+_keyword);
		$.ajax({
			 url:'/apis/keyInfo/findRelatedInfoById.json?id='+_id,
			 type:'GET',
			 asynyc:false,
			 success:function(res){
				 if(res.data!=null){
					 console.log(res.data);
					 $('#dynamicData').append();
					 label +='<form id="form4" class="form-horizontal mb-lg" novalidate="novalidate" >'
					 label +='<div class="form-group"><label class="col-md-3 control-label" for="text-input">'+_keyword+'词关系</label>'
					 if(res.data.result.length==0){
						label +='<div class="col-md-9">'
							label +='<h4>无</h4>';
						label +='</div></div>';
					}else{
						label +='<div class="col-md-9">';
					    label +='<table class="table table-bordered table-striped mb-none" id="">';
					    label +='<thead><tr><th>编号</th><th>词名称</th><th>关系项</th><th>操作</th></tr></thead>';
					    label +='<tbody>';
					    $.each(res.data.result,function(i,e){
					    	label +='<tr><td>'+e.relate_keyword_id+'</td>';
					    	label +='<td>'+e.relate_keyword+'</td>';
					    	label +='<td>'+e.related+'</td>';
					    	label +='<td><a href="javascript:void(0);" class="on-default editRelateInfo"><i class="fa fa-pencil">编辑</i></a>';
							label +='<a href="javascript:void(0);" class="on-default removeRelateInfo modal-basic"><i class="fa fa-trash-o">删除</i></a></td></tr>';
						});
					    label +='</tbody></table></div></div>';
					}
					 	label +='</form>';
					 	label +='<div class="form-group" style="padding-left: 30%"><button class="btn btn-info btn-xs my_relatetion_info">确认</button></div>'
					 		$('#dynamicData').append(label);
					 	$('.my_relatetion_info').on('clik',function(){
					 		window.location.href="/apis/keyInfo/ThesaurusManage.html";
					 	});
					 $('#fourModal').modal('show');
				 }else{
					 layer.msg("查看词关系信息失败", {icon: 2});
				 }
			 }
		});
	});
	label='';
};
type='0';
