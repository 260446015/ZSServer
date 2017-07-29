package com.huishu.ait.echart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huishu.ait.common.conf.MsgConstant;

/**
 * echart样式配置
 */
public class EchartConfig {
	
	private static Logger LOGGER = LoggerFactory.getLogger(EchartConfig.class);
	
	private static EchartConfig config = new EchartConfig();
	
	private String color[];
	
	private Object left;
	
	private Object right;
	
	private String axisPointerType;
	
	private EchartConfig() {
		Properties prop = null;
		BufferedReader bf = null;
		URL url = null;
		
		try {
			url = EchartConfig.class.getResource("/echarts.properties");
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			prop = new Properties();
			prop.load(bf);
			
			this.color = prop.getProperty("color").split(",");
			this.left = prop.getProperty("left");
			this.right = prop.getProperty("right");
			this.axisPointerType = prop.getProperty("axisPointerType");
		
		} catch (IOException e) {
			LOGGER.error(MsgConstant.SYSTEM_ERROR, e);

		} finally {
			if (bf != null) {
				try {
					bf.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	public static EchartConfig getInstance() {
		return config;
	}
	
	public String[] getColor() {
		return color;
	}

	public void setColor(String[] color) {
		this.color = color;
	}

	public Object getLeft() {
		return left;
	}

	public void setLeft(Object left) {
		this.left = left;
	}

	public Object getRight() {
		return right;
	}

	public void setRight(Object right) {
		this.right = right;
	}

	public String getAxisPointerType() {
		return axisPointerType;
	}

	public void setAxisPointerType(String axisPointerType) {
		this.axisPointerType = axisPointerType;
	}
	
}
