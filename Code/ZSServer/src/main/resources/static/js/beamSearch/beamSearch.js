/**
 * Created by zhangxin on 2017/11/29.
 */

$(function () {
	$("#search").addClass("active");
    //计算内容区域的高度
    //$(".right-content").height($(window).height()-$(".navbar-trans").height()-$(".footer").height()-148).css("min-height",$(".right-content .mt88").height()+88);
    $(".search-company").on("click",function () {
    	if($(this).parents(".active").length==0){
    		$(this).parents(".col-md-4").addClass("active");
    	}else{
    		if($(".input").val()==null||$(".input").val()==""){
    			new Alert({flag: "warning",text:'请输入公司名',timer:2000}).show();
    		}else{
    			window.location.href="/apis/getcompany/listCompanyByName.json?companyName="+$(".input").val() 
    		}
    	}
    });
    $(".search-company-two").on("click",function () {
		if($("#mySearch").val()==null||$("#mySearch").val()==""){
			new Alert({flag: "warning",text:'请输入公司名',timer:2000}).show();
		}else{
			window.location.href="/apis/getcompany/listCompanyByName.json?companyName="+$("#mySearch").val() 
		}
    });
    $(".upload-card").on("click",function () {
        $(this).parents(".col-md-4").addClass("active");
    });
    $("input[type=file]").on("change",function () {
        run(this, function(data){  
        	$("#myModal").modal({
                keyboard: false,
                backdrop: "static"
            });
        	$.ajax({  
                url: "/apis/getcompany/uploadImage.json",  
                type: "post",  
                dataType: "json",  
                data: {  
                    "imageBase64": data,  
                },  
                async: false,  
                success: function (result) {  
                	$('#myModal').modal('hide');
                	if(result.success&&result.data==null){
                		new Alert({flag: "warning",text:'该名片上未检索到相关公司信息',timer:3000}).show();
                    }else if(result.success){
                    	window.location.href="/apis/getcompany/listCompanyByName.json?companyName="+result.data;
                    }else{
                    	new Alert({flag:false,text:result.message,timer:2000}).show();
                    } 
                }  
            });  
        });
    });
});
function run(input_file, get_data) {  
    /*input_file：文件按钮对象*/  
    /*get_data: 转换成功后执行的方法*/  
    if (typeof (FileReader) === 'undefined') {  
    	new Alert({flag:false,text:"抱歉，你的浏览器不支持 FileReader，不能将图片转换为Base64，请使用其他浏览器操作！",timer:2000}).show();
    } else {  
        try {  
            /*图片转Base64 核心代码*/  
            var file = input_file.files[0];  
            //这里我们判断下类型如果不是图片就返回 去掉就可以上传任意文件  
            if (!/image\/\w+/.test(file.type)) {
            	new Alert({flag:false,text:"请确保文件为图像类型",timer:2000}).show();
                return false;  
            }  
            var reader = new FileReader();  
            reader.onload = function () {  
                get_data(this.result);  
            }  
            reader.readAsDataURL(file);  
        } catch (e) {  
            new Alert({flag:false,text:"图片转Base64出错啦！" + e.toString(),timer:2000}).show();
        }  
    }  
}
var options;
function showCompany(list){
	if(list.length==0){
		new Alert({flag:'warning',text:"未查询到相关讯息！",timer:2000}).show();
	}else{
		options={
			"id":"page",//显示页码的元素
			"data":list,//显示数据
	       "maxshowpageitem":5,//最多显示的页码个数
	       "pagelistcount":8,//每页显示数据个数
	       "callBack":function(result){
	       	   var cHtml="";
	           for(var i=0;i<result.length;i++){
	        	   var obj = eval('(' + result[i] + ')');
	        	   var str=obj.name;
	        	   var name=str.replace(/<em>/g,'').replace(/<\/em>/g,'');
	        	   str=str.replace(/<em>/g,'<span style="color:#00ffe4">');
	        	   str=str.replace(/<\/em>/g,'</span>');
	               cHtml+='<div class="col-md-3"><div class="img-list company-list"><div class="company-img">'+
	               '<img src="/images/ZClick.png"/></div><p class="title company-title">'+str+
	               '</p><div class="details company-details"><p> 法人:<span class="company-range">'+obj.legalPersonName+'</span></p>'+
	               '<p>成立时间:<span class="company-time">'+obj.estiblishTime.substring(0,10)+'</span></p>'+
	               '<p>注册资金:<span class="company-money">'+obj.regCapital+'</span></p>'+
	               '<p>总部地点:<span class="company-address">'+obj.base+'</span></p>'+
	               '<div class="company-mask"><div class="circle-empty"><a href="/apis/company/baseInfo.html?companyName='+name+'" class="text-center"><img src="/images/see_icon.png" /></a>'+
	               '<a href="/apis/company/baseInfo.html?companyName='+name+'" class="text-center">查看详情</a></div></div></div></div></div>';
	           }
	           $("#city_list").html(cHtml);//将数据增加到页面中
	       }
	   };
	   page.init(list.length,1,options);
	}
}