 <!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>慧数招商-企业基本信息</title>
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
      <#include "/common/sidebar2.ftl"/>
      	<div class="posa-right-container">
      		<#include "/common/companyDetail.ftl"/>
      		<div class="model-box">
      			<div class="model-header">
                    <div class="modal-sub-title">基本信息</div>
                </div>
                <div class="model-body border-shadow-box pd0">
                    <table class="table table-striped table-bordered border-table">
                        <tbody>
                            <tr>
                                <td>
                                    <span class="lyt-lt">法人信息</span>
                                    <span class="lyt-rt" id="legalPerson"></span>
                                </td>
                                <td>
                                    <span class="lyt-lt">注册资本</span>
                                    <span class="lyt-rt" id="regCapital"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="lyt-lt">注册时间</span>
                                    <span class="lyt-rt" id="estiblishTime"></span>
                                </td>
                                <td>
                                    <span class="lyt-lt">企业状态</span>
                                    <span class="lyt-rt" id="regStatus"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="lyt-lt">工商注册号</span>
                                    <span class="lyt-rt" id="regNumber"></span>
                                </td>
                                <td>
                                    <span class="lyt-lt">行业</span>
                                    <span class="lyt-rt" id="industry"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="lyt-lt">组织机构代码</span>
                                    <span class="lyt-rt" id="orgNumber"></span>
                                </td>
                                <td>
                                    <span class="lyt-lt">营业期限</span>
                                    <span class="lyt-rt" id="toTime"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="lyt-lt">统一信用代码</span>
                                    <span class="lyt-rt" id="creditCode"></span>
                                </td>
                                <td>
                                    <span class="lyt-lt">核准日期</span>
                                    <span class="lyt-rt" id="approvedTime"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="lyt-lt">企业类型</span>
                                    <span class="lyt-rt" id="companyOrgType"></span>
                                </td>
                                <td>
                                    <span class="lyt-lt">英文名称</span>
                                    <span class="lyt-rt" id=""></span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="lyt-lt">纳税人识别号</span>
                                    <span class="lyt-rt" id=""></span>
                                </td>
                                <td>
                                    <span class="lyt-lt">登记机关</span>
                                    <span class="lyt-rt" id="regInstitute"></span>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <span class="lyt-lt">注册地址</span>
                                    <span class="lyt-rt" id="regLocation"></span>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <span class="lyt-lt">经营范围</span>
                                    <span class="lyt-rt" id="businessScope"></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
      	</div>
      	
<div class="footer">
    <p class="text-center">Copyright©2008-2016 中科点击（北京）科技有限公司-版权所有  京ICP备11012241-3号</p>
</div>
<!-- js 共用部分 start -->
<#include  "/common/script.ftl"/>
<script src="/js/companyDetails/common.js"></script>
<!-- js 共用部分 end -->

</body>
</html>