var industry ="全部";
var registerTime = new Array();
var area = "全部";
var register= new Array();
$("#screen").addClass("active");
$("#searchTag").on("click",".search-tag span.close",function () {
	var del = $(this).parent().text();
	$(".search-box").find(".active").each(function(){
		if(del.indexOf($(this).html()) >= 0){
			$(this).removeClass("active");
			if($(this).parent().find(".active").length == 0){
				$(this).parent().children().eq(0).addClass("active");
			}
		}
	})
	$(this).parent().remove();
	updateLabel();
	searchAjax();
});
$(function () {
		getTab();
});
function getTab(){
	$.ajax({
		url:'/apis/user/getLabel.json',
		type:'GET',
		async:false,
		success:function(res){
			if(res.data==null){
				 $("#myModal").modal("show");
			}else{
				arr = [];
				arr.push(res.data.industry);
				industry = res.data.industry;
				$(".search-box").find(".search-item-content").eq(0).find("a").each(function(){
					if($(this).text() == industry){
						$(this).addClass("active");
					}
				})
				arr.push(res.data.area);
				area = res.data.area;
				$(".search-box").find(".search-item-content").eq(1).find("a").each(function(){
					if($(this).text() == area){
						$(this).addClass("active");
					}
				})
				registerTime = [];
				for(var i = 0;i<res.data.registerTime.length;i++){
					if(res.data.registerTime[i] == ""){
						continue;
					}
					arr.push(res.data.registerTime[i]);
					registerTime.push(res.data.registerTime[i]);
					$(".search-box").find(".search-item-content").eq(2).find("a").each(function(){
						if($(this).text() == res.data.registerTime[i]){
							$(this).addClass("active");
						}
					})
				}
				register = [];
				for(var i = 0;i<res.data.register.length;i++){
					if(res.data.register[i] == ""){
						continue;
					}
					arr.push(res.data.register[i]);
					register.push(res.data.register[i]);
					$(".search-box").find(".search-item-content").eq(3).find("a").each(function(){
						if($(this).text() == res.data.register[i]){
							$(this).addClass("active");
						}
					})
				}
				 $("#searchTag").html(TagList(arr));
				   $("#horizontal-info").hide();
				   searchAjax();
			}
		}
	});
};
function updateLabel(){
	var label = $(".search-box").find(".search-item-content");
	label.each(function(i){
		if(i == 0){
			industry = $(this).find(".active").text();
		}else if(i == 1){
			area = $(this).find(".active").text();
		}else if(i == 2){
			registerTime = [];
			$(this).find(".active").each(function(){
				registerTime.push($(this).text());
			});
		}else if(i == 3){
			register = [];
			$(this).find(".active").each(function(){
				register.push($(this).text());
			});
		}
	})
	var req = {'industry':industry,'area':area,'registerTime':registerTime,'register':register}
	$.ajax({
	  url:'/apis/user/updateLabel.json',
	  type:'post',
	  data:JSON.stringify(req),
	  contentType:'application/json',
	  success:function(res){
		  if(res.data != null){
			  console.log("更新标签成功");
		  }
	  }
	});
};
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
$(".search-box").on("click",".search-item-content>a",function(){
	var esTime = $('#esTime').children().text().replace('全部','');
	var capital = $('#capital').children().text().replace('全部','');
	if(esTime.indexOf($(this).html()) != -1){
		$(this).addClass("active");
		$(this).siblings().eq(0).removeClass('active');
	}else if(capital.indexOf($(this).html()) != -1){
		$(this).addClass("active");
		$(this).siblings().eq(0).removeClass('active');
	}else{
		var _id = $(this).attr("id");
		$(this).addClass("active").siblings().removeClass("active");
	}
});
$("#search_tag").on("click",function () {
    $("#myModal").modal("show");
   
});
$("#LabelBlue").click(function(){
	$("#myModal").modal("hide");
		var arr = [];
		var s = $(".search-box").find('.search-item-content');
		s.each(function(i){
			if(i == 0){
				industry = $(this).find('.active').text();
			}else if(i == 1){
				area = $(this).find('.active').text();
			}else if(i == 2){
				registerTime = [];
				var reTime = $(this).find('.active');
				reTime.each(function(){
					var h = $(this).html();
					registerTime.push(h);
				});
			}else if(i == 3){
				register = [];
				var reg = $(this).find('.active');
				reg.each(function(){
					var r = $(this).html();
					register.push(r);
				});
			}
		})
		var select = $(".search-box").find('.active');
		arr = [];
		select.each(function(){
			arr.push($(this).html());
		});
	    $("#searchTag").html(TagList(arr));
	   $("#horizontal-info").hide();
	   updateLabel();
	   searchAjax();
	
});
function TagList(arr){
	var array=[];
	$.each(arr,function(index,item){
		if(item=="全部"){
			return true;
		}else{
			var i = index+1;
			array.push(
					'<button class="btn btn-fill btn-blue search-tag" id="'+i+'+'+item+'">'+item
					+'<span type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</span></button>'
			);
		}
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
            	/* opacity:.5, */
            	 color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#20c2fe'},
                        {offset: 0.5, color: '#6e92fb'},
                        {offset: 1, color: '#bd62f7'}
                    ]
                )
            },
            emphasis:{
            	opacity:1
            }
        },

        data: [],
    };

    var datalist = [
        /**
		 * x坐标 y坐标 name 标签名称 symbolSize 点大小 趋势，1:上升 0:下降
		 */
     
       
       [39, 10, '北京现代', 75, 1,.8],
       [62, 50, '百度', 60, 1,.6],
       [65, 17, '矿视科技', 40, 1,.8],
       [63, 95, '长电科技', 70, 1,.8],
       [52, 87, '腾讯', 60, 0,.5],
       [28, 70, '神州数据', 44, 0,.8],
       [56, 8, '华胜天成', 60, 0,.3],
       [33, 36, '神州融讯', 50, 0,.3],
    
    ];
   
  
   
    var dataMap = datalist.map((item) => {
            return Object.assign({}, dot, {
                symbolSize: item[3],
                itemStyle:{
                	normal:{
                		opacity:item[5],
                		color: new echarts.graphic.LinearGradient( 
                				0, 0, 0, 1,[
                				 {offset: 0, color: '#20c2fe'},
                				 {offset: 0.5, color: '#6e92fb'},
                				 {offset: 1, color: '#bd62f7'}
                				 ])},
                				 emphasis:{
                		            	opacity:1
                		            }
                },
                data: [
                    item
                ]
            })
        });
    var dataListInfo = [ // 浪潮
                [33, 15, '大数据', 10, 1,.8,'left'],
                [33, 2, '人工智能', 10, 1,.8,'left'],
                [44, 10, '物联网', 10, 0,.8,'right'],
                         
                 // 华为
                [62, 62, '华为', 10, 0,.6,'top'],
                [59, 40, '华为', 10, 0,.6,'right'],
                [67, 50, '华为', 10, 0,.6,'right'],
                         
                 // 亚信
                [69, 17, '亚信', 10, 1,.8,'right'],
                [65, 8, '亚信', 10, 1,.8,'right'],
                [65, 26, '亚信', 10, 1,.8,'right'],
                       
                 // 小米
                 [58, 93, '小米', 10, 1,.8,'top'],
                 [68, 95, '小米', 10, 1,.8,'right'],
                 [63, 80, '小米', 10, 1,.8,'bottom'],
                         
                 // 360
                 [52, 99, '360', 10, 0,.5,'top'],
                 [48, 82, '360', 10, 0,.5,'bottom'],
                 [56, 82, '360', 10, 0,.5,'bottom'],
                        
                 // 微软
                 [24, 70, '微软', 10, 0,.8,'left'], 
                 [28, 81, '微软', 10, 0,.8,'top'],
                 [32, 69, '微软', 10, 0,.8,'right'],
                        
                 // 网易
                 [52, 3, '网易', 10, 0,.5,'left'],
                 [61, 3, '网易', 10, 0,.5,'right'],
                 [56, 21, '网易', 10, 0,.5,'top'],
                         
                 // 网易
                 [29, 36, '网易', 10, 0,.5,'left'],
                 [37, 36, '网易', 10, 0,.5,'top'],
                 [33, 47, '网易', 10, 0,.5,'top'],
                         
                 // 中科点击
                 [47, 64, '物联网', 10, 1,.5,'right'],
                 [55, 41, '人工智能', 10, 1,.5,'bottom'],
                 [44, 41, '大数据',10, 1,.5,'bottom']
                         ];
              var dataInfoMap = dataListInfo.map((item) => {
                  return Object.assign({}, dot, {
                    symbolSize: item[3],
                     label: {
                     normal: {
                        show: true,
                         textStyle: {
                         color: '#fff'
                         },
                      position:item[6],
                        formatter: function(param) {
                           return param.data[2];
                           },
                        },
                      },
                     itemStyle:{
                       normal:{
                          opacity:item[5],
                          color: new echarts.graphic.LinearGradient( 
                          0, 0, 0, 1,[
                           {offset: 0, color: '#20c2fe'},
                           {offset: 0.5, color: '#6e92fb'},
                           {offset: 1, color: '#bd62f7'}
                           ])},
                          emphasis:{
                             opacity:1
                           }
                          },
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
        symbolSize: 80,
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
            [50, 50, '中科点击', '#fff']
        ]
        }]
//    },...dataInfoMap]
};
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
    			 $('#horizontal-info').html(
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
function searchAjax(){
	var param = {'industry':industry,'area':area,'registerTime':registerTime,'register':register};
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
//            	var arr = initEchartData(array,datalist,dataListInfo);
            	var arr = initEchartData(array,datalist);
            	/** 根据返回结果构建echart图形 */
            	 $("#charts").height($(window).height()-$(".navbar-trans").height()-$(".footer").height()-192-$(".mt50.mb20").height());
            	     option.series.data=arr;
            	    option.series[8].data.push(
            	    		[50, 50, array[array.length-1].companyName, '#fff']
            	    );
            	    charts.setOption(option);
            	   
            	
            }
		}
	});
};
/*function initEchartData(array,datalist,dataListInfo){
	var data=[];
	var b=[];
	var c =[];
	$.each(array,function(index,item){
		var a = datalist[index];
		if(index<=7){
			a[2]=item.companyName;
			data.push(b.push(a));
			c.push(item.industry);
			c.push(item.induszero);
			c.push(item.industryLabel);
		}
		if(index==(array.length-1)){
			c.push(item.industry);
			c.push(item.induszero);
			c.push(item.industryLabel);
		}
	});
	$.each(c,function(index,item){
		var a =  dataListInfo[index];
		var d = [];
		if(item==null||item==""){
			a[2]=item;
			a[3]=0;
		}else{
			a[2]=item;
		}
		data.push(d.push(a));
	});
	return data;
};*/
function initEchartData(array,datalist,dataListInfo){
	var data=[];
	var b=[];
	$.each(array,function(index,item){
		var a = datalist[index];
		if(index<=7){
			a[2]=item.companyName;
			data.push(b.push(a));
		}
		
	});
	
	return data;
};
// 关闭内容
$("#horizontal-info").on("click",".close",function () {
	$(this).parents(".layer-person").hide();
});
