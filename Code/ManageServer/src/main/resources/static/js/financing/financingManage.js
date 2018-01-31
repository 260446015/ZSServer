var _invest="全部";
var _area="全部";
var _industry="全部";
var _sort="按时间";
var _pageNum = 0;
var _pageSize = 10;
var options={
    "id":"page",//显示页码的元素
    "data":null,//显示数据
    "maxshowpageitem":5,//最多显示的页码个数
    "pagelistcount":10,//每页显示数据个数
    "callBack":function(){}
};
$(function() {
    var req = {
        "pageNum" : _pageNum,
        "pageSize" : _pageSize,
        "invest":_invest,
        "sort":_sort,
        "area":_area,
        "industry":_industry
    };
    showPage(req);
});
$(".btn").on("click",function(){
    $(this).addClass("btn-success").siblings().removeClass("btn-success");
    if($(this).siblings().attr("value")==1){
        _industry = $(this).html();
    }else if($(this).siblings().attr("value")==2){
        _area = $(this).html();
    }else if($(this).siblings().attr("value")==3){
        _invest = $(this).html();
    }else if($(this).siblings().attr("value")==4){
        _sort = $(this).html();
    }
    var req = {
        "pageNum" : _pageNum,
        "pageSize" : _pageSize,
        "invest":_invest,
        "sort":_sort,
        "area":_area,
        "industry":_industry
    };
    showPage(req);
});
function showPage(req) {
    $.ajax({
        type : "post",
        contentType : "application/json",
        async:false,
        url : "/apis/financing/getCompanyList.json",
        data : JSON.stringify(req),
        success : function(res) {
            if (res.success) {
                if(res.data.totalPage>0){
                    var arr = res.data.dataList;
                    var html = '';
                    for (var i = 0; i < arr.length; i++) {
                        html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td>' + arr[i].financingDate + '</td><td>' + arr[i].financingCompany + '</td><td>' + arr[i].invest + '</td><td>' + arr[i].financingAmount
                            + '</td><td>' + arr[i].investor + '</td><td class="actions">'+
                            '<a href="javascript:void(0);" class="on-default my_edit">查看详情</a>' +
                            '<a href="javascript:void(0);" class="on-default my_remove modal-basic"><i class="fa fa-trash-o"></i></a></td></tr>';

                    }
                    $("#user_list").html(html);
                    page.init(res.data.totalNumber,res.data.pageNumber,options);
                    $("#"+page.pageId +">li[class='pageItem']").on("click",function(){
                        var req = {
                            "pageNum" : $(this).attr("page-data")-1,
                            "pageSize" : _pageSize,
                            "invest":_invest,
                            "sort":_sort,
                            "area":_area,
                            "industry":_industry
                        };
                        showPage(req);
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
    $(".btn-labeled").on("click",function(){
        var _id = $(this).parents('.gradeX').find( 'input' ).val();
        $.ajax({
            url: "/apis/user/modifyIsCheck.json",
            data: {id:_id},
            success: function (res) {
                if(res.success){
                    layer.msg(res.data, {icon: 1});
                    var req = {
                        "pageNum" : _pageNum,
                        "pageSize" : _pageSize,
                        "type" : _type,
                        "time" : _time
                    };
                    showPage(req);
                }else{
                    layer.msg(res.message, {icon: 2});
                }
            }
        });
    });
    $(".my_remove").on("click",function(i){
        var _id = $(this).parents('.gradeX').find( 'input' ).val();
        layer.confirm('确定要删除该数据？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            $.ajax({
                async:false,
                url : "/apis/financing/drop.json?id="+_id,
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