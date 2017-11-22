<html>
	<head>
		<script type="text/javascript" src="/vendor/jquery.min.js"></script>
	</head>
	<body>
        <form>
            <fieldset>
                <legend>融资快报</legend>
                <input type="button" value="获取融资企业列表" onclick="aa()"/>
                <input type="button" value="获取融资柱状图" onclick="dd()"/>
                <input type="button" value="获取融资动态数据" onclick="ee()"/>
                <input type="button" value="获取某产业融资企业推荐列表" onclick="bb()"/>
            </fieldset>
        </form>
        <form>
            <fieldset>
                <legend>园区分析</legend>
                <input type="button" value="企业融资分布" onclick="fenbu()"/>
                <input type="button" value="获取某轮次融资企业列表" onclick="cc()"/>
                <input type="button" value="获取价值榜分布图" onclick="jiazhi()"/>
                <input type="button" value="获取TOP企业列表" onclick="myTop()"/>
                <input type="button" value="关注园区-情报推送" onclick="push()"/>
            </fieldset>
        </form>
	</body>
	<script type="text/javascript">
	function aa(){
	    $.ajax({
            type: 'post',
            url: "/apis/financing/getCompanyList.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify({invest:"全部",sort:"按时间",area:"全部",industry:"全部"}),
            success: function (response) {
                if(response.message!=null){
                	alert(response.message);
                }else{
               		alert(response.data);
                }
            }
        });
	}
	function dd(){
         alert("没写");
	}
	function ee(){
         $.ajax({
            url: "/apis/financing/getFinancingDynamic.json",
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
		datalist.push("文化娱乐");         
		datalist.push("信息技术");         
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
	function fenbu(){
         $.ajax({
            url: "/apis/analysis/getFinancingDistribution.json?park=天津中新生态城",
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
	function jiazhi(){
          $.ajax({
            url: "/apis/analysis/getValueDistribution.json?park=天津中新生态城&type=年税收",
            success: function (response) {
                if(response.message!=null){
                	alert(response.message);
                }else{
               		alert(response.data);
                }
            }
        });
	}
	function myTop(){
         $.ajax({
            url: "/apis/analysis/getTopCompany.json?park=天津中新生态城&industry=人工智能",
            success: function (response) {
                if(response.message!=null){
                	alert(response.message);
                }else{
               		alert(response.data);
                }
            }
        });
	}
	function push(){
	    $.ajax({
	    	type: 'post',
            url: "/apis/area/getInformationPush.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify({park:'天津中新生态城',dimension:'园区动态'}),
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
</html>
