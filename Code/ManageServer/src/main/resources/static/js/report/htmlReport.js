$("#report_info").addClass("active nav-expanded ");
$("#html_item").addClass("active");
var options={
    "id":"page",//显示页码的元素
    "data":null,//显示数据
    "maxshowpageitem":5,//最多显示的页码个数
    "pagelistcount":20,//每页显示数据个数
    "callBack":function(){}
};
$(".my_add").on("click",function(){
    window.location.href="/apis/report/addHtml.html";
});
$(function() {
    showIndusCompany(pageNum,pageSize);
});
var pageSize = 20;
var pageNum = 0;
function showIndusCompany(_pageNum,_pageSize) {
    var req = {
        "pageNum": _pageNum,
        "pageSize": _pageSize
    };
    $.ajax({
        type: "post",
        contentType: "application/json",
        async: false,
        url: "/apis/report/getHtmlReport.json",
        data: JSON.stringify(req),
        success: function (res) {
            if (res.success) {
                if(res.data.totalPage>0){
                    var arr = res.data.dataList;
                    var html = '';
                    for (var i = 0; i < arr.length; i++) {
                        html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td><a href="/apis/report/htmlInfo.html?id='+arr[i].id+'">' + arr[i].name +
                            '</a></td><td>' + arr[i].time + '</td><td><a href="/apis/report/cover.htm?id='+arr[i].id+'" target="_blank">/apis/report/cover.htm?id=' + arr[i].id + '</a></td><td class="actions">' +
                            '<a href="javascript:void(0);" class="on-default my_edit modal-basic">修改</a><a href="javascript:void(0);" class="on-default my_drop modal-basic">删除</a></td></tr>';

                    }
                    $("#report_list").html(html);
                    page.init(res.data.totalNumber,res.data.pageNumber,options);
                    $("#"+page.pageId +">li[class='pageItem']").on("click",function(){
                        showIndusCompany($(this).attr("page-data")-1,pageSize);
                    });
                }else{
                    $('#report_list').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
                    $('#page').html("");
                }
            }else{
                layer.msg(res.message, {icon: 2});
            }
        }
    });
    initPage();
};
function initPage(){
    $(".my_edit").on("click",function(){
        var _id = $(this).parents('.gradeX').find( 'input' ).val();
        window.location.href="/apis/report/editHtml.html?id="+_id;
    });
    $(".my_drop").on("click",function(){
        layer.confirm('确认删除该内容？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            $.get("/apis/report/dropHtmlData.json?id="+_id,function (response) {
                if(response.success){
                    window.location.href="/apis/report/htmlReport.html";
                }else{
                    layer.alert(response.message);
                }
            });
        });
    });
}