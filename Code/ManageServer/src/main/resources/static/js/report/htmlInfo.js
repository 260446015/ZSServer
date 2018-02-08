$("#report_info").addClass("active nav-expanded ");
$("#html_item").addClass("active");
var _id;
function addData(id) {
    _id = id;
}
$(function () {
    $.ajax({
        type: 'get',
        url: "/apis/report/getHtmlData.json",
        async: false,
        data: {id:_id},
        success: function (response) {
            if(response.success){
                var data=response.data;
                $(".name").html(data.info.name);
                $(".time").html(data.info.time);
                $.each(data.arr, function (index, obj) {
                    if(obj.level==1){
                        var content="";
                        $.each(obj.content, function (index, c) {
                            content+='<p >'+c.text+'</p>';
                        });
                        $(".form-horizontal").append('<div class="form-group"><label class="col-md-3 control-label" for="text-input"><b>'
                            +obj.name+'</b></label><div class="col-md-9">' +content+ '</div></div>');
                    }else{
                        var children="";
                        var content="";
                        $.each(obj.children, function (index, c) {
                            $.each(c.content, function (index, inn) {
                                content+='<p >'+inn.text+'</p>';
                            });
                            children+='<label class="col-md-3 control-label" for="text-input">'+c.name+'</label><div class="col-md-9">'+content+'</div>';
                        });
                        $(".form-horizontal").append('<div class="form-group"><label class="col-md-3 control-label" for="text-input"><b>'
                            +obj.name+'</b></label></div><div class="form-group">' +children+ '</div>');
                    }
                });
            }else{
                layer.alert(response.message);
            }
        }
    });
});

