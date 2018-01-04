$("#enterprise_item").addClass("active");
var _id;
function addData(enter) {
    _id=enter.id;
    $("input[name='industryType']").val(enter.industryType);
    $("input[name='company']").val(enter.company);
    $("input[name='phone']").val(enter.phone);
    $("input[name='email']").val(enter.email);
    $("input[name='url']").val(enter.url);
    $("input[name='address']").val(enter.address);
    $("input[name='boss']").val(enter.boss);
    $("input[name='registerCapital']").val(enter.registerCapital);
    $("input[name='engageState']").val(enter.engageState);
    $("input[name='registerTime']").val(enter.registerTime);
    $("input[name='industry']").val(enter.industry);
    $("input[name='icRegisterNo']").val(enter.icRegisterNo);
    $("input[name='companyType']").val(enter.companyType);
    $("input[name='orgMechanismNo']").val(enter.orgMechanismNo);
    $("input[name='businessDate']").val(enter.businessDate);
    $("input[name='registerAgency']").val(enter.registerAgency);
    $("input[name='examineTime']").val(enter.examineTime);
    $("input[name='registerAddress']").val(enter.registerAddress);
    $("input[name='operateScope']").val(enter.operateScope);
    $("input[name='park']").val(enter.park);
    $("input[name='area']").val(enter.area);
    $("input[name='scoring']").val(enter.scoring);
    $("input[name='publicCompany']").val(enter.publicCompany);

}
$(".btn-success").on("click",function(){
    etitData();
})
$(".btn-danger").on("click",function(){
    if(_id==null){
        window.location.href = "/apis/enterprise/enterpriseManage.html";
    }else {
        layer.confirm('直接离开将会失去修改内容，确认离开？', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            window.location.href = "/apis/enterprise/enterpriseManage.html";
        });
    }
})
function etitData() {
    var _industryType = $("input[name='industryType']").val();
    var _company = $("input[name='company']").val();
    var _phone = $("input[name='phone']").val();
    var _email = $("input[name='email']").val();
    var _url = $("input[name='url']").val();
    var _address = $("input[name='address']").val();
    var _boss = $("input[name='boss']").val();
    var _registerCapital = $("input[name='registerCapital']").val();
    var _engageState = $("input[name='engageState']").val();
    var _registerTime = $("input[name='registerTime']").val();
    var _industry = $("input[name='industry']").val();
    var _icRegisterNo = $("input[name='icRegisterNo']").val();
    var _companyType = $("input[name='companyType']").val();
    var _orgMechanismNo = $("input[name='orgMechanismNo']").val();
    var _businessDate = $("input[name='businessDate']").val();
    var _registerAgency = $("input[name='registerAgency']").val();
    var _examineTime = $("input[name='examineTime']").val();
    var _registerAddress = $("input[name='registerAddress']").val();
    var _operateScope = $("input[name='operateScope']").val();
    var _park = $("input[name='park']").val();
    var _area = $("input[name='area']").val();
    var _scoring = $("input[name='scoring']").val();
    var _publicCompany = $("input[name='publicCompany']").val();
    var obj={
            id:_id,industryType:_industryType,company:_company,phone:_phone,email:_email,url:_url,address:_address,boss:_boss,
            registerCapital:_registerCapital,engageState:_engageState,registerTime:_registerTime,industry:_industry,icRegisterNo:_icRegisterNo,
            companyType:_companyType,orgMechanismNo:_orgMechanismNo,businessDate:_businessDate,registerAgency:_registerAgency,examineTime:_examineTime,
            registerAddress:_registerAddress,operateScope:_operateScope,park:_park,area:_area,scoring:_scoring,publicCompany:_publicCompany
        };
    $.ajax({
        type : "post",
        contentType : "application/json",
        async:false,
        url : "/apis/enterprise/saveEnterprise.json",
        data : JSON.stringify(obj),
        success : function(res) {
            if(res.success){
                layer.msg('操作成功', {icon: 1});
                window.location.href="/apis/enterprise/enterpriseManage.html";
            }else{
                layer.msg(res.message, {icon: 2});
            }
        }
    });
}