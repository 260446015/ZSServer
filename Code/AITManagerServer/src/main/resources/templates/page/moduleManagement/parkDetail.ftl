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
        <!-- 内容主体区域 -->
        <div class="layui-col-md12" style="margin:20px;">
            <span style="float:left;margin-left:10px;"><a class="layui-btn layui-btn-primary" href="/apis/area/parkSupervision">返回上一页</a></span>
        
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
		</fieldset>
        <!-- 选项卡 -->
        <div class="layui-tab layui-tab-brief">
            <ul class="layui-tab-title">
                <li class="layui-this" onclick="myClick(0,'企业动态')">企业动态</li>
                <li onclick="myClick(0,'疑似外流')">疑似外流</li>
            </ul>
        </div>
        <div class="layui-tab layui-tab-brief" id="tt">
            <ul class="layui-tab-title">
                <li ><i class="layui-icon">&#xe650;</i>情感</li>
                <li class="layui-this" onclick="myClick(1,'全部')">全部</li>
                <li onclick="myClick(1,'positive')">正面</li>
                <li onclick="myClick(1,'negative')" >负面</li>
                <li onclick="myClick(1,'neutral')" >中性</li>
            </ul>
        </div>
    <ul id="biuuu_city_list"></ul> 
    <div id="demo"></div>
    </div>
    </div>
    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
</body>
<#include "/common/script.ftl">
<script>
    var emotion="全部";
	var dimension="企业动态";
	$(function(){
        myClick(0,"企业动态");
    })
    function myClick(a,b){
	    if(a==0){
	    	dimension=b;
	    }else if(a==1){
	    	emotion=b
	    }
    	var obj={emotion:emotion,dimension:dimension,park:'${Request.park}'};
		$.ajax({
                type: 'post',
                url: "/apis/area/findDynamicList.json",
                async: false,
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function (response) {
	                if(response.data.totalSize=='0'){
	                	document.getElementById('biuuu_city_list').innerHTML =
	                	"<div style='margin-top:10%;margin-left:40%;'><i class='layui-icon' style='font-size: 50px; color: #1E9FFF;'>&#xe69c;</i>"+
	                	"</br>暂无数据</div>";
	                	document.getElementById('demo').innerHTML="";
	                }else{
	                	showTable(response.data);
                	}
                }
            });
	}
  	function showTable(data){
  		layui.use(['laypage', 'layer'], function(){
			var laypage = layui.laypage
			,layer = layui.layer;
			//调用分页
			laypage.render({
				elem: 'demo'
			    ,count: data.totalSize
			    ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
			    ,jump: function(obj, first){
			    	if(first){
			    		show(data.list);
			    	}
				    //首次不执行
				    if(!first){
				    	var obj={dimension:dimension,
				    			emotion:emotion,
				    			park:'${Request.park}',
				    			pageNumber:obj.curr,
				    			pageSize:obj.limit};
				     	$.ajax({
			                type: 'post',
			                url: "/apis/area/findDynamicList.json",
			                async: false,
			                contentType: 'application/json',
			                data: JSON.stringify(obj),
			                success: function (response) {
			                	document.getElementById('biuuu_city_list').innerHTML ="";
				                show(response.data.list);
			                }
			            });
				    }
				}
			});
		});
		function show(d){
			document.getElementById('biuuu_city_list').innerHTML = function(){
				var before='<table class="layui-table" lay-even="" lay-skin="nob">'+
					 	'<colgroup><col width="90"><col width="200"><col width="450"><col width="200"><col width="220"><col></colgroup>'+
					 	'<thead><tr><th>作者</th><th>标题</th><th>详情</th><th>时间</th><th>来源</th><th>操作</th></tr></thead><tbody>';
		        var arr = []
		        layui.each(d, function(index, item){
		          arr.push('<tr><td>'+item.author+'</td><td>'+item.title+'</td><td>'+item.summary+
		          			'</td><td>'+item.publishTime+'</td><td>'+item.source+
		          			'</td><td id="appendix"><a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="detail" onclick="myDrop(\''+item.id+'\')">删除</a>'+
		          			'<a class="layui-btn layui-btn-mini" lay-event="detail" onclick="myTop(\''+item.id+'\')">置顶</a></td></tr>');
		        });
		        var inner=arr.join('');
		        var after='</tbody></table> ';
		        return before+inner+after;
			}();
		}
  	}
  	function myTop(id){
  		alert('置顶是干嘛')
  	}
  	function myDrop(id){
  		layer.confirm('确定删除该文章？', function(index){
            $.ajax({
                url: "/apis/area/dropEssay.json",
                contentType: 'application/json',
                data: {id: id},
                success: function (response) {
                    layer.alert(response.message);
                    myClick(2,0)
                }
            });
            layer.close(index);
        });
  	}
</script>
</html>