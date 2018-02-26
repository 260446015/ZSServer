$("#report_info").addClass("active nav-expanded ");
$("#html_item").addClass("active");
var _id;
function addData(id) {
    _id = id;
}
$(function () {
    $.ajax({
        type: 'get',
        url: "/apis/report/getInfoData.json",
        async: false,
        data: {id: _id,type:'focus'},
        success: function (response) {
            if(response.success){
                var focus="";
                $.each(response.data, function (index, c) {
                    var inner="";
                    $.each(c.value, function (index, v) {
                        inner+='<label class="control-label">关键词：'+v.keyWord+'</label><br>' +
                            '<label class="control-label">----'+v.text+'</label><br>';
                    });
                    focus+='<div class="form-group"><label class="col-md-3 control-label" for="text-input"><b>'+c.name+'</b></label><div class="col-md-9">' +inner+'</div></div>';
                });
                $(".form-horizontal").html(focus);
            }else{
                layer.msg(res.message, {icon: 2});
            }
        }
    })
});