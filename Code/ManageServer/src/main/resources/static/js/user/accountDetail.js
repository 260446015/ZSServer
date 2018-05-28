$(function () {
    $('#roleDetail').on('show.bs.modal', function (event) {
        var btnThis = $(event.relatedTarget); //触发事件的按钮
        var modal = $(this);  //当前模态框
        var role = btnThis.parent().prev().text();
        $("#myModalLabel").html(role);
        var roleId = btnThis.prev().val();
        var names = btnThis.parent().siblings("input");
        var roleBody = $("#roleBody").find("li");
        roleBody.find("span").html('<input type="hidden" value="'+roleId+'"/>')
        modal.find("input").attr("checked",false);
        names.each(function () {
            var permissionName = $(this).val();
            roleBody.each(function () {
                if(permissionName == $(this).text()){
                    $(this).find("input").attr("checked",true);
                }
            })
        })

        // var modalId = btnThis.data('id');   //解析出data-id的内容
        // var content = btnThis.closest('tr').find('td').eq(2).text();
        // modal.find('.content').val(content);
        // var rolename = btnThis.data('ro')
    });
//saveUserRolePer.json
    $("#updateRole").on("click",function () {
        var roleBody = $("#roleBody").find("li");
        var rolePermissions = new Array();
        roleBody.each(function () {
            if($(this).find("input").eq(0).attr("checked")){
                var permissionId = $(this).find("input").val();
                var roleId = $(this).find("input").eq(1).val();
                var data = {"permissionId":permissionId,"roleId":roleId};
                rolePermissions.push(data);
            }
        })
        $.ajax({
            type:'post',
            contentType:'application/json',
            data:JSON.stringify(rolePermissions),
            url:'/apis/user/saveUserRolePer.json',
            success:function () {
                window.location.reload();
            }
        })
    })
    $(".edit-row").on("click",function(){
        $(this).parents('.gradeX').children( 'td' ).each(function( i ) {
            var $this = $( this );
            if ( $this.hasClass('actions') ) {
                $this.find("a").each(function(){
                    if($(this).hasClass("hidden")){
                        $(this).removeClass("hidden");
                    }else{
                        $(this).addClass("hidden");
                    }
                })
            } else {
                if(i == 1){
                    $this.html( '<select class="selectpicker"><option value="1">admin</option>\n' +
                        '    <option value="2">system</option>\n' +
                        '    <option value="3">root</option></select>' );
                }
            }
        });
    })
    $(".cancel-row").on("click",function(){
        window.location.reload();
    })
    $(".save-row").on("click",function(i){
        var userElements = $("#userList").find(".gradeX");
        var dataArr = [];
        userElements.each(function () {
            var userElement = $(this);
            if(userElement.is(':has(select)')) {
                var userId = userElement.find("input").val();
                var roleId = userElement.find("select").val();
                var data = {"userId": userId, "roleId": roleId};
                dataArr.push(data);
            }
        })
        $.ajax({
            type:'post',
            contentType:'application/json',
            url:'/apis/user/updateUserRole',
            data:JSON.stringify(dataArr),
            success:function (res) {
                console.log(res.data);
                if(res.data == true){
                    window.location.reload();
                }
            }
        })
    })
})

