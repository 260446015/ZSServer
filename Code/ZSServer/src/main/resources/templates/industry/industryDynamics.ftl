<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-产业动态</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="慧数招商平台，是一个关于园区产业招商的大数据管理平台">
    <meta name="keywords" content="慧数，招商，慧数招商，招商平台，园区，园区招商，园区招商平台，科技园，产业园，大数据，产业">
    <meta name="author" content="张鑫，慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
    <!-- css共用部分 start -->
   
    
   	<#include "/common/link.ftl"/>
   
</head>
<body class="bg2">
<#include "/common/header.ftl"/>
<div class="wrapper">
    <div class="page-content">
        <#include "/common/sidebar.ftl"/>
        <div class="right-content">
            <div id="myCarousel" class="carousel slide">
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="/images/swiper_slide1.jpg">
                        <div class="mask"></div>
                        <div class="carousel-text-box">
                            <div class="carousel-text-body">
                                <p class="title fadeInLeft animated">2017年第三届深圳国际互联网与电子商务博览会</p>
                                <p class="details fadeIn animated">举办时间：2017-10-12～2017-10-14</p>
                                <p class="details fadeIn animated">举办展馆：深圳会展中心</p>
                                <p class="details fadeIn animated">展会行业：电脑、多媒体、网络、通信</p>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <img src="/images/swiper_slide2.jpg">
                        <div class="mask"></div>
                        <div class="carousel-text-box">
                            <div class="carousel-text-body">
                                <p class="title fadeInLeft animated">2018年第三届深圳国际互联网与电子商务博览会</p>
                                <p class="details fadeIn animated">举办时间：2018-10-12～2018-10-14</p>
                                <p class="details fadeIn animated">举办展馆：深圳会展中心</p>
                                <p class="details fadeIn animated">展会行业：电脑、多媒体、网络、通信</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="model-box">
                    <div class="model-header">
                        <h3 class="model-title">关键词云</h3>
                    </div>
                    <div class="model-body">
                        <div class="radio-box">
                            <label class="radio-inline">
                                <input class="radio-item" name="timer" checked  value="近1周" type="radio" />
                                近1周
                            </label>
                            <label class="radio-inline">
                                <input class="radio-item" name="timer" value="近1个月"  type="radio" />
                                近1个月
                            </label>
                            <label class="radio-inline">
                                <input class="radio-item" name="timer" value="近6个月" type="radio" />
                                近6个月
                            </label>
                            <label class="radio-inline">
                                <input class="radio-item" name="timer" value="近1年" type="radio" />
                                近1年
                            </label>
                        </div>
                        <div class="charts" id="scatter"></div>
                        <div class="row" id="articleList">
                            <div class="col-md-6">
                                <a class="scatter-blocks" href="javascript:void(0);">
                                    <span class="scatter-type">【新能源】</span>
                                    <span class="scatter-title">2017年第十九届中国国际高新技术成果交易会</span>
                                </a>
                            </div>
                            <div class="col-md-6">
                                <a class="scatter-blocks" href="javascript:void(0);">
                                    <span class="scatter-type">【新能源】</span>
                                    <span class="scatter-title">2017年第十九届中国国际高新技术成果交易会</span>
                                </a>
                            </div>
                            <div class="col-md-6">
                                <a class="scatter-blocks" href="javascript:void(0);">
                                    <span class="scatter-type">【新能源】</span>
                                    <span class="scatter-title">2017年第十九届中国国际高新技术成果交易会</span>
                                </a>
                            </div>
                            <div class="col-md-6">
                                <a class="scatter-blocks" href="javascript:void(0);">
                                    <span class="scatter-type">【新能源】</span>
                                    <span class="scatter-title">2017年第十九届中国国际高新技术成果交易会</span>
                                </a>
                            </div>
                            <div class="col-md-6">
                                <a class="scatter-blocks" href="javascript:void(0);">
                                    <span class="scatter-type">【新能源】</span>
                                    <span class="scatter-title">2017年第十九届中国国际高新技术成果交易会</span>
                                </a>
                            </div>
                            <div class="col-md-6">
                                <a class="scatter-blocks" href="javascript:void(0);">
                                    <span class="scatter-type">【新能源】</span>
                                    <span class="scatter-title">2017年第十九届中国国际高新技术成果交易会</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="model-box">
                    <div class="model-header">
                        <h3 class="model-title">科研成果</h3>
                    </div>
                    <div class="model-body border-box">
                        <div class="row">
                        <#list content as info>
                            <div class="col-md-6">
                                <a class="scatter-blocks no-border" href="/summit/getEssayDetails.json?essayId=${info.id}" target="_blank" >
                                    <span class="icon-block"></span>
                                    <span class="scatter-type">${info.industryLabel}</span>
                                    <span class="scatter-title">${info.title}</span>
                                </a>
                                <p class="scatter-content">
                                    ${info.summary}
                                </p>
                                <p class="scatter-lib"><span>时间：${info.publishTime}</span><span>涉及公司：
                                   <#list info.bus as param>
                                       ${param}
                                   </#list>
                                </span></p>
                            </div>
                            </#list>
                        </div>
                    </div>
                </div>
                <div class="model-box">
                    <div class="model-header">
                        <h3 class="model-title">产业资讯</h3>
                    </div>
                    <div class="search-box">
                        <div class="search-group">
                            <div class="search-item-title">
                                产业
                            </div>
                            <div class="search-item-content">
                                <a href="javascript:void(0);" onclick="getIndustry(1,'全部')" class="search-item active">全部</a>
                                <a href="javascript:void(0);" onclick="getIndustry(1,'人工智能')" class="search-item">人工智能</a>
                                <a href="javascript:void(0);" onclick="getIndustry(1,'大数据')" class="search-item">大数据</a>
                                <a href="javascript:void(0);" onclick="getIndustry(1,'物联网')" class="search-item">物联网</a>
                                <a href="javascript:void(0);" onclick="getIndustry(1,'生物技术')" class="search-item">生物技术</a>
                            </div>
                        </div>
                        <div class="search-group">
                            <div class="search-item-title">
                                区域
                            </div>
                            <div class="search-item-content">
                                <a href="javascript:void(0);" onclick="getIndustry(2,'全部')" class="search-item active">全部</a>
                                <a href="javascript:void(0);" onclick="getIndustry(2,'北京')" class="search-item">北京</a>
                                <a href="javascript:void(0);" onclick="getIndustry(2,'上海')" class="search-item">上海</a>
                                <a href="javascript:void(0);" onclick="getIndustry(2,'广州')" class="search-item">广州</a>
                                <a href="javascript:void(0);" onclick="getIndustry(2,'深圳')" class="search-item">深圳</a>
                            </div>
                        </div>
                        <div class="search-group">
                            <div class="search-item-title">
                                排序
                            </div>
                            <div class="search-item-content">
                                <a href="javascript:void(0);" onclick="getIndustry(3,'按热度')" class="search-item active">按热度</a>
                                <a href="javascript:void(0);" onclick="getIndustry(3,'按时间')" class="search-item">按时间</a>
                            </div>
                        </div>
                    </div>
                    <div class="model-body border-box">
                        <div class="row" id="industryInfoList">
                            <div class="col-md-12 border-bottom">
                                <a class="scatter-blocks no-border" href="javascript:void(0);">
                                    <span class="scatter-type">【新能源】</span>
                                    <span class="scatter-title">2017年第十九届中国国际高新技术成果交易会</span>
                                </a>
                                <p class="scatter-content">
                                    【TechWeb报道】近日，华为云AI开发部总经理罗华霖在深圳举办的GIIS-全球产业创新
                                    峰会做了《华为人工智能实践与创新》主题演讲，其核心观点如下：目前人们看到的都是
                                    弱人工智能，弱人 工智能的本质
                                </p>
                                 <p class="scatter-lib">
                                   <span>发布时间:2017-10-02</span>
                                </p>
                            </div>
                            <div class="col-md-12 border-bottom">
                                <a class="scatter-blocks no-border" href="javascript:void(0);">
                                    <span class="scatter-type">【新能源】</span>
                                    <span class="scatter-title">2017年第十九届中国国际高新技术成果交易会</span>
                                </a>
                                <p class="scatter-content">
                                    【新浪科技讯北京时间10月10日早间消息，Facebook&nbsp;CEO马克&middot;扎克
                                    伯格（MarkZuckerberg）今天宣布，该公司正在与美国红十字会共同为波多黎克提供
                                    帮助，借助该公司正在与美国红十字会共同为波多黎克提供帮助
                                </p>
                                 <p class="scatter-lib">
                                   <span>发布时间:2017-10-02</span>
                                </p>
                            </div>
                            <div class="col-md-12 border-bottom">
                                <a class="scatter-blocks no-border" href="javascript:void(0);">
                                    <span class="scatter-type">【新能源】</span>
                                    <span class="scatter-title">2017年第十九届中国国际高新技术成果交易会</span>
                                </a>
                                <p class="scatter-content">
                                    【TechWeb报道】近日，华为云AI开发部总经理罗华霖在深圳举办的GIIS-全球产业创新
                                    峰会做了《华为人工智能实践与创新》主题演讲，其核心观点如下：目前人们看到的都是
                                    弱人工智能，弱人 工智能的本质
                                </p>
                               <p class="scatter-lib">
                                    <span>发布时间:2017-10-02</span>
                                </p>
                            </div>
                            <div class="col-md-12 border-bottom">
                                <a class="scatter-blocks no-border" href="javascript:void(0);">
                                    <span class="scatter-type">【新能源】</span>
                                    <span class="scatter-title">2017年第十九届中国国际高新技术成果交易会</span>
                                </a>
                                <p class="scatter-content">
                                    【新浪科技讯北京时间10月10日早间消息，Facebook&nbsp;CEO马克&middot;扎克
                                    伯格（MarkZuckerberg）今天宣布，该公司正在与美国红十字会共同为波多黎克提供
                                    帮助，借助该公司正在与美国红十字会共同为波多黎克提供帮助
                                </p>
                                <p class="scatter-lib">
                                   <span>发布时间:2017-10-02</span>
                                </p>
                            </div>
                            <div class="col-md-12 border-bottom">
                                <a class="scatter-blocks no-border" href="javascript:void(0);">
                                    <span class="scatter-type">【新能源】</span>
                                    <span class="scatter-title">2017年第十九届中国国际高新技术成果交易会</span>
                                </a>
                                <p class="scatter-content">
                                    【TechWeb报道】近日，华为云AI开发部总经理罗华霖在深圳举办的GIIS-全球产业创新
                                    峰会做了《华为人工智能实践与创新》主题演讲，其核心观点如下：目前人们看到的都是
                                    弱人工智能，弱人 工智能的本质
                                </p>
                                 <p class="scatter-lib">
                                    <span>发布时间:2017-10-02</span>
                                </p>
                            </div>
                            <div class="col-md-12 border-bottom">
                                <a class="scatter-blocks no-border" href="javascript:void(0);">
                                    <span class="scatter-type">【新能源】</span>
                                    <span class="scatter-title">2017年第十九届中国国际高新技术成果交易会</span>
                                </a>
                                <p class="scatter-content">
                                    【新浪科技讯北京时间10月10日早间消息，Facebook&nbsp;CEO马克&middot;扎克
                                    伯格（MarkZuckerberg）今天宣布，该公司正在与美国红十字会共同为波多黎克提供
                                    帮助，借助该公司正在与美国红十字会共同为波多黎克提供帮助
                                </p>
                                <p class="scatter-lib">
                                   <span>发布时间:2017-10-02</span>
                                </p>
                            </div>
                        </div>
                    </div>
                        <div class="page-box clearfix">
                				<ul class="page pull-right" id="page"></ul>
            			</div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include  "/common/script.ftl"/>
<script src="/js/industryDynamics/industryDynamics.js"></script>
</body>
</html>