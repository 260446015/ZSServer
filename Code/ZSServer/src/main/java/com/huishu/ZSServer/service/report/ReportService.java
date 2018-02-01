package com.huishu.ZSServer.service.report;

import java.util.Set;

import com.huishu.ZSServer.entity.vo.PdfVO;
import org.springframework.data.domain.Page;

import com.huishu.ZSServer.entity.report.FilePdf;
import com.huishu.ZSServer.entity.dto.ReportSearchDTO;

/**
 * 招商报告
 * 
 * @author yindq
 * @date 2017年12月13日
 */
public interface ReportService {
	
	/**
	 * 获取报告筛选项
	 * @return
	 */
	Set<Integer> getScreeningItem();
	
	/**
	 * 查询招商报告PDF列表
	 * @param dto
	 * @return
	 */
	Page<FilePdf> getExpertReport(ReportSearchDTO dto);
	
	/**
	 * 获取报告筛选项
	 * @param id
	 * @return
	 */
	Set<Integer> getUserScreeningItem(Long userId);
	
	/**
	 * 查询招商报告PDF列表
	 * @param userId
	 * @param dto
	 * @return
	 */
	Page<FilePdf> getUserExpertReport(Long userId,ReportSearchDTO dto);
	
	/**
	 * 查看PDF详情
	 * @param id
	 * @return
	 */
	PdfVO getReportContent(Long id);
	
	/**
	 * PDF下载记录
	 * @param id
	 * @return
	 */
	void addReportRecord(Long userId,Long id);
}
