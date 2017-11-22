/**
 * Created by zhangxin on 2017/11/20.
 */
var option;
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
});
$("input[type=checkbox]").iCheck({
	checkboxClass: 'icheckbox_square-blue'
});
var invest="全部";
var area="全部";
var industry="全部";
var sort="按时间";
$(function(){
	$.ajax({
        url: "/apis/financing/getFinancingDynamic.json",
        success: function (response) {
            if(response.message!=null){
            	alert(response.message);
            }else{
           		showDynamic(response.data.dataList);
            }
        }
    });
	var datalist= new Array();
    datalist.push("人工智能");
	myCheck(datalist);
	myCharts(datalist,'month');
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
            	alert(response.message);
            }else{
            	changeOption(response.data);
            	barCharts.setOption(option);
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
	            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'蒸发量',
	            type:'bar',
	            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }
	        },
	        {
	            name:'降水量',
	            type:'bar',
	            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
	            markPoint : {
	                data : [
	                    {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183},
	                    {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name : '平均值'}
	                ]
	            }
	        }
	    ]
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
            	alert(response.message);
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
            	alert(response.message);
            }else{
           		$('#city_list').html(show(response.data.dataList));
            }
        }
    });
}
function showCheck(d){
	var arr = [];
	$.each(d, function(index, item){
		arr.push('<div class="search-group"><div class="col-title">1500亿人民币</div>'+
				  '<div class="col-title"><a href="javascript:void(0);" class="search-item">紫光集团</a></div>'+
				  '<div class="col-title"><a href="javascript:void(0);" class="search-item">人工智能</a></div></div>');
	});
	$('#company').html(arr.join(''));
}
function showDynamic(d){
	var arr = [];
	var arr2 = [];
	$.each(d, function(index, item){
		var inner='<div class="model-body border-no-shadow"><div class="row"><div class="col-md-12 border-bottom">'+
        '<a class="scatter-blocks no-border" href="javascript:void(0);">'+
        '<span class="icon-block"></span><span class="scatter-type">'+item.industry+'</span>'+
        '  <span class="scatter-title">'+item.title+'</span></a></div></div></div>';
		(index%2 ==0) ?arr.push(inner):arr2.push(inner); 
	});
	$('#city_dynamic').html(arr.join(''));
	$('#city_dynamic2').html(arr2.join(''));
}
function show(d){
    var arr = []
    $.each(d, function(index, item){
      var imageSrc='/images/c_logo.png';
      if(item.logo.length!=0){
      	imageSrc=item.logo;
      }
      arr.push('<tr><td class="text-center">'+item.financingDate+'</td>'+
			    '<td class="text-left"><img src="'+imageSrc+'" class="c-logo"/>'+
			    '<p>'+item.financingCompany+'</p><p><span class="c-tag">'+item.industry+'</span>  <span class="c-tag">'+item.area+'</span></p></td>'+
			    '<td class="text-center">'+item.invest+'</td>'+
			    '<td class="text-center">'+item.financingAmount+'</td>'+
			    '<td class="text-center"><p>'+item.investor+'</p></td>'+
			    '<td class="text-center"><a href="'+item.articleLink+'" target="_blank" class="btn btn-fill btn-blue">详情</a></td></tr>'
      		);
    });
    var inner=arr.join('');
    return inner;
}