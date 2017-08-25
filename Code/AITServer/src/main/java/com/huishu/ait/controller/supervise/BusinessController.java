package com.huishu.ait.controller.supervise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.CompanyCount;
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
@RequestMapping("/apis/business")
public class BusinessController extends BaseController {
    
    /* 日志 */
    private static Logger LOGGER = LoggerFactory.getLogger(BusinessController.class);
    
    @Autowired
    private BusinessService businessService;
    
    
    /**
     * 获取园区内企业动态列表 
     * park 园区内企业动态 emotion 添加情感信息
     * emotion :neutral：中立的   negative：消极的  positive：积极的
     * @author jdz
     * @param dto 企业监管DTO
     * msg[park,emotion] 
     * @return
     * @createDate 2017-8-3
     */
    @RequestMapping(value="/getBehaviours.json",method=RequestMethod.POST)
    public AjaxResult getParkEmotionBehaviours(@RequestBody BusinessSuperviseDTO dto){
        
        String park = getUserPark();
        
        if (null == dto) {
            return error(MsgConstant.ILLEGAL_PARAM);
        }
        JSONArray array = new JSONArray();
        try{
            String[] msg = dto.getMsg();
            /*if (!StringUtil.isEmpty(msg[0])) {
                dto.setPark(msg[0]);
            }*/
            dto.setPark(park);
            if (!StringUtil.isEmpty(msg[0])) {
                dto.setEmotion(msg[0]);
            }
            dto = initPage(dto);
            dto.setDimension("企业动态");
            Page<AITInfo> page = businessService.getBusinessBehaviours(dto);
            for (AITInfo p : page) {
                JSONObject map = new JSONObject();
                map.put("id", p.getId());
                map.put("business", p.getBusiness());
                map.put("emotion", p.getEmotion());
                map.put("publishDate", p.getPublishDate());
                map.put("title", p.getTitle());
                map.put("content", p.getContent());
                array.add(map);
             }
            Long totleNumber = page.getTotalElements();
            int pageSize = dto.getPageSize();
            JSONObject obj = new JSONObject();
            obj.put("page", array);
            obj.put("totalNumber", totleNumber);
            obj.put("pageSize", pageSize);
            return success(obj).setSuccess(true);
        }catch(Exception e){
            LOGGER.error("获取园区内企业动态列表失败",e);
            return error("获取动态列表失败");
        }
    }

    /**
     * 获取园区内企业动态列表
     * @param dto msg[park]
     * @return
     */
    @RequestMapping(value = "getParkBehaviours.json", method = RequestMethod.POST)
    public AjaxResult getParkBusinessBehaviours(@RequestBody BusinessSuperviseDTO dto){
        if (dto == null || dto.getMsg().length == 0) {
            return error(MsgConstant.ILLEGAL_PARAM);
        }
        String[] msg = dto.getMsg();
        JSONArray array = new JSONArray();
        try {
            dto.setPark(msg[0]);
            initPage(dto);
            dto.setDimension("园区动态");
            Page<AITInfo> page = businessService.getBusinessBehaviours(dto);
            for (AITInfo p : page) {
                JSONObject map = new JSONObject();
                map.put("id", p.getId());
                map.put("business", p.getBusiness());
                map.put("emotion", p.getEmotion());
                map.put("publishDate", p.getPublishDate());
                map.put("title", p.getTitle());
                map.put("content", p.getContent());
                array.add(map);
             }
            Long totleNumber = page.getTotalElements();
            int pageSize = dto.getPageSize();
            JSONObject obj = new JSONObject();
            obj.put("page", array);
            obj.put("totalNumber", totleNumber);
            obj.put("pageSize", pageSize);
            return success(obj).setSuccess(true);
        } catch (Exception e) {
            LOGGER.error("获取企业动态失败：",e);
            return error("获取园区内企业动态列表失败");
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
     * 关键字查询企业动态列表
     * @param dto msg[park,keyword]
     * @return
     * @createDate 2017-8-8
     */
    @RequestMapping(value="/searchBusinessBehaviours.json", method = RequestMethod.POST)
    public AjaxResult searchBusinessBehaviours(@RequestBody BusinessSuperviseDTO dto){
        
        JSONArray array = new JSONArray();
        if (null == dto) {
            return error(MsgConstant.ILLEGAL_PARAM);
        }
        //获取参数数组
        String[] msg = dto.getMsg();
        //分别赋值
        if (StringUtil.isEmpty(msg[0])) {
            return error(MsgConstant.ILLEGAL_PARAM);
        }else {
            dto.setBusiness(msg[0]);
        }
        if (!StringUtil.isEmpty(msg[1])) {
            dto.setKeyword(msg[1]);
        }
        
        try {
//            Page<AITInfo> page = null;
//            dto.setEmotion("园区动态");
            dto = initPage(dto);
            array = businessService.searchBusinessBehaviours(dto);
            
            return success(array).setSuccess(true);
        } catch (Exception e) {
            LOGGER.error("关键字查询企业动态列表失败：", e);
            return error(MsgConstant.ILLEGAL_PARAM);
        }
    }

    /**
     * 根据关键字查询企业列表
     * @param msg[关键字,行业,规模,成立时间]
     * @return
     * @author jdz
     */
    @RequestMapping(value="searchBusiness.json", method = RequestMethod.POST)
    public AjaxResult searchBusiness( /* 企业查询对象 */ ) {
        try {
            return null;
        } catch (Exception e) {
            LOGGER.error("关键字查询企业列表失败：", e);
            return error(MsgConstant.ILLEGAL_PARAM);
        }
    }
    
    /**
     * 获取搜索企业列表，按照点击量排序
     * @author jdz
     * @param 无参数 
     * @return
     */
    @RequestMapping(value = "getSearchBusinessList", method = RequestMethod.GET)
    public AjaxResult getSearchBusinessList(){
        try {
            Page<CompanyCount> page = businessService.getBusinessList();
            return success(page).setSuccess(true);
        }
        catch (Exception e) {
            LOGGER.error("获取搜索框企业列表：", e);
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
            dto.setPageSize(10);
        }
        if(dto.getPageSize()>1000){
            dto.setPageSize(1000);
        }
        return dto;
    }
}
