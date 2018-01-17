$('#industry_info').addClass("active");
var industry ="人工智能";
$(function () {
	findMapInfo(industry);
});



$(".search-box").on("click",".search-item-content>a",function(){
	$(this).addClass("active").siblings().removeClass("active");
	var _id = $(this).attr("id");
	console.log(_id);
	industry = _id;
	findMapInfo(_id);
	
});

function findMapInfo(e){
	console.log("产业类型为:"+e);
	 $.ajax({
	        type : "GET",
	        async:false,
	        url : "/apis/industrymap/findIndustryMapInfo.json?industry="+e,
	        success:function(res){
	        	if(res.message!=null){
	        		$('#industryRank').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
	        		$('#industryinst').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
	        		$('#indusrySummit').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
	        	}else{
	        		var data = 	res.data;
	        		console.log(data);
	        		getRankInfo(data.rank);
	        		getSummitInfo(data.summit);
	        		getCountInfo(data.count);
	        	}
	        }
	 });
};
//获取产业热度排行数据
function getRankInfo(e){
	var arr = e;
	console.log(e);
	if(arr.length<=0){
		$('#industryRank').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
	}else{
		var string = '';
		for(var i = 0; i<arr.length;i++){
		 string += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td>' 
		 + arr[i].id+ '</td><td>' 
		 + arr[i].area + '</td><td>' 
		 + arr[i].count + '</td><td>'
		 + arr[i].industry + '</td>'
		 + '<td class="actions"><a href="javascript:void(0);" class="hidden on-editing save-info"><i class="fa fa-save"></i></a>'
		 + '<a href="javascript:void(0);" class="hidden on-editing cancel-info"><i class="fa fa-times"></i></a>' + '<a href="javascript:void(0);" class="on-default edit-info"><i class="fa fa-pencil"></i></a>'
		 + '<a href="javascript:void(0);" class="on-default remove-info"><i class="fa fa-trash-o"></i></a></td></tr>';
		}
		$("#industryRank").html(string);
		
	}
};
//获取产业峰会数据
function getSummitInfo(e){
	var arr = e;
	console.log(e);
	if(arr.length<=0){
		$('#indusrySummit').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
	}else{
		var string = '';
		for(var i = 0; i<arr.length;i++){
		 string += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td>' 
		 + arr[i].id+ '</td><td>' 	
		 + arr[i].publishTime+ '</td><td>' 	
		 +'<a href="'+ arr[i].articleLink+'" target="_blank">'+ arr[i].title+ '</a></td><td>' 
		 + arr[i].exhibitiontime + '</td><td>' 
		 + arr[i].idustryZero + '</td><td>'
		 + arr[i].idustryTwice + '</td><td>'
		 + arr[i].idustryThree + '</td>'
		 + '<td class="actions"><a href="javascript:void(0);" class="hidden on-editing save-info"><i class="fa fa-save"></i></a>'
		 + '<a href="javascript:void(0);" class="hidden on-editing cancel-info"><i class="fa fa-times"></i></a>' + '<a href="javascript:void(0);" class="on-default edit-info"><i class="fa fa-pencil"></i></a>'
		 + '<a href="javascript:void(0);" class="on-default remove-info"><i class="fa fa-trash-o"></i></a></td></tr>';
		}
		$("#indusrySummit").html(string);
	}
};
//获取重点实验室数量
function getCountInfo(e){
	var arr = e;
	console.log(e);
    if(arr.length<=0){
    	$('#industryinst').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
	}else{
		var string = '';
		for(var i = 0; i<arr.length;i++){
			var array = arr[i];
		 string += '<tr class="gradeX"><td>' 
		 + array[0]+ '</td><td>' 
		 + array[1] + '</td><td>' 
		 + industry + '</td>'
		 + '<td class="actions"><a href="javascript:void(0);" class="hidden on-editing save-info"><i class="fa fa-save"></i></a>'
		 + '<a href="javascript:void(0);" class="hidden on-editing cancel-info"><i class="fa fa-times"></i></a>' + '<a href="javascript:void(0);" class="on-default edit-info"><i class="fa fa-pencil"></i></a>'
		 + '<a href="javascript:void(0);" class="on-default remove-info"><i class="fa fa-trash-o"></i></a></td></tr>';
		}
		$("#industryinst").html(string);
	}
};
