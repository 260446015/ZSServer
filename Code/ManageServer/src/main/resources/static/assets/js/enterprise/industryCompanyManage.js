$("#company_indus").addClass("active nav-expanded ");
$("#insutryCompany_item").addClass("active");
$(function() {
    showIndusCompany();
});

function showIndusCompany() {
   
    $.ajax({
        type : "GET",
        async:false,
        url : "/apis/inds/listIndustInfo.json",
        success : function(res) {
            if (res.success) {
                    var arr = res.data;
                    var html = '';
                    for (var i = 0; i < arr.length; i++) {
                        html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td>'
                        		+ arr[i].id+ '</td><td>' 
                        		+ arr[i].industry + '</td><td>' 
                        		+ arr[i].company+ '</td><td>' 
                        		+ arr[i].companyName + '</td><td>' 
                        		+ arr[i].industryLabel+ '</td><td>'
                        		+ arr[i].induszero + '</td><td>' 
                        		+ arr[i].createTime+ '</td><td>' 
                        		+ arr[i].updateTime + '</td><td class="actions"><a href="javascript:void(0);" class="hidden on-editing my_save"><i class="fa fa-save"></i></a>'
                       		+ '<a href="javascript:void(0);" class="hidden on-editing my_cancel"><i class="fa fa-times"></i></a>'
                     		+ '<a href="javascript:void(0);" class="on-default my_edit"><i class="fa fa-pencil"></i></a>'
                            + '<a href="javascript:void(0);" class="on-default my_remove modal-basic"><i class="fa fa-trash-o"></i></a></td></tr>';
                    }
                    $("#industry_list").html(html);
            }else{
                layer.msg(res.message, {icon: 2});
            }
        }
    });
    initPage();
}

function initPage(){
	/*//新增一条数据
	$('.my_add').on('click',function(){
		$(this).attr("disabled",true);
		var html = '<tr role="row" class="adding odd"><td class="sorting_1"><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td class="actions"><a href="#" class="on-editing save-rankinfo">' +
			'<i class="fa fa-save"></i></a> <a href="#" class="on-editing cancel-rankinfo">' +
			'<i class="fa fa-trash-o"></i></a></td></tr>';
		$('#industry_list').children().eq(0).before(html);
		//保存
		$('.save-rankinfo').on("click",function(){
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
			var param = {"id":indusArr[0],"industry":indusArr[1],"company":indusArr[2],"companyName":indusArr[3],
					"induszero":indusArr[4],"industryLabel":indusArr[5],"createTime":indusArr[6],"updateTime":indusArr[7]}
			saveOrUpdateInfo(param);
		});
		//取消
		$(".cancel-rankinfo").on("click",function(){
			window.location.reload();
		});
	});*/
	//保存按钮
	$('.my_save').on("click",function(){
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
		
		var param = {"id":indusArr[1],"industry":indusArr[2],"company":indusArr[3],"companyName":indusArr[4],
				"induszero":indusArr[5],"industryLabel":indusArr[6],"createTime":indusArr[7],"updateTime":indusArr[8]}
		saveOrUpdateInfo(param);
		
	});
      //修改一条数据
      $(".my_edit").on("click",function(){
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
    	});
    //删除一条数据
    $(".my_remove").on("click",function(i){
        var _id = $(this).parents('.gradeX').find( 'input' ).val();
        layer.confirm('确定要删除该数据？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            $.ajax({
                async:false,
                url : "/apis/indus/deleteIndusInfo.json?id="+_id,
                success : function(res) {
                    if(res.success){
                        layer.msg('成功删除', {icon: 1});
                        showIndusCompany();
                    }else{
                        layer.msg(res.message, {icon: 2});
                    }
                }
            });
        });
    });
    
  //取消按钮
	$(".my_cancel").on("click",function(){
		window.location.reload();
	})
};
function saveOrUpdateInfo(e){
	$.ajax({
		type:"POST",
		url:"/apis/inds/addOrUpdateIndusInfo.json",
		async:false,
		contentType : "application/json",
		data : JSON.stringify(e),
        success:function(res){
        	if(res.message == null){
        		layer.msg('添加/保存成功', {icon: 1});
                showIndusCompany();
        	}else{
                layer.msg('添加/保存失败', {icon: 2});
        	}
        }
	          
	});
};