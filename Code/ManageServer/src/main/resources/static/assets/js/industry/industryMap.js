$('#industry_info').addClass("active");
var industry ="人工智能";
$(function () {
	findMapInfo(industry);
});



$(".search-box").on("click",".search-item-content>a",function(){
	$(this).addClass("active").siblings().removeClass("active");
	var _id = $(this).attr("id");
	console.log(_id);
	industry = _id;
	findMapInfo(_id);
	
});

function findMapInfo(e){
	console.log("产业类型为:"+e);
	 $.ajax({
	        type : "GET",
	        async:false,
	        url : "/apis/industrymap/findIndustryMapInfo.json?industry="+e,
	        success:function(res){
	        	if(res.message!=null){
	        		$('#industryRank').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
	        		$('#industryinst').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
	        		$('#indusrySummit').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
	        	}else{
	        		var data = 	res.data;
	        		console.log(data);
	        		getRankInfo(data.rank);
	        		getSummitInfo(data.summit);
	        		getCountInfo(data.count);
	        	}
	        	 initRankInfo();
	        	 initSummitInfo();
	        }
	 });
};
//获取产业热度排行数据
function getRankInfo(e){
	var arr = e;
	console.log(e);
	if(arr == null){
		$('#industryRank').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
	}else{
		var string = '';
		for(var i = 0; i<arr.length;i++){
		 string += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td>' 
		 + arr[i].id+ '</td><td>' 
		 + arr[i].area + '</td><td>' 
		 + arr[i].count + '</td><td>'
		 + arr[i].industry + '</td>'
		 + '<td class="actions"><a href="javascript:void(0);" class="hidden on-editing save-rankinfo"><i class="fa fa-save"></i></a>'
		 + '<a href="javascript:void(0);" class="hidden on-editing cancel-rankinfo"><i class="fa fa-times"></i></a>'
		 + '<a href="javascript:void(0);" class="on-default edit-rankinfo"><i class="fa fa-pencil"></i></a>'
		 + '<a href="javascript:void(0);" class="on-default remove-rankinfo"><i class="fa fa-trash-o"></i></a></td></tr>';
		
		}
		$("#industryRank").html(string);
	}
};
//获取产业峰会数据
function getSummitInfo(e){
	var arr = e;
	console.log(e);
	if(arr==null){
		$('#indusrySummit').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
	}else{
		var string = '';
		for(var i = 0; i<arr.length;i++){
		 string += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td>' 
		 + arr[i].id+ '</td><td>' 	
		 + arr[i].publishTime+ '</td><td>' 	
		 +'<a href="'+ arr[i].articleLink+'" target="_blank">'+ arr[i].title+ '</a></td><td>' 
		 + arr[i].exhibitiontime + '</td><td>' 
		 + arr[i].idustryZero + '</td><td>'
		 + arr[i].idustryTwice + '</td><td>'
		 + arr[i].idustryThree + '</td>'
		 + '<td class="actions"><a href="javascript:void(0);" class="on-default editSummitInfo"><i class="fa fa-pencil"></i></a>'
         +'<a href="javascript:void(0);" class="on-default removeSummitInfo modal-basic"><i class="fa fa-trash-o"></i></a></td></tr>'
         
		}
		$("#indusrySummit").html(string);
	}
};
//获取重点实验室数量
function getCountInfo(e){
	var arr = e;
	console.log(e);
    if(arr==null){
    	$('#industryinst').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
	}else{
		var string = '';
		for(var i = 0; i<arr.length;i++){
			var array = arr[i];
		 string += '<tr class="gradeX"><td>' 
		 + array[0]+ '</td><td>' 
		 + array[1] + '</td><td>' 
		 + industry + '</td>'
		 + '<td class="actions"><a href="javascript:void(0);" class="hidden on-editing save-info"><i class="fa fa-save"></i></a>'
		 + '<a href="javascript:void(0);" class="hidden on-editing cancel-info"><i class="fa fa-times"></i></a>' + '<a href="javascript:void(0);" class="on-default edit-info"><i class="fa fa-pencil"></i></a>'
		 + '<a href="javascript:void(0);" class="on-default remove-info"><i class="fa fa-trash-o"></i></a></td></tr>';
		}
		$("#industryinst").html(string);
	}
};
function deleteRank(_id){
	 $.ajax({
	     type : "GET",
	     async:false,
	     url : "/apis/industrymap/deleteRankInfoById.json?id="+_id,
	     success:function(res){
	    	 if(res.message != null){
	    		 layer.msg(res.message, {icon: 2});
	    	 }else{
	    		 layer.msg("操作成功", {icon: 2});
	    		 window.location.href="/apis/industrymap/industryMapManage.html";
	    	 }
	     }   
	 });
};
function saveOrUpdateRank(param){
	$.ajax({
        type : "post",
        contentType : "application/json",
        async:false,
        url : "/apis/industrymap/saveOrUpdateRankInfo.json",
        data : JSON.stringify(param),
        success : function(res) {
            if(res.success){
                layer.msg('操作成功', {icon: 1});
                window.location.href="/apis/industrymap/industryMapManage.html";
            }else{
                layer.msg(res.message, {icon: 2});
            }
        }
    });
};
function initRankInfo(){
	//删除一条数据
	$(".remove-rankinfo").on("click",function(i){
		var id = $(this).parents('.gradeX').find( 'input' ).val();
		deleteRank(id);
	});
	//添加一条数据
	$('#addRankToTable').on("click",function(i){
		$(this).attr("disabled",true);
		var html = '<tr role="row" class="adding odd"><td class="sorting_1"><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td class="actions"><a href="#" class="on-editing save-rankinfo">' +
			'<i class="fa fa-save"></i></a> <a href="#" class="on-editing cancel-rankinfo">' +
			'<i class="fa fa-times"></i></a> <a href="#" class="on-default edit-rankinfo hidden">'+
			'<i class="fa fa-pencil"></i></a> <a href="#" class="on-default remove-rankinfo hidden">'+
			'<i class="fa fa-trash-o"></i></a></td></tr>';
		$('#industryRank').children().eq(0).before(html);
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
			var param = {"id":indusArr[0],"area":indusArr[1],"count":indusArr[2],"industry":indusArr[3]}
			saveOrUpdateRank(param);
		});
		//取消
		$(".cancel-rankinfo").on("click",function(){
			window.location.reload();
		});
	});
	//修改一条数据
	$(".edit-rankinfo").on("click",function(){
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
	//保存按钮
	$('.save-rankinfo').on("click",function(){
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
		var param = {"id":indusArr[1],"area":indusArr[2],"count":indusArr[3],"industry":indusArr[4]}
		saveOrUpdateRank(param);
		
	});
	//取消按钮
	$(".cancel-rankinfo").on("click",function(){
		window.location.reload();
	})
	
};


function initSummitInfo(){
	 $(".editSummitInfo").on("click",function(){
	        var _id = $(this).parents('.gradeX').find( 'input' ).val();
	        window.location.href="/apis/industrysummit/editIndustrySummit.html?id="+_id;
	    })
	   $("#addSummitToTable").on("click",function(i){
	        window.location.href="/apis/industrysummit/editIndustrySummit.html";
	    })
	    $(".removeSummitInfo").on("click",function(i){
	        var _id = $(this).parents('.gradeX').find( 'input' ).val();
	        layer.confirm('确定要删除该数据？', {
	            btn: ['确认','取消'] //按钮
	        }, function(){
	            $.ajax({
	                async:false,
	                url : "/apis/industrysummit/deleteIndustrySummit.json?id="+_id,
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
}