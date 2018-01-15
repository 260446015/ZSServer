var _id;
function addData(id) {
	_id=id;
	 $.ajax({
	        type : "get",
	        contentType : "application/json",
	        async:false,
	        url : "/apis/user/hahahah.json",
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
    addData();
})
$(".btn-danger").on("click",function(){
    window.location.href="/apis/user/userPark.html";
})