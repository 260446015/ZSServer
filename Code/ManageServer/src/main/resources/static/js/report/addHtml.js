$("#report_info").addClass("active nav-expanded ");
$("#html_item").addClass("active");
var i=1;
$(function () {
    $(".my_add").on("click",function () {
        i=i+1;
        $("#my_body").append('<tr><td><input type="text" name="sort'+i+'" class="form-control" value="'+i+'" disabled></td>' +
            '<td><input type="text" name="name'+i+'" class="form-control" placeholder=""></td>' +
            '<td><input type="text" name="logo'+i+'" class="form-control" placeholder=""></td></tr>');
    });
    $(".add_word").on("click",function () {
        var a=$(this);
        layer.prompt({title: '输入关键字，并确认', formType: 0}, function(text, index){
            layer.close(index);
            var inner=a.prev().html();
            if(inner==""){
                a.prev().html(text);
            }else{
                a.prev().html(inner+'、'+text);
            }
        });
    });
    $(".btn-success").on("click",function(){
        var _name = $("input[name='name']").val();
        var _time = $("input[name='time']").val();
        var _keyWord = new Array();
        for(var i=1;i<4;i++){
            _keyWord.push({
                text:$("input[name='key"+i+"']").val(),
                key:$("#key"+i+"").html()
            });
        }
        var req={
            name:_name,
            time:_time,
            keyWord:_keyWord
        }
        console.log(req)
        /*$.ajax({
            type: 'post',
            url: "/apis/report/addHtmlData.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify(req),
            success: function (response) {
                if(response.success){
                    window.location.href="/apis/report/addHtml2.html?id="+response.data;
                }else{
                    layer.alert(response.message);
                }
            }
        });*/
    });
    $(".btn-danger").on("click",function(){
        layer.confirm('直接离开将会失去修改内容，确认离开？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            window.location.href="/apis/report/htmlReport.html";
        });
    });
});

