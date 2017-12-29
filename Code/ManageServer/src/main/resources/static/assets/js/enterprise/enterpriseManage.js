$(function() {
    showIndusCompany(pageNum,pageSize);
});
var pageSize = 20;
var pageNum = 0;
function showIndusCompany(_pageNum,_pageSize) {
    var req = {
        "pageNum" : _pageNum,
        "pageSize" : _pageSize
    };
    $.ajax({
        type : "post",
        contentType : "application/json",
        async:false,
        url : "/apis/enterprise/listEnterprise.json",
        data : JSON.stringify(req),
        success : function(res) {
            if (res.success) {
                if(res.data.totalPage>0){
                    var arr = res.data.dataList;
                    var html = '';
                    for (var i = 0; i < arr.length; i++) {
                        html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td>' + arr[i].industryType + '</td><td>' + arr[i].industry + '</td><td>' + arr[i].company + '</td><td>' + arr[i].address
                            + '</td><td>' + arr[i].registerTime + '</td><td class="actions">'
                            + '<a href="javascript:void(0);" class="on-default my_edit"><i class="fa fa-pencil"></i></a>'
                            + '<a href="javascript:void(0);" class="on-default my_remove modal-basic"><i class="fa fa-trash-o"></i></a></td></tr>';
                    }
                    $("#enterprise_list").html(html);
                    page.init(res.data.totalNumber,res.data.pageNumber,options);
                    $("#"+page.pageId +">li[class='pageItem']").on("click",function(){
                        showIndusCompany($(this).attr("page-data")-1,pageSize);
                    });
                }else{
                    $('#enterprise_list').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
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
    "pagelistcount":20,//每页显示数据个数
    "callBack":function(){}
};
function initPage(){
    $(".my_edit").on("click",function(){
        var _id = $(this).parents('.gradeX').find( 'input' ).val();
        window.location.href="/apis/enterprise/editEnterprise.html?id="+_id;
    })
    $(".my_add").on("click",function(i){
        window.location.href="/apis/enterprise/editEnterprise.html";
    })
    $(".my_remove").on("click",function(i){
        var _id = $(this).parents('.gradeX').find( 'input' ).val();
        layer.confirm('确定要删除该数据？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            $.ajax({
                async:false,
                url : "/apis/enterprise/dropEnterprise.json?id="+_id,
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
