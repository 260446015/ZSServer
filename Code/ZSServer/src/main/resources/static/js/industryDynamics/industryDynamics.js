/**
 * Created by zhangxin on 2017/11/17.
 */
var industry = "全部";
var area = "全部";
var sort = "按热度";
pageSize = 6;
var pageNumber = 0;
var options={
		"id":"page",//显示页码的元素
		"data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":6,//每页显示数据个数
	    "callBack":function(){}
	};
$(function () {
	new Loading({isfullscreen:true,text:"正在努力加载,三秒后消失"}).show();
	$("#indus").addClass("active");
	var time ="近1周";
	getKeyWordCloud(time);
    getIndustry(0,0);
});
var scatter = echarts.init(document.getElementById("scatter"));
scatter.on('click',function(param){
	var d = $("input[type=radio]:checked").val();
	getArticleByKeyWord(d,param.name);
});

function getKeyWordCloud(d){
 	var param={time:d};
 	$.ajax({
 		url:'/indus/findKeyWord.json',
 		type:'POST',
		data:param,
 		success:function(res){
 			if(res.data==null){
 				new Alert({flag:false,text:res.message,timer:2000}).show();
 			}else{
 				var arr = res.data;
 				echartDataInit(arr);
 				option.series[0].data=data1;
 				scatter.setOption(option);
 				new Loading({isfullscreen:true}).hide();
 				getArticleByKeyWord(d,arr[0].name);
 			}
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
			value: [50, 50], symbolSize: 140, name: arr[0].name, label:label
		},
		{
			value: [25,60], symbolSize: 100, name: arr[1].name, label:label
		},
		{
			value: [65,90], symbolSize: 85, name: arr[2].name, label:label
		},
		{
			value: [32,20], symbolSize: 75, name: arr[3].name, label:label
		},
		{
			value: [55,10], symbolSize: 70, name: arr[4].name, label:label
		},
		{
			value: [70,50], symbolSize: 65, name: arr[5].name, label: label
		},
		{
			value: [35,95], symbolSize: 60, name: arr[6].name, label: label
		},
		{
			value: [50,100], symbolSize: 55, name: arr[7].name, label:label
		},
		{
			value:  [82,90], symbolSize: 50, name: arr[8].name, label:label
		},
		{
			value:  [15,80], symbolSize: 45, name: arr[9].name, label:label
		},
		{
			value:  [15,10], symbolSize: 45, name: arr[10].name, label:label
		},
		{
			value:  [82,15], symbolSize: 45, name: arr[11].name, label:label
		}
	];
	
	return data1;
};
var label = {
	normal: {
		show: true,
		textStyle: {
			fontSize: '16',
			color: '#ffffff'
		},
		position: 'inside',
		formatter: function(param) {
			return param.data.name;
		}
	}
};
var data1 = [
{
	value: [50, 50], symbolSize: 140, name: '智能', label:label
},
{
	value: [35,60], symbolSize: 100, name: '节能', label:label
},
{
	value: [65,90], symbolSize: 85, name: '大数据', label:label
},
{
	value: [32,20], symbolSize: 75, name: 'AI', label:label
},
{
	value: [55,10], symbolSize: 70, name: 'VR', label:label
},
{
	value: [70,50], symbolSize: 65, name: '科技园', label: label
},
{
	value: [35,95], symbolSize: 60, name: '行业', label: label
},
{
	value: [50,100], symbolSize: 55, name: '创新', label:label
},
{
	value:  [82,90], symbolSize: 50, name: '融合', label:label
},
{
	value:  [15,80], symbolSize: 50, name: '大数据', label:label
},
{
	value:  [15,10], symbolSize: 50, name: '智能化', label:label
},
{
	value:  [82,15], symbolSize: 50, name: '变化', label:label
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
		showEffectOn: 'emphasis',
		itemStyle: {
			normal: {
				color: "rgba(0,0,0,0)",
				borderWidth: 1,
                borderType: "solid",
				borderColor: "#093982",
                shadowBlur: 20,
                shadowColor: "#00c3f3",
                shadowOffsetX: 3,
                opacity: 1
			},
            emphasis: {
				color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#00a5fb'},
                        {offset: 0.5, color: '#00caf2'},
                        {offset: 1, color: '#00f0e8'}
                    ]
                ),
                shadowBlur: 0,
                borderWidth: 0,
                shadowOffsetX: 0,
			}
		},
		data: data1
	}]
};

function getArticleByKeyWord(a,b){
	var param={time:a,keyWord:b};
	$.ajax({
		type: 'post',
        url: "/indus/findArticleListByKeyWord.json",
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
		'<div class="col-md-6"><a class="scatter-blocks" href="/summit/getEssayDetails.json?essayId='+item.id+'">'
        +'<span class="scatter-type">【'+item.industryLabel+'】</span><span class="scatter-title">'+
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
	var param ={industry:industry,area:area,sort:sort,pageSize:pageSize,pageNumber:pageNumber};
	AjaxPost(param);
};

$(".search-box").on("click",".search-item-content>a",function(){
	$(this).addClass("active").siblings().removeClass("active");
	var _id = $(this).attr("id");
	var array = _id.split('-');
	var a = array[0];
	var b = array[1];
	getIndustry(a,b);
});


function AjaxPost(param){
	$.ajax({
		url:'/indus/findIndustryInfoArticleList.json',
		type:'POST',
		asynyc:false,
		contentType:'application/json',
		data:JSON.stringify(param),
		success:function(res){
			if(res.data.dataList.length==0){
				new Alert({flag:false,text:"暂无数据",timer:2000}).show();
				$('#industryInfoList').html('');
				$('#page').html("");
			}else{
				var arr = res.data;
				$('#industryInfoList').html(ShowArticleList(arr.dataList));
				
				if(res.data.totalPage>1){
					page.init(res.data.totalNumber,res.data.pageNumber,options);
					$("#"+page.pageId +">li[class='pageItem']").on("click",function(){
						var param={industry:industry,sort:sort,area:area,pageNumber:$(this).attr("page-data")-1,pageSize:pageSize};
						AjaxPost(param);
					});
				}else{
					$('#page').html("");
				}
			}
			
		}
	});
};
function ShowArticleList(arr){
	var array = [];
	$.each(arr,function(index,item){
		array.push(
				'<div class="col-md-12 border-bottom">'+
                '<a class="scatter-blocks no-border" href="/summit/getEssayDetails.json?essayId='+item.id+'" >'+
                '<span class="scatter-type">【'+item.industryLabel+'】</span><span class="scatter-title">'+
                item.title+'</span></a><p class="scatter-content">'+item.content+'</p>'
                +'<p class="scatter-lib"><span>发布时间:'+item.publishTime+'</span></p></div>'
		);
	});
	var inner = array.join('');
	return inner;
};