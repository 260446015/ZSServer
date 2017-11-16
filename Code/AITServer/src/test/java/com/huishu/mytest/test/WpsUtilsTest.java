package com.huishu.mytest.test;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.app.Application;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.repository.ExpertOpinion.BaseElasticsearch;

@RunWith(SpringJUnit4ClassRunner.class)
// 这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class WpsUtilsTest {

	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;
	private XSSFWorkbook xwb;
	private XSSFSheet xsheet;
	private XSSFRow xrow;
	@Autowired
	private BaseElasticsearch baseElasticsearch;
//	@Autowired
//	private IndustryClassRepository industryClassRepository;

	/**
	 * 读取xls Excel表格表头的内容
	 * 
	 * @param InputStream
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
	 * @param InputStream
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
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public Map<Integer, String> readExcelContentXls(InputStream is) {
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
						: getCellFormatValueXls(row.getCell(j))) + "-";
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
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public Map<Integer, String> readExcelContentXlsx(InputStream is) {
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
			row = sheet.getRow(i);
			int j = 0;
			while (j < colNum) {
				// 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
				// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
				// str += getStringCellValue(row.getCell((short) j)).trim() +
				// "-";
				str += (getCellFormatValueXlsx(xrow.getCell((short) j)).equals("") ? " "
						: getCellFormatValueXlsx(xrow.getCell((short) j))) + "-";
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

	/*@Test
	public void testSaveIndustryClass() {
		Map<String, String> map = new HashMap<>();
		map.put("互联网+", "网络游戏,大数据,电子商务,网络视听,移动阅读,智能硬件");
		map.put("高科技", "新一代信息技术,智能机器人,生物医药,节能环保技术装备,新能源,新材料,航空装备");
		map.put("文化创意", "动漫制作,影视传媒,图书出版,广告营销");
		map.put("精英配套", "金融服务,住宅地产,商业综合体,康体美容,母婴产业,健康产业,教育培训");
		map.put("滨海旅游", "特色旅游综合体,体育产业");
		map.put("港口物流", "生鲜贸易,食品加工,冷链物流");
		try {
			// 对读取Excel表格标题测试
			InputStream is = new FileInputStream("C:\\Users\\yindawei\\Desktop\\慧数招商\\园区招商网-全国园区省份地域信息.xls");
			WpsUtilsTest util = new WpsUtilsTest();
			String[] title = util.readExcelTitle(is);
			System.out.println("获得Excel表格的标题:");
			for (String s : title) {
				System.out.print(s + " ");
			}

			// 对读取Excel表格内容测试
			InputStream is2 = new FileInputStream("C:\\Users\\yindawei\\Desktop\\慧数招商\\园区招商网-全国园区省份地域信息.xls");
			Map<Integer, String> map2 = util.readExcelContent(is2);
			System.out.println("获得Excel表格的内容:");
			List<GardenCollection> gardenList = new ArrayList<>();
			for (int i = 1; i <= map2.size(); i++) {
				String str = (String) map2.get(i);
				String[] split = str.split("-");
				GardenCollection gc = new GardenCollection();
				gc.setName(split[0]);
				gc.setLevel(split[1]);
				gc.setProvinces(split[2]);
				gc.setArea(split[3]);
				if (split[4] == null || "".equals(split[4]))
					gc.setLeadIndustry(null);
				else
					gc.setLeadIndustry(split[4].split("、"));
				gc.setAddress(split[5]);
				gardenList.add(gc);
			}
			Map<String, List<GardenCollection>> huliwang = new HashMap<>();
			Map<String, List<GardenCollection>> gaokeji = new HashMap<>();
			Map<String, List<GardenCollection>> wenhuachuangyi = new HashMap<>();
			Map<String, List<GardenCollection>> jingyingpeitao = new HashMap<>();
			Map<String, List<GardenCollection>> binhailvyou = new HashMap<>();
			Map<String, List<GardenCollection>> gangkouwuliu = new HashMap<>();
			for (GardenCollection gc : gardenList) {
				if (!StringUtil.isEmpty(gc.getArea())) {
					String[] arr = gc.getLeadIndustry();
					for (int i = 0; i < arr.length; i++) {
						String str = arr[i];
						for (Map.Entry<String, String> entry : map.entrySet()) {
							if (entry.getKey().equals(str) || entry.getValue().contains(str)) {
								if ("互联网+,网络游戏,大数据,电子商务,网络视听,移动阅读,智能硬件".contains(str)) {
									if (huliwang.containsKey(gc.getArea())) {
										for (Map.Entry<String, List<GardenCollection>> e : huliwang.entrySet()) {
											if (e.getKey().equals(gc.getArea())) {
												e.getValue().add(gc);
											}
										}
									} else {
										List<GardenCollection> list = new ArrayList<>();
										list.add(gc);
										huliwang.put(gc.getArea(), list);
									}
								} else if ("高科技,新一代信息技术,智能机器人,生物医药,节能环保技术装备,新能源,新材料,航空装备".contains(str)) {
									if (gaokeji.containsKey(gc.getArea())) {
										for (Map.Entry<String, List<GardenCollection>> e : gaokeji.entrySet()) {
											if (e.getKey().equals(gc.getArea())) {
												e.getValue().add(gc);
											}
										}
									} else {
										List<GardenCollection> list = new ArrayList<>();
										list.add(gc);
										gaokeji.put(gc.getArea(), list);
									}
								} else if ("文化创意,动漫制作,影视传媒,图书出版,广告营销".contains(str)) {
									if (wenhuachuangyi.containsKey(gc.getArea())) {
										for (Map.Entry<String, List<GardenCollection>> e : wenhuachuangyi.entrySet()) {
											if (e.getKey().equals(gc.getArea())) {
												e.getValue().add(gc);
											}
										}
									} else {
										List<GardenCollection> list = new ArrayList<>();
										list.add(gc);
										wenhuachuangyi.put(gc.getArea(), list);
									}
								} else if ("精英配套,金融服务,住宅地产,商业综合体,康体美容,母婴产业,健康产业,教育培训".contains(str)) {
									if (jingyingpeitao.containsKey(gc.getArea())) {
										for (Map.Entry<String, List<GardenCollection>> e : jingyingpeitao.entrySet()) {
											if (e.getKey().equals(gc.getArea())) {
												e.getValue().add(gc);
											}
										}
									} else {
										List<GardenCollection> list = new ArrayList<>();
										list.add(gc);
										jingyingpeitao.put(gc.getArea(), list);
									}
								} else if ("滨海旅游,特色旅游综合体,体育产业".contains(str)) {
									if (binhailvyou.containsKey(gc.getArea())) {
										for (Map.Entry<String, List<GardenCollection>> e : binhailvyou.entrySet()) {
											if (e.getKey().equals(gc.getArea())) {
												e.getValue().add(gc);
											}
										}
									} else {
										List<GardenCollection> list = new ArrayList<>();
										list.add(gc);
										binhailvyou.put(gc.getArea(), list);
									}
								} else if ("港口物流,生鲜贸易,食品加工,冷链物流".contains(str)) {
									if (gangkouwuliu.containsKey(gc.getArea())) {
										for (Map.Entry<String, List<GardenCollection>> e : gangkouwuliu.entrySet()) {
											if (e.getKey().equals(gc.getArea())) {
												e.getValue().add(gc);
											}
										}
									} else {
										List<GardenCollection> list = new ArrayList<>();
										list.add(gc);
										gangkouwuliu.put(gc.getArea(), list);
									}
								}
							}
						}
					}
				}
			}
			List<IndustryClass> save = new ArrayList<>();
			for (Map.Entry<String, List<GardenCollection>> entry : huliwang.entrySet()) {
				IndustryClass ic = new IndustryClass();
				ic.setArea(entry.getKey());
				ic.setIndustry("互联网");
				ic.setCount(entry.getValue().size());
				save.add(ic);
			}
			for (Map.Entry<String, List<GardenCollection>> entry : gaokeji.entrySet()) {
				IndustryClass ic = new IndustryClass();
				ic.setArea(entry.getKey());
				ic.setIndustry("高科技");
				ic.setCount(entry.getValue().size());
				save.add(ic);
			}
			for (Map.Entry<String, List<GardenCollection>> entry : wenhuachuangyi.entrySet()) {
				IndustryClass ic = new IndustryClass();
				ic.setArea(entry.getKey());
				ic.setIndustry("文化创意");
				ic.setCount(entry.getValue().size());
				save.add(ic);
			}
			for (Map.Entry<String, List<GardenCollection>> entry : jingyingpeitao.entrySet()) {
				IndustryClass ic = new IndustryClass();
				ic.setArea(entry.getKey());
				ic.setIndustry("精英配套");
				ic.setCount(entry.getValue().size());
				save.add(ic);
			}
			for (Map.Entry<String, List<GardenCollection>> entry : binhailvyou.entrySet()) {
				IndustryClass ic = new IndustryClass();
				ic.setArea(entry.getKey());
				ic.setIndustry("滨海旅游");
				ic.setCount(entry.getValue().size());
				save.add(ic);
			}
			for (Map.Entry<String, List<GardenCollection>> entry : gangkouwuliu.entrySet()) {
				IndustryClass ic = new IndustryClass();
				ic.setArea(entry.getKey());
				ic.setIndustry("港口物流");
				ic.setCount(entry.getValue().size());
				save.add(ic);
			}
			industryClassRepository.save(save);
		} catch (FileNotFoundException e) {
			System.out.println("未找到指定路径的文件!");
			e.printStackTrace();
		}
	}*/
	@Test
	public void testDeleteEs(){
		Iterable<AITInfo> findAll = baseElasticsearch.findAll();
		Map<Integer, AITInfo> map = new HashMap<>();
		for (AITInfo a : findAll) {
			String title = a.getTitle();
			String content = a.getContent();
			int hashCode = (title + content).hashCode();
			if(map.containsKey(hashCode)){
				baseElasticsearch.delete(a);
			}else{
				map.put(hashCode, a);
			}
		}
	}
	
	@Test
	public void testAdd(){
		AITInfo a = new AITInfo();
		a.setSummary("<IMG alt=\"棋哥：蓝黑红黄绿 2016体育大年的五环五色\" src=\"http://img1.gtimg.com/sports/pics/hv1/182/5/2174/141365807.pn/>");
		a.setContent("<img src=\"http://cache1.bioon.com/webeditor/uploadfile/201709/20170915092834974.jpg\"/> 在这周举行的RAPS监管大会上，生物类似药和生物原研药的相互替换性（interchangeable）成了监管当局和业界人士的聚焦热点。 美国食品和药物管理局（FDA）最近发布的FDA关于互换性的行业准则草案，概述了生物类似药获得互换性所需的研究，包括使用切换研究（Switching Study），<A class=channel_keylink target=_blank href=\"http://news.bioon.com/fda/\">FDA</A>治疗生物制剂办公室副主任Leah Christl在会上介绍了这个草案。 她指");
		a.setDimension("科学研究");
		a.setIndustry("高科技");
		a.setIndustryLabel("新能源");
		a.setPublishTime("2017-09-19");
		a.setPublishDate("2017-09-19");
		baseElasticsearch.save(a);
	}
	@Test
	public void testFind(){
		Iterable<AITInfo> it = baseElasticsearch.findAll();
		it.forEach((a)->{
			if(StringUtil.isEmpty(a.getSummary())){
				baseElasticsearch.delete(a);
			}
		});
		
	}
	@Test
	public void testB(){
		String str = "{\"summary\":\"\",\"emotion\":\"negative\",\"business\":\"乐视网（天津）\",\"publishDate\":\"2017-09-08 02:36:51\",\"id\":\"110664d58ea192f4e414b98fb1bb1a607dd3cd88\",\"title\":\"乐视网中报政府补助转为“零” 变创业板亏损王\",\"content\":\"<a hidefocus=\"true\" class=\"hqimg_client\" href=\"http://m.sina.com.cn/m/finance.html\" target='_blank' suda-uatrack=\"key=finance_stock_hotcol&value=6\" >客户端</a>\n  ■本报记者 矫 月\n  据《证券日报》记者整理<a href=http://finance.sina.com.cn/realstock/company/sz300033/nc.shtml class=\"keyword\" target=_blank>同花顺</a>统计数据得知，2017年上半年，有2649家上市公司获得政府补助，其中，<a href=http://finance.sina.com.cn/realstock/company/sh601211/nc.shtml class=\"keyword\" target=_blank>国泰君安</a>获得7.04亿元的政府补助，是2017年上半年获得政府补助最高的A股上市公司。\n  而与国泰君安待遇相反的<a href=http://finance.sina.com.cn/realstock/company/sz300104/nc.shtml class=\"keyword\" target=_blank>乐视网</a>则是一分钱也没从政府的兜里拿到。据乐视网中报显示，公司2017年上半年的政府补助为零，而在2016年上半年，公司还获得1792.05万元的政府补助。\n  据《证券日报》记者查阅公司财报发现，2016年上半年，公司曾因2016年度<a href=http://finance.sina.com.cn/realstock/company/sz000931/nc.shtml class=\"keyword\" target=_blank>中关村</a>技术创新能力建设、北京影视出版创作基金2016年度扶持影视项目、天津生态城旗舰性国际合作项目、税收奖励款、影视产业发展基金等项目获得政府补助。\n  但是，在2017年上半年，公司在中报中列举的9个补助项目中，没有一个项目在今年上半年获得政府补助。\n  值得注意的是，乐视网获得政府零补助的期间，正是乐视网归属于母公司所有者的净利润是首次出现亏损的时期。\n  中报显示，乐视网在2017年上半年实现的营业收入约为55.79亿元，同比下滑44.56%；归属于母公司所有者的净利润亏损金额约6.37亿元。\n  对于公司业绩突然报亏的原因，乐视网表示：“是由于受到乐视体系关联方资金状况的影响，加之公司品牌受到一定冲击，随之客户黏性出现波动，公司的广告收入、终端收入以及会员收入均出现较大幅度的下滑。”\n  另外，乐视网还表示，为了坚持精品内容的独播策略，公司在二季度基本未对外进行版权分销业务，导致版权分销收入同期也大幅下滑。此外，乐视网资产减值损失计提规模较大也对公司的业绩产生了一定的影响。据悉，乐视网上半年共计提资产减值准备约为2.4亿元。\n  事实上，目前的乐视网正处于风雨飘摇的时期。公司的第一大股东贾跃亭长期滞留海外，至今未归，而公司却传出了控股权之争的消息。\n  据报道，孙宏斌近日在融创业绩发布会上公开表示称“新乐视包括乐视网、乐视致新、乐视影业和乐视云，我相信这个新体系将来一定是有价值的。新的乐视商业模式完全不同，不做平台做内容，这一块BAT谁都没有，BAT都要来抢的。商业模式稍微一改就是赚钱的。”\n  但已经巨亏且并未获得政府补助支持的乐视网何时能走出亏损阴影还需观察。\n进入<a href='http://guba.sina.com.cn' target='_blank'>【新浪财经股吧】</a>讨论\"}";
		JSONObject obj = JSONObject.parseObject(str);
		AITInfo a = new AITInfo();
		a.setSummary(obj.getString("summary"));
		a.setContent(obj.getString("content"));
		a.setPublishDate(obj.getString("publishDate"));
		a.setPublishTime(obj.getString("publishTime"));
		a.setEmotion(obj.getString("emotion"));
		baseElasticsearch.save(a);
	}
	@Test
	public void testDelete(){
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("dimension", "产业头条"));
		bq.must(QueryBuilders.termQuery("industry", "高科技"));
		bq.must(QueryBuilders.termQuery("industryLabel", "新能源"));
		Iterable<AITInfo> it = baseElasticsearch.search(bq);
		LinkedHashMap<Integer, AITInfo> map = new LinkedHashMap<>();
		int count = 0;
		it.forEach((a)->{
			int key = a.getTitle().hashCode();
			if(map.containsKey(key)){
				baseElasticsearch.delete(a);
				System.out.println("删除:"+(count + 1));
			}else{
				map.put(key, a);
			}
		});
	}
}
