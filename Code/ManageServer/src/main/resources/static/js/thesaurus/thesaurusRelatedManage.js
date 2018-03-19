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
	/*console.log(e);
	console.log(e.keyword);
	console.log(e.type);
	console.log("词语描述："+e.desc);
	console.log("词语解释性："+e.exp);
	console.log("词语业务性："+e.kbs);
	console.log(e.relate);
	var array = [];
	array.push(e.keyword+"("+e.type+")",e.type,e.desc,e.exp,e.kbs,e.relate);
	console.log(array);
	*/
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
						+'<input type="text" name="name" class="form-control" placeholder="'+item[i].rkeyword+'" value="'+item[i].rkeyword+'('+item[i].rtype+') " required/>'
						+'</div>'
						+'<label class="col-sm-3 control-label">关联关系</label>'
						+'<div class="col-sm-9">'
						+'<input type="text" name="name" class="form-control" placeholder="'+item[i].related+'" value="'+item[i].related+'" required/>'
						+'</div>'
						+'</div>'	
						+'</div></div>'	);
				}
			}
		}else if(index=="desc"){
			arr.push(
					'<div class="form-group"><label class="col-sm-3 control-label">关键词描述</label>'
					+'<div class="col-sm-9">'
					+'<input type="text" name="name" class="form-control" placeholder="'+item+'" value="'+item+'" required/>'
					+'</div></div>'		
			);
		}else if(index=="exp"){
			arr.push(
					'<div class="form-group"><label class="col-sm-3 control-label">解释性关键词</label>'
					+'<div class="col-sm-9">'
					+'<input type="text" name="name" class="form-control" placeholder="'+item+'" value="'+item+'" required/>'
					+'</div></div>'		
			);
		}else if(index=="kbs"){
			arr.push(
					'<div class="form-group"><label class="col-sm-3 control-label">业务性关键词</label>'
					+'<div class="col-sm-9">'
					+'<input type="text" name="name" class="form-control" placeholder="'+item+'" value="'+item+'" required/>'
					+'</div></div>'		
			);
		}
	});
	var inner = arr.join('');
	return inner;
};

$(".btn-danger").on("click",function(){
    layer.confirm('直接离开将会失去修改内容，确认离开？', {
        btn: ['确认','取消'] //按钮
    }, function(){
        window.location.href="/apis/keyInfo/ThesaurusManage.html";
    });
});