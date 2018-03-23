$("#report_info").addClass("active nav-expanded ");
$("#html_item").addClass("active");
var _id;
var _size;
var result= new Array();

function addData(id) {
    _id = id;
    $.get("/apis/report/getHtmlData.do?id="+_id+"&type=dynamic",function (response) {
        if(response.success){
            _size = response.data.length;
            $.each(response.data,function (index,value) {
                var _data_length=1;
                if(value.name=="会议日程") {
                	$.get("/apis/report/getHtmlData.do?id="+ _id+"&type="+ value.id,function (response) {
                		var _inner="";
                        _data_length=response.data.schedule.length;
                        var arr = new Array();
                        $.each(response.data.schedule,function (i,v) {
                            $.each(v,function (_i,_v) {
                                arr.push(_v);
                            })
                        })
	            		$.each(arr,function (_i,v) {
	            			var j= 1+_i;
	            			_inner+='<tr id="tr_'+value.id+'_'+j+'"><td><input name="hui_ri_' + value.id + '_' + j + '" value="'+v.date+'"></td>' +
                            '<td><input name="hui_di_' + value.id + '_' + j + '" value="'+v.place+'"></td>' +
                            '<td><input name="hui_zhu_' + value.id + '_' + j + '" value="'+v.sponsor+'"></td>' +
                            '</tr>';
	            		});
                		$(".form-horizontal").append('<div class="form-group"><label class="col-md-3 control-label" for="text-input">' + value.name + '</label>' +
                                '<div class="col-md-9"><div class="table-responsive"><table class="table table-striped table-bordered bootstrap-datatable datatable">' +
                                '<thead><tr><th colspan="3">本月一共<input name="money_' + value.id + '_1" value="'+response.data.total+'">场会议</th></tr></thead>' +
                                '<thead><tr><th colspan="3">最多会议地点是<input name="time_' + value.id + '_1" value="'+response.data.place+'"></th></tr></thead>' +
                                '<thead><tr><th colspan="3">会议覆盖的行业是<textarea name="text_' + value.id + '_1">'+response.data.industry+'</textarea></th></tr></thead>' +
                                '<thead><tr><th colspan="3">推荐参加<input name="key_' + value.id + '_1" value="'+response.data.advise+'">交流大会</th></tr></thead>' +
                                '<thead><tr><th>日期(<span style="color: red">只输入日，必须为数字</span>)</th><th>地点</th><th>主办方</th></tr></thead>' +
                                '<tbody id="table_' + value.id + '">'+_inner+'</tbody></table>' +
                                '<div class="bk-margin-bottom-10"><button class="btn btn-info btn-xs add_' + value.id + '">添加数据 <i class="fa fa-plus"></i></button>' +
                                '    <button class="btn btn-info btn-xs drop_'+value.id+'">删除数据 <i class="fa fa-minus"></i></button></div></div>');
                        $(".add_" + value.id + "").on("click", function () {
                            _data_length++;
                            $("#table_" + value.id + "").append('<tr id="tr_'+value.id+'_'+ _data_length +'">' +
                                '<td><input name="hui_ri_' + value.id + '_' + _data_length + '"></td>' +
                                '<td><input name="hui_di_' + value.id + '_' + _data_length + '"></td>' +
                                '<td><input name="hui_zhu_' + value.id + '_' + _data_length + '"></td></tr>');
                        });
                        $(".drop_"+value.id+"").on("click",function () {
                            if(_data_length>1){
                                $("#tr_"+value.id+"_"+_data_length+"").remove();
                                _data_length--;
                            }
                        });
                	});
                }else if(value.name=="排行报告") {
                	$.get("/apis/report/getHtmlData.do?id="+ _id+"&type="+ value.id,function (response) {
                		var _inner="";
                        _data_length=response.data.length;
	            		$.each(response.data,function (_i,v) {
	            			var j= 1+_i;
	            			_inner+='<tr id="tr_'+value.id+'_'+j+'"><td><input name="key_' + value.id + '_' + j + '" value="'+v.keyWord+'"></td>' +
                            '<td><textarea name="text_' + value.id + '_' + j + '" class="col-md-9" >'+v.text+'</textarea></td>' +
                            '<td><input name="img_' + value.id + '_' + j + '" value="'+v.img+'"></td>' +
                            '<td><input name="people_' + value.id + '_' + j + '" value="'+v.people+'"></td>' +
                            '</tr>';
	            		});
	            		$(".form-horizontal").append('<div class="form-group"><label class="col-md-3 control-label" for="text-input">' + value.name + '</label>' +
	                            '<div class="col-md-9"><div class="table-responsive"><table class="table table-striped table-bordered bootstrap-datatable datatable">' +
	                            '<thead><tr><th style="width: 50px;">标题</th><th>概要</th><th>原文链接</th><th>发布者</th></tr></thead>' +
	                            '<tbody id="table_' + value.id + '">'+_inner+'</tbody></table>' +
	                            '<div class="bk-margin-bottom-10"><button class="btn btn-info btn-xs add_' + value.id + '">添加数据 <i class="fa fa-plus"></i></button>' +
	                            '    <button class="btn btn-info btn-xs drop_'+value.id+'">删除数据 <i class="fa fa-minus"></i></button></div></div>');
                        $(".add_" + value.id + "").on("click", function () {
                            _data_length++;
                            $("#table_" + value.id + "").append('<tr id="tr_'+value.id+'_'+_data_length+'">' +
                                '<td><input name="key_' + value.id + '_' + _data_length + '"></td>' +
                                '<td><textarea name="text_' + value.id + '_' + _data_length + '" class="col-md-9"></textarea></td>' +
                                '<td><input name="img_' + value.id + '_' + _data_length + '"></td>' +
                                '<td><input name="people_' + value.id + '_' + _data_length + '"></td></tr>');
                        });
                        $(".drop_"+value.id+"").on("click",function () {
                            if(_data_length>1){
                                $("#tr_"+value.id+"_"+_data_length+"").remove();
                                _data_length--;
                            }
                        });
                	});
                }else if(value.name=="投融速递") {
                	$.get("/apis/report/getHtmlData.do?id="+ _id+"&type="+ value.id,function (response) {
                		var _inner="";
                        _data_length=response.data.length;
	            		$.each(response.data,function (_i,v) {
	            			var j= 1+_i;
	            			var _array=""
	            			$.each(v.array,function (_i,vv) {
		            			if(_array==""){
		            				_array+=vv;
		                        }else{
		                        	_array+="、"+vv;
		                        }
	            			});
	            			_inner+='<tr id="tr_'+value.id+'_'+j+'">' +
	                        '<td><input name="key_' + value.id + '_' + j + '" value="'+v.industry+'"></td>' +
	                        '<td><input name="money_' + value.id + '_' + j + '" value="'+v.money+'"></td>' +
	                        '<td><textarea name="text_' + value.id + '_' + j + '" class="col-md-9">'+_array+'</textarea></td>' +
	                        '</tr>';
	            		});
	                    $(".form-horizontal").append('<div class="form-group"><label class="col-md-3 control-label" for="text-input">' + value.name + '</label>' +
	                        '<div class="col-md-9"><div class="table-responsive"><table class="table table-striped table-bordered bootstrap-datatable datatable">' +
	                        '<thead><tr ><th style="width: 50px;">产业</th><th style="width: 50px;">金额(亿人民币)</th><th>推荐公司(<span style="color: red">用中文顿号分割公司</span>)</th></tr></thead>' +
	                        '<tbody id="table_' + value.id + '">'+_inner+'</tbody></table>' +
	                        '<div class="bk-margin-bottom-10"><button class="btn btn-info btn-xs add_' + value.id + '">添加数据 <i class="fa fa-plus"></i></button>' +
	                        '    <button class="btn btn-info btn-xs drop_'+value.id+'">删除数据 <i class="fa fa-minus"></i></button></div></div>');
	                    $(".add_" + value.id + "").on("click", function () {
                            _data_length++;
	                        $("#table_" + value.id + "").append('<tr id="tr_'+value.id+'_'+_data_length+'">' +
	                            '<td><input name="key_' + value.id + '_' + _data_length + '"></td>'+
	                            '<td><input name="money_' + value.id + '_' + _data_length + '"></td>'+
	                            '<td><textarea name="text_' + value.id + '_' + _data_length + '" class="col-md-9"></textarea></td>' +
	                            '</tr>');
	                    });
	                    $(".drop_"+value.id+"").on("click",function () {
	                        if(_data_length>1){
	                            $("#tr_"+value.id+"_"+_data_length+"").remove();
                                _data_length--;
	                        }
	                    });
                	});
                }else {
                    if(value.name=="各地新闻") {
                        $.get("/apis/report/getLocalNews.json?headlinesId="+ value.id,function (_response) {
                            _data_length=_response.data.length;
                            var td = "地域";
                            var _inner="";
                            $.each(_response.data,function (_i,v) {
                                var j= 1+_i;
                                _inner+='<tr id="tr_'+value.id+'_'+j+'">'+
                                    '<td><input name="key_' + value.id + '_' + j + '" value="'+v.keyWord+'"></td>'+
                                    '<td><textarea name="text_' + value.id + '_' + j + '" class="col-md-12">'+v.text+'</textarea></td></tr>';
                            });
                            $(".form-horizontal").append('<div class="form-group"><label class="col-md-3 control-label" for="text-input">' + value.name + '</label>' +
                                '<div class="col-md-9"><div class="table-responsive"><table class="table table-striped table-bordered bootstrap-datatable datatable">' +
                                '<thead><tr><th style="width: 50px;">'+td+'</th><th>文本</th></tr></thead>' +
                                '<tbody id="table_' + value.id + '">'+_inner+'</tbody></table>' +
                                '<div class="bk-margin-bottom-10"><button class="btn btn-info btn-xs add_' + value.id + '">添加数据 <i class="fa fa-plus"></i></button>' +
                                '    <button class="btn btn-info btn-xs drop_'+value.id+'">删除数据 <i class="fa fa-minus"></i></button></div></div>');
                            $(".add_" + value.id + "").on("click", function () {
                                _data_length++;
                                $("#table_" + value.id + "").append('<tr id="tr_'+value.id+'_'+_data_length+'">' +
                                    '<td><input name="key_' + value.id + '_' + _data_length + '"></td>' +
                                    '<td><textarea name="text_' + value.id + '_' + _data_length + '" class="col-md-12"></textarea></td></tr>');
                            });
                            $(".drop_"+value.id+"").on("click",function () {
                                if(_data_length>1){
                                    $("#tr_"+value.id+"_"+_data_length+"").remove();
                                    _data_length--;
                                }
                            });
                        });
                    }else if(value.name=="合作动向") {
                        $.get("/apis/report/getHtmlData.do?id="+ _id+"&type="+ value.id,function (_response) {
                            _data_length=_response.data.length;
                            var td= "关键词";
                            var _inner="";
                            $.each(_response.data,function (_i,v) {
                                var j= 1+_i;
                                _inner+='<tr id="tr_'+value.id+'_'+j+'">'+
                                    '<td><input name="key_' + value.id + '_' + j + '" value="'+v.keyWord+'"></td>'+
                                    '<td><textarea name="text_' + value.id + '_' + j + '" class="col-md-12">'+v.text+'</textarea></td></tr>';
                            });
                            $(".form-horizontal").append('<div class="form-group"><label class="col-md-3 control-label" for="text-input">' + value.name + '</label>' +
                                '<div class="col-md-9"><div class="table-responsive"><table class="table table-striped table-bordered bootstrap-datatable datatable">' +
                                '<thead><tr><th style="width: 50px;">'+td+'</th><th>文本</th></tr></thead>' +
                                '<tbody id="table_' + value.id + '">'+_inner+'</tbody></table>' +
                                '<div class="bk-margin-bottom-10"><button class="btn btn-info btn-xs add_' + value.id + '">添加数据 <i class="fa fa-plus"></i></button>' +
                                '    <button class="btn btn-info btn-xs drop_'+value.id+'">删除数据 <i class="fa fa-minus"></i></button></div></div>');
                            $(".add_" + value.id + "").on("click", function () {
                                _data_length++;
                                $("#table_" + value.id + "").append('<tr id="tr_'+value.id+'_'+_data_length+'">' +
                                    '<td><input name="key_' + value.id + '_' + _data_length + '"></td>' +
                                    '<td><textarea name="text_' + value.id + '_' + _data_length + '" class="col-md-12"></textarea></td></tr>');
                            });
                            $(".drop_"+value.id+"").on("click",function () {
                                if(_data_length>1){
                                    $("#tr_"+value.id+"_"+_data_length+"").remove();
                                    _data_length--;
                                }
                            });
                        });
                    }else if(value.name=="企业动向") {
                        $.get("/apis/report/getHtmlData.do?id="+ _id+"&type="+ value.id,function (_response) {
                            _data_length=_response.data.length;
                            var td="企业";
                            var _inner="";
                            $.each(_response.data,function (_i,v) {
                                var j= 1+_i;
                                _inner+='<tr id="tr_'+value.id+'_'+j+'">'+
                                    '<td><input name="key_' + value.id + '_' + j + '" value="'+v.keyWord+'"></td>'+
                                    '<td><textarea name="text_' + value.id + '_' + j + '" class="col-md-12">'+v.text+'</textarea></td></tr>';
                            });
                            $(".form-horizontal").append('<div class="form-group"><label class="col-md-3 control-label" for="text-input">' + value.name + '</label>' +
                                '<div class="col-md-9"><div class="table-responsive"><table class="table table-striped table-bordered bootstrap-datatable datatable">' +
                                '<thead><tr><th style="width: 50px;">'+td+'</th><th>文本</th></tr></thead>' +
                                '<tbody id="table_' + value.id + '">'+_inner+'</tbody></table>' +
                                '<div class="bk-margin-bottom-10"><button class="btn btn-info btn-xs add_' + value.id + '">添加数据 <i class="fa fa-plus"></i></button>' +
                                '    <button class="btn btn-info btn-xs drop_'+value.id+'">删除数据 <i class="fa fa-minus"></i></button></div></div>');
                            $(".add_" + value.id + "").on("click", function () {
                                _data_length++;
                                $("#table_" + value.id + "").append('<tr id="tr_'+value.id+'_'+_data_length+'">' +
                                    '<td><input name="key_' + value.id + '_' + _data_length + '"></td>' +
                                    '<td><textarea name="text_' + value.id + '_' + _data_length + '" class="col-md-12"></textarea></td></tr>');
                            });
                            $(".drop_"+value.id+"").on("click",function () {
                                if(_data_length>1){
                                    $("#tr_"+value.id+"_"+_data_length+"").remove();
                                    _data_length--;
                                }
                            });
                        });
                    }
                }
                $(".btn-success").on("click",function(){
                    result.push({
                        title:value.name,
                        name:value.id,
                        value:_data_length
                    });
                    savaFocus();
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
    var index = layer.load();
    var _data =new Array();
    if(result.length==_size){
        for(var j=0;j<result.length;j++){
            var _schedule=null;
            if(result[j].title=="会议日程"){
                _schedule=new Array();
                for(var n=1;n<=result[j].value;n++){
                    _schedule.push({
                        date:$("input[name='hui_ri_"+result[j].name+"_"+n+"']").val(),
                        place:$("input[name='hui_di_"+result[j].name+"_"+n+"']").val(),
                        sponsor:$("input[name='hui_zhu_"+result[j].name+"_"+n+"']").val()
                    });
                }
                _data.push({
                    headlinesId:result[j].name,
                    money:$("input[name='money_"+result[j].name+"_1']").val(),
                    time:$("input[name='time_"+result[j].name+"_1']").val(),
                    text:$("textarea[name='text_"+result[j].name+"_1']").val(),
                    keyWord:$("input[name='key_"+result[j].name+"_"+i+"']").val(),
                    schedule:_schedule
                });
            }else{
                for(var i=1;i<=result[j].value;i++){
                    _data.push({
                        headlinesId:result[j].name,
                        keyWord:$("input[name='key_"+result[j].name+"_"+i+"']").val(),
                        text:$("textarea[name='text_"+result[j].name+"_"+i+"']").val(),
                        img:$("input[name='img_"+result[j].name+"_"+i+"']").val(),
                        money:$("input[name='money_"+result[j].name+"_"+i+"']").val(),
                        time:$("input[name='time_"+result[j].name+"_"+i+"']").val(),
                        people:$("input[name='people_"+result[j].name+"_"+i+"']").val()
                    });
                }
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
                    layer.close(index);
                    layer.alert(response.message);
                }
            }
        });
    }
}


