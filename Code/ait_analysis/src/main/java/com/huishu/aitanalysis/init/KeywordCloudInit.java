package com.huishu.aitanalysis.init;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.merchantKey.articleToKeywordCloud.ArticleConToKeywordCloud;
import com.merchantKey.itemModel.KeywordModel;

/**
 * @author hhy
 * @date 2017年9月19日
 * @Parem
 * @return
 * 
 */
@Component
@Order(value = 1)
public class KeywordCloudInit implements ApplicationRunner {
	private static Logger LOGGER = Logger.getLogger(KeywordCloudInit.class);

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOGGER.debug("==========加载分析关键词程序开始===================");
		List<String> contentList = new ArrayList<String>();
		contentList.add(
				"本报记者 王丹 实习生 黄佶滢 上海报道 又到了半年度的盘点季。截至8月30日，半年报披露接近尾声，公募基金半年报于前一日披露完毕，随着各项统计数据的出炉，包括公募基金在内，几大类重要机构上半年的投资路线清晰浮水。");
		JSONObject keywordCloud = ArticleConToKeywordCloud.toKeywordCloud(contentList, 0, 50);
		LOGGER.info("分析结果为：" + (List<KeywordModel>) keywordCloud.get("result"));
		LOGGER.debug("==========加载分析关键词程序结束===================");

	}

}
