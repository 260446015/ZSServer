package com.huishu.ait.controller.companyIntelligence;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ait.common.util.exporter.ExcelExporter;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.service.companyIntelligence.CompanyIntelligenceService;

@SuppressWarnings("serial")
@RestController
@RequestMapping(value = "intelligence")
public class ExportController extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(ExportController.class);

	@Autowired
	private CompanyIntelligenceService companyIntelligenceService;

	@RequestMapping(value = "exportFile.json")
	public void exportFile(HttpServletRequest request, HttpServletResponse response, String[] ids)
			throws ServletException, IOException {
		List<AITInfo> list = companyIntelligenceService.getCompanyIntelligenceByIds(ids);
		try {
			String fileName = "企业情报画像.xls";
			fileName = new String(fileName.getBytes("utf8"), "ISO8859_1");
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);// 指定下载的文件名
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			OutputStream output = response.getOutputStream();
			BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);

			String worksheetTitle = "企业情报画像信息";

			HSSFWorkbook wb = new HSSFWorkbook();

			// 创建单元格样式
			HSSFCellStyle cellStyleTitle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			cellStyleTitle.setWrapText(false);
			// ------------------------------------------------------------------
			HSSFCellStyle cellStyle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			cellStyle.setWrapText(false);
			// ------------------------------------------------------------------
			// 设置单元格字体
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontName("宋体");
			font.setFontHeight((short) 200);
			cellStyleTitle.setFont(font);

			// 工作表名
			String id = "id";
			String title = "标题";
			String content = "内容";
			String publishTime = "发布时间";
			String source = "来源";
			String dimension = "纬度";

			HSSFSheet sheet = wb.createSheet();
			ExcelExporter exportExcel = new ExcelExporter(wb, sheet);
			sheet.setDefaultColumnWidth(20);
			sheet.setDefaultRowHeightInPoints(20);
			// 创建报表头部
			exportExcel.createNormalHead(worksheetTitle, 5);
			// 定义第一行
			HSSFRow row1 = sheet.createRow(1);

			// 第一行第1列
			HSSFCell cell1 = row1.createCell(0);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString(id));
			// 第一行第2列
			cell1 = row1.createCell(1);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString(title));
			// 第一行第3列
			cell1 = row1.createCell(2);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString(content));
			// 第一行第4列
			cell1 = row1.createCell(3);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString(publishTime));
			// 第一行第5列
			cell1 = row1.createCell(4);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString(source));
			// 第一行第6列
			cell1 = row1.createCell(5);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString(dimension));

			// 定义第二行 填充内容
			HSSFRow row = sheet.createRow(2);
			HSSFCell cell = row.createCell(1);
			for (int i = 0; i < list.size(); i++) {
				AITInfo aitInfo = list.get(i);
				row = sheet.createRow(i + 2);
				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(aitInfo.getId() + ""));
				cell = row.createCell(1);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(aitInfo.getTitle() + ""));
				cell = row.createCell(2);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(aitInfo.getContent() + ""));
				cell = row.createCell(3);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(aitInfo.getPublishDateTime() + ""));
				cell = row.createCell(4);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(aitInfo.getSource() + ""));
				cell = row.createCell(5);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(aitInfo.getDimension() + ""));
			}
			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			log.error("导出失败", e.getMessage());
		} finally {
			list.clear();
		}
	}
}
