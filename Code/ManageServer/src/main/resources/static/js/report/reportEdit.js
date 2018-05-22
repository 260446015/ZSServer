$("#report_info").addClass("active nav-expanded ");
$("#report_item").addClass("active");
var _id;
function addData(id) {
    _id=id;
    $.ajax({
        type : "get",
        contentType : "application/json",
        async:false,
        url : "/apis/report/getReportById.json",
        data : {id:id},
        success : function(res) {
            if(res.success){
                var enter=res.data;
                $("input[name='name']").val(enter.name);
                $("input[name='data']").val(enter.data);
                $("input[name='label']").val(enter.label);
                $("input[name='fileType'][value='"+enter.fileType+"']").attr('checked','true');
                $("input[name='downloads']").val(enter.downloads);
                $("input[name='url']").val(enter.url);
            }else{
                layer.msg(res.message, {icon: 2});
            }
        }
    });
}
$(".btn-success").on("click",function(){
    etitData();
});
$(".btn-danger").on("click",function(){
    layer.confirm('直接离开将会失去修改内容，确认离开？', {
        btn: ['确认','取消'] //按钮
    }, function(){
        window.location.href="/apis/report/reportManage.html";
    });
})
function etitData() {
    var _logo = $("input[name='name']").val();
    var _financingCompany = $("input[name='data']").val();
    var _financingDate = $("input[name='label']").val();
    var _invest = $('input:radio:checked').val();
    var _financingAmount = $("input[name='downloads']").val();
    var _investor = $("input[name='url']").val();
    var obj = {
        id: _id,
        name: _logo,
        data: _financingCompany,
        label: _financingDate,
        fileType: _invest,
        downloads: _financingAmount,
        url: _investor
    };
    $.ajax({
        type : "post",
        contentType : "application/json",
        async:false,
        url : "/apis/report/saveReport.json",
        data : JSON.stringify(obj),
        success : function(res) {
            if(res.success){
                layer.msg('操作成功', {icon: 1});
                window.location.href="/apis/report/reportManage.html";
            }else{
                layer.msg(res.message, {icon: 2});
            }
        }
    });
}