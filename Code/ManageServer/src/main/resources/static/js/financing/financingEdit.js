$("#financing_item").addClass("active");
var _id;
function addData(id) {
    _id=id;
    $.ajax({
        type : "get",
        contentType : "application/json",
        async:false,
        url : "/apis/financing/getCompanyById.json",
        data : {id:id},
        success : function(res) {
            if(res.success){
                var enter=res.data;
                $("input[name='logo']").val(enter.logo);
                $("input[name='financingCompany']").val(enter.financingCompany);
                $("input[name='financingDate']").val(enter.financingDate);
                $("input[name='invest']").val(enter.invest);
                $("input[name='financingAmount']").val(enter.financingAmount);
                $("input[name='investor']").val(enter.investor);
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
        window.location.href="/apis/financing/financingManage.html";
    });
})
function etitData() {
    var _logo = $("input[name='logo']").val();
    var _financingCompany = $("input[name='financingCompany']").val();
    var _financingDate = $("input[name='financingDate']").val();
    var _invest = $("input[name='invest']").val();
    var _financingAmount = $("input[name='financingAmount']").val();
    var _investor = $("input[name='investor']").val();
    var obj = {
        id: _id,
        logo: _logo,
        financingCompany: _financingCompany,
        financingDate: _financingDate,
        invest: _invest,
        financingAmount: _financingAmount,
        investor: _investor
    };
    $.ajax({
        type : "post",
        contentType : "application/json",
        async:false,
        url : "/apis/financing/saveCompany.json",
        data : JSON.stringify(obj),
        success : function(res) {
            if(res.success){
                layer.msg('操作成功', {icon: 1});
                window.location.href="/apis/financing/financingManage.html";
            }else{
                layer.msg(res.message, {icon: 2});
            }
        }
    });
}