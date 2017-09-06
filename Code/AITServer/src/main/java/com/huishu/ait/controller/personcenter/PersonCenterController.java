package com.huishu.ait.controller.personcenter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.export.News;
import com.huishu.ait.common.util.ConcersUtils;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.common.util.exporter.WordExporter;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.PersonCollectDto;
import com.huishu.ait.service.personcenter.PersonCenterService;
import com.lowagie.text.Document;

/**
 * 
 * @author yindawei
 * @date 2017年9月4日下午12:02:11
 * @description 下载中心的Controller
 * @version 1.0
 */
@RestController
@RequestMapping("/apis/background")
public class PersonCenterController extends BaseController {

	@Autowired
	private PersonCenterService personCenterService;

	private final Logger LOGGER = LoggerFactory.getLogger(PersonCenterController.class);

	/**
	 * 个人中心中查询用户收藏的专家观点，政策，企业报告的Controller
	 * 
	 * @param PersonCollectDto里面传递query为查询类型
	 *            分页请传pageNumber当前页 pageSize页大小
	 * @return 返回一个jsonobj，里面存有专家观点，政策，企业报告
	 */
	@RequestMapping(value = "/getPersonCollection.json", method = RequestMethod.POST)
	public AjaxResult getPersonCollection(@RequestBody PersonCollectDto dto) {
		if (getUserId() == null) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		initPage(dto);
		JSONArray data = null;
		try {
			data = personCenterService.getPersonCollection(dto);
		} catch (Exception e) {
			LOGGER.error("个人中心中查询用户收藏的专家观点，政策，企业报告失败!", e.getMessage());
			return error("个人中心中查询用户收藏的专家观点，政策，企业报告失败!");
		}
		return success(data);
	}

	/**
	 * 下载文档
	 * 
	 * @param type
	 *            pdf请传pdf,word请传doc字符串
	 * @param articleId
	 *            文章id
	 * @param path
	 *            用户保存路径
	 * @param fileName
	 *            用户存储名字
	 * @return
	 */
	@RequestMapping(value = "/downloadCol.json", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		String articleId = request.getParameter("articleId");
		String path = request.getParameter("path");
		String fileName = request.getParameter("fileName");
		// String articleId = "40";
		// String path = "F:/test/";
		if (StringUtil.isEmpty(fileName)) {
			fileName = new SimpleDateFormat("yyyyMMddHH:mm:ss").format(new Date()) + "." + type;
		} else {
			fileName = fileName + "." + type;
		}

		response.setContentType("application/octet-stream; charset=UTF-8");
		if ("doc".equals(type)) {
			response.setHeader("content-disposition", "attachment;filename=" + fileName);
		} else if ("pdf".equals(type)) {
			response.setHeader("content-disposition", "attachment;filename=" + fileName);
		}
		OutputStream os = null;
		try {
			Document document = new Document();
			os = response.getOutputStream();
			News artic = personCenterService.getData(articleId,getUserId());
			WordExporter ex = new WordExporter(os, document);
			ex.exportWord(request, artic, response, fileName, type);
			os.flush();
		} catch (Exception e) {
			LOGGER.error("下载文档出错", e.getMessage());
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private PersonCollectDto initPage(PersonCollectDto dto) {
		if (dto.getPageNumber() == null) {
			dto.setPageNumber(ConcersUtils.ES_MIN_PAGENUMBER);
		}
		if (dto.getPageSize() == null) {
			dto.setPageSize(ConcersUtils.PAGE_SIZE);
		}
		if (dto.getPageNumber() > ConcersUtils.ES_MAX_PAGENUMBER) {
			dto.setPageNumber(ConcersUtils.ES_MAX_PAGENUMBER);
		}
		dto.setUserId(getUserId());
		return dto;
	}
}
