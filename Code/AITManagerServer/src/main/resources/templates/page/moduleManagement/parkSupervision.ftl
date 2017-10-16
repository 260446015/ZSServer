<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Basic -->
    <meta charset="UTF-8" />
    <title>慧数招商后台系统</title>
    <!-- Mobile Metas -->
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<#include "/common/link.ftl">
</head>
<body class="">
<div class="layui-layout layui-layout-admin">
<#include "/common/header.ftl">
<#include "/common/sidebar.ftl">
    <div class="layui-body">
        <div style="padding: 15px;">
        <div class="layui-tab layui-tab-brief">
                <ul class="layui-tab-title">
                    <li class="layui-nav-item"><a>筛选设置:</a></li>
                </ul>
            </div>
            <div class="layui-tab layui-tab-brief">
                <ul class="layui-tab-title">
                    <li ><i class="layui-icon">&#xe68e;</i>产业类型</li>
                    <li class="layui-this" onclick="myClick(1,'不限')">不限</li>
                    <li onclick="myClick(1,'互联网+')">互联网+</li>
                    <li onclick="myClick(1,'高科技')">高科技</li>
	                <li onclick="myClick(1,'文化创意')">文化创意</li>
	                <li onclick="myClick(1,'精英配套')">精英配套</li>
	                <li onclick="myClick(1,'港口物流')">港口物流</li>
	                <li onclick="myClick(1,'其他')">其他</li>
                </ul>
            </div>
            <div class="layui-tab layui-tab-brief">
                <ul class="layui-tab-title" type="hidden">
                    <li ><i class="layui-icon">&#xe715;</i>区域</li>
                    <li class="layui-this" onclick="myClick(2,'不限')">不限</li>
	                <li onclick="myClick(2,'北京')">北京</li>
	                <li onclick="myClick(2,'上海')">上海</li>
	                <li onclick="myClick(2,'广州')">广州</li>
	                <li onclick="myClick(2,'深圳')">深圳</li>
	                <li onclick="myClick(2,'杭州')">杭州</li>
	                <li onclick="myClick(2,'苏州')">苏州</li>
	                <li onclick="myClick(2,'南京')">南京</li>
	                <li onclick="myClick(2,'天津')">天津</li>
	                <li onclick="myClick(2,'青岛')">青岛</li>
	                <li onclick="myClick(2,'大连')">大连</li>
                </ul>
            </div>
        <div style="height: 15px;"></div>
         <div class="layui-row">
                <div class="layui-col-md3">
                    <input type="text" name="title" placeholder="请输入园区名称"  class="layui-input">
                </div>
                <div class="layui-col-md4 ">
                    <button class="layui-btn layui-btn-normal layui-btn-radius" onclick="mySearch()">搜索</button>
                </div>
            </div>
            <table id="demo" lay-filter="filter"></table></div>
        </div>
    </div>

</div>
<#include "/common/script.ftl">
<script  type="text/html" id="appendix">
    <a class="layui-btn layui-btn-normal layui-btn-mini" lay-event="detail">查看详情</a>
</script>
<script>
     var tab=0;
        var type='不限';
        var area='不限';
        $(function(){
            myClick(0,0);
        })
        function mySearch(){
            var search=$('input[name=title]').val();
            var obj={type:type,
                area:area,
                search:search};
            myRequest(obj);
        }
        function myClick(a,b){
        	if(a==1){
        		type=b;
        	}else if(a==2){
        		area=b;
        	}
            var obj={type:type,area:area};
            myRequest(obj);
        }
        function myRequest(obj){
            $.ajax({
                type: 'post',
                url: "/apis/area/findGardensList.json",
                async: false,
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function (response) {
                    layui.use('layer', function(){
                        var layer = layui.layer;
                        if(response.success){
                            var colList=[ //标题栏
                                {field: 'name', title: '园区名称', width: 170}
                                ,{field: 'area', title: '地域(城市)', width: 120}
                                ,{field: 'address', title: '具体地址', width: 120}
                                ,{field: 'industry', title: '园区产业', width: 170}
                                ,{field: 'boss', title: '负责人', width: 195}
                                ,{field: 'phone', title: '电话', width: 195}
                                ,{field: 'position', title: '职位', width: 195}
                                ,{fixed: 'right',  align:'center', title: '操作', width: 110 ,toolbar: '#appendix'}
                            ]
                            showTable(response.data,colList);
                        }else{
                            layer.alert(response.message);
                        }
                    });
                }
            });
        }
        function showTable(dataList,colList){
            layui.use('table', function(){
                var table = layui.table;
                //监听工具条
                table.on('tool(filter)', function(obj){
                	var data = obj.data;
                    window.location.href="/apis/area/parkDetail?park="+data.name;
                });
                //展示已知数据
                table.render({
                    elem: '#demo'
                    ,data:dataList
                    ,height: 272
                    ,cols: [colList]
                    ,skin: 'row' //表格风格
                    ,even: true
                    ,page: true //是否显示分页
                    ,limits: [5, 7, 10]
                    ,limit: 5 //每页默认显示的数量
                });
            });
        }

</script>
</body>
</html>