package com.huishu.ManageServer.service.report;

import com.huishu.ManageServer.entity.dbFirst.FilePdf;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.HtmlAddDTO;
import org.springframework.data.domain.Page;

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
	 * 添加h5报告基本数据
	 * @param dto
	 * @return
	 */
	Boolean addHtmlData(HtmlAddDTO dto);
}
