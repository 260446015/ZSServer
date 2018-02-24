/**
 * Created by zhangxin on 2017/11/22.
 */
$(function(){
    $("#gardenMap").addClass("active");
    $("#indus").removeClass("active");
    $("#report").removeClass("active");
    $("#all").addClass("active");
    area = GetQueryString("area");
    showGardenindustry();
    showGardenArea();
    $("#gardenArea a").each(function(){
    	if($(this).html() == area){
    		$(this).addClass("active").siblings().removeClass("active");
    	}
    });
    $(".search-box").on("click",".search-item-content>a",function(){
        $(this).addClass("active").siblings().removeClass("active");
        showGardenList(sortType,pageNumber,pageSize);
    });
    showGardenList(sortType,pageNumber,pageSize);
});
var area;
var pageNumber = 0;
var pageSize = 10;
var options={
    "id":"page",//显示页码的元素
    "data":null,//显示数据
    "maxshowpageitem":5,//最多显示的页码个数
    "pagelistcount":10,//每页显示数据个数
    "callBack":function(){}
};
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
                var html = '';
                for(var i=0;i<arr.length;i++){
                    html += '<a href="javascript:void(0);" class="search-item">'+arr[i].industryOne+'</a>';
                }
                $("#gardenIndustry").append(html);
            }
        }
    });
}
function showGardenArea(){//获取园区地域分组
    $.ajax({
        type:'get',
        url:'/apis/area/getGardenArea.json',
        async:false,
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
function showGardenList(d,e,f){
    var msg = new Array();
    var arr = $(".search-box").find(".active");
    arr.each(function(){
        msg.push($(this).html());
    });
    msg.push(d);
    var req = {"pageNumber":e,"pageSize":f,"msg":msg};
    $.ajax({
        type:'post',
        url:'/apis/area/findGardensList.json',
        data:JSON.stringify(req),
        contentType:'application/json',
        success:function(res){
            if(res.success){
                var arr = res.data.content;
                var html = "";
                if(arr.length != 0){
                    for (var i = 0; i < arr.length; i++) {
                        var gid = arr[i].id;
                        html += '<div class="col-md-12 border-bottom">' +
                            '<div class="layout-box">' +
                            '<div class="left-img">' +
                            '<img src="'+arr[i].gardenPicture+'" width="160" /></div>' +
                            '<div class="right-list">' +
                            '<a class="scatter-blocks no-border" href="/apis/area/garden/allCityParkDetails.html?name='+arr[i].gardenName+'">' +
                            '<span class="scatter-title">'+arr[i].gardenName+'</span>';
                        if(arr[i].gardenLevel != null && arr[i].gardenLevel != ''){
                        	html += '<span class="scatter-type ml10">'+arr[i].gardenLevel+'</span>';
                        }
                        	html += '<span class="pull-right">入驻企业<span class="numbers">'+arr[i].enterCount+'</span>家</span></a>' +
                            '<p class="park-address">' +
                            '<span class="glyphicon glyphicon-map-marker"></span>'+arr[i].address+'</p>' +
                            '<p class="net-address">' +
                            '<span class="glyphicon glyphicon-globe"></span>'+arr[i].gardenWebsite;
                        if(arr[i].flag)
                            html += '<a href="javascript:void(0);" class="follow pull-right" onclick="attentionGarden('+arr[i].id+',false);">取消关注</a></p></div></div></div>';
                        else
                            html += '<a href="javascript:void(0);" class="follow pull-right" onclick="attentionGarden('+arr[i].id+',true);">添加关注</a></p></div></div></div>';
                    }
                }else{
                    new Alert({
                        flag : false,
                        text : '暂无数据',
                        timer : 2000
                    }).show();
                }
                $("#gardenList").html(html);
                if(res.data.totalPages>1){
                    page.init(res.data.totalElements,res.data.number+1,options);
                    $("#"+page.pageId +">li[class='pageItem']").on("click",function(){
                        showGardenList(sortType,$(this).attr("page-data")-1,pageSize);
                    });
                }else{
                    $('#page').html("");
                }
            }
        }
    });
}
function attentionGarden(id,flag){//关注园区
    $.ajax({
        type:'get',
        url:'/apis/area/attentionGarden.json?gardenId='+id+'&flag='+flag,
        success:function(res){
            if(res.success){
                if(flag)
                    new Alert({flag:true,text:'关注成功',timer:2000}).show();
                else
                    new Alert({flag:true,text:'取消关注成功',timer:2000}).show();
            }else{
                new Alert({flag:true,text:'关注失败',timer:2000}).show();
            }
            showGardenList(sortType,pageNumber,pageSize);
        }
    });
    if(flag){
    	scanGarden(id);
    }
}
function scanGarden(id){
	$.ajax({
		 url:'/apis/area/scanGarden.json?gardenId='+id,
		 success:function(res){
		 }
	});
}