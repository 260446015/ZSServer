$(function(){
	id = GetQueryString("artId");
	showData(id);
});
var id;
function showData(_id){
	console.log(_id);
	$.ajax({
		url:'/apis/area/getDetailArt.json?id='+_id,
		success:function(res){
			$("#detailArt").html(show(res.data));
		}
	})
}
function GetQueryString(key) {// 获取地址栏中的name
	// 获取参数
	var url = window.location.search;
	// 正则筛选地址栏
	var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
	// 匹配目标参数
	var result = url.substr(1).match(reg);
	// 返回参数值
	return result ? decodeURIComponent(result[2]) : null;
}
function show(data){
	var before='<div class="meeting-details-box-header"><h4>'+data.title+'</h4></div><div class="item"><div><span class="iconfont icon-company"></span>涉及公司:<span>';
    var after='</span></div><div><span class="iconfont icon-shijian2"></span>发布时间:<span>'+data.publishDate+'</span></div>'+
			  '</div><pre id="kr-article-article" class="kr-article-article meeting-details-box-text" v-html="content">'+data.content+'</pre>'+
              '<div class="item"><div><span class="iconfont icon-qianbi"></span>情报采集:<span>'+data.vector+'</span></div>'+
              '<div>情报原址:<a href="'+data.articleLink+'" target="_blank">'+data.articleLink+'</a></div></div>';
   return before+after;
}