$("#report_info").addClass("active nav-expanded ");
$("#html_item").addClass("active");
var i=1;
var _id;
function addData(id) {
    _id = id;
}
$(function () {
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
                var keyWord="";
                $.each(data.keyWord, function (index, c) {
                    keyWord+='<label style="font-weight:normal;margin-top:7px">'+c.text+'</label><br>' +
                        '<label class="control-label" for="text-input"><b>关键词：</b>'+c.keyWord+'</label><br>';
                });
                $(".keyWord").html(keyWord);
                var chain="";
                $.each(data.chain, function (index, c) {
                    chain+='<label style="font-weight:normal;margin-top:7px">'+c.text+'</label><br>' +
                        '<label class="control-label" for="text-input"><b>高亮词汇：</b>'+c.keyWord+'</label><br>';
                });
                $(".chain").html(chain);
                $(".focus").html('<label class="control-label"><a href="/apis/report/focusInfo.html?id='+_id+'">点击查看详情</a></label>');
                $(".dynamic").html('<label class="control-label"><a href="/apis/report/dynamicInfo.html?id='+_id+'">点击查看详情</a></label>');
                var recommend="<br>";
                recommend+='<label class="col-md-2 control-label" for="text-input"><b>企业</b></label><div class="col-md-9">';
                $.each(data.recommend.company, function (index, c) {
                    recommend+='<label class="control-label">'+c.name+'</label>&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">'+c.reason+'</label><br>';
                });
                recommend+='</div><br><label class="col-md-2 control-label" for="text-input"><b>人物</b></label><div class="col-md-9">';
                var c=data.recommend.people;
                recommend+='<label class="control-label">'+c.name+'</label><br><label class="control-label">'+c.identity+
                    '</label><br><label class="control-label">'+c.reason+'</label><br>';
                recommend+='</div>';
                $(".recommend").html(recommend);
                var obj =data.industry;
                var industry="<br>";
                industry+='<label class="col-md-2 control-label" for="text-input"><b>龙头企业</b></label><div class="col-md-9">';
                $.each(obj.faucet, function (index, c) {
                    industry+='<label class="control-label">'+c.company+'</label>&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">'+c.money+'</label>' +
                        '&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">'+c.time+'</label>&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">'+c.text+'</label><br>';
                });
                industry+='</div><br><label class="col-md-2 control-label" for="text-input"><b>成长企业</b></label><div class="col-md-9">';
                $.each(obj.growth, function (index, c) {
                    industry+='<label class="control-label">'+c.company+'</label>&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">'+c.money+'</label>' +
                        '&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">'+c.time+'</label>&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">'+c.text+'</label><br>';
                });
                industry+='</div><label class="col-md-2 control-label" for="text-input"><b>潜力企业</b></label><div class="col-md-9">';
                $.each(obj.potential, function (index, c) {
                    industry+='<label class="control-label">'+c.company+'</label>&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">'+c.money+'</label>' +
                        '&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">'+c.time+'</label>&nbsp;&nbsp;&nbsp;&nbsp;<label class="control-label">'+c.text+'</label><br>';
                });
                industry+='</div>';
                $(".industry").html(industry);
            }else{
                layer.alert(response.message);
            }
        }
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
        var _chain = new Array();
        for(var i=1;i<4;i++){
            var groupCheckbox=$("input[name='inline-checkbox"+i+"']");
            var _val = new Array();
            for(var j=0;j<groupCheckbox.length;j++){
                if(groupCheckbox[j].checked){
                    var val =groupCheckbox[j].value;
                    _val.push(val);
                }
            }
            _chain.push({
                text:$("input[name='chain"+i+"']").val(),
                key:_val
            });
        }
        var _focus = new Array();
        var groupCheckbox=$("input[name='inline-checkbox4']");
        for(var j=0;j<groupCheckbox.length;j++){
            if(groupCheckbox[j].checked){
                var val =groupCheckbox[j].value;
                _focus.push(val);
            }
        };
        var _company =new Array();
        for(var i=1;i<5;i++){
            _company.push({
                name:$("input[name='company_name"+i+"']").val(),
                reason:$("textarea[name='company_reason"+i+"']").val(),
                logo:$("input[name='company_logo"+i+"']").val()
            });
        }
        var _recommend={
            company:_company,
            people:{
                reason:$("textarea[name='people_reason']").val(),
                identity:$("input[name='people_identity']").val(),
                name:$("input[name='people_name']").val(),
                logo:$("input[name='people_logo']").val()
            }
        };
        var _dynamic = new Array();
        var groupCheckbox=$("input[name='inline-checkbox5']");
        for(var j=0;j<groupCheckbox.length;j++){
            if(groupCheckbox[j].checked){
                var val =groupCheckbox[j].value;
                _dynamic.push(val);
            }
        };
        var _faucet =new Array();
        for(var i=1;i<4;i++){
            _faucet.push({
                company:$("input[name='industry_name"+i+"']").val(),
                money:$("input[name='industry_money"+i+"']").val(),
                time:$("input[name='industry_time"+i+"']").val(),
                text:$("input[name='industry"+i+"']").val()
            });
        }
        var _growth =new Array();
        for(var i=4;i<7;i++){
            _growth.push({
                company:$("input[name='industry_name"+i+"']").val(),
                money:$("input[name='industry_money"+i+"']").val(),
                time:$("input[name='industry_time"+i+"']").val(),
                text:$("input[name='industry"+i+"']").val()
            });
        }
        var _potential =new Array();
        for(var i=7;i<10;i++){
            _potential.push({
                company:$("input[name='industry_name"+i+"']").val(),
                money:$("input[name='industry_money"+i+"']").val(),
                time:$("input[name='industry_time"+i+"']").val(),
                text:$("input[name='industry"+i+"']").val()
            });
        }
        var _industry={
            faucet:_faucet,
            growth:_growth,
            potential:_potential
        };
        var req={
            name:_name,
            time:_time,
            keyWord:_keyWord,
            chain:_chain,
            focus:_focus,
            recommend:_recommend,
            dynamic:_dynamic,
            industry:_industry
        }
        $.ajax({
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

