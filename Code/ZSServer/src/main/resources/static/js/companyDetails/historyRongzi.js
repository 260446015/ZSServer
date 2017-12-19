$(function(){
	showRongZi();
});
function showRongZi(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getHistoryRongZi.json',
		success:function(res){
			if(res.success){
				var arr = res.data.result.page.rows;
				var thead = '<tr><th class="text-left">时间</th><th class="text-left">轮次</th><th class="text-left">估值</th><th class="text-left">金额</th>'+
                            '<th class="text-left">比例</th><th class="text-left">投资方</th><th class="text-left">新闻来源</th></tr>';
				$("#rongzi").prev().html(thead);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var dateStr = getFormatDate(new Date(arr[i].date));
					var tzArr = arr[i].investorName.split("，");
					var tzHtml = '';
					for(var j=0;j<tzArr.length;j++){
						tzHtml += '<p>'+tzArr[j]+'</p>';
					}
					html += '<tr><td>'+dateStr+'</td><td>'+arr[i].round+'</td><td>'+arr[i].value+'</td>' +
							'<td>'+arr[i].money+'</td><td>'+arr[i].share+'</td><td>' + tzHtml +
							'</td><td>'+arr[i].newsTitle+'</td></tr>';
				}
				$("#rongzi").html(html);
			}else{
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#rongzi").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	}
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}
/**
 * date 为long类型 pattern 为格式化参数
 */
function getFormatDate(date, pattern) {
	if (date == undefined) {
		date = new Date();
	}
	if (pattern == undefined) {
		pattern = "yyyy-MM-dd";
	}
	return date.format(pattern);
}