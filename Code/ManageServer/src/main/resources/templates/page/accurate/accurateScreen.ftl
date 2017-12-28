<!DOCTYPE html>
<html lang="en">

	<head>
	
		<!-- Basic -->
    	<meta charset="UTF-8" />

		<title>Editable Table | Fire - Admin Template</title>
	 
		<!-- Mobile Metas -->
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		
	
		<#include "/common/link.ftl">
	    
		
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
								<li><a href="index.html"><i class="icon fa fa-home"></i>Home</a></li>
								<li><a href="#"><i class="fa fa-table"></i>Tables</a></li>
								<li class="active"><i class="fa fa-pencil-square-o"></i>Editable</li>
							</ol>						
						</div>
						<div class="pull-right">
							<h2>Editable Table</h2>
						</div>					
					</div>
					<!-- End Page Header -->
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="panel panel-default bk-bg-white">
								<div class="panel-heading bk-bg-white">
									<h6><i class="fa fa-table red"></i><span class="break"></span>Default</h6>							
									<div class="panel-actions">
										<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
										<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-sm-6">
											<div class="bk-margin-bottom-10">
												<button id="addToTable" class="btn btn-info">Add <i class="fa fa-plus"></i></button>
											</div>
										</div>
									</div>
									<table class="table table-bordered table-striped mb-none" id="datatable-editable">
										<thead>
											<tr>
												<th>Template</th>
												<th>Browser</th>
												<th>Platform(s)</th>
												<th>Actions</th>
											</tr>
										</thead>
										<tbody>
											<tr class="gradeX">
												<td>Magento</td>
												<td>Internet
													Explorer 4.0
												</td>
												<td>Win 95+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeC">
												<td>Magento</td>
												<td>Internet
													Explorer 5.0
												</td>
												<td>Win 95+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Magento</td>
												<td>Internet
													Explorer 5.5
												</td>
												<td>Win 95+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Magento</td>
												<td>Internet
													Explorer 6
												</td>
												<td>Win 98+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Magento</td>
												<td>Internet Explorer 7</td>
												<td>Win XP SP2+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Magento</td>
												<td>Safari</td>
												<td>Win XP</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Firefox 1.0</td>
												<td>Win 98+ / OSX.2+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Firefox 1.5</td>
												<td>Win 98+ / OSX.2+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Firefox 2.0</td>
												<td>Win 98+ / OSX.2+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Firefox 3.0</td>
												<td>Win 2k+ / OSX.3+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Safari</td>
												<td>OSX.2+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Safari 2.0</td>
												<td>OSX.3+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Safari 2.0</td>
												<td>Win 95+ / Mac OS 8.6-9.2</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Safari 2.0</td>
												<td>Win 98SE+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Opera</td>
												<td>Win 98+ / OSX.2+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Mozilla 1.0</td>
												<td>Win 95+ / OSX.1+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Mozilla 1.1</td>
												<td>Win 95+ / OSX.1+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Mozilla 1.2</td>
												<td>Win 95+ / OSX.1+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Mozilla 1.3</td>
												<td>Win 95+ / OSX.1+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Mozilla 1.4</td>
												<td>Win 95+ / OSX.1+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Mozilla 1.5</td>
												<td>Win 95+ / OSX.1+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Mozilla 1.6</td>
												<td>Win 95+ / OSX.1+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Mozilla 1.7</td>
												<td>Win 98+ / OSX.1+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Mozilla 1.8</td>
												<td>Win 98+ / OSX.1+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Opera</td>
												<td>Win 98+ / OSX.2+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Wordpress</td>
												<td>Opera</td>
												<td>Gnome</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Jigoshop</td>
												<td>Safari 1.2</td>
												<td>OSX.3</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Jigoshop</td>
												<td>Safari 1.3</td>
												<td>OSX.3</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Jigoshop</td>
												<td>Safari 2.0</td>
												<td>OSX.4+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Jigoshop</td>
												<td>Safari 3.0</td>
												<td>OSX.4+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Jigoshop</td>
												<td>Safari 2.0</td>
												<td>OSX.4+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Jigoshop</td>
												<td>Safari 2.0</td>
												<td>iPod</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Jigoshop</td>
												<td>Safari 2.0</td>
												<td>S60</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Prestashop</td>
												<td>Opera 7.0</td>
												<td>Win 95+ / OSX.1+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Prestashop</td>
												<td>Opera 7.5</td>
												<td>Win 95+ / OSX.2+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Prestashop</td>
												<td>Opera 8.0</td>
												<td>Win 95+ / OSX.2+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Prestashop</td>
												<td>Opera 8.5</td>
												<td>Win 95+ / OSX.2+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Prestashop</td>
												<td>Opera 9.0</td>
												<td>Win 95+ / OSX.3+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Prestashop</td>
												<td>Opera 9.2</td>
												<td>Win 88+ / OSX.3+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Prestashop</td>
												<td>Opera 9.5</td>
												<td>Win 88+ / OSX.3+</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Prestashop</td>
												<td>Safari</td>
												<td>Wii</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Prestashop</td>
												<td>Safari</td>
												<td>N800</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Prestashop</td>
												<td>Safari</td>
												<td>Nintendo DS</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeC">
												<td>Jomla</td>
												<td>Safari 2.0</td>
												<td>KDE 3.1</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Jomla</td>
												<td>Safari 2.0</td>
												<td>KDE 3.3</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Jomla</td>
												<td>Safari 2.0</td>
												<td>KDE 3.5</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeX">
												<td>Open chart</td>
												<td>Internet Explorer 4.5</td>
												<td>Mac OS 8-9</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeC">
												<td>Open chart</td>
												<td>Internet Explorer 5.1</td>
												<td>Mac OS 7.6-9</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeC">
												<td>Open chart</td>
												<td>Internet Explorer 5.2</td>
												<td>Mac OS 8-X</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Drupal</td>
												<td>Safari 2.0</td>
												<td>Embedded devices</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeA">
												<td>Drupal</td>
												<td>Safari 2.0</td>
												<td>Embedded devices</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeX">
												<td>Drupal</td>
												<td>Safari 2.0</td>
												<td>Embedded devices</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeX">
												<td>Drupal</td>
												<td>Safari 2.0</td>
												<td>Text only</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeX">
												<td>Drupal</td>
												<td>Safari 2.0</td>
												<td>Text only</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeC">
												<td>Drupal</td>
												<td>Safari 2.0</td>
												<td>Windows Mobile 6</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeC">
												<td>Drupal</td>
												<td>Safari 2.0</td>
												<td>PSP</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
											<tr class="gradeU">
												<td>Other theme</td>
												<td>All others</td>
												<td>-</td>
												<td class="actions">
													<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
													<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>					   
				</div>
				<!-- End Main Page -->	
		
				<!-- Footer -->
				<div id="footer">
					<ul>
						<li>
							<div class="title">Memory</div>
							<div class="bar">
								<div class="progress light progress-sm  progress-striped active">
									<div class="progress-bar progress-squared progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
										60%
									</div>
								</div>
							</div>			
							<div class="desc">4GB of 8GB</div>
						</li>
						<li>
							<div class="title">HDD</div>
							<div class="bar">
								<div class="progress light progress-sm  progress-striped active">
									<div class="progress-bar progress-squared progress-bar-primary" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
										40%
									</div>
								</div>
							</div>			
							<div class="desc">250GB of 1TB</div>
						</li>
						<li>
							<div class="title">SSD</div>
							<div class="bar">
								<div class="progress light progress-sm  progress-striped active">
									<div class="progress-bar progress-squared progress-bar-warning" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%;">
										70%
									</div>
								</div>
							</div>			
							<div class="desc">700GB of 1TB</div>
						</li>
						<li>
							<div class="copyright">
								<p class="text-muted text-right">Fire <i class="fa fa-coffee"></i> Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a> - More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a></p>
							</div>
						</li>				
					</ul>	
				</div>
				<!-- End Footer -->
			
			</div>
		</div><!--/container-->
		
		
		<div class="clearfix"></div>		
		
		
		<!-- start: JavaScript-->
		
		<!-- Vendor JS-->				
		<#include "/common/script.ftl">
		
		<!-- end: JavaScript-->
		
	</body>
	
</html>