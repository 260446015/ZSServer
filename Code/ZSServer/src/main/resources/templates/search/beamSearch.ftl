<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-定向搜索</title>
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
<#include "/common/header2.ftl"/>
<div class="wrapper remove-bottom">
    <div class="page-content">
        <#include "/common/searchSidebar.ftl"/>
        <div class="right-content">
            <div class="container">
                <div class="row mt88">
                    <div class="col-md-4">
                        <img src="/images/left_side.png" width="100%" alt="">
                        <div class="mt30 btn-box-center input-btn">
                            <input type="text" placeholder="请输入公司名称" class="input"/>
                            <button class="btn btn-blue btn-gradient search-company" type="button" >搜企业</button>
                        </div>
                    </div>
                    <div class="col-md-4"></div>
                    <div class="col-md-4">
                        <img src="/images/right_side.png" class="right-side" width="100%" alt="">
                        <div class="side-active">
                            <label>
                                <img src="/images/right_side_active.png" width="100%" />
                                <input type="file" class="file-opacity" />
                            </label>
                        </div>
                        <div class="mt30 btn-box-center">
                            <button class="btn btn-blue btn-gradient upload-card" type="button">上传名片</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include  "/common/footer.ftl"/>
<div class="modal load-modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <img src="/images/loading_search.png" class="search-loading" alt="">
                <p class="text-loading">正在识别名片信息......</p>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- js 共用部分 start -->
<#include  "/common/script.ftl"/>
<!-- js 共用部分 end -->
<script src="/js/beamSearch/beamSearch.js"></script>
</body>
</html>