package com.huishu.ait.controller.supervise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.dto.BusinessSuperviseDTO;
import com.huishu.ait.service.supervise.BusinessService;

/**
 * 园区监管-企业 相关接口
 * @author jdz
 * @createDate 2017-8-3
 * @version 1.0
 */
@RestController
@RequestMapping("business")
public class BusinessController extends BaseController {
    
    /* 日志 */
    private static Logger LOGGER = LoggerFactory.getLogger(BusinessController.class);
    
    @Autowired
    private BusinessService businessService;
    
    
    /**
     * 获取园区企业动态列表 或者单企业动态列表 
     * park && ！business 园区内企业动态  ; ！park && business 单个企业动态列表 加上emotion就添加了情感信息
     * @author jdz
     * @param dto 企业监管DTO
     * @return
     * @createDate 2017-8-3
     */
    @RequestMapping(value="/getBusinessBehaviours.json",method=RequestMethod.POST)
    public AjaxResult getBusinessBehaviours(BusinessSuperviseDTO dto){
        try{
            if(null == dto){
                return error(MsgConstant.ILLEGAL_PARAM);
            }
            dto = initPage(dto);
            dto.setDimension("园区动态");
            Page<AITInfo> pagedata = businessService.getBusinessBehaviours(dto);
            return success(pagedata).setSuccess(true);
        }catch(Exception e){
            LOGGER.error("获取园区内企业动态列表或企业动态列表失败 ",e);
            return error("获取动态列表失败");
        }
    }

    /**
     * @author jdz 
     * @param id 企业动态详情
     * @return 企业动态详情
     * createDate 2017-8-3
     */ 
    @RequestMapping(value="/getBusinessBehaviourDetail.json",method=RequestMethod.GET)
    public AjaxResult getBusinessBehaviourDetail(String id){
        try{
            if(null == id || StringUtil.isEmpty(id)){
                return error(MsgConstant.ILLEGAL_PARAM);
            }
            JSONArray array = businessService.getBusinessBehaviourDetail(id);
            return success(array);
        }
        catch(Exception e){
            LOGGER.error("获取企业动态详情失败：",e);
            return error(MsgConstant.ILLEGAL_PARAM);
        }
    }
    /**
     * 
     * @param dto msg[park,keyword]
     * @return
     */
    @RequestMapping(value="/searchBusinessBehaviours.json", method = RequestMethod.POST)
    public AjaxResult searchBusinessBehaviours(BusinessSuperviseDTO dto){
        
        if (null == dto) {
            return error(MsgConstant.ILLEGAL_PARAM);
        }
        //获取参数数组
        String[] msg = dto.getMsg();
        //分别赋值
        if (StringUtil.isEmpty(msg[0])) {
            return error(MsgConstant.ILLEGAL_PARAM);
        }else {
            dto.setPark(msg[0]);
        }
        if (!StringUtil.isEmpty(msg[1])) {
            dto.setKeyword(msg[1]);
        }
        try {
            Page<AITInfo> page = null;
            dto.setEmotion("园区动态");
            dto = initPage(dto);
            page = businessService.searchBusinessBehaviours(dto);
            
            return success(page).setSuccess(true);
        } catch (Exception e) {
            LOGGER.error("关键字查询企业动态列表失败：", e);
            return error(MsgConstant.ILLEGAL_PARAM);
        }
    }
    
    /**
     * 分页初始化
     */
    private BusinessSuperviseDTO initPage(BusinessSuperviseDTO dto){
        
        if(dto.getPageNumber()==null){
            dto.setPageNumber(0);
        }
        if(dto.getPageSize()==null){
            dto.setPageSize(15);
        }
        if(dto.getPageSize()>1000){
            dto.setPageSize(1000);
        }
        return dto;
    }
}
