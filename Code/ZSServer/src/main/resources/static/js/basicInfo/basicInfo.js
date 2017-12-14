$(function(){
	$("#basicinfoItem").addClass("active");
	$.ajax({  
        url: "/user/findMyInformation.json",  
        async: false,  
        success: function (result) {  
        	if(result.success){
        		$("#userAccount").html(result.data.userAccount);
        		$("#createTime").html(result.data.createTime);
        		$("#name").val(result.data.realName);
        		$("#park").val(result.data.userPark);
        		$("#department").val(result.data.userDepartment);
        		$("#position").val(result.data.userJob);
        		$("#phone").val(result.data.telphone);
        		$("#email").val(result.data.userEmail);
        	}else{
        		new Alert({flag:false,text:result.message,timer:2000}).show();
        	}
        }  
    });  
});
function expandMenu(){
	$("#followItem").addClass("active");
}
function changeInformation(){
	
}
function changePassword(){
	alert(getCode());
}