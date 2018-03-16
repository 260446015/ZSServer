/**
 * Created by zhangxin on 2017/11/13.
 */
var print = console.log;
$(function() {
    /*横向菜单的点击事件*/
    $(".nav-menu").on("click", "li>a", function() {
        var _this = $(this);
        _this.parent().addClass("active").siblings().removeClass("active");
    });
    /*左侧导航的点击事件*/
    $(".left-nav").on("click", "li>a", function() {
        var _this = $(this);
        _this.parent().addClass("active").siblings().removeClass("active");
        _this.next("ul").children("li").removeClass("active");
    });
    /*mark筛选项*/
    $(".mark-box").on("click", "li>a.mark-item", function() {
        var _this = $(this);
        if (_this.parent().hasClass("active")) {
            _this.parent().removeClass("active");
        } else {
            _this.parent().addClass("active").siblings().removeClass("active");
        }
    });
    /*mark筛选项下拉列表s*/
    $(".mark-menu").on("click", "li>a", function() {
        var _this = $(this);
        _this.parent().addClass("active").siblings().removeClass("active");
        _this.parents(".mark-box>li").removeClass("active");
    });
    var a_idx = 0;
    $("body").click(function (e) {
        var a = new Array('中科点击','慧数招商');
        var $i = $("<span/>").text(a[a_idx]);
        a_idx = (a_idx + 1) % a.length;
        var x = e.pageX,
            y = e.pageY;
        $i.css({
            "z-index": 99999,
            "top": y - 20,
            "left": x,
            "position": "absolute",
            "font-weight": "bold",
            "color": "#207ff9"
        });
        $("body").append($i);
        $i.animate({
                "top": y - 180,
                "opacity": 0
            },
            1500,
            function () {
                $i.remove();
            });
    });
});