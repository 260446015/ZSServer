$("#report_info").addClass("active nav-expanded ");
$("#html_item").addClass("active");
var _id;
var _size;
var result= new Array();
function addData(id) {
    _id = id;
    $.get("/apis/report/getHtmlData.do?id="+_id+"&type=focus",function (response) {
        if(response.success){
            _size = response.data.length;
            $.each(response.data,function (index,value) {
                var i=1;
                $(".form-horizontal").append('<div class="form-group"><label class="col-md-3 control-label" for="text-input">'+value.name+'</label>' +
                    '<div class="col-md-9"><div class="table-responsive"><table class="table table-striped table-bordered bootstrap-datatable datatable">' +
                    '<thead><tr><th style="width: 50px;">关键词</th><th>文本</th></tr></thead>' +
                    '<tbody id="table_'+index+'"><tr><td><input name="key_'+index+'_'+i+'"></td>' +
                    '<td><textarea name="text_'+index+'_'+i+'" class="col-md-12"></textarea></td></tr></tbody></table>' +
                    '<div class="bk-margin-bottom-10"><button class="btn btn-info btn-xs add_'+index+'">添加数据 <i class="fa fa-plus"></i></button></div></div>');
                $(".add_"+index+"").on("click",function () {
                    $("#table_"+index+"").append('<tr><td><input name="key_'+index+'_'+i+'"></td><td><textarea name="text_'+index+'_'+i+'" class="col-md-12"></textarea></td></tr>');
                    i++;
                });
                $(".btn-success").on("click",function(){
                    result.push({
                        name:value.id,
                        value:i-1
                    });
                    savaFocus();
                });
                i++;
            });
        }else{
            layer.alert(response.message);
        }
    });
}
$(".btn-danger").on("click",function(){
    layer.confirm('直接离开将会失去修改内容，确认离开？', {
        btn: ['确认','取消'] //按钮
    }, function(){
        window.location.href="/apis/report/htmlReport.html";
    });
});
function myDelete() {
    $.get("/apis/report/dropHtmlData.json?id="+_id,function (response) {

    });
}
function savaFocus() {
    var _data =new Array();
    if(result.length==_size){
        for(var j=0;j<result.length;j++){
            for(var i=1;i<=result[j].value;i++){
                _data.push({
                    headlinesId:result[j].name,
                    keyWord:$("input[name='key_"+j+"_"+i+"']").val(),
                    text:$("input[name='text_"+j+"_"+i+"']").val()
                });
            }
        };
        result=new Array();
        $.ajax({
            type: 'post',
            url: "/apis/report/addParagraphData.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify({
                id:_id,
                obj:_data
            }),
            success: function (response) {
                if(response.success){
                    window.location.href="/apis/report/addHtml3.html?id="+_id;
                }else{
                    layer.alert(response.message);
                }
            }
        });
    }
}

