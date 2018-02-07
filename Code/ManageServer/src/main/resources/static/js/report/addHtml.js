var i=1;
var i2=1;
$(function () {
    $(".my_add").on("click",function () {
        i=i+1;
        $("#my_body").append('<tr><td><input type="text" name="sort'+i+'" class="form-control" placeholder=""></td>' +
            '<td><input type="text" name="name'+i+'" class="form-control" placeholder=""></td>' +
            '<td><input type="text" name="logo'+i+'" class="form-control" placeholder=""></td></tr>');
    });
    $(".my_add2").on("click",function () {
        i2=i2+1;
        $("#my_body2").append('<tr><td><input type="text" name="sort2'+i+'" class="form-control" placeholder=""></td>' +
            '<td><input type="text" name="name2'+i+'" class="form-control" placeholder=""></td>' +
            '<td><input type="text" name="logo2'+i+'" class="form-control" placeholder=""></td>' +
            '<td><input type="text" name="parent2'+i+'" class="form-control" placeholder=""></td></tr>');
    });
    $(".btn-success").on("click",function(){
        var _name = $("input[name='name']").val();
        var _time = $("input[name='time']").val();
        var _array=new Array()
        for (var j=1;j<=i;j++){
            var _obj={
                sort:$("input[name='sort"+j+"']").val(),
                name:$("input[name='name"+j+"']").val(),
                logoClass:$("input[name='logo"+j+"']").val()
            };
            _array.push(_obj);
        }
        var _array2=new Array()
        for (var j=1;j<=i;j++){
            var _obj={
                sort:$("input[name='sort2"+j+"']").val(),
                name:$("input[name='name2"+j+"']").val(),
                logoClass:$("input[name='logo2"+j+"']").val(),
                parentId:$("input[name='parent2"+j+"']").val()
            };
            _array2.push(_obj);
        }
        var req={
            name:_name,
            time:_time,
            arr:_array,
            arr2:_array2
        }
        $.ajax({
            type: 'post',
            url: "/apis/report/addHtmlData.json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify(req),
            success: function (response) {
                if(response.success){
                    window.location.href="/apis/report/addHtml2.html";
                }else{
                    layer.alert(response.message);
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
});

