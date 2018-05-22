package com.huishu.ManageServer.service.report;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.entity.dbFirst.FilePdf;
import com.huishu.ManageServer.entity.dbFirst.h5.MonthlyReport;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.HtmlAddDTO;
import com.huishu.ManageServer.entity.dto.ParagraphAddDTO;
import com.huishu.ManageServer.entity.vo.HeadlinesVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 招商报告
 * 
 * @author yindq
 * @date 2017年12月13日
 */
public interface ReportService {
	
	/**
	 * 查询招商报告PDF列表
	 * @param dto
	 * @return
	 */
	Page<FilePdf> getExpertReport(AbstractDTO dto);

	/**
	 * 删除PDF
	 * @param id
	 * @return
	 */
	Boolean dropReport(Long id);

	/**
	 * 修改
	 * @param dto
	 * @return
	 */
	Boolean saveReport(FilePdf dto);

	/**
	 * 详情
	 * @param id
	 * @return
	 */
	FilePdf getReportById(Long id);

	/**
	 * 获取h5报告数据
	 * @param id
	 * @param type
	 * @return
	 */
	Object getHtmlData(Long id,String type);

	/**
	 * 获取h5报告全部数据
	 * @param id
	 * @return
	 */
	JSONObject getHtmlData(Long id);

	/**
	 * 获取h5报告焦点/动态数据
	 * @param id
	 * @return
	 */
	JSONArray getInfoData(Long id, String type);

	/**
	 * 获取h5报告各地新闻数据
	 * @param headlinesId
	 * @return
	 */
	JSONArray getLocalNews(Long headlinesId);

	/**
	 * 获取h5报告列表
	 * @param dto
	 * @return
	 */
	Page<MonthlyReport> getHtmlReport(AbstractDTO dto);

	/**
	 * 删除H5
	 * @param id
	 * @return
	 */
	Boolean dropHtmlData(Long id);

	/**
	 * 添加h5报告基本数据
	 * @param dto
	 * @return
	 */
	Long addHtmlData(HtmlAddDTO dto);

	/**
	 * 添加h5报告段落数据
	 * @param dto
	 * @return
	 */
	Boolean addParagraphData(ParagraphAddDTO dto);

	/**
	 * 获取h5报告模块信息
	 * @param id
	 * @return
	 */
	List<HeadlinesVO> findHtmlHeadlines(Long id);
}
