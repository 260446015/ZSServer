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
		
		<!-- end: CSS file-->	
	    
		<!-- end: CSS file-->	
	    <link href="/assets/css/public.css" rel="stylesheet" /> 
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
								<li><a href="index.html"><i class="icon fa fa-home"></i>主页</a></li>
								<li><a href="#"><i class="fa fa-table"></i>表格</a></li>
								<li class="active"><i class="fa fa-pencil-square-o"></i>词库管理</li>
							</ol>						
						</div>
						<div class="pull-right">
							<h2>产业知识图谱关键词管理</h2>
						</div>					
					</div>
					<!-- End Page Header -->
					<div class="row">
					 <div class="search-box">
                        <div class="search-group">
                            <div class="search-item-title">
                            	词性
                            </div>
                            <div class="search-item-content">
                                <a href="javascript:void(0);" id="全部" class="search-item active">全部</a>
                                <a href="javascript:void(0);" id="人名"  class="search-item">人名</a>
                                <a href="javascript:void(0);" id="产业" class="search-item">产业</a>
                                <a href="javascript:void(0);" id="企业" class="search-item">企业</a>
                                <a href="javascript:void(0);" id="地域" class="search-item">地域</a>
                                <a href="javascript:void(0);" id="产业解释性关键词" class="search-item">产业解释性关键词</a>
                                <a href="javascript:void(0);" id="产业业务性关键词" class="search-item">产业业务性关键词</a>
                            </div>
                        </div>      
                    </div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="panel panel-default bk-bg-white">
								<div class="panel-heading bk-bg-white">
									<h6><i class="fa fa-table red"></i><span class="break"></span>知识图谱关键词</h6>							
									<div class="panel-actions">
										<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
										<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-sm-6">
											<div class="bk-margin-bottom-10">
												<button id="addKeywordToTable" class="btn btn-info">添加 <i class="fa fa-plus"></i></button>
											</div>
										</div>
									</div>
									<table class="table table-bordered table-striped mb-none" id="">
										<thead>
											<tr>
												<th>主键</th>
												<th>关键词</th>
												<th>词性</th>
												<th>描述</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="manage_keyword">
										</tbody>
									</table>
									<div class="page-box clearfix">
        								<ul class="page pull-right" id="page"></ul>
            						</div>
								</div>
							</div>
						</div>
					</div>	
				<!-- model -->
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="foothide">
					    <div class="panel panel-default bk-bg-white">
								<div class="panel-body">	
								<!-- Modal Form -->
									<div class="modal fade" id="myModal">
  											<div class="modal-dialog">
    											<div class="modal-content">
      											<div class="modal-header">
      											  <button type="button" class="close" data-dismiss="modal">
      										     <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
      											  </button>
    												    <h4 class="modal-title">关联关系</h4>
    													  </div>
      												<div class="modal-body">
    											   		<form id="demo-form" class="form-horizontal mb-lg" novalidate="novalidate" >
												</form>
    											  </div>
   											 </div><!-- /.modal-content -->
  												</div><!-- /.modal-dialog -->
									</div><!-- /.modal -->
							</div>
						</div>	
					</div>									   
				<!-- Modal Form -->
				<!-- model -->
				</div>
				<!-- End Main Page -->	
									
			
				<!-- Footer -->
				
				<!-- End Footer -->

			
			</div>
		</div><!--/container-->
		
		
		<div class="clearfix"></div>		
		
		
		<!-- start: JavaScript-->
		
		<!-- Vendor JS-->				
		<#include "/common/script.ftl">
		<script src="/js/thesaurus/thesaurusManage.js"></script>
		<script src="/assets/js/pages/ui-modals.js"></script>
		<!-- end: JavaScript-->
		
	</body>
	
</html>