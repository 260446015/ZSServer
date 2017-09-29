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
        <div class="layui-col-md12" style="margin-top:20px;">
            <span style="float:left;margin-left:20px;"><a href="/apis/area/parkSupervision">返回</a></span>
        </div>
        <div class="btns marginL20">
            <a class="layui-btn layui-btn-normal">企业动态</a>
            <a class="layui-btn layui-btn-normal">疑似外流</a>
        </div>
        <!-- 选项卡 -->
        <div class="chotable">
            <div style=" height:420px;display:block;float:left;" class="layui-col-md12 paddingX20">
                <table class="layui-table">
                    <colgroup>
                        <col width="50">
                        <col width="200">
                        <col width="200">
                        <col width="50">
                        <col width="50">
                        <col width="50">
                    </colgroup>
                    <thead>
                        <tr>
                            <td>作者</td>
                            <td>标题</td>
                            <td>详情</td>
                            <td>时间</td>
                            <td>来源</td>
                            <td>操作</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>1中欧基金：后3300点时代 股指“持续上攻”尚需观察</td>
                            <td>中欧基金：后3300点时代 股指“持续上攻”尚需观察 上周，上证综指上涨1.92%，上证50上涨3.56%，创业板指微跌0.49%，银行、非银金融、家用电器、有色金属、采掘等行业涨幅居前，投资者情绪有所回暖。中欧基金认为，股指会否突破3300点且形成上升趋势尚需观察；目前来看个股向</td>
                            <td>2017-08-29</td>
                            <td>21世纪经济报道</td>
                            <td style="text-align:center;"><button class="layui-btn layui-btn-mini" lay-event="detail" onclick="onDel()">删除</button><button class="layui-btn layui-btn-mini" lay-event="detail" onclick="toTop()">置顶</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div style=" height:420px;display:none;float:left;"class="layui-col-md12 paddingX20">
                <table class="layui-table">
                    <colgroup>
                        <col width="50">
                        <col width="200">
                        <col width="200">
                        <col width="50">
                        <col width="50">
                        <col width="50">
                    </colgroup>
                    <thead>
                        <tr>
                            <th>作者</th>
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
                            <td>中欧基金：后3300点时代 股指“持续上攻”尚需观察</td>
                            <td>中欧基金：后3300点时代 股指“持续上攻”尚需观察 上周，上证综指上涨1.92%，上证50上涨3.56%，创业板指微跌0.49%，银行、非银金融、家用电器、有色金属、采掘等行业涨幅居前，投资者情绪有所回暖。中欧基金认为，股指会否突破3300点且形成上升趋势尚需观察；目前来看个股向</td>
                            <td>2017-08-29</td>
                            <td>21世纪经济报道</td>
                            <td style="text-align:center;"><button class="layui-btn layui-btn-mini" lay-event="detail" onclick="onDel()">删除</button><button class="layui-btn layui-btn-mini" lay-event="detail" onclick="toTop()">置顶</button></td>
                        </tr>
                    </tbody>
                </table>
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
var choB=$(".btns a")
    choB.click(function(){
    $(this).addClass('selected').siblings().removeClass('selected');
    var index=choB.index(this);
    $(".chotable>div").eq(index).show().siblings().hide();
})
</script>
</html>