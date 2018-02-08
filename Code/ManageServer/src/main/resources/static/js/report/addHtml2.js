$("#report_info").addClass("active nav-expanded ");
$("#html_item").addClass("active");
var req = new Array();
var i=0;
var E = window.wangEditor
var _id;
function addData(id) {
    _id = id;
}
$(function () {
    $.ajax({
        type: 'get',
        url: "/apis/report/findHtmlHeadlines.json",
        async: false,
        data: {id:_id},
        success: function (response) {
            if(response.success){
                var data=response.data;
                $.each(data, function (index, obj) {
                    if(obj.children.length==0){
                        i=i+1;
                        $(".form-horizontal").append('<div class="panel-body" id="my_body"><p>'+obj.name+'</p><div id="div'+i+'"><p></p></div></div>');
                        req.push({id:obj.id,value:i});
                    }else{
                        $(".form-horizontal").append('<div class="panel-body" id="my_body"><p>'+obj.name+'</div></div>');
                        $.each(obj.children, function (index, o) {
                            i=i+1;
                            $(".form-horizontal").append('<div class="panel-body" id="my_body"><p>'+o.name+'</p><div id="div'+i+'"><p></p></div></div>');
                            req.push({id:o.id,value:i});
                        });
                    }
                });
            }else{
                layer.msg(response.message);
            }
        }
    });
    init();
});
var divs;
function init() {
    divs = new Array();
    $.each(req, function (index, obj) {
        var editor = new E('#div'+obj.value);
        editor.create();
        divs.push({id:obj.id,value:editor});
    });
    $(".btn-success").on("click",function(){
        var arr = new Array();
        $.each(divs, function (index, obj) {
            arr.push({id:obj.id,text:obj.value.txt.html()});
        });
        $.ajax({
            type: 'post',
            url: "/apis/report/addParagraphData.json",
            async: false,
            contentType: "application/json",
            data: JSON.stringify({obj:arr}),
            success: function (response) {
                if(response.success){
                    layer.msg(response.message, {icon: 1});
                    window.location.href="/apis/report/htmlReport.html";
                }else{
                    layer.msg(response.message);
                }
            }
        });
    });
    $(".btn-danger").on("click",function(){
        layer.confirm('直接离开将会失去修改内容，确认离开？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            window.location.href="/apis/report/htmlReport.html";
        });
    });

}

