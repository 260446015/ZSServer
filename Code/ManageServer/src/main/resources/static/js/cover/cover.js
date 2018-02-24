$(function(){
    // $.ajax({
    //     url:'/apis/report/getHtmlData.do?id=5',
    //     type:'GET',
    //     async:false,
    //     success:function(data){
    //         var strHtml = "";
    //             strHtml += "<p>"+data.data.time+"</p>"
    //         $(".time").html(strHtml);
    //     }
    // })
    $.ajax({
        url:'/apis/report/getHtmlData.do?id=5&chain',
        type: 'GET',
        async:false,
        success:function(res){
            console.log(res)
        }
    })
})