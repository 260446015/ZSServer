var industry ="全部";
var registerTime = new Array();
var area = "全部";
var register= new Array();
$("#screen").addClass("active");
$("#searchTag").on("click",".search-tag span.close",function () {
	var del = $(this).parent().text();
	$(".search-box").find(".active").each(function(){
		if(del.indexOf($(this).html()) >= 0){
			$(this).removeClass("active");
			if($(this).parent().find(".active").length == 0){
				$(this).parent().children().eq(0).addClass("active");
			}
		}
	})
	$(this).parent().remove();
	updateLabel();
	searchAjax();
});
$(function () {
		getTab();
});
function getTab(){
	$.ajax({
		url:'/apis/user/getLabel.json',
		type:'GET',
		async:false,
		success:function(res){
			if(res.data==null){
				 $("#myModal").modal("show");
			}else{
				arr = [];
				arr.push(res.data.industry);
				industry = res.data.industry;
				$(".search-box").find(".search-item-content").eq(0).find("a").each(function(){
					if($(this).text() == industry){
						$(this).addClass("active");
					}
				})
				arr.push(res.data.area);
				area = res.data.area;
				$(".search-box").find(".search-item-content").eq(1).find("a").each(function(){
					if($(this).text() == area){
						$(this).addClass("active");
					}
				})
				registerTime = [];
				for(var i = 0;i<res.data.registerTime.length;i++){
					if(res.data.registerTime[i] == ""){
						continue;
					}
					arr.push(res.data.registerTime[i]);
					registerTime.push(res.data.registerTime[i]);
					$(".search-box").find(".search-item-content").eq(2).find("a").each(function(){
						if($(this).text() == res.data.registerTime[i]){
							$(this).addClass("active");
						}
					})
				}
				register = [];
				for(var i = 0;i<res.data.register.length;i++){
					if(res.data.register[i] == ""){
						continue;
					}
					arr.push(res.data.register[i]);
					register.push(res.data.register[i]);
					$(".search-box").find(".search-item-content").eq(3).find("a").each(function(){
						if($(this).text() == res.data.register[i]){
							$(this).addClass("active");
						}
					})
				}
				 $("#searchTag").html(TagList(arr));
				   $("#horizontal-info").hide();
				    searchAjax();
			}
		}
	});
};
function updateLabel(){
	var label = $(".search-box").find(".search-item-content");
	label.each(function(i){
		if(i == 0){
			industry = $(this).find(".active").text();
		}else if(i == 1){
			area = $(this).find(".active").text();
		}else if(i == 2){
			registerTime = [];
			$(this).find(".active").each(function(){
				registerTime.push($(this).text());
			});
		}else if(i == 3){
			register = [];
			$(this).find(".active").each(function(){
				register.push($(this).text());
			});
		}
	})
	var req = {'industry':industry,'area':area,'registerTime':registerTime,'register':register}
	$.ajax({
	  url:'/apis/user/updateLabel.json',
	  type:'post',
	  data:JSON.stringify(req),
	  contentType:'application/json',
	  success:function(res){
		  if(res.data != null){
			  console.log("更新标签成功");
		  }
	  }
	});
};
function searchTab(a,b){
	if(a == 1){
		industry = b;
	}else if(a == 2){
		area = b;
	}else if(a == 3){
		registerTime = b;
	}else if(a==4){
		register = b;
	}
};
$(".search-box").on("click",".search-item-content>a",function(){
	var esTime = $('#esTime').children().text().replace('全部','');
	var capital = $('#capital').children().text().replace('全部','');
	if(esTime.indexOf($(this).html()) != -1){
		$(this).addClass("active");
		$(this).siblings().eq(0).removeClass('active');
	}else if(capital.indexOf($(this).html()) != -1){
		$(this).addClass("active");
		$(this).siblings().eq(0).removeClass('active');
	}else{
		var _id = $(this).attr("id");
		$(this).addClass("active").siblings().removeClass("active");
	}
});
$("#search_tag").on("click",function () {
    $("#myModal").modal("show");
   
});
$("#LabelBlue").click(function(){
	$("#myModal").modal("hide");
		var arr = [];
		var s = $(".search-box").find('.search-item-content');
		s.each(function(i){
			if(i == 0){
				industry = $(this).find('.active').text();
			}else if(i == 1){
				area = $(this).find('.active').text();
			}else if(i == 2){
				registerTime = [];
				var reTime = $(this).find('.active');
				reTime.each(function(){
					var h = $(this).html();
					registerTime.push(h);
				});
			}else if(i == 3){
				register = [];
				var reg = $(this).find('.active');
				reg.each(function(){
					var r = $(this).html();
					register.push(r);
				});
			}
		})
		var select = $(".search-box").find('.active');
		arr = [];
		select.each(function(){
			arr.push($(this).html());
		});
	    $("#searchTag").html(TagList(arr));
	   $("#horizontal-info").hide();
	   updateLabel();
	   searchAjax();
	
});
function TagList(arr){
	var array=[];
	$.each(arr,function(index,item){
		if(item=="全部"){
			return true;
		}else{
			var i = index+1;
			array.push(
					'<button class="btn btn-fill btn-blue search-tag" id="'+i+'+'+item+'">'+item
					+'<span type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</span></button>'
			);
		}
	});
	var inner = array.join('');
	return inner;
};

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
					strHtml += "</div>"
					if(res.data[i].industry!=null){
						strHtml += "<small class='smaa smCircle'>"+"<i>"+res.data[i].industry+"</i></small>" ;    
					}
					if(res.data[i].industryLabel!=null){
						strHtml += "<small class='smbb smCircle'><i>"+res.data[i].industryLabel+"</i></small>" ;  
					}
					if(res.data[i].industryZero!=null){
						strHtml += "<small class='smcc smCircle'><i>"+res.data[i].industryZero+"</i></small></li>" ;       
					}    
				}
			}
		$('.Precisecircles ul').html(strHtml);
	}   
}) 

