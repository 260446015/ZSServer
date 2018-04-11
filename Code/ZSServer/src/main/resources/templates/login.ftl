<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-登录页面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="慧数招商平台，是一个关于园区产业招商的大数据管理平台">
    <meta name="keywords" content="慧数，招商，慧数招商，招商平台，园区，园区招商，园区招商平台，科技园，产业园，大数据，产业">
    <meta name="author" content="张鑫，慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
    <!-- css共用部分 start -->
    <#include "/common/link.ftl"/>
    <!-- css 共用部分 end -->
</head>
<body class="login-bg">
    <div class="header navbar navbar-trans">
        <div class="navbar-header">
            <a class="navbar-brand hidden-sm" href="javascript:void(0)">
                <img height="32" width="159" src="/images/logo.png" />
            </a>
        </div>
    </div>
    <div class="panel-login">
        <div class="panel-heading">
            <h2 class="panel-title">登录</h2>
        </div>
        <div class="panel-body">
            <form class="form-horizontal" onsubmit="return false;">
                <div class="form-group">
                    <div class="input-icon">
                        <i class="glyphicon glyphicon-user"></i>
                        <input type="text" class="form-control" placeholder="请输入用户名" id="username"/>
                    </div>
                </div>
               <div class="form-group">
                    <div class="input-icon">
                        <i class="glyphicon glyphicon-lock"></i>
                        <input type="password" class="form-control" placeholder="请输入密码" id="userPassword"/>
                    </div>
                </div>
                <div class="form-group">
                    <button class="btn btn-blue btn-login" type="submit" onclick="doLogin()">立即登录</button>
                </div>
                <div class="form-group">
                    <p class="form-control-static">没有账号？<a href="/apis/reg/register.htm"> 申请试用</a></p>
                </div>
            </form>
        </div>
    </div>
	<#include  "/common/script.ftl"/>
	<script type="text/javascript" src="/vendor/jquery.min.js"></script>
	<script type="text/javascript" src="/vendor/security.js"></script>
	<script type="text/javascript" src="/js/login/login.js"></script>
</body>
</html>