package com.huishu.ZSServer.controller.report;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.report.FilePdf;
import com.huishu.ZSServer.entity.dto.ReportSearchDTO;
import com.huishu.ZSServer.service.report.ReportService;

/**
 * 招商报告
 * 
 * @author yindq
 * @date 2017年11月10日
 */
@Controller
@RequestMapping("/apis/report")
public class ReportController extends BaseController {

	private static final Logger LOGGER = Logger.getLogger(ReportController.class);

	@Autowired
	private ReportService reportService;
	
	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String show(@PathVariable String page,String fileId,String isUser,Model model) {
		if(!StringUtil.isEmpty(fileId)){
			model.addAttribute("fileId",fileId);
		}
		if(StringUtil.isEmpty(isUser)){
			model.addAttribute("isUser",false);
		}else{
			model.addAttribute("isUser",true);
		}
		return "/report/"+page;
	}
	/**
	 * 直接跳转页面
	 * @param page
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/{page}.htm"}, method = RequestMethod.GET)
	public String showPage(@PathVariable String page,String id,String type,Model model) {
		model.addAttribute("id",id);
		model.addAttribute("type",type);
		return "/report/"+page;
	}

	/**
	 * 获取h5报告数据
	 * @param type
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getHtmlData.do", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getHtmlData(Long id,String type) {
		if(id==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return success(reportService.getHtmlData(id,type));
		}catch (Exception e){
			LOGGER.error("获取h5报告数据失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	/**
	 * 获取报告筛选项
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getScreeningItem.json", method = RequestMethod.GET)
	public AjaxResult getScreeningItem() {
		try {
			return success(reportService.getScreeningItem());
		} catch (Exception e) {
			LOGGER.error("获取报告筛选项失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 获取招商报告PDF列表
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getExpertReport.json", method = RequestMethod.POST)
	public AjaxResult getExpertReport(@RequestBody ReportSearchDTO dto) {
		if(dto==null||StringUtil.isEmpty(dto.getType())||StringUtil.isEmpty(dto.getYear())){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Page<FilePdf> page = reportService.getExpertReport(dto);
			return successPage(page,dto.getPageNumber()+1);
		} catch (Exception e) {
			LOGGER.error("查询失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 获取用户下载的报告筛选项
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getUserScreeningItem.json", method = RequestMethod.GET)
	public AjaxResult getUserScreeningItem() {
		try {
			return success(reportService.getUserScreeningItem(getUserId()));
		} catch (Exception e) {
			LOGGER.error("获取报告筛选项失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 获取用户下载PDF列表
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getUserExpertReport.json", method = RequestMethod.POST)
	public AjaxResult getUserExpertReport(@RequestBody ReportSearchDTO dto) {
		if(dto==null||StringUtil.isEmpty(dto.getType())||StringUtil.isEmpty(dto.getYear())){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Page<FilePdf> page = reportService.getUserExpertReport(getUserId(),dto);
			return successPage(page,dto.getPageNumber()+1);
		} catch (Exception e) {
			LOGGER.error("查询失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 查看PDF详情
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getReportContent.json", method = RequestMethod.GET)
	public AjaxResult getReportContent(Long id) {
		if(id==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return success(reportService.getReportContent(id));
		} catch (Exception e) {
			LOGGER.error("查询失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 下载记录
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addReportRecord.json", method = RequestMethod.GET)
	public AjaxResult addReportRecord(Long id) {
		if(id==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			reportService.addReportRecord(getUserId(),id);
			return success(MsgConstant.OPERATION_SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
}