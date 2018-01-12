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
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		url:'/apis/openeyes/getStaffInfo.json',
		data:JSON.stringify(req),
		async:false,
		contentType:'application/json',
		type:'post',
		async:false,
		success:function(res){
			if(res.success){
				var html = '';
				arr  = res.data.data.result;
				$(".tableShow").eq(1).css("display","none");
				$(".tableShow").eq(0).css("display","inline");
				for(var i=0;i<arr.length;i++){
					html += '<div class="item"><div class="slider-header">'+arr[i].name+'('+arr[i].typeJoin[0]+')</div><div class="row">' +
							'<div class="col-md-3 col-md-offset-1"><label class="control-label col-md-5">联系方式</label>' +
							'<div class="col-md-7"><input class="input phone masks" readonly value="" /></div></div>' +
							'<div class="col-md-3 col-md-offset-1"><label class="control-label col-md-5">邮箱</label>' +
							'<div class="col-md-7"><input class="input email masks" readonly value="" /></div></div>' +
							'<div class="col-md-3 col-md-offset-1"><button class="btn btn-blue" onclick="telContact(this)">我要联系</button></div></div></div>';
				}
				$("#staff").html(html);
				$(".item").eq(0).addClass("active");
			}else{
				$(".tableShow").eq(1).css("display","none");
				$(".tableShow").eq(0).css("display","inline");
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#staff").html(html);
				$(".item").eq(0).addClass("active");
			}
		}
	});
}
function showHolder(){//展示股东的功能
	var req = {"cname":companyName,"pageNumber":1,"pageSize":200}
	$.ajax({
		url:'/apis/openeyes/getHolder.json',
		data:JSON.stringify(req),
		async:false,
		contentType:'application/json',
		type:'post',
		success:function(res){
			if(res.success){
				var html = '';
				arr = res.data.data.result;
				var thead = '<tr class="tabTitle"><th class="text-left">股东</th><th class="text-left">出资比例</th><th class="text-left">认缴出资</th></tr>';
				$("#holder").prev().html(thead);
				$(".tableShow").eq(0).css("display","none");
				$(".tableShow").eq(1).css("display","inline");
				for(var i=0;i<arr.length;i++){
					html += '<tr><td>'+arr[i].name+'</td>' +
							'<td>'+arr[i].capital[0].percent+'</td><td>'+arr[i].capital[0].amomon+'</td></tr>'
				}
				$("#holder").html(html);
			}else{
				$(".tableShow").eq(0).css("display","none");
				$(".tableShow").eq(1).css("display","inline");
				var html = '<div class="not-data" style="text-align:center"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>';
				$("#holder").html(html);
				window.setTimeout(goBack, 2000); 
			}
		}
	});
}
function telContact(element){
	var name = $(element).parents(".row").prev().html();
	
	$.ajax({
		url:'/apis/company/telContact.json?name='+name+'&cname='+companyName,
		success:function(res){
			if(res.success){
				if(res.data){
					new Alert({flag:true,text:'正在为您全力联系，稍后可在个人中心中查看',timer:2000}).show();
				}else{
					new Alert({flag:false,text:'暂无数据',timer:2000}).show();
				}
			}
		}
	});
}
function showNews(){
	$.ajax({
		url:'/apis/openeyes/searchInfo.json?target=riskinfo&pageNum=1&name=广西医科大学',
		success:function(res){
			console.log(res.data);
		}
	})
}