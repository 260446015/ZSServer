 <!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-债券信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="慧数招商平台，是一个关于园区产业招商的大数据管理平台">
    <meta name="keywords" content="慧数，招商，慧数招商，招商平台，园区，园区招商，园区招商平台，科技园，产业园，大数据，产业">
    <meta name="author" content="张鑫，慧数科技，中科点击">
    <meta name="application-name" content="慧数招商">
    <!-- css共用部分 start -->
    

	<#include "/common/link.ftl"/>
    <!-- css 共用部分 end -->
    <!-- js 兼容低版本IE start -->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- js 兼容低版本IE end -->
</head>
<body class="bg2">
<#include "/common/header.ftl"/>
<div class="wrapper">
    <div class="page-content">
      <#include "/common/sidebar2.ftl"/>
      	<div class="posa-right-container">
      		<#include "/common/companyDetail.ftl"/>
      		<div class="model-box">
      			<div class="model-header">
      				<h3 class="model-title">经营状况</h3>
                    <div class="modal-sub-title">债券信息</div>
                </div>
                <div class="model-body border-shadow-box pd0">
                    <table class="table table-striped">
                        <thead>
                            
                        </thead>
                        <tbody id="bond">
                        </tbody>
                    </table>
                </div>
            </div>
      	</div>
<div class="modal fade in customed-modal small-modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">债券信息</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">债券名称</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="bondName">16广州汽车CP001</p>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">债券代码</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="bondNum">16广州汽车CP001</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">发行人</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="publisherName">16广州汽车CP001</p>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">债券类型</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="bondType">16广州汽车CP001</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">债券发行日</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="publishTime">16广州汽车CP001</p>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">债劵到期日</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="">16广州汽车CP001</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">计息方式</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="calInterestType">16广州汽车CP001</p>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">上市交易日</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="bondTradeTime">16广州汽车CP001</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">信用评级机构</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="creditRatingGov">16广州汽车CP001</p>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">债劵摘牌日</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="bondStopTime">16广州汽车CP001</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">面值（元）</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="faceValue">16广州汽车CP001</p>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">参考利率（％）</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="refInterestRate">16广州汽车CP001</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">票面利率（％）</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="faceInterestRate">16广州汽车CP001</p>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">实际发行量（亿）</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="realIssuedQuantity">16广州汽车CP001</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">计划发行量（亿）</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="planIssuedQuantity">16广州汽车CP001</p>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">发行价格（元） </label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="issuedPrice">16广州汽车CP001</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">利差（BP）</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="interestDiff">16广州汽车CP001</p>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">付息频率</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="payInterestHZ">16广州汽车CP001</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">债劵起息日</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="startCalInterestTime">16广州汽车CP001</p>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">行权类型</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="exeRightType">16广州汽车CP001</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">行权日期</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="exeRightTime">16广州汽车CP001</p>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">托管机构</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="escrowAgent">16广州汽车CP001</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label text-blue">流通范围</label>
                            <div class="col-md-8">
                                <p class="form-control-static" id="flowRange">16广州汽车CP001</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <p class="text-center">Copyright©2008-2016 中科点击（北京）科技有限公司-版权所有  京ICP备11012241-3号</p>
</div>
<!-- js 共用部分 start -->
<#include  "/common/script.ftl"/>
<script src="/js/companyDetails/common.js"></script>
<!-- js 共用部分 end -->
<script src="/js/companyDetails/bond.js"></script>

</body>
</html>