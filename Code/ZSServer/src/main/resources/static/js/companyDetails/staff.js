$(function(){
	$(".tabs-custom").find("a").on("click",function(){
		var value = $(this).text();
		if('高管' == value){
			showStaff();
		}else if('股东' == value){
			showHolder();
		}
	});
	showStaff(companyName);
});
function showStaff(companyName){//展示主要人员的功能
	var req = {"cname":'中科点击（北京）科技有限公司',"pageNumber":1}
	$.ajax({
		url:'/apis/openeyes/getStaffInfo.json',
		data:JSON.stringify(req),
		contentType:'application/json',
		type:'post',
		success:function(res){
			if(res.success){
				var html = '';
				var arr = res.data;
				console.log(arr);
				for(var i=0;i<arr.length;i++){
					html += '<tr><td>'+arr[i].name+'<a href="javascript:void(0)" >他有7家公司>></a></td>' +
							'<td>77.08%</td><td>143，934.0478万元</td><td>2017-09-09</td></tr>'
				}
			}
		}
	});
}
function showHolder(){//展示股东的功能
	
}