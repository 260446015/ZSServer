/**
 * Created by zhangxin on 2017/11/23.
 */
$(function () {
	$("#gardenMap").addClass("active");
	$("#follow").addClass("active");
	showGardenindustry();
    showGardenAttainArea();
    showGardenAttainList(industryType,area,sort,sortType);
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
                    {value:12, name:'直接访问'},
                    {value:23, name:'邮件营销'},
                    {value:50, name:'联盟广告'},
                    {value:41, name:'视频广告'},
                    {value:200, name:'搜索引擎'}
                ]
            }
        ]
    };
    // var circleCharts1 = echarts.init(document.getElementById("charts1"),"customed");
    // var circleCharts2 = echarts.init(document.getElementById("charts2"),"customed");
    // var circleCharts3 = echarts.init(document.getElementById("charts3"),"customed");
    var clickIndex = 0,chartsIndex=0;
    $("#gardenList").on("click",".follow-btn",function () {
        var imgSrc = $(this).parents(".layout-box").find(".left-img>img").attr("src");
        var attId = $(this).parents(".border-bottom").find("input").eq(2).val();//关注园区id
        var imgArr = $(".img-box-list .img-box");
        var flag = false;
        $(".contrast-content input[type=hidden]").each(function(index,item){
        	if(item.value == attId){
        		flag = true;
        	}
        });
        if(flag)
        	return;
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
            _right_wrapper.addClass("open-img").find(".img-box-list>.img-box>img").eq(clickIndex).attr("src",imgSrc).css("display","inline");
            _right_wrapper.addClass("open-img").find(".img-box-list>.img-box>input[type=hidden]").eq(clickIndex).attr("value",attId);;
            clickIndex++;
        }
    });
    $(".park-btn").on("click",function () {
        var parent_wrapper = $(this).parent();
        if(parent_wrapper.hasClass("open-img")){
        	var value = $(".park-contrast-box").find(".img-box-list>.img-box>input[type=hidden]");
        	var arr = new Array();
        	value.each(function(){
        		var value = $(this).val();
        		if(value != ''){
        			arr.push(value);
        		}
        	});
        	if(arr.length < 2){
        		return;
        	}
        	for(var i=0;i<arr.length;i++){
        		$(".charts-box-list").find(".charts-box").eq(i).css("display","inline");
        	}
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
				var html = "";
				for (var i = 0; i < arr.length; i++) {
					html += '<div class="col-md-12 border-bottom"><input type="hidden" class="gdp" value="'+arr[i].gdp+'"/><input type="hidden" class="square" value="'+arr[i].gardenSquare+'"/><input type="hidden" value="'+arr[i].id+'" class="attId"/><div class="layout-box">' +
								'<div class="left-img"><img src="'+arr[i].gardenPicture+'" width="160" /></div>' +
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
								arr[i].gardenWebsite + '</span><a href="javascript:void(0);" class="btn btn-fill btn-blue follow-btn pull-right">园区对比</a><a href="javascript:void(0);" class="btn btn-fill btn-blue follow-btn pull-right  mr15" onclick="attation('+arr[i].gardenId+');">取消关注</a>' +
								'</p></div></div></div>';
				}
				$("#gardenList").html(html);
			}
		}
	});
}
function attation(gardenId){
	console.log($(this));
}
