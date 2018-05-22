package com.huishu.ManageServer.controller.file;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.controller.BaseController;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * PDF文件上传
 *
 * @author yindq
 * @date 2018/3/16
 */
@Controller
@RequestMapping("/apis/file")
public class FileController extends BaseController {
	private static final Logger LOGGER = Logger.getLogger(FileController.class);
	/**
	 * 默认大小
	 */
	private static long FILE_SIZE = 1024 * 1024;
	/**
	 * 默认文件格式
	 */
	private static String FILE_TYPE = "pdf";
	/**
	 * 获取招商报告PDF列表
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pdfUpload.json", method = RequestMethod.POST)
	public AjaxResult pdfUpload(@RequestParam("file") MultipartFile file){
		if (file.isEmpty()) {
			return error("请选择上传文件");
		}
		if (file.getSize() > FILE_SIZE) {
			return error("文件超过上传大小");
		}
		String OriginalFilename = file.getOriginalFilename();
		String fileSuffix = OriginalFilename.substring(OriginalFilename.lastIndexOf(".") + 1).toLowerCase();
		if (!FILE_TYPE.equals(fileSuffix)) {
			return error("文件格式错误");
		}
		File f ;
		try {
			f=File.createTempFile("tmp", null);
			file.transferTo(f);
			f.deleteOnExit();
		} catch (Exception e) {
			LOGGER.error("MultipartFile转化成File失败",e);
			return error("文件转化失败");
		}
		PostMethod postMethod = new PostMethod("http://58.16.181.24:9322/fileserver/file/PDFUpload.do");
		//PostMethod postMethod = new PostMethod("http://localhost:8080/file/PDFUpload.do");
		try {
			//FilePart：用来上传文件的类
			FilePart fp = new FilePart("file", f);
			Part[] parts = { fp };
			//对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
			MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());
			postMethod.setRequestEntity(mre);
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 设置连接时间
			int status = client.executeMethod(postMethod);
			System.out.println("======================"+status);
			if (status == HttpStatus.SC_OK) {
				JSONObject object = JSONObject.parseObject(postMethod.getResponseBodyAsString());
				if(object.getBoolean("success")){
					return success(null);
				}
				return error(object.getString("message"));
			} else {
				return error(MsgConstant.SYSTEM_ERROR);
			}
		} catch (Exception e) {
			return error(MsgConstant.SYSTEM_ERROR);
		} finally {
			//释放连接
			postMethod.releaseConnection();
		}
	}

	@GetMapping("uploadPage")
	public String pageMapping(){
		return "/upload/uploadPage";
	}
}
