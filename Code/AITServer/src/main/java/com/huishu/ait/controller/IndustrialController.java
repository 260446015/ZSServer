package com.huishu.ait.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.IndustriaPolicyDTO;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.service.industrialPolicy.IndustrialPolicyService;
import com.huishu.ait.common.conf.ConfConstant;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.ConcersUtils.DateUtil;

/**
 * 产业政策相关接口
 * @author jdz
 * @version 1.0Bate
 * @CreateTime 2017-7-27 17:18:16
 */
@Controller
@RequestMapping(value="industriaPolicy")
public class IndustrialController extends BaseController {

    //加载日志
    private static Logger log = LoggerFactory.getLogger(IndustrialController.class);
    
    @Autowired      //产业政策service
    private IndustrialPolicyService industrialPolicyService;
    
    /**
     * 分页初始处理
     */
    private IndustriaPolicyDTO pageInit(IndustriaPolicyDTO dto){
        /*
         * 如果没传入当前页，将当前页设定为0
         * 如果没有单页最大数据量，将数据量设定为15
         * 如果单页数据量大于1000，将其设定为1000
         */
        if(dto.getCurrentPage()==null){
            dto.setCurrentPage(0);
        }
        if(dto.getPageSize()==null){
            dto.setPageSize(15);
        }
        if(dto.getCurrentPage()>1000){
            dto.setPageSize(1000);
        }
        return dto;
    }
    
    /**
     * 对时间阶段进行处理
     */
    private IndustriaPolicyDTO timeInit(IndustriaPolicyDTO dto){
        /*
         * 如果不传入时间段，将时间设定为 1980-1-1 00:00:00 
         * 传入今日，为今天0点到系统时间的时间段
         * 传入昨日，为昨天0点到11:59:59
         * 传入近七天为 一周前的这个时间到系统当前时间
         * 一个月 为当前月份减一 到系统当前时间
         * 半年 为当前时间月份减 6到 系统当前时间 
         * 一年  为当前时间年份 减1 到系统当前时间
         */
        if(dto.getTimeBucket()==null || dto.getTimeBucket()==""){
            try {
                dto.setStartDate(new SimpleDateFormat(DateUtil.FORMAT_TIME).parse("1980-1-1 00:00:01"));
                dto.setEndDate(new Date());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(dto.getTimeBucket().equals("今日")){
            dto.setStartDate(DateUtil.getStartTime());
            dto.setEndDate(new Date());
        }
        if(dto.getTimeBucket().equals("昨日")){
                    
        }
        if(dto.getTimeBucket().equals("近七天")){
            dto.setStartDate(DateUtil.getWeekAgoStartTime(new Date()));
            dto.setEndDate(new Date());
        }
        if(dto.getTimeBucket().equals("一个月")){
            dto.setStartDate(DateUtil.getMonthAgoStartTime(new Date()));
            dto.setEndDate(new Date());
        }
        if(dto.getTimeBucket().equals("半年")){
            dto.setStartDate(DateUtil.getHalfYearStartTime(new Date()));
            dto.setEndDate(new Date());
        }
        if(dto.getTimeBucket().equals("一年")){
            dto.setStartDate(DateUtil.getYearStartTime(new Date()));
            dto.setEndDate(new Date());
        }
        return dto;
    }
    
    
    
    
    
    /**
     * 获取产业政策列表接口
     * @param IndustriaPolicyDTO 产业政策查询对象
     * @return AjaxResult 返回一个对象，里面是数据
     */
    @ResponseBody
    @RequestMapping(value="getIndustrialPolicyList.json")
    public AjaxResult getIndustrialPolicyList(@RequestBody IndustriaPolicyDTO dto ){
        try{
            if(dto == null){
                return error(MsgConstant.ILLEGAL_PARAM);
            }
            pageInit(dto);  //页面分页初始化
            if(dto.getTimeBucket()==null || dto.getTimeBucket() == ""){
                return error(MsgConstant.ILLEGAL_PARAM);
            }
            timeInit(dto);  //时间初始化
            
            Integer currentPage = dto.getCurrentPage(),pageSize = dto.getPageSize();
            
            
            if (currentPage == null) {
                currentPage = ConfConstant.MIN_PAGE_NUMBER;
            } else if (currentPage > ConfConstant.MAX_PAGE_NUMBER) {
                currentPage = ConfConstant.MAX_PAGE_NUMBER;
            } else if (currentPage < ConfConstant.MIN_PAGE_NUMBER) {
                currentPage = ConfConstant.MIN_PAGE_NUMBER;
            }
            if (pageSize == null) {
                pageSize = ConfConstant.DEFAULT_PAGE_SIZE;
            } else if (pageSize > ConfConstant.MAX_PAGE_SIZE) {
                pageSize = ConfConstant.MAX_PAGE_SIZE;
            } else if (pageSize < ConfConstant.MIN_PAGE_SIZE) {
                pageSize = ConfConstant.MIN_PAGE_SIZE;
            }
            
            /**
             * 创建一个 indusPolList对象，用于存储产业政策文章列表
             */
            Page<AITInfo> indusPolList = null; 
            
            
            
            return null;
        }catch(Exception e){
//            e.printStackTrace();
            log.error("获取产业政策列表失败："+e.getMessage());
            return error("获取产业政策列表失败"); 
        }
    }
    
    /**
     * 获取产业政策详情
     * @param id 产业政策id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getIndustrialPolicyDetailById.json")
    public AjaxResult getIndustrialPolicyDetailById(@RequestBody String id){
        try{
            return success(industrialPolicyService.getIndustrialPolicyDetailById(id));
        }catch(Exception e){
//            e.printStackTrace();
//            System.out.println(e.getMessage());
            log.error("获取产业政策详情失败："+e.getMessage());
            return error("获取产业政策详情失败");    
        }
    }
}
