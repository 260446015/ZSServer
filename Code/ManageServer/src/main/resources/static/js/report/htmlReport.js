$("#report_info").addClass("active nav-expanded ");
$("#html_item").addClass("active");
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
                            '</a></td><td>' + arr[i].time + '</td><td>' + arr[i].createTime + '</td><td class="actions">' +
                            '<a href="javascript:void(0);" class="on-default my_edit modal-basic"><i class="fa fa-pencil"></i></a></td></tr>';

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
        //window.location.href="/apis/user/editUserBase.html?id="+_id+"&parkId="+_park_id;
    });
}