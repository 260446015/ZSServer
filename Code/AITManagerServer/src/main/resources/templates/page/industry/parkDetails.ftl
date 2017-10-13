<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>慧数招商后台系统</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<#include "/common/link.ftl">
</head>

<body class="">
<div class="layui-layout layui-layout-admin">
<#include "/common/header.ftl">
<#include "/common/sidebar.ftl">
    <div class="layui-body">
        <div class="layui-col-md12" style="margin:20px;">
            <span style="float:left;margin-left:10px;"><a class="layui-btn layui-btn-primary" href="/apis/parkInfo/parkInformation">返回上一页</a></span>
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
            </fieldset>
	        <div class="layui-row layui-col-space30 ">
	            <div class="layui-col-xs8">
	                <a  data-toggle="modal" data-target="#myModal">编辑</a>
	                <table class="info-table layui-table">
	                    <tr>
	                        <td>园区名称</td>
	                        <td>${Request.garden.gardenName}</td>
	                    </tr>
	                    <tr>
	                        <td>园区级别</td>
	                        <td>${Request.garden.gardenLevel}</td>
	                    </tr>
	                    <tr>
	                        <td>地域（城市）</td>
	                        <td>${Request.garden.area}</td>
	                    </tr>
	                    <tr>
	                        <td>具体地址</td>
	                        <td>${Request.garden.address}</td>
	                    </tr>
	                    <tr>
	                        <td>园区产业</td>
	                        <td>${Request.garden.industry}</td>
	                    </tr>
	                    <tr>
	                        <td>成立时间</td>
	                        <td>${Request.garden.establishDate}</td>
	                    </tr>
	                    <tr>
	                        <td>园区面积</td>
	                        <td>${Request.garden.gardenSquare}</td>
	                    </tr>
	                </table>
	            </div>
	            <div class="layui-col-xs4 upLoad">
	                <div class="layui-upload">
	                    <div class="layui-upload-list">
	                        <img class="layui-upload-img" id="demo1" src="${Request.garden.gardenPicture}" alt="logo" width="200px" height="100px">
	                        <p id="demoText"></p>
	                    </div>
	                    <button type="button" class="layui-btn" id="test1">上传LOGO</button>
	                </div>
	            </div>
	        </div>
	        <div class="layui-tab layui-tab-brief">
	            <ul class="layui-tab-title">
	                <li class="layui-this" onclick="myClick('企业库')">入驻企业</li>
	                <li class="" onclick="myClick('园区政策')">园区政策</li>
	                <li class="" onclick="myClick('园区情报')">园区情报</li>
	            </ul>
	        </div>
		    <ul id="biuuu_city_list"></ul> 
		    <div id="demo"></div>
	    </div>
    </div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            <h4 class="modal-title" id="myModalLabel"> 修改园区信息</h4>
                </div>
                <div class="modal-body">
                   <form class="layui-form" action="" id="myForm">
                       <div class="layui-form-item">
                            <label class="layui-form-label">园区名称</label>
                            <div class="layui-input-block">
                                <input type="text" name="title" required  lay-verify="required" placeholder="请输入园区名称" value="${Request.garden.gardenName}" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">园区级别</label>
                            <div class="layui-input-block">
                                <select name="city" lay-verify="required">
                                    <option value="国家级">国家级</option>
                                    <option value="省级">省级</option>
                                    <option value="地市级">地市级</option>
                                    <option value="县级">县级</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">地域（城市）</label>
                            <div class="layui-input-block">
                                <input type="text" name="title" required  lay-verify="required" placeholder="请输入地域" value="${Request.garden.area}" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">具体地址</label>
                            <div class="layui-input-block">
                                <input type="text" name="title" required  lay-verify="required" placeholder="请输入具体地址" value="${Request.garden.address}" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">园区产业</label>
                            <div class="layui-input-block">
                                <input type="text" name="title" required  lay-verify="required" placeholder="请输入园区产业" value="${Request.garden.industry}" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">成立时间</label>
                            <div class="layui-input-block">
                                <input type="text" name="title" required  lay-verify="required" placeholder="请输入成立时间" value="${Request.garden.establishDate}" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">园区面积</label>
                            <div class="layui-input-block">
                                <input type="text" name="title" required  lay-verify="required" placeholder="请输入园区面积" value="${Request.garden.gardenSquare}" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                   </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary">提交更改</button>
                </div>
            </div>
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
	var dimension="企业库";
	$(function(){
        myClick("企业库");
    })
    function myClick(a){
	    dimension=a;
    	var obj={dimension:dimension,park:'${Request.garden.gardenName}'};
		$.ajax({
                type: 'post',
                url: "/apis/parkInfo/findInformationList.json",
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
			    		if(dimension=="企业库"){
	                		show2(data.list);
	                	}else{
	                		show(data.list);
	                	}
			    	}
				    //首次不执行
				    if(!first){
				    	var obj={dimension:dimension,
				    			park:'${Request.garden.gardenName}',
				    			pageNumber:obj.curr,
				    			pageSize:obj.limit};
				     	$.ajax({
			                type: 'post',
			                url: "/apis/parkInfo/findInformationList.json",
			                async: false,
			                contentType: 'application/json',
			                data: JSON.stringify(obj),
			                success: function (response) {
			                	document.getElementById('biuuu_city_list').innerHTML ="";
			                	if(dimension=="企业库"){
			                		show2(response.data.list);
			                	}else{
			                		show(response.data.list);
			                	}
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
		function show2(d){
			document.getElementById('biuuu_city_list').innerHTML = function(){
				var before='<table class="layui-table" lay-even="" lay-skin="nob">'+
					 	'<colgroup><col width="90"><col width="300"><col width="700"><col></colgroup>'+
					 	'<thead><tr><th>编号</th><th>公司名</th><th>详细地址</th><th>操作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
					 	'<a class="layui-btn layui-btn-normal layui-btn-mini" lay-event="detail" onclick="myAdd()">添加企业</a>'+
					 	'</th></tr></thead><tbody>';
		        var arr = []
		        layui.each(d, function(index, item){
		          arr.push('<tr><td>'+item.cid+'</td><td>'+item.companyName+'</td><td>'+item.address+
		          			'</td><td id="appendix"><a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="detail" onclick="myDrop2(\''+item.cid+'\')">删除</a>');
		        });
		        var inner=arr.join('');
		        var after='</tbody></table> ';
		        return before+inner+after;
			}();
		}
  	}
  	function myDrop(id){
        layer.open({
            type: 1,
            offset: 'auto',
            title:false,
            id: 'layerDemo' , //防止重复弹出
            content: '<div style="padding: 20px 100px;">确认删除该文章数据吗</div>',
            btn: ["确定","取消"],
            btnAlign: 'c', //按钮居中
            shade: 0.8, //不显示遮罩
            yes: function() {
                $.ajax({
	                url: "/apis/area/dropEssay.json",
	                async: false,
	                contentType: 'application/json',
	                data: {id:id},
	                success: function (response) {
	                	layer.closeAll();
	                	layer.msg(response.message);
                    	myClick(dimension)
	                }
	            });
            },
            btn2: function(){
                layer.closeAll();
            }
        });
    }
    function myDrop2(id){
        layer.open({
            type: 1,
            offset: 'auto',
            title:false,
            id: 'layerDemo' , //防止重复弹出
            content: '<div style="padding: 20px 100px;">确认删除该园区企业吗</div>',
            btn: ["确定","取消"],
            btnAlign: 'c', //按钮居中
            shade: 0.8, //不显示遮罩
            yes: function() {
                $.ajax({
	                url: "/apis/parkInfo/dropCompany.json",
	                async: false,
	                contentType: 'application/json',
	                data: {id:id},
	                success: function (response) {
	                	layer.closeAll();
	                	layer.msg(response.message);
                    	myClick(dimension)
	                }
	            });
            },
            btn2: function(){
                layer.closeAll();
            }
        });
    }
    function myTop(){
        layer.open({
            type: 1,
            offset: 'auto',
            title:false,
            id: 'layerDemo' , //防止重复弹出
            content: '<div style="padding: 20px 100px;">确认置顶该数据？</div>',
            btn: ["确定","取消"],
            btnAlign: 'c', //按钮居中
            shade: 0.8, //不显示遮罩
            yes: function() {
            	layer.closeAll();
                layer.msg("置顶还没写");
            },
            btn2: function(){
                layer.closeAll();
            }
        });
    }
    layui.use(['laypage', 'layer','upload'], function(){
        var layer = layui.layer,
                laypage = layui.laypage,
                $ = layui.jquery,
                upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            ,url: '/apis/imageUpload.do?id=${Request.garden.id}'
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                if(res.success){
                     return layer.msg('上传成功');
                }
                return layer.msg('上传失败');
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });
    });

</script>
</html>