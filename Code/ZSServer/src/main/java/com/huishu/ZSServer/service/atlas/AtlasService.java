package com.huishu.ZSServer.service.atlas;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface AtlasService {

    JSONObject getAtlasAndResponse(String subject);

    String getKeyNo(String name);

    JSONArray getEncyclopediaFromDataBase(String subject, Map<String, String> relationMap);

    JSONArray getAtlasCompany(String name);
}
