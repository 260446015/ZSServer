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
        <div class="layui-nav layui-bg-cyan">
            <ul class="" lay-filter="">
                <li class="layui-nav-item"><a>筛选设置:</a></li>
            </ul>
            <ul class="" lay-filter="">
                <li class="layui-nav-item"><a>产业类型：</a></li>
                <li class="layui-nav-item layui-this"><a href="">不限</a></li>
                <li class="layui-nav-item"><a href="">节能环保</a></li>
                <li class="layui-nav-item"><a href="">动漫产业</a></li>
                <li class="layui-nav-item"><a href="">新能源</a></li>
                <li class="layui-nav-item"><a href="">信息技术</a></li>
                <li class="layui-nav-item"><a href="">生态科技</a></li>
                <li class="layui-nav-item"><a href="">新一代信息技术</a></li>
                <li class="layui-nav-item"><a href="">高端设备制造</a></li>
                <li class="layui-nav-item"><a href="">影视产业</a></li>
            </ul>
            <ul class="" lay-filter="">
                <li class="layui-nav-item"><a>区域</a></li>
                <li class="layui-nav-item layui-this"><a href="">不限</a></li>
                <li class="layui-nav-item"><a href="">北京</a></li>
                <li class="layui-nav-item"><a href="">上海</a></li>
                <li class="layui-nav-item"><a href="">天津</a></li>
                <li class="layui-nav-item"><a href="">深圳</a></li>
                <li class="layui-nav-item"><a href="">广州</a></li>
                <li class="layui-nav-item"><a href="">贵阳</a></li>
                <li class="layui-nav-item"><a href="">浙江</a></li>
                <li class="layui-nav-item"><a href="">河北</a></li>
                <li class="layui-nav-item"><a href="">江苏</a></li>
            </ul>
        </div>
        <div style="height: 15px;"></div>
        <div class="paddingX20">
         <div class="layui-row">
                <div class="layui-col-md3">
                    <input type="text" name="title" placeholder="请输入园区名称/人物称呼"  class="layui-input">
                </div>
                <div class="layui-col-md4 ">
                    <button class="layui-btn layui-btn-normal layui-btn-radius" onclick="mySearch()">搜索</button>
                </div>
            </div>
            <table id="demo" lay-filter="filter"></table></div>
    </div>

</div>
<#include "/common/script.ftl">
<script  type="text/html" id="appendix">
    <a class="layui-btn layui-btn-mini" lay-event="detail">查看详情</a>
</script>
<script>
     var tab=0;
        var type='不限';
        var day='不限';
        $(function(){
            myClick('不限','不限');
        })
        function mySearch(){
            var search=$('input[name=title]').val();
            var obj={type:type,
                day:day,
                search:search};
            myRequest(obj);
        }
        function myClick(a,b){
            if(a==0){
                tab=b;
            }else if(a==1){
                day=b;
            }
            var obj={type:type,area:day};
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
                                {field: 'gardenName', title: '园区名称', width: 170}
                                ,{field: 'gardenLevel', title: '园区级别', width: 150}
                                ,{field: 'area', title: '地域(城市)', width: 120}
                                ,{field: 'address', title: '具体地址', width: 120}
                                ,{field: 'industry', title: '园区产业', width: 170}
                                ,{field: 'establishDate', title: '成立时间', width: 195}
                                ,{field: 'gardenSquare', title: '园区面积', width: 195}
                                ,{fixed: 'right',  align:'center', title: '操作', width: 110 ,toolbar: '#appendix'}
                            ]
                            showTable(response.data,colList);
                        }else{
                            if(response.code!=null){
                                window.location.href="/login";
                            }else{
                                layer.alert(response.message);
                            }
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
                    window.location.href="/apis/area/parkDetail";
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