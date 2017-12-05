<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-园区地图</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="慧数招商平台，是一个关于园区产业招商的大数据管理平台">
    <meta name="keywords" content="慧数，招商，慧数招商，招商平台，园区，园区招商，园区招商平台，科技园，产业园，大数据，产业">
    <meta name="author" content="张鑫，慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
    <!-- css共用部分 start -->
    

	<#include "/common/link.ftl"/>
    <!-- css 共用部分 end -->
    <!-- js 兼容低版本IE start -->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- js 兼容低版本IE end -->
</head>
<body class="bg2">
<#include "/common/header.ftl"/>
<div class="wrapper">
    <div class="page-content">
      <#include "/common/sidebar2.ftl"/>
        <div class="right-content">
            <div class="map-box">
               <div class="gaodeMap" id="map"></div>
                <div id="tip"></div>
                <div class="search-circle-box">
                    <img class="search-circle-img" src="/images/search_img.png" />
                </div>
                <a href="javascript:void(0);" class="follow follow-black" id="attation" onclick="attation();">添加关注</a>
                <div class="panel panel-primary panel-opacity">
                    <div class="panel-heading">
                        <h3 class="panel-title text-center" id="gardenName">中关村软件园</h3>
                    </div>
                    <div class="panel-body">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-4 control-label">地址：</label>
                                <div class="col-md-8">
                                    <p class="form-control-static" id="gardenAddress">北京市海淀区西二旗</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label">成立时间：</label>
                                <div class="col-md-8">
                                    <p class="form-control-static" id="registTime">2009年</p></div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label">产业：</label>
                                <div class="col-md-8">
                                    <p class="form-control-static" id="gardenIndustry">人工智能、大数据、互联网</p></div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label">占地面积：</label>
                                <div class="col-md-8">
                                    <p class="form-control-static" id="gardenSquare">159公顷</p></div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label">项目地址：</label>
                                <div class="col-md-8">
                                    <p class="form-control-static" id="ddd">海淀区东北旺西路8号中关村软件园1号楼 （信息中心）C座运</p></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="model-box">
                    <div class="search-box">
                        <div class="search-group">
                            <div class="search-item-title">
                                产业
                            </div>
                            <div class="search-item-content">
                                <a href="javascript:void(0);" class="search-item active" onclick="sendIndustry('全部');">全部</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendIndustry('节能环保和新能源产业');">节能环保和新能源产业</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendIndustry('生物产业');">生物产业</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendIndustry('高端装备制造产业');">高端装备制造产业</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendIndustry('新材料产业');">新材料产业</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendIndustry('数字创意产业');">数字创意产业</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendIndustry('新兴信息产业');">新兴信息产业</a>
                            </div>
                        </div>
                        <div class="search-group">
                            <div class="search-item-title">
                                成立时间
                            </div>
                            <div class="search-item-content">
                                <a href="javascript:void(0);" class="search-item active" onclick="sendRegister('全部');">全部</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendRegister('1');">成立1年内</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendRegister('1-5');">成立1-5年</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendRegister('5-10');">成立5-10年</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendRegister('10-15');">成立10-15年</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendRegister('more');">成立5年以上</a>
                            </div>
                        </div>
                        <div class="search-group">
                            <div class="search-item-title">
                                注册资本
                            </div>
                            <div class="search-item-content">
                                <a href="javascript:void(0);" class="search-item active" onclick="sendCapital('全部');">全部</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendCapital('0-100');">0-100万</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendCapital('100-200');">100-200万</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendCapital('200-500');">200-500万</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendCapital('500-1000');">500-1000万</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendCapital('more');">1000万以上</a>
                            </div>
                        </div>
                        <div class="search-group">
                            <div class="search-item-title">
                                融资阶段
                            </div>
                            <div class="search-item-content">
                                <a href="javascript:void(0);" class="search-item active" onclick="sendInvest('全部');">全部</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendInvest('天使轮');">天使轮</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendInvest('A轮');">A轮</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendInvest('B轮');">B轮</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendInvest('C轮');">C轮</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendInvest('D轮');">D轮</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendInvest('新三板');">新三板</a>
                                <a href="javascript:void(0);" class="search-item" onclick="sendInvest('创业板');">创业板</a>
                            </div>
                        </div>
                    </div>
                    <div class="model-body border-box">
                        <div class="row" id="companyList">
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <p class="text-center">Copyright©2008-2016 中科点击（北京）科技有限公司-版权所有  京ICP备11012241-3号</p>
</div>
<!-- js 共用部分 start -->
<#include  "/common/script.ftl"/>
<!-- js 共用部分 end -->
<script type="text/javascript" src = 'http://webapi.amap.com/maps?v=1.4.0&key=8a3cd2d06a064e33adbf0d7a71c3246f'></script>
<script src="http://webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
<script src="/js/allCityPark/allCityParkDetails.js"></script>
</body>
</html>