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
		<link href="/css/thru.css" rel="stylesheet" />
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
                            <div class="search-item-content" id="labelInfo">
                               <!-- <a href="javascript:void(0);" id="全部" class="search-item active">全部</a>
                                <a href="javascript:void(0);" id="人名"  class="search-item">人名</a>
                                <a href="javascript:void(0);" id="产业" class="search-item">产业</a>
                                <a href="javascript:void(0);" id="企业" class="search-item">企业</a>
                                <a href="javascript:void(0);" id="地域" class="search-item">地域</a>
                                <a href="javascript:void(0);" id="描述性" class="search-item">产业解释性关键词</a>
                                <a href="javascript:void(0);" id="业务性" class="search-item">产业业务性关键词</a>
                            	-->
                            </div>
                             <div class="search-item-content" >
                             	<div>
                            	 <button class="btn btn-info btn-xs " id="new_add">新增分类<i class="fa fa-plus"></i></button>
                             	</div>
                             </div>
                        </div>   
                        <!-- 选择框  -->  
                         <div class="form-group">
                           <label class="col-sm-1 control-label sort">排序：</label>
							<div>
								<div class="col-sm-3">
						      	  <select name="dealer"  class="selectpicker show-tick form-control"  data-width="98%" data-first-option="false" required data-live-search="true">
						       		<option value="1">添加时间倒序</option> 
						       		<option value="2">添加时间顺序</option> 
						       		<option value="3">词性热度</option> 
						       		<option value="4">词性复杂度</option> 
						       		<option value="5">词性关系复杂度</option> 
						       		</select>
						        </div>
						    </div>
                        <!-- 搜索功能  -->    
                        	<div class="col-md-8">
                        		<div class='col-md-5'>
                        			<input type="text" name="keyword_name" class="form-control" placeholder="请填写产业关键词"/>
                        		</div>
                        		<div class='col-md-3'>
                        			<button class="btn btn-info btn-xs btns">搜索 <i class="fa fa-search"></i></button>
                        		</div>
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
												<button id="addKeyword" class="btn btn-info">批量添加 <i class="fa fa-plus"></i></button>
											</div>
										</div>
										
									</div>
								
									<table class="table table-bordered table-striped mb-none" id="">
										<thead>
											<tr>
												<th>编号</th>
												<th>词名</th>
												<th>词性</th>
												<th>词属性</th>
												<th>词关系</th>
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
    											 </div>
      												<div class="modal-body" >
    											   		<form id="demo-form" class="form-horizontal mb-lg" novalidate="novalidate" >
															<div class="form-group">
                                          					  <label class="col-md-3 control-label" for="text-input">选择添加类型:</label>
                                           						 <div class="col-md-9">
                                             			 		  <select id="select" name="select" class="form-control input-lg" size="1">'
                                                					</select>
                                          					  	</div>
                                          					  	<div class="col-md-9 list" >
                                          					  		<input type="text" name="TypeWord"  class="form-control" placeholder="类型名">
                                          					  	</div>
                                       						 </div>		
                                       						 <div class="form-group">
                                          					  <label class="col-md-3 control-label" for="text-input">词名称:</label>
                                          					  	<div class="col-md-9" >
                                          					  		<input type="text" name="keyword"  class="form-control" placeholder="词名称">
                                          					  	</div>
                                       						 </div>		
                                       						  <div class="form-group">
                                          					  <label class="col-md-3 control-label" for="text-input">词相关内容:</label>
                                          					  	<div class="col-md-9" >
                                          					  		<h4>词说明</h4>
                                          					  		<input type="text" name="describe"  class="form-control" placeholder="词说明">
                                          					  	</div>
                                          					  	<!--<div class="col-md-9" >
                                          					  		<h4>产业分类</h4>
                                          					  		<input type="text" name="industry"  class="form-control" placeholder="产业分类">
                                          					  	</div>-->
                                       						 </div>																
														</form>
														<div class="form-group" style="padding-left: 30%">
                                       						 <button class="btn btn-info btn-xs my_add">添加属性<i class="fa fa-plus"></i></button>
                                       						 <button class="btn btn-info btn-xs my_nextadd" style="margin-left: 30%">下一步</button>
                                    					</div>
   
    											  </div>
   											 </div><!-- /.modal-content -->
  												</div><!-- /.modal-dialog -->
									</div>
									<!-- /.modal -->
									
									<!-- Modal Form -->
									<div class="modal fade" id="secondModal">
  											<div class="modal-dialog">
    											<div class="modal-content">
      											<div class="modal-header">
      											  <button type="button" class="close" data-dismiss="modal">
      										     	<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
      											  	</button>
    											 </div>
      												<div class="modal-body">
      												
															<div class="form-group">
                                          					 	 <label class="col-md-3 control-label" for="text-input">选择词性:</label>
                                           						 <div class="col-md-9">
                                             			 		  <select id="select1" name="select" class="form-control input-lg" size="1">'
                                                					</select>
                                          					  	</div>
                                       						 </div>	
                                       						 <div class="form-group">
                                          					 	 <label class="col-md-3 control-label mt10" for="text-input">选择词:</label>
                                           						 <div class="col-md-9 mt10">
                                           						   <div style="color:#99BBE8;background:#fafafa;padding:5px;" >
                                           						    <input type="text" name="test"  placeholder="输入词名"/>
                                           						   </div>
                                             			 			 <div style="padding:5px;" id="word_info" class='texteara'>
                                             			 			 <input type="checkbox" name="lang" value="01"><span>天天挖坑</span><br />
            															<input type="checkbox" name="lang" value="02"><span>爱游戏</span><br />
           																<input type="checkbox" name="lang" value="03"><span>沃商店</span><br />
           																 <input type="checkbox" name="lang" value="04"><span>咪咕</span>	<br/>	
           																 <input type="checkbox" name="lang" value="02"><span>爱游戏</span><br />
           																<input type="checkbox" name="lang" value="03"><span>沃商店</span><br />
           																 <input type="checkbox" name="lang" value="04"><span>咪咕</span>	<br/>
           																 <input type="checkbox" name="lang" value="02"><span>爱游戏</span><br />
           																<input type="checkbox" name="lang" value="03"><span>沃商店</span><br />
           																 <input type="checkbox" name="lang" value="04"><span>咪咕</span>	<br/>
           																 <input type="checkbox" name="lang" value="02"><span>爱游戏</span><br />
           																<input type="checkbox" name="lang" value="03"><span>沃商店</span><br />
           																 <input type="checkbox" name="lang" value="04"><span>咪咕</span>	<br/>
           																 	 
      																</div>
                                          					  	</div>
                                       						 </div>	
                                       						 
                                       						 <div class="form-group">
                                          					 	 <label class="col-md-3 control-label mt10" for="text-input">选择关系项:</label>
                                           						 <div class="col-md-9 mt10">
                                             			 		 	<div style="padding:5px" id="related_info" class='texteara'>
            															<input type="checkbox" name="lang" value="01"><span>天天挖坑</span><br />
            															<input type="checkbox" name="lang" value="02"><span>爱游戏</span><br />
           																<input type="checkbox" name="lang" value="03"><span>沃商店</span><br />
           																 <input type="checkbox" name="lang" value="04"><span>咪咕</span>
      																</div>
                                          					  	</div>
                                          					  	<div class="col-md-9">
                                          					  	 	<button class="btn btn-info btn-xs my_relatedadd">添加关系项<i class="fa fa-plus"></i></button>
                                          					  	</div>
                                       						 </div>										
    											   		<form id="form2" class="form-horizontal mb-lg" novalidate="novalidate" >
														
														</form>
														
                                    					<div class="form-group" style="padding-left: 30%">
                                       						 <button class="btn btn-info btn-xs my_firstadd">上一步</button>
                                       						 <button class="btn btn-info btn-xs my_secondadd">下一步</button>
                                    					</div>
    											  </div>
   											 </div><!-- /.modal-content -->
  												</div><!-- /.modal-dialog -->
									</div>
									
									<!-- /.modal -->
									
										<!-- Modal Form -->
									<div class="modal fade" id="thirdModal">
  											<div class="modal-dialog">
    											<div class="modal-content">
      											<div class="modal-header">
      											  <button type="button" class="close" data-dismiss="modal">
      										     	<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
      											  	</button>
    											 </div>
      												<div class="modal-body">
    											   		<form id="form3" class="form-horizontal mb-lg" novalidate="novalidate" >
																										
														</form>
														<div class="form-group" style="padding-left: 30%">
                                       						 <button class="btn btn-info btn-xs my_fireadd">确认添加</button>
                                    					</div>
    											  </div>
   											 </div><!-- /.modal-content -->
  												</div><!-- /.modal-dialog -->
									</div>
									
									<!-- /.modal -->
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
		<script src="/js/thesaurus/thesaurusManage2.js"></script>
		<script src="/assets/js/pages/ui-modals.js"></script>
		<!-- end: JavaScript-->
		
	</body>
	
</html>