<!DOCTYPE html>
           <html>
           	<head>
           		<meta charset="UTF-8">
           		<title>接口测试页面</title>
           		<style type="text/css">
           			textarea {
           				vertical-align:top;

           			}
           		</style>
           		<link rel="shortcut icon" href="/img/favicon.ico">
           		<script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
           	</head>
	<body>
		<div>
			<div class="title">参数:</div> 
			<div class="content">
				<textarea id="param" rows="10" cols="60"></textarea>
			</div>
			<font id="parammsg" style="color:red;font-size: 12px"></font>
			<br/>
			<div class="title">路径:</div> 
			<div class="content">
				<input style="width:385px;" id="url"/> <input id="submit" type="button" value="提交">
			</div>
			<font id="urlmsg" style="color:red;font-size: 12px"></font>
			<br/>
			<div class="title">结果:</div>
			<div class="content">
				<textarea id="result" rows="10" cols="60"></textarea>
			</div>
			<br/>
			<div class="title">耗时:</div>
			<div  class="content">
				<div id="spid" style="border:1px solid #a9a9a9;display: block; width:443px;height: 20px; text-align: center;color:white;"></div>
			</div>
	</div> 
<script type="text/javascript">
	$(function(){
		$("#submit").click(function(){
			var path = $("#url").val();
			if("" == path){
				$('#urlmsg').text("*路径不能为空");
				return;
			}else{
				$('#urlmsg').text("");
			}
			var param = $("#param").val();
			var oldDate = new Date().getTime();
			var data = $("#param").val();
			if ($.trim(data) == "") {
				data = "{}";
			} 
			$.ajax({
				url:path,
				data:  $.parseJSON(data),
				dataType:'json',
				type:'post',
				success:function(json){
					var nowDate = new Date().getTime();
					var d = nowDate - oldDate;
					$("#result").val(FormatJSON(json));
					if(d <= 300){
						$("#spid").css({'background-color':'#00CD00'});
					}else if (d > 300 && d < 800){
						$("#spid").css({'background-color':'#FF8247'});
					}else{
						$("#spid").css({'background-color':'#EE0000'});
					}
					$("#spid").text(d+"毫秒");
				},
				error:function(r, t, e){
					$('#urlmsg').text("*请求路径报"+r.status);
				}
			});
		});
		$('#param').blur(function(){
			$(this).val(FormatJSON(JSON.parse($(this).val())))
		});
	});
	
	function RealTypeOf(v) {
		if (typeof(v) == "object") {
		  if (v === null) return "null";
		  if (v.constructor == (new Array).constructor) return "array";
		  if (v.constructor == (new Date).constructor) return "date";
		  if (v.constructor == (new RegExp).constructor) return "regex";
		  return "object";
		}
		return typeof(v);
	}
		
	function FormatJSON(oData, sIndent) {
		if (arguments.length < 2) {
		  var sIndent = "";
		}
		var sIndentStyle = "    ";
		var sDataType = RealTypeOf(oData);
		
		if (sDataType == "array") {
		  if (oData.length == 0) {
		    return "[]";
		  }
		  var sHTML = "[";
		} else {
		  var iCount = 0;
		  $.each(oData, function() {
		    iCount++;
		    return;
		  });
		  if (iCount == 0) {
		    return "{}";
		  }
		  var sHTML = "{";
		}
		
		var iCount = 0;
		$.each(oData, function(sKey, vValue) {
		  if (iCount > 0) {
		    sHTML += ",";
		  }
		  if (sDataType == "array") {
		    sHTML += ("\n" + sIndent + sIndentStyle);
		  } else {
		    sHTML += ("\n" + sIndent + sIndentStyle + "\"" + sKey + "\"" + ": ");
		  }
		
		  switch (RealTypeOf(vValue)) {
		    case "array":
		    case "object":
		      sHTML += FormatJSON(vValue, (sIndent + sIndentStyle));
		      break;
		    case "boolean":
		    case "number":
		      sHTML += vValue.toString();
		      break;
		    case "null":
		      sHTML += "null";
		      break;
		    case "string":
		      sHTML += ("\"" + vValue + "\"");
		      break;
		    default:
		      sHTML += ("TYPEOF: " + typeof(vValue));                                                                                              }
		      iCount++;
		  }
		)
		
		if (sDataType == "array") {
		  sHTML += ("\n" + sIndent + "]");
		} else {
		  sHTML += ("\n" + sIndent + "}");
		}
		
		return sHTML;
	}
</script>
</body>
</html>


