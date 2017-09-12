package com.huishu.ait.common.util.exporter;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import com.huishu.ait.common.export.ContentSub;
import com.huishu.ait.common.export.News;
import com.huishu.ait.common.util.StringUtil;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;

/**
 * 导出word公共方法
 * 
 * @version 1.0
 * 
 * @author cmx
 *
 */
public class WordExporter {
	private OutputStream os;
	// 创建Document对象（word文档）
	private Document document;

	public WordExporter(OutputStream os, Document document) {
		super();
		this.os = os;
		this.document = document;
	}

	/**
	 * 导出图片，且图片格式为png
	 */
	public void exportImage(HttpServletRequest request, String echartImage, HttpServletResponse response,
			String fileName, String type) {
		try {
			// 建立一个书写器与document对象关联，通过书写器可以将文档写入到输出流中
			if ("word".equals(type)) {
				RtfWriter2.getInstance(document, os);
			} else if ("pdf".equals(type)) {
				PdfWriter.getInstance(document, os);
			}
			document.open();
			// echartImage = "/9j/"+echartImage;
			byte[] buffer = Base64.decodeBase64(echartImage);
			// 添加图片
			Image img = Image.getInstance(buffer);
			img.setAbsolutePosition(0, 0);
			img.setAlignment(Image.ALIGN_CENTER);
			img.scaleAbsolute(100, 100);
			img.scalePercent(50);
			img.scalePercent(50, 50);
			img.setRotation(30);
			img.setOriginalType(Image.ORIGINAL_PNG);
			document.add(img);
			setHeader(request, response, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
			System.out.println("完成");
		}
	}

	/**
	 * 添加图片，且图片格式为png
	 * 
	 * @param echartImage
	 */
	public void addPNGImage(String titleString, String echartImage) {
		try {
			if (StringUtils.isNotBlank(titleString)) {
				/** 标题字体 */
				RtfFont titleFont = new RtfFont("微 软 雅 黑", 16, Font.BOLD, Color.BLACK);
				/** 第一行（标题） */
				Paragraph title = new Paragraph(titleString);
				// 设置标题格式对其方式
				title.setAlignment(Element.ALIGN_CENTER);
				title.setFont(titleFont);
				document.add(title);
			}

			if (StringUtils.isNotBlank(echartImage)) {
				// String[] arr = echartImage.split("base64,");
				// if (arr.length > 1) {
				byte[] buffer = Base64.decodeBase64(echartImage);
				// 添加图片
				Image img = Image.getInstance(buffer);
				img.setAbsolutePosition(0, 0);
				img.setAlignment(Image.ALIGN_CENTER);
				img.scaleAbsolute(100, 100);
				img.scalePercent(100);// 设置图片按原图大小显示
				img.setRotation(30);
				img.setOriginalType(Image.ORIGINAL_PNG);
				document.add(img);
			}
			// }
			Paragraph context = new Paragraph("dewfejfef");
			// 离上一段落（标题）空的行数
			context.setSpacingBefore(20);
			// 设置第一行空的列数
			context.setFirstLineIndent(20);
			document.add(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exportWord(HttpServletRequest request, News artic, HttpServletResponse response, String fileName,
			String type) {
		try {
			// 建立一个书写器与document对象关联，通过书写器可以将文档写入到输出流中
			if ("doc".equals(type)) {
				RtfWriter2.getInstance(document, os);
			} else if ("pdf".equals(type)) {
				PdfWriter.getInstance(document, os);
			}
			document.open();
			// 设置中文字体
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			// 标题字体风格
			Font titleFont = new Font(bfChinese, 12, Font.BOLD);
			// 正文字体风格
			Font contentFont = new Font(bfChinese, 10, Font.NORMAL);
			Paragraph title = new Paragraph(artic.getTitle(), titleFont);
			// 设置标题格式对齐方式
			title.setAlignment(Element.ALIGN_CENTER);
			title.setFont(titleFont);
			document.add(title);
			// 设置发布日期
			Paragraph publishTime = new Paragraph(artic.getPublishTime(), contentFont);
			publishTime.setAlignment(Element.ALIGN_CENTER);
			publishTime.setFont(contentFont);
			document.add(publishTime);
			String contentTxt = artic.getContent();
			if (contentTxt.contains("<img")) {
				List<ContentSub> contentList = getContentSplitByImg(contentTxt);
				contentList.forEach(sub -> {
					try {
						Paragraph content = new Paragraph(sub.getContent(), contentFont);
						document.add(content);
						Image img = Image.getInstance(new URL(sub.getSrc()));
						document.add(img);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			} else {
				Paragraph content = new Paragraph(contentTxt, contentFont);
				// 正文格式左对齐
				content.setAlignment(Element.ALIGN_LEFT);
				content.setFont(contentFont);
				// 离上一段落（标题）空的行数
				content.setSpacingBefore(20);
				// 设置第一行空的列数
				content.setFirstLineIndent(20);
				document.add(content);
			}

			// 设置情报采集
			Paragraph source = new Paragraph("情报采集:" + artic.getSource(), contentFont);
			publishTime.setAlignment(Element.ALIGN_CENTER);
			publishTime.setFont(contentFont);
			document.add(source);
			// 设置情报原址
			Paragraph sourceLink = new Paragraph("情报原址:" + artic.getSourceLink(), contentFont);
			publishTime.setAlignment(Element.ALIGN_CENTER);
			publishTime.setFont(contentFont);
			document.add(sourceLink);
			// URL url = new
			// URL("http://i3.sinaimg.cn/cj/2013/0304/U5566P1081DT20130304094711.jpg");
			// Image img = Image.getInstance(url);
			// document.add(img);
			setHeader(request, response, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
			System.out.println("完成");
		}
	}

	public void ready() {
		Rectangle rectPageSize = new Rectangle(PageSize.A4);
		rectPageSize = rectPageSize.rotate();
		/** 建立一个书写器与document对象关联，通过书写器可以将文档写入到输出流中 */
		RtfWriter2.getInstance(document, os);
		document.open();
	}

	public void exportWordTable(HttpServletRequest request, HttpServletResponse response, String fileName,
			String tableTitle, String[] columnNames, String[] mappingKeys, List<Map<String, Object>> dataList) {
		try {
			/** 创建Document对象（word文档） */
			Rectangle rectPageSize = new Rectangle(PageSize.A4);
			rectPageSize = rectPageSize.rotate();
			/** 建立一个书写器与document对象关联，通过书写器可以将文档写入到输出流中 */
			RtfWriter2.getInstance(document, os);
			document.open();
			/** 标题字体 */
			RtfFont titleFont = new RtfFont("微 软 雅 黑", 16, Font.BOLD, Color.BLACK);
			RtfFont columnNameFont = new RtfFont("微 软 雅 黑", 12, Font.BOLD, Color.BLACK);
			RtfFont contentFont = new RtfFont("微 软 雅 黑", 12, Font.NORMAL, Color.BLACK);
			Table table = getTable(columnNames, dataList);
			/** 第一行（标题） */
			Paragraph title = new Paragraph(tableTitle);
			// 设置标题格式对其方式
			title.setAlignment(Element.ALIGN_CENTER);
			title.setFont(titleFont);
			document.add(title);
			// 设置第一行空的列数（缩进）
			// context.setFirstLineIndent(20);
			Cell cell = null;
			if (null != columnNames && columnNames.length > 0) {
				for (int i = 0; i < columnNames.length; i++) {
					Paragraph p = new Paragraph(columnNames[i], columnNameFont);
					p.setAlignment(Element.ALIGN_CENTER);
					p.setFont(columnNameFont);
					cell = new Cell(p);
					cell.setHeader(true);
					table.addCell(cell);
				}
			}
			if (null != dataList && dataList.size() > 0) {
				for (int i = 0; i < dataList.size(); i++) {
					Map<String, Object> map = dataList.get(i);
					if (null != map && map.size() > 0 && null != mappingKeys && mappingKeys.length > 0) {
						for (int j = 0; j < mappingKeys.length; j++) {
							Paragraph p = null;
							Object value = map.get(mappingKeys[j]);
							if (null != value) {
								if (j == 0) {
									p = new Paragraph(value.toString(), columnNameFont);
								} else {
									p = new Paragraph(value.toString(), contentFont);
								}
							} else {
								if (j == 0) {
									p = new Paragraph("", columnNameFont);
								} else {
									p = new Paragraph("", contentFont);
								}
							}
							p.setAlignment(Element.ALIGN_CENTER);
							cell = new Cell(p);
							table.addCell(cell);
						}
					}
				}
			}

			document.add(table);
			setHeader(request, response, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
			System.out.println("完成");
		}
	}

	public void addTable(String tableTitle, String[] columnNames, String[] mappingKeys,
			List<Map<String, Object>> dataList) {
		try {
			/** 标题字体 */
			RtfFont titleFont = new RtfFont("微 软 雅 黑", 16, Font.BOLD, Color.BLACK);
			RtfFont columnNameFont = new RtfFont("微 软 雅 黑", 12, Font.BOLD, Color.BLACK);
			RtfFont contentFont = new RtfFont("微 软 雅 黑", 12, Font.NORMAL, Color.BLACK);
			Table table = getTable(columnNames, dataList);
			/** 第一行（标题） */
			Paragraph title = new Paragraph(tableTitle, titleFont);
			// 设置标题格式对其方式
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			// 设置第一行空的列数（缩进）
			// context.setFirstLineIndent(20);
			Cell cell = null;
			if (null != columnNames && columnNames.length > 0) {
				for (int i = 0; i < columnNames.length; i++) {
					Paragraph p = new Paragraph(columnNames[i], columnNameFont);
					p.setAlignment(Element.ALIGN_CENTER);
					p.setFont(columnNameFont);
					cell = new Cell(p);
					cell.setHeader(true);
					table.addCell(cell);
				}
			}
			if (null != dataList && dataList.size() > 0) {
				for (int i = 0; i < dataList.size(); i++) {
					Map<String, Object> map = dataList.get(i);
					if (null != map && map.size() > 0 && null != mappingKeys && mappingKeys.length > 0) {
						for (int j = 0; j < mappingKeys.length; j++) {
							Paragraph p = null;
							Object value = map.get(mappingKeys[j]);
							if (null != value) {
								if (j == 0) {
									p = new Paragraph(value.toString(), columnNameFont);
								} else {
									p = new Paragraph(value.toString(), contentFont);
								}
							} else {
								if (j == 0) {
									p = new Paragraph("", columnNameFont);
								} else {
									p = new Paragraph("", contentFont);
								}
							}
							p.setAlignment(Element.ALIGN_CENTER);
							cell = new Cell(p);
							table.addCell(cell);
						}
					}
				}
			}

			document.add(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Table getTable(String[] columnNames, List<Map<String, Object>> dataList) {
		Table table = null;
		try {
			/** 表格设置 第一个参数是列，第二个参数是行 */
			table = new Table(columnNames.length, dataList.size());
			// table = new Table(4, 3);
			/** 居中显示 */
			table.setAlignment(Element.ALIGN_CENTER);
			/** 自动填满 */
			table.setAutoFillEmptyCells(true);
			table.setBorderWidth(5); // 边框宽度
			table.setBorderColor(new Color(0, 125, 255)); // 边框颜色
			table.setPadding(12);// 衬距，看效果就知道什么意思了
			table.setSpacing(0);// 即单元格之间的间距
			table.setBorder(5);// 边框
		} catch (Exception e) {
			e.printStackTrace();
		}

		return table;
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
		try {
			if (response != null) {
				String filename = "";
				if (StringUtil.isIE(request)) {
					filename = new String(fileName);
					filename = URLEncoder.encode(filename, "UTF-8");
				} else {
					filename = new String((fileName).getBytes(), "iso-8859-1");
				}
				String header = "attachment; filename=\"" + filename + "\"";
				response.reset();
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", header);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Document document = new Document();
		try {
			OutputStream os = new FileOutputStream("C:\\Users\\yindawei\\Desktop\\test.doc");
			/*
			 * String[] solu = {"车型","用户声量","媒体声量"}; String[] mapping =
			 * {"v1","v2","v3"}; List<Map<String, Object>> datas = new
			 * ArrayList<Map<String, Object>>(); Map<String,Object> map = new
			 * HashMap<String,Object>(); map.put("v1","宝马");
			 * map.put("v2","200"); map.put("v3","234"); datas.add(map);
			 * 
			 * Map<String,Object> map1 = new HashMap<String,Object>();
			 * map1.put("v1","绅宝"); map1.put("v2","456"); map1.put("v3","324");
			 * datas.add(map1);
			 * 
			 * Map<String,Object> map2 = new HMap<String,Object>();
			 * map2.put("v1","综合"); map2.put("v2","656"); map2.put("v3","558");
			 * datas.add(map2); ex.setTable(solu, mapping, datas);
			 * ex.exportWordTable("测试标题");
			 */
			WordExporter ex = new WordExporter(os, document);
			String a = ex.imageToBase64("C:\\Users\\yindawei\\Desktop\\a.jpg");
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}

	public String imageToBase64(String path) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;
		// 读取图片字节数组
		try {
			InputStream in = new FileInputStream(path);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		// 返回Base64编码过的字节数组字符串
		return Base64.encodeBase64String(data);
	}

	public void export(HttpServletRequest request, HttpServletResponse response, String fileName) throws Exception {
		setHeader(request, response, fileName);
		document.close();
	}

	private List<ContentSub> getContentSplitByImg(String str) {
		String jiequ = "";
		String shengyu = str;
		List<ContentSub> listStr = new ArrayList<>();
		String src = "";
		while (shengyu.contains("<img")) {
			ContentSub sub = new ContentSub();
			int startIndex = shengyu.indexOf("<img");
			jiequ = shengyu.substring(0, startIndex);
			sub.setContent(jiequ);
			src = shengyu.substring(shengyu.indexOf("src=\"") + 5,
					shengyu.indexOf("\"", shengyu.indexOf("src=\"") + 5));
			sub.setSrc(src);
			shengyu = shengyu.substring(shengyu.indexOf("/>") + 2);
			listStr.add(sub);
		}
		return listStr;
	}
}
