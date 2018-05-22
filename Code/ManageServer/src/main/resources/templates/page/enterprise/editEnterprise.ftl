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
                                    <label class="col-md-3 control-label" for="text-input">产业类型</label>
                                    <div class="col-md-9">
                                        <input type="text" name="industryType" class="form-control" placeholder="请输入产业类型">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">公司名</label>
                                    <div class="col-md-9">
                                        <input type="text" name="company" class="form-control" placeholder="请输入公司名">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">公司简称</label>
                                    <div class="col-md-9">
                                        <input type="text" name="companyName" class="form-control" placeholder="请输入公司名">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">公司联系方式</label>
                                    <div class="col-md-9">
                                        <input type="text" name="phone" class="form-control" placeholder="请输入公司联系方式">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">公司邮箱</label>
                                    <div class="col-md-9">
                                        <input type="text" name="email" class="form-control" placeholder="请输入公司邮箱">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">公司网址</label>
                                    <div class="col-md-9">
                                        <input type="text" name="url" class="form-control" placeholder="请输入公司网址">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">公司地址</label>
                                    <div class="col-md-9">
                                        <input type="text" name="address" class="form-control" placeholder="请输入公司地址">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">法定代表</label>
                                    <div class="col-md-9">
                                        <input type="text" name="boss" class="form-control" placeholder="请输入法定代表">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">经营状态</label>
                                    <div class="col-md-9">
                                        <input type="text" name="registerCapital" class="form-control" placeholder="请输入经营状态">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">注册时间</label>
                                    <div class="col-md-9">
                                        <input type="text" name="registerTime" class="form-control" placeholder="请输入注册时间">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">行业领域</label>
                                    <div class="col-md-9">
                                        <input type="text" name="industry" class="form-control" placeholder="请输入行业领域">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">行业标签</label>
                                    <div class="col-md-9">
                                        <input type="text" name="industryLabel" class="form-control" placeholder="请输入行业标签">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">所属新兴战略产业</label>
                                    <div class="col-md-9">
                                        <input type="text" name="industryZero" class="form-control" placeholder="所属新兴战略产业">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">工商注册号</label>
                                    <div class="col-md-9">
                                        <input type="text" name="icRegisterNo" class="form-control" placeholder="请输入工商注册号">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">企业类型</label>
                                    <div class="col-md-9">
                                        <input type="text" name="companyType" class="form-control" placeholder="请输入企业类型">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">组织机构代码</label>
                                    <div class="col-md-9">
                                        <input type="text" name="orgMechanismNo" class="form-control" placeholder="请输入组织机构代码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">经营期限</label>
                                    <div class="col-md-9">
                                        <input type="text" name="businessDate" class="form-control" placeholder="请输入经营期限">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">登记机关</label>
                                    <div class="col-md-9">
                                        <input type="text" name="registerAgency" class="form-control" placeholder="请输入登记机关">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">核准日期</label>
                                    <div class="col-md-9">
                                        <input type="text" name="examineTime" class="form-control" placeholder="请输入核准日期">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">注册地址</label>
                                    <div class="col-md-9">
                                        <input type="text" name="registerAddress" class="form-control" placeholder="请输入注册地址">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">经营范围</label>
                                    <div class="col-md-9">
                                        <input type="text" name="operateScope" class="form-control" placeholder="请输入经营范围">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">所属园区</label>
                                    <div class="col-md-9">
                                        <input type="text" name="park" class="form-control" placeholder="请输入所属园区">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">所属地域</label>
                                    <div class="col-md-9">
                                        <input type="text" name="area" class="form-control" placeholder="请输入所属地域">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">评分</label>
                                    <div class="col-md-9">
                                        <input type="text" name="scoring" class="form-control" placeholder="请输入评分">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">上市公司</label>
                                    <div class="col-md-9">
                                        <input type="text" name="publicCompany" class="form-control" placeholder="请输入上市公司">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">入库时间</label>
                                    <div class="col-md-9">
                                        <input type="text" name="createTime" class="form-control" placeholder="请输入入库时间">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">更新时间</label>
                                    <div class="col-md-9">
                                        <input type="text" name="updateTime" class="form-control" placeholder="请输入更新时间">
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
<script src="/assets/js/enterprise/editEnterprise.js"></script>
<script>
    <#if enterprise??>
        addData(${enterprise});
    </#if>
</script>
<!-- end: JavaScript-->
</body>
</html>