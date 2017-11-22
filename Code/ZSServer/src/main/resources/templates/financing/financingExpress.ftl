<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-融资速递</title>
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
            <div class="model-box">
                <div class="model-header">
                    <h3 class="model-title">产业融资</h3>
                </div>
                <div class="model-body">
                    <div class="radio-box">
                        <label class="radio-inline">
                            <input class="radio-item" name="timer" value="人工智能" checked type="checkbox" />
                            人工智能
                        </label>
                        <label class="radio-inline">
                            <input class="radio-item" name="timer" value="大数据" type="checkbox" />
                            大数据
                        </label>
                        <label class="radio-inline">
                            <input class="radio-item" name="timer" value="物联网" type="checkbox" />
                            物联网
                        </label>
                        <label class="radio-inline">
                            <input class="radio-item" name="timer" value="生物技术" type="checkbox" />
                            生物技术
                        </label>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="charts" id="barCharts"></div>
                        </div>
                        <div class="col-md-6">
                            <div class="box box1">
                                <img class="lay lay-left-bottom" src="/images/left_bottom.png" alt="">
                                <img class="lay lay-right-top" src="/images/right_top.png" alt="">
                                <h3 class="box-title"></h3>
                                <div class="box-body">
                                    <div class="search-box" id="company">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="model-box">
                <div class="row">
                    <div class="col-md-6" id="city_dynamic">
                    </div>
                    <div class="col-md-6" id="city_dynamic2">
                    </div>
                </div>

            </div>
            <div class="model-box">
                <div class="model-header">
                    <h3 class="model-title">融资企业</h3>
                </div>
                <div class="search-box">
                    <div class="search-group">
                        <div class="search-item-title">
                            产业
                        </div>
                        <div class="search-item-content">
                            <a href="javascript:void(0);" onclick="myClick(1,'全部')" class="search-item active">全部</a>
                            <a href="javascript:void(0);" onclick="myClick(1,'人工智能')" class="search-item">人工智能</a>
                            <a href="javascript:void(0);" onclick="myClick(1,'大数据')" class="search-item">大数据</a>
                            <a href="javascript:void(0);" onclick="myClick(1,'互联网')" class="search-item">互联网</a>
                            <a href="javascript:void(0);" onclick="myClick(1,'生物技术')" class="search-item">生物技术</a>
                        </div>
                    </div>
                    <div class="search-group">
                        <div class="search-item-title">
                            区域
                        </div>
                        <div class="search-item-content">
                            <a href="javascript:void(0);" onclick="myClick(2,'全部')" class="search-item active">全部</a>
                            <a href="javascript:void(0);" onclick="myClick(2,'北京')" class="search-item">北京</a>
                            <a href="javascript:void(0);" onclick="myClick(2,'上海')" class="search-item">上海</a>
                            <a href="javascript:void(0);" onclick="myClick(2,'广州')" class="search-item">广州</a>
                            <a href="javascript:void(0);" onclick="myClick(2,'深圳')" class="search-item">深圳</a>
                        </div>
                    </div>
                    <div class="search-group">
                        <div class="search-item-title">
                            融资轮次
                        </div>
                        <div class="search-item-content">
                            <a href="javascript:void(0);" onclick="myClick(3,'全部')" class="search-item active">全部</a>
                            <a href="javascript:void(0);" onclick="myClick(3,'种子轮')" class="search-item">种子轮</a>
                            <a href="javascript:void(0);" onclick="myClick(3,'天使轮')" class="search-item">天使轮</a>
                            <a href="javascript:void(0);" onclick="myClick(3,'A轮')" class="search-item">A轮</a>
                            <a href="javascript:void(0);" onclick="myClick(3,'B轮')" class="search-item">B轮</a>
                            <a href="javascript:void(0);" onclick="myClick(3,'C轮')" class="search-item">C轮</a>
                            <a href="javascript:void(0);" onclick="myClick(3,'D轮')" class="search-item">D轮</a>
                            <a href="javascript:void(0);" onclick="myClick(3,'IPO上市')" class="search-item">IPO上市</a>
                            <a href="javascript:void(0);" onclick="myClick(3,'新三板')" class="search-item">新三板</a>
                        </div>
                    </div>
                    <div class="search-group">
                        <div class="search-item-title">
                            排序
                        </div>
                        <div class="search-item-content">
                            <a href="javascript:void(0);" onclick="myClick(4,'按时间')" class="search-item active">按时间</a>
                            <a href="javascript:void(0);" onclick="myClick(4,'按金额')" class="search-item">按金额</a>
                        </div>
                    </div>
                </div>
                <div class="model-body border-box">
                    <div class="row">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>时间</th>
                                    <th class="text-left">公司</th>
                                    <th>轮次</th>
                                    <th>融资额</th>
                                    <th>投资方</th>
                                    <th>详情</th>
                                </tr>
                            </thead>
                            <tbody id="city_list">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include  "/common/footer.ftl"/>
<!-- js 共用部分 start -->
<#include  "/common/script.ftl"/>
<!-- js 共用部分 end -->
<script src="/js/financingExpress/financingExpress.js"></script>
</body>
</html>