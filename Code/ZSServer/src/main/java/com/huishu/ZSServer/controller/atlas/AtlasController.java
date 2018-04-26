package com.huishu.ZSServer.controller.atlas;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.service.atlas.AtlasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/apis/atlas")
public class AtlasController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AtlasController.class);

    @Autowired
    private AtlasService atlasService;


    @GetMapping(value = "/getAtlasAndResponse.json")
    @ResponseBody
    public AjaxResult getAtlasAndResponse(String subject){
        return success(atlasService.getAtlasAndResponse(subject));
    }

    @GetMapping(value = "/keyNo.json",params = {"name"})
    @ResponseBody
    public AjaxResult getKeyNo(String name){
        return success(atlasService.getKeyNo(name));
    }

    @GetMapping(value = "/getAtlasCompany.json")
    @ResponseBody
    public AjaxResult getAtlasCompany(String name){
        return success(atlasService.getAtlasCompany(name));
    }

    @GetMapping(value = "{page}")
    public String pageMapping(@PathVariable String page){
        return "/atlas/"+page;
    }
}
