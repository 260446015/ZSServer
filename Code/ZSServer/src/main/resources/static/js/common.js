/**
 * Created by zhangxin on 2017/11/13.
 */
var print = console.log;
$(function () {
    /*横向菜单的点击事件*/
    $(".nav-menu").on("click","li>a",function () {
        var _this = $(this);
        _this.parent().addClass("active").siblings().removeClass("active");
    });
    /*左侧导航的点击事件*/
    $(".left-nav").on("click","li>a",function () {
        var _this = $(this);
        _this.parent().addClass("active").siblings().removeClass("active");
        _this.next("ul").children("li").removeClass("active");
    });
    /*mark筛选项*/
    $(".mark-box").on("click","li>a.mark-item",function () {
        var _this = $(this);
        _this.parent().addClass("active").siblings().removeClass("active");
    });
    /*mark筛选项下拉列表s*/
    $(".mark-menu").on("click","li>a",function () {
        var _this = $(this);
        _this.parent().addClass("active").siblings().removeClass("active");
        _this.parents(".mark-box>li").removeClass("active");
    });
});