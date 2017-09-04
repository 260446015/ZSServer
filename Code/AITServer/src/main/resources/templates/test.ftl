<html>
	<head>
		<script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
	    <script type="text/javascript" src="/js/security.js"></script>
	    <script type="text/javascript" src="/js/jquery.tablesort.js"></script>
	    <script type="text/javascript" src="/js/ajaxfileupload.js"></script>
	</head>
	<body>
		<form>
		    <fieldset>
		        <legend>发送手机验证码</legend>
		        <div>
		            <lable>手机号:</lable>
		            <input type="text" name="telphone1"/></div>
		        <div>
		            <lable>类型:</lable>
		            <input name="type1" type="radio" value="findPassword" />找回密码
		            <input name="type1" type="radio" value="register" />注册</div>
		        <div>
		            <lable>账号(找回密码的时候需要携带):</lable>
		            <input type="text" name="userAccount1"/></div>
		        <div>
		            <lable>提交:</lable>
		            <input type="button" value="发送" onclick="doCaptcha()"/>
		        </div>
		    </fieldset>
		</form>
		<form>
		    <fieldset>
		        <legend>注册（*为必填）</legend>
		        <div>
		            <lable>昵称*（登陆账号）:</lable>
		            <input type="text" name="userAccount2"/></div>
		        <div>
		            <lable>手机号*:</lable>
		            <input type="text" name="telphone2"/></div>
		            <div>
		            <lable>验证码*:</lable>
		            <input type="text" name="captcha2"/></div>
		            <div>
		            <lable>邮箱*:</lable>
		            <input type="text" name="userEmail2"/></div>
		            <div>
		            <lable>所在园区*:</lable>
		            <input type="text" name="park2"/></div>
		            <div>
		            <lable>所在公司:</lable>
		            <input type="text" name="company2"/></div>
		            <div>
		            <lable>所在部门:</lable>
		            <input type="text" name="department2"/></div>
		            <div>
		            <lable>名片:</lable>
                    <input type="file" id="file" name="file" onchange="pushImg();"/> 
                    <input type="hidden" id="pic" name="pic" /></div>
		        <div>
		            <lable>提交:</lable>
		            <input type="button" value="注册" onclick="doRegister()"/>
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
		<form>
		    <fieldset>
		        <div>
		            <lable>查看个人信息(
					   <#if Session.isLogin?exists>  
							${Session.isLogin}   
						</#if>  
					):</lable>
		            <input type="button" value="查看" onclick="my()"/>
		            <input type="button" value="登出" onclick="logout()"/>
		            <input type="button" value="查看园区疑似外流（测权限用）" onclick="haha()"/>
		        </div>
		    </fieldset>
		</form>
		<form>
		    <fieldset>
		        <legend>找回密码</legend>
		        <div>
		            <lable>用户名:</lable>
		            <input type="text" name="username3"/></div>
		        <div>
		            <lable>手机号:</lable>
		            <input type="text" name="telphone3"/></div>
		            <div>
		            <lable>验证码:</lable>
		            <input type="text" name="captcha3"/></div>
		        <div>
		            <lable>新密码:</lable>
		            <input type="password" name="newpassword3"/></div>
		        <div>
		            <lable>提交:</lable>
		            <input type="button" value="提交" onclick="dofind()"/>
		        </div>
		    </fieldset>
		</form>
		<form>
		    <fieldset>
		        <legend>修改密码</legend>
		        <div>
		            <lable>原密码:</lable>
		            <input type="text" name="oldpassword"/></div>
		        <div>
		            <lable>新密码:</lable>
		            <input type="password" name="newpassword"/></div>
		        <div>
		            <lable>确认密码:</lable>
		            <input type="password" name="newpassword2"/></div>
		        <div>
		            <lable>提交:</lable>
		            <input type="button" value="修改" onclick="dochange()"/>
		        </div>
		    </fieldset>
		</form>
	</body>
