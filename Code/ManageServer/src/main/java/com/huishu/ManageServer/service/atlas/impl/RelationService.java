package com.huishu.ManageServer.service.atlas.impl;

import com.huishu.ManageServer.common.util.POIUtils;
import com.huishu.ManageServer.service.atlas.UploadAbstractService;
import com.huishu.ManageServer.service.third.ThesaurusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class RelationService extends UploadAbstractService{

    @Autowired
    private ThesaurusService thesaurusService;
    @Autowired
    private POIUtils util;

    @Override
    public <T> T getUploadFileJsonText(MultipartFile fileName) {
        InputStream is = null;
        Map<String,String> returnMap = new HashMap<>();
        try {
            is = fileName.getInputStream();
            Map<Integer, String> integerStringMap = util.readExcelContentXlsx(is, "~");
            integerStringMap.forEach((k, v) -> {
                try {
                    String[] split = v.split("~");
                    returnMap.put(split[0],split[1]);
                } catch (Exception e) {
                    System.out.println(k + "~~~" + v);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            super.closeStream(is);
        }
        return (T)returnMap;
    }

    @Override
    public boolean jsonTextHandle(Object obj) {
        HashMap<String,String> map = (HashMap<String,String>) obj;
        try {
            map.forEach((k,v) ->{
                thesaurusService.saveOrUpdateData(k,v);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
