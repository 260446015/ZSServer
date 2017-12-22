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
	myAjax('人工智能',0);
	$(".mark-item").on("click",function(){
		myAjax($(this).html(),0);
	});
});
function myAjax(i,num){
	$.ajax({  
        url: "/apis/follow/findOrganizationList.json",  
        async: false,  
        data:{industry:i,pageNum:num},
        success: function (result) {  
        	if(result.success){
        		if(result.data.totalNumber==0){
            		 $('#organization_list').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
            		 $('#page').html("");
        		}else{
            		page.init(result.data.totalNumber,result.data.pageNumber,options);
	            	$("#"+page.pageId +">li[class='pageItem']").on("click",function(){
	            		myAjax(i,$(this).attr("page-data")-1);
	                });
        			$('#organization_list').html(show(result.data.dataList));
        		}
        	}else{
        		new Alert({flag:false,text:result.message,timer:2000}).show();
        	}
        }  
    }); 
}
function show(d){
	var arr = []
	var us = []
	$.each(d, function(index, item){
		var name=item.academicLeader;
		var users=name.split("、");
		$.each(users, function(index, user){
			us.push('<span style="width:250px;">'+user+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
		});
      arr.push('<div class="institution-item"><div class="item-title"><span class="icon-block bgc-green"></span>'+
			    '<span>'+item.laboratoryName+'</span></div><div class="item-body"><div class="top"><div class="left">'+
			    '<p><span class="mr40">机构性质</span><span>'+item.institutionalCategory+'</span></p><p><span class="mr40">机构产业</span><span>'+item.industry+'</span></p>'+
			    '<p><span class="mr40">依托单位</span><span>'+item.supportUnit+'</span></p><p><span class="mr40">机构网址</span><span>'+item.url+'</span></p>'+
			    '<p><span class="mr40">学科带头人</span>'+us.join('')+'</p></div>'+
			    '</div><div class="bottom"><span class="mr40  btn-clicked" onclick="liaison('+item.id+')">意向联络</span><span onclick="cancel('+item.id+')">取消关注</span></div></div></div>'
      		);
    });
    return arr.join('');
}
function cancel(id){
	$.ajax({  
        url: "/apis/follow/cancelOrganization.json",  
        async: false,  
        data:{id:id},
        success: function (result) {  
        	if(result.success){
        		new Alert({flag:true,text:"操作成功"}).show();
    			setTimeout("window.location.reload()",2000);
        	}else{
        		new Alert({flag:false,text:result.message,timer:2000}).show();
        	}
        }
	});
}
function liaison(id){
	$.ajax({  
        url: "/apis/follow/liaison.json",  
        async: false,  
        data:{id:id},
        success: function (result) {  
        	if(result.success){
        		new Alert({flag:true,text:result.data}).show();
        	}else{
        		new Alert({flag:false,text:result.message,timer:2000}).show();
        	}
        }
	});
}