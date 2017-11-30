/**
 * Created by zhangxin on 2017/11/29.
 */

$(function () {
    //计算内容区域的高度
    $(".right-content").height($(window).height()-$(".navbar-trans").height()-$(".footer").height()-148).css("min-height",$(".right-content .mt88").height()+88);
    $(".search-company").on("click",function () {
        $(this).parents(".col-md-4").addClass("active");
    });
    $(".upload-card").on("click",function () {
        $(this).parents(".col-md-4").addClass("active");
    });
    $("input[type=file]").on("change",function () {
        $("#myModal").modal({
            keyboard: false,
            backdrop: "static"
        });
    });
});