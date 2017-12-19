$(function(){
	$("#followItem").addClass("active");
	$("#organizationItem").addClass("active");
	$.ajax({  
        url: "/apis/follow/findOrganizationList.json",  
        async: false,  
        data:{industry:'人工智能',pageNum:0},
        success: function (result) {  
        	if(result.success){
        	}else{
        		new Alert({flag:false,text:result.message,timer:2000}).show();
        	}
        }  
    }); 
});
/*
 *                             <div class="institution-item">
                                <div class="item-title">
                                    <span class="icon-block bgc-green"></span>
                                    <span>大数据挖掘</span>
                                </div>
                                <div class="item-body">
                                    <div class="top">
                                        <div class="left">
                                            <p>
                                                <span class="mr40">机构性质</span>
                                                <span>国家重点实验室</span>
                                            </p>
                                            <p>
                                                <span class="mr40">机构产业</span>
                                                <span>大数据</span>
                                            </p>
                                            <p>
                                                <span class="mr40">依托单位</span>
                                                <span>中国科学院数学</span>
                                            </p>
                                            <p>
                                                <span class="mr40">机构网址</span>
                                                <span>http://www.baidu.com</span>
                                            </p>
                                        </div>
                                        <div class="right">
                                            <div>
                                                <img src="/images/user_head.png" alt="">
                                                <p>孙悟空</p>
                                            </div>
                                            <div>
                                                <img src="/images/user_head.png" alt="">
                                                <p>孙悟空</p>
                                            </div>
                                            <div>
                                                <img src="/images/user_head.png" alt="">
                                                <p>孙悟空</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="bottom">
                                        <span class="mr40  btn-clicked">意向联络</span>
                                        <span>取消关注</span>
                                    </div>
                                </div>
                            </div>

 */
