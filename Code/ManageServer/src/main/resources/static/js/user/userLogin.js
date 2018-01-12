$("#user_item").addClass("active");
$("#login_item").addClass("active");
$(function() {
    showIndusCompany(pageNum,pageSize);
});
var pageSize = 10;
var pageNum = 0;
function showIndusCompany(_pageNum,_pageSize) {
    var req = {
        "pageNum" : _pageNum,
        "pageSize" : _pageSize
    };
    $.ajax({
        type : "post",
        contentType : "application/json",
        async:false,
        url : "/apis/user/listUserBase.json",
        data : JSON.stringify(req),
        success : function(res) {
            if (res.success) {
                if(res.data.totalPage>0){
                    var arr = res.data.dataList;
                    var html = '';
                    for (var i = 0; i < arr.length; i++) {
                        html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td>' + arr[i].userAccount + '</td><td>' + arr[i].realName + '</td><td>' + arr[i].telphone + '</td><td>' + arr[i].userEmail
                            + '</td><td>' + arr[i].createTime + '</td><td class="actions"><div class="col-lg-12 col-md-4 col-sm-4 col-xs-4"><label class="switch switch-success bk-margin-top-10">';
                            if(arr[i].isSingle==0){
                                html +='<input type="checkbox" class="switch-input" checked />';
                            }else{
                                html +='<input type="checkbox" class="switch-input" />';
                            }
                            html += '<span class="switch-label" data-on="On" data-off="Off"></span><span class="switch-handle"></span></label></div></td></tr>';

                    }
                    $("#user_list").html(html);
                    page.init(res.data.totalNumber,res.data.pageNumber,options);
                    $("#"+page.pageId +">li[class='pageItem']").on("click",function(){
                        showIndusCompany($(this).attr("page-data")-1,pageSize);
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
    initPage();
}
var options={
    "id":"page",//显示页码的元素
    "data":null,//显示数据
    "maxshowpageitem":5,//最多显示的页码个数
    "pagelistcount":10,//每页显示数据个数
    "callBack":function(){}
};
function initPage(){
    $(".switch-input").on("click",function(){
        var _id = $(this).parents('.gradeX').find( 'input' ).val();
        var _isSingle;
        if($(this).is(':checked')){
            _isSingle = 0;
        }else{
            _isSingle = 1;
        }
        $.ajax({
            url: "/apis/user/modifyIsSingle.json",
            data: {id:_id,isSingle:_isSingle},
            success: function (res) {
                if(res.success){
                    layer.msg(res.data, {icon: 1});
                    showIndusCompany(pageNum,pageSize);
                }else{
                    layer.msg(res.message, {icon: 2});
                }
            }
        });
    });
}
