package com.huishu.ZSServer.common.util;

import com.huishu.ZSServer.app.Application;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class POIUtils {

	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;
	private XSSFWorkbook xwb;
	private XSSFSheet xsheet;
	private XSSFRow xrow;
//	@Autowired
//	private BaseElasticsearch baseElasticsearch;
//	@Autowired
//	private IndustryClassRepository industryClassRepository;

	/**
	 * 读取xls Excel表格表头的内容
	 * 
	 * @return String 表头内容的数组
	 */
	public String[] readExcelTitleXls(InputStream is) {
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			// title[i] = getStringCellValue(row.getCell((short) i));
			title[i] = getCellFormatValueXls(row.getCell(i));
		}
		return title;
	}
	/**
	 * 读取xlsx Excel表格表头的内容
	 * 
	 * @return String 表头内容的数组
	 */
	public String[] readExcelTitleXlsx(InputStream is) {
		try {
			xwb = new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		xsheet = xwb.getSheetAt(0);
		xrow = xsheet.getRow(0);
		// 标题总列数
		int colNum = xrow.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			// title[i] = getStringCellValue(row.getCell((short) i));
			title[i] = getCellFormatValueXlsx(xrow.getCell(i));
		}
		return title;
	}

	/**
	 * 读取Excel数据内容
	 * 
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public Map<Integer, String> readExcelContentXls(InputStream is,String split) {
		Map<Integer, String> content = new HashMap<Integer, String>();
		String str = "";
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			while (j < colNum) {
				// 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
				// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
				// str += getStringCellValue(row.getCell((short) j)).trim() +
				// "-";
				str += (getCellFormatValueXls(row.getCell(j)).equals("") ? " "
						: getCellFormatValueXls(row.getCell(j))) + split;
				j++;
			}
			content.put(i, str);
			str = "";
		}
		return content;
	}
	/**
	 * 读取Xlsx Excel数据内容
	 * 
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public Map<Integer, String> readExcelContentXlsx(InputStream is,String split) {
		Map<Integer, String> content = new HashMap<Integer, String>();
		String str = "";
		try {
			xwb = new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		xsheet = xwb.getSheetAt(0);
		// 得到总行数
		int rowNum = xsheet.getLastRowNum();
		xrow = xsheet.getRow(0);
		int colNum = xrow.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			xrow = xsheet.getRow(i);
			int j = 0;
			while (j < colNum) {
				// 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
				// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
				// str += getStringCellValue(row.getCell((short) j)).trim() +
				// "-";
				str += (getCellFormatValueXlsx(xrow.getCell((short) j)).equals("") ? " "
						: getCellFormatValueXlsx(xrow.getCell((short) j))) + split;
				j++;
			}
			content.put(i, str);
			str = "";
		}
		return content;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getStringCellValueXls(HSSFCell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 获取单元格数据内容为日期类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getDateCellValueXls(HSSFCell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate();
			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringCellValueXls(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellFormatValueXls(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式

					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();

					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);

				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;

	}
	/**
	 * 根据XSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellFormatValueXlsx(XSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case XSSFCell.CELL_TYPE_NUMERIC:
			case XSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式
					
					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();
					
					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
					
				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case XSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
				// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
		
	}

	public void writeExceXlsx(List<Map<Integer,String>> arr, String[] titles, String filePath) throws Exception{
		//创建工作簿
		XSSFWorkbook workBook = new XSSFWorkbook();
		//创建工作表
		XSSFSheet sheet = workBook.createSheet("helloWorld");
		XSSFRow titleRow = sheet.createRow(0);

		for(int i= 0;i<titles.length;i++){
			XSSFCell titleCell = titleRow.createCell(i);
			titleCell.setCellValue(titles[i]);
		}
		//创建单元格，操作第三行第三列
		for(int i = 1;i<=arr.size();i++) {
			//创建行
			XSSFRow row = sheet.createRow(i);
//			Object target = arr.get(i);
			/*Field[] declaredFields = target.getClass().getDeclaredFields();
			for(int j =0 ;j<declaredFields.length;j++){
				XSSFCell cell = row.createCell(j);
				cell.setCellValue(declaredFields[i].toString());
			}*/
			int temp = 0;
			try {
				Map<Integer, String> integerStringMap = arr.get(i - 1);
				for (Map.Entry<Integer,String> entry:integerStringMap.entrySet()) {
					temp = entry.getKey();
					XSSFCell cell = row.createCell( entry.getKey());
					cell.setCellValue(entry.getValue());
				}
			} catch (Exception e) {
				System.out.println(temp);
				e.printStackTrace();
			}
		}

		FileOutputStream outputStream = new FileOutputStream(new File(filePath));
		workBook.write(outputStream);

//		workBook.close();//记得关闭工作簿
	}
}
