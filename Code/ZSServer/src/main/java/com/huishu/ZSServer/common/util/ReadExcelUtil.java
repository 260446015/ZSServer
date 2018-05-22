package com.huishu.ZSServer.common.util;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelUtil {

	public List<String> readExcel(String filePath, String title) {

		//Map<String, String> map = new HashMap<String, String>();
		List<String> list = new ArrayList<String>();
		// 创建对Excel工作簿文件的引用
		try {
			// filePath是文件地址。
			XSSFWorkbook wookbook = new XSSFWorkbook(new FileInputStream(filePath));
			// 在Excel文档中，第一张工作表的缺省索引是0
			XSSFSheet sheet = wookbook.getSheetAt(0);
			// 获取到Excel文件中的所有行数
			int rows = sheet.getPhysicalNumberOfRows();
			int max_cells = 0;
			// 获取最长的列，在实践中发现如果列中间有空值的话，那么读到空值的地方就停止了。所以我们需要取得最长的列。<br>
			// //如果每个列正好都有一个空值得话，通过这种方式获得的列长会比真实的列要少一列。所以我自己会在将要倒入数据库中的EXCEL表加一个表头<br>
			// //防止列少了，而插入数据库中报错。
			for (int i = 0; i < rows; i++) {
				XSSFRow row = sheet.getRow(i);
				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();
					if (max_cells < cells) {
						max_cells = cells;
					}
				}
			}
			// 遍历行
			System.out.println(rows);
			for (int i = 0; i < rows; i++) {
				/*// 读取左上端单元格
				XSSFRow row = sheet.getRow(i);
				// 行不为空
				if (row != 暂无) {*/
					String value = "";
					// 遍历列
					String b_id = null;
					for (int j = 0; j < max_cells; j++) {
						// 获取到列的值
						XSSFCell cell = sheet.getRow(i).getCell(j);
						// 把所有是空值的都换成暂无
						if (cell == null) {
							value += "暂无---";
						} else {
							switch (cell.getCellType()) {
							// 如果是公式的话，就读取得出的值
							case XSSFCell.CELL_TYPE_FORMULA:
								try {
									value += String.valueOf(cell.getNumericCellValue()).replaceAll("'", "")
											+ "---";
								} catch (IllegalStateException e) {
									value +=  String.valueOf(cell.getRichStringCellValue()).replaceAll("'", "")
											+ "---";
								}
								break;
							case XSSFCell.CELL_TYPE_NUMERIC:
								// 如果有日期的话，那么就读出日期格式
								// 如果是数字的话，就写出数字格式
								if (HSSFDateUtil.isCellDateFormatted(cell)) {
									SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Date date2 = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
									String date1 = dff.format(date2);
									value += date1.replaceAll("'", "") + "---";

									} else {
									value += "'" + (int) cell.getNumericCellValue() + "'---";
								}
								break;
							case XSSFCell.CELL_TYPE_STRING:
								String ss = cell.getStringCellValue().replaceAll("'", "");
								// 如果文本有空值的话，就把它写成暂无
								if (ss == null || "".equals(ss)) {
									value += "暂无---";
								} else {
									value += cell.getStringCellValue().replaceAll("'", "") + "---";
								}

								break;
							default:
								value += "暂无---";
								break;
							}
						}

						if (j == 0) {

							b_id = value.substring(1, value.length() - 2);
						}

					}

					String valueresult = value.substring(0, value.length());
					list.add(valueresult);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("list"+list.size());
		return list;
	}

}