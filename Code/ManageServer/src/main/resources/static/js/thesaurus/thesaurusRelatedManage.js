var _id;
function editData(info){
	console.log(info);
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
						$('#form-info').html(ShowRelatedInfo(res.data));
					}
				}
			}
	 });
};
function ShowRelatedInfo(e){
	var arr = [];
	$.each(e,function(index,item){
		if(index=="keyword"){
			arr.push(
					'<div class="form-group mt-lg"><label class="col-sm-3 control-label">关键词</label>'
					+'<div class="col-sm-9">'
					+'<input type="text" name="name" class="form-control" placeholder="'+item+'" value="'+item+'" required/>'
					+'</div></div>'		
			);
		}else if(index=="type"){
			arr.push(
					'<div class="form-group"><label class="col-sm-3 control-label">词性</label>'
					+'<div class="col-sm-9">'
					+'<select id="select" name="select" class="form-control input-lg" size="1">'
					+'	<option value="0">'+item+'</option>'
					+'	<option value="1">人名</option>'
					+'	<option value="2">产业</option>'
					+'	<option value="3">企业</option>'
					+'	<option value="3">地域</option>'
					+'	</select>'
					
					+'</div></div>'		
			);
		}else if(index=="relate"){
			if(item==false){
			}else{
				for(var i =0;i<item.length;i++){
					arr.push(
						'<div class="form-group"><label class="col-sm-3 control-label">关联词</label>'
						+'<div class="col-sm-9">'
						+'<input type="text" name="name" class="form-control" placeholder="'+item[i].rkeyword+'" value="'+item[i].rkeyword+'" required/>'
						+'</div></div>'	
						+'<div class="form-group"><label class="col-sm-3 control-label">关联关系</label>'
						+'<div class="col-sm-9">'
						+'<input type="text" name="name" class="form-control" placeholder="'+item[i].related+'" value="'+item[i].related+'" required/>'
						+'</div></div>'	
						+'<div class="form-group"><label class="col-sm-3 control-label">关联词词性</label>'
						+'<div class="col-sm-9">'
						+'<select id="select" name="select" class="form-control input-lg" size="1">'
						+'	<option value="0">'+item[i].rtype+'</option>'
						+'	<option value="1">人名</option>'
						+'	<option value="2">产业</option>'
						+'	<option value="3">企业</option>'
						+'	<option value="3">地域</option>'
						+'	</select>'
//						+'<input type="text" name="name" class="form-control" placeholder="'+item[i].rtype+'" value="'+item[i].rtype+'" required/>'
						+'</div></div>'	
					);
				}
			}
		}
	});
	var inner = arr.join('');
	return inner;
};