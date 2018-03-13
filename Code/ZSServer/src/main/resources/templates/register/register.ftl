<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-申请试用界面</title>
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
     <div class="panel-use" >
        <div class="panel-heading">
            <h3 class="panel-title">申请试用</h3>
        </div>
        <div class="form-group">
            <label for="username" class="col-sm-4 textcolor">姓名</label>
            <div class="col-sm-8 mt10">
                <input type="text" class="form-control" placeholder="请输入你的姓名" id="name" name="username">
            </div>
        </div>
        <div class="form-group">
            <label for="address" class="col-sm-4 textcolor">所在城市</label>
            <div class="col-sm-8 mt10">
                <input type="text" class="form-control" placeholder="请输入你所在城市" id="address">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-4 textcolor">联系方式</label>
            <div class="col-sm-8 mt10">
                <input type="text" class="form-control" placeholder="请输入你的联系方式" id="phone">
            </div>
        </div>
        <div class="form-group">
            <label for="verificationCode" class="col-sm-4 textcolor">验证码</label>
            <div class="col-sm-4 mt10">
                <input type="text" id="captcha" class="form-control" placeholder="请输入验证码">
            </div>
            <div style="display:inline">
                <button type='button' class="btn btn-blue mt10  btns" onclick="getPhone(this)">获取验证码</button>   
              </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-4 textcolor">邮箱</label>
            <div class="col-sm-8 mt10">
                <input type="text" class="form-control" id="email" name="email" placeholder="请输入你的邮箱">
            </div>
        </div>
        <div class="form-group">
            <label for=" position" class="col-sm-4 textcolor">职位</label>
            <div class="col-sm-8 mt10">
                <input type="text" class="form-control" id="position" name="position" placeholder="请输入你的职位">
            </div>
        </div>
        <div class="form-group">
            <label for="units" class="col-sm-4 textcolor">所在单位</label>
            <div class="col-sm-8 mt10">
                <input type="text" class="form-control" id="units" name="units" placeholder="请输入你的所在单位">
            </div>
        </div>
        <div class="form-group">
            <label for="garden" class="col-sm-4 textcolor">所在园区/地域</label>
            <div class="col-sm-8 mt10">
                <input type="text" class="form-control" id="garden" name="garden" placeholder="请输入你的所在园区/地域">
            </div>
        </div>
        <div class="form-group">
            <label for="develop" class="col-sm-4 textcolor">主要发展单位</label>
            <div class="col-sm-8 mt10">
                <input type="text" class="form-control" id="develop" name="develop" placeholder="请输入主要发展单位">
            </div>
        </div>
        <div class="form-group">
            <div>
                  <button type="submit" class="btn btn-blue btn-ind mt10" onclick="addRegister()">申请试用</button>
            </div>
        </div>
    </div>
     
   <!-- <#include  "/common/footer.ftl"/>-->
	<#include  "/common/script.ftl"/>
	<script type="text/javascript" src="/vendor/jquery.min.js"></script>
	<script type="text/javascript" src="/vendor/security.js"></script>
	<script type="text/javascript" src="/js/login/register.js"></script>
</body>
</html>