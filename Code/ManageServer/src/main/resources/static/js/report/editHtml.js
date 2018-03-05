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
                $("input[name='name']").val(data.info.name);
                $("input[name='time']").val(data.info.time);
                for(var i=1;i<4;i++){
                    $("input[name='key"+i+"']").val(data.keyWord[i-1].text);
                    $("input[name='h_key"+i+"']").val(data.keyWord[i-1].id);
                    var _val=""
                    $.each(data.keyWord[i-1].keyWord, function (index, c) {
                        if (index == 0) {
                            _val += c;
                        } else {
                            _val += "、" + c;
                        }
                    });
                    $("#key"+i+"").html(_val);
                }
                for(var i=1;i<4;i++){
                    $("input[name='h_chain"+i+"']").val(data.chain[i-1].id);
                    $("input[name='chain"+i+"']").val(data.chain[i-1].text);
                    var groupCheckbox=$("input[name='inline-checkbox"+i+"']");
                    for(var j=0;j<groupCheckbox.length;j++){
                        var val =groupCheckbox[j].value;
                        $.each(data.chain[i-1].keyWord, function (index, c) {
                            if (c == val) {
                                groupCheckbox[j].checked =true;
                            }
                        });
                    }
                }
                $("textarea[name='people_reason']").val(data.recommend.people.reason);
                $("input[name='people_identity']").val(data.recommend.people.identity);
                $("input[name='people_name']").val(data.recommend.people.name);
                $("input[name='h_people_name']").val(data.recommend.people.id);
                $("input[name='people_logo']").val(data.recommend.people.logo);
                for(var i=1;i<5;i++){
                    $("input[name='h_company_name"+i+"']").val(data.recommend.company[i-1].id);
                    $("input[name='company_name"+i+"']").val(data.recommend.company[i-1].name);
                    $("input[name='h_company_name"+i+"']").val(data.recommend.company[i-1].id);
                    $("textarea[name='company_reason"+i+"']").val(data.recommend.company[i-1].reason);
                    $("input[name='company_logo"+i+"']").val(data.recommend.company[i-1].logo);
                }
                for(var i=1;i<4;i++){
                    $("input[name='h_industry_name"+i+"']").val(data.industry.faucet[i-1].id);
                    $("input[name='industry_name"+i+"']").val(data.industry.faucet[i-1].company);
                    $("input[name='industry_money"+i+"']").val(data.industry.faucet[i-1].money);
                    $("input[name='industry_time"+i+"']").val(data.industry.faucet[i-1].time);
                    $("input[name='industry"+i+"']").val(data.industry.faucet[i-1].text);
                }
                for(var i=4;i<7;i++){
                    $("input[name='h_industry_name"+i+"']").val(data.industry.growth[i-4].id);
                    $("input[name='industry_name"+i+"']").val(data.industry.growth[i-4].company);
                    $("input[name='industry_money"+i+"']").val(data.industry.growth[i-4].money);
                    $("input[name='industry_time"+i+"']").val(data.industry.growth[i-4].time);
                    $("input[name='industry"+i+"']").val(data.industry.growth[i-4].text);
                }
                for(var i=7;i<10;i++){
                    $("input[name='h_industry_name"+i+"']").val(data.industry.potential[i-7].id);
                    $("input[name='industry_name"+i+"']").val(data.industry.potential[i-7].company);
                    $("input[name='industry_money"+i+"']").val(data.industry.potential[i-7].money);
                    $("input[name='industry_time"+i+"']").val(data.industry.potential[i-7].time);
                    $("input[name='industry"+i+"']").val(data.industry.potential[i-7].text);
                }
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
                id:$("input[name='h_key"+i+"']").val(),
                text:$("input[name='key"+i+"']").val(),
                key:$("#key"+i+"").html()
            });
        }
        var _chain = new Array();
        for(var i=1;i<4;i++){
            var groupCheckbox=$("input[name='inline-checkbox"+i+"']");
            var _val = "";
            for(var j=0;j<groupCheckbox.length;j++){
                if(groupCheckbox[j].checked){
                    var val =groupCheckbox[j].value;
                    if(_val==""){
                        _val+=val;
                    }else{
                        _val+="、"+val;
                    }
                }
            }
            _chain.push({
                id:$("input[name='h_chain"+i+"']").val(),
                text:$("input[name='chain"+i+"']").val(),
                key:_val
            });
        }
        var _company =new Array();
        for(var i=1;i<5;i++){
            _company.push({
                name:$("input[name='company_name"+i+"']").val(),
                id:$("input[name='h_company_name"+i+"']").val(),
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
                id:$("input[name='h_people_name']").val(),
                logo:$("input[name='people_logo']").val()
            }
        };
        var _faucet =new Array();
        for(var i=1;i<4;i++){
            _faucet.push({
                company:$("input[name='industry_name"+i+"']").val(),
                id:$("input[name='h_industry_name"+i+"']").val(),
                money:$("input[name='industry_money"+i+"']").val(),
                time:$("input[name='industry_time"+i+"']").val(),
                text:$("input[name='industry"+i+"']").val()
            });
        }
        var _growth =new Array();
        for(var i=4;i<7;i++){
            _growth.push({
                company:$("input[name='industry_name"+i+"']").val(),
                id:$("input[name='h_industry_name"+i+"']").val(),
                money:$("input[name='industry_money"+i+"']").val(),
                time:$("input[name='industry_time"+i+"']").val(),
                text:$("input[name='industry"+i+"']").val()
            });
        }
        var _potential =new Array();
        for(var i=7;i<10;i++){
            _potential.push({
                company:$("input[name='industry_name"+i+"']").val(),
                id:$("input[name='h_industry_name"+i+"']").val(),
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
            id:_id,
            name:_name,
            time:_time,
            keyWord:_keyWord,
            chain:_chain,
            recommend:_recommend,
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
                    layer.alert("操作成功");
                    window.location.href="/apis/report/htmlReport.html";
                }else{
                    layer.alert(response.message);
                }
            }
        });
    });
    $(".btn_focus").on("click",function (){
        window.location.href="/apis/report/editFocus.html?id="+_id;
    });
    $(".btn_dy").on("click",function (){
        window.location.href="/apis/report/editDynamic.html?id="+_id;
    });
});

