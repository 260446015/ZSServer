package com.huishu.ManageServer.controller.report;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dbFirst.FilePdf;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.FinancingDTO;
import com.huishu.ManageServer.entity.dto.HtmlAddDTO;
import com.huishu.ManageServer.service.report.ReportService;
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
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String show(@PathVariable String page,String id,Model model) {
		if("reportEdit".equals(page)){
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
	@RequestMapping(value = "getHtmlData.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getHtmlData(Long id,String type) {
		if(StringUtil.isEmpty(type)){
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
	 * 添加h5报告基本数据
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
			Boolean flag = reportService.addHtmlData(dto);
			if (flag) {
				return success(MsgConstant.OPERATION_SUCCESS);
			} else {
				return error(MsgConstant.OPERATION_ERROR);
			}
		}catch (Exception e){
			LOGGER.error("获取h5报告数据失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
}