function searchAjax(){
	var param = {'industry':industry,'area':area,'registerTime':registerTime,'register':register};
	$.ajax({
		url:'/accurateScreening/getCompanyInfoBySearch.json',
		type:'POST',
		async: false,
        contentType: 'application/json',
		data:JSON.stringify(param),
		success:function(res){
			var res = res.data.slice(0,10);
			if(res.message!=null){
            	 $(".Precisecircles").html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
            }else{	

				var strHtml = "";
				for(var i=0;i<res.length;i++){
						if(res[i].companyName!=null){
							if(res[i].companyName.length>7){   
								strHtml +="<li class='bigborder'><div class='bigdiv'>"
							}else{
								strHtml +="<li class='smallborder'><div class='smalldiv'>"
							}  
							strHtml += "<p>"+res[i].companyName+"</p>"
							strHtml += "</div>"
							if(res[i].industry!=null){
								strHtml += "<small class='smaa smCircle'>"+"<i>"+res[i].industry+"</i></small>" ;    
							}
							if(res[i].industryLabel!=null){
								strHtml += "<small class='smbb smCircle'><i>"+res[i].industryLabel+"</i></small>" ;  
							}
							if(res[i].industryZero!=null){
								strHtml += "<small class='smcc smCircle'><i>"+res[i].industryZero+"</i></small></li>" ;       
							}    
						}
					}
				$('.Precisecircles ul').html(strHtml);
				$(".Precisecircles ul>li").each(function(index,el){
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
		}
	});
	$('.uls li').on("click",function (e){  
		var str = $(this).find('p').text();
		$.ajax({
				url:'/intelligent/getCompanyInfoByName.json?name='+str,
				   type:'GET',
				   async: false,
				   success:function(base){
					   console.log(base)
					   if(base.data==null){
						   new Alert({flag:false,text:base.message,timer:1000}).show();
					   }else{
						 $('#horizontal-info').html(
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
			})
};
function initEchartData(array,datalist,dataListInfo){
	var data=[];
	var b=[];
	var c =[];
	$.each(array,function(index,item){
		var a = datalist[index];
		if(index<=7){
			a[2]=item.companyName;
			data.push(b.push(a));
			c.push(item.industry);
			c.push(item.induszero);
			c.push(item.industryLabel);
		}
		if(index==(array.length-1)){
			c.push(item.industry);
			c.push(item.induszero);
			c.push(item.industryLabel);
		}
	});
	$.each(c,function(index,item){
		var a =  dataListInfo[index];
		var d = [];
		if(item==null||item==""){
			a[2]=item;
			a[3]=0;
		}else{
			a[2]=item;
		}
		data.push(d.push(a));
	});
	return data;
};
function initEchartData(array,datalist,dataListInfo){
	var data=[];
	var b=[];
	$.each(array,function(index,item){
		var a = datalist[index];
		if(index<=7){
			a[2]=item.companyName;
			data.push(b.push(a));
		}
		
	});
	
	return data;
};
// 关闭内容
$("#horizontal-info").on("click",".close",function () {
	$(this).parents(".layer-person").hide();
});
