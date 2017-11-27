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
    showGardenindustry();
    showGardenAttainArea();
    showGardenAttainList(industryType,area,sort,sortType);
});
function showGardenindustry(){//获取园区产业
	$.ajax({
		type:'get',
		url:'/apis/area/getGardenIndustry.json',
		success:function(res){
			if(res.success){
				var arr = res.data;
				var html = '<a href="javascript:void(0);" class="search-item active" onclick="sendIndustry(\'全部\')">全部</a>';
				for(var i=0;i<arr.length;i++){
					html += '<a href="javascript:void(0);" class="search-item" onclick="sendIndustry(\''+arr[i].industryOne+'\')">'+arr[i].industryOne+'</a>';
				}
				$("#gardenIndustry").html(html);
			}
		}
	});
}
function showGardenAttainArea(){
	$.ajax({
		type:'get',
		url:'/apis/area/getGardenAttainArea.json',
		success:function(res){
			if(res.success){
				var arr = res.data;
				var html = ' <a href="javascript:void(0);" class="search-item active" onclick="sendArea(\'全部\')">全部</a>';
				for(var i=0;i<arr.length;i++){
					html += '<a href="javascript:void(0);" class="search-item" onclick="sendArea(\''+arr[i]+'\')">'+arr[i]+'</a>';
				}
				$("#gardenArea").html(html);
			}
		}
	});
}
function sendIndustry(data){
	industryType = data;
	showGardenAttainList(industryType,area,sort,sortType);
}
function sendArea(data){
	area = data;
	showGardenAttainList(industryType,area,sort,sortType);
}
function sendSort(data){
	sort = data;
	showGardenAttainList(industryType,area,sort,sortType);
}
var industryType = '全部';
var area = '全部';
var sort = '园区占地';
var sortType = 'desc';
function showGardenAttainList(a,b,c,d){
	var req = {"pageNumber":0,"pageSize":6,msg:[a,b,c,d]};
	$.ajax({
		type:'post',
		url:'/apis/area/getAttentionGardenList.json',
		data:JSON.stringify(req),
		contentType:'application/json',
		success:function(res){
			if(res.success){
				var arr = res.data.content;
				console.log(arr);
				var html = "";
				for (var i = 0; i < arr.length; i++) {
					html += '<div class="col-md-12 border-bottom"><div class="layout-box">' +
								'<div class="left-img"><img src="../images/list_img.jpg" width="160" /></div>' +
								'<div class="right-list"><a class="scatter-blocks no-border" href="./allCityParkDetails.html">'+
								'<span class="scatter-title">'+arr[i].gardenName+'</span>'+
								'<span class="scatter-type ml10">'+arr[i].gardenLevel+'</span>'+
								'<span class="pull-right">入驻企业<span class="numbers">'+arr[i].enterCount+'</span>家</span></a>' +
								'<p class="park-address"><span class="glyphicon glyphicon-tag"></span>';
								var arr2 = arr[i].industryType.split('、')
								for(var j=0;j<arr2.length;j++){
									html += '<span class="mr15">'+arr2[j]+'</span>';
								}
								html += '</p><p class="net-address"><span class="mr15"><span class="glyphicon glyphicon-map-marker"></span>' +
								arr[i].address +'</span><span class="mr15"><span class="glyphicon glyphicon-globe"></span>' +
								arr[i].gardenWebsite + '</span><a href="javascript:void(0);" class="btn btn-fill btn-blue follow-btn pull-right">园区对比</a>' +
								'</p></div></div></div>'
				}
				$("#gardenList").html(html);
			}
		}
	});
}
