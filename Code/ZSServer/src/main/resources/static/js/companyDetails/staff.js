$(function(){
	$(".tabs-custom").find("a").on("click",function(){
		var value = $(this).text();
		$(this).parent("li").addClass("active");
		$(this).parent("li").siblings().removeClass("active");
		if('高管' == value){
			showStaff();
		}else if('股东' == value){
			showHolder();
		}
	});
	showStaff(companyName);
});
function showStaff(companyName){//展示主要人员的功能
	var req = {"cname":companyName,"pageNumber":1}
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
				$(".tableShow").eq(1).css("display","none");
				$(".tableShow").eq(0).css("display","inline");
				for(var i=0;i<arr.length;i++){
					html += '<tr><td>'+arr[i].name+'<a href="javascript:void(0)" >他有7家公司>></a></td>' +
							'<td>77.08%</td><td>143，934.0478万元</td><td>2017-09-09</td></tr>'
				}
			}
		}
	});
}
function showHolder(){//展示股东的功能
	var req = {"cname":companyName,"pageNumber":1}
	$.ajax({
		url:'/apis/openeyes/getHolder.json',
		data:JSON.stringify(req),
		contentType:'application/json',
		type:'post',
		success:function(res){
			if(res.success){
				var html = '';
				var arr = res.data.data.result;
				console.log(arr);
				$(".tableShow").eq(0).css("display","none");
				$(".tableShow").eq(1).css("display","inline");
				for(var i=0;i<arr.length;i++){
					html += '<tr><td>'+arr[i].name+'</td>' +
							'<td>'+arr[i].capital[0].percent+'</td><td>'+arr[i].capital[0].amomon+'</td></tr>'
				}
			}
			$("#holder").html(html);
		}
	});
}