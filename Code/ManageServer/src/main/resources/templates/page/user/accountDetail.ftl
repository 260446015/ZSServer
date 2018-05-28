<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Basic -->
    <meta charset="UTF-8"/>
    <title>慧数招商—添加账户园区</title>
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <!-- Favicon and touch icons -->
<#include "/common/link.ftl">
    <!-- Head Libs -->
    <script src="/assets/plugins/modernizr/js/modernizr.js"></script>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        label {font-size:12px;cursor:pointer;}
        label i {font-size:12px;font-style:normal;display:inline-block;width:12px;height:12px;text-align:center;line-height:12px;color:#fff;vertical-align:middle;margin:-2px 2px 1px 5px;border:#2489c5 1px solid;}
        input[type="checkbox"]:checked + i{background:#2489c5;}
        input[type="checkbox"]:disabled + i {border-color:#ccc;}
        input[type="checkbox"]:checked:disabled + i {background:#ccc;}
    </style>
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
                    <h2>用户园区详情</h2>
                </div>
            </div>
            <!-- End Page Header -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-default bk-bg-white">
                        <div class="panel-body">
                            <div class="panel-heading bk-bg-white text-center" style="margin-top: 30px">
                                <div class="row">
                                    <div class="col-xs-8 text-left bk-vcenter">
                                        <h2 class="bk-margin-off">账户权限</h2>
                                    </div>
                                    <div class="panel-body bk-padding-off-top bk-padding-off-bottom bk-noradius">
                                        <table class="table table-bordered table-striped mb-none" id="amount_table">
                                            <thead>
                                            <tr>
                                                <th>账户</th>
                                                <th>角色</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody id="userList">
                                            <#list userList as list>
                                                <tr class="gradeX">
                                                    <input type="hidden" value="${list.id}"/>
                                                    <td>${list.userAccount}</td>
                                                    <td>${list.role.rolename}</td>
                                                    <td class="actions"><a href="javascript:void(0);" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
                                                        <a href="javascript:void(0);" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a><a href="javascript:void(0);" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
                                                        <a href="javascript:void(0);" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
                                                    </td>
                                                </tr>
                                            </#list>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                            <div class="panel-heading bk-bg-white text-center" style="margin-top: 30px">
                                <div class="row">
                                    <div class="col-xs-8 text-left bk-vcenter">
                                        <h2 class="bk-margin-off">角色列表</h2>
                                    </div>
                                    <div class="panel-body bk-padding-off-top bk-padding-off-bottom bk-noradius">
                                        <table class="table table-bordered table-striped mb-none" id="amount_table">
                                            <thead>
                                            <tr>
                                                <th>角色</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody id="amount">
                                            <#list roleList as role>
                                            <tr class="gradeX">
                                                <#list role.permissions as permission>
                                                    <input type="hidden" value="${permission.permissionName}"/>
                                                </#list>
                                                <td>${role.rolename}</td>
                                                <td class="actions">
                                                    <input type="hidden" value="${role.id}"/>
                                                    <a style="cursor: pointer" data-toggle="modal" data-target="#roleDetail">查看详情</a>
                                                </td>
                                            </tr>
                                            </#list>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                            <div class="panel-heading bk-bg-white text-center" style="margin-top: 30px">
                                <div class="row">
                                    <div class="col-xs-8 text-left bk-vcenter">
                                        <h2 class="bk-margin-off">权限列表</h2>
                                    </div>
                                    <div class="panel-body bk-padding-off-top bk-padding-off-bottom bk-noradius">
                                        <table class="table table-bordered table-striped mb-none" id="amount_table">
                                            <thead>
                                            <tr>
                                                <th>权限</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody id="permission">
                                            <#list permissionList as permission>
                                            <tr class="gradeX">
                                                <input type="hidden" value="${permission.id}"/>
                                                <td>${permission.permissionName}</td>
                                                <td class="actions"><a href="javascript:void(0);" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
                                                    <a href="javascript:void(0);" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a><a href="javascript:void(0);" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
                                                    <a href="javascript:void(0);" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
                                                </td>
                                            </tr>
                                            </#list>
                                            </tbody>
                                        </table>
                                    </div>
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

<!-- end: JavaScript-->
<script src="/js/user/accountDetail.js"></script>
<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">开始演示模态框</button>
<!-- 模态框（Modal） -->
<!-- 模态框（Modal） -->
<div class="modal fade" id="roleDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div class="modal-body" id="roleBody">
                <ul>
                <#list permissionList as permission>
                    <li>${permission.permissionName}<label><input type="checkbox" value="${permission.id}"></label><span></span></li>
                </#list>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="updateRole">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>