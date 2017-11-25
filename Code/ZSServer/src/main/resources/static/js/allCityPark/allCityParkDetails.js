/**
 * Created by zhangxin on 2017/11/22.
 */
$(function () {
    AMapUI.setDomLibrary($);

    //加载BasicControl，loadUI的路径参数为模块名中 'ui/' 之后的部分
    AMapUI.loadUI(['control/BasicControl'], function(BasicControl) {

        var map = new AMap.Map('map', {
            // mapStyle: 'amap://styles/e15ea366314a2314abda4c7761ee02a6',
            resizeEnable: false,
            zoom:13,
        });
        //缩放控件
        map.addControl(new BasicControl.Zoom({
            position: 'rb', //left top，左上角
            showZoomNum: false //显示zoom值
        }));
        /*雷达转动*/
        if($(window).scrollTop()>0){
            $(window).scrollTop(0);
        }
        rotates();
        setTimeout(function () {
            unrotates();
        },2000);
    });
    function rotates() {
        $("body,.page-content").addClass("modal-open");
        $(".search-circle-box").addClass("open").children(".search-circle-img").addClass("rotates").css({"margin-left":function(){
            return -$(this).width()/2
        },"margin-top":function () {
            return -$(this).height()/2
        }});
    }
    function unrotates() {
        $("body,.page-content").removeClass("modal-open");
        $(".search-circle-box").removeClass("open").children(".search-circle-img").removeClass("rotates").css({"margin-left":function(){
            return -$(this).width()/2
        },"margin-top":function () {
            return -$(this).height()/2
        }});
        $('html,body').animate({scrollTop:$(".right-content .container").offset().top-50},300);
    }
});