</html>
<script type="text/javascript">
	function doCaptcha(){
	var obj={telphone: $('input[name=telphone1]').val(), userAccount: $('input[name=userAccount1]').val(), type:  $('input[name="type1"]:checked').val()};
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
	var obj={telphone: $('input[name=telphone2]').val(), 
			 userAccount: $('input[name=userAccount2]').val(),
			 userEmail: $('input[name=userEmail2]').val(),
			 park: $('input[name=park2]').val(),
			 company: $('input[name=company2]').val(),
			 department: $('input[name=department2]').val(),
			 captcha: $('input[name=captcha2]').val(),
			 userType: 'user'};
		$.ajax({
            type: 'post',
            url: "/apis/register.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (response) {
                if(response.message!=null){
                	alert(response.message);
                }else{
               		alert(response.data);
                }
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
                        }
                    });
                }
            }
        });
    }
    function my(){
		$.ajax({
            type: 'get',
            url: "/apis/user/findMyInformation.json",
            async: false,
            success: function (response) {
                if(response.message!=null){
                	alert(response.message);
                }else{
	                var description = "";  
				    for (var i in response.data) {  
				        description += i + " = " + response.data[i] + "\n";  
				    }  
				    alert(description); 
                }
            }
        });
	}
	function logout(){
		$.ajax({
            type: 'get',
            url: "/apis/logOut.do",
            async: false,
            success: function (response) {
                if(response.message!=null){
                	alert(response.message);
                }else{
               		alert(response.data);
                }
            }
        });
	}
	 function haha(){
		$.ajax({
            type: 'post',
            url: "/apis/warning/getBusinessOutflowList.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify({a:1}),
            success: function (response) {
                if(response.message!=null){
                	alert(response.message);
                }else{
	                var description = "";  
				    for (var i in response.data) {  
				        description += i + " = " + response.data[i] + "\n";  
				    }  
				    alert(description); 
                }
            }
        });
	}
	function dofind(){
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
                    var password = $('input[name=captcha3]').val();
                    var encrypedPwd = RSAUtils.encryptedString(key, password);
                    var obj={telphone: $('input[name=telphone3]').val(), 
							 userAccount: $('input[name=username3]').val(),
							 captcha: $('input[name=captcha3]').val(),
							 newPassword:encrypedPwd};
					$.ajax({
			            type: 'post',
			            url: "/apis/findPassword.json",
			            async: false,
			            contentType: 'application/json',
			            data: JSON.stringify(obj),
			            success: function (response) {
			                if(response.message!=null){
			                	alert(response.message);
			                }else{
			               		alert(response.data);
			                }
			            }
			        });
                }
            }
        });
	}
	function dochange(){
		var newpassword = $('input[name=newpassword]').val();
		var newpassword2 = $('input[name=newpassword2]').val();
		if(newpassword!=newpassword2){
			alert("两次密码不一致");
		}
		if(newpassword==newpassword2){
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
	                    var password = $('input[name=oldpassword]').val();
	                    var encrypedPwd = RSAUtils.encryptedString(key, password);
	                    var ennewpassword = RSAUtils.encryptedString(key, newpassword);
	                    var obj={oldPassword: encrypedPwd, 
								 newPassword: ennewpassword};
						$.ajax({
				            type: 'post',
				            url: "/apis/user/modifyPassword.json",
				            async: false,
				            contentType: 'application/json',
				            data: JSON.stringify(obj),
				            success: function (response) {
				                if(response.message!=null){
				                	alert(response.message);
				                }else{
				               		alert(response.data);
				                }
				            }
				        });
	                }
            	}
        	});
        }
	}
	function pushImg(){
	    var picpath="";
	    $.ajaxFileUpload({
	    　　　　url : "/apis/imageUpload.do",
           fileElementId:'file',
           dataType : "json",
           success: function(response){
          	 alert(response.message);
            //$("#picshow").append("<img src='http://localhost:8082/img/tempImg/"+data.fileName+"' width='80px' height='80px'/>")
           //alert('success');
            //picpath=$("#pic").val()+data.fileName+",";
            //$("#pic").val(picpath);
           },
           error: function(response)
           {
              alert(response.message);
              console.log(response);
            }
           }
		);
	}
	
</script> 