var i=1;
var E = window.wangEditor
var editor1 = new E('#div1');
editor1.create();
$(function () {
    $(".my_add").on("click",function () {
        i=i+1;
        $("#my_body").append('<div id="div'+i+'"><p> demo（常规）</p></div>');
        var editor2 = new E('#div'+i);
        editor2.create()
    });
});

