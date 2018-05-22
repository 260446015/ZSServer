$('#yq_info').addClass("active");
var pageSize = 20;
var pageNum = 0;
$(function(){
	showList(pageNum,pageSize);
});
function showList(_pageNum,_pageSize){
	var req = {
	        "pageNum" : _pageNum,
	        "pageSize" : _pageSize
	  };
	 $.ajax({
	        type : "post",
	        contentType : "application/json",
	        async:false,
	        url : "/apis/yq/listCompany.json",
	        data : JSON.stringify(req),
	        success:function(res){
	        	if(res.success){
	        		if(res.data.totalPage>0){
	        			var info = res.data.dataList;
	        			var string = '';
	        			for(var i =0 ;i <info.length;i++){
	        				string += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+info[i].id+'"/><td>' 
	        				+ info[i].id+ '</td><td>' 
	        				+ info[i].keyword + '</td><td>' 
	        				+ info[i].adword + '</td><td>' 
	        				+ info[i].index + '</td><td>' 
	        				+ info[i].createTime+ '</td><td>' + 
	        				info[i].updateTime + '</td><td class="actions"><a href="javascript:void(0);" class="hidden on-editing save-info"><i class="fa fa-save"></i></a>'
	        				+ '<a href="javascript:void(0);" class="hidden on-editing cancel-info"><i class="fa fa-times"></i></a>' + '<a href="javascript:void(0);" class="on-default edit-info"><i class="fa fa-pencil"></i></a>'
							+ '<a href="javascript:void(0);" class="on-default remove-info"><i class="fa fa-trash-o"></i></a></td></tr>';
	        			}
	        			$("#yq_companyList").html(string);
	        			page.init(res.data.totalNumber,res.data.pageNumber,options);
	                    $("#"+page.pageId +">li[class='pageItem']").on("click",function(){
	                    	showList($(this).attr("page-data")-1,pageSize);
	                    });
	        		}else{
	        			$('#yq_companyList').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
	        			$('#page').html('');
	        		}
	        	}else{
	        		 layer.msg(res.message, {icon: 2});
	        	}
	        }
	 });
	 initInfo();
};
var options={
	    "id":"page",//显示页码的元素
	    "data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":20,//每页显示数据个数
	    "callBack":function(){}
};
function initInfo(){
	//删除一条数据
	$(".remove-info").on("click",function(i){
		var id = $(this).parents('.gradeX').find( 'input' ).val();
		delCompany(id);
	});
	//添加一条数据
	$('#addTable').on("click",function(i){
		$(this).attr("disabled",true);
		var html = '<tr role="row" class="adding odd"><td class="sorting_1"><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td class="actions"><a href="#" class="on-editing save-info">' +
			'<i class="fa fa-save"></i></a> <a href="#" class="on-editing cancel-info">' +
			'<i class="fa fa-times"></i></a> <a href="#" class="on-default edit-info hidden">'+
			'<i class="fa fa-pencil"></i></a> <a href="#" class="on-default remove-info hidden">'+
			'<i class="fa fa-trash-o"></i></a></td></tr>';
		$('#yq_companyList').children().eq(0).before(html);
		//保存
		$('.save-info').on("click",function(){
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
			var indus = {"id":indusArr[0],"keyword":indusArr[1],"adword":indusArr[2],"index":indusArr[3],"createTime":indusArr[4],"updateTime":indusArr[5]}
			insertCompany(indus);
			
		});
		//取消
		$(".cancel-info").on("click",function(){
			window.location.reload();
		});
	});
	//修改一条数据
	$(".edit-info").on("click",function(){
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
	$('.save-info').on("click",function(){
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
		var indus = {"id":indusArr[1],"keyword":indusArr[2],"adword":indusArr[3],"index":indusArr[4],"createTime":indusArr[5],"updateTime":indusArr[6]}
		insertCompany(indus);
		
	});
	//取消按钮
	$(".cancel-info").on("click",function(){
		window.location.reload();
	})
}
function delCompany(_id){
	$.ajax({
		async:false,
		url : "/apis/yq/delCompany.json?id="+_id,
		success : function(res) {
			if(res.success){
				showList(pageNum,pageSize);
			}
		}
	});
};
function insertCompany(_indus){
	$.ajax({
		type : "post",
		contentType : "application/json",
		async:false,
		url : "/apis/yq/saveCompany.json",
		data : JSON.stringify(_indus),
		success : function(res) {
			if(res.success){
				showList(pageNum,pageSize);
			}
		}
	});
};