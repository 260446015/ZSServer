$('#yq_info').addClass("active");
var pageSize = 20;
var pageNum = 0;
$(function(){
	showKeyWordList(pageNum,pageSize);
});
function showKeyWordList(_pageNum,_pageSize){
	var req = {
	        "pageNum" : _pageNum,
	        "pageSize" : _pageSize
	  };
	 $.ajax({
	        type : "post",
	        contentType : "application/json",
	        async:false,
	        url : "/apis/keywordinfo/listKeyWord.json",
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
	        				+ info[i].proportion + '</td><td>' 
	        				+ info[i].industry + '</td><td class="actions"><a href="javascript:void(0);" class="hidden on-editing save-keyinfo"><i class="fa fa-save"></i></a>'
	        				+ '<a href="javascript:void(0);" class="hidden on-editing cancel-keyinfo"><i class="fa fa-times"></i></a>' + '<a href="javascript:void(0);" class="on-default edit-keyinfo"><i class="fa fa-pencil"></i></a>'
							+ '<a href="javascript:void(0);" class="on-default remove-keyinfo"><i class="fa fa-trash-o"></i></a></td></tr>';
	        			}
	        			$("#yq_keyword").html(string);
	        			page.init(res.data.totalNumber,res.data.pageNumber,options);
	                    $("#"+page.pageId +">li[class='pageItem']").on("click",function(){
	                    	showKeyWordList($(this).attr("page-data")-1,pageSize);
	                    });
	        		}else{
	        			$('#yq_keyword').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
	        			$('#page').html('');
	        		}
	        	}else{
	        		 layer.msg(res.message, {icon: 2});
	        	}
	        }
	 });
	 initKeyWordInfo();
};
var options={
	    "id":"page",//显示页码的元素
	    "data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":20,//每页显示数据个数
	    "callBack":function(){}
};
function initKeyWordInfo(){
	//删除一条数据
	$(".remove-keyinfo").on("click",function(i){
		var id = $(this).parents('.gradeX').find( 'input' ).val();
		deleteKeyWord(id);
	});
	//添加一条数据
	$('#addOneToTable').on("click",function(i){
		$(this).attr("disabled",true);
		var html = '<tr role="row" class="adding odd"><td class="sorting_1"><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td><input class="form-control input-block" value="" type="text"></td>' +
		'<td class="actions"><a href="#" class="on-editing save-keyinfo">' +
			'<i class="fa fa-save"></i></a> <a href="#" class="on-editing cancel-keyinfo">' +
			'<i class="fa fa-times"></i></a> <a href="#" class="on-default edit-keyinfo hidden">'+
			'<i class="fa fa-pencil"></i></a> <a href="#" class="on-default remove-keyinfo hidden">'+
			'<i class="fa fa-trash-o"></i></a></td></tr>';
		$('#yq_keyword').children().eq(0).before(html);
		//保存
		$('.save-keyinfo').on("click",function(){
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
			var indus = {"id":indusArr[0],"keyword":indusArr[1],"proportion":indusArr[2],"industry":indusArr[3]}
			saveOrUpdateKeyWord(indus);
			
		});
		//取消
		$(".cancel-keyinfo").on("click",function(){
			window.location.reload();
		});
	});
	//修改一条数据
	$(".edit-keyinfo").on("click",function(){
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
	$('.save-keyinfo').on("click",function(){
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
		var indus = {"id":indusArr[1],"keyword":indusArr[2],"proportion":indusArr[3],"industry":indusArr[4]}
		saveOrUpdateKeyWord(indus);
		
	});
	//取消按钮
	$(".cancel-keyinfo").on("click",function(){
		window.location.reload();
	})
}
function deleteKeyWord(_id){
	$.ajax({
		async:false,
		url : "/apis/keywordinfo/deleteKeyWord.json?id="+_id,
		success : function(res) {
			if(res.success){
				showKeyWordList(pageNum,pageSize);
			}
		}
	});
};
function saveOrUpdateKeyWord(_indus){
	$.ajax({
		type : "post",
		contentType : "application/json",
		async:false,
		url : "/apis/keywordinfo/saveOrUpdateKeyWord.json",
		data : JSON.stringify(_indus),
		success : function(res) {
			if(res.success){
				showKeyWordList(pageNum,pageSize);
			}
		}
	});
};