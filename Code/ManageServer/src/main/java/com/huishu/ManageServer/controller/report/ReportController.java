package com.huishu.ManageServer.controller.report;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dbFirst.FilePdf;
import com.huishu.ManageServer.entity.dbFirst.h5.MonthlyReport;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.HtmlAddDTO;
import com.huishu.ManageServer.entity.dto.ParagraphAddDTO;
import com.huishu.ManageServer.service.report.ReportService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * 招商报告
 * 
 * @author yindq
 * @date 2018/01/01
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
	@RequestMapping(value = {"/{page}.html","/{page}.htm"}, method = RequestMethod.GET)
	public String show(@PathVariable String page,String id,Model model) {
		if("reportEdit".equals(page)||("addHtml2").equals(page)||("htmlInfo").equals(page)){
			model.addAttribute("id",id);
		}
		return "/report/"+page;
	}
	
	/**
	 * 获取招商报告PDF列表
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getExpertReport.json", method = RequestMethod.POST)
	public AjaxResult getExpertReport(@RequestBody AbstractDTO dto) {
		try {
			Page<FilePdf> page = reportService.getExpertReport(dto);
			return successPage(page,dto.getPageNum()+1);
		} catch (Exception e) {
			LOGGER.error("查询失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 删除报告
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "dropReport.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult dropReport(Long id) {
		if(id==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean flag = reportService.dropReport(id);
			if (flag) {
				return success(MsgConstant.OPERATION_SUCCESS);
			} else {
				return error(MsgConstant.OPERATION_ERROR);
			}
		}catch (Exception e){
			LOGGER.error("删除用户失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 查看报告详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getReportById.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getReportById(Long id) {
		if(id==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return success(reportService.getReportById(id));
		}catch (Exception e){
			LOGGER.error("查看失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 修改报告信息
	 * @param dto
	 * @return
	*/
	@RequestMapping(value = "saveReport.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveReport(@RequestBody FilePdf dto) {
		if(dto==null||StringUtil.isEmpty(dto.getData())||StringUtil.isEmpty(dto.getFileType())||StringUtil.isEmpty(dto.getLabel())
				||StringUtil.isEmpty(dto.getName())||StringUtil.isEmpty(dto.getUrl())){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean flag = reportService.saveReport(dto);
			if (flag) {
				return success(MsgConstant.OPERATION_SUCCESS);
			} else {
				return error(MsgConstant.OPERATION_ERROR);
			}
		}catch (Exception e){
			LOGGER.error("修改失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
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
		if(id==null||StringUtil.isEmpty(type)){
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
	 * 获取h5报告全部数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getHtmlData.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getHtmlData(Long id) {
		if(id==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return success(reportService.getHtmlData(id));
		}catch (Exception e){
			LOGGER.error("获取h5报告数据失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 获取h5报告列表
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getHtmlReport.json", method = RequestMethod.POST)
	public AjaxResult getHtmlReport(@RequestBody AbstractDTO dto) {
		try {
			Page<MonthlyReport> page = reportService.getHtmlReport(dto);
			return successPage(page,dto.getPageNum()+1);
		} catch (Exception e) {
			LOGGER.error("查询失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 添加h5报告基本数据与模块信息
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "addHtmlData.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addHtmlData(@RequestBody HtmlAddDTO dto) {
		if(dto==null||StringUtil.isEmpty(dto.getName())||StringUtil.isEmpty(dto.getTime())
				||dto.getArr()==null||dto.getArr().length==0){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Long l = reportService.addHtmlData(dto);
			if (l==null) {
				return error(MsgConstant.OPERATION_ERROR);
			} else {
				return success(l);
			}
		}catch (Exception e){
			LOGGER.error("获取h5报告数据失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 添加h5报告段落数据
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "addParagraphData.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addParagraphData(@RequestBody ParagraphAddDTO dto) {
		if(dto==null||dto.getObj().length==0){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean b = reportService.addParagraphData(dto);
			if (b) {
				return success(MsgConstant.OPERATION_SUCCESS);
			} else {
				return error(MsgConstant.OPERATION_ERROR);
			}
		}catch (Exception e){
			LOGGER.error("添加h5报告段落数据失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 获取h5报告模块信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "findHtmlHeadlines.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult findHtmlHeadlines(Long id) {
		if(id==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return success(reportService.findHtmlHeadlines(id));
		}catch (Exception e){
			LOGGER.error("获取h5报告数据失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
}