/**
 * Created by zhangxin on 2017/11/17.
 */
var industry = "全部";
var area = "全部";
var sort = "按热度";
$(function () {
	var time ="近一周";
	getKeyWordCloud(time);
   /* var scatter = echarts.init(document.getElementById("scatter"));
    scatter.setOption(option);*/
    
    getIndustry(0,0);
});

function getKeyWordCloud(d){
	console.log(d);
	var param={time:d};
	
	$.ajax({
		url:'/indus/findKeyWord.json',
		type:'POST',
		data:param,
		success:function(res){
			if(res.data==null){
				new Alert({flag:false,text:res.message,timer:2000}).show();
			}
			var arr = res.data;
			getArticleByKeyWord(d,arr[0].name);
			echartDataInit(arr);
			var scatter = echarts.init(document.getElementById("scatter"));
		    option.series[0].data=data1;
		    scatter.on('click',function(param){
		    	getArticleByKeyWord(d,param.name);
		    });
			scatter.setOption(option);
		}
		
	});
}
$("input[type=radio]").on("ifClicked",function(event){
	if(event.type == 'ifClicked'){
		getKeyWordCloud($(this).val());
	}
});
$("input[type=radio]").iCheck({
	radioClass: 'iradio_square-blue'
});
function echartDataInit(arr){
	data1 = [
		        {
		            value: [18,90], symbolSize: 140, name: arr[0].name, itemStyle: {normal: {color: '#5D9CEC'}},label:label
		        },
		        {
		            value: [10,20], symbolSize: 90, name: arr[1].name, itemStyle: {normal: {color: '#62C87F'}},label:label
		        },
		        {
		            value: [57,11], symbolSize: 79, name: arr[2].name, itemStyle: {normal: {color: '#F57BC1'}},label:label
		        },
		        {
		            value: [90,30], symbolSize: 79, name: arr[3].name, itemStyle: {normal: {color: '#6ED5E6'}},label:label
		        },
		        {
		            value: [85,82], symbolSize: 76, name: arr[4].name, itemStyle: {normal: {color: '#DCB186'}},label:label
		        },
		        {
		            value: [65,90], symbolSize: 74, name: arr[5].name, itemStyle: {normal: {color: '#7053B6'}},label: label
		        },
		        {
		            value: [42,100], symbolSize: 74, name: arr[6].name, itemStyle: {normal: {color: '#647C9D'}},label: label
		        },
		        {
		            value: [18,55], symbolSize: 69, name: arr[7].name, itemStyle: {normal: {color: '#F15755'}},label:label
		        },
		        {
		            value: [50, 50], symbolSize: 63, name: arr[8].name, itemStyle: {normal: {color: '#FC863F'}},label:label
		        }
		    ];
	return data1;
};
var  label = {
        normal: {
            show: true,
            textStyle: {
                fontSize: '16',
                color: '#ffffff'
            },
            formatter: function(param) {
                return param.data.name;
            }
        }
    };
    var data1 = [
 		        {
 		            value: [18,90], symbolSize: 140, name: '智能', itemStyle: {normal: {color: '#5D9CEC'}},label:label
 		        },
 		        {
 		            value: [10,20], symbolSize: 90, name: '节能', itemStyle: {normal: {color: '#62C87F'}},label:label
 		        },
 		        {
 		            value: [57,11], symbolSize: 79, name: '大数据', itemStyle: {normal: {color: '#F57BC1'}},label:label
 		        },
 		        {
 		            value: [90,30], symbolSize: 79, name: 'AI', itemStyle: {normal: {color: '#6ED5E6'}},label:label
 		        },
 		        {
 		            value: [85,82], symbolSize: 76, name: 'VR', itemStyle: {normal: {color: '#DCB186'}},label:label
 		        },
 		        {
 		            value: [65,90], symbolSize: 74, name: '科技园', itemStyle: {normal: {color: '#7053B6'}},label: label
 		        },
 		        {
 		            value: [42,100], symbolSize: 74, name: '行业', itemStyle: {normal: {color: '#647C9D'}},label: label
 		        },
 		        {
 		            value: [18,55], symbolSize:  69, name: '创新', itemStyle: {normal: {color: '#F15755'}},label:label
 		        },
 		        {
 		            value:  [50, 50], symbolSize:63, name: '融合', itemStyle: {normal: {color: '#FC863F'}},label:label
 		        }
 		    ];


    var option = {
        xAxis: {
            show: false,
            type: 'value',
            splitLine: {
                lineStyle: {
                    type: 'dashed'
                }
            }
        },
        yAxis: {
            show: false,
            type: 'value',
            splitLine: {
                lineStyle: {
                    type: 'dashed'
                }
            },
        },

        series: [{
            name: '热词',
            type: 'scatter',
            data: data1
        }]
    };

function getArticleByKeyWord(a,b){
	var param={time:a,keyWord:b};
	console.log(a);
	console.log(b);
	$.ajax({
		type: 'post',
        url: "/indus/findArticleListByKeyWord.json",
        async: false,
        data: param,
        success:function(res){
        	if(res.data==null){
        		new Alert({flag:false,text:res.message,timer:2000}).show();
        	}
        	var arr = res.data;
        	$('#articleList').html(showList(arr));
        }
	});
};
function showList(arr){
	var array=[];
	$.each(arr,function(index,item){
		array.push(
		'<div class="col-md-6"><a class="scatter-blocks" href="'+item.articleLink+'" target="_blank">'
        +'<span class="scatter-type">'+item.industryLabel+'</span><span class="scatter-title">'+
        item.title+'</span></a></div>');
	});
	var inner = array.join('');
	return inner;
};

function getIndustry(a,b){
	if(a==1){
		industry = b;
	}else if(a==2){
		area = b;
	}else if(a==3){
		sort = b;
	}
	var param ={industry:industry,area:area,sort:sort};
	AjaxPost(param);
};
function AjaxPost(param){
	$.ajax({
		url:'/indus/findIndustryInfoArticleList.json',
		type:'POST',
		asynyc:false,
		contentType:'application/json',
		data:JSON.stringify(param),
		success:function(res){
			if(res.data==null){
				new Alert({flag:false,text:res.message,timer:2000}).show();
			}
			var arr = res.data;
			$('#industryInfoList').html(ShowArticleList(arr));
		}
	});
};
function ShowArticleList(arr){
	var array = [];
	$.each(arr,function(index,item){
		array.push(
				'<div class="col-md-12 border-bottom">'+
                '<a class="scatter-blocks no-border" href="'+item.articleLink+'" target="_blank">'+
                '<span class="scatter-type">'+item.industryLabel+'</span><span class="scatter-title">'+
                item.title+'</span></a><p class="scatter-content">'+item.content+'</p></div>'	
		);
	});
	var inner = array.join('');
	return inner;
};