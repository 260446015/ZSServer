$("#report_item").addClass("active");
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
        "pageSize" : _pageSize
    };
    showPage(req);
});
function showPage(req) {
    $.ajax({
        type : "post",
        contentType : "application/json",
        async:false,
        url : "/apis/report/getExpertReport.json",
        data : JSON.stringify(req),
        success : function(res) {
            if (res.success) {
                if(res.data.totalPage>0){
                    var arr = res.data.dataList;
                    var html = '';
                    for (var i = 0; i < arr.length; i++) {
                        html += '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+arr[i].id+'"/><td>'+arr[i].name+'</td><td>'
                            + arr[i].data + '</td><td>' + arr[i].label + '</td><td>' + arr[i].fileType + '</td><td>' + arr[i].downloads
                            + '</td><td><a href="http://58.16.181.24:9322/fileserver/static/pdf/pdf/' + arr[i].url + '" target="_blank">http://58.16.181.24:9322/fileserver/static/pdf/pdf/' + arr[i].url + '</a></td><td class="actions">'+
                            '<a href="javascript:void(0);" class="on-default my_remove modal-basic"><i class="fa fa-trash-o"></i></a>' +
                            '<a href="javascript:void(0);" class="on-default my_edit modal-basic"><i class="fa fa-pencil"></i></a></td></tr>';

                    }
                    $("#user_list").html(html);
                    page.init(res.data.totalNumber,res.data.pageNumber,options);
                    $("#"+page.pageId +">li[class='pageItem']").on("click",function(){
                        var req = {
                            "pageNum" : $(this).attr("page-data")-1,
                            "pageSize" : _pageSize
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
    $(".my_remove").on("click",function(i){
        var _id = $(this).parents('.gradeX').find( 'input' ).val();
        layer.confirm('确定要删除该数据？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            $.ajax({
                async:false,
                url : "/apis/report/dropReport.json?id="+_id,
                success : function(res) {
                    if(res.success){
                        layer.msg('成功删除', {icon: 1});
                        window.location.reload();
                    }else{
                        layer.msg(res.message, {icon: 2});
                    }
                }
            });
        });
    });
    $(".my_edit").on("click",function(i){
        var _id = $(this).parents('.gradeX').find( 'input' ).val();
        window.location.href="/apis/report/reportEdit.html?id="+_id;
    });
}
function pushImg(){
    var picpath="";
    $.ajaxFileUpload({
            url : "http://58.16.181.24:9322/fileserver/file/PDFUpload.do",
            fileElementId:'file',
            dataType : "json",
            success: function(response){
                if(res.success){
                    layer.alert('文件上传成功，图片页面的形成可能需要一点时间，请耐心等待', {icon: 1});
                    window.location.reload();
                }else{
                    layer.msg(res.message, {icon: 2});
                }
            },
            error: function(response){
                layer.msg(res.message, {icon: 2});
            }
        }
    );
}