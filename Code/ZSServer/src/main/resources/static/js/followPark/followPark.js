/**
 * Created by zhangxin on 2017/11/23.
 */
$(function () {
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        series: [
            {
                name:'访问来源',
                type:'pie',
                radius: ['30%', '50%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: true,
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '16',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: true
                    }
                },
                data:[
                    {value:335, name:'直接访问'},
                    {value:310, name:'邮件营销'},
                    {value:234, name:'联盟广告'},
                    {value:135, name:'视频广告'},
                    {value:1548, name:'搜索引擎'}
                ]
            }
        ]
    };
    // var circleCharts1 = echarts.init(document.getElementById("charts1"),"customed");
    // var circleCharts2 = echarts.init(document.getElementById("charts2"),"customed");
    // var circleCharts3 = echarts.init(document.getElementById("charts3"),"customed");
    var clickIndex = 0,chartsIndex=0;
    $(".follow-btn").on("click",function () {
        var imgSrc = $(this).parents(".layout-box").find(".left-img>img").attr("src");
        var _right_wrapper = $(".park-contrast-box");
        if(_right_wrapper.hasClass("open-charts")){
            if(chartsIndex == 3){
                chartsIndex = 0;
            }
            var chartsDom = _right_wrapper.find(".small-charts").eq(chartsIndex)[0];
            var newChart = echarts.init(chartsDom,"customed");
            newChart.setOption(option,true);
            chartsIndex++;
        }else{
            if(clickIndex==3){
                clickIndex = 0;
            }
            _right_wrapper.addClass("open-img").find(".img-box-list>.img-box>img").eq(clickIndex).attr("src",imgSrc);
            clickIndex++;
        }
    });
    $(".park-btn").on("click",function () {
        var parent_wrapper = $(this).parent();
        if(parent_wrapper.hasClass("open-img")){
            parent_wrapper.removeClass("open-img").addClass("open-charts");
            var circleCharts1 = echarts.init(document.getElementById("charts1"),"customed");
            var circleCharts2 = echarts.init(document.getElementById("charts2"),"customed");
            var circleCharts3 = echarts.init(document.getElementById("charts3"),"customed");
            circleCharts1.setOption(option,true);
            circleCharts2.setOption(option,true);
            circleCharts3.setOption(option,true);
        }else if(parent_wrapper.hasClass("open-charts")){
            parent_wrapper.removeClass("open-charts");
        }else if(parent_wrapper.find(".small-charts>div").length>0){
            parent_wrapper.addClass("open-charts");
        }
    });
});
