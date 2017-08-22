<html>
<head>
    <link rel="shortcut icon" href="/img/favicon.ico">
    <script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/js/security.js"></script>
    <style>
        fieldset {
            margin-top: 150px;
            width: 300px;
            height: 300px;
            margin-left: auto;
            margin-right: auto;
            border-radius: 5px;
        }

        fieldset > div {
            margin-top: 20px;
        }

        lable {
            display: inline-block;
            width: 60px;
            text-align: right;
            margin-right: 20px;
        }

        input[type='text'], input[type='password'] {
            width: 170px;
        }
    </style>
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
</body>
</html>
<script type="text/javascript">

    function doLogin() {
        $.ajax({
            url: "/apis/security/generateKey.do",
            dataType: "json",
            success: function (response) {
                if (response.success) {
                    console.log("获取到加密钥匙");
                    var exponent = response.data.publicKeyExponent;
                    var modulus = response.data.publicKeyModulus;
                    RSAUtils.setMaxDigits(200);
                    var key = new RSAUtils.getKeyPair(exponent, "", modulus);
                    var password = $('input[type=password]').val();
                    var encrypedPwd = RSAUtils.encryptedString(key, password);
                    $.ajax({
                        type: 'post',
                        url: "/apis/login.do",
                        async: false,
                        data: {username: $('input[name=username]').val(), password: encrypedPwd, type: 'user'},
                        success: function (response) {
	                        if(response.message!=null){
	                        	alert(response.message);
	                        }else{
	                       		alert(response.data);
	                       		window.location.href="http://localhost:8000/intelligence"
	                        }
                        	console.log(response);
                        }
                    });
                }
            }
        });
    }
</script> 