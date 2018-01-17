$('#industry_info').addClass("active");
var _id;
function addData(info){
	_id=info.id;
	  $("input[name='id']").val(info.id);
	  $("input[name='title']").val(info.title);
	  $("input[name='summary']").val(info.summary);
	  $("input[name='content']").val(info.content);
	  $("input[name='articleLink']").val(info.articleLink);
	  $("input[name='logo']").val(info.logo);
	  $("input[name='address']").val(info.address);
	  $("input[name='area']").val(info.area);
	  $("input[name='publishTime']").val(info.publishTime);
	  $("input[name='exhibitiontime']").val(info.exhibitiontime);
	  $("input[name='vector']").val(info.vector);
	  $("input[name='source']").val(info.source);
	  $("input[name='author']").val(info.author);
	  $("input[name='emotion']").val(info.emotion);
	  $("input[name='idustryZero']").val(info.idustryZero);
	  $("input[name='idustryTwice']").val(info.idustryTwice);
	  $("input[name='idustryThree']").val(info.idustryThree);
	  $("input[name='bus']").val(info.bus);
	  $("input[name='business']").val(info.business);
	  $("input[name='publishDate']").val(info.publishDate);
	  $("input[name='publishYear']").val(info.publishYear);
	  $("input[name='hitCount']").val(info.hitCount);
	  $("input[name='supportCount']").val(info.supportCount);
	  $("input[name='replyCount']").val(info.replyCount);
	  $("input[name='hasWarn']").val(info.hasWarn);
	  $("input[name='dimension']").val(info.dimension);
	  $("input[name='istop']").val(info.istop);
	 
};
$(".btn-success").on("click",function(){
    etitData();
});
$(".btn-danger").on("click",function(){
    if(_id==null){
        window.location.href = "/apis/industrysummit/industrySummitManage.html";
    }else {
        layer.confirm('直接离开将会失去修改内容，确认离开？', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            window.location.href = "/apis/industrysummit/industrySummitManage.html";
        });
    }
});
function etitData() {
	 var _id = $("input[name='id']").val();
	 var _title = $("input[name='title']").val();
	 var _summary = $("input[name='summary']").val();
	 var _content = $("input[name='content']").val();
	 var _articleLink = $("input[name='articleLink']").val();
	 var _logo = $("input[name='logo']").val();
	 var _address = $("input[name='address']").val();
	 var _area = $("input[name='area']").val();
	 var _publishTime = $("input[name='publishTime']").val();
	 var _exhibitiontime = $("input[name='exhibitiontime']").val();
	 var _vector = $("input[name='vector']").val();
	 var _source = $("input[name='source']").val();
	 var _author = $("input[name='author']").val();
	 var _emotion = $("input[name='emotion']").val();
	 var _idustryZero = $("input[name='idustryZero']").val();
	 var _idustryTwice = $("input[name='idustryTwice']").val();
	 var _idustryThree = $("input[name='idustryThree']").val();
	 var _bus = $("input[name='bus']").val();
	 var _business = $("input[name='business']").val();
	 var _publishDate = $("input[name='publishDate']").val();
	 var _publishYear = $("input[name='publishYear']").val();
	 var _hitCount = $("input[name='hitCount']").val();
	 var _supportCount = $("input[name='supportCount']").val();
	 var _replyCount = $("input[name='replyCount']").val();
	 var _hasWarn = $("input[name='hasWarn']").val();
	 var _dimension = $("input[name='dimension']").val();
	 var _istop = $("input[name='istop']").val();
	var param ={
		id:_id,	title:_title,summary:_summary,content:_content,articleLink:_articleLink,
		logo:_logo,address:_address,area:_area,
		publishTime:_publishTime,exhibitiontime:_exhibitiontime,vector:_vector,
		source:_source,author:_author,emotion:_emotion,idustryZero:_idustryZero,idustryTwice:_idustryTwice,
		idustryThree:_idustryThree,bus:_bus,business:_business,publishDate:_publishDate,
		publishYear:_publishYear,hitCount:_hitCount,supportCount:_supportCount,replyCount:_replyCount,
		hasWarn:_hasWarn,dimension:_dimension,istop:_istop
	};
	 $.ajax({
	        type : "post",
	        contentType : "application/json",
	        async:false,
	        url : "/apis/industrysummit/saveIndustrysummit.json",
	        data : JSON.stringify(obj),
	        success : function(res) {
	            if(res.success){
	                layer.msg('操作成功', {icon: 1});
	                window.location.href="/apis/industrysummit/industrySummitManage.html";
	            }else{
	                layer.msg(res.message, {icon: 2});
	            }
	        }
	    });
};