<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>基本信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="慧数招商平台，是一个关于园区产业招商的大数据管理平台">
    <meta name="keywords" content="慧数，招商，慧数招商，招商平台，园区，园区招商，园区招商平台，科技园，产业园，大数据，产业">
    <meta name="author" content="张鑫，慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
    <!-- css共用部分 start -->
    <#include "/common/link.ftl"/>
    <!-- css 共用部分 end -->
    <link rel="stylesheet" href="/css/basicInfo.css">
    <!-- js 兼容低版本IE start -->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- js 兼容低版本IE end -->
</head>
<body class="bg2">
<#include "/common/header3.ftl"/>
<!-- header部分  end -->
<div class="wrapper">
    <div class="page-content">
        <#include "/common/UserSidebar.ftl"/>
        <div class="right-content">
            <div class="container">
                <div class="model-box">
                    <!--<div class="model-header">
                        <h3 class="model-title">参展企业</h3>
                    </div>-->
                    <div class="model-body">
                        <div class="border-shadow-box">
                            <div class="basic-info-box">
                                <div class="info-item">
                                    <div class="info-title">
                                        <span class="icon-block"></span>
                                        <span>个人中心</span>
                                    </div>
                                    <div class="info-content">
                                        <div class="img-box">
                                            <img src="/images/user_head.png" alt="">
                                           <div class="account-info">
                                               <p>
                                                   <span>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span>
                                                   <span id="userAccount"></span>
                                               </p>
                                               <p>
                                                   <span>注册时间</span>
                                                   <span id="createTime"></span>
                                               </p>
                                           </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="info-item">
                                    <div class="info-title">
                                        <span class="icon-block"></span>
                                        <span>基本信息</span>
                                    </div>
                                    <div class="info-content">
                                        <div class="info-form">
                                            <form class="form-horizontal" action="javascript:void(0)">
                                                <div class="form-group form-group-sm no-height">
                                                </div>
                                                <div class="form-group form-group-sm mb30">
                                                    <label class="col-sm-4 control-label info-label" for="name">姓名</label>
                                                    <div class="col-sm-5">
                                                        <input class="form-control blue-border" type="text" id="name" placeholder="">
                                                    </div>
                                                    <div class="namestatus"></div>
                                                </div>
                                                <div class="form-group form-group-sm mb30">
                                                    <label class="col-sm-4 control-label info-label" for="park">所在园区</label>
                                                    <div class="col-sm-5">
                                                        <input class="form-control blue-border" type="text" id="park" placeholder="">
                                                    </div>
                                                    <div class="parkstatus"></div>
                                                </div>
                                                <div class="form-group form-group-sm mb30">
                                                    <label class="col-sm-4 control-label info-label" for="department">所在部门</label>
                                                    <div class="col-sm-5">
                                                        <input class="form-control blue-border" type="text" id="department" placeholder=" ">
                                                    </div>
                                                    <div class="departmentstatus"></div>
                                                </div>
                                                <div class="form-group form-group-sm mb30">
                                                    <label class="col-sm-4 control-label info-label" for="position">职位</label>
                                                    <div class="col-sm-5">
                                                        <input class="form-control blue-border" type="text" id="position" placeholder=" ">
                                                    </div>
                                                    <div class="positionstatus"></div>
                                                </div>
                                                <div class="form-group form-group-sm mb30">
                                                    <label class="col-sm-4 control-label info-label" for="phone">联系电话</label>
                                                    <div class="col-sm-5">
                                                        <input class="form-control blue-border" type="text" id="phone" placeholder=" ">
                                                    </div>
                                                    <div class="phonestatus"></div>
                                                </div>
                                                <div class="form-group form-group-sm mb30">
                                                    <label class="col-sm-4 control-label info-label" for="email">邮箱</label>
                                                    <div class="col-sm-5">
                                                        <input class="form-control blue-border" type="text" id="email" placeholder=" ">
                                                    </div>
                                                    <div class="emailstatus"></div>
                                                </div>
                                                <div class="form-group form-group-sm mb30">
                                                    <label class="col-sm-4 control-label info-label" for="email"></label>
                                                    <div class="col-sm-5 text-align-left">
                                                        <button class="btn btn-fill btn-blue" type="button" onclick="changeInformation()">保存</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="info-item">
                                    <div class="info-title">
                                        <span class="icon-block"></span>
                                        <span>修改密码</span>
                                    </div>
                                    <div class="info-content">
                                        <div class="info-form">
                                            <form class="form-horizontal" action="javascript:void(0)">
                                                <div class="form-group form-group-sm mb30">
                                                    <label class="col-sm-4 control-label info-label" for="beforPassword">原始密码</label>
                                                    <div class="col-sm-5">
                                                        <input class="form-control blue-border" type="text" id="beforPassword">
                                                    </div>
                                                    <div class="beforstatus"></div>
                                                </div>
                                                <div class="form-group form-group-sm mb30">
                                                    <label class="col-sm-4 control-label info-label" for="newPassword">新密码</label>
                                                    <div class="col-sm-5">
                                                        <input class="form-control blue-border" type="text" id="newPassword">
                                                    </div>
                                                    <div class="newstatus"></div>
                                                </div>
                                                <div class="form-group form-group-sm mb30">
                                                    <label class="col-sm-4 control-label info-label" for="rePassword">在次输入新密码</label>
                                                    <div class="col-sm-5">
                                                        <input class="form-control blue-border" type="text" id="rePassword">
                                                    </div>
                                                    <div class="restatus"></div>
                                                </div>
                                                <div class="form-group form-group-sm mb30">
                                                    <label class="col-sm-4 control-label info-label" for="code">验证码</label>
                                                    <div class="col-sm-3">
                                                        <input class="form-control blue-border" type="text" id="code" >
                                                    </div>
                                                    <a href="#" id="changeImg">
													    <canvas class="show-captcha" id="canvas" width="140" height="35"></canvas>
													</a>
													<div class="codestatus"></div>
                                                </div>
                                                <div class="form-group form-group-sm mb30">
                                                    <label class="col-sm-4 control-label info-label" for="email"></label>
                                                    <div class="col-sm-5 text-align-left">
                                                        <button class="btn btn-fill btn-blue" type="button" onclick="changePassword()">确定</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include  "/common/footer.ftl"/>
<!-- js 共用部分 start -->
<#include  "/common/script.ftl"/>
<!-- js 共用部分 end -->
<script src="/js/image.js"></script>
<script src="/js/basicInfo/basicInfo.js"></script>
</body>
</html>