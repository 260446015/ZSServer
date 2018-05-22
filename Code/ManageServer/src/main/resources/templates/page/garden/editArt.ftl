<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Basic -->
    <meta charset="UTF-8" />
    <title>慧数招商—企业库管理</title>
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
                            <li class="active"><i class="fa fa-pencil-square-o"></i>editEnterprise</li>
                        </ol>
                    </div>
                    <div class="pull-right">
                    <#if enterprise??>
                        <h2>编辑企业</h2>
                    <#else>
                        <h2>添加企业</h2>
                    </#if>
                    </div>
                </div>
            <!-- End Page Header -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-default bk-bg-white">
                        <div class="panel-body">
                            <form action="" method="post" enctype="multipart/form-data" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">标题</label>
                                    <div class="col-md-9">
                                        <input type="text" name="title" class="form-control" placeholder="请输入标题">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">简介</label>
                                    <div class="col-md-9">
                                        <input type="text" name="summary" class="form-control" placeholder="请输入简介">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">所属园区</label>
                                    <div class="col-md-9">
                                        <input type="text" name="park" class="form-control" placeholder="请输入园区">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">来源</label>
                                    <div class="col-md-9">
                                        <input type="text" name="source" class="form-control" placeholder="请输入来源">
                                    </div>
                                </div>
                                 <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">时间</label>
                                    <div class="col-md-9">
                                        <input type="text" name="publishTime" class="form-control" placeholder="请输入时间">
                                    </div>
                                </div>
                                 <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">原文链接</label>
                                    <div class="col-md-9">
                                        <input type="text" name="sourceLink" class="form-control" placeholder="请输入原文链接">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">文章地址</label>
                                    <div class="col-md-9">
                                        <input type="text" name="articleLink" class="form-control" placeholder="请输入文章地址">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">产业</label>
                                    <div class="col-md-9">
                                        <input type="text" name="industry" class="form-control" placeholder="请输入产业">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">产业标签</label>
                                    <div class="col-md-9">
                                        <input type="text" name="industryLabel" class="form-control" placeholder="请输入产业标签">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">产业类型</label>
                                    <div class="col-md-9">
                                        <input type="text" name="industryType" class="form-control" placeholder="请输入产业类型">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">作者</label>
                                    <div class="col-md-9">
                                        <input type="text" name="author" class="form-control" placeholder="请输入作者">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">载体</label>
                                    <div class="col-md-9">
                                        <input type="text" name="vector" class="form-control" placeholder="请输入载体">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">维度</label>
                                    <div class="col-md-9">
                                        <input type="text" name="dimension" class="form-control" placeholder="请输入维度">
                                    </div>
                                </div>
                                 <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">内容</label>
                                    <div class="col-md-9">
                                    	<textarea rows="10" cols="20" name="content" class="form-control" placeholder="请输入内容"></textarea>
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
<script src="/assets/js/garden/editArt.js"></script>
<!-- end: JavaScript-->
</body>
</html>