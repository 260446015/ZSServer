<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <#include "/common/link.ftl">
    <script type="text/javascript" src="/vendor/jquery.min.js"></script>
    <script type="text/javascript" src="/js/page.js"></script>
</head>
<body>
 <ul id="demoContent"></ul>
 <ul class="page" id="page"></ul>
</body>
</html>
<script type="text/javascript">
   
var datas=[{"id":4,"patientName":"zhangsan","officeName":"检查室1","departmentName":"检查室"},
{"id":4,"patientName":"zhangsan","officeName":"检查室1","departmentName":"检查室"},
{"id":4,"patientName":"zhangsan","officeName":"检查室1","departmentName":"检查室"},
{"id":4,"patientName":"zhangsan","officeName":"检查室1","departmentName":"检查室"}];
var options={
	"id":"page",//显示页码的元素
	"data":datas,//显示数据
    "maxshowpageitem":5,//最多显示的页码个数
    "pagelistcount":4,//每页显示数据个数
    "callBack":function(result){
    	     var cHtml="";
        for(var i=0;i<result.length;i++){
            cHtml+=result[i].patientName+"<br/>";//处理数据
			
        }
        $("#demoContent").html(cHtml);//将数据增加到页面中
    }
};
   page.init(35,4,options);
</script>