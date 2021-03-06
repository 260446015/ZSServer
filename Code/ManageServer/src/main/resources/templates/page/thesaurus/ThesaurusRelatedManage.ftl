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
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
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
						<div class="pull-left">
							<ol class="breadcrumb visible-sm visible-md visible-lg">								
								<li><a href="/apis/user/userPark.html"><i class="icon fa fa-home"></i>主页</a></li>
								<li><a href="#"><i class="fa fa-table"></i>表格</a></li>
								<li class="active"><i class="fa fa-pencil-square-o"></i>词库关联关系管理</li>
							</ol>						
						</div>
						<div class="pull-right">
							<h2>产业知识图谱关联关系管理</h2>
						</div>					
					</div>
					<!-- End Page Header -->
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="panel panel-default bk-bg-white">
								<div class="panel-heading bk-bg-white">
									<h6><i class="fa fa-table red"></i><span class="break"></span>知识图谱关联关系</h6>							
									<div class="panel-actions">
										<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
										<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<!-- 展示内容部分 -->
											<form  class="form-horizontal mb-lg" action="javascript:void(0)" novalidate="novalidate" >		
												<input type="hidden" name="id" value="${info}"/>
												<div class="form-group mt-lg">
												<label class="col-sm-3 control-label">关键词</label>
												<div class="col-md-3">
													<input type="text" name="keyword" class="form-control" placeholder="请输入关键词" value="请输入关键词" required/>
													</div></div>
													<div class="form-group">
													<label class="col-sm-3 control-label">词性</label>
														<div class="col-md-3">
														<input type="text" name="type" class="form-control" placeholder="请输入词性" value="请输入词性" required/>
													</div></div>
													<div class="form-group">
														<label class="col-sm-3 control-label">关键词描述</label>
															<div class="col-md-3">
														<input type="text" name="describe" class="form-control" placeholder="请填写关键词的描述" value="请填写关键词的描述" required/>
													</div>
													<div class="form-group" id="attributeInfo">
													</div>
												</form>
											<form id="fomr-info" class="form-horizontal mb-lg" action="javascript:void(0)" novalidate="novalidate" >		
											</form>
											<div class="form-group" style="padding-left: 30%">
											 <button class="btn btn-info btn-xs my_addinfo">添加关系 <i class="fa fa-plus"></i></button></div>
											<p>
                              				 <button class="bk-margin-5 btn btn-labeled btn-success" type="button">
                                    	<span class="btn-label"><i class="fa fa-check"></i></span>保存</button>
                                			<button class="bk-margin-5 btn btn-labeled btn-danger" type="button">
                                   	 	<span class="btn-label"><i class="fa fa-times"></i></span>取消</button>
                            		</p>				
										<!-- 展示结束 -->
									</div>
									
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
		
		
		<!-- start: JavaScript-->
		
		<!-- Vendor JS-->				
		<#include "/common/script.ftl">
	
        <script src="/assets/vendor/bootstrap/js/bootstrap-select.js"></script>
        
		<script src="/js/thesaurus/thesaurusRelatedManage.js"></script>
		<script >
				editData(${info});
		</script>
		<!-- end: JavaScript-->
		
	</body>
	
</html>