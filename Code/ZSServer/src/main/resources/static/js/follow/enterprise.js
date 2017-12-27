var industry="全部";
var time="全部";
var money="全部";
var area="全部";
var group="全部";
var sum=0;
var dropdownHtml="";
var operationId;
$("#followItem").addClass("active");
$("#companyItem").addClass("active");
$(".btn-fill").on("click",function(){
	$(".input_QY").val("");
});
$("#LabelBlue").on("click",function(){
	if($(".input_QY").val().replace(/(^\s+)|(\s+$)/g, "").length ==0){
		$("#message").html("请输入有效内容！");
	}else{
		var name=$(".input_QY").val();
		$.ajax({
	        type:'get',
	        url:'/apis/follow/addCompnayGroup.json?name='+name,
	        success:function(res){
	            if(res.success){
	    			window.location.reload();
	            }else{
	        		new Alert({flag:false,text:res.message,timer:2000}).show();
	        	}
	        }
	    });
	}
});
String.prototype.trim=function() {
    return this.replace(/(^\s*)|(\s*$)/g,'');
}
$(function(){
	addIndustryItem();
	addAreaItem();
	addGroupItem();
});
function injection(){
	if(sum==3){
		myAjax();
		$(".search-item").on("click",function(){
			$(this).addClass("active").siblings().removeClass("active");
			var type = $(this).parent().siblings().html();  
			if(type.trim()=='产业'){
				industry=$(this).html();
			}else if(type.trim()=='成立时间'){
				time=$(this).html();
			}else if(type.trim()=='注册资本'){
				money=$(this).html();
			}else if(type.trim()=='地域'){
				area=$(this).html();
			}else if(type.trim()=='企业分组'){
				group=$(this).html();
			}
			myAjax();
		});
	}
}
function addIndustryItem(){
	$.ajax({
        type:'get',
        url:'/apis/follow/getCompnayIndustry.json',
        success:function(res){
            if(res.success){
                var arr = res.data;
                var html = '';
                for(var i=0;i<arr.length;i++){
                    html += '<a href="javascript:void(0);" class="search-item">'+arr[i]+'</a>';
                }
                $("#gardenIndustry").append(html);
            }else{
        		new Alert({flag:false,text:res.message,timer:2000}).show();
        	}
            sum=sum+1;
            injection();
        }
    });
}
function addAreaItem(){
	$.ajax({
        type:'get',
        url:'/apis/follow/getCompnayArea.json',
        success:function(res){
            if(res.success){
                var arr = res.data;
                var html = '';
                for(var i=0;i<arr.length;i++){
                    html += '<a href="javascript:void(0);" class="search-item">'+arr[i]+'</a>';
                }
                $("#gardenArea").append(html);
            }else{
        		new Alert({flag:false,text:res.message,timer:2000}).show();
        	}
            sum=sum+1;
            injection();
        }
    });
}
function addGroupItem(){
	$.ajax({
        type:'get',
        url:'/apis/follow/getCompnayGroup.json',
        success:function(res){
            if(res.success){
                var arr = res.data;
                var html = '';
                for(var i=0;i<arr.length;i++){
                    html += '<a href="javascript:void(0);" class="search-item">'+arr[i].groupName+'</a>';
                    dropdownHtml+='<li><a href="javascript:void(0);" onclick="myMove(\''+arr[i].id+'\',\''+arr[i].groupName+'\')" >'+arr[i].groupName+'</a></li>';
                }
                $("#gardenGroup").after(html);
            }else{
        		new Alert({flag:false,text:res.message,timer:2000}).show();
        	}
            sum=sum+1;
            injection();
        }
    });
}
function myAjax(){
    var req = {industry:industry,time:time,money:money,area:area,group:group};
    $.ajax({
        type:'post',
        url:'/apis/follow/findCompnayList.json',
        data:JSON.stringify(req),
        contentType:'application/json',
        success:function(res){
            if(res.success){
            	showCompany(res.data);
            }else{
        		new Alert({flag:false,text:res.message,timer:2000}).show();
        	}
        }
    });
}
function myMove(id,name){
	 $.ajax({  
	        url: "/apis/follow/moveCompnayGroup.json",  
	        async: false,  
	        data:{id:operationId,groupId:id,name:name},
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
function getId(id){
	operationId=id;
}
function quxiao(id){
    $.ajax({  
        url: "/apis/follow/cancelCompnay.json",  
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
var options;
function showCompany(list){
	if(list.length==0){
		$('#city_list').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
		$('#page').html('');
    }else{
		options={
			"id":"page",//显示页码的元素
			"data":list,//显示数据
	       "maxshowpageitem":5,//最多显示的页码个数
	       "pagelistcount":10,//每页显示数据个数
	       "callBack":function(result){
	       	   var cHtml="";
	           for(var i=0;i<result.length;i++){
	        	   cHtml += '<div class="col-md-12 border-bottom">' +
                       '<a class="scatter-blocks no-border" href="/apis/company/baseInfo.html?companyName='+result[i].name+'" target="_blank">' +
                       '<span class="scatter-title">'+result[i].name+'</span></a>' +
                       '<p class="park-address">' +
                       '<span class="glyphicon glyphicon-map-marker"></span>'+result[i].base+'</p>' +
                       '<div class="net-address mb20">' +
                       '<span class="mr15">法定代表人：'+result[i].legalPersonName+'</span><span class="mr15">注册资本：'+result[i].regCapital+'</span><span class="mr15">注册时间：'+result[i].estiblishTime+'</span>'+
                       '<a href="javascript:void(0);" class="pull-right mr15" onclick="quxiao('+result[i].id+');">取消关注</a>'+
                       '<a href="javascript:void(0);" data-toggle="dropdown" class="pull-right mr15" onclick="getId('+result[i].id+')">移动到<b class="caret"></b></a>'+
                       '<ul class="dropdown-menu" style="position:absolute;left:87%;top:80%;width:100px;">'+dropdownHtml+'</ul></div></div>';
               }
	           $("#city_list").html(cHtml);//将数据增加到页面中
	       }
	   };
	   page.init(list.length,1,options);
	}
}