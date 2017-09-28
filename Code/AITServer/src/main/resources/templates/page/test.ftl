<html>
	<head>
		<script type="text/javascript" src="/assets/js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="/assets/js/security.js"></script>
		<script type="text/javascript" src="/assets/js/jquery.tablesort.js"></script>
		<script type="text/javascript" src="/assets/js/ajaxfileupload.js"></script>
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
                    <img src="/images/a2e552bb-523d-44de-b57a-e6c41c693d77.jpg"  width="149" height="149"/> 
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
		            <input type="button" value="查看园区预警数量（测权限用）" onclick="haha()"/>
		            <input type="button" value="查看后台园区管理列表" onclick="hahaha()"/>
		            <a href="../apis/oauth/loginOpenEye.json" target="_blank">*****</a>
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
		<form>
		    <fieldset>
		        <legend>修改邮箱</legend>
		        <div>
		            <lable>新邮箱:</lable>
		            <input type="text" name="email"/></div>
		        <div>
		            <lable>提交:</lable>
		            <input type="button" value="修改" onclick="doemailchange()"/>
		        </div>
		    </fieldset>
		</form>
		<form>
		    <fieldset>
		        <legend>添加正式用户</legend>
		        <div>
		            <lable>真实姓名:</lable>
		            <input type="text" name="realName"/></div>
		        <div>
		            <lable>手机号*:</lable>
		            <input type="text" name="realPhone"/></div>
		            <div>
		            <lable>邮箱*:</lable>
		            <input type="text" name="realEmail"/></div>
		            <div>
		            <lable>所在园区*:</lable>
		            <input type="text" name="realPark"/></div>
		            <div>
		            <lable>所在公司:</lable>
		            <input type="text" name="realCompany"/></div>
		            <div>
		            <lable>所在部门:</lable>
		            <input type="text" name="realDepartment"/></div>
		            <div>
		            <lable>职务:</lable>
		            <input type="text" name="realJob"/></div>
		        <div>
		            <lable>提交:</lable>
		            <input type="button" value="添加" onclick="doadd()"/>
		        </div>
		    </fieldset>
		</form>
        <form>
            <fieldset>
                <legend>添加需求</legend>
                <div>
                    <lable>企业名:</lable>
                    <input type="text" name="a"/></div>
                <div>
                    <lable>所属企业*:</lable>
                    <input type="text" name="b"/></div>
                <div>
                    <lable>企业标签*:</lable>
                    <input type="text" name="c"/></div>
                <div>
                    <lable>企业关系:</lable>
                    <input type="text" name="z"/></div>
                <div>
                    <lable>企业关系备注:</lable>
                    <input type="text" name="d"/></div>
                <div>
                    <lable>企业状态:</lable>
                    <input type="text" name="e"/></div>
                <div>
                    <lable>负责人:</lable>
                    <input type="text" name="f"/></div>
                <div>
                    <lable>招商状态:</lable>
                    <input type="text" name="g"/></div>
                <div>
                    <lable>招商备注:</lable>
                    <input type="text" name="h"/></div>
                </div>
                <input type="button" value="添加" onclick="pooladd()"/>
                <input type="button" value="查看我的需求池" onclick="poolList()"/>
                <input type="button" value="获取当前园区需求池中企业标签" onclick="labelList()"/>
                <input type="button" value="获取我的需求池中企业标签" onclick="labelMyList()"/>
                <input type="button" value="添加我的需求池中企业标签" onclick="addLabel()"/>
                <input type="button" value="删除我的需求池中企业标签" onclick="deleteLabel()"/>
            </fieldset>
        </form>
	</body>
</html>
<script type="text/javascript">
	function labelList(){
		$.ajax({
            url: "/apis/label/getLabel.json",
            success: function (response) {
                if(response.message!=null){
                    alert(response.message);
                }else{
                    alert(response.data);
                }
            }
        });
	}
	function labelMyList(){
	$.ajax({
            url: "/apis/label/getMyLabel.json",
            success: function (response) {
                if(response.message!=null){
                    alert(response.message);
                }else{
                    alert(response.data);
                }
            }
        });
	}
	function addLabel(){
	$.ajax({
            url: "/apis/label/addMyLabel.json",
            contentType: 'application/json',
            data: {name: '中国500强'},
            success: function (response) {
                if(response.message!=null){
                    alert(response.message);
                }else{
                    alert(response.data);
                }
            }
        });
	}
    function deleteLabel(){
    $.ajax({
            url: "/apis/label/dropMyLabel.json",
            contentType: 'application/json',
            data: {id: 1},
            success: function (response) {
                if(response.message!=null){
                    alert(response.message);
                }else{
                    alert(response.data);
                }
            }
        });
    }
    
	function poolList() {
        var datalist= new Array();
        datalist.push("大数据");
        datalist.push("未入住");
        datalist.push("全部");
        $.ajax({
            type: 'post',
            url: "/apis/pool/getMyCompanyList.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify({msg: datalist}),
            success: function (response) {
                if(response.message!=null){
                    alert(response.message);
                }else{
                    alert(response.data);
                }
            }
        });
    }
	function pooladd() {
        var obj={name: $('input[name=a]').val(),
            fatherName: $('input[name=b]').val(),
            label: $('input[name=c]').val(),
            relation: $('input[name=z]').val(),
            relationRemark: $('input[name=d]').val(),
            companyStatus: $('input[name=e]').val(),
            responsiblePerson: $('input[name=f]').val(),
            investmentRemark: $('input[name=h]').val(),
            investmentStatus:$('input[name=g]').val()};
        $.ajax({
            type: 'post',
            url: "/apis/pool/addPoolCompany.json",
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
	function doadd(){
		var obj={name: $('input[name=realName]').val(), 
				telphone: $('input[name=realPhone]').val(), 
				 userEmail: $('input[name=realEmail]').val(),
				 park: $('input[name=realPark]').val(),
				 company: $('input[name=realCompany]').val(),
				 department: $('input[name=realDepartment]').val(),
				 jbo: $('input[name=realJob]').val(),
				 time:'一年',
				 userType: 'user'};
		$.ajax({
            type: 'post',
            url: "/apis/back/garden/addParkAccount.json",
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
                        data: {username: $('input[name=username]').val(), password: encrypedPwd, type: 'admin'},
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
            type: 'get',
            url: "/apis/warning/getGardenWarningCout.json",
            async: false,
            contentType: 'application/json',
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
	function doemailchange(){
	    var iemail = $('input[name=email]').val();
	    $.ajax({
            type: 'get',
            url: "/apis/user/modifyEmail.json",
            async: false,
            contentType: 'application/json',
            data: {email: iemail},
            success: function (response) {
                if(response.message!=null){
                	alert(response.message);
                }else{
               		alert(response.data);
                }
            }
        });
	}
	function hahaha(){
		var datalist= new Array();
		datalist.push("全部");         
		datalist.push("全部");         
		datalist.push("全部");         
	    $.ajax({
            type: 'post',
            url: "/apis/back/garden/getGardenList.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify({msg: datalist}),
            success: function (response) {
                if(response.message!=null){
                	alert(response.message);
                }else{
               		alert(response.data);
                }
            }
        });
	}
</script> 