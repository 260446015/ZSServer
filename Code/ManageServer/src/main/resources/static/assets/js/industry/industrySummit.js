
var industry = "全部";
var area = "全部";
var sort = "按热度";
var pageSize = 8;
var pageNumber = 0;
var options={
		"id":"page",//显示页码的元素
		"data":null,//显示数据
	    "maxshowpageitem":5,//最多显示的页码个数
	    "pagelistcount":8,//每页显示数据个数
	    "callBack":function(){}
};

$(function(){
	$('#industry_info').addClass("active");
	getIndustry(0,0);
});


$(".search-box").on("click",".search-item-content>a",function(){
	$(this).addClass("active").siblings().removeClass("active");
	var _id = $(this).attr("id");
	console.log(_id);
	var array = _id.split('-');
	var a = array[0];
	var b = array[1];
	getIndustry(a,b);
});

function getIndustry(a,b){
	if(a==1){
		industry = b;
		getLabel(industry);
	}else if(a==2){
		area = b;
	}else if(a==3){
		sort = b;
	}
	var param={industry:industry,area:area,sort:sort,pageSize:pageSize,pageNumber:pageNumber}
	SummitInfo(param);
};
function SummitInfo(e){
	$.ajax({
		type:'POST',
		url:'/apis/industrysummit/findIndustrySummitInfo.json',
		asynyc:false,
		contentType:'application/json',
		data:JSON.stringify(e),
		success:function(res){
			if( res.message != null){
				$('#industrySummitList').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
			}else{
				$('#industrySummitList').html(show(res.data.dataList));
				if(res.data.totalPage>1){
					page.init(res.data.totalNumber,res.data.pageNumber,options);
					$("#"+page.pageId +">li[class='pageItem']").on("click",function(){
	            		var param={industry:industry,sort:sort,area:area,pageNumber:$(this).attr("page-data")-1,pageSize:pageSize};
	            		SummitInfo(param);
	                });
				}else{
					$('#page').html("");
				}
			}
			initPage();
		 }
			
		});
};
function show(data){
	var arr=[];
	$.each(data,function(index,item){
		var imgsrc='/images/notData.png';
		if(item.logo.length != 0){
			imgsrc=item.logo;
		};
		arr.push(
			'<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+item.id+'"/><td>' 
			+item.title+ '</td><td>'
			+item.publishTime+ '</td><td>'
			+item.area + '</td><td>' 
			+item.address + '</td><td>'
			+item.exhibitiontime + '</td><td>'
			+imgsrc + '</td><td class="actions">'
            +'<a href="javascript:void(0);" class="on-default editinfo"><i class="fa fa-pencil"></i></a>'
            +'<a href="javascript:void(0);" class="on-default removeinfo modal-basic"><i class="fa fa-trash-o"></i></a></td></tr>'
		);
	});
	var inner = arr.join('');
	return inner;
};
function initPage(){
    $(".editinfo").on("click",function(){
        var _id = $(this).parents('.gradeX').find( 'input' ).val();
        window.location.href="/apis/industrysummit/editIndustrySummit.html?id="+_id;
    })
    $("#addToTable").on("click",function(i){
        window.location.href="/apis/industrysummit/editIndustrySummit.html";
    })
    $(".removeinfo").on("click",function(i){
        var _id = $(this).parents('.gradeX').find( 'input' ).val();
        layer.confirm('确定要删除该数据？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            $.ajax({
                async:false,
                url : "/apis/industrysummit/deleteIndustrySummit.json?id="+_id,
                success : function(res) {
                    if(res.success){
                        layer.msg('成功删除', {icon: 1});
                        getIndustry(0,0);
                    }else{
                        layer.msg(res.message, {icon: 2});
                    }
                }
            });
        });
    })
};

