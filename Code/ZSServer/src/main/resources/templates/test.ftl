<html>
	<head>
		<script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
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
</script> 