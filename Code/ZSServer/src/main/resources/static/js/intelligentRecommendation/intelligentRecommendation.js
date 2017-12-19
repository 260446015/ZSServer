/**
 * Created by zhangxin on 2017/11/27.
 */
$(function () {
	$("#recommend").addClass("active");
    var dot = {
        name: '强相关',
        type: 'effectScatter',
        showEffectOn: 'emphasis',
        xAxisIndex: 0,
        yAxisIndex: 0,
        symbol: 'circle',
        symbolSize: 20,
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
    }

    var datalist = [
        /**
         * x坐标
         * y坐标
         * name 标签名称
         * symbolSize 点大小
         * 趋势，1:上升 0:下降
         */
        [38, 26, '浪潮', 80, 1],
        [70, 100, '华为', 60, 1],
        [60, 20, '亚信', 80, 1],
        [65, 70, '小米', 87, 1],
        [52, 95, '360', 60, 0],
        [32, 83, '微软', 90, 0],
        [17, 80, '网易', 60, 0],
        [22, 36, '网易', 60, 0],
        [75, 30, '新浪', 60, 0]
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
            symbolSize: 100,
            label: {
                normal: {
                    show: true,
                    textStyle: {
                        color:"#fff",
                        fontSize: '15'
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
                [50, 50, '中科点击','#fff']
            ]
        }]
    };
    
   $.ajax({
    	url:'/intelligent/list.json',
    	type:'GET',
    	async: false,
    	success:function(res){
    		if(res.data==null){
    			new Alert({flag:false,text:res.message,timer:2000}).show();
    		}else{
    			var arr = res.data;
    			initDataList(option,arr);
    			$("#charts").height($(window).height()-$(".navbar-trans").height()-$(".footer").height()-122);
    			charts.setOption(option);
    		}
    	}
    });
   
});
var charts = echarts.init(document.getElementById("charts"),"customed");
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
    		  $('#layer-person-info').html(
    				  '<h3 class="layer-person-title text-center">'
    				  +res.data.name+'<button type="button" class="close">×</button></h3>'
    				  +'<div class="layer-body small-line-height"><div class="form-horizontal">'
    				  +'<div class="form-group"><label class="col-md-4 text-right control-label">法人代表</label>'
    				  +'<div class="col-md-7"><p class="form-control-static" >'+res.data.boss+'</p></div></div>'
    				  +'<div class="form-group"><label class="col-md-4 text-right control-label">状态</label>'
    				  +'<div class="col-md-7"><p class="form-control-static" >'+res.data.state+'</p></div></div>'
    				  +'<div class="form-group"><label class="col-md-4 text-right control-label">注册时间</label>'
    				  +'<div class="col-md-7"><p class="form-control-static" >'+res.data.time+'</p></div></div>'
    				  +'<div class="form-group"><label class="col-md-4 text-right control-label">行业</label>'
    				  +'<div class="col-md-7"><p class="form-control-static" >'+res.data.industry+'</p></div></div>'
    				  +'<div class="form-group"><label class="col-md-4 text-right control-label">注册资本</label>'
    				  +'<div class="col-md-7"><p class="form-control-static" >'+res.data.money+'</p></div></div>'
    				  +'<div class="form-group"><label class="col-md-4 text-right control-label">注册地址</label>'
    				  +'<div class="col-md-7"><p class="form-control-static" >'+res.data.address+'</p></div></div>'
    				  +'<div class="form-group"><label class="col-md-4 text-right control-label">&nbsp</label>'
    				  +'<div class="col-md-7"><p class="form-control-static" >'+'&nbsp</p></div></div>'
    				  +'<div class="form-group"><label class="col-md-4 text-right control-label">&nbsp</label>'
    				  +'<div class="col-md-7"><p class="form-control-static" >'+'&nbsp</p></div></div>'
    				  +'</div></div>' +'<div class="layer-footer text-center" >'
    				  +'<a href="/apis/company/baseInfo.html?companyName='+res.data.name+'" class="like">查看更多</a></div>'
    		  );
    		  $(".layer-person").css({
    		        display: "block",
    		        top: e.event.offsetY,
    		        left: e.event.offsetX+200
    		    });
    		}
    		}
	});
   
    
});
function initDataList(option,arr){
	
	for(var i = 0;i<option.series.length;i++){
		var  dd = option.series[i].data;
		var aa = dd[dd.length-1];
		for(var j=0;j<aa.length-1;j++){
			if(j==2){
				aa[j]=arr[i].companyName;
			}
		}
		option.series[i].data.push(dd.push(aa));
	}
	console.log(option);
	
	return option;
};
//关闭内容
$(".layer-person").on("click",".text-center .close",function () {
	$(this).parents("#layer-person-info").hide();
});
