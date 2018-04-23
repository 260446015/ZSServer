package com.huishu.ZSServer.controller.atlas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/atlas")
public class AtlasController {

    @GetMapping(value = "{page}")
    public ModelAndView pageMapping(@PathVariable String page,ModelAndView modelAndView,String name){
        if(page.equals("atlas")){
            modelAndView.addObject("name",name);
        }
        modelAndView.setViewName("/atlas/"+page);
        return modelAndView;
    }
}
