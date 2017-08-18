package com.huishu.ait.controller.industry;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.ConcersUtils.DateUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.IndustrialPolicyDTO;
import com.huishu.ait.service.industrialPolicy.IndustrialPolicyService;

/**
 * 产业政策相关接口
 * @author jdz
 * @version 1.0Bate
 * @CreateTime 2017-7-27 17:18:16
 */
@Controller
@RequestMapping(value="/apis/industry")
public class IndustrialController extends BaseController {

    //加载日志
    private static Logger log = LoggerFactory.getLogger(IndustrialController.class);
    
    @Autowired      //产业政策service
    private IndustrialPolicyService industrialPolicyService;
    
    /**
     * 获取产业政策列表接口
     * @param IndustrialPolicyDTO 产业政策查询对象
     * @return AjaxResult 返回一个对象，里面是数据
     */
    @RequestMapping(value="getIndustrialPolicyList.json", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult getIndustrialPolicyList(@RequestBody IndustrialPolicyDTO dto){
        try{
            String[] labels = dto.getMsg();
            dto.setIndustry(labels[0]);
            dto.setIndustryLabel(labels[1]);
//            dto.setPeriodDate(labels[2]);
            dto.setArea(labels[2]);
            dto.setPeriodDate("不限");
            
            JSONArray array = new JSONArray();
            
            dto = pageInit(dto);
            if (dto.getPeriodDate() != null){
                dto = dateInit(dto);
                dto.setPeriodDate(null);
            }
            Boolean b = checkPolicyDTO(dto);
            if (b == true){
                /** 创建一个 indusPolList对象，用于存储产业政策文章列表 */
                array = industrialPolicyService.getIndustrialPolicyList(dto);
                return success(array).setSuccess(true);
            }
            else {
                return error(MsgConstant.ILLEGAL_PARAM);
            }
        } catch(Exception e) {
            log.error("获取产业政策列表失败："+e.getMessage());
            return error("获取产业政策列表失败"); 
        }
    }
    /**
     * 获取产业政策详情
     * @param id 产业政策id
     * @return
     */
    @RequestMapping(value="getIndustrialPolicyDetailById.json", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getIndustrialPolicyDetailById(String id){
        try{
            if(id!="" || id!=null){
                return success(industrialPolicyService.getIndustrialPolicyDetailById(id));
            }else{
                return error(MsgConstant.ILLEGAL_PARAM);
            }
        }catch(Exception e){
            log.error("获取产业政策详情失败："+e.getMessage());
            return error("获取产业政策详情失败");    
        }
    }
    
    /**
     * 分页初始处理
     */
    private IndustrialPolicyDTO pageInit(IndustrialPolicyDTO dto){
        /*
         * 如果没传入当前页，将当前页设定为0
         * 如果没有单页最大数据量，将数据量设定为15
         * 如果单页数据量大于1000，将其设定为1000
         */
        if(dto.getPageNumber()==null){
            dto.setPageNumber(0);
        }
        if(dto.getPageSize()==null){
            dto.setPageSize(10);
        }
        if(dto.getPageSize()>1000){
            dto.setPageSize(1000);
        }
        return dto;
    }
    
    /**
     * 时间初始处理 yyyy-MM-dd HH-mm-ss
     */
    private IndustrialPolicyDTO dateTimeInit(IndustrialPolicyDTO dto){
        Date date = new Date();
        String endTime = DateUtil.getFormatDate(date, DateUtil.FORMAT_TIME); //今天的当前时间（获取服务端时间）
        String startTime = DateUtil.getFormatDate(DateUtil.getStartTime(), DateUtil.FORMAT_TIME); //今天的起始时间
        String yesterAgo = DateUtil.getFormatDate(DateUtil.getYesterAgoStartTime(date), DateUtil.FORMAT_TIME); //昨天的起始时间
        String weekAgo = DateUtil.getFormatDate(DateUtil.getYearStartTime(date), DateUtil.FORMAT_TIME); //近7天的起始时间
        String monthAgo = DateUtil.getFormatDate(DateUtil.getMonthAgoStartTime(date), DateUtil.FORMAT_TIME); //一个月内
        String halfYearAgo = DateUtil.getFormatDate(DateUtil.getHalfYearStartTime(date), DateUtil.FORMAT_TIME); //半年内
        String yearAgo = DateUtil.getFormatDate(DateUtil.getYearStartTime(date), DateUtil.FORMAT_TIME); //一年内
        
        //对时间段进行判断
        if(dto.getPeriodDate().equals("今日")){
            dto.setStartDate(startTime);
            dto.setEndDate(endTime);
        }
        if(dto.getPeriodDate().equals("昨天")){
            dto.setStartDate(yesterAgo);
            dto.setEndDate(startTime);
        }
        if(dto.getPeriodDate().equals("近7天")){
            dto.setStartDate(weekAgo);
            dto.setEndDate(endTime);
        }
        if(dto.getPeriodDate().equals("1个月")){
            dto.setStartDate(monthAgo);
            dto.setEndDate(endTime);
        }
        if(dto.getPeriodDate().equals("半年")){
            dto.setStartDate(halfYearAgo);
            dto.setEndDate(endTime);
        }
        if(dto.getPeriodDate().equals("一年")){
            dto.setStartDate(yearAgo);
            dto.setEndDate(endTime);
        }
        if(dto.getPeriodDate().equals("不限")){
            dto.setStartDate("1980-01-01 00:00:01");
            dto.setEndDate(endTime);
        }
        return dto;
    }
    
    /**
     * 时间初始处理 yyyy-MM-dd
     * @param dto
     * @return
     */
    private IndustrialPolicyDTO dateInit(IndustrialPolicyDTO dto){
        Date date = new Date();
        String endTime = DateUtil.getFormatDate(date, DateUtil.FORMAT_DATE); //今天的当前时间（获取服务端时间）
        String startTime = DateUtil.getFormatDate(DateUtil.getStartTime(), DateUtil.FORMAT_DATE); //今天的起始时间
        String yesterAgo = DateUtil.getFormatDate(DateUtil.getYesterAgoStartTime(date), DateUtil.FORMAT_DATE); //昨天的起始时间
        String weekAgo = DateUtil.getFormatDate(DateUtil.getYearStartTime(date), DateUtil.FORMAT_DATE); //近7天的起始时间
        String monthAgo = DateUtil.getFormatDate(DateUtil.getMonthAgoStartTime(date), DateUtil.FORMAT_DATE); //一个月内
        String halfYearAgo = DateUtil.getFormatDate(DateUtil.getHalfYearStartTime(date), DateUtil.FORMAT_DATE); //半年内
        String yearAgo = DateUtil.getFormatDate(DateUtil.getYearStartTime(date), DateUtil.FORMAT_DATE); //一年内
        
        //对时间段进行判断
        if(dto.getPeriodDate().equals("今日")){
            dto.setStartDate(startTime);
            dto.setEndDate(endTime);
        }
        if(dto.getPeriodDate().equals("昨天")){
            dto.setStartDate(yesterAgo);
            dto.setEndDate(startTime);
        }
        if(dto.getPeriodDate().equals("近7天")){
            dto.setStartDate(weekAgo);
            dto.setEndDate(endTime);
        }
        if(dto.getPeriodDate().equals("1个月")){
            dto.setStartDate(monthAgo);
            dto.setEndDate(endTime);
        }
        if(dto.getPeriodDate().equals("半年")){
            dto.setStartDate(halfYearAgo);
            dto.setEndDate(endTime);
        }
        if(dto.getPeriodDate().equals("一年")){
            dto.setStartDate(yearAgo);
            dto.setEndDate(endTime);
        }
        if(dto.getPeriodDate().equals("不限")){
            dto.setStartDate("1980-01-01");
            dto.setEndDate(endTime);
        }
        return dto;
    }
}
