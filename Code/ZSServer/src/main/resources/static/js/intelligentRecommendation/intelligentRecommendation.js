///**
// * Created by zhangxin on 2017/11/27.
// */
//$(function () {
//	$("#recommend").addClass("active");
//    var dot = {
//        name: '强相关',
////        type: 'effectScatter',
//        type:'scatter',
//        showEffectOn: 'emphasis',
//        xAxisIndex: 0,
//        yAxisIndex: 0,
//        symbol: 'circle',
//        symbolSize: 20,
//        //圈里文字位置
//        label: {
//            normal: {
//                show: true,
//                textStyle: {
//                    color: '#fff'
//                },
//                position: 'inside',
//                formatter: function(param) {
//                    return param.data[2];
//                },
//            },
//        },
//        itemStyle: {
//            normal: {
//            	opacity:.5,
//                color: new echarts.graphic.LinearGradient(
//                    0, 0, 0, 1,
//                    [
//                        {offset: 0, color: '#20c2fe'},
//                        {offset: 0.5, color: '#6e92fb'},
//                        {offset: 1, color: '#bd62f7'}
//                    ]
//                )
//            },
//            emphasis:{
//            	opacity:1
//            }
//        },
//
//        data: [],
//    }
//
//    var datalist = [
//        /**
//         * x坐标
//         * y坐标
//         * name 标签名称
//         * symbolSize 点大小
//         * 趋势，1:上升 0:下降
//         */
//        [39, 10, '浪潮', 75, 1],
//        [62, 50, '华为', 60, 1],
//        [65, 17, '亚信', 40, 1],
//        [63, 95, '小米', 70, 1],
//        [52, 87, '360', 60, 0],
//        [28, 70, '微软', 44, 0],
//        [56, 8, '网易', 60, 0],
//        [33, 36, '网易', 50, 0],
//        [38, 96, '新浪', 70, 0]
//    ];
//
//    var dataMap = datalist.map((item) => {
//            return Object.assign({}, dot, {
//                symbolSize: item[3],
//                data: [
//                    item
//                ]
//            })
//        });
//
//                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
//    var option = {
//        title: {
//            text: '',
//            x: '35%',
//            y: 0
//      },
//        tooltip: {
//           trigger: 'item',
//           backgroundColor: '#fff',
//           textStyle: {
//                color: '#999'
//            },
//            formatter: (item) => {
//               if (item.data[2]) {
//                    return `${item.data[2]}<br/>  坐标: x ${item.data[0]}  y ${item.data[1]}`;
//               }
//            }
//        },
//        xAxis: [{
//            gridIndex: 0,
//            type: 'value',
//            show: false,
//            min: 0,
//            max: 100,
//            nameLocation: 'middle',
//            nameGap: 30
//
//
//        }],
//            yAxis: [{
//            gridIndex: 0,
//            min: 0,
//            show: false,
//            max: 100,
//            nameLocation: 'middle',
//            nameGap: 30,
//        }],
//            series: [
//            ...dataMap, {
//            name: '弱相关',
//            type: 'scatter',
////            showEffectOn: 'emphasis',  
//            xAxisIndex: 0,
//            yAxisIndex: 0,
//            symbol: 'circle',
//            symbolSize: 100,
//            label: {
//                normal: {
//                    show: true,
//                    textStyle: {
//                        color:"#fff",
//                        fontSize: '15'
//                    },
//                    formatter: function(param) {
//                        return param.data[2];
//                    },
//                },
//
//            },
//            itemStyle: {
//                normal: {
//                    color: new echarts.graphic.LinearGradient(
//                        0, 0, 0, 1,
//                        [
//                            {offset: 0, color: '#20c2fe'},
//                            {offset: 0.5, color: '#6e92fb'},
//                            {offset: 1, color: '#bd62f7'}
//                        ]
//                    )
//                }
//            },
//            data: [
//                [49, 51, '中科点击','#fff']
//            ]
//        }]
//    };
//    
//   $.ajax({
//    	url:'/intelligent/list.json',
//    	type:'GET',
//    	async: false,
//    	success:function(res){
//    		console.log(res)
//    		if(res.data==null){
//    			echarts.dispose(document.getElementById("charts"));
//           	   $("#charts").html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
//    		}else{
//    			var arr = res.data;
//    			initDataList(option,arr);
//    			$("#charts").height($(window).height()-$(".navbar-trans").height()-$(".footer").height()-122);
//    			charts.setOption(option);
//    		}
//    	}
//    });
//   
//});
//console.log(document.getElementById("charts"))
//var charts = echarts.init(document.getElementById("charts"),"customed");
//charts.on("click",function (e) {
//	var name = e.data[2];
//	$.ajax({
//		url:'/intelligent/getCompanyInfoByName.json?name='+name,
//    	type:'GET',
//    	async: false,
//    	success:function(res){
//    		if(res.data==null){
//    			new Alert({flag:false,text:res.message,timer:2000}).show();
//    		}else{
//    		  $('#layer-person-info').html(
//    				  '<h3 class="layer-person-title text-center">'
//    				  +res.data.name+'<button type="button" class="close">×</button></h3>'
//    				  +'<div class="layer-body small-line-height"><div class="form-horizontal">'
//    				  +'<div class="form-group"><label class="col-md-4 text-right control-label">法人代表</label>'
//    				  +'<div class="col-md-7"><p class="form-control-static" >'+res.data.boss+'</p></div></div>'
//    				  +'<div class="form-group"><label class="col-md-4 text-right control-label">状态</label>'
//    				  +'<div class="col-md-7"><p class="form-control-static" >'+res.data.state+'</p></div></div>'
//    				  +'<div class="form-group"><label class="col-md-4 text-right control-label">注册时间</label>'
//    				  +'<div class="col-md-7"><p class="form-control-static" >'+res.data.time+'</p></div></div>'
//    				  +'<div class="form-group"><label class="col-md-4 text-right control-label">行业</label>'
//    				  +'<div class="col-md-7"><p class="form-control-static" >'+res.data.industry+'</p></div></div>'
//    				  +'<div class="form-group"><label class="col-md-4 text-right control-label">注册资本</label>'
//    				  +'<div class="col-md-7"><p class="form-control-static" >'+res.data.money+'</p></div></div>'
//    				  +'<div class="form-group"><label class="col-md-4 text-right control-label">注册地址</label>'
//    				  +'<div class="col-md-7"><p class="form-control-static" >'+res.data.address+'</p></div></div>'
//    				  +'</div></div>' +'<div class="layer-footer text-center" >'
//    				  +'<a href="/apis/company/baseInfo.html?companyName='+res.data.name+'" class="like">查看更多</a></div>'
//    		  );
//    		  $(".layer-person").css({
//    		        display: "block",
//    		        top: e.event.offsetY,
//    		        left: e.event.offsetX+200
//    		    });
//    		}
//    		}
//	});
//   
//    
//});
//function initDataList(option,arr){
//	
//	for(var i = 0;i<option.series.length;i++){
//		var  dd = option.series[i].data;
//		var aa = dd[dd.length-1];
//		for(var j=0;j<aa.length-1;j++){
//			if(j==2){
//				aa[j]=arr[i].companyName;
//			}
//		}
//		option.series[i].data.push(dd.push(aa));
//	}
//	console.log(option);
//	
//	return option;
//};
////关闭内容
//$(".layer-person").on("click",".text-center .close",function () {
//	$(this).parents("#layer-person-info").hide();
//});
$(function(){
    $.ajax({
        url:'/intelligent/list.json',
        type:'GET',
        async: false,
        success:function(res){
            var strHtml = "";
            for(var i=0;i<res.data.length;i++){
                    if(res.data[i].companyName!=null){
                        strHtml += "<li>"
                        strHtml += "<div>"
                        strHtml += "<p>"+res.data[i].companyName+"</p>"
                        if(res.data[i].flag!=false){
                            strHtml += "<span>new</span>"
                        }
                        strHtml += "</div>"
                        if(res.data[i].industry!=null){
                            strHtml += "<small class='sma smCircle'>"+"<i>"+res.data[i].industry+"</i></small>" ;    
                        }
                        if(res.data[i].industryLabel!=null){
                            strHtml += "<small class='smb smCircle'><i>"+res.data[i].industryLabel+"</i></small>" ;  
                        }
                        if(res.data[i].industryZero!=null){
                            strHtml += "<small class='smc smCircle'><i>"+res.data[i].industryZero+"</i></small></li>" ;       
                        }    
                    }
                }
            $('.circles ul').html(strHtml);
            $(".circles ul>li").each(function(index,el){
               if($(el).children("small").length==0){
                 $(el).css('border','0')
               }
               $('.smCircle>i').hover(function(){
                   $('.smCircle>i').addClass('spot')
               },function(){
                $('.smCircle>i').removeClass('spot')
               })
            })    
        }   
    })
   $('.uls li').on("click",function (e){  
    var str = $(this).find('p').text();
    // var name = e.str;
    $.ajax({
        	url:'/intelligent/getCompanyInfoByName.json?name='+str,
           	type:'GET',
           	async: false,
           	success:function(base){
                   console.log(base)
           		if(base.data==null){
           			new Alert({flag:false,text:base.message,timer:1000}).show();
           		}else{
                       console.log(str)
           		  $('#layer-person-info').html(
           				  '<h3 class="layer-person-title text-center">'
           				  +base.data.name+'<button type="button" class="close">×</button></h3>'
           				  +'<div class="layer-body small-line-height"><div class="form-horizontal">'
           				  +'<div class="form-group"><label class="col-md-4 text-right control-label">法人代表</label>'
           				  +'<div class="col-md-7"><p class="form-control-static" >'+base.data.boss+'</p></div></div>'
           				  +'<div class="form-group"><label class="col-md-4 text-right control-label">状态</label>'
           				  +'<div class="col-md-7"><p class="form-control-static" >'+base.data.state+'</p></div></div>'
           				  +'<div class="form-group"><label class="col-md-4 text-right control-label">注册时间</label>'
           				  +'<div class="col-md-7"><p class="form-control-static" >'+base.data.time+'</p></div></div>'
           				  +'<div class="form-group"><label class="col-md-4 text-right control-label">行业</label>'
           				  +'<div class="col-md-7"><p class="form-control-static" >'+base.data.industry+'</p></div></div>'
           				  +'<div class="form-group"><label class="col-md-4 text-right control-label">注册资本</label>'
           				  +'<div class="col-md-7"><p class="form-control-static" >'+base.data.money+'</p></div></div>'
           				  +'<div class="form-group"><label class="col-md-4 text-right control-label">注册地址</label>'
           				  +'<div class="col-md-7"><p class="form-control-static" >'+base.data.address+'</p></div></div>'
           				  +'</div></div>' +'<div class="layer-footer text-center" >'
           				  +'<a href="/apis/company/baseInfo.html?companyName='+base.data.name+'" class="like">查看更多</a></div>'
                     );   
           		  $(".layer-person").css({
           		        display: "block",
           		        top: e.offsetY,
           		        left: e.offsetX+200
           		    });
           		}
           		}
        	});
      })
      $(".layer-person").on("click",".text-center .close",function () {
        	$(this).parents("#layer-person-info").hide();
        });
})
