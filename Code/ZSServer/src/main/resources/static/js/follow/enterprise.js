var industry="全部";
var size="全部";
var time="全部";
var money="全部";
var area="全部";
var group="全部";
$("#followItem").addClass("active");
$("#companyItem").addClass("active");
$(".search-item").on("click",function(){
	$(this).addClass("active").siblings().removeClass("active");
	var type = $(this).parent().siblings().html();  
	if(type.trim()=='产业'){
		industry=$(this).html();
	}else if(type.trim()=='企业规模'){
		size=$(this).html();
	}else if(type.trim()=='成立时间'){
		time=$(this).html();
	}else if(type.trim()=='注册资本'){
		money=$(this).html();
	}else if(type.trim()=='地域'){
		area=$(this).html();
	}else if(type.trim()=='企业分组'){
		group=$(this).html();
	}
});
String.prototype.trim=function() {
    return this.replace(/(^\s*)|(\s*$)/g,'');
}
$(function(){
	addIndustryItem();
	addAreaItem();
	myAjax();
});
function addIndustryItem(){
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
            }else{
        		new Alert({flag:false,text:result.message,timer:2000}).show();
        	}
        }
    });
}
function addAreaItem(){
	$.ajax({
        type:'get',
        url:'/apis/area/getGardenArea.json',
        success:function(res){
            if(res.success){
                var arr = res.data;
                var html = '';
                for(var i=0;i<arr.length;i++){
                    html += '<a href="javascript:void(0);" class="search-item">'+arr[i]+'</a>';
                }
                $("#gardenArea").append(html);
            }else{
        		new Alert({flag:false,text:result.message,timer:2000}).show();
        	}
        }
    });
}
function myAjax(){
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
                console.log(arr);
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