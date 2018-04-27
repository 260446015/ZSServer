package com.huishu.ManageServer.service.atlas;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ManageServer.service.third.ThesaurusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public abstract class UploadAbstractService implements IUploadService{




    public boolean upLoadFile(MultipartFile multipartFile){
        Object obj = getUploadFileJsonText(multipartFile);
        return jsonTextHandle(obj);//有问题暂时只做对关键词的解析
    }

    @Override
    public abstract  <T> T getUploadFileJsonText(MultipartFile fileName);

    @Override
    public abstract boolean jsonTextHandle(Object obj);

    public void closeStream(InputStream is){
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
