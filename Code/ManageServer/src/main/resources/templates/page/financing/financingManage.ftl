<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Basic -->
    <meta charset="UTF-8" />
    <title>慧数招商—融资数据</title>
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
                <div class="pull-left">
                    <ol class="breadcrumb visible-sm visible-md visible-lg">
                        <li><a href="javascript:void(0)"><i class="icon fa fa-home"></i>Home</a></li>
                        <li class="active"><i class="fa fa-pencil-square-o"></i>financingManage</li>
                    </ol>
                </div>
                <div class="pull-right">
                    <h2>融资数据</h2>
                </div>
            </div>
            <!-- End Page Header -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-default bk-bg-white">
                        <div class="panel-body">
                            <div class="panel-body">产业：
                                <div class="bk-margin-5 btn-group">
                                    <a class="btn btn-default btn-success" role="button" value="1">全部</a>
                                    <a class="btn btn-default" role="button">人工智能</a>
                                    <a class="btn btn-default" role="button">大数据</a>
                                    <a class="btn btn-default" role="button">物联网</a>
                                    <a class="btn btn-default" role="button">生物技术</a>
                                </div>
                            </div>
                            <div class="panel-body">区域：
                                <div class="bk-margin-5 btn-group">
                                    <a class="btn btn-default btn-success" role="button" value="2">全部</a>
                                    <a class="btn btn-default" role="button">北京</a>
                                    <a class="btn btn-default" role="button">上海</a>
                                    <a class="btn btn-default" role="button">广州</a>
                                    <a class="btn btn-default" role="button">深圳</a>
                                </div>
                            </div>
                            <div class="panel-body">融资轮次：
                                <div class="bk-margin-5 btn-group">
                                    <a class="btn btn-default btn-success" role="button" value="3">全部</a>
                                    <a class="btn btn-default" role="button">种子轮</a>
                                    <a class="btn btn-default" role="button">天使轮</a>
                                    <a class="btn btn-default" role="button">A轮</a>
                                    <a class="btn btn-default" role="button">B轮</a>
                                    <a class="btn btn-default" role="button">C轮</a>
                                    <a class="btn btn-default" role="button">D轮</a>
                                    <a class="btn btn-default" role="button">IPO上市</a>
                                    <a class="btn btn-default" role="button">新三板</a>
                                </div>
                            </div>
                            <div class="panel-body">排序：
                                <div class="bk-margin-5 btn-group">
                                    <a class="btn btn-default btn-success" role="button" value="4">按时间</a>
                                    <a class="btn btn-default" role="button">按金额</a>
                                </div>
                            </div>
                            <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                                <thead>
                                <tr>
                                    <th>logo</th>
                                    <th>时间	</th>
                                    <th>公司</th>
                                    <th>轮次</th>
                                    <th>融资额</th>
                                    <th>投资方</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody id="user_list">
                                </tbody>
                            </table>
                            <div class="page-box clearfix">
                                <ul class="page pull-right" id="page"></ul>
                            </div>
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
<script src="/js/financing/financingManage.js"></script>
<!-- end: JavaScript-->
</body>
</html>