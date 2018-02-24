<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Basic -->
    <meta charset="UTF-8" />
    <title>慧数招商—报告管理</title>
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <!-- Favicon and touch icons -->
    <#include "/common/link.ftl">
    <!-- Head Libs -->
    <script src="/assets/plugins/modernizr/js/modernizr.js"></script>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- Start: Header -->
<#include "/common/header.ftl">
<!-- End: Header -->
<!-- Start: Content -->
<div class="container-fluid content">
    <div class="row">
        <!-- Sidebar -->
    <#include "/common/sidebar.ftl">
        <!-- End Sidebar -->
        <!-- Main Page -->
        <div class="main sidebar-minified">
            <!-- Page Header -->
            <div class="page-header">
                <div class="pull-right">
                    <h2>h5月报详情</h2>
                </div>
            </div>
            <!-- End Page Header -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-default bk-bg-white">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="bk-margin-bottom-10">
                                        <a class="btn btn-info" href="javascript:history.go(-1)">返回上一页</a>
                                    </div>
                                </div>
                            </div>
                            <form action="javascript:void(0)" method="post" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">名称</label>
                                    <div class="col-md-9">
                                        <label class="control-label name"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">时段</label>
                                    <div class="col-md-9">
                                        <label class="control-label time"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">月关键词</label>
                                    <div class="col-md-9">
                                        <label class="control-label keyWord"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">产业链高亮</label>
                                    <div class="col-md-9">
                                        <label class="control-label chain"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">本月焦点</label>
                                    <div class="col-md-9">
                                        <label class="control-label focus"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">行业动态</label>
                                    <div class="col-md-9">
                                        <label class="control-label dynamic"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">明星推荐</label>
                                    <div class="col-md-9">
                                        <label class="control-label recommend"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">优质企业</label>
                                    <div class="col-md-9 industry">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Main Page -->
    <#include "/common/footer.ftl">
    </div>
</div><!--/container-->
<div class="clearfix"></div>
<!-- start: JavaScript-->
<!-- Vendor JS-->
<#include "/common/script.ftl">
<script src="/js/report/htmlInfo.js"></script>
<script>
    addData(${id});
</script>
<!-- end: JavaScript-->
</body>
</html>