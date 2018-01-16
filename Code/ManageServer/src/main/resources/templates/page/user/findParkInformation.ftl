<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Basic -->
    <meta charset="UTF-8" />
    <title>慧数招商—添加账户园区</title>
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
        <div class="main">
            <!-- Page Header -->
            <div class="page-header">
                <div class="pull-left">
                    <ol class="breadcrumb visible-sm visible-md visible-lg">
                        <li><a href="javascript:void(0)"><i class="icon fa fa-home"></i>Home</a></li>
                        <li class="active"><i class="fa fa-pencil-square-o"></i>addUserPark</li>
                    </ol>
                </div>
                <div class="pull-right">
                    <h2>添加用户园区</h2>
                </div>
            </div>
            <!-- End Page Header -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-default bk-bg-white">
                        <div class="panel-body">
                            <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                                <tbody>
                                    <tr>
                                        <td rowspan="3" id="logo"></td>
                                        <th>园区名称</th>
                                        <td id="parkName"></td>
                                        <th>所属地</th>
                                        <td id="parkArea"></td>
                                    </tr>
                                    <tr>
                                        <th>详细地址</th>
                                        <td id="address"></td>
                                        <th>园区产业</th>
                                        <td id="industry"></td>
                                    </tr>
                                    <tr>
                                        <th>简介</th>
                                        <td colspan="3" id="introduction"></td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="panel-heading bk-bg-white text-center" style="margin-top: 30px">
                                <div class="row">
                                    <div class="col-xs-8 text-left bk-vcenter">
                                        <h2 class="bk-margin-off">消费监测</h2>
                                    </div>
                                    <div class="col-xs-4 bk-vcenter text-right">
                                        <i class="fa fa-file-text-o"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body bk-padding-off-top bk-padding-off-bottom bk-noradius">
                                <table class="table table-bordered table-striped mb-none" id="amount_table">
                                    <thead>
                                    <tr>
                                        <th>账户</th>
                                        <th>消费金额</th>
                                        <th>剩余金额</th>
                                    </tr>
                                    </thead>
                                    <tbody id="amount">
                                    </tbody>
                                </table>
                                总计:<div id="total"></div>
                                <div class="page-box clearfix">
                                    <ul class="page pull-right" id="amount_page"></ul>
                                </div>
                                <a href="javascript:" class="bk-fg-primary bk-fg-lighten"></a>
                            </div>
                            <div class="panel-heading bk-bg-white text-center" style="margin-top: 30px">
                                <div class="row">
                                    <div class="col-xs-8 text-left bk-vcenter">
                                        <h2 class="bk-margin-off">账号列表</h2>
                                    </div>
                                    <div class="col-xs-4 bk-vcenter text-right">
                                        <i class="fa fa-file-text-o"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="bk-margin-bottom-10">
                                            <button class="btn btn-info my_add">添加账号 <i class="fa fa-plus"></i></button>
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
        </div>
        <!-- End Main Page -->
    <#include "/common/footer.ftl">
    </div>
</div><!--/container-->
<div class="clearfix"></div>
<!-- start: JavaScript-->
<!-- Vendor JS-->
<#include "/common/script.ftl">
<script src="/js/user/findParkInformation.js"></script>
<script>
    $(function() {
        information(${id})
    });
</script>
<!-- end: JavaScript-->
</body>
</html>