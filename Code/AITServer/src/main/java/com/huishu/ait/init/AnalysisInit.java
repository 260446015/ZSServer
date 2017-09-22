package com.huishu.ait.init;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;

/**
 * @author hhy
 * @date 2017年9月19日
 * @Parem
 * @return
 * 
 */
@Component
@Order(value = 2)
public class AnalysisInit implements ApplicationRunner {
	private static Logger LOGGER = Logger.getLogger(AnalysisInit.class);

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOGGER.debug("===========加载获取公司名称方法开始=================");
		JSONObject company = Analysis.getCompany("基金等机构持仓底牌揭晓：茅台招行平安",
				"本报记者 王丹 实习生 黄佶滢 上海报道 又到了半年度的盘点季。截至8月30日，半年报披露接近尾声，公募基金半年报于前一日披露完毕，随着各项统计数据的出炉，包括公募基金在内，几大类重要机构上半年的投资路线清晰浮水。");
		LOGGER.info("获取的公司名称为：" + (List<String>) company.get("result"));

		LOGGER.debug("===========加载获取公司名称方法结束=================");
	}

}
