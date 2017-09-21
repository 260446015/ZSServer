<!-- Start: Header -->
<div class="navbar" role="navigation">
	<div class="container-fluid container-nav">
		<!-- Navbar Action -->
		<ul class="nav navbar-nav navbar-actions navbar-left">
			<li class="visible-md visible-lg"><a href="#"
				id="main-menu-toggle"><i class="fa fa-th-large"></i></a></li>
			<li class="visible-xs visible-sm"><a href="#" id="sidebar-menu"><i
					class="fa fa-navicon"></i></a></li>
		</ul>
		<!-- Navbar Right -->
		<div class="navbar-right">
			<!-- Userbox -->
			<div class="userbox">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					<figure class="profile-picture hidden-xs">
						<img src="/assets/img/avatar.jpg" class="img-circle" alt="" />
					</figure>
					<div class="profile-info">
						<span class="name"><@shiro.principal/></span> 
					</div> <i class="fa custom-caret"></i>
				</a>
				<div class="dropdown-menu">
					<ul class="list-unstyled">
						<li class="dropdown-menu-header bk-bg-white bk-margin-top-15">
							<div class="progress progress-xs  progress-striped active">
								<div class="progress-bar progress-bar-primary"
									role="progressbar" aria-valuenow="60" aria-valuemin="0"
									aria-valuemax="100" style="width: 60%;">60%</div>
							</div>
						</li>
						
						<li><a href="/role"><i class="fa fa-trophy"></i><@shiro.principal property="roleName"/></a></li>
						<li><a href="/updatePasswordList"><i class="fa fa-wrench"></i>修改密码</a></li>
						<li><a href="/logout"><i class="fa fa-power-off"></i>退出</a>
						</li>
					</ul>
				</div>
			</div>
			<!-- End Userbox -->
		</div>
		<!-- End Navbar Right -->
	</div>
</div>
<!-- End: Header -->

