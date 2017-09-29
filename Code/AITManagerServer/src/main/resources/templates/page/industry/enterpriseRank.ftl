<!DOCTYPE html>
<html lang="en">
<head>
<!-- Basic -->
<meta charset="UTF-8" />
<title>慧数招商后台系统</title>
<!-- Mobile Metas -->
<!--企业排行 -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<#include "/common/link.ftl">
</head>
<body class="">
<div class="layui-layout layui-layout-admin">
	<#include "/common/header.ftl">
	<!-- Start: Content -->
	<!--企业排行-->
	<#include "/common/sidebar.ftl">
	<!-- 内容 Page -->
	<div class="layui-body">
		<!-- 内容主体区域 -->
		 <div class="layui-nav layui-bg-cyan">
				            
				            <ul class="" lay-filter="">
					            <li class="layui-nav-item"><a href="">产业分类：</a></li>
					            <li class="layui-nav-item layui-this"><a href="">互联网</a></li>
					            <li class="layui-nav-item"><a href="">生态科技</a></li>
					            <li class="layui-nav-item"><a href="">环保产业</a></li>
					            <li class="layui-nav-item"><a href="">物流产业</a></li>
					            <li class="layui-nav-item"><a href="">制造产业</a></li>
					            <li class="layui-nav-item"><a href="">医药化学</a></li>
					            <li class="layui-nav-item"><a href="">新能源</a></li>
				            </ul>
				            <ul class="" lay-filter="">
					            <li class="layui-nav-item"><a href="">产业子类：</a></li>
					            <li class="layui-nav-item layui-this"><a href="">不限</a></li>
					            <li class="layui-nav-item"><a href="">电子竞技</a></li>
					            <li class="layui-nav-item"><a href="">大数据</a></li>
					            <li class="layui-nav-item"><a href="">电子商务</a></li>
					            <li class="layui-nav-item"><a href="">网络视听</a></li>
					            <li class="layui-nav-item"><a href="">移动阅读</a></li>
					            <li class="layui-nav-item"><a href="">智能硬件</a></li>
				            </ul>
				            <ul class="" lay-filter="">
					            <li class="layui-nav-item"><a href="">时排序间：</a></li>
					            <li class="layui-nav-item layui-this"><a href="">全部</a></li>
					            <li class="layui-nav-item"><a href="">今日</a></li>
					            <li class="layui-nav-item"><a href="">昨日</a></li>
					            <li class="layui-nav-item"><a href="">近3天</a></li>
				            </ul>
			            </div>
			            <div class="layui-col-md12 paddingX20">
			            <form class="layui-form" action="">
			                <div class="layui-form-item" style="margin-top:20px;width:400px;float:right;">
                                <label class="layui-form-label">地域:</label>
                                <div class="layui-input-block">
                                    <select name="city" lay-verify="required" style="width:100px;size:20">
                                        <option value=""></option>
                                        <option value="0">北京</option>
                                        <option value="1">上海</option>
                                        <option value="2">广州</option>
                                        <option value="3">深圳</option>
                                        <option value="4">杭州</option>
                                    </select>
                                </div>
                            </div>
                        </form>
			            <table class="layui-table" lay-size="lg">
			            	<colgroup>
			            		<col width="100">
			            		<col width="200">
			            		<col width="500">
			            		<col width="150">
			            		<col width="150">
			            		<col width="100">
			            	</colgroup>
			            	<thead>
			            		<tr>
			            			<th>编号</th>
			            			<th>标题</th>
			            			<th>详情</th>
			            			<th>时间</th>
			            			<th>来源</th>
			            			<th>操作</th>
			            		</tr>
			            	</thead>
			            	<tbody>
			            		<tr>
			            			<td>1</td>
			            			<td><a href="招商情报管理内容详情.html">中欧基金：后3300点时代 股指“持续上攻”尚需观察</a></td>
			            			<td><a href="招商情报管理内容详情.html">中欧基金：后3300点时代 股指“持续上攻”尚需观察 上周，上证综指上涨1.92%，上证50上涨3.56%，创业板指微跌0.49%，银行、非银金融、家用电器、有色金属、采掘等行业涨幅居前，投资者情绪有所回暖。中欧基金认为，股指会否突破3300点且形成上升趋势尚需观察；目前来看个股向</a></td>
			            			<td>2017-08-29</td>
			            			<td>21世纪经济报道</td>
			            			<td><div style="width:80px;height:40px"><button class="layui-btn layui-btn-mini" lay-event="detail" onclick="onDel()">删除</button><button class="layui-btn layui-btn-mini" lay-event="detail" onclick="toTop()">置顶</button></div></td>
			            		</tr>
			            		<tr>
			            			<td>2</td>
			            			<td><a href="招商情报管理内容详情.html">基金等机构持仓底牌揭晓：茅台招行平安是"吉祥三宝"</a></td>
			            			<td><a href="招商情报管理内容详情.html">报记者 王丹 实习生 黄佶滢 上海报道 又到了半年度的盘点季。截至8月30日，半年报披露接近尾声，公募基金半年报于前一日披露完毕，随着各项统计数据的出炉，包括公募基金在内，几大类重要机构上半年的投资路线清晰浮水。 据同花顺iFinD</a></td>
			            			<td>2017-08-31</td>
			            			<td>21世纪经济报道</td>
			            			<td><div style="width:80px;height:40px"><button class="layui-btn layui-btn-mini" lay-event="detail" onclick="onDel()">删除</button><button class="layui-btn layui-btn-mini" lay-event="detail" onclick="toTop()">置顶</button></div></td>
			            		</tr>
			            		<tr>
			            			<td>3</td>
			            			<td><a href="招商情报管理内容详情.html">中欧基金：后3300点时代 股指“持续上攻”尚需观察</a></td>
			            			<td><a href="招商情报管理内容详情.html">中欧基金：后3300点时代 股指“持续上攻”尚需观察 上周，上证综指上涨1.92%，上证50上涨3.56%，创业板指微跌0.49%，银行、非银金融、家用电器、有色金属、采掘等行业涨幅居前，投资者情绪有所回暖。中欧基金认为，股指会否突破3300点且形成上升趋势尚需观察；目前来看个股向</a></td>
			            			<td>2017-08-29</td>
			            			<td>证券时报</td>
			            			<td><div style="width:80px;height:40px"><button class="layui-btn layui-btn-mini" lay-event="detail" onclick="onDel()">删除</button><button class="layui-btn layui-btn-mini" lay-event="detail" onclick="toTop()">置顶</button></div></td>
			            		</tr>
			            		<tr>
			            			<td>4</td>
			            			<td><a href="招商情报管理内容详情.html">基金等机构持仓底牌揭晓：茅台招行平安是"吉祥三宝"</a></td>
			            			<td><a href="招商情报管理内容详情.html">报记者 王丹 实习生 黄佶滢 上海报道 又到了半年度的盘点季。截至8月30日，半年报披露接近尾声，公募基金半年报于前一日披露完毕，随着各项统计数据的出炉，包括公募基金在内，几大类重要机构上半年的投资路线清晰浮水。 据同花顺iFinD</a></td>
			            			<td>2017-08-31</td>
			            			<td>21世纪经济报道</td>
			            			<td><div style="width:80px;height:40px"><button class="layui-btn layui-btn-mini" lay-event="detail" onclick="onDel()">删除</button><button class="layui-btn layui-btn-mini" lay-event="detail" onclick="toTop()">置顶</button></div></td>
			            		</tr>
			            	</tbody>
			            </table>
			        </div>	
			
	</div>
	</div>
	<#include "/common/script.ftl">
	<script>
	function onDel(){
		layer.open({
           content: '确认删除该片数据'
           ,btn: ['确认', '取消']
           ,yes: function(index, layero){
           //按钮【按钮一】的回调
          }
          ,btn2: function(index, layero){
           //按钮【按钮二】的回调
    
           //return false 开启该代码可禁止点击该按钮关闭
          }
          ,cancel: function(){ 
          //右上角关闭回调
    
          //return false 开启该代码可禁止点击该按钮关闭
           }
        });
	}
	function toTop(){
		layer.open({
           content: '确认置顶该片数据'
           ,btn: ['确认', '取消']
           ,yes: function(index, layero){
           //按钮【按钮一】的回调
          }
          ,btn2: function(index, layero){
           //按钮【按钮二】的回调
    
           //return false 开启该代码可禁止点击该按钮关闭
          }
          ,cancel: function(){ 
          //右上角关闭回调
    
          //return false 开启该代码可禁止点击该按钮关闭
           }
        });
	}
	</script>
</body>

</html>	