function doLogin() {
    $.ajax({
        url: "/security/generateKey.do",
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
                    url: "/login.do",
                    async: false,
                    data: {username: $("#username").val(), password: encrypedPwd},
                    success: function (response) {
					  	if(response.success){
					 		window.location.href="/indusMap/industryMap.html";
                        }else{
                       		alert(response.message);
                        }
                    }
                });
            }
        }
    });
}