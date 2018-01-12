<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Basic -->
    <meta charset="UTF-8" />
    <title>慧数招商—用户列表</title>
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
                        <li class="active"><i class="fa fa-pencil-square-o"></i>userManage</li>
                    </ol>
                </div>
                <div class="pull-right">
                    <h2>用户列表</h2>
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
                                        <button class="btn btn-info my_add">Add <i class="fa fa-plus"></i></button>
                                    </div>
                                </div>
                            </div>
                            <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                                <thead>
                                <tr>
                                    <th>账号</th>
                                    <th>真实姓名</th>
                                    <th>手机</th>
                                    <th>邮箱</th>
                                    <th>创建时间</th>
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
<script src="/js/user/userManage.js"></script>
<!-- end: JavaScript-->
</body>
</html>