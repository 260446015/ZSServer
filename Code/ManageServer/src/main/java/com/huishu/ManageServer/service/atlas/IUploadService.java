package com.huishu.ManageServer.service.atlas;

import com.alibaba.fastjson.JSONArray;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {

    <T>T getUploadFileJsonText(MultipartFile multipartFile);

    boolean jsonTextHandle(Object obj);


}
