
var industry = "全部";
$(function(){
	$('#industry_info').addClass("active");
	getIndustry(0,0);
});


$(".search-box").on("click",".search-item-content>a",function(){
	$(this).addClass("active").siblings().removeClass("active");
	var _id = $(this).attr("id");
	console.log(_id);
	var array = _id.split('-');
	var a = array[0];
	var b = array[1];
	getIndustry(a,b);
});

function getIndustry(a,b){
	if(a==1){
		industry = b;
	}
	SummitInfo(industry);
};
function SummitInfo(e){
	$.ajax({
		type:'GET',
		url:'/apis/institution/findInstitutionalInfo.json?industry='+e,
		asynyc:false,
		success:function(res){
			if( res.message != null){
				$('#institutionalList').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
			}else{
				$('#institutionalList').html(show(res.data));
				
			}
			initPage();
		 }
			
		});
};
function show(data){
	var arr=[];
	$.each(data,function(index,item){
		arr.push(
			'<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+item.id+'"/><td>' 
			+item.id+ '</td><td>'
			+item.laboratoryName+ '</td><td>'
			+item.institutionalCategory + '</td><td>' 
			+item.industry + '</td><td>'
			+item.area + '</td><td>'
			+item.supportUnit + '</td><td>'
			+item.academicLeader + '</td><td>'
			+item.url + '</td><td>'
			+item.label + '</td><td class="actions">'
            +'<a href="javascript:void(0);" class="hidden on-editing saveinsinfo"><i class="fa fa-save"></i></a>'
			+ '<a href="javascript:void(0);" class="hidden on-editing cancelinsinfo"><i class="fa fa-times"></i></a>'
			+ '<a href="javascript:void(0);" class="on-default editinsinfo"><i class="fa fa-pencil"></i></a>'
			+ '<a href="javascript:void(0);" class="on-default removeinsinfo"><i class="fa fa-trash-o"></i></a></td></tr>'
		);
	});
	var inner = arr.join('');
	return inner;
};
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
	$(".saveinsinfo").on("click",function(i){
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
		var indus = {"id":indusArr[1],"laboratoryName":indusArr[2],"institutionalCategory":indusArr[3],
				"industry":indusArr[4],"area":indusArr[5],"supportUnit":indusArr[6],"academicLeader":indusArr[7],
		"url":indusArr[8],"label":indusArr[9]}
		layer.confirm('确定要保存该数据？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            $.ajax({
            	type:'POST',
        		asynyc:false,
        		contentType:'application/json',
        		data:JSON.stringify(indus),
                url : "/apis/institution/insertInstitutionInfo.json",
                success : function(res) {
                    if(res.success){
                        layer.msg('保存成功', {icon: 1});
                        getIndustry(0,0);
                    }else{
                        layer.msg(res.message, {icon: 2});
                    }
                }
            });
        });
	})
	$("#addInsInfoToTable").on("click",function(i){
		$(this).attr("disabled",true);
		var html = '<tr role="row" class="adding odd"><td class="sorting_1"><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td><input class="form-control input-block" value="" type="text"></td>' +
			'<td class="actions"><a href="#" class="on-editing saveinsinfo">' +
				'<i class="fa fa-save"></i></a> <a href="#" class="on-editing cancelinsinfo">' +
				'<i class="fa fa-times"></i></a> <a href="#" class="on-default editinsinfo hidden">'+
				'<i class="fa fa-pencil"></i></a> <a href="#" class="on-default removeinsinfo hidden">'+
				'<i class="fa fa-trash-o"></i></a></td></tr>';
		$("#institutionalList").children().eq(0).before(html);
		$(".saveinsinfo").on("click",function(i){
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
			var indus = {"id":indusArr[0],"laboratoryName":indusArr[1],"institutionalCategory":indusArr[2],
					"industry":indusArr[3],"area":indusArr[4],"supportUnit":indusArr[5],"academicLeader":indusArr[6],
			"url":indusArr[7],"label":indusArr[8]};
			layer.confirm('确定要保存该数据？', {
	            btn: ['确认','取消'] //按钮
	        }, function(){
	            $.ajax({
	            	type:'POST',
	        		asynyc:false,
	        		contentType:'application/json',
	        		data:JSON.stringify(indus),
	                url : "/apis/institution/insertInstitutionInfo.json",
	                success : function(res) {
	                    if(res.success){
	                        layer.msg('保存成功', {icon: 1});
	                        getIndustry(0,0);
	                    }else{
	                        layer.msg(res.message, {icon: 2});
	                    }
	                }
	            });
	        });
		})
		$(".cancelinsinfo").on("click",function(){
			window.location.reload();
		})
	})
	$(".removeinsinfo").on("click",function(i){
		var id = $(this).parents('.gradeX').find( 'input' ).val();
		 layer.confirm('确定要删除该数据？', {
	            btn: ['确认','取消'] //按钮
	        }, function(){
	            $.ajax({
	                async:false,
	                url : "/apis/institution/deleteInstitutionInfo.json?id="+_id,
	                success : function(res) {
	                    if(res.success){
	                        layer.msg('成功删除', {icon: 1});
	                        getIndustry(0,0);
	                    }else{
	                        layer.msg(res.message, {icon: 2});
	                    }
	                }
	            });
	        });
	})
	$(".cancelinsinfo").on("click",function(){
		window.location.reload();
	})
};
