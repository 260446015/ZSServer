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
	<div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <blockquote class="layui-elem-quote layui-row">
                <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
                    <span style="color: #1E9FFF;" class="layui-icon">&#xe629;</span>
                    <span>园区会员总数</span>
                    <span style="color: #1E9FFF; margin-left: 15px;">42</span>
                </div>
                <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
                    <span class="layui-icon" style="color: #ff992c;">&#xe637;</span>
                    <span>即将到期会员总数</span>
                    <span style=" color: #ff992c;margin-left: 15px;">7</span>
                </div>
            </blockquote>

            <div class="layui-row">
                <div class="layui-col-md6 " >
                    会员区域分布
                </div>
                <div class="layui-col-md6 " >
                    产业在园区中的占比
                </div>

            </div>
            <div class="layui-row">
                <div class="layui-col-md6 ">
                    <div id="chart1" style="width: 500px;height: 220px; border: 1px solid #e6e6e6;"></div>
                </div>
                <div class="layui-col-md6 ">
                    <div id="chart2" style="width: 500px;height: 220px; border: 1px solid #e6e6e6 ;"></div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md12 ">
                    <div id="barChart" style="width: 1000px;height: 400px; border-top: 1px solid #e6e6e6;"></div>
                </div>
            </div>
        </div>
    </div>
	<#include "/common/script.ftl">
	<script>
    // 基于准备好的dom，初始化echarts实例
    var myChart1 = echarts.init(document.getElementById('chart1'));
    var myChart2 = echarts.init(document.getElementById('chart2'));
    var barChart = echarts.init(document.getElementById('barChart'));
    option1 = {
        color: ['#3398DB','#FFB800','#2F4056','#009688','#4AAD44'],
        series: [
            {
                name:'访问来源',
                type:'pie',
                radius: ['50%', '70%'],

                data:[
                    {value:335, name:'天津'},
                    {value:310, name:'北京'},
                    {value:234, name:'上海'},
                    {value:135, name:'郑州'},
                    {value:23, name:'某某'}
                ]
            }
        ]
    };
    option2 = {
        color: ['#3398DB','#FFB800','#2F4056','#009688','#4AAD44'],
        series: [
            {
                name:'访问来源',
                type:'pie',
                radius: ['50%', '70%'],
                data:[
                    {value:335, name:'影视'},
                    {value:310, name:'外贸'},
                    {value:234, name:'生物医药'},
                    {value:135, name:'互联网'},
                ]
            },
        ]
    };

    option3 = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun','Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'直接访问',
                type:'bar',
                barWidth: '60%',
                data:[10, 52, 200, 334, 390, 330, 220,100, 52, 200, 334, 390, 330, 220]
            }
        ]
    };

    myChart1.setOption(option1);
    myChart2.setOption(option2);
    barChart.setOption(option3);
  </script>
</body>
