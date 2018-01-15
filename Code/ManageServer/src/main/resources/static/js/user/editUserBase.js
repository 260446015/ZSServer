$("#user_item").addClass("active nav-expanded ");
$("#user_park_item").addClass("active");
var _id;
var _park_id;
function addParkId(id) {
    _park_id = id;
}
function addData(id) {
	_id=id;
	 $.ajax({
	        type : "get",
	        contentType : "application/json",
	        async:false,
	        url : "/apis/user/getUserBase.json",
	        data : {id:id},
	        success : function(res) {
	            if(res.success){
	            	var enter=res.data;
	            	 $("input[name='userAccount']").val(enter.userAccount);
            	    $("input[name='realName']").val(enter.realName);
            	    $("input[name='telphone']").val(enter.telphone);
            	    $("input[name='userEmail']").val(enter.userEmail);
            	    $("input[name='userJob']").val(enter.userJob);
            	    $("input[name='userDepartment']").val(enter.userDepartment);
            	    $("input[name='userPark']").val(enter.userPark);
            	    $("input[name='imageUrl']").val(enter.imageUrl);
            	    $("input[name='userType'][value='"+enter.userType+"']").attr('checked','true');
            	    if(enter.userType=='admin'){
            	        $("#select").attr("disabled","disabled");
            	        $("#select").html("<option value=\"-1\">无限制</option>")
            	    }else if(enter.userType=='user'){
            	        $("#select").attr("disabled",false);
            	        $("#select").html("<option value=\"12\">一年</option><option value=\"24\">两年</option><option value=\"36\">三年</option>");
            	    }else{
            	        $("#select").attr("disabled","disabled");
            	        $("#select").html("<option value=\"1\">一个月</option>");
            	    }
	            }else{
	                layer.msg(res.message, {icon: 2});
	            }
	        }
	    });
}
$(":radio").click(function(){
    if($(this).val()=='admin'){
        $("#select").attr("disabled","disabled");
        $("#select").html("<option value=\"-1\">无限制</option>")
    }else if($(this).val()=='user'){
        $("#select").attr("disabled",false);
        $("#select").html("<option value=\"12\">一年</option><option value=\"24\">两年</option><option value=\"36\">三年</option>");
    }else{
        $("#select").attr("disabled","disabled");
        $("#select").html("<option value=\"1\">一个月</option>");
    }
});
$(".btn-success").on("click",function(){
    etitData();
})
$(".btn-danger").on("click",function(){
    if(_id==null){
        window.location.href="/apis/user/findParkInformation.html?id="+_park_id;
    }else{
        layer.confirm('直接离开将会失去修改内容，确认离开？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            window.location.href="/apis/user/findParkInformation.html?id="+_park_id;
        });
    }
})
function etitData() {
    var _userAccount = $("input[name='userAccount']").val();
    var _realName = $("input[name='realName']").val();
    var _telphone = $("input[name='telphone']").val();
    var _userEmail =$("input[name='userEmail']").val();
    var _userJob =$("input[name='userJob']").val();
    var _userDepartment =$("input[name='userDepartment']").val();
    var _imageUrl =$("input[name='imageUrl']").val();
    var _userType = $('input:radio:checked').val();
    var _userTime = $("#select option:selected").val();
    var _userPark = $("input[name='userPark']").val();
    var obj={
            id:_id,userAccount:_userAccount,realName:_realName,telphone:_telphone,userEmail:_userEmail,userJob:_userJob,
            userDepartment:_userDepartment,userPark:_userPark,userType:_userType,userTime:_userTime,mageUrl:_imageUrl
        };
    $.ajax({
        type : "post",
        contentType : "application/json",
        async:false,
        url : "/apis/user/saveUserBase.json",
        data : JSON.stringify(obj),
        success : function(res) {
            if(res.success){
                layer.msg('操作成功', {icon: 1});
                window.location.href="/apis/user/findParkInformation.html?id="+_park_id;
            }else{
                layer.msg(res.message, {icon: 2});
            }
        }
    });
}