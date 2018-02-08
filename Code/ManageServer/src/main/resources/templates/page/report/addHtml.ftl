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
                    <h2>h5月报</h2>
                </div>
            </div>
            <!-- End Page Header -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-default bk-bg-white">
                        <div class="panel-body">
                            <form action="javascript:void(0)" method="post" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">月报名字</label>
                                    <div class="col-md-9">
                                        <input type="text" name="name" class="form-control" placeholder="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">月报时间段</label>
                                    <div class="col-md-9">
                                        <input type="text" name="time" class="form-control" placeholder="">
                                    </div>
                                </div>
                                <div>
                                    <label class="col-md-3 control-label" for="text-input">大模块</label>
                                    <div class="col-md-9">
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th>排序</th>
                                                <th>名字</th>
                                                <th>logo样式</th>
                                            </tr>
                                            </thead>
                                            <tbody id="my_body">
                                            <tr>
                                                <td><input type="text" name="sort1" class="form-control" value="1" disabled></td>
                                                <td><input type="text" name="name1" class="form-control" placeholder=""></td>
                                                <td><input type="text" name="logo1" class="form-control" placeholder=""></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <div class="bk-margin-bottom-10">
                                            <button class="btn btn-info my_add">添加模块数据 <i class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <label class="col-md-3 control-label" for="text-input">二级模块</label>
                                    <div class="col-md-9">
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th>排序</th>
                                                <th>名字</th>
                                                <th>logo样式</th>
                                                <th>父菜单名字</th>
                                            </tr>
                                            </thead>
                                            <tbody id="my_body2">
                                            <tr>
                                                <td><input type="text" name="sort21" class="form-control" value="1" disabled></td>
                                                <td><input type="text" name="name21" class="form-control" placeholder=""></td>
                                                <td><input type="text" name="logo21" class="form-control" placeholder=""></td>
                                                <td><input type="text" name="parent21" class="form-control" placeholder=""></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <div class="bk-margin-bottom-10">
                                            <button class="btn btn-info my_add2">添加模块数据 <i class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <p>
                                <button class="bk-margin-5 btn btn-labeled btn-success" type="button">
                                    <span class="btn-label"><i class="fa fa-check"></i></span>下一步</button>
                                <button class="bk-margin-5 btn btn-labeled btn-danger" type="button">
                                    <span class="btn-label"><i class="fa fa-times"></i></span>取消</button>
                            </p>
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
<script type="text/javascript" src="/assets/js/wangEditor.min.js"></script>
<script src="/js/report/addHtml.js"></script>
<!-- end: JavaScript-->
</body>
</html>