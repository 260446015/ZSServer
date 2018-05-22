package com.huishu.ManageServer.service.atlas.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.common.util.POIUtils;
import com.huishu.ManageServer.service.atlas.UploadAbstractService;
import com.huishu.ManageServer.service.third.ThesaurusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;

@Service
public class KeywordService extends UploadAbstractService {
    @Autowired
    private POIUtils util;

    @Autowired
    private ThesaurusService thesaurusService;
    @Override
    public <T> T getUploadFileJsonText(MultipartFile fileName) {
        InputStream is = null;
        JSONArray jsonArray = new JSONArray();
        try {
            is = fileName.getInputStream();
            Map<Integer, String> integerStringMap = util.readExcelContentXls(is, "yindawei");
            for (Map.Entry<Integer, String> entry: integerStringMap.entrySet()) {
                try {
                    String[] split = entry.getValue().split("yindawei");
                    JSONArray readArr = null;
                    JSONObject readObj = null;
                    try {
                        readArr = JSONArray.parseArray(split[1]);
                        if (readArr != null && readArr.size() > 0) {
                            jsonArray.addAll(readArr);
                        }
                    } catch (Exception e) {
                        try {
                            readObj = JSONObject.parseObject(split[1]);
                            if(readObj != null && readObj.size() > 0){
                                jsonArray.add(readObj);
                            }
                        } catch (Exception e1) {
                            throw e;
                        }
                    }
                } catch (Exception e) {
                    System.out.println(entry.getKey() + "~~~" + entry.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeStream(is);
        }
        return (T)jsonArray;
    }

    @Override
    public boolean jsonTextHandle(Object obj) {
        return thesaurusService.saveOrUpdateData((JSONArray) obj);
    }
}
