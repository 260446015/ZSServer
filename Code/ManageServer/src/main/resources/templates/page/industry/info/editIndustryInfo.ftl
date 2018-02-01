<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Basic -->
    <meta charset="UTF-8" />
    <title>慧数招商—产业信息</title>
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <!-- Favicon and touch icons -->
    <#include "/common/link.ftl">
    <!-- Head Libs -->
    <script src="/assets/plugins/modernizr/js/modernizr.js"></script>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- Start: Header -->
<#include "/common/header.ftl">
<!-- End: Header -->
<!-- Start: Content -->
<div class="container-fluid content">
    <div class="row">
        <!-- Sidebar -->
        <#include "/common/sidebar.ftl">
        <!-- End Sidebar -->
        <!-- Main Page -->
            <div class="main">
                <!-- Page Header -->
                <div class="page-header">
                    <div class="pull-left">
                        <ol class="breadcrumb visible-sm visible-md visible-lg">
                            <li><a href="javascript:void(0)"><i class="icon fa fa-home"></i>Home</a></li>
                            <li class="active"><i class="fa fa-pencil-square-o"></i>编辑产业资讯列表</li>
                        </ol>
                    </div>
                    <div class="pull-right">
                    <#if info??>
                        <h2>编辑产业资讯信息</h2>
                    <#else>
                        <h2>添加产业资讯信息</h2>
                    </#if>
                    </div>
                </div>
            <!-- End Page Header -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-default bk-bg-white">
                        <div class="panel-body">
                            <form action="" method="post" enctype="multipart/form-data" class="form-horizontal ">
                                 <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">文章id</label>
                                    <div class="col-md-9">
                                        <input type="text" name="id" class="form-control" placeholder="请输入文章id">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">文章标题</label>
                                    <div class="col-md-9">
                                        <input type="text" name="title" class="form-control" placeholder="请输入文章标题">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">摘要</label>
                                    <div class="col-md-9">
                                        <input type="text" name="summary" class="form-control" placeholder="请输入文章摘要">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">内容</label>
                                    <div class="col-md-9">
                                        <input type="text" name="content" class="form-control" placeholder="请输入文章内容">
                                    </div>
                                </div>
                                 <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">原文地址</label>
                                    <div class="col-md-9">
                                        <input type="text" name="articleLink" class="form-control" placeholder="请输入原文地址">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">原文链接</label>
                                    <div class="col-md-9">
                                        <input type="text" name="sourceLink" class="form-control" placeholder="请输入原文链接">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">logo</label>
                                    <div class="col-md-9">
                                        <input type="text" name="logo" class="form-control" placeholder="请输入logo">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">所属园区</label>
                                    <div class="col-md-9">
                                        <input type="text" name="park" class="form-control" placeholder="请输入所属园区">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">地域</label>
                                    <div class="col-md-9">
                                        <input type="text" name="area" class="form-control" placeholder="请输入地域">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">发布时间</label>
                                    <div class="col-md-9">
                                        <input type="text" name="publishTime" class="form-control" placeholder="请输入发布时间">
                                    </div>
                                </div>
                                <div class="form-group">
                                   <label class="col-md-3 control-label" for="text-input">展会时间</label>
                                    <div class="col-md-9">
                                        <input type="text" name="exhibitiontime" class="form-control" placeholder="请输入展会时间">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">载体</label>
                                    <div class="col-md-9">
                                        <input type="text" name="vector" class="form-control" placeholder="请输入文章载体">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">来源</label>
                                    <div class="col-md-9">
                                        <input type="text" name="source" class="form-control" placeholder="请输入文章来源">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">作者</label>
                                    <div class="col-md-9">
                                        <input type="text" name="author" class="form-control" placeholder="请输入文章作者">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">情感</label>
                                    <div class="col-md-9">
                                        <input type="text" name="emotion" class="form-control" placeholder="请输入文章情感（正面，负面，中性）">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">产业类型</label>
                                    <div class="col-md-9">
                                        <input type="text" name="industryType" class="form-control" placeholder="请输入产业类型">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">产业标签</label>
                                    <div class="col-md-9">
                                        <input type="text" name="industryLabel" class="form-control" placeholder="请输入产业标签">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">产业</label>
                                    <div class="col-md-9">
                                        <input type="text" name="industry" class="form-control" placeholder="请输入产业">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">涉及行业</label>
                                    <div class="col-md-9">
                                        <input type="text" name="bus" class="form-control" placeholder="涉及行业">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">公司名录</label>
                                    <div class="col-md-9">
                                        <input type="text" name="bus" class="form-control" placeholder="请输入公司名录">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">发布时间</label>
                                    <div class="col-md-9">
                                        <input type="text" name="publishDate" class="form-control" placeholder="请输入发布时间 （yyyy-MM-dd HH:mm:ss）">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">年份</label>
                                    <div class="col-md-9">
                                        <input type="text" name="publishYear" class="form-control" placeholder="请输入发布年份">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">点击数</label>
                                    <div class="col-md-9">
                                        <input type="text" name="hitCount" class="form-control" placeholder="请输入点击数">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">支持数</label>
                                    <div class="col-md-9">
                                        <input type="text" name="supportCount" class="form-control" placeholder="请输入支持数">
                                    </div>
                                </div>
                                 <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">回复数</label>
                                    <div class="col-md-9">
                                        <input type="text" name="replyCount" class="form-control" placeholder="请输入回复数">
                                    </div>
                                </div>
                                 <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">是否预警</label>
                                    <div class="col-md-9">
                                        <input type="text" name="hasWarn" class="form-control" placeholder="请输入是否预警">
                                    </div>
                                </div>
                                 <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">维度</label>
                                    <div class="col-md-9">
                                        <input type="text" name="dimension" class="form-control" placeholder="请输入维度">
                                    </div>
                                </div>
                                 <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">是否置顶</label>
                                    <div class="col-md-9">
                                        <input type="text" name="istop" class="form-control" placeholder="请输入是否置顶">
                                    </div>
                                </div>
                               <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">存续（在营、开业、在册）</label>
                                    <div class="col-md-9">
                                        <input type="text" name="engageState" class="form-control" placeholder="请输入状态：存续（在营、开业、在册）">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">身份</label>
                                    <div class="col-md-9">
                                        <input type="text" name="identity" class="form-control" placeholder="请输入身份">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">注册资金</label>
                                    <div class="col-md-9">
                                        <input type="text" name="registerCapital" class="form-control" placeholder="请输入注册资金">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">注册时间 </label>
                                    <div class="col-md-9">
                                        <input type="text" name="registerData" class="form-control" placeholder="请输入注册时间 ">
                                    </div>
                                </div> 
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">成立时间 </label>
                                    <div class="col-md-9">
                                        <input type="text" name="establishTime" class="form-control" placeholder="请输入成立时间(公司) ">
                                    </div>
                                </div> 
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">园区面积 </label>
                                    <div class="col-md-9">
                                        <input type="text" name="acreage" class="form-control" placeholder="请输入园区面积">
                                    </div>
                                </div> 
                                 <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">企业名称</label>
                                    <div class="col-md-9">
                                        <input type="text" name="business" class="form-control" placeholder="请输入企业名称 ">
                                    </div>
                                </div> 
                                 <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">企业变更的属性</label>
                                    <div class="col-md-9">
                                        <input type="text" name="updateAttribute" class="form-control" placeholder="请输入企业变更的属性">
                                    </div>
                                </div> 
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">企业法人</label>
                                    <div class="col-md-9">
                                        <input type="text" name="boss" class="form-control" placeholder="请输入企业法人">
                                    </div>
                                </div> 
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">企业类型</label>
                                    <div class="col-md-9">
                                        <input type="text" name="businessType" class="form-control" placeholder="请输入企业类型">
                                    </div>
                                </div> 
                                <p>
                                    <button class="bk-margin-5 btn btn-labeled btn-success" type="button">
                                        <span class="btn-label"><i class="fa fa-check"></i></span>保存</button>
                                    <button class="bk-margin-5 btn btn-labeled btn-danger" type="button">
                                        <span class="btn-label"><i class="fa fa-times"></i></span>取消</button>
                                </p>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Main Page -->
        <#include "/common/footer.ftl">
    </div>
</div><!--/container-->
<div class="clearfix"></div>
<!-- start: JavaScript-->
<!-- Vendor JS-->
<#include "/common/script.ftl">
<script src="/assets/js/industry/editIndustryinfo.js"></script>
<script>
    <#if info??>
        addData(${info});
    </#if>
</script>
<!-- end: JavaScript-->
</body>
</html>