var industry ="全部";
var registerTime = "1-5年";
var area = "全部";
var register="0-50万";
var arrTab;
$(function () {
	$("#screen").addClass("active");
	/*var param = {industry:industry,area:area,register:register,registerTime:registerTime};
    searchAjax(param);*/
    $(".search-tag span.close").on("click",function () {
        $(this).parent().remove();
        console.log($(this).parent().val());
    });
    if(arrTab!=null){
    	$("#searchTag").html(TagList(arrTab));
    }
});
function searchTab(a,b){
	if(a == 1){
		industry = b;
	}else if(a == 2){
		area = b;
	}else if(a == 3){
		registerTime = b;
	}else if(a==4){
		register = b;
	}
};
$("#search_tag").on("click",function () {
    $("#myModal").modal("show");
    
  
    $("#LabelBlue").click(function(){
    	
    	$("#myModal").modal("hide");
    	var arr=[];
    	if(industry=="全部"){
    		arr = [area,registerTime,register];
    	}else if(area=="全部"){
    		arr=[industry,registerTime,register];
    	}else{
    		arr = [industry,area,registerTime,register];
    	}
    	arrTab=arr;
    	$("#searchTag").html(TagList(arr));
    	var  param = {industry:industry,area:area,registerTime:registerTime,register:register};
    	searchAjax(param);
    	
    });
});
function TagList(arr){
	var array=[];
	$.each(arr,function(index,item){
		array.push(
				'<button class="btn btn-fill btn-blue search-tag">'+item
                +'<span type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</span></button>'
		);
	});
	var inner = array.join('');
	return inner;
};
var dot = {
        name: '强相关',
        type: 'effectScatter',
        showEffectOn: 'emphasis',
        xAxisIndex: 0,
        yAxisIndex: 0,
        symbol: 'circle',
        symbolSize: 40,
        label: {
            normal: {
                show: true,
                textStyle: {
                    color: '#fff'
                },
                position: 'inside',
                formatter: function(param) {
                    return param.data[2];
                },
            },
            
        },
        itemStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#20c2fe'},
                        {offset: 0.5, color: '#6e92fb'},
                        {offset: 1, color: '#bd62f7'}
                    ]
                )
            }
        },

        data: [],
    };

    var datalist = [
        /**
         * x坐标
         * y坐标
         * name 标签名称
         * symbolSize 点大小
         * 趋势，1:上升 0:下降
         */
        [22, 36, '浪潮', 80, 1],
        [80, 50, '华为', 70, 1],
        [60, 20, '亚信', 80, 1],
        [75, 70, '小米', 87, 1],
        [52, 83, '360', 60, 0],
        [32, 83, '微软', 90, 0],
        [17, 60, '网易', 70, 0]
    ];

    var dataMap = datalist.map((item) => {
            return Object.assign({}, dot, {
                symbolSize: item[3],
                data: [
                    item
                ]
            })
        });


    var option = {
        title: {
            text: '',
            x: '35%',
            y: 0
        },
        tooltip: {
            trigger: 'item',
            backgroundColor: '#fff',
            textStyle: {
                color: '#999'
            },
            formatter: (item) => {
            if (item.data[2]) {
            return `${item.data[2]}<br/>  坐标: x ${item.data[0]}  y ${item.data[1]}`;
    }
}
},
    xAxis: [{
        gridIndex: 0,
        type: 'value',
        show: false,
        min: 0,
        max: 100,
        nameLocation: 'middle',
        nameGap: 30


    }],
        yAxis: [{
        gridIndex: 0,
        min: 0,
        show: false,
        max: 100,
        nameLocation: 'middle',
        nameGap: 30,
    }],
        series: [
        ...dataMap, {
        name: '弱相关',
        type: 'effectScatter',
        showEffectOn: 'emphasis',
        xAxisIndex: 0,
        yAxisIndex: 0,
        symbol: 'circle',
        symbolSize: 120,
        label: {
            normal: {
                show: true,
                textStyle: {
                    color:"#fff",
                    fontSize: '20'
                },
                formatter: function(param) {
                    return param.data[2];
                },
            },

        },
        itemStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#20c2fe'},
                        {offset: 0.5, color: '#6e92fb'},
                        {offset: 1, color: '#bd62f7'}
                    ]
                )
            }
        },
        data: [
            [50, 50, '中科点击', '#fff']
        ]
    }]
};
   
function searchAjax(param){
	console.log(param);
	$.ajax({
		url:'/accurateScreening/getCompanyInfoBySearch.json',
		type:'POST',
		async: false,
        contentType: 'application/json',
		data:JSON.stringify(param),
		success:function(res){
			if(res.message!=null){
            	new Alert({flag:false,text:res.message,timer:1500}).show();
            }else{
            	var array = res.data;
            	var arr = initEchartData(array,datalist);
            	/**根据返回结果构建echart图形*/
            	 $("#charts").height($(window).height()-$(".navbar-trans").height()-$(".footer").height()-192-$(".mt50.mb20").height());
            	    var charts = echarts.init(document.getElementById("charts"),"customed");
            	    option.series.data=arr;
            	    option.series[option.series.length-1].data.push(
            	    		[50, 50, array[array.length-1].companyName, '#fff']
            	    );
            	    charts.setOption(option);
            	    charts.on("click",function (e) {
            	    	var name = e.data[2];
            	    	$.ajax({
            	    		url:'/intelligent/getCompanyInfoByName.json?name='+name,
            	        	type:'GET',
            	        	async: false,
            	        	success:function(res){
            	        		if(res.data==null){
            	        			new Alert({flag:false,text:res.message,timer:2000}).show();
            	        		}else{
            	        			var a = res.data;
            	        			console.log(res.data);
            	        			$('#cname').html(a.name);
            	        			$('#address1').html(a.address);
            	        			$('#time1').html(a.time);
            	        			$('#boss1').html(a.boss);
            	        			$('#ind1').html(a.industry);
            	        			$('#money1').html(a.money);
            	        			$('#state1').html(a.state);
            	        			$('.like').remove();
                	        		$('#text-content').append('<a href="/apis/company/baseInfo.html?companyName='+a.name+'"  class="like">查看更多</a>');
            	        		}
            	        	}
            	    	});
            	        $(".layer-person").css({
            	            display: "block",
            	            top: e.event.offsetY,
            	            left: e.event.offsetX+200
            	        });
            	    });
            	
            }
		}
	});
};
function initEchartData(array,datalist){
	var data=[];
	var b=[];
	$.each(array,function(index,item){
		var a = datalist[index];
		if(index<=6){
			a[2]=item.companyName;
			data.push(b.push(a));
		}
	});
	return data;
};
