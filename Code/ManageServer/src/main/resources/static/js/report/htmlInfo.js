$("#report_info").addClass("active nav-expanded ");
$("#html_item").addClass("active");
var _id;
function addData(id) {
    _id = id;
}
$(function () {
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
                var focus="";
                $.each(data.focus, function (index, c) {
                    focus+='<br><label class="col-md-2 control-label" for="text-input"><b>'+c.name+'</b></label><div class="col-md-9"><label class="control-label">点击查看详情</label></div>';
                });
                $(".focus").html(focus);
                var dynamic="";
                $.each(data.dynamic, function (index, c) {
                    dynamic+='<br><label class="col-md-2 control-label" for="text-input"><b>'+c.name+'</b></label><div class="col-md-9"><label class="control-label">点击查看详情</label></div>';
                });
                $(".dynamic").html(dynamic);
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
});

