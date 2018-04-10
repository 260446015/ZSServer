var _id;

var index=1;
var length=1;
var option="";
var ind ;
function editData(info){
	_id = info;
	 $.ajax({
		 	type:'GET',
			url:'/apis/keyInfo/findRelatedInfoById.json?id='+_id,
			asynyc:false,
			success:function(res){
				if(res.message!=null){
					layer.msg(res.message, {icon: 2});
				}else{
					if(res.data.result==true){
						$('input[name="id"]').val(res.data.id);
						$('input[name="keyword"]').val(res.data.keyword);
						$('input[name="type"]').val(res.data.type);
						$('input[name="describe"]').val(res.data.desc);
						if(res.data.attribute==false){
							$('#attributeInfo').html(
									'<label class="col-sm-3 control-label">属性</label><div class="col-md-3">'+
								'<input type="text" name="attribute1" class="form-control" placeholder="请填写关键词的描述" value="无" required/></div>'
							)
						}else{
							$('#attributeInfo').html(
									
							);
						}
						$('#fomr-info').html(ShowRelatedInfo(res.data.relate));
						$(".selectpicker" ).selectpicker('refresh');
						var param ={type:"全部",pageSize:100,pageNumber:0};
						   $.ajax({
								type:'POST',
								url:'/apis/keyInfo/findKeyWordInfo.json',
								asynyc:false,
								contentType:'application/json',
								data:JSON.stringify(param),
								success:function(response){
						                if(response.success){
						                    $.each(response.data.dataList,function (i,e) {
						                        option+="<option value='"+e.entntity.id+"'>"+e.entntity.keyword+"("+e.entntity.type+")</option>";
						                    });
						                }else{
						                    layer.alert(response.message);
						                }
						            }
						   });
						   //添加关系事件
						$(".my_addinfo").on("click",function(){
						    $("#fomr-info").append('<div class="form-group add_'+index+'">' +
						        '<label class="col-sm-3 control-label" for="state">关联关系</label>' +
						        '<div class="col-sm-3">' +
						        '<select name="dealer_'+index+'" id="dealer_'+index+'" class="selectpicker show-tick form-control"  data-width="98%" data-first-option="false" required data-live-search="true">' +
						        option +'</select>' +
						        '</div>' +
						        '<div class="col-md-3">' +
						        '<input type="text" name="name_'+index+'" class="form-control" placeholder="请填写关联关系">' +
						        '</div>' +
						        '<button class="btn btn-info btn-xs drop_'+index+'">删除 <i class="fa fa-minus"></i></button>' +
						        '</div>');
						    $(".selectpicker" ).selectpicker('refresh')
						    $(".drop_"+index+"").on("click",function () {
						        var a = $(this).attr('class');
						        var s =a.substring(25,a.length);
						        $(".add_"+s+"").remove();
						        length--;
						    });
						    index++;
						    length++;
						});
						
						
					}
				}
			}
	 });
};
var m=1;
$(function(){
	
$('.btn-success').on('click',function(){
	var keyword = $('input[name="keyword"]').val();
	var type = $('input[name="type"]').val();
	var _id = $('input[name="id"]').val();
	var descrip = $('input[name="describe"]').val();
	var arr = new Array();
	var array = new Array();
    var i=1;
    var j=1;
      while (true){
          var _options=$("#dealer_"+i+" option:selected").val();
          var _describe = $("input[name='name_"+i+"']").val();
          if(typeof(_options) != "undefined"){
              arr.push({
                  options:_options,
                  describe:_describe
              });
              j++;
          }
          i++;
          if(j==length) break;
          if(i>50) break;
      }
    
      var n=m;
        while (true){
            var _options=$("#dept_"+m+" option:selected").val();
            var _describe = $("input[name='relate_"+m+"']").val();
            if(typeof(_options) != "undefined"){
            	array.push({
                    options:_options,
                    describe:_describe
                });
                n++;
            }
            m++;
            if(n==length) break;
            if(m>50) break;
        }
        for(var i = 0;i<array.length;i++){
        	arr.push({
        		 options:array[i].options,
                 describe:array[i].describe
        	})
        }
        var param={"id":_id,"name":keyword,"type":type,"descrip":descrip,"msg":arr};
        $.ajax({
        	type:'POST',
        	url:'/apis/keyInfo/addOrUpdateKeyWordData.json',
        	asynyc:false,
    		contentType:'application/json',
    		data:JSON.stringify(param),
    		success:function(res){
    			if(res.data==true){
    				window.location.href="/apis/keyInfo/ThesaurusManage.html";
    			}else{
    				window.location.href="/apis/keyInfo/addThesaurus.html";
    			}
    		}
        });
     
});
$(".btn-danger").on("click",function(){
    layer.confirm('直接离开将会失去修改内容，确认离开？', {
        btn: ['确认','取消'] //按钮
    }, function(){
        window.location.href="/apis/keyInfo/ThesaurusManage.html";
    });
});
});
$()
function ShowRelatedInfo(e){
	var arr = [];
	if(e!=false){
		var ar=e;
		var html ='';
		m=ar[0].r_id;
		for(var i = 0;i<ar.length;i++){
			html +='<div class="form-group '+ar[i].r_id+'"><label class="col-sm-3 control-label">关联关系</label>'
			html +='<div class="col-md-3">'
			html +=	'<select name="dept_'+ar[i].r_id+'" id="dept_'+ar[i].r_id+'" class="selectpicker show-tick form-control"  data-width="98%" data-first-option="false" required data-live-search="true">' 
			html +='<option value="'+ar[i].ralate_id+'">'+ar[i].rkeyword+'('+ar[i].rtype+')</option></select>'
			html +='</div>'
			html +='<div class="col-md-3">' 
			html +='<input type="text" name="relate_'+ar[i].r_id+'"  value="'+ar[i].related+'" class="form-control" placeholder="请填写关联关系" required/>'
			html +='</div><button class="btn btn-info btn-xs derop_'+ar[i].r_id+' " onclick=derop('+ar[i].r_id+')>删除 <i class="fa fa-minus"></i></button> </div>'
		}
		arr.push(html);	
	}
	var inner = arr.join('');
	return inner;
};	
function derop(e){
	$.ajax({
		type:'GET',
		url:'/apis/keyInfo/deleteRelateById.json?id='+e,
		asynyc:false,
		success:function(res){
			if(res.data!=null){
				window.location.href='/apis/keyInfo/ThesaurusRelatedManage.html?id='+_id;
			}else{
				layer.msg(res.message, {icon: 2});
			}
			
		}
	});
}