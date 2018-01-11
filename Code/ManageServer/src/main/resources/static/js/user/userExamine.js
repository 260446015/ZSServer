var _type = "试用会员";
var _time = "全部";
var _pageNum = 10;
var _pageSize = 0;
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
    if($(this).html()=='试用会员'||$(this).html()=='正式会员'){
        _type = $(this).html();
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
    console.log(req)
}
var a='<button class="bk-margin-5 btn btn-labeled btn-success" type="button"><span class="btn-label"><i class="fa fa-check"></i></span>Success</button>';