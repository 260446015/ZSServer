<html>
	<head>
		<script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
	    <script type="text/javascript" src="/js/security.js"></script>
	</head>
	<body>
		<form>
		    <fieldset>
		        <legend>发送手机验证码</legend>
		        <div>
		            <lable>手机号:</lable>
		            <input type="text" name="telphone"/></div>
		        <div>
		            <lable>类型:</lable>
		            <input name="type" type="radio" value="findPassword" />找回密码
		            <input name="type" type="radio" value="register" />注册</div>
		        <div>
		            <lable>账号(找回密码的时候需要携带):</lable>
		            <input type="text" name="userAccount"/></div>
		        <div>
		            <lable>提交:</lable>
		            <input type="button" value="验证码" onclick="doCaptcha()"/>
		        </div>
		    </fieldset>
		</form>
		<form>
		    <fieldset>
		        <legend>注册</legend>
		        <div>
		            <lable>手机号:</lable>
		            <input type="text" name="telphone"/></div>
		        <div>
		            <lable>类型:</lable>
		            <input name="type" type="radio" value="findPassword" />找回密码
		            <input name="type" type="radio" value="register" />注册</div>
		        <div>
		            <lable>账号(找回密码的时候需要携带):</lable>
		            <input type="text" name="userAccount"/></div>
		        <div>
		            <lable>提交:</lable>
		            <input type="button" value="验证码" onclick="doRegister()"/>
		        </div>
		    </fieldset>
		</form>
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
	function doCaptcha(){
	var obj={telphone: $('input[name=telphone]').val(), userAccount: $('input[name=userAccount]').val(), type:  $('input[name="type"]:checked').val()};
		$.ajax({
            type: 'post',
            url: "/apis/getPhoneCaptcha.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (response) {
                if(response.message!=null){
                	alert(response.message);
                }else{
               		alert(response.data);
                }
            	console.log(response);
            }
        });
	}
	function doRegister(){
	var obj={telphone: $('input[name=telphone]').val(), userAccount: $('input[name=userAccount]').val(), type:  $('input[name="type"]:checked').val()};
		$.ajax({
            type: 'post',
            url: "/apis/getPhoneCaptcha.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (response) {
                if(response.message!=null){
                	alert(response.message);
                }else{
               		alert(response.data);
                }
            	console.log(response);
            }
        });
	}
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
	                        }
                        	console.log(response);
                        }
                    });
                }
            }
        });
    }
</script> 