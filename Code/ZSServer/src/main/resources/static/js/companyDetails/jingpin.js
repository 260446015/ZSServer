$(function(){
	showJinpin();
});
function showJinpin(){
	var req = {"cname":companyName,"pageNumber":1,"pageSize":10}
	$.ajax({
		type:'post',
		data:JSON.stringify(req),
		contentType:'application/json',
		url:'/apis/openeyes/getJingPin.json',
		success:function(res){
			if(res.success){
				var arr = res.data.result.page.rows;
				console.log(arr);
				var html = '';
				for(var i=0;i<arr.length;i++){
					var dateStr = getFormatDate(new Date(arr[i].setupDate));
					html += '<tr><td>'+arr[i].companyName+'</td><td>'+arr[i].hangye+'</td>' +
							'<td>'+arr[i].jingpinProduct+'</td><td>'+arr[i].location+'</td>' +
							'<td>'+arr[i].product+'</td><td>'+arr[i].round+'</td>' +
							'<td>'+dateStr+'</td><td>'+arr[i].value+'</td><td>'+arr[i].yewu+'</td></tr>'
				}
				$("#jingpin").html(html);
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