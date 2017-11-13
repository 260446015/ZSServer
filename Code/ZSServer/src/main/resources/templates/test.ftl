<html>
	<head>
		<script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
	</head>
	<body>
        <form>
            <fieldset>
                <legend>融资快报</legend>
                <input type="button" value="获取融资企业列表" onclick="aa()"/>
                <input type="button" value="获取某产业融资企业推荐列表" onclick="bb()"/>
            </fieldset>
        </form>
        <form>
            <fieldset>
                <legend>园区分析</legend>
                <input type="button" value="获取某轮次融资企业列表" onclick="cc()"/>
            </fieldset>
        </form>
        e:/file/pdf/c6090b50-0344-407d-8d0a-db063ecec69a.pdf
        <form>
            <fieldset>
                <legend>上传PDF</legend>
                 <lable>名片:</lable>
                <input type="file" id="file" name="file" onchange="pushImg();"/> 
                <input type="hidden" id="pic" name="pic" /></div>
                <div>
		            <lable>在线预览:</lable>
		            <input type="text" name="but"/></div>
		            <input type="button" value="发送" onclick="yulan()"/>
		            <input type="button" value="发送2" onclick="yulan2()"/>
            </fieldset>
        </form>
	</body>
</html>
<script type="text/javascript">
	function aa(){
		var datalist= new Array();
		datalist.push("人工智能");         
		datalist.push("天津");         
		datalist.push("种子轮");         
		datalist.push("按时间");         
	    $.ajax({
            type: 'post',
            url: "/apis/financing/getCompanyList.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify({park:'天津中新生态城',msg: datalist}),
            success: function (response) {
                if(response.message!=null){
                	alert(response.message);
                }else{
               		alert(response.data);
                }
            }
        });
	}
	function bb(){
		var datalist= new Array();
		datalist.push("人工智能");         
		datalist.push("物联网");         
	    $.ajax({
            type: 'post',
            url: "/apis/financing/getFinancingCompany.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify(datalist),
            success: function (response) {
                if(response.message!=null){
                	alert(response.message);
                }else{
               		alert(response.data);
                }
            }
        });
	}
	function cc(){
	    $.ajax({
	    	type: 'post',
            url: "/apis/analysis/getCompanyList.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify({park:'天津中新生态城',invest:'种子轮'}),
            success: function (response) {
                if(response.message!=null){
                	alert(response.message);
                }else{
               		alert(response.data);
                }
            }
        });
	}
	function yulan() {
		$.ajax({
            url: "/pdf/changePDFUrl.do?path=e:/file/pdf/c6090b50-0344-407d-8d0a-db063ecec69a.pdf",
            success: function (response) {
            	window.location.href="/pdf/web/viewer.html?file="+"/pdf/previewPDF.do";
            }
        });
    }
	function pushImg(){
	    var picpath="";
	    $.ajaxFileUpload({
	    　　　　url : "/pdf/uploadPDF.do",
           fileElementId:'file',
           dataType : "json",
           success: function(response){
          	 alert(response.message);
           },
           error: function(response)
           {
              alert(response.message);
            }
           }
		);
	}
</script> 