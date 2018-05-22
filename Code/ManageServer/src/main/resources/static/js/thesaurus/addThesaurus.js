var index=1;
var length=1;
var option="";
$(function () {
    $(".btn-success").on("click",function(){
        var _name = $("input[name='name']").val();
        var _type=$("#select option:selected").val();
        var _descrip = $("input[name='descrp']").val();
        var arr = new Array();
        var i=1;
        var j=1;
        while (true){
            var _options=$("#dealer_"+i+" option:selected").val();
            var _describe = $("input[name='name_"+i+"']").val();
            if(typeof(_options) != "undefined"){
                arr.push({
                    options:_options,
                    describe:_describe
                });
                j++;
            }
            i++;
            if(j==length) break;
            if(i>50) break;
        }
        var param={"id":0,"name":_name,"type":_type,"descrip":_descrip,"msg":arr};
        $.ajax({
        	type:'POST',
        	url:'/apis/keyInfo/addOrUpdateKeyWordData.json',
        	asynyc:false,
    		contentType:'application/json',
    		data:JSON.stringify(param),
    		success:function(res){
    			if(res.data==true){
    				window.location.href="/apis/keyInfo/ThesaurusManage.html";
    			}else{
    				window.location.href="/apis/keyInfo/addThesaurus.html";
    			}
    		}
        });
    });
    $(".btn-danger").on("click",function(){
        layer.confirm('直接离开将会失去修改内容，确认离开？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            window.location.href="/apis/keyInfo/ThesaurusManage.html";
        });
    });
    var param ={type:"全部",pageSize:100,pageNumber:0};
    $.ajax({
		type:'POST',
		url:'/apis/keyInfo/findKeyWordInfo.json',
		asynyc:false,
		contentType:'application/json',
		data:JSON.stringify(param),
		success:function(response){
                if(response.success){
                    $.each(response.data.dataList,function (i,e) {
                    	
                        option+="<option value='"+e.entntity.id+"'>"+e.entntity.keyword+"("+e.entntity.type+")</option>";
                    });
                }else{
                    layer.alert(response.message);
                }
            }
        });
    $(".my_add").on("click",function(){
        $("#form-info").append('<div class="form-group add_'+index+'">' +
            '<label class="col-sm-3 control-label" for="state">关联关系</label>' +
            '<div class="col-sm-3">' +
            '<select name="dealer_'+index+'" id="dealer_'+index+'" class="selectpicker show-tick form-control"  data-width="98%" data-first-option="false" required data-live-search="true">' +
            option +'</select>' +
            '</div>' +
            '<div class="col-md-3">' +
            '<input type="text" name="name_'+index+'" class="form-control" placeholder="请填写关联关系">' +
            '</div>' +
            '<button class="btn btn-info btn-xs drop_'+index+'">删除 <i class="fa fa-minus"></i></button>' +
            '</div>');
        $(".selectpicker" ).selectpicker('refresh')
        $(".drop_"+index+"").on("click",function () {
            var a = $(this).attr('class');
            var s =a.substring(25,a.length);
            $(".add_"+s+"").remove();
            length--;
        });
        index++;
        length++;
    });
});