 <script src="/assets/js/jquery-1.10.2.min.js"></script>
 <div class="layui-header layui-bg-blue">
        <div class="layui-logo " style="color: #ffffff;"><img src="" alt="">慧数招商</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:void(0)">个人中心<span class="layui-badge-dot"></span></a>
            </li>
            <li class="layui-nav-item"><a href="javascript:void(0)" onclick="logout()">退出</a></li>
            <span class="layui-nav-bar" style="left: 76px; top: 55px; width: 0px; opacity: 0;"></span>
        </ul>
   </div>
   <script type="text/javascript">
	   function logout(){
			$.ajax({
	            type: 'get',
	            url: "/apis/logOut.do",
	            async: false,
	            success: function (response) {
					window.location.href="/login";
	            }
	        });
		}
   </script>