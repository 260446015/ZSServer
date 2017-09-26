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
                    <span style="color: #1E9FFF; margin-left: 15px;">${Request.data.data.memberNum} </span>
                </div>
                <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
                    <span class="layui-icon" style="color: #ff992c;">&#xe637;</span>
                    <span>即将到期会员总数</span>
                    <span style=" color: #ff992c;margin-left: 15px;">${Request.data.data.expireMemberNum} </span>
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
		var areaRatio= new Array();
		<#list Request.data.data.areaRatio?keys as key>
			var obj = {value:'${Request.data.data.areaRatio[key]}', name:'${key}'}; 
			areaRatio.push(obj);
		</#list>
		var industryRatio= new Array();
		<#list Request.data.data.industryRatio?keys as key>
			var obj = {value:'${Request.data.data.industryRatio[key]}', name:'${key}'}; 
			industryRatio.push(obj);
		</#list>
		var myName= new Array();
		var myValue= new Array();
		<#list Request.data.data.areaNum as list>
			myName.push('${list[1]}');
			myValue.push('${list[0]}');
		</#list>
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

                data:areaRatio
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
                data:industryRatio
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
                data : myName,
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
                name:'人数',
                type:'bar',
                barWidth: '60%',
                data:myValue
            }
        ]
    };

    myChart1.setOption(option1);
    myChart2.setOption(option2);
    barChart.setOption(option3);
  </script>
</body>
