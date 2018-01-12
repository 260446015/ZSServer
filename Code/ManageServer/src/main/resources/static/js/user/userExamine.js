var _type = "trial";
var _time = "全部";
var _pageNum = 0;
var _pageSize = 10;
var options={
	    "id":"page",//显示页码的元素
	    "data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":10,//每页显示数据个数
	    "callBack":function(){}
	};
$(function() {
    var req = {
        "pageNum" : _pageNum,
        "pageSize" : _pageSize,
        "type" : _type,
        "time" : _time
    };
    showPage(req);
});
$(".btn").on("click",function(){
    $(this).addClass("btn-success").siblings().removeClass("btn-success");
    if($(this).html()=='试用会员'){
        _type = "trial";
    }else if($(this).html()=='正式会员'){
    	_type = "user";
    }else{
    	_time = $(this).html();
    }
    var req = {
        "pageNum" : _pageNum,
        "pageSize" : _pageSize,
        "type" : _type,
        "time" : _time
    };
    showPage(req);
});
function showPage(req) {
	$.ajax({
        type : "post",
        contentType : "application/json",
        async:false,
        url : "/apis/user/getAccountList.json",
        data : JSON.stringify(req),
        success : function(res) {
            if (res.success) {
                if(res.data.totalPage>0){
                    var arr = res.data.dataList;
                    var html = '';
                    for (var i = 0; i < arr.length; i++) {
                        html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td>' + arr[i].userAccount + '</td><td>' + arr[i].realName + '</td><td>' + arr[i].telphone + '</td><td>' + arr[i].userEmail
                            + '</td><td>' + arr[i].createTime + '</td><td class="actions">'+
                            '<button class="bk-margin-5 btn btn-labeled btn-success" type="button"><span class="btn-label"><i class="fa fa-check"></i></span>通过</button></td></tr>';

                    }
                    $("#user_list").html(html);
                    page.init(res.data.totalNumber,res.data.pageNumber,options);
                    $("#"+page.pageId +">li[class='pageItem']").on("click",function(){
                    	var req = {
                    	        "pageNum" : $(this).attr("page-data")-1,
                    	        "pageSize" : _pageSize,
                    	        "type" : _type,
                    	        "time" : _time
                    	    };
                    	showPage(req);
                    });
                }else{
                    $('#user_list').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
                    $('#page').html("");
                }
            }else{
                layer.msg(res.message, {icon: 2});
            }
        }
    });
	$(".btn-success").on("click",function(){
		var _id = $(this).parents('.gradeX').find( 'input' ).val();
        $.ajax({
            url: "/apis/user/modifyIsCheck.json",
            data: {id:_id},
            success: function (res) {
                if(res.success){
                    layer.msg(res.data, {icon: 1});
                    var req = {
                	        "pageNum" : _pageNum,
                	        "pageSize" : _pageSize,
                	        "type" : _type,
                	        "time" : _time
                	    };
                	showPage(req);
                }else{
                    layer.msg(res.message, {icon: 2});
                }
            }
        });
	});
}