<!DOCTYPE html>
<html lang="en">
<head>

    <!-- Basic -->
    <meta charset="UTF-8" />

    <title>慧数招商后台登录</title>

    <!-- Mobile Metas -->
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <#include "/common/link.ftl">
    <style>
        footer {
            display: none;
        }
    </style>

    <!-- end: CSS file-->
    <script type="text/javascript" src="/js/security.js"></script>
    <#include "/common/script.ftl">
    <!-- Head Libs -->
</head>

<body>
<!-- Start: Content -->
<div class="container-fluid content">
    <div class="row">
        <!-- Main Page -->
        <div id="content" class="col-sm-12 full">
            <div class="row">
                <div class="login-box">
                    <div class="panel">
                        <div class="panel-body">
                            <div class="header bk-margin-bottom-20 text-center" style="background: #3BBFB4;">
                                <img src="/assets/img/logo.png" class="img-responsive" style="margin: auto;padding: 20px;" alt="" />
                            </div>
                            <form id="signupLogin" class="form-horizontal login" method="post">
                                <input type="hidden" id="password" name="password">
                                <div class="bk-padding-left-20 bk-padding-right-20">
                                    <div class="form-group">
                                        <div class="input-group input-group-icon">
                                            <input type="text" class="form-control bk-radius"
                                                   id="username" name="username" placeholder="输入用户名" required/> <span
                                                class="input-group-addon"> <span class="icon">
														<i class="fa fa-user"></i>
												</span>
												</span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group input-group-icon">
                                            <input type="password" class="form-control bk-radius"
                                                   id="userPassword" name="userPassword" placeholder="输入密码" required/> <span
                                                class="input-group-addon"> <span class="icon">
														<i class="fa fa-key"></i>
												</span>
												</span>
                                        </div>
                                    </div>
                                    <div class="col-sm-4 text-right">
                                        <button type="button" class="btn btn-primary hidden-xs"  onclick="doLogin()">登陆</button>
                                    </div>
                                </div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Main Page -->
</div>
</div>
<!--/container-->
<script type="text/javascript">
    function doLogin() {
        $.ajax({
            url: "/apis/security/generateKey.do",
            dataType: "json",
            success: function (response) {
                if (response.success) {
                    var exponent = response.data.publicKeyExponent;
                    var modulus = response.data.publicKeyModulus;
                    RSAUtils.setMaxDigits(200);
                    var key = new RSAUtils.getKeyPair(exponent, "", modulus);
                    var password = $("#userPassword").val();
                    var encrypedPwd = RSAUtils.encryptedString(key, password);
                    $.ajax({
                        type: 'post',
                        url: "/apis/login.do",
                        async: false,
                        data: {username: $("#username").val(), password: encrypedPwd, type: 'admin'},
                        success: function (response) {
                            layui.use('layer', function(){
                                var layer = layui.layer;
                                if(response.success){
                                    window.location.href="/apis/back/admin/globalManagement.json";
                                }else{
                                    layer.alert(response.message);
                                }
                            });
                        }
                    });
                }
            }
        });
    }
</script>
</body>
</html>