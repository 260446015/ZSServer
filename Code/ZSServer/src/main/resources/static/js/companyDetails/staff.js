$(function(){
	showStaff();
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
});
function showStaff(){//展示主要人员的功能
	var req = {"cname":companyName,"pageNumber":1}
	$.ajax({
		url:'/apis/openeyes/getStaffInfo.json',
		data:JSON.stringify(req),
		contentType:'application/json',
		type:'post',
		async:false,
		success:function(res){
			if(res.success){
				var html = '';
				var arr = res.data.result;
				console.log(arr);
				$(".tableShow").eq(1).css("display","none");
				$(".tableShow").eq(0).css("display","inline");
				for(var i=0;i<arr.length;i++){
					console.log(arr[i]);
					html += '<div class="item"><div class="slider-header">'+arr[i].name+'('+arr[i].typeJoin[0]+')</div><div class="row">' +
							'<div class="col-md-3 col-md-offset-1"><label class="control-label col-md-5">联系方式</label>' +
							'<div class="col-md-7"><input class="input phone masks" readonly value="" /></div></div>' +
							'<div class="col-md-3 col-md-offset-1"><label class="control-label col-md-5">邮箱</label>' +
							'<div class="col-md-7"><input class="input email masks" readonly value="" /></div></div>' +
							'<div class="col-md-3 col-md-offset-1"><button class="btn btn-blue">我要联系</button></div></div></div>';
				}
				$("#staff").html(html);
				$(".item").eq(0).addClass("active");
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