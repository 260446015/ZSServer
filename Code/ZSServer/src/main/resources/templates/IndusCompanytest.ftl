<html>
	<head>
		<script type="text/javascript" src="/js/jquery-2.1.1.min.js"></script>
	</head>
	<body>
       <div  width=15px>
          <p>智能企业列表</p>
      		<table border="1"> 
      		<tr>
      		<th>id</th>
      		<th>简称</th>
      		<th>全称</th>
      		<th>行业</th>
      		<th>操作</th>
      		</tr>
      		<#list info as param>
     		<tr>
      				<td>${param.id}</td>
      				<td>${param.companyName}</td>
      				<td>${param.company}</td>
      				<td>${param.industry}</td>
      				<td><input type="button" name="companyName" value="${param.companyName}" onclick="test(this.value)"/></td>
      			</tr>
      		</#list>
      		</table>
       </div>
      <div>
          <p>产业地图测试</p>
	   <div>
	   	 <legend>产业峰会</legend>	
	   	 <div >
	   	 	<table border="1" id="table"> 
			</table>      		
	   	 </div>
	   </div>
	 </div>	
	</body>
</html>
<script type="text/javascript">
$(function($) {
  // 你可以在这里继续使用$作为别名...
	aa();
	

});
	function test(val){
		alert(val);
		var s = val;
		$.ajax({
			type: 'GET',
			url:"/getcompany/findCompanyInfoByName.json",
			async: false,
            contentType: 'application/json',
			data:{companyName:s},
			success :function(result){
				alert(result.data.industry+result.data.legalPersonName+result.data.name);
				}
			});
	};
	function aa(){
		var s = "大数据";
		$.ajax({
			type: 'GET',
			url:"/summit/getIndustrySummit.json",
			async: false,
            contentType: 'application/json',
			data:{industryLabel:s},
			success :function(result){
			var arr = result.data;
			var ss = "";
			ss += "<tr>"+"<th>id</th>"+"<th>发布时间</th>"+"<th>标题</th>"+"</tr>";
			for(var i=0;i<arr.length;i++ ){
				ss += "<tr>"+
				"<td>"+arr[i].id+"</td><td>"
				+arr[i].publishTime+"</td><td>"+arr[i].title +"</td>";
			}
				$("#table").html(ss);
			} 
		
		});
	
	}
</script> 