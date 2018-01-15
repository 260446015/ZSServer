$("#user_item").addClass("active nav-expanded ");
$("#user_park_item").addClass("active");
var _park;
var _park_id;
function information(id) {
    $.ajax({
        async: false,
        url: "/apis/park/findParkInformation.json",
        data: {id:id},
        success: function (res) {
            _park=res.data.name;
            _park_id=res.data.id;
            $("#parkName").html(_park);
            $("#parkArea").html(res.data.area);
            $("#address").html(res.data.address);
            $("#industry").html(res.data.industry);
            $("#introduction").html(res.data.introduction);
            $("#logo").html("<img alt=\"logo\" src=\""+res.data.logo+"\">");
        }
    });
    showAccount(_park);
    showIndusCompany(pageNum,pageSize);
}
function showAccount(park){
    $.ajax({
        type:'post',
        url:'/apis/user/getAccountByUser.json',
        data:JSON.stringify({"park":park}),
        contentType:'application/json',
        success:function(res){
            console.log(res.data);
            var thead = '';
            var html = '';
            var arr = res.data;
            var userPrice = 0;
            for (var i = 0; i < arr.length; i++) {
                userPrice += arr[i].totalPrice;
                thead += '<th>'+arr[i].userAccount+'</th>';
                html += '<td>' + arr[i].totalPrice + '</td>';
            }
            console.log(userPrice);
            $("#thead").html(thead);
            $("#account").html(html);
            $("#total").html(userPrice+"元");
        }
    })
}
var pageSize = 10;
var pageNum = 0;
function showIndusCompany(_pageNum,_pageSize) {
    var req = {
        "park":_park,
        "pageNum" : _pageNum,
        "pageSize" : _pageSize
    };
    $.ajax({
        type : "post",
        contentType : "application/json",
        async:false,
        url : "/apis/user/listParkUserBase.json",
        data : JSON.stringify(req),
        success : function(res) {
            if (res.success) {
                if(res.data.totalPage>0){
                    var arr = res.data.dataList;
                    var html = '';
                    for (var i = 0; i < arr.length; i++) {
                        html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td>' + arr[i].userAccount + '</td><td>' + arr[i].realName + '</td><td>' + arr[i].telphone + '</td><td>' + arr[i].userEmail
                            + '</td><td>' + arr[i].createTime + '</td><td class="actions">'
                            + '<a href="javascript:void(0);" class="on-default my_edit"><i class="fa fa-pencil"></i></a>'
                            + '<a href="javascript:void(0);" class="on-default my_remove modal-basic"><i class="fa fa-trash-o"></i></a></td></tr>';
                    }
                    $("#user_list").html(html);
                    page.init(res.data.totalNumber,res.data.pageNumber,options);
                    $("#"+page.pageId +">li[class='pageItem']").on("click",function(){
                        showIndusCompany($(this).attr("page-data")-1,pageSize);
                    });
                }else{
                    $('#user_list').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
                    $('#page').html("");
                }
            }else{
                layer.msg(res.message, {icon: 2});
            }
        }
    });
    initPage();
}
var options={
    "id":"page",//显示页码的元素
    "data":null,//显示数据
    "maxshowpageitem":5,//最多显示的页码个数
    "pagelistcount":10,//每页显示数据个数
    "callBack":function(){}
};
function initPage(){
    $(".my_edit").on("click",function(){
        var _id = $(this).parents('.gradeX').find( 'input' ).val();
        window.location.href="/apis/user/editUserBase.html?id="+_id+"&parkId="+_park_id;
    })
    $(".my_add").on("click",function(i){
        window.location.href="/apis/user/addUserBase.html?id="+_park_id;
    })
    $(".my_remove").on("click",function(i){
        var _id = $(this).parents('.gradeX').find( 'input' ).val();
        layer.confirm('确定要删除该数据？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            $.ajax({
                async:false,
                url : "/apis/user/dropUserBase.json?id="+_id,
                success : function(res) {
                    if(res.success){
                        layer.msg('成功删除', {icon: 1});
                        showIndusCompany(pageNum,pageSize);
                    }else{
                        layer.msg(res.message, {icon: 2});
                    }
                }
            });
        });
    })
}
