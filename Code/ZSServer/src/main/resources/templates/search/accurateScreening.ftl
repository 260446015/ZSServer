<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-精确筛选</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="慧数招商平台，是一个关于园区产业招商的大数据管理平台">
    <meta name="keywords" content="慧数，招商，慧数招商，招商平台，园区，园区招商，园区招商平台，科技园，产业园，大数据，产业">
    <meta name="author" content="张鑫，慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
   <#include "/common/link.ftl"/>
    <!-- js 兼容低版本IE start -->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- js 兼容低版本IE end -->
</head>
<body class="bg2">
<#include "/common/header2.ftl"/>
<div class="wrapper">
    <div class="page-content">
    <#include "/common/searchSidebar.ftl"/>
         <div class="right-content">
            <div class="mt50 mb20">
                <div class="col-md-1">
                    <button class="btn btn-blue" id="search_tag" type="button">筛选标签</button>
                </div>
                <div id="searchTag">
                    <!--<button class="btn btn-fill btn-blue search-tag">生物技术
                        <span type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</span>
                    </button>
                    <button class="btn btn-fill btn-blue search-tag">5000人以上
                        <span type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</span>
                    </button>
                    <button class="btn btn-fill btn-blue search-tag">创业板块
                        <span type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</span>
                    </button>
                    <button class="btn btn-fill btn-blue search-tag">5000-10000万
                        <span type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</span>
                    </button>-->
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="charts" id="charts"></div>
                </div>
            </div>
            <div class="layer-person">
                <h3 class="layer-person-title text-center" id="cname">深圳市腾讯计算机系统有限公司
                	 <button type="button" class="close">×</button>
                </h3>
                <div class="layer-body small-line-height">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-4 text-right control-label">法人代表</label>
                            <div class="col-md-7">
                                <p class="form-control-static" id="boss1">马化腾</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 text-right control-label">状态</label>
                            <div class="col-md-7">
                                <p class="form-control-static" id="state1">存续</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 text-right control-label">注册时间</label>
                            <div class="col-md-7">
                                <p class="form-control-static" id="time1">1998-11-11</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 text-right control-label">行业</label>
                            <div class="col-md-7">
                                <p class="form-control-static" id="ind1">软件和信息技术服务业</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 text-right control-label">注册资本</label>
                            <div class="col-md-7">
                                <p class="form-control-static" id="money1">6,500.0000万元人民币</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 text-right control-label">注册地址</label>
                            <div class="col-md-7">
                                <p class="form-control-static" id="address1">深圳市南山区高新区高新南 一路飞亚达大厦5-10楼</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layer-footer text-center" id="text-content">
                    <a href="javascript:void(0);" class="like">查看更多</a>
                </div>
            </div>
            <div class="modal fade in customed-modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <img class="lay lay-left-bottom" src="/images/left_bottom.png" alt="">
                        <img class="lay lay-right-top" src="/images/right_top.png" alt="">
                        <img class="lay lay-left-top" src="/images/left_bottom.png" alt="">
                        <img class="lay lay-right-bottom" src="/images/right_top.png" alt="">
                        <div class="modal-header">
                            <!--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>-->
                            <h4 class="modal-title" id="myModalLabel">选择企业筛选标签
                                <a href="javascript:void(0);" class="pull-right multiple ">+ 支持多选</a>
                            </h4>
                        </div>
                        <div class="modal-body">
                            <div class="search-box">
                                <div class="search-group">
                                    <div class="search-item-title">
                                        产业
                                    </div>
                                    <div class="search-item-content">
                                        <a href="javascript:void(0);" onclick="searchTab(1,'全部')"  class="search-item active">全部</a>
                                        <a href="javascript:void(0);" onclick="searchTab(1,'人工智能')"  class="search-item">人工智能</a>
                                        <a href="javascript:void(0);" onclick="searchTab(1,'大数据')"  class="search-item">大数据</a>
                                        <a href="javascript:void(0);" onclick="searchTab(1,'物联网')"  class="search-item">物联网</a>
                                        <a href="javascript:void(0);" onclick="searchTab(1,'生物科技')"  class="search-item">生物科技</a>
                                    </div>
                                </div>
                               
                                <div class="search-group">
                                    <div class="search-item-title">
                                        区域
                                    </div>
                                    <div class="search-item-content">
                                        <a href="javascript:void(0);" onclick="searchTab(2,'全部')" class="search-item active">全部</a>
                                        <a href="javascript:void(0);" onclick="searchTab(2,'北京')" class="search-item">北京</a>
                                        <a href="javascript:void(0);" onclick="searchTab(2,'上海')" class="search-item">上海</a>
                                        <a href="javascript:void(0);" onclick="searchTab(2,'广州')" class="search-item">广州</a>
                                        <a href="javascript:void(0);" onclick="searchTab(2,'天津')" class="search-item">天津</a>
                                    </div>
                                </div>
                                <!--	<div class="search-group">
                                    	<div class="search-item-title">
                                      	  产业规模
                                   	 </div>
                                    <div class="search-item-content">
                                         <a href="javascript:void(0);" class="search-item active">1-15人</a>
                                         <a href="javascript:void(0);" class="search-item">51-100人</a>
                                         <a href="javascript:void(0);" class="search-item">101-200人</a>
                                         <a href="javascript:void(0);" class="search-item">200人以上</a>
                                     </div>
                                 </div>-->
                                <div class="search-group">
                                    <div class="search-item-title">
                                        成立时间
                                    </div>
                                    <div class="search-item-content">
                                        <a href="javascript:void(0);" onclick="searchTab(3,'1-5年')" class="search-item active">1-5年</a>
                                        <a href="javascript:void(0);" onclick="searchTab(3,'5-10年')" class="search-item">5-10年</a>
                                        <a href="javascript:void(0);" onclick="searchTab(3,'10-20年')" class="search-item">10-20年</a>
                                        <a href="javascript:void(0);" onclick="searchTab(3,'20年以上')" class="search-item">20年以上</a>
                                    </div>
                                </div>
                                <div class="search-group">
                                    <div class="search-item-title">
                                        成立资本
                                    </div>
                                    <div class="search-item-content">
                                        <a href="javascript:void(0);" onclick="searchTab(4,'0-50万')" class="search-item active">0-50万</a>
                                        <a href="javascript:void(0);" onclick="searchTab(4,'50-100万')" class="search-item">50-100万</a>
                                        <a href="javascript:void(0);" onclick="searchTab(4,'100-500万')" class="search-item">100-500万</a>
                                        <a href="javascript:void(0);" onclick="searchTab(4,'500万以上')" class="search-item">500万以上</a>
                                    </div>
                                </div>
                                 <!-- <div class="search-group">
                                     <div class="search-item-title">
                                            融资阶段
                                     </div>
                                     <div class="search-item-content">
                                         <a href="javascript:void(0);" class="search-item active">1-5年</a>
                                         <a href="javascript:void(0);" class="search-item">5-10年</a>
                                         <a href="javascript:void(0);" class="search-item">10-20年</a>
                                         <a href="javascript:void(0);" class="search-item">20年以上</a>
                                     </div>
                                 </div>-->
                            </div>
                        </div>
                        <div class="modal-footer text-center">
                            <button class="btn btn-blue" id="LabelBlue">保存标签</button>
                            <button class="btn btn-fill btn-blue" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/common/footer.ftl"/>
<#include "/common/script.ftl"/>
<script src="/js/accurateScreening/accurateScreening.js"></script>
</body>
</html>	