/**
 * Created by zhangxin on 2017/11/24.
 */
var park;
var industry;
var type;
function eConsole(param) {
	$.ajax({
		type:"post",
        url: "/apis/analysis/getCompanyList.json",
		contentType:'application/json',
		data:JSON.stringify({park:park,invest:param.data.name}),
        success: function (response) {
            if(response.message!=null){
            	alert(response.message);
            }else{
            	$('#city_list').html(show(response.data.dataList));
            }
        }
    });
}
function myNum(d){
	$("input[type=radio]").on('ifClicked', function(event){
		if(event.type == 'ifClicked'){
			myPost2(type,$(this).val());
			myTopList($(this).val());
        }
	});
	 $("input[type=radio]").iCheck({
	        radioClass: 'iradio_square-blue'
	    });
	    var barOption = {
	        tooltip: {
	            trigger: 'item',
	            formatter: "{a} <br/>{b}: {c} ({d}%)"
	        },
	        series: [
	            {
	                name:'融资轮次',
	                type:'pie',
	                radius: ['50%', '70%'],
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
	                data:d
	            }
	        ]
	    };
	    var charts1 = echarts.init(document.getElementById('charts1'),'customed');
	    charts1.setOption(barOption);
	    charts1.on("click", eConsole);
};
function myLine1(d){
    var lineChart = {
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : d.time,
                splitLine:{
                    show:false
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'年产值',
                type:'line',
                stack: '总量',
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#018dde'
                        }, {
                            offset: 1,
                            color: '#07263b'
                        }])
                    }
                },
                data:d.count
            }
        ]
    };
    var charts2 = echarts.init(document.getElementById("charts2"),'customed');
    charts2.setOption(lineChart);
};
function myLine2(d){
	var lineChart = {
	        color: ['#3398DB'],
	        tooltip : {
	            trigger: 'axis',
	            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	            }
	        },
	        grid: {
	            left: '3%',
	            right: '4%',
	            bottom: '3%',
	            containLabel: true
	        },
	        xAxis : [
	            {
	                type : 'category',
	                data : d.time,
	                axisTick: {
	                    alignWithLabel: true
	                }
	            }
	        ],
	        yAxis : [
	            {
	                type : 'value'
	            }
	        ],
	        series : [
	            {
	                name:'年税收',
	                type:'bar',
	                barWidth: '40%',
	                label: {
	                    normal: {
	                        show: true,
	                        position: 'top',
	                        color:"#fff",
	                        formatter:function(val){
	                            return val.data+" 万";
	                        }
	                    }
	                },
	                itemStyle: {
	                    normal: {
	                        color: new echarts.graphic.LinearGradient(
	                            0, 0, 0, 1,
	                            [
	                                {offset: 0, color: '#008fe0'},
	                                {offset: 0.7, color: '#006dab'},
	                                {offset: 1, color: '#004c78'}
	                            ]
	                        )
	                    },
	                    emphasis: {
	                        color: new echarts.graphic.LinearGradient(
	                            0, 0, 0, 1,
	                            [
	                                {offset: 0, color: '#004c78'},
	                                {offset: 0.5, color: '#006dab'},
	                                {offset: 1, color: '#008fe0'}
	                            ]
	                        )
	                    }
	                },
	                data:d.count
	            }
	        ]
	    };
	    var charts2 = echarts.init(document.getElementById("charts2"),'customed');
	    charts2.setOption(lineChart);
};
function myPost(a){
	park = a;
	$.ajax({
        url: "/apis/analysis/getFinancingDistribution.json?park="+a,
        success: function (response) {
            if(response.message!=null){
            	alert(response.message);
            }else{
				myNum(response.data);
            }
        }
    });
}
function myClick(type){
	if(type=='年产值'){
		$("#chan").addClass("active");
		$("#shui").removeClass("active");
	}else{
		$("#shui").addClass("active");
		$("#chan").removeClass("active");
	}
	myPost2(type,industry);
}
function myPost2(t,ins){
	type=t;
	industry=ins;
	$.ajax({
        url: "/apis/analysis/getValueDistribution.json",
		data:{park:park,type:t,industry:ins},
        success: function (response) {
            if(response.message!=null){
            	alert(response.message);
            }else{
            	if(t=='年产值'){
            		myLine1(response.data);
            	}else{
            		myLine2(response.data);
            	}
            }
        }
    });
}
function myTopList(ins){
	$.ajax({
        url: "/apis/analysis/getTopCompany.json",
		data:{park:park,industry:ins},
        success: function (response) {
            if(response.message!=null){
            	alert(response.message);
            }else{
            	$('#top_list').html(showTop(response.data.dataList));
            }
        }
    });
}
function show(d){
    var arr = []
    $.each(d, function(index, item){
    	if(index<3){
    		arr.push('<div class="search-group"><div class="col-title text-new">new</div><div class="col-title text-left">'+
    	            '<a href="javascript:void(0);" class="search-item">'+item.companyName+'</a></div><div class="col-title"><a href="javascript:void(0);" class="search-item">'+
    	            '<span class="glyphicon glyphicon-tag rotate90"></span>'+item.industry+'</a></div></div>'
          		);
    	}else{
    		arr.push('<div class="search-group"><div class="col-title text-new"></div><div class="col-title text-left">'+
    	            '<a href="javascript:void(0);" class="search-item">'+item.companyName+'</a></div><div class="col-title"><a href="javascript:void(0);" class="search-item">'+
    	            '<span class="glyphicon glyphicon-tag rotate90"></span>'+item.industry+'</a></div></div>'
          		);
    	}
    });
    var inner=arr.join('');
    return inner;
}
function showTop(d){
	var arr = []
    $.each(d, function(index, item){
		arr.push('<div class="search-group"><div class="col-title text-top">TOP '+(index+1)+'</div><div class="col-title">'+
		        '<a href="javascript:void(0);" class="search-item">'+item.companyName+'</a></div></div>'
      		);
    });
    var inner=arr.join('');
    return inner;
}