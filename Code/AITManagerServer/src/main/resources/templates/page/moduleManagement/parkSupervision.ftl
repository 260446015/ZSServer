<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>园区监管管理 </title>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="../layui/css/add.css">
    <script src="../layui/js/jquery-1.8.2.min.js"></script>
    <script src="../layui/layui.js"></script>
    <script src="../layui/echarts.min.js"></script>
</head>
<body class="">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header layui-bg-blue">
        <div class="layui-logo " style="color: #ffffff;">
            <img src="" alt="">慧数招商
        </div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item"><a href="">个人中心<span
                    class="layui-badge-dot"></span></a></li>
            <li class="layui-nav-item"><a href="">退出</a></li>
            <span class="layui-nav-bar"
                  style="left: 76px; top: 55px; width: 0px; opacity: 0;"></span>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree">
                <li class="layui-nav-item layui-nav-itemed"><a href="全局管理.html">全局管理</a></li>
                <li class="layui-nav-item"><a href="账号审核.html">账号审核</a></li>
                <li class="layui-nav-item"><a href="">产业管理</a></li>
                <li class="layui-nav-item"><a href="园区管理.html">园区管理</a></li>
                <li class="layui-nav-item"><a href="">模块管理</a></li>
                <li class="layui-nav-item"><a href="">订单管理</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-nav layui-bg-cyan">
            <ul class="" lay-filter="">
                <li class="layui-nav-item"><a href="">筛选设置:</a></li>
            </ul>
            <ul class="" lay-filter="">
                <li class="layui-nav-item"><a href="">产业类型：</a></li>
                <li class="layui-nav-item layui-this"><a href="">不限</a></li>
                <li class="layui-nav-item"><a href="">节能环保</a></li>
                <li class="layui-nav-item"><a href="">动漫产业</a></li>
                <li class="layui-nav-item"><a href="">新能源</a></li>
                <li class="layui-nav-item"><a href="">信息技术</a></li>
                <li class="layui-nav-item"><a href="">生态科技</a></li>
                <li class="layui-nav-item"><a href="">新一代信息技术</a></li>
                <li class="layui-nav-item"><a href="">高端设备制造</a></li>
                <li class="layui-nav-item"><a href="">影视产业</a></li>
            </ul>
            <ul class="" lay-filter="">
                <li class="layui-nav-item"><a href="">区域</a></li>
                <li class="layui-nav-item layui-this"><a href="">不限</a></li>
                <li class="layui-nav-item"><a href="">北京</a></li>
                <li class="layui-nav-item"><a href="">上海</a></li>
                <li class="layui-nav-item"><a href="">天津</a></li>
                <li class="layui-nav-item"><a href="">深圳</a></li>
                <li class="layui-nav-item"><a href="">广州</a></li>
                <li class="layui-nav-item"><a href="">贵阳</a></li>
                <li class="layui-nav-item"><a href="">浙江</a></li>
                <li class="layui-nav-item"><a href="">河北</a></li>
                <li class="layui-nav-item"><a href="">江苏</a></li>
            </ul>
        </div>
        <div class="layui-col-md12 paddingX20" style="margin-top:20px;">
            <form class="layui-form" action="">
                <div class="layui-form-item">
                    <input type="text" name="title" required  lay-verify="required" placeholder="请输入搜索内容" autocomplete="off" class="layui-input" style="width:200px;display:inline"><button class="layui-btn layui-btn-normal">搜索</button>
                </div>
            </form>
            <table class="layui-table" lay-size="lg">
                <colgroup>
                    <col width="70">
                    <col width="70">
                    <col width="70">
                    <col width="150">
                    <col width="150">
                    <col width="80">
                    <col width="100">
                </colgroup>
                <thead>
                <tr>
                    <th>园区名称</th>
                    <th>园区级别</th>
                    <th>地域(城市)</th>
                    <th>具体地址</th>
                    <th>园区产业产业</th>
                    <th>成立时间</th>
                    <th>园区面积</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><a href="园区监管.html">清华创业园</a></td>
                    <td>市级</td>
                    <td>北京</td>
                    <td>海淀区清华科技园学研大厦A308</td>
                    <td>互联网+光伏机电生物医药食品加工精英配套石油化工文化创意其他</td>
                    <td>1999年8月20日</td>
                    <td>0平方公里(已开发0平方公里)</td>
                </tr>
                <tr>
                    <td><a href="园区监管.html">清华创业园</a></td>
                    <td>市级</td>
                    <td>北京</td>
                    <td>海淀区清华科技园学研大厦A308</td>
                    <td>互联网+光伏机电生物医药食品加工精英配套石油化工文化创意其他</td>
                    <td>1999年8月20日</td>
                    <td>0平方公里(已开发0平方公里)</td>
                </tr>
                <tr>
                    <td><a href="园区监管.html">清华创业园</a></td>
                    <td>市级</td>
                    <td>北京</td>
                    <td>海淀区清华科技园学研大厦A308</td>
                    <td>互联网+光伏机电生物医药食品加工精英配套石油化工文化创意其他</td>
                    <td>1999年8月20日</td>
                    <td>0平方公里(已开发0平方公里)</td>
                </tr>
                <tr>
                    <td><a href="园区监管.html">清华创业园</a></td>
                    <td>市级</td>
                    <td>北京</td>
                    <td>海淀区清华科技园学研大厦A308</td>
                    <td>互联网+光伏机电生物医药食品加工精英配套石油化工文化创意其他</td>
                    <td>1999年8月20日</td>
                    <td>0平方公里(已开发0平方公里)</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="../layui/js/index.js"></script>
<script>
    function onDel(){
        layer.open({
            content: '确认删除该片数据'
            ,btn: ['确认', '取消']
            ,yes: function(index, layero){
                //按钮【按钮一】的回调
            }
            ,btn2: function(index, layero){
                //按钮【按钮二】的回调

                //return false 开启该代码可禁止点击该按钮关闭
            }
            ,cancel: function(){
                //右上角关闭回调

                //return false 开启该代码可禁止点击该按钮关闭
            }
        });
    }
    function toTop(){
        layer.open({
            content: '确认置顶该片数据'
            ,btn: ['确认', '取消']
            ,yes: function(index, layero){
                //按钮【按钮一】的回调
            }
            ,btn2: function(index, layero){
                //按钮【按钮二】的回调

                //return false 开启该代码可禁止点击该按钮关闭
            }
            ,cancel: function(){
                //右上角关闭回调

                //return false 开启该代码可禁止点击该按钮关闭
            }
        });
    }
</script>
</body>
</html>