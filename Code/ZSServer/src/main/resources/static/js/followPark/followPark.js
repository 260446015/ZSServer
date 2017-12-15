/**
 * Created by zhangxin on 2017/11/23.
 */
$(function () {
	$("#gardenMap").addClass("active");
	$("#follow").addClass("active");
	showGardenindustry();
    showGardenAttainArea();
    showGardenAttainList(sortType,pageNumber,pageSize);
    $(".search-box").on("click",".search-item-content>a",function(){
		$(this).addClass("active").siblings().removeClass("active");
		var _industry = $(this).html();
		var arr = $(".search-box").find(".active");
		arr.each(function(){
			showGardenAttainList(sortType,pageNumber,pageSize);
		});
	});
    var clickIndex = 0,chartsIndex=0;
    $("#gardenList").on("click",".follow-btn",function () {
    	var value = $(this).html();
    	if(value != '园区对比'){
    		return;
    	}
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
        	$("#gardenList").find(".follow-btn").removeClass("follow-btn");
        	var value = $(".park-contrast-box").find(".img-box-list>.img-box>input[type=hidden]");
        	var arr = new Array();
        	value.each(function(){
        		var value = $(this).val();
        		if(value != ''){
        			arr.push(value);
        		}
        	});
        	if(arr.length < 2){
        		if(arr.length == 0){
        			new Alert({
						flag : false,
						text : '您必须先选择园区才能进行对比',
						timer : 3000
					}).show();
        		}else if(arr.length == 1){
        			new Alert({
						flag : false,
						text : '您必须至少选择两个园区才能进行对比',
						timer : 3000
					}).show();
        		}
        		return;
        	}
        	for(var i=0;i<arr.length;i++){
        		$(".charts-box-list").find(".charts-box").eq(i).css("display","inline");
        	}
            parent_wrapper.removeClass("open-img").addClass("open-charts");
            var ids = $("#gardenCompare").find("input[type=hidden]");
            var sendIds = new Array();
            ids.each(function(){
            	if($(this).val() != ''){
            		sendIds.push($(this).val());
            	}
            });
            showCompareEcharts(sendIds);
        }else if(parent_wrapper.hasClass("open-charts")){
        	$("#gardenList").find(".btn-blue").addClass("follow-btn");
            parent_wrapper.removeClass("open-charts");
        }else if(parent_wrapper.find(".small-charts>div").length>0){
            parent_wrapper.addClass("open-charts");
        }else{
        	new Alert({
				flag : false,
				text : '您必须先选择园区才能进行对比',
				timer : 3000
			}).show();
        }
    });
});
var pageNumber = 0;
var pageSize = 6;
function showGardenindustry(){//获取园区产业
	$.ajax({
		type:'get',
		url:'/apis/area/getGardenIndustry.json',
		success:function(res){
			if(res.success){
				var arr = res.data;
				var html = '';
				for(var i=0;i<arr.length;i++){
					html += '<a href="javascript:void(0);" class="search-item">'+arr[i].industryOne+'</a>';
				}
				$("#gardenIndustry").append(html);
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
				var html = '';
				for(var i=0;i<arr.length;i++){
					html += '<a href="javascript:void(0);" class="search-item">'+arr[i]+'</a>';
				}
				$("#gardenArea").append(html);
			}
		}
	});
}
var sortType = 'desc';
var gardenAttList;//定义全局变量初始化关注园区数据
function showGardenAttainList(d,e,f){
	var msg = new Array();
	var arr = $(".search-box").find(".active");
	arr.each(function(){
		msg.push($(this).html());
	});
	msg.push(d);
	var req = {"pageNumber":e,"pageSize":f,"msg":msg};
	$.ajax({
		type:'post',
		url:'/apis/area/getAttentionGardenList.json',
		data:JSON.stringify(req),
		contentType:'application/json',
		success:function(res){
			if(res.success){
				var arr = res.data.content;
				var html = "";
				gardenAttList = arr;
				if(arr.length != 0){
					for (var i = 0; i < arr.length; i++) {
						html += '<div class="col-md-12 border-bottom"><input type="hidden" class="gdp" value="'+arr[i].gdp+'"/><input type="hidden" class="square" value="'+arr[i].gardenSquare+'"/><input type="hidden" value="'+arr[i].gardenId+'" class="attId"/><div class="layout-box">' +
									'<div class="left-img"><img src="'+arr[i].gardenPicture+'" width="160" /></div>' +
									'<div class="right-list"><a class="scatter-blocks no-border" href="/apis/area/garden/followAllCityPark.html?name='+arr[i].gardenName+'">'+
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
									arr[i].gardenWebsite + '</span><a href="javascript:void(0);" class="btn btn-fill btn-blue follow-btn pull-right">园区对比</a><a href="javascript:void(0);" class="btn btn-fill btn-blue follow-btn pull-right  mr15" onclick="attation(this);">取消关注</a>' +
									'</p></div></div></div>';
					}
					$("#gardenList").html(html);
					if(res.data.totalPages>1){
						page.init(res.data.totalElements,res.data.number+1,options);
						$("#"+page.pageId +">li[class='pageItem']").on("click",function(){
							showGardenAttainList(sortType,$(this).attr("page-data")-1,pageSize);
		                });
					}else{
						$('#page').html("");
					}
				}else{
					new Alert({
						flag : false,
						text : '暂无关注园区数据',
						timer : 3000
					}).show();
					setTimeout("window.location.href = '/apis/area/garden/allCityPark.html'",3000);
					
				}
			}
		}
	});
}
function attation(event){
	var value = $(event).html();
	var gardenId = $(event).parents(".layout-box").siblings("input[class=attId]").val();
	var flag;
	if(value == '关注'){
		flag = true;
	}else{
		flag = false;
	}
	$.ajax({
		url:'/apis/area/attentionGarden.json?gardenId='+gardenId+'&flag='+flag,
		type:'get',
		success:function(res){
			if(res.success){
				showGardenAttainList(sortType,pageNumber,pageSize);
			}
		}
	});
}
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
function showCompareEcharts(ids){//园区对比展示echarts的功能
	$.ajax({
		type:'post',
		url:'/apis/area/getGardenCompare.json?arrId='+ids,
		success:function(res){
			var arr = res.data;
			var showDatas = new Array();
			console.log(arr);
			for(var i=1;i<=arr.length;i++){
				var arr2 = arr[i-1].industryType;
				showDatas = [];
				for(var j=0;j<arr2.length;j++){
					var value = arr2[j].count;
					var name = arr2[j].industry;
					var showData = {"value":value,"name":name};
					showDatas.push(showData);
				}
				option.series[0].data = showDatas;
				var circleCharts1 = echarts.init(document.getElementById("charts"+i),"customed");
	            circleCharts1.setOption(option,true);
	            $(".charts-box").find(".enterCount").eq(i-1).html(arr[i-1].enterCount);
	            $(".charts-box").find(".square").eq(i-1).html(arr[i-1].square+'平方千米');
	            $(".charts-box").find(".charts-title").eq(i-1).html(arr[i-1].gardenName);
			}
			
		}
	});
}