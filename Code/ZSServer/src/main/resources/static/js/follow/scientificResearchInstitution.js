var options={
	"id":"page",//显示页码的元素
	"data":null,//显示数据
    "maxshowpageitem":5,//最多显示的页码个数
    "pagelistcount":5,//每页显示数据个数
    "callBack":function(){}
};
$(function(){
	$("#followItem").addClass("active");
	$("#organizationItem").addClass("active");
	$.ajax({  
        url: "/apis/follow/findOrganizationList.json",  
        async: false,  
        data:{industry:'人工智能',pageNum:0},
        success: function (result) {  
        	if(result.success){
        		if(result.data.totalNumber==0){
            		 $('#organization_list').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
            		 $('#page').html("");
        		}else{
        			if(result.data.totalPage<2){
            			$('#page').html("");
                	}else{
                		page.init(result.data.totalNumber,result.data.pageNumber,options);
    	            	$("#"+page.pageId +">li[class='pageItem']").on("click",function(){
    	            		var param={invest:invest,sort:sort,area:area,industry:industry,pageNumber:$(this).attr("page-data")-1};
    	            		ajaxPost(param)
    	                });
                	}
        			$('#organization_list').html(show(result.data.dataList));
        		}
        	}else{
        		new Alert({flag:false,text:result.message,timer:2000}).show();
        	}
        }  
    }); 
});
function show(d){
	var arr = []
	var us = []
	$.each(d, function(index, item){
		var name=item.academicLeader;
		var users=name.split("、");
		$.each(users, function(index, user){
			us.push('<div><img src="/images/user_head.png" alt=""><p>'+user+'</p></div>');
		});
      arr.push('<div class="institution-item"><div class="item-title"><span class="icon-block bgc-green"></span>'+
			    '<span>大数据挖掘</span></div><div class="item-body"><div class="top"><div class="left">'+
			    '<p><span class="mr40">机构性质</span><span>'+item.laboratoryName+'</span></p><p><span class="mr40">机构产业</span><span>'+item.industry+'</span></p>'+
			    '<p><span class="mr40">依托单位</span><span>'+item.supportUnit+'</span></p><p><span class="mr40">机构网址</span><span>'+item.url+'</span></p></div>'+
			    '<div class="right">'+us.join('')+'</div>'+
			    '</div><div class="bottom"><span class="mr40  btn-clicked">意向联络</span><span>取消关注</span></div></div></div>'
      		);
    });
    return arr.join('');
}
