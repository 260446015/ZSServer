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
            	$.get("/apis/report/getHtmlData.do?id="+ _id+"&type="+ value.id,function (response) {
            		var i=response.data.length+1;
            		var _inner="";
            		$.each(response.data,function (_i,v) {
            			var j= 1+_i;
            			_inner+='<tr id="tr_'+value.id+'_'+j+'"><td><input type="hidden" name="h_key_'+value.id+'_'+j+'" value="'+v.id+'"><input name="key_'+value.id+'_'+j+'" value="'+v.keyWord+'"></td>' +
                        '<td><textarea name="text_'+value.id+'_'+j+'" class="col-md-12">'+v.text+'</textarea></td></tr>';
            		});
            		$(".form-horizontal").append('<div class="form-group"><label class="col-md-3 control-label" for="text-input">'+value.name+'</label>' +
                            '<div class="col-md-9"><div class="table-responsive"><table class="table table-striped table-bordered bootstrap-datatable datatable">' +
                            '<thead><tr><th style="width: 50px;">关键词</th><th>文本</th></tr></thead>' +
                            '<tbody id="table_'+value.id+'">'+_inner+'</tbody></table>' +
                            '<div class="bk-margin-bottom-10"><button class="btn btn-info btn-xs add_'+value.id+'">添加数据 <i class="fa fa-plus"></i></button>    <button class="btn btn-info btn-xs drop_'+value.id+'">删除数据 <i class="fa fa-minus"></i></button></div></div>');
                    $(".add_"+value.id+"").on("click",function () {
                        $("#table_"+value.id+"").append('<tr id="tr_'+value.id+'_'+i+'"><td><input name="key_'+value.id+'_'+i+'"></td><td><textarea name="text_'+value.id+'_'+i+'" class="col-md-12"></textarea></td></tr>');
                        i++;
                    });
                    $(".drop_"+value.id+"").on("click",function () {
                        if(i>2){
                            i--;
                            $("#tr_"+value.id+"_"+i+"").remove();
                        }
                    });
                    $(".btn-success").on("click",function(){
                        result.push({
                            name:value.id,
                            value:i-1
                        });
                        savaFocus();
                    });
            	});
            	
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
function savaFocus() {
    var _data =new Array();
    if(result.length==_size){
    	var index = layer.load();
    	for(var j=0;j<result.length;j++){
            for(var i=1;i<=result[j].value;i++){
                _data.push({
                    headlinesId:result[j].name,
                    keyWord:$("input[name='key_"+result[j].name+"_"+i+"']").val(),
                    id:$("input[name='h_key_"+result[j].name+"_"+i+"']").val(),
                    text:$("textarea[name='text_"+result[j].name+"_"+i+"']").val()
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
                	layer.close(index); 
                	window.location.href="/apis/report/htmlReport.html";
                }else{
                    layer.alert(response.message);
                }
            }
        });
    }
}

