package com.huishu.ait.security;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuwei
 * 
 */
public abstract class CheckCodeUtil {

	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

	/**
	 * 验证码图片的宽度。
	 */
	private static int width = 105;
	/**
	 * 验证码图片的高度。
	 */
	private static int height = 29;
	/**
	 * 验证码的数量。
	 */
	private static Random random = new Random();

	public CheckCodeUtil() {
	}

	/**
	 * 生成随机颜色
	 * 
	 * @param fc
	 *            前景色
	 * @param bc
	 *            背景色
	 * @return Color对象，此Color对象是RGB形式的。
	 */
	public static Color getRandomColor(int fc, int bc) {
		if (fc > 255)
			fc = 200;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 绘制干扰线
	 * 
	 * @param g
	 *            Graphics2D对象，用来绘制图像
	 * @param nums
	 *            干扰线的条数
	 */
	public static void drawRandomLines(Graphics2D g, int nums) {
		g.setColor(getRandomColor(160, 200));
		for (int i = 0; i < nums; i++) {
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			int x2 = random.nextInt(12);
			int y2 = random.nextInt(12);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	/**
	 * 获取随机字符串， 此函数可以产生由大小写字母，汉字，数字组成的字符串
	 * 
	 * @param length
	 *            随机字符串的长度
	 * @return 随机字符串
	 */
	public static String drawRandomString(int length, Graphics2D g) {
		StringBuffer strbuf = new StringBuffer();
		String temp = null;
		int itmp = 0;
		for (int i = 0; i < length; i++) {
			switch (random.nextInt(5)) {
			case 1: // 生成A～Z的字母
				itmp = random.nextInt(26) + 65;
				temp = String.valueOf((char) itmp);
				break;
			case 2:
				itmp = random.nextInt(26) + 97;
				temp = String.valueOf((char) itmp);
				break;
			default:
				itmp = random.nextInt(10) + 48;
				temp = String.valueOf((char) itmp);
				break;
			}
			Color color = new Color(20 + random.nextInt(20), 20 + random.nextInt(20), 20 + random.nextInt(20));
			g.setColor(color);
			// 想文字旋转一定的角度
			AffineTransform trans = new AffineTransform();
			trans.rotate(random.nextInt(45) * 3.14 / 180, 15 * i + 8, 7);
			// 缩放文字
			float scaleSize = random.nextFloat() + 0.8f;
			if (scaleSize > 1f)
				scaleSize = 1f;
			trans.scale(scaleSize, scaleSize);
			g.setTransform(trans);
			g.drawString(temp, 15 * i + 18, 14);
			strbuf.append(temp);
		}
		g.dispose();
		return strbuf.toString();
	}

	public static String getImageCode(HttpServletResponse response) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics2D g = image.createGraphics();
		// 定义字体样式
		Font myFont = new Font(Font.DIALOG, Font.BOLD, 20);
		// 设置字体
		g.setFont(myFont);
		g.setColor(getRandomColor(200, 250));
		// 绘制背景
		g.fillRect(0, 0, width, height);
		g.setColor(getRandomColor(180, 200));
		drawRandomLines(g, 160);
		String code = drawRandomString(4, g);
		g.dispose();
		try {
			// Set to expire far in the past.
			response.setDateHeader("Expires", 0);
			// Set standard HTTP/1.1 no-cache headers.
			response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			// Set standard HTTP/1.0 no-cache header.
			response.setHeader("Pragma", "no-cache");
			// return a jpeg
			response.setContentType("image/jpeg");
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}

	/**
	 * 随机生成6位数短信验证码
	 * 
	 * @return
	 */
	public static String getRandomPhoneCaptcha() {
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			code.append(random.nextInt(10));
		}
		return code.toString();
	}

}
