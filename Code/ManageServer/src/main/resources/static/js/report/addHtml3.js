$("#report_info").addClass("active nav-expanded ");
$("#html_item").addClass("active");
var _id;
var _size;
var k=1;
var result= new Array();
function addData(id) {
    _id = id;
    $.get("/apis/report/getHtmlData.do?id="+_id+"&type=dynamic",function (response) {
        if(response.success){
            _size = response.data.length;
            $.each(response.data,function (index,value) {
                var i=1;
                if(value.name=="会议日程") {
                    $(".form-horizontal").append('<div class="form-group"><label class="col-md-3 control-label" for="text-input">' + value.name + '</label>' +
                        '<div class="col-md-9"><div class="table-responsive"><table class="table table-striped table-bordered bootstrap-datatable datatable">' +
                        '<thead><tr><th colspan="3">本月一共<input name="money_' + index + '_1">场会议</th></tr></thead>' +
                        '<thead><tr><th colspan="3">最多会议地点是<input name="time_' + index + '_1"></th></tr></thead>' +
                        '<thead><tr><th colspan="3">会议覆盖的行业是<textarea name="text_' + index + '_1"></textarea></th></tr></thead>' +
                        '<thead><tr><th colspan="3">推荐参加<input name="key_' + index + '_1">交流大会</th></tr></thead>' +
                        '<thead><tr><th>日期(<span style="color: red">只输入日，必须为数字</span>)</th><th>地点</th><th>主办方</th></tr></thead>' +
                        '<tbody id="table_' + index + '"><tr><td><input name="hui_ri_' + index + '_' + k + '"></td>' +
                        '<td><input name="hui_di_' + index + '_' + k + '"></td>' +
                        '<td><input name="hui_zhu_' + index + '_' + k + '"></td>' +
                        '</tr></tbody></table>' +
                        '<div class="bk-margin-bottom-10"><button class="btn btn-info btn-xs add_' + index + '">添加数据 <i class="fa fa-plus"></i></button>' +
                        '    <button class="btn btn-info btn-xs drop_'+index+'">删除数据 <i class="fa fa-minus"></i></button></div></div>');
                    $(".add_" + index + "").on("click", function () {
                        k=k+1;
                        $("#table_" + index + "").append('<tr id="tr_'+index+'_'+k+'"><td><input name="hui_ri_' + index + '_' + k + '"></td>' +
                            '<td><input name="hui_di_' + index + '_' + k + '"></td>' +
                            '<td><input name="hui_zhu_' + index + '_' + k + '"></td></tr>');
                    });
                    $(".drop_"+index+"").on("click",function () {
                        if(k>1){
                            $("#tr_"+index+"_"+k+"").remove();
                            k--;
                        }
                    });
                }else if(value.name=="排行报告") {
                    $(".form-horizontal").append('<div class="form-group"><label class="col-md-3 control-label" for="text-input">' + value.name + '</label>' +
                        '<div class="col-md-9"><div class="table-responsive"><table class="table table-striped table-bordered bootstrap-datatable datatable">' +
                        '<thead><tr><th style="width: 50px;">标题</th><th>概要</th><th>原文链接</th><th>发布者</th></tr></thead>' +
                        '<tbody id="table_' + index + '"><tr><td><input name="key_' + index + '_' + i + '"></td>' +
                        '<td><textarea name="text_' + index + '_' + i + '" class="col-md-9"></textarea></td>' +
                        '<td><input name="img_' + index + '_' + i + '"></td>' +
                        '<td><input name="people_' + index + '_' + i + '"></td>' +
                        '</tr></tbody></table>' +
                        '<div class="bk-margin-bottom-10"><button class="btn btn-info btn-xs add_' + index + '">添加数据 <i class="fa fa-plus"></i></button>' +
                        '    <button class="btn btn-info btn-xs drop_'+index+'">删除数据 <i class="fa fa-minus"></i></button></div></div>');
                    $(".add_" + index + "").on("click", function () {
                        $("#table_" + index + "").append('<tr id="tr_'+index+'_'+i+'"><td><input name="key_' + index + '_' + i + '"></td><td><textarea name="text_' + index + '_' + i + '" class="col-md-9"></textarea></td>' +
                            '<td><input name="img_' + index + '_' + i + '"></td><td><input name="people_' + index + '_' + i + '"></td></tr>');
                        i++;
                    });
                    $(".drop_"+index+"").on("click",function () {
                        if(i>2){
                            i--;
                            $("#tr_"+index+"_"+i+"").remove();
                        }
                    });
                }else if(value.name=="投融速递") {
                    $(".form-horizontal").append('<div class="form-group"><label class="col-md-3 control-label" for="text-input">' + value.name + '</label>' +
                        '<div class="col-md-9"><div class="table-responsive"><table class="table table-striped table-bordered bootstrap-datatable datatable">' +
                        '<thead><tr><th style="width: 50px;">产业</th><th style="width: 50px;">金额(亿人民币)</th><th>推荐公司(<span style="color: red">用中文顿号分割公司</span>)</th></tr></thead>' +
                        '<tbody id="table_' + index + '"><tr>' +
                        '<td><input name="key_' + index + '_' + i + '"></td>' +
                        '<td><input name="money_' + index + '_' + i + '"></td>' +
                        '<td><textarea name="text_' + index + '_' + i + '" class="col-md-9"></textarea></td>' +
                        '</tr></tbody></table>' +
                        '<div class="bk-margin-bottom-10"><button class="btn btn-info btn-xs add_' + index + '">添加数据 <i class="fa fa-plus"></i></button>' +
                        '    <button class="btn btn-info btn-xs drop_'+index+'">删除数据 <i class="fa fa-minus"></i></button></div></div>');
                    $(".add_" + index + "").on("click", function () {
                        $("#table_" + index + "").append('<tr id="tr_'+index+'_'+i+'">' +
                            '<td><input name="key_' + index + '_' + i + '"></td>'+
                            '<td><input name="money_' + index + '_' + i + '"></td>'+
                            '<td><textarea name="text_' + index + '_' + i + '" class="col-md-9"></textarea></td>' +
                            '</tr>');
                        i++;
                    });
                    $(".drop_"+index+"").on("click",function () {
                        if(i>2){
                            i--;
                            $("#tr_"+index+"_"+i+"").remove();
                        }
                    });
                }else {
                    var td="";
                    if(value.name=="各地新闻") {
                        td="地域"
                    }else if(value.name=="合作动向") {
                        td="关键词"
                    }else if(value.name=="企业动向") {
                        td="企业"
                    }
                    $(".form-horizontal").append('<div class="form-group"><label class="col-md-3 control-label" for="text-input">' + value.name + '</label>' +
                        '<div class="col-md-9"><div class="table-responsive"><table class="table table-striped table-bordered bootstrap-datatable datatable">' +
                        '<thead><tr><th style="width: 50px;">'+td+'</th><th>文本</th></tr></thead>' +
                        '<tbody id="table_' + index + '"><tr><td><input name="key_' + index + '_' + i + '"></td><td><textarea name="text_' + index + '_' + i + '" class="col-md-12"></textarea></td></tr></tbody></table>' +
                        '<div class="bk-margin-bottom-10"><button class="btn btn-info btn-xs add_' + index + '">添加数据 <i class="fa fa-plus"></i></button>' +
                        '    <button class="btn btn-info btn-xs drop_'+index+'">删除数据 <i class="fa fa-minus"></i></button></div></div>');
                    $(".add_" + index + "").on("click", function () {
                        $("#table_" + index + "").append('<tr id="tr_'+index+'_'+i+'"><td><input name="key_' + index + '_' + i + '"></td><td><textarea name="text_' + index + '_' + i + '" class="col-md-12"></textarea></td></tr>');
                        i++;
                    });
                    $(".drop_"+index+"").on("click",function () {
                        if(i>2){
                            i--;
                            $("#tr_"+index+"_"+i+"").remove();
                        }
                    });
                }
                $(".btn-success").on("click",function(){
                    result.push({
                        title:value.name,
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
function savaFocus() {
    var _data =new Array();
    if(result.length==_size){
        for(var j=0;j<result.length;j++){
            for(var i=1;i<=result[j].value;i++){
                var _schedule=null;
                if(result[j].title=="会议日程"){
                    _schedule=new Array();
                    for(var n=1;n<=k;n++){
                        _schedule.push({
                            date:$("input[name='hui_ri_"+j+"_"+n+"']").val(),
                            place:$("input[name='hui_di_"+j+"_"+n+"']").val(),
                            sponsor:$("input[name='hui_zhu_"+j+"_"+n+"']").val()
                        });
                    }
                }
                _data.push({
                    headlinesId:result[j].name,
                    keyWord:$("input[name='key_"+j+"_"+i+"']").val(),
                    text:$("textarea[name='text_"+j+"_"+i+"']").val(),
                    img:$("input[name='img_"+j+"_"+i+"']").val(),
                    money:$("input[name='money_"+j+"_"+i+"']").val(),
                    time:$("input[name='time_"+j+"_"+i+"']").val(),
                    people:$("input[name='people_"+j+"_"+i+"']").val(),
                    schedule:_schedule
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
                    window.location.href="/apis/report/htmlReport.html";
                }else{
                    layer.alert(response.message);
                }
            }
        });
    }
}

