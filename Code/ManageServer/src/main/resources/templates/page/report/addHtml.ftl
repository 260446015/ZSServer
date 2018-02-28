<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Basic -->
    <meta charset="UTF-8" />
    <title>慧数招商—报告管理</title>
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
        <div class="main sidebar-minified">
            <!-- Page Header -->
            <div class="page-header">
                <div class="pull-right">
                    <h2>h5月报</h2>
                </div>
            </div>
            <!-- End Page Header -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-default bk-bg-white">
                        <div class="panel-body">
                            <form action="javascript:void(0)" method="post" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">月报名字</label>
                                    <div class="col-md-9">
                                        <input type="text" name="name" class="form-control" placeholder="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">月报时间段</label>
                                    <div class="col-md-9">
                                        <input type="text" name="time" class="form-control" placeholder="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">月关键词</label>
                                    <div class="col-md-9">
                                        <input type="text" name="key1" class="form-control" placeholder="">
                                        关键词：<span id="key1"></span><button class="btn btn-info btn-xs add_word">添加关键词 <i class="fa fa-plus"></i></button>
                                        <input type="text" name="key2" class="form-control" placeholder="">
                                        关键词：<span id="key2"></span><button class="btn btn-info btn-xs add_word">添加关键词 <i class="fa fa-plus"></i></button>
                                        <input type="text" name="key3" class="form-control" placeholder="">
                                        关键词：<span id="key3"></span><button class="btn btn-info btn-xs add_word">添加关键词 <i class="fa fa-plus"></i></button>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">产业链高亮</label>
                                    <div class="col-md-9">
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox1" value="自动无人技术">
                                            <label>自动无人技术</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox1" value="语义识别与分析">
                                            <label>语义识别与分析</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox1" value="语音与声学识别">
                                            <label> 语音与声学识别</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox1" value="计算机视觉与图像">
                                            <label> 计算机视觉与图像</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox1" value="AI平台">
                                            <label>AI平台</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox1" value="其他">
                                            <label for="inline-checkbox6">其他</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox1" value="技术层">
                                            <label for="inline-checkbox7">技术层</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox1" value="人工智能产业">
                                            <label>人工智能产业</label>
                                        </div>
                                        <input type="text" name="chain1" class="form-control" placeholder="">
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox2" value="软件">
                                            <label> 软件</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox2" value="金融">
                                            <label> 金融</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox2" value="教育">
                                            <label> 教育</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox2" value="客服">
                                            <label> 客服</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox2" value="农业">
                                            <label> 农业</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox2" value="网络安全">
                                            <label> 网络安全</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox2" value="定制服务">
                                            <label> 定制服务</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox2" value="营销和销售">
                                            <label> 营销和销售</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox2" value="医疗健康">
                                            <label> 医疗健康</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox2" value="交通">
                                            <label> 交通</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox2" value="物流">
                                            <label> 物流</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox2" value="家用机器人">
                                            <label> 家用机器人</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox2" value="工业机器人">
                                            <label> 工业机器人</label>
                                        </div>
                                        <input type="text" name="chain2" class="form-control" placeholder="">
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox3" value="大数据">
                                            <label> 大数据</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox3" value="云计算">
                                            <label> 云计算</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox3" value="算法结构">
                                            <label> 算法结构</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox3" value="软件">
                                            <label> 软件</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox3" value="基础支撑层">
                                            <label> 基础支撑层</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox3" value="硬件">
                                            <label> 硬件</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox3" value="传感器 ">
                                            <label> 传感器</label>
                                        </div>
                                        <input type="text" name="chain3" class="form-control" placeholder="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">本月焦点</label>
                                    <div class="col-md-9">
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox4" value="1">
                                            <label>政策焦点</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox4" value="2">
                                            <label>资本焦点</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox4" value="3">
                                            <label>市场焦点</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox4" value="4">
                                            <label>舆论焦点</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox4" value="5">
                                            <label>技术焦点</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox4" value="6">
                                            <label>未来焦点</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">明星推荐</label>
                                    <div class="col-md-9">
                                        <label class="col-md-2 control-label" for="text-input">人物</label>
                                        <div class="col-md-9">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                                                    <thead>
                                                    <tr>
                                                        <th>名字</th>
                                                        <th>身份</th>
                                                        <th>logo</th>
                                                        <th>上榜理由</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td><input name="people_name"></td>
                                                        <td><input name="people_identity"></td>
                                                        <td><input name="people_logo"></td>
                                                        <td><input name="people_reason"></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <label class="col-md-2 control-label" for="text-input">企业</label>
                                        <div class="col-md-9">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                                                    <thead>
                                                    <tr>
                                                        <th>企业名称</th>
                                                        <th>logo</th>
                                                        <th>上榜理由</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td><input name="company_name1"></td>
                                                        <td><input name="company_logo1"></td>
                                                        <td><input name="company_reason1"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><input name="company_name2"></td>
                                                        <td><input name="company_logo2"></td>
                                                        <td><input name="company_reason2"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><input name="company_name3"></td>
                                                        <td><input name="company_logo3"></td>
                                                        <td><input name="company_reason3"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><input name="company_name4"></td>
                                                        <td><input name="company_logo4"></td>
                                                        <td><input name="company_reason4"></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">行业动态</label>
                                    <div class="col-md-9">
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox5" value="1">
                                            <label>各地新闻</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox5" value="2">
                                            <label>合作动向</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox5" value="3">
                                            <label>企业动向</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox5" value="4">
                                            <label>会议日程</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox5" value="5">
                                            <label>排行报告</label>
                                        </div>
                                        <div class="checkbox-custom checkbox-inline">
                                            <input type="checkbox" name="inline-checkbox5" value="6">
                                            <label>投融速递</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="text-input">优质企业</label>
                                    <div class="col-md-9">
                                        <label class="col-md-2 control-label" for="text-input">龙头企业</label>
                                        <div class="col-md-9">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                                                    <thead>
                                                    <tr>
                                                        <th>企业名称</th>
                                                        <th>产业</th>
                                                        <th>注册资金</th>
                                                        <th>成立时间</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td><input name="industry_name1"></td>
                                                        <td><input name="industry1"></td>
                                                        <td><input name="industry_money1"></td>
                                                        <td><input name="industry_time1"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><input name="industry_name2"></td>
                                                        <td><input name="industry2"></td>
                                                        <td><input name="industry_money2"></td>
                                                        <td><input name="industry_time2"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><input name="industry_name3"></td>
                                                        <td><input name="industry3"></td>
                                                        <td><input name="industry_money3"></td>
                                                        <td><input name="industry_time3"></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <label class="col-md-2 control-label" for="text-input">成长企业</label>
                                        <div class="col-md-9">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                                                    <thead>
                                                    <tr>
                                                        <th>企业名称</th>
                                                        <th>产业</th>
                                                        <th>注册资金</th>
                                                        <th>成立时间</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td><input name="industry_name4"></td>
                                                        <td><input name="industry4"></td>
                                                        <td><input name="industry_money4"></td>
                                                        <td><input name="industry_time4"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><input name="industry_name5"></td>
                                                        <td><input name="industry5"></td>
                                                        <td><input name="industry_money5"></td>
                                                        <td><input name="industry_time5"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><input name="industry_name6"></td>
                                                        <td><input name="industry6"></td>
                                                        <td><input name="industry_money6"></td>
                                                        <td><input name="industry_time6"></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <label class="col-md-2 control-label" for="text-input">潜力企业</label>
                                        <div class="col-md-9">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                                                    <thead>
                                                    <tr>
                                                        <th>企业名称</th>
                                                        <th>产业</th>
                                                        <th>注册资金</th>
                                                        <th>成立时间</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td><input name="industry_name7"></td>
                                                        <td><input name="industry7"></td>
                                                        <td><input name="industry_money7"></td>
                                                        <td><input name="industry_time7"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><input name="industry_name8"></td>
                                                        <td><input name="industry8"></td>
                                                        <td><input name="industry_money8"></td>
                                                        <td><input name="industry_time8"></td>
                                                    </tr>
                                                    <tr>
                                                        <td><input name="industry_name9"></td>
                                                        <td><input name="industry9"></td>
                                                        <td><input name="industry_money9"></td>
                                                        <td><input name="industry_time9"></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <p>
                                <button class="bk-margin-5 btn btn-labeled btn-success" type="button">
                                    <span class="btn-label"><i class="fa fa-check"></i></span>下一步</button>
                                <button class="bk-margin-5 btn btn-labeled btn-danger" type="button">
                                    <span class="btn-label"><i class="fa fa-times"></i></span>取消</button>
                            </p>
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
<script type="text/javascript" src="/assets/js/wangEditor.min.js"></script>
<script src="/js/report/addHtml.js"></script>
<!-- end: JavaScript-->
</body>
</html>