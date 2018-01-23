package com.huishu.aitanalysis.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.huishu.aitanalysis.util.EmptyUtils;



public class ConfigManager {
	private static final Logger _log = Logger.getRootLogger();
	private static final String DEFAULT_PATH = "config.properties";
	private static ConfigManager instance;

	private Properties config;

	private ConfigManager() {
		config = new Properties();
		config(null);
	}

	public static ConfigManager getInstance() {
		if (instance == null) {
			instance = new ConfigManager();
		}
		return instance;
	}

	/**
	 * 读取配置文件
	 * 
	 * @param path
	 *            配置文件classpath路径下相对地址
	 * @return
	 */
	public void config(String path) {
		path = EmptyUtils.isEmpty(path) ? DEFAULT_PATH : path;
		InputStream in = null;
		try {
			Properties tmp = new Properties();
			in = ConfigManager.class.getClassLoader().getResourceAsStream(path);
			tmp.load(in);
			config = tmp;
		} catch (Exception e) {
			_log.error("load config file [" + path + "] error -> " + e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					_log.error("close inStream error");
				}
			}
		}
	}

	/**
	 * 读取配置文件
	 * 
	 * @param key
	 *            配置信息键
	 * @return 配置信息值
	 */
	public String get(String key) {
		return config.getProperty(key);
	}
}
