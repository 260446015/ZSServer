<!DOCTYPE html>
<html lang="en">

<head>

    <!-- Basic -->
    <meta charset="UTF-8"/>
    <title>词库管理-关键词</title>

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>


    <!-- Favicon and touch icons -->
		<#include "/common/link.ftl">
    <link href="/assets/vendor/bootstrap/css/bootstrap-select.css" rel="stylesheet"/>
    <!-- end: CSS file-->

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
                    <h2>文件上传界面</h2>
                </div>
            </div>
            <!-- End Page Header -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-default bk-bg-white">
                        <div class="panel-heading bk-bg-white">
                            <h6><i class="fa fa-table red"></i><span class="break"></span>文件上传界面</h6>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                            </div>

                            <button class="btn btn-primary" data-toggle="modal" data-target="#myModal" id="uploadKeyword">添加知识图谱关键词 <i class="fa fa-plus"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Main Page -->
        <!-- Footer -->
				<#include "/common/footer.ftl">
        <!-- End Footer -->
    </div>
</div><!--/container-->

<div class="clearfix"></div>
		<#include "/common/script.ftl">
        <script src="/assets/js/upload/uploadPage.js"></script>
<!-- end: JavaScript-->
</body>
</html>