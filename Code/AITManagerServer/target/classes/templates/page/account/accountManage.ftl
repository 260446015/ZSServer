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
    <!-- Start: Content -->

<#include "/common/sidebar.ftl">
    <!-- 内容 Page -->

    <!-- End Page Header -->
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-tab layui-tab-brief">
                <ul class="layui-tab-title">
                    <li class="layui-this" onclick="myClick(0,0)">待审核会员</li>
                    <li onclick="myClick(0,1)">预到期会员</li>
                    <li onclick="myClick(0,2)">已到期会员</li>
                </ul>
            </div>
            <div class="layui-tab layui-tab-brief" id="tt">
                <ul class="layui-tab-title">
                    <li ><i class="layui-icon">&#xe637;</i>日期</li>
                    <li class="layui-this" onclick="myClick(1,'全部')">全部</li>
                    <li  onclick="myClick(1,'今日')">今日</li>
                    <li onclick="myClick(1,'昨日')" >昨日</li>
                    <li  onclick="myClick(1,'近一周')">近一周</li>
                </ul>
            </div>
            <div class="layui-tab layui-tab-brief">
                <ul class="layui-tab-title" type="hidden">
                    <li ><i class="layui-icon">&#xe756;</i>会员类型</li>
                    <li class="layui-this" onclick="myClick(2,1)" >正式会员</li>
                    <li  onclick="myClick(2,0)">试用会员</li>
                </ul>
            </div>
            <div class="layui-row">
                <div class="layui-col-md3">
                    <input type="text" name="title" placeholder="请输入园区名称/人物称呼"  class="layui-input">
                </div>
                <div class="layui-col-md4 ">
                    <button class="layui-btn layui-btn-normal layui-btn-radius" onclick="mySearch()">搜索</button>
                </div>
            </div>

            <table id="demo" lay-filter="filter"></table>
        </div>
        <form class="layui-form" type="hidden" action="">
        </form>
    </div>
	<div id="convert" style="display:none;width:300px;">  
	    <form class="layui-form" action="" >  
	        <div class="layui-form-item">  
	            <label class="layui-form-label">期限</label>  
	            <div class="layui-input-block">  
	                <div class="layui-inline">  
	                    <div class="layui-input-inline">  
	                        <select name="modules" id="modules" lay-verify="required" lay-filter="test" lay-search="">  
	                            <option value="1">1个月</option>  
	                            <option value="2">2个月</option>
	                            <option value="6">6个月</option>
	                            <option value="12">12个月</option>
	                        </select>  
	                    </div>  
	                </div>  
	            </div>  
	        </div>  
	    </form>  
	</div> 
