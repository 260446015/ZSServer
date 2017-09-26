<!DOCTYPE html>
<html lang="en">
<head>

<!-- Basic -->
<meta charset="UTF-8" />

<title>慧数招商后台登录</title>

<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />


<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="/assets/ico/favicon.ico"
	type="image/x-icon" />
<link rel="apple-touch-icon" href="/assets/ico/apple-touch-icon.png" />
<link rel="apple-touch-icon" sizes="57x57"
	href="/assets/ico/apple-touch-icon-57x57.png" />
<link rel="apple-touch-icon" sizes="72x72"
	href="/assets/ico/apple-touch-icon-72x72.png" />
<link rel="apple-touch-icon" sizes="76x76"
	href="/assets/ico/apple-touch-icon-76x76.png" />
<link rel="apple-touch-icon" sizes="114x114"
	href="/assets/ico/apple-touch-icon-114x114.png" />
<link rel="apple-touch-icon" sizes="120x120"
	href="/assets/ico/apple-touch-icon-120x120.png" />
<link rel="apple-touch-icon" sizes="144x144"
	href="/assets/ico/apple-touch-icon-144x144.png" />
<link rel="apple-touch-icon" sizes="152x152"
	href="/assets/ico/apple-touch-icon-152x152.png" />

<!-- start: CSS file-->

<!-- Vendor CSS-->
<link href="/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="/assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
<link href="/assets/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />

<!-- Plugins CSS-->
<link href="/assets/plugins/bootkit/css/bootkit.css" rel="stylesheet" />

<!-- Theme CSS -->
<link href="/assets/css/jquery.mmenu.css" rel="stylesheet" />

<!-- Page CSS -->
<link href="/assets/css/style.css" rel="stylesheet" />
<link href="/assets/css/add-ons.min.css" rel="stylesheet" />

<style>
footer {
	display: none;
}
</style>

<!-- end: CSS file-->


<!-- Head Libs -->
<script src="/assets/plugins/modernizr/js/modernizr.js"></script>

</head>

<body>
	<!-- Start: Content -->
	<div class="container-fluid content">
		<div class="row">
			<!-- Main Page -->
			<div id="content" class="col-sm-12 full">
				<div class="row">
					<div class="login-box">
						<div class="panel">
							<div class="panel-body">
								<div class="header bk-margin-bottom-20 text-center" style="background: #3BBFB4;">
									<img src="assets/img/logo.png" class="img-responsive" style="margin: auto;padding: 20px;" alt="" />
								</div>
								<form id="signupLogin" class="form-horizontal login"
									method="post">
									<input type="hidden" id="password" name="password">
									<div class="bk-padding-left-20 bk-padding-right-20">
										<div class="form-group">
											<div class="input-group input-group-icon">
												<input type="text" class="form-control bk-radius"
													id="username" name="username" placeholder="输入用户名" required/> <span
													class="input-group-addon"> <span class="icon">
														<i class="fa fa-user"></i>
												</span>
												</span>
											</div>
										</div>
										<div class="form-group">
											<div class="input-group input-group-icon">
												<input type="password" class="form-control bk-radius"
													id="userPassword" name="userPassword" placeholder="输入密码" required/> <span
													class="input-group-addon"> <span class="icon">
														<i class="fa fa-key"></i>
												</span>
												</span>
											</div>
										</div>
										<#-- <div>
											<input type="text" name="captcha" id="captcha"
												class="form-control" style="width: 200px; display: inline;"
												placeholder="输入验证码" required/> <img id='captchaImg' src="/security/captcha"
												alt="图形验证码" />
										</div>
										<div class="row bk-margin-top-20 bk-margin-bottom-10">
											<div class="col-sm-8">
												<div class="checkbox-custom checkbox-default">
													<input id="rememberMe" name="rememberMe" type="checkbox"/>
													<label for="rememberMe">记住我</label>
												</div>
											</div> -->
											<div class="col-sm-8">
												<label for="errorMsg" class="errorMsg" style="font-weight: 800;color: red;font-size: 1.1em;">
													<#if errorMsg?exists>
														${errorMsg}
													</#if>
												</label>
											</div>
											<div class="col-sm-4 text-right">
												<button type="submit" class="btn btn-primary hidden-xs">登陆</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- End Main Page -->
		</div>
	</div>
	<!--/container-->


	<!-- start: JavaScript-->

	<!-- Vendor JS-->
	<script src="/assets/vendor/js/jquery.min.js"></script>
	<script src="/assets/vendor/js/jquery-2.1.1.min.js"></script>
	<script src="/assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="/assets/vendor/skycons/js/skycons.js"></script>

	<!-- Theme JS -->
	<script src="/assets/js/jquery.mmenu.min.js"></script>

	<!-- Pages JS -->
	<script src="/assets/js/pages/page-login.js"></script>
	<!-- end: JavaScript-->
	<script src="/assets/js/jquery.validate.min.js"></script>
	<script src="/assets/js/md5.js"></script>
</body>
</html>