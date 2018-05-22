$('#industry_info').addClass("active");
var _id;
function addData(info){
	_id=info.id;
	  $("input[name='id']").val(info.id);
	  $("input[name='engageState']").val(info.engageState);
	  $("textarea[name='summary']").val(info.summary);
	  $("input[name='identity']").val(info.identity);
	  $("input[name='registerCapital']").val(info.registerCapital);
	  $("input[name='registerData']").val(info.registerData);
	  $("input[name='address']").val(info.address);
	  $("input[name='publishDate']").val(info.publishDate);
	  $("input[name='publishTime']").val(info.publishTime);
	  $("input[name='publishYear']").val(info.publishYear);
	  $("input[name='articleLink']").val(info.articleLink);
	  $("input[name='title']").val(info.title);
	  $("textarea[name='content']").val(info.content);
	  $("input[name='author']").val(info.author);
	  $("input[name='sourceLink']").val(info.sourceLink);
	  $("input[name='source']").val(info.source);
	  $("input[name='area']").val(info.area);
	  $("input[name='industry']").val(info.industry);
	  $("input[name='industryLabel']").val(info.industryLabel);
	  $("input[name='vector']").val(info.vector);
	  $("input[name='industryType']").val(info.industryType);
	  $("input[name='park']").val(info.park);
	  $("input[name='establishTime']").val(info.establishTime);
	  $("input[name='acreage']").val(info.acreage);
	  $("input[name='business']").val(info.business);
	  $("input[name='updateAttribute']").val(info.updateAttribute);
	  $("input[name='boss']").val(info.boss);
	  $("input[name='businessType']").val(info.businessType);
	  $("input[name='emotion']").val(info.emotion);
	  $("input[name='hitCount']").val(info.hitCount);
	  $("input[name='supportCount']").val(info.supportCount);
	  $("input[name='replyCount']").val(info.replyCount);
	  $("input[name='hasWarn']").val(info.hasWarn);
	  $("input[name='logo']").val(info.logo);
	  $("input[name='istop']").val(info.istop);
	  $("input[name='dimension']").val(info.dimension);
	 
};
$(".btn-success").on("click",function(){
    etitData();
});
$(".btn-danger").on("click",function(){
    if(_id==null){
        window.location.href = "/apis/industryinfo/industryInfoManage.html";
    }else {
        layer.confirm('直接离开将会失去修改内容，确认离开？', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            window.location.href = "/apis/industryinfo/industryInfoManage.html";
        });
    }
});
function etitData() {
	 var _id = $("input[name='id']").val();
	 var _engageState = $("input[name='engageState']").val();
	 var _summary = $("textarea[name='summary']").val();
	 var _identity = $("input[name='identity']").val();
	 var _registerCapital = $("input[name='registerCapital']").val();
	 var _registerData = $("input[name='registerData']").val();
	 var _address = $("input[name='address']").val();
	 var _publishDate = $("input[name='publishDate']").val();
	 var _publishTime = $("input[name='publishTime']").val();
	 var _publishYear = $("input[name='publishYear']").val();
	 var _articleLink = $("input[name='articleLink']").val();
	 var _title = $("input[name='title']").val();
	 var _content = $("textarea[name='content']").val();
	 var _author = $("input[name='author']").val();
	 var _sourceLink = $("input[name='sourceLink']").val();
	 var _source = $("input[name='source']").val();
	 var _area = $("input[name='area']").val();
	 var _industry = $("input[name='industry']").val();
	 var _industryLabel = $("input[name='industryLabel']").val();
	 var _vector = $("input[name='vector']").val();
	 var _industryType = $("input[name='industryType']").val();
	 var _park = $("input[name='park']").val();
	 var _establishTime = $("input[name='establishTime']").val();
	 var _acreage = $("input[name='acreage']").val();
	 var _business = $("input[name='business']").val();
	 var _updateAttribute = $("input[name='updateAttribute']").val();
	 var _boss = $("input[name='boss']").val();
	 var _businessType = $("input[name='businessType']").val();
	 var _emotion = $("input[name='emotion']").val();
	 var _hitCount = $("input[name='hitCount']").val();
	 var _supportCount = $("input[name='supportCount']").val();
	 var _replyCount = $("input[name='replyCount']").val();
	 var _hasWarn = $("input[name='hasWarn']").val();
	 var _logo = $("input[name='logo']").val();
	 var _istop = $("input[name='istop']").val();
	 var _istop = $("input[name='dimension']").val();
	 var _istop = $("input[name='bus']").val();
	var param ={
		id:_id,	engageState:_engageState,summary:_summary,identity:_identity,registerCapital:_registerCapital,
		registerData:_registerData,address:_address,publishDate:_publishDate,
		publishTime:_publishTime,publishYear:_publishYear,articleLink:_articleLink,
		title:_title,content:_content,author:_author,sourceLink:_sourceLink,source:_source,
		area:_area,industry:_industry,industryLabel:_industryLabel,vector:_vector,
		industryType:_industryType,park:_park,establishTime:_establishTime,acreage:_acreage,
		business:_business,updateAttribute:_updateAttribute,boss:_boss,businessType:_businessType,emotion:_emotion,
		hitCount:_hitCount,supportCount:_supportCount,replyCount:_replyCount,hasWarn:_hasWarn,
		logo:_logo,istop:_istop,dimension:_dimension,bus:_bus
	};
	 $.ajax({
	        type : "post",
	        contentType : "application/json",
	        async:false,
	        url : "/apis/industryinfo/saveIndustryinfo.json",
	        data : JSON.stringify(param),
	        success : function(res) {
	            if(res.success){
	                layer.msg('操作成功', {icon: 1});
	                window.location.href="/apis/industryinfo/industryInfoManage.html";
	            }else{
	                layer.msg(res.message, {icon: 2});
	            }
	        }
	    });
};