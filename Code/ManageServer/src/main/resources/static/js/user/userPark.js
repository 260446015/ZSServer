
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
        url : "/apis/park/getGardenList.json",
        data : JSON.stringify(req),
        success : function(res) {
            if (res.success) {
                if(res.data.totalPage>0){
                    var arr = res.data.dataList;
                    var html = '';
                    for (var i = 0; i < arr.length; i++) {
                        html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td>' + arr[i].parkName + '</td><td>' + arr[i].area + '</td><td>' + arr[i].accountCount + '</td><td>' + arr[i].checkAccountCount
                            + '</td><td>' + arr[i].expireAccountCount + '</td><td>' + arr[i].normalAccountCount + '</td><td>' + arr[i].dueAccountCount + '</td><td class="actions">'
                            + '<a href="javascript:void(0);" class="on-default my_edit">查看详情</a></td></tr>';
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
    "pagelistcount":20,//每页显示数据个数
    "callBack":function(){}
};
function initPage() {
    $(".my_edit").on("click", function () {
        var _id = $(this).parents('.gradeX').find('input').val();
        window.location.href = "/apis/user/findParkInformation.html?id="+_id;
    });
    $(".my_add").on("click", function (i) {
        window.location.href = "/apis/user/addUserPark.html";
    });
};