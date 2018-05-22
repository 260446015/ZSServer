package com.huishu.ManageServer.controller.atlas;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.service.atlas.impl.KeywordService;
import com.huishu.ManageServer.service.atlas.impl.RelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/apis/atlas")
public class AtlasController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AtlasController.class);

    @Autowired
    private KeywordService keywordService;
    @Autowired
    private RelationService relationService;

    @PostMapping(value = "/updataAtlas.json")
    public AjaxResult updataAtlas(@RequestParam("fileName") MultipartFile fileName){
        if(fileName.getOriginalFilename().contains("关键词")){
            return success(keywordService.upLoadFile(fileName));
        }else if(fileName.getOriginalFilename().contains("词性")){
            return success(relationService.upLoadFile(fileName));
        }else{
            return error("上传文本不对");
        }
    }
}
