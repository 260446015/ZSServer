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
                        <li class="active"><i class="fa fa-pencil-square-o"></i>financingEdit</li>
                    </ol>
                </div>
                <div class="pull-right">
                    <h2>修改融资企业数据</h2>
                </div>
            </div>
            <!-- End Page Header -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-default bk-bg-white">
                        <div class="panel-body">
                            <form action="" method="post" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">logo</label>
                                    <div class="col-md-9">
                                        <input type="text" name="logo" class="form-control" placeholder="" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">融资企业</label>
                                    <div class="col-md-9">
                                        <input type="text" name="financingCompany" class="form-control" placeholder="" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">融资时间</label>
                                    <div class="col-md-9">
                                        <input type="text" name="financingDate" class="form-control" placeholder="" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">轮次</label>
                                    <div class="col-md-9">
                                        <input type="text" name="invest" class="form-control" placeholder="" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">融资额</label>
                                    <div class="col-md-9">
                                        <input type="text" name="financingAmount" class="form-control" placeholder="" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">投资方</label>
                                    <div class="col-md-9">
                                        <input type="text" name="investor" class="form-control" placeholder="" >
                                    </div>
                                </div>
                                <p>
                                    <button class="bk-margin-5 btn btn-labeled btn-success" type="button">
                                        <span class="btn-label"><i class="fa fa-check"></i></span>保存</button>
                                    <button class="bk-margin-5 btn btn-labeled btn-danger" type="button">
                                        <span class="btn-label"><i class="fa fa-times"></i></span>取消</button>
                                </p>
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
<script src="/js/financing/financingEdit.js"></script>
<script>
    addData('${id}');
</script>
<!-- end: JavaScript-->
</body>
</html>