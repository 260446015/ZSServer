package com.huishu.ZSServer.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.ConfConstant;

/**
 * pdf在线预览与下载
 * 
 * @author yindq
 * @date 2017年11月6日
 */
@RestController
@RequestMapping("pdf")
public class PdfController extends BaseController {

	private static final Logger LOGGER = Logger.getLogger(PdfController.class);

	// 设置文件上传大小
	public static long fileSize = 1 * 1024 * 1024;
	
	public static String filePath = "";
	
	/**
	 * 在线预览PDF文件
	 * @param path
	 */
	@RequestMapping(value = "/changePDFUrl.do", method = RequestMethod.GET)
    public void changePDFUrl(String path) {
		filePath=path;
    }
	
	/**
	 * 在线预览PDF文件
	 * @param response
	 */
	@RequestMapping(value = "/previewPDF.do", method = RequestMethod.GET)
    public void displayPDF(HttpServletResponse response) {
		try {
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment;fileName=test.pdf");
            response.setContentType("multipart/form-data");
            OutputStream outputStream = response.getOutputStream();
            IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
        } catch(Exception e) {
        	LOGGER.error("在线预览PDF失败！", e);
        }
    }

	/**
	 * PDF文件上传
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadPDF.do", method = RequestMethod.POST)
	public AjaxResult imageUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		LOGGER.info("file name is :" + file.getOriginalFilename());
		if (!file.isEmpty()) {
			if (file.getSize() > fileSize) {
				return error("文件超过上传大小");
			}
			String OriginalFilename = file.getOriginalFilename();
			String fileSuffix = OriginalFilename.substring(OriginalFilename.lastIndexOf(".") + 1).toLowerCase();
			if (!"pdf".equals(fileSuffix)) {
				return error("文件格式错误");
			}
			if (!ServletFileUpload.isMultipartContent(request)) {
				return error("没有文件上传");
			}
			File uploadDir = new File(ConfConstant.DEFAULT_PDF);
			if (!uploadDir.isDirectory()) {
				if (!uploadDir.mkdirs()) {
					return error("上传文件路径非法");
				}
			}
			if (!uploadDir.canWrite()) {
				return error("上传目录没有写权限");
			}
			String newname = UUID.randomUUID() + "." + fileSuffix;
			try {
				File saveFile = new File(ConfConstant.DEFAULT_PDF, newname);
				file.transferTo(saveFile);
				return success(ConfConstant.DEFAULT_PDF + "/" + newname).setMessage("上传成功");
			} catch (Exception e) {
				LOGGER.error("PDF上传失败！", e);
				return error("上传失败");
			}
		} else {
			return error("没有文件上传");
		}
	}
}