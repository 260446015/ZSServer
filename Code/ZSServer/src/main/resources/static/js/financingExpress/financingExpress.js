/**
 * Created by zhangxin on 2017/11/20.
 */
var option;
var options={
	"id":"page",//显示页码的元素
	"data":null,//显示数据
    "maxshowpageitem":5,//最多显示的页码个数
    "pagelistcount":10,//每页显示数据个数
    "callBack":function(){}
};
var barCharts = echarts.init(document.getElementById("barCharts"),"customed");
$("input[type=checkbox]").on('ifChanged', function(event){
	var datalist= new Array();
	$("input[type=checkbox]:checkbox").each(function(){
        if(true == $(this).is(':checked')){
        	if($(this).val()!="on"){
        		datalist.push($(this).val());
        	}
        }
    });
    myCheck(datalist);
    myCharts(datalist,"season");
});
$("input[type=checkbox]").iCheck({
	checkboxClass: 'icheckbox_square-blue'
});
var invest="全部";
var area="全部";
var industry="全部";
var sort="按时间";
$(function(){
	$("#financing").addClass("active");
	$.ajax({
        url: "/apis/financing/getFinancingDynamic.json",
        success: function (response) {
            if(response.message!=null){
            	new Alert({flag:false,text:result.message,timer:1500}).show();
            }else{
           		showDynamic(response.data);
            }
        }
    });
	var datalist= new Array();
    datalist.push("人工智能");
	myCheck(datalist);
	myCharts(datalist,'season');
	myClick(0,0);
})
function myCharts(d,type){
	$.ajax({
        type: 'post',
        url: "/apis/financing/getHistogram.json",
        async: false,
        contentType: 'application/json',
        data: JSON.stringify({industry:d,type:type}),
        success: function (response) {
            if(response.message!=null){
            	new Alert({flag:false,text:result.message,timer:1500}).show();
            }else{
            	changeOption(response.data);
            	barCharts.setOption(option,true);
            }
        }
    });
}
function changeOption(d){
	option = {
	    tooltip : {
	        trigger: 'axis'
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            data : d.charts
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : d.series
	};
}
function myCheck(d){
	$.ajax({
        type: 'post',
        url: "/apis/financing/getFinancingCompany.json",
        async: false,
        contentType: 'application/json',
        data: JSON.stringify({industry:d}),
        success: function (response) {
            if(response.message!=null){
            	new Alert({flag:false,text:result.message,timer:1500}).show();
            }else{
            	showCheck(response.data);
            }
        }
    });
}
function myClick(a,b){
    if(a==1){
    	industry=b;
    }else if(a==2){
    	area=b;
    }else if(a==3){
    	invest=b;
    }else if(a==4){
    	sort=b;
    }
    var param={invest:invest,sort:sort,area:area,industry:industry};
	ajaxPost(param);
}
function ajaxPost(param){
	$.ajax({
        type: 'post',
        url: "/apis/financing/getCompanyList.json",
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function (response) {
            if(response.message!=null){
            	new Alert({flag:false,text:result.message,timer:1500}).show();
            }else{
            	 if(response.data.totalPage>1){
	            	page.init(response.data.totalNumber,response.data.pageNumber,options);
	            	$("#"+page.pageId +">li[class='pageItem']").on("click",function(){
	            		var param={invest:invest,sort:sort,area:area,industry:industry,pageNumber:$(this).attr("page-data")-1};
	            		ajaxPost(param)
	                });
            	 }else{
            		 $('#page').html("");
            	 }
            	 $('#city_list').html(show(response.data.dataList));
            }
        }
    });
}
function showCheck(d){
	var arr = [];
	$.each(d, function(index, item){
		arr.push('<div class="search-group"><div class="col-title">'+item.financingAmount+'</div>'+
				  '<div class="col-title"><a href="/apis/getcompany/listCompanyByName.json?companyName='+item.financingCompany+'" class="search-item">'+item.financingCompany+'</a></div>'+
				  '<div class="col-title"><a href="javascript:void(0);" class="search-item">'+item.industry+'</a></div></div>');
	});
	$('#company').html(arr.join(''));
}
function showDynamic(d){
	var arr = [];
	var arr2 = [];
	$.each(d, function(index, item){
		var inner='<div class="model-body border-no-shadow"><div class="row"><div class="col-md-12 border-bottom">'+
        '<a class="scatter-blocks no-border" href="'+item.articleLink+'" target="_blank">'+
        '<span class="icon-block"></span><span class="scatter-type">【'+item.industry+'】</span>'+
        '  <span class="scatter-title">'+item.title+'</span></a></div></div></div>';
		(index%2 ==0) ?arr.push(inner):arr2.push(inner); 
	});
	$('#city_dynamic').html(arr.join(''));
	$('#city_dynamic2').html(arr2.join(''));
}
function show(d){
    var arr = []
    $.each(d, function(index, item){
      var imageSrc=item.logo;
      if(item.logo==null||item.logo.length==0){
    	  imageSrc='/images/c_logo.png';
      }
      arr.push('<tr><td class="text-center">'+item.financingDate+'</td>'+
			    '<td class="text-left"><img src="'+imageSrc+'" class="c-logo"/>'+
			    '<p><a href="/apis/getcompany/listCompanyByName.json?companyName='+item.financingCompany+'">'+item.financingCompany+'</a></p><p><span class="c-tag">'+item.industry+'</span>  <span class="c-tag">'+item.area+'</span></p></td>'+
			    '<td class="text-center">'+item.invest+'</td>'+
			    '<td class="text-center">'+item.financingAmount+'</td>'+
			    '<td class="text-center"><p>'+item.investor+'</p></td>'+
			    '<td class="text-center"><a href="'+item.articleLink+'" target="_blank" class="btn btn-fill btn-blue">详情</a></td></tr>'
      		);
    });
    var inner=arr.join('');
    return inner;
}