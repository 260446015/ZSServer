<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8" />

<title>慧数招商登录</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<script type="text/javascript" src="/vendor/jquery.min.js"></script>
<script type="text/javascript" src="/vendor/security.js"></script>
</head>

<body>
	<form>
	    <fieldset>
	        <legend>登录</legend>
	        <div>
	            <lable>用户名:</lable>
	            <input type="text" name="username"/></div>
	        <div>
	            <lable>密码:</lable>
	            <input type="password" name="password"/></div>
	        <div>
	            <lable>记住我:</lable>
	            <input id="rememberMe" type="checkbox" name="rememberMe"/></div>
	        <div>
	            <lable>提交:</lable>
	            <input type="button" value="提交" onclick="doLogin()"/>
	        </div>
	    </fieldset>
	</form>
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
	                        data: {username: $("#username").val(), password: encrypedPwd,rememberMe: $("#rememberMe").val()},
	                        success: function (response) {
		                        layui.use('layer', function(){
								  var layer = layui.layer;
								  if(response.success){
								 		window.location.href="/indusMap/industryMap.html";
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