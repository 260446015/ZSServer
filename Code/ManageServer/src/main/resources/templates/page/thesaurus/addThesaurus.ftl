<!DOCTYPE html>
<html lang="en">

	<head>
	
		<!-- Basic -->
    	<meta charset="UTF-8" />

		<title>词库管理-关键词</title>
	 
		<!-- Mobile Metas -->
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		
	
		<!-- Favicon and touch icons -->
		<#include "/common/link.ftl">
        <link href="/assets/vendor/bootstrap/css/bootstrap-select.css" rel="stylesheet" />
		<!-- end: CSS file-->	

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
							<h2>添加关键词</h2>
						</div>					
					</div>
					<!-- End Page Header -->
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="panel panel-default bk-bg-white">
								<div class="panel-heading bk-bg-white">
									<h6><i class="fa fa-table red"></i><span class="break"></span>添加关键词</h6>
								</div>
								<div class="panel-body">
                                    <div class="row">
									</div>
                                    <!-- 展示内容部分 -->
                                    <form id="form-info" class="form-horizontal" action="javascript:void(0)" >
                                        <div class="form-group">
                                            <label class="col-md-3 control-label" for="text-input">关键词</label>
                                            <div class="col-md-9">
                                                <input type="text" name="name" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label" for="text-input">关键词词性</label>
                                            <div class="col-md-9">
                                                <select id="select" name="select" class="form-control input-lg" size="1">'
                                                    <option>产业解释性关键词</option>
                                                    <option>产业业务性关键词</option>
                                                    <option>产业关键词</option>
                                                    <option>企业</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label" for="text-input">关键词描述</label>
                                            <div class="col-md-9">
                                                <input type="text" name="descrp" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                    </form>
                                    <div class="form-group" style="padding-left: 30%">
                                        <button class="btn btn-info btn-xs my_add">添加关系 <i class="fa fa-plus"></i></button>
                                    </div>
                                    <p>
                                        <button class="bk-margin-5 btn btn-labeled btn-success" type="button">
                                            <span class="btn-label"><i class="fa fa-check"></i></span>确定</button>
                                        <button class="bk-margin-5 btn btn-labeled btn-danger" type="button">
                                            <span class="btn-label"><i class="fa fa-times"></i></span>取消</button>
                                    </p>
                                    <!-- 展示结束 -->
								</div>
							</div>
						</div>
					</div>					   
				</div>
				<!-- End Main Page -->
				<!-- Footer -->
				<#include "/common/footer.ftl">
				<!-- End Footer -->
			</div>
		</div><!--/container-->

		<div class="clearfix"></div>
		<#include "/common/script.ftl">
        <script src="/assets/vendor/bootstrap/js/bootstrap-select.js"></script>
		<script src="/js/thesaurus/addThesaurus.js"></script>
		<!-- end: JavaScript-->
	</body>
	
</html>