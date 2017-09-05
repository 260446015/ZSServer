package com.huishu.ait.service.personcenter;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.export.News;
import com.huishu.ait.entity.dto.PersonCollectDto;

public interface PersonCenterService {

	JSONArray getPersonCollection(PersonCollectDto dto);

	News getData(String articleId);
}
