$(function(){
    $.ajax({
        url:'/intelligent/list.json',
        type:'GET',
        async: false,
        success:function(res){
            var strHtml = "";
            for(var i=0;i<res.data.length;i++){
                    if(res.data[i].companyName!=null){
                        if(res.data[i].companyName.length>7){   
                            strHtml +="<li class='bigborder'><div class='bigdiv'>"
                        }else{
                            strHtml +="<li class='smallborder'><div class='smalldiv'>"
                        }  
                        strHtml += "<p>"+res.data[i].companyName+"</p>"
                        if(res.data[i].flag!=false){
                            strHtml += "<span>new</span>"
                        }
                        strHtml += "</div>"
                        if(res.data[i].industry!=null && res.data[i].industry!=''){
                            strHtml += "<small class='sma smCircle'>"+"<i>"+res.data[i].industry+"</i></small>" ;    
                        }
                        if(res.data[i].industryLabel!=null){
                            strHtml += "<small class='smb smCircle'><i>"+res.data[i].industryLabel+"</i></small>" ;  
                        }
                        if(res.data[i].industryZero!=null){
                            strHtml += "<small class='smc smCircle'><i>"+res.data[i].industryZero+"</i></small></li>" ;       
                        }    
                    }
                }
            $('.circles ul').html(strHtml);
            $(".circles ul>li").each(function(index,el){
                $(el).hover(function(){
                    if($(el).children("small").length!=0){
                        $(el).css('border','1px solid #7a8798')
                        $(el).find('small').css('display','block')
                      }
                },function(){
                    $(el).css('border','0')
                    $(el).find('small').css('display','none')
                })
               $('.smCircle>i').hover(function(){
                   $('.smCircle>i').addClass('spot')
               },function(){
                $('.smCircle>i').removeClass('spot')
               })
            })    
           
        }   
    })
   $('.uls li').on("click",function (e){  
    var str = $(this).find('p').text();
    // var name = e.str;
    $.ajax({
        	url:'/intelligent/getCompanyInfoByName.json?name='+str,
           	type:'GET',
           	async: false,
           	success:function(base){
                   console.log(base)
           		if(base.data==null){
           			new Alert({flag:false,text:base.message,timer:1000}).show();
           		}else{
                       console.log(str)
           		  $('#layer-person-info').html(
           				  '<h3 class="layer-person-title text-center">'
           				  +base.data.name+'<button type="button" class="close">×</button></h3>'
           				  +'<div class="layer-body small-line-height"><div class="form-horizontal">'
           				  +'<div class="form-group"><label class="col-md-4 text-right control-label">法人代表</label>'
           				  +'<div class="col-md-7"><p class="form-control-static" >'+base.data.boss+'</p></div></div>'
           				  +'<div class="form-group"><label class="col-md-4 text-right control-label">状态</label>'
           				  +'<div class="col-md-7"><p class="form-control-static" >'+base.data.state+'</p></div></div>'
           				  +'<div class="form-group"><label class="col-md-4 text-right control-label">注册时间</label>'
           				  +'<div class="col-md-7"><p class="form-control-static" >'+base.data.time+'</p></div></div>'
           				  +'<div class="form-group"><label class="col-md-4 text-right control-label">行业</label>'
           				  +'<div class="col-md-7"><p class="form-control-static" >'+base.data.industry+'</p></div></div>'
           				  +'<div class="form-group"><label class="col-md-4 text-right control-label">注册资本</label>'
           				  +'<div class="col-md-7"><p class="form-control-static" >'+base.data.money+'</p></div></div>'
           				  +'<div class="form-group"><label class="col-md-4 text-right control-label">注册地址</label>'
           				  +'<div class="col-md-7"><p class="form-control-static" >'+base.data.address+'</p></div></div>'
           				  +'</div></div>' +'<div class="layer-footer text-center" >'
           				  +'<a href="/apis/company/baseInfo.html?companyName='+base.data.name+'" class="like">查看更多</a></div>'
                     );   
           		  $(".layer-person").css({
           		        display: "block",
           		        top: e.clientY-200,
           		        left: e.clientX-200
           		    });
           		}
           		}
        	});
      })
      $(".layer-person").on("click",".text-center .close",function () {
        	$(this).parents("#layer-person-info").hide();
        });
})