<#include "/common/script.ftl">
    <script  type="text/html" id="appendix">
        <a class="layui-btn layui-btn-normal layui-btn-mini" lay-event="detail">查看详情</a>
    </script>
    <script  type="text/html" id="operation">
        <a class="layui-btn layui-btn-mini" lay-event="edit">开通试用</a>
    </script>
    <script  type="text/html" id="ration">
        <a class="layui-btn layui-btn-mini" lay-event="edit">开通正式账号</a>
    </script>
    <script  type="text/html" id="warning">
        <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="warn">预警提醒</a>
    </script>
    <script  type="text/html" id="delay">
        <a class="layui-btn layui-btn-warm layui-btn-mini" lay-event="delay">账号延期</a>
    </script>
    <script>
        var tab=0;
        var type=1;
        var day='全部';
        $(function(){
            myClick(0,0);
        })
        function mySearch(){
            var search=$('input[name=title]').val();
            var obj={type:type,
                day:day,
                search:search};
            if(tab==0){
                myRequest(obj);
            }else if(tab==1){
            	myRequest2(obj);
            }else{
                myRequest3(obj);
            }
        }
        function myClick(a,b){
            if(a==0){
                tab=b;
            }else if(a==1){
                day=b;
            }else{
                type=b;
            }
            var obj={type:type,
                day:day};
            if(tab==0){
           		$("#tt").show();
                myRequest(obj);
            }else if(tab==1){
            	$("#tt").show();
                myRequest2(obj);
            }else{
            	$("#tt").hide();
                myRequest3(obj);
            }
        }
        function myRequest(obj){
            $.ajax({
                type: 'post',
                url: "/apis/back/admin/getAccountList.json",
                async: false,
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function (response) {
                    layui.use('layer', function(){
                        var layer = layui.layer;
                        if(response.success){
                            if(obj.type==1){
                                var colList=[ //标题栏
                                    {field: 'id', title: 'ID', width: 70, sort: true}
                                	,{field: 'userAccount', title: '账号', width: 120}
                                    ,{field: 'userPark', title: '园区', width: 170}
                                    ,{field: 'telphone', title: '手机号', width: 140}
                                    ,{field: 'realName', title: '称呼', width: 100}
                                    ,{field: 'userJob', title: '职务', width: 100}
                                    ,{field: 'userEmail', title: '邮箱', width: 170}
                                    ,{field: 'appendix', title: '附录', width: 110 ,toolbar: '#appendix'}
                                    ,{field: 'createTime', title: '申请日期', width: 195}
                                    ,{fixed: 'right',  align:'center', title: '操作', width: 130 ,toolbar: '#ration'}
                                ]
                            }else{
                                var colList=[ //标题栏
                                    {field: 'id', title: 'ID', width: 70, sort: true}
                                	,{field: 'userAccount', title: '账号', width: 120}
                                    ,{field: 'userPark', title: '园区', width: 170}
                                    ,{field: 'telphone', title: '手机号', width: 140}
                                    ,{field: 'realName', title: '称呼', width: 100}
                                    ,{field: 'userJob', title: '职务', width: 100}
                                    ,{field: 'userEmail', title: '邮箱', width: 170}
                                    ,{field: 'appendix', title: '附录', width: 110 ,toolbar: '#appendix'}
                                    ,{field: 'createTime', title: '申请日期', width: 195}
                                    ,{fixed: 'right',  align:'center', title: '操作', width: 110 ,toolbar: '#operation'}
                                ]
                            }
                            showTable(response.data,colList);
                        }else{
                            if(response.message==null){
                                window.location.href="/login";
                            }else{
                                layer.alert(response.message);
                            }
                        }
                    });
                }
            });
        }
        function myRequest2(obj){
            $.ajax({
                type: 'post',
                url: "/apis/back/admin/getWarningAccountList.json",
                async: false,
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function (response) {
                    layui.use('layer', function(){
                        var layer = layui.layer;
                        if(response.success){
                            var colList=[ //标题栏
                                {field: 'id', title: 'ID', width: 70, sort: true}
                                ,{field: 'userAccount', title: '账号', width: 110}
                                ,{field: 'userPark', title: '园区', width: 150}
                                ,{field: 'telphone', title: '手机号', width: 120}
                                ,{field: 'realName', title: '称呼', width: 90}
                                ,{field: 'userJob', title: '职务', width: 90}
                                ,{field: 'userEmail', title: '邮箱', width: 160}
                                ,{field: 'appendix', title: '附录', width: 100 ,toolbar: '#appendix'}
                                ,{field: 'startTime', title: '开通日期', width: 175}
                                ,{field: 'expireTime', title: '到期日期', width: 175}
                                ,{fixed: 'right',  align:'center', title: '操作', width: 110 ,toolbar: '#warning'}
                            ]
                            showTable(response.data,colList);
                        }else{
                            if(response.message==null){
                                window.location.href="/login";
                            }else{
                                layer.alert(response.message);
                            }
                        }
                    });
                }
            });
        }
        function myRequest3(obj){
            $.ajax({
                type: 'post',
                url: "/apis/back/admin/getDelayAccountList.json",
                async: false,
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function (response) {
                    layui.use('layer', function(){
                        var layer = layui.layer;
                        if(response.success){
                            var colList=[ //标题栏
                                {field: 'id', title: 'ID', width: 60, sort: true}
                                ,{field: 'userAccount', title: '账号', width: 100}
                                ,{field: 'userPark', title: '园区', width: 150}
                                ,{field: 'telphone', title: '手机号', width: 120}
                                ,{field: 'realName', title: '称呼', width: 80}
                                ,{field: 'userJob', title: '职务', width: 80}
                                ,{field: 'userEmail', title: '邮箱', width: 160}
                                ,{field: 'appendix', title: '附录', width: 100 ,toolbar: '#appendix'}
                                ,{field: 'startTime', title: '开通日期', width: 175}
                                ,{field: 'expireTime', title: '到期日期', width: 175}
                                ,{fixed: 'right',  align:'center', title: '操作', width: 110 ,toolbar: '#delay'}
                            ]
                            showTable(response.data,colList);
                        }else{
                            if(response.message==null){
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
                    var data = obj.data;
                    if(obj.event === 'detail'){
                        layer.open({
                            type: 1,
                            title: false,
                            closeBtn: 0,
                            skin: 'layui-layer-nobg', //没有背景色
                            shadeClose: true,
                            content: '<img src='+data.imageUrl+'  alt="个人名片" />'
                        });
                    } else if(obj.event === 'warn'){
                        $.ajax({
                            url: "/apis/back/admin/warnAccount.json",
                            contentType: 'application/json',
                            data: {id: data.id},
                            success: function (response) {
                                layer.alert(response.message);
                            }
                        });
                    } else if(obj.event === 'delay'){
                        layer.open({   
				            type: 1 , 
				            area: ['340px', '200px'],
				            title:'账号延期' ,  
				            content:$('#convert') ,  
				            btn: ['延期', '取消'] ,  
				            btnAlign: 'c' ,  
				            shade: 0  ,  
				            yes: function(index){ 
				            	$.ajax({
		                            url: "/apis/back/admin/delayAccount.json",
		                            contentType: 'application/json',
		                            data: {id: data.id,month:$("#modules ").val()},
		                            success: function (response) {
		                            	if(response.success){
		                            		myClick(0,2);
		                            	}
		                                layer.alert(response.message);
		                            }
		                        });  
				            	layer.close(index);
				            }   
				        });  
                    } else if(obj.event === 'edit'){
                        layer.confirm('确定为该用户开通服务？', function(index){
                            $.ajax({
                                url: "/apis/back/admin/auditAccount.json",
                                contentType: 'application/json',
                                data: {id: data.id},
                                success: function (response) {
                                    layer.alert(response.message);
                                }
                            });
                            layer.close(index);
                        });
                    }
                });
                //展示已知数据
                table.render({
                    elem: '#demo'
                    ,data:dataList
                    ,height: 372
                    ,cols: [colList]
                    ,skin: 'row' //表格风格
                    ,even: true
                    ,page: true //是否显示分页
                    ,limits: [5, 7, 10]
                    ,limit: 7 //每页默认显示的数量
                });
            });
        }

    </script>
</body>

</html>