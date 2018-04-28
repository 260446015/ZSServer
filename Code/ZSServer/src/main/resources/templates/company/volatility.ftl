 <!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-股票行情</title>
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
      <#include "/common/searchSidebar.ftl"/>
      	<div class="posa-right-container">
      		<#include "/common/companyDetail.ftl"/>
      		<div class="model-box">
      			 <div class="model-header">
      			 	<h3 class="model-title">上市信息</h3>
                    <div class="modal-sub-title">股票行情</div>
                </div>
                <div class="model-body border-shadow-box pd0">
                     <table class="table table-striped">
                        <thead>
                            
                        </thead>
                        <tbody id="volatility">
                        	<tr colspan="6"><td id="cname"></td></tr>
                        	<tr>
                        		<td>涨停：</td>
                        		<td id="tmaxprice"></td>
                        		<td>跌停：</td>
                        		<td id="tminprice"></td>
                        		<td>今开：</td>
                        		<td id="topenprice"></td>
                        	</tr>
                        	<tr>
                        		<td>昨收：</td>
                        		<td id="pprice"></td>
                        		<td>最高：</td>
                        		<td id="thighprice"></td>
                        		<td>最低：</td>
                        		<td id="tlowprice"></td>
                        	</tr>
                        	<tr>
                        		<td>总市值：</td>
                        		<td id="tvalue"></td>
                        		<td>流通市值：</td>
                        		<td id="flowvalue"></td>
                        		<td>成交量：</td>
                        		<td id="tamount"></td>
                        	</tr>
                        	<tr>
                        		<td>成交额：</td>
                        		<td id="tamounttotal"></td>
                        		<td>市净率：</td>
                        		<td id="tvaluep"></td>
                        		<td>市盈率（动）：</td>
                        		<td id="fvaluep"></td>
                        	</tr>
                        	<tr>
                        		<td>振幅：</td>
                        		<td id="trange"></td>
                        		<td>换手：</td>
                        		<td id="tchange"></td>
                        		<td></td>
                        		<td></td>
                        	</tr>
                        </tbody>
                    </table>
                </div>
            </div>
      		
      	</div>
<#include "/common/footer.ftl"/>
<!-- js 共用部分 start -->
<#include  "/common/script.ftl"/>
<script src="/js/companyDetails/common.js"></script>
<!-- js 共用部分 end -->
<script src="/js/companyDetails/volatility.js"></script>

</body>
</html>