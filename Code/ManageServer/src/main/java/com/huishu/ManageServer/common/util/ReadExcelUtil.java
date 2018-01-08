package com.huishu.ManageServer.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
										if((int) cell.getNumericCellValue()==1){
											value += "" + (int) cell.getNumericCellValue() + "---";
										}else{
											value += "" + (Double) cell.getNumericCellValue() + "---";
										}
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

	/**
	 * 存储关键词
	 * @param array
	 * @param string
	 */
	public static  void saveKeyWord(JSONArray array, String filePath) {
		HSSFWorkbook wb = createExcelInfo(array);
		 try  
	        {  
				FileOutputStream fout = new FileOutputStream(filePath);  
	            wb.write(fout);  
	            fout.close();  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        }  
		 
	}

	@SuppressWarnings("deprecation")
	private static HSSFWorkbook createExcelInfo(JSONArray array) {
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("sheet");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("企业名称");  
        cell.setCellStyle(style);
        
        cell =  row.createCell((short) 1);  
        cell.setCellValue("相关关键词汇总");  
        cell.setCellStyle(style);
        
        cell = row.createCell((short) 2);  
        cell.setCellValue("主体姓名");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);  
        cell.setCellValue("主体地域");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);  
        cell.setCellValue("主体公司");  
        cell.setCellStyle(style);
        for(int i=0;i<50;i++){
        	cell = row.createCell((short) (5+i));  
            cell.setCellValue("关键词"+(i+1));  
            cell.setCellStyle(style);
        }
        int line = 0;
        System.out.println(array.size());
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		for(int i=0;i<array.size();i++ ){
			// 第四步，创建单元格，并设置值  
 			line ++;
 			row=sheet.createRow((int) line);
			JSONObject obj = array.getJSONObject(i);
			String str = obj.getString("企业名称");
			String json1 = obj.getJSONArray("主体姓名").toString();
			String json2 = obj.getJSONArray("主体地域").toString();
			String json3 = obj.getJSONArray("主体公司").toString();
			row.createCell((short)0).setCellValue(str);
			row.createCell((short)1).setCellValue(obj.getString("相关关键词"));
			row.createCell((short)2).setCellValue(json1);
			row.createCell((short)3).setCellValue(json2);
			if(json3.length()>=32767){
				row.createCell((short)4).setCellValue(json3.substring(0, 32700));
			}
			JSONArray arr = obj.getJSONArray("关键词");
			int count = 5;
			for(int j =0;j<arr.size();j++){
				 row.createCell((short)count).setCellValue(arr.getString(j));
					 count++;
			}
		}
		return wb;
	}
		
}