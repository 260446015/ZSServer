/**
 * Created by zhangxin on 2017/11/22.
 */
$(function(){
	var value = GetQueryString("area");
	if(value != null)
		area = value;
	showGardenindustry();
	showGardenArea();
	showGardenList(industryType,area,sort,sortType);
});

function GetQueryString(key) {//获取地址栏中的name
	// 获取参数
	var url = window.location.search;
	// 正则筛选地址栏
	var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
	// 匹配目标参数
	var result = url.substr(1).match(reg);
	// 返回参数值
	return result ? decodeURIComponent(result[2]) : null;
}
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
function showGardenArea(){//获取园区地域分组
	$.ajax({
		type:'get',
		url:'/apis/area/getGardenArea.json',
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
	showGardenList(industryType,area,sort,sortType);
}
function sendArea(data){
	area = data;
	showGardenList(industryType,area,sort,sortType);
}
function sendSort(data){
	sort = data;
	showGardenList(industryType,area,sort,sortType);
}

var industryType = '全部';
var area = '全部';
var sort = '园区占地';
var sortType = 'desc';
function showGardenList(a,b,c,d){
	var req = {"pageNumber":0,"pageSize":6,msg:[a,b,c,d]};
	$.ajax({
		type:'post',
		url:'/apis/area/findGardensList.json',
		data:JSON.stringify(req),
		contentType:'application/json',
		success:function(res){
			if(res.success){
				var arr = res.data.content;
				var html = "";
				for (var i = 0; i < arr.length; i++) {
					var gid = arr[i].id;
					html += '<div class="col-md-12 border-bottom">' +
							'<div class="layout-box">' +
								'<div class="left-img">' +
									'<img src="'+arr[i].gardenPicture+'" width="160" /></div>' +
							'<div class="right-list">' +
								'<a class="scatter-blocks no-border" href="/apis/area/garden/allCityParkDetails?name='+arr[i].gardenName+'">' +
								'<span class="scatter-title">'+arr[i].gardenName+'</span>' +
								'<span class="scatter-type ml10">'+arr[i].gardenLevel+'</span>' +
								'<span class="pull-right">入驻企业<span class="numbers">'+arr[i].enterCount+'</span>家</span></a>' +
							'<p class="park-address">' +
								'<span class="glyphicon glyphicon-map-marker"></span>'+arr[i].address+'</p>' +
							'<p class="net-address">' +
								'<span class="glyphicon glyphicon-globe"></span>'+arr[i].gardenWebsite;
					if(arr[i].flag)
						html += '<a href="javascript:void(0);" class="follow pull-right" onclick="attentionGarden('+arr[i].id+',false);">取消关注</a></p></div></div></div>';
					else
						html += '<a href="javascript:void(0);" class="follow pull-right" onclick="attentionGarden('+arr[i].id+',true);">添加关注</a></p></div></div></div>';
				}
				$("#gardenList").html(html);
			}
		}
	});
}
function attentionGarden(id,flag){//关注园区
	$.ajax({
		type:'get',
		url:'/apis/area/attentionGarden.json?gardenId='+id+'&flag='+flag,
		async:false,
		success:function(res){
			if(res.success){
				if(flag)
					new Alert({flag:false,text:'关注成功',timer:2000}).show();
				else
					new Alert({flag:false,text:'取消关注成功',timer:2000}).show();
			}else{
				new Alert({flag:true,text:'关注失败',timer:2000}).show();
			}
		}
	});
	showGardenList(industryType,area,sort,sortType);
}