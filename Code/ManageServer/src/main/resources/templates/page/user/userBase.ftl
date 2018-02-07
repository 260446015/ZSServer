<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Basic -->
    <meta charset="UTF-8" />
    <title>慧数招商—修改用户</title>
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
                    <div class="pull-right">
                        <h2>查看用户信息</h2>
                    </div>
                </div>
            <!-- End Page Header -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-default bk-bg-white">
                        <div class="panel-body">
                            <form action="" method="post" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">账号</label>
                                    <div class="col-md-9">
                                        <input type="text" name="userAccount" class="form-control" placeholder="" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">真实姓名</label>
                                    <div class="col-md-9">
                                        <input type="text" name="realName" class="form-control" placeholder="" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">手机号</label>
                                    <div class="col-md-9">
                                        <input type="text" name="telphone" class="form-control" placeholder="" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">邮箱号</label>
                                    <div class="col-md-9">
                                        <input type="text" name="userEmail" class="form-control" placeholder="" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">所属职务</label>
                                    <div class="col-md-9">
                                        <input type="text" name="userJob" class="form-control" placeholder="" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">所属部门</label>
                                    <div class="col-md-9">
                                        <input type="text" name="userDepartment" class="form-control" placeholder="" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">所属园区</label>
                                    <div class="col-md-9">
                                        <input type="text" name="userPark" class="form-control" placeholder="" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">用户类型</label>
                                    <div class="col-md-9">
                                        <div class="radio-custom radio-inline">
                                            <input type="radio" name="userType" value="admin" disabled="disabled">
                                            <label for="inline-radio1">管理员</label>
                                        </div>
                                        <div class="radio-custom radio-inline">
                                            <input type="radio" name="userType" value="user" disabled="disabled">
                                            <label for="inline-radio2">普通会员</label>
                                        </div>
                                        <div class="radio-custom radio-inline">
                                            <input type="radio" name="userType" value="trial" disabled="disabled">
                                            <label for="inline-radio3">试用会员</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="select">会员开通时长</label>
                                    <div class="col-md-9">
                                        <select id="select" name="select" class="form-control" size="1">
                                            
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="file-multiple-input">人物名片</label>
                                    <div class="col-md-9" id="logo">
                                    </div>
                                </div>
                                <p>
                                    <button class="bk-margin-5 btn btn-labeled btn-normal" type="button">返回上一页</button>
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
<script src="/js/user/editUserBase.js"></script>
<script>
        addData(${id});
        addParkId(${parkId});
        addDisabled();
</script>
<!-- end: JavaScript-->
</body>
</html